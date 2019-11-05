package com.rentmate.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rentmate.dao.ApartmentRepository;
import com.rentmate.dao.ImageRepository;
import com.rentmate.dao.PostingRepository;
import com.rentmate.dao.ReviewRepository;
import com.rentmate.dao.TourRepository;
import com.rentmate.dao.UserRepository;
import com.rentmate.entity.Apartment;
import com.rentmate.entity.Image;
import com.rentmate.entity.Posting;
import com.rentmate.entity.PostingDetails;
import com.rentmate.entity.Review;
import com.rentmate.entity.Tour;
import com.rentmate.entity.User;
import com.rentmate.model.PublicViewResponse;
import com.rentmate.model.SpecificPostingResponse;

@RestController
public class PostingController {

	@Autowired
	PostingRepository postingRepository;
	
	@Autowired
	ApartmentRepository apartmentRepository;
	
	@Autowired
	ReviewRepository reviewRepository;
	
	@Autowired
	TourRepository tourRepository;
	
	@Autowired
	ImageRepository imageRepository;
	
	@Autowired
	UserRepository userRepo;

	@CrossOrigin
	@GetMapping(path="/publicView")
	public ResponseEntity<?> getAllPostings() {
		List<PostingDetails> pDetailsList = new ArrayList<PostingDetails>();
		PostingDetails pDetails;
		
		try {
		List<Posting> postingList = null;
	    Image image= null;
	    List<Review> reviewList = null;
		postingList = (List<Posting>) postingRepository.findAll();
		if(postingList != null) {
			
			for(Posting p : postingList) {
				int stars = 0;
				pDetails = new PostingDetails();
				pDetails.setPostingId(p.getPostingId());
				pDetails.setStartAvailability(p.getStartAvailability());
				pDetails.setEndAvailability(p.getEndAvailability());
				pDetails.setApartmentId(p.getApartmentId());
				Apartment a = apartmentRepository.findApartmentByPostingId(p.getPostingId());
				
				if(a != null) {	
					image = imageRepository.findFirstByAid(a.getApartmentId());
					pDetails.setAddress(a.getAddress());
					pDetails.setManagerId(a.getManagerId());
					pDetails.setBathCount(a.getBathCount());
					pDetails.setBedCount(a.getBedCount());
					pDetails.setRent(a.getRent());
					pDetails.setImage(image);
				}
				
				reviewList = null;
				reviewList = reviewRepository.findAllByAid(p.getApartmentId());
				
				if(reviewList != null) {
					if(reviewList.size() != 0) {
						for(Review r: reviewList)
							stars += r.getRating();
						
						stars = stars / reviewList.size();
					}
				}
				pDetails.setAvgRating(stars);
				
				pDetailsList.add(pDetails);
			}
		}
		
		
		
		} catch(Exception e) {
			e.printStackTrace();
		}	
		
		if(pDetailsList.size() > 0)
			return ResponseEntity.ok(new PublicViewResponse(true, pDetailsList));
		else
			return ResponseEntity.ok(new PublicViewResponse(false, null));
	}
	
	
	@CrossOrigin
	@GetMapping(path="/specificPosting")
	public ResponseEntity<?> getSpecificPosting(@RequestParam Integer postingId) {
		PostingDetails pDetails = null;
		Apartment a = null;
		Posting p = null;
		User u = null;
		List<Review> reviewList = null;
		List<Tour> tourList = null;
		List<Image> imageList = null;
		p  = postingRepository.findPostingByPostingId(postingId);
		a = apartmentRepository.findApartmentByPostingId(p.getPostingId());
		u = userRepo.findByUid(a.getManagerId());
		reviewList = reviewRepository.findAllByAid(p.getApartmentId());
		tourList = tourRepository.findAllByAid(p.getApartmentId());
		imageList = imageRepository.findAllByAid(p.getApartmentId());
		if(a != null && p!= null) {
			pDetails = new PostingDetails();
			pDetails.setPostingId(p.getPostingId());
			pDetails.setStartAvailability(p.getStartAvailability());
			pDetails.setEndAvailability(p.getEndAvailability());
			pDetails.setApartmentId(p.getApartmentId());
			
			pDetails.setAddress(a.getAddress());
			
			pDetails.setManagerId(a.getManagerId());
			pDetails.setManagerName(u.getFirstname()+" "+u.getLastname());
			pDetails.setManagerEmail(u.getEmail());
			pDetails.setManagerPhone(u.getPhone());
			
			pDetails.setTenantId(a.getTenantId());
			pDetails.setBathCount(a.getBathCount());
			pDetails.setBedCount(a.getBedCount());
			//pDetails.setLatitude(a.getLatitude());
			//pDetails.setLongitude(a.getLongitude());
			pDetails.setRent(a.getRent());
			pDetails.setWater(a.getWater());
			pDetails.setGas(a.getGas());
			pDetails.setInternet(a.getInternet());
			pDetails.setElectric(a.getElectric());
			
			pDetails.setReviews(reviewList);
			pDetails.setTours(tourList);
			pDetails.setImages(imageList);
			
			return ResponseEntity.ok(new SpecificPostingResponse(true, pDetails));
		} else {
			return ResponseEntity.ok(new SpecificPostingResponse(false, null));
		}
		
	}
	
	
		
}
