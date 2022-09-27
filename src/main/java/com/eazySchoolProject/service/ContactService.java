package com.eazySchoolProject.service;

import org.springframework.stereotype.Service;

import com.eazySchoolProject.model.Contact;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ContactService {
	
	
	public boolean saveContent(Contact contact) {
		log.info(contact.toString());
		return true;
		
	}
}
