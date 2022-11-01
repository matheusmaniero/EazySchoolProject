package com.eazySchoolProject.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
		contact.setStatus(Constants.OPEN);		
		Contact savedContact = repo.save(contact);
		if (savedContact != null && savedContact.getContactId() > 0) {
			return true;
		}
		
		return false;
		
	}
	
	public Page<Contact> findWithOpenStatus(int pageNum, String sortField, String sortDir){
		
		int pageSize = 5;
		 Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
	                sortDir.equals("asc") ? Sort.by(sortField).ascending()
	                        : Sort.by(sortField).descending());
		
		Page<Contact> msgPage = repo.findByStatus(Constants.OPEN, pageable);
		
		return msgPage; 
	}
	
	public boolean updatedMsgStatus(int contactId) {
		
		Optional<Contact> contact = repo.findById(contactId);
		contact.ifPresent(contact1 -> {
			contact1.setStatus(Constants.CLOSED);
		
		});
		
		Contact updatedContact = repo.save(contact.get());
		
		return true;
		
		
	}
}
