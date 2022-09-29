package com.eazySchoolProject.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.eazySchoolProject.model.Contact;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
//@RequestScope
@SessionScope
public class ContactService {
	
	private int counter = 0;
	
	public ContactService() {
		System.out.println("ContactService is been initialized");
	}
	
	public boolean saveContent(Contact contact) {
		log.info(contact.toString());
		this.counter += 1;
		System.out.println(counter);
		return true;
		
	}
}
