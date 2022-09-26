package com.eazySchoolProject.service;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eazySchoolProject.model.Contact;

@Service
public class ContactService {

	private static   org.slf4j.Logger log =  LoggerFactory.getLogger(ContactService.class);
	
	public boolean saveContent(Contact contact) {
		log.info(contact.toString());
		return true;
		
	}
}
