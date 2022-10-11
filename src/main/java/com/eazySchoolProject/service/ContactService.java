package com.eazySchoolProject.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eazySchoolProject.model.Contact;
import com.eazySchoolProject.repository.ContactRepository;
import com.eazySchoolProject.status.Constants;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j

public class ContactService {
	
	@Autowired
	public ContactRepository repo;
	
	
	
	public boolean saveContent(Contact contact) {
		contact.setCreatedBy(Constants.ANONYMOUS);
		contact.setStatus(Constants.OPEN);
		contact.setCreatedAt(LocalDateTime.now());
		Contact savedContact = repo.save(contact);
		if (savedContact != null && savedContact.getContactId() > 0) {
			return true;
		}
		
		return false;
		
	}
	
	public List<Contact> findWithOpenStatus(){
		List<Contact> contactMsgs = repo.findByStatus(Constants.OPEN);
		return contactMsgs;
	}
	
	public boolean updatedMsgStatus(int contactId, String updatedBy) {
		
		Optional<Contact> contact = repo.findById(contactId);
		contact.ifPresent(contact1 -> {
			contact1.setStatus(Constants.CLOSED);
			contact1.setUpdatedBy(updatedBy);
			contact1.setUpdatedAt(LocalDateTime.now());
			
		});
		
		Contact updatedContact = repo.save(contact.get());
		
		return true;
		
		
	}
}
