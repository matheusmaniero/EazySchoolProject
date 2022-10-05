package com.eazySchoolProject.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

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
		int rowsAffected = repo.saveContactMsg(contact);
		if (rowsAffected >= 1) {
			return true;
		}
		
		return false;
		
	}
}
