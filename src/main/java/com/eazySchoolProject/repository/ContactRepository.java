package com.eazySchoolProject.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.eazySchoolProject.model.Contact;

@Repository
public interface ContactRepository extends PagingAndSortingRepository<Contact, Integer> {
	
	 public List<Contact> findByStatus(String status);
	@Query("SELECT c FROM Contact c WHERE c.status = :status")
	 //@Query(value = "SELECT * FROM contact_msg WHERE contact_msg.status = :status",nativeQuery = true)
	 public Page<Contact> findByStatus(String status,  Pageable pageable);
	
	@Transactional
	@Modifying
	@Query("UPDATE Contact c SET c.status = ?1 WHERE c.contactId = ?2")
	public int updateStatusById(String status, int id); 
	 

}
