package com.eazySchoolProject.controller;

import java.util.logging.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactController {
	private static   org.slf4j.Logger log =  LoggerFactory.getLogger(ContactController.class);
	
	@RequestMapping(value= {"/contact"})
	public String displayContactPage() {
		return "contact.html";
	}
	
	@PostMapping(value="/saveMsg")
	public ModelAndView saveMessage(@RequestParam String name, @RequestParam String mobileNum, @RequestParam String
			email, @RequestParam String subject, @RequestParam String message) {
		
		log.info("Name: " + name);
		log.info("Mobile Number: " + mobileNum);
		log.info("Email: " + email);
		log.info("Subject: " + subject);
		log.info("Message: " + message);
		return new ModelAndView("redirect:/contact");
		
		
		
		
	}
	
	
	
	

}
