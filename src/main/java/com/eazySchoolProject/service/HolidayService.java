package com.eazySchoolProject.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
		Iterable<Holiday> holidays = repo.findAll();
		List<Holiday> holidayList = StreamSupport.stream(holidays.spliterator(), false).collect(Collectors.toList());
		return holidayList;
	}
	

}
