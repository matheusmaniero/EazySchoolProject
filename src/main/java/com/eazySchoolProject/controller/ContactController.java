package com.eazySchoolProject.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	
	@RequestMapping("/displayMessages")
	public ModelAndView displayMessages(Model model) {
		List<Contact> contactMsgs = cs.findWithOpenStatus();
		ModelAndView modelAndView = new ModelAndView("messages.html");
		modelAndView.addObject("contactMsgs",contactMsgs);
		return modelAndView;
	}
	
	@GetMapping("/closeMsg")
	public String closeMsg(@RequestParam int id, Authentication auth) {
		
		cs.updatedMsgStatus(id, auth.getName());
		return "redirect:/displayMessages";
		
		
	}
	
	
	
	

}
