package com.eazySchoolProject.rest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eazySchoolProject.model.Contact;
import com.eazySchoolProject.model.Response;
import com.eazySchoolProject.repository.ContactRepository;
import com.eazySchoolProject.status.Constants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path="/api/contact", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
@CrossOrigin(origins="*")
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
	
	@DeleteMapping("/deleteMsg")
	public ResponseEntity<Response> deleteMsg(RequestEntity<Contact> requestEntity){
		
		HttpHeaders headers = requestEntity.getHeaders();
		
		headers.forEach((key,value) -> {
			
			log.info(String.format("Header '%s' = %s",key,value.stream().collect(Collectors.joining("|"))));
			
		});
		
		Contact contact = requestEntity.getBody();
		contactRepository.deleteById(contact.getContactId());
		Response response = new Response();
		response.setStatusCode("200");
		response.setStatusMsg("Message Successfully Deleted");
		return ResponseEntity.status(HttpStatus.OK).body(response);
		
	}
	
	@PatchMapping("/closeMsg")
	public ResponseEntity<Response> closeMsg(@RequestBody Contact contactReq){
		
		Response response = new Response();
		Optional<Contact> contact = contactRepository.findById(contactReq.getContactId());
		
		if (contact.isPresent()) {
			contact.get().setStatus(Constants.CLOSED);
			contactRepository.save(contact.get());
			response.setStatusCode("200");
			response.setStatusMsg("Message Successfully Closed");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		response.setStatusCode("400");
		response.setStatusMsg("Invalid Contact ID received");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		
	}
	
	
	
	
	
	
	
	
}
