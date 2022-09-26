package com.eazySchoolProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.eazySchoolProject.model.Contact;
import com.eazySchoolProject.service.ContactService;

@Controller
public class ContactController {
	
	private final ContactService cs;
	
	@Autowired
	public ContactController(ContactService cs) {
		this.cs = cs;
	}
	
	@RequestMapping(value= {"/contact"})
	public String displayContactPage() {
		return "contact.html";
	}
	
	@PostMapping(value="/saveMsg")
	public ModelAndView saveMessage(Contact contact) {
		
		cs.saveContent(contact);
		
		return new ModelAndView("redirect:/contact");
		
		
		
		
	}
	
	
	
	

}
