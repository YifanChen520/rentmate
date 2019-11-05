package com.rentmate.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rentmate.dao.UserOTPRepository;
import com.rentmate.dao.UserRepository;
import com.rentmate.entity.User;
import com.rentmate.entity.UserOTP;
import com.rentmate.model.ApiResponse;
import com.rentmate.model.ForgotPasswordResponse;
import com.rentmate.model.GenerateOTPRequest;
import com.rentmate.model.LoginRequest;
import com.rentmate.model.RegistrationRequest;
import com.rentmate.model.ResetPasswordRequest;
import com.rentmate.model.SecurityQuestionAnswer;
import com.rentmate.model.UserResponse;
import com.rentmate.model.ValidateOTPRequest;

@RestController
public class RegisterLoginController {
	@Autowired
	UserRepository userRepo;
	@Autowired
	UserOTPRepository userOTPRepo;
	
	@CrossOrigin
	@PostMapping(path="/register")
	public ResponseEntity<?> register(@RequestBody RegistrationRequest registrationRequest) {
		User u = null;
		u = userRepo.findUserByEmail(registrationRequest.getEmail());	
		if(u != null) {
			return ResponseEntity.ok(new UserResponse(false, u));
		} else {
			User user = new User();
			user.setRole(registrationRequest.getRole());
			user.setFirstname(registrationRequest.getFirstname());
			user.setLastname(registrationRequest.getLastname());
			user.setUsername(registrationRequest.getFirstname().substring(0,1)+registrationRequest.getLastname());
			user.setEmail(registrationRequest.getEmail());
			user.setPassword(registrationRequest.getPassword());
			user.setPhone(registrationRequest.getPhone());
			user.setSecurityQ1(registrationRequest.getSecurityquestion1());
			user.setSecurityQ1Ans(registrationRequest.getAnswer1());
			user.setSecurityQ2(registrationRequest.getSecurityquestion2());
			user.setSecurityQ2Ans(registrationRequest.getAnswer2());
			user.setSecurityQ3(registrationRequest.getSecurityquestion3());
			user.setSecurityQ3Ans(registrationRequest.getAnswer3());
			
			u = userRepo.save(user);
			if(u!=null) {
				return ResponseEntity.ok(new UserResponse(true, u));
			} else {
				return ResponseEntity.ok(new UserResponse(false, null));
			}
		}
		
	}
	
	@CrossOrigin
	@PostMapping(path="/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
		User u = userRepo.findUserByEmailAndPassword(loginRequest.getEmail(),loginRequest.getPassword());
		if(u!=null) {
			return ResponseEntity.ok(new UserResponse(true, u));
		}
		return ResponseEntity.ok(new UserResponse(false, null));
	}
	
	@CrossOrigin
	@GetMapping(path="/forgotPassword")
	public ResponseEntity<?> forgotPassword(@RequestParam String email) {
		User u = userRepo.findUserByEmail(email);
		List<SecurityQuestionAnswer> result = new ArrayList<SecurityQuestionAnswer>();
		if(u != null) {
			SecurityQuestionAnswer sec = new SecurityQuestionAnswer();
			sec.setSecurityQuestion(u.getSecurityQ1());
			sec.setAnswer(u.getSecurityQ1Ans());
			result.add(sec);
			sec = new SecurityQuestionAnswer();
			sec.setSecurityQuestion(u.getSecurityQ2());
			sec.setAnswer(u.getSecurityQ2Ans());
			result.add(sec);
			sec = new SecurityQuestionAnswer();
			sec.setSecurityQuestion(u.getSecurityQ3());
			sec.setAnswer(u.getSecurityQ3Ans());
			result.add(sec);
			
			return ResponseEntity.ok(new ForgotPasswordResponse(true, result));
		}
		
		return ResponseEntity.ok(new ForgotPasswordResponse(false, null));
	}
	
	@CrossOrigin
	@GetMapping(path="/sendOTP")
	public ResponseEntity<?> sendOTP(@RequestParam String email) {
		User u = null;
		u = userRepo.findUserByEmail(email);	
		if(u != null) {
			Integer otp = RegisterLoginController.generateOTP();
			UserOTP uOtp = new UserOTP(email, otp);
			userOTPRepo.save(uOtp);
			setUpMailConfiguration("rentmateservices@gmail.com", "rentmate#2019", otp, email);
			return ResponseEntity.ok(new ApiResponse(true, "OTP sent Successfully"));
		} else {
			return ResponseEntity.ok(new ApiResponse(false, "email id not registered!!"));
		}
	}
	
	@CrossOrigin
	@PostMapping(path="/validateOTP")
	public ResponseEntity<?> validateOTP(@RequestBody ValidateOTPRequest validateOTPRequest) {
		UserOTP uOtp = userOTPRepo.findUserOTPByEmail(validateOTPRequest.getEmail());
		if(uOtp != null) {
			if(validateOTPRequest.getOtp() == uOtp.getOtp())
				return ResponseEntity.ok(new ApiResponse(true, "OTP matched!!"));
			else
				return ResponseEntity.ok(new ApiResponse(false, "Wrong OTP!!"));
		} else {
			return ResponseEntity.ok(new ApiResponse(false, "email id not registered"));
		}
	}
	
	@CrossOrigin
	@PostMapping(path="/resetPassword")
	public ResponseEntity<?> ResetPassword(@RequestBody ResetPasswordRequest resetPasswordPRequest) {
		userRepo.UpdatePassword(resetPasswordPRequest.getPassword(), resetPasswordPRequest.getEmail());
		
		return ResponseEntity.ok(new ApiResponse(true, "Password Changed!!"));
	}

	public static Integer generateOTP() {
		  int i;
	      Random r = new Random();
		  i = r.nextInt((9999 - 1000) + 1) + 1000;
		  return i; 
	}
			
			
	private void setUpMailConfiguration(final String email, final String password,Integer otp, final String toEmail){
        Properties props = setUpProperties();
        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        };
        Session session = Session.getDefaultInstance(props, auth);
        sendMails(session, email, "RentMate OTP",otp, toEmail);
    }
		    
    private void sendMails(Session session, String sender, String subject,Integer otp, String toEmail){
        String body = "Your OTP is " + otp.toString();
        sendEmail(session, sender, toEmail, subject, body);
    }
		    
    private Properties setUpProperties(){
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
        props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
        props.put("mail.smtp.port", "465"); //SMTP Port
        return props;
    }

	private static void sendEmail(Session session, String from, String toEmail, String subject, String body) {
        try {
            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");
            msg.setFrom(new InternetAddress(from, "RentMate"));
            msg.setReplyTo(InternetAddress.parse(from, false));
            msg.setSubject(subject, "UTF-8");
            msg.setText(body, "UTF-8");
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            Transport.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
