package com.eazySchoolProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eazySchoolProject.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
	
	public Person readByEmail(String email); // readByEmail is equals to "findByEmail, getByEmail, etc" its just a select query on DB

}
