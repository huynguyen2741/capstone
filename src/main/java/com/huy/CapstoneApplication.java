package com.huy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootApplication
public class CapstoneApplication  {
	

	public static void main(String[] args) {
		SpringApplication.run(CapstoneApplication.class, args);
	}
	
	

}
