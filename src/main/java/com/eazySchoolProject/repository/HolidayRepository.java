package com.eazySchoolProject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eazySchoolProject.model.Holiday;

@Repository
public interface HolidayRepository extends CrudRepository<Holiday, String> {

	
	
}
