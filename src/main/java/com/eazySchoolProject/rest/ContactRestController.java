package com.eazySchoolProject.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eazySchoolProject.model.Contact;
import com.eazySchoolProject.model.Response;
import com.eazySchoolProject.repository.ContactRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path="/api/contact")
public class ContactRestController {
	
	@Autowired
	private ContactRepository contactRepository;
	
	@GetMapping("/getMessagesByStatus")
	//@ResponseBody
	public List<Contact> getMessagesByStatus(@RequestParam(name="status") String status){
		
		return contactRepository.findByStatus(status);
		
	}
	
	@GetMapping("/getAllMessagesByStatus")
	//@ResponseBody
	public List<Contact> getAllMessagesByStatus(@RequestBody Contact contact){
		
		if (contact != null && contact.getStatus() != null) {
			return contactRepository.findByStatus(contact.getStatus());
		}
		
		return List.of();
	}
	
	@PostMapping("/saveMsg")
	public ResponseEntity<Response> saveMessages(@RequestHeader("invocationFrom") String invocationFrom,
			@Valid @RequestBody Contact contact){
		
		log.info(String.format("Header invocation from: %s", invocationFrom));
		contactRepository.save(contact);
		Response response = new Response();
		response.setStatusCode("200");
		response.setStatusMsg("Message saved successfully");
		return ResponseEntity.status(HttpStatus.CREATED).header("isMsgSaved", "true").body(response);
			
	}
	
	
	
	
	
	
	
	
}
