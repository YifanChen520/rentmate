package com.rentmate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.*;


@SpringBootApplication
@EnableJpaAuditing
@EnableAutoConfiguration
public class RentmateApp {
	public static void main(String[] args) {
        @RequestMapping("/")
        @ResponseBody
        SpringApplication.run(RentmateApp.class, args);
    }
}
