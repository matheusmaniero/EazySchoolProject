package com.eazySchoolProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.eazySchoolProject.config.EazySchoolProps;
import com.eazySchoolProject.model.Contact;
import com.eazySchoolProject.repository.ContactRepository;
import com.eazySchoolProject.status.Constants;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j

public class ContactService {
	
	@Autowired
	public ContactRepository repo;
	
	@Autowired
	private EazySchoolProps props;
	
	public boolean saveContent(Contact contact) {		
		contact.setStatus(Constants.OPEN);		
		Contact savedContact = repo.save(contact);
		if (savedContact != null && savedContact.getContactId() > 0) {
			return true;
		}
		
		return false;
		
	}
	
	public Page<Contact> findWithOpenStatus(int pageNum, String sortField, String sortDir){
		
		int pageSize = props.getPageSize();
		
		if (props.getContact() != null && props.getContact().get("pagesize") != null) {
			
			pageSize = Integer.parseInt(props.getContact().get("pagesize").trim());
		}
		
		 Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
	                sortDir.equals("asc") ? Sort.by(sortField).ascending()
	                        : Sort.by(sortField).descending());
		
		Page<Contact> msgPage = repo.findOpenMsgs(Constants.OPEN, pageable);
		
		return msgPage; 
	}
	
	public boolean updatedMsgStatus(int contactId) {		
		int rows = repo.updateMsgStatus(Constants.CLOSED, contactId);
		if (rows > 0) {
			return true;
		}
		return false;
		
		
		
	}
}
