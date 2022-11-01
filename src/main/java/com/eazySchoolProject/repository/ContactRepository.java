package com.eazySchoolProject.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.eazySchoolProject.model.Contact;

@Repository
public interface ContactRepository extends PagingAndSortingRepository<Contact, Integer> {
	
	 public List<Contact> findByStatus(String status);
	 @Query("SELECT c FROM Contact c WHERE c.status = :status")
	 public Page<Contact> findByStatus(String status,  Pageable pageable);
	 

}
