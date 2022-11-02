package com.eazySchoolProject.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eazySchoolProject.model.Contact;
import com.eazySchoolProject.repository.ContactRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(path="/api/contact")
public class ContactRestController {
	
	@Autowired
	private ContactRepository contactRepository;
	
	@GetMapping("/getMessagesByStatus")
	@ResponseBody
	public List<Contact> getMessagesByStatus(@RequestParam(name="status") String status){
		
		return contactRepository.findByStatus(status);
		
	}
	
	@GetMapping("/getAllMessagesByStatus")
	@ResponseBody
	public List<Contact> getAllMessagesByStatus(@RequestBody Contact contact){
		
		if (contact != null && contact.getStatus() != null) {
			return contactRepository.findByStatus(contact.getStatus());
		}
		
		return List.of();
	}
	
	
	
	
	
	
	
	
}
