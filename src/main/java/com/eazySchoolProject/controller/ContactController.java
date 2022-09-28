package com.eazySchoolProject.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eazySchoolProject.model.Contact;
import com.eazySchoolProject.service.ContactService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ContactController {
	
	private final ContactService cs;
	
	@Autowired
	public ContactController(ContactService cs) {
		this.cs = cs;
	}
	
	@RequestMapping(value= {"/contact"})
	public String displayContactPage(Model model) {
		model.addAttribute("contact", new Contact());
		
		return "contact";
	}
	
	@PostMapping(value="/saveMsg")
	public String saveMessage(@Valid @ModelAttribute("contact") Contact contact, Errors errors) {
		
		if (errors.hasErrors()) {
			
			log.error("Contact form validation failed due to: "+ errors.toString());
			return "contact";
			
			
		}
		
		cs.saveContent(contact);
		
		return "redirect:/contact";
		
		
		
		
	}
	
	
	
	

}
