package com.eazySchoolProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eazySchoolProject.model.Person;
import com.eazySchoolProject.model.Roles;
import com.eazySchoolProject.repository.PersonRepository;
import com.eazySchoolProject.repository.RolesRepository;
import com.eazySchoolProject.status.Constants;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personRepo;
	
	@Autowired
	private RolesRepository rolesRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public boolean savePerson(Person person) {
		
		Roles role = rolesRepo.getByRoleName(Constants.STUDENT_ROLE);
		person.setRoles(role);
		person.setPwd(passwordEncoder.encode(person.getPwd()));
		person = personRepo.save(person);
		
		if (person != null && person.getPersonId() > 0) {
			return true;
		}
		
		return false;
	}

}
