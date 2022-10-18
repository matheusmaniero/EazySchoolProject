package com.eazySchoolProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eazySchoolProject.model.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {
	
	public Roles getByRoleName(String roleName);

}
