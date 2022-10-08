package com.eazySchoolProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eazySchoolProject.model.Holiday;
import com.eazySchoolProject.repository.HolidayRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HolidayService {
	
	@Autowired
	public HolidayRepository repo;
	
	public List<Holiday> getHolidays(){
		return repo.getHolidays();
	}
	

}
