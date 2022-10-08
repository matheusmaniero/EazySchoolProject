package com.eazySchoolProject.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.eazySchoolProject.model.Holiday;
import com.eazySchoolProject.rowmappers.HolidayRowMapper;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class HolidayRepository {

	public final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public HolidayRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<Holiday> getHolidays(){
		
		String sql = "SELECT * FROM HOLIDAYS";
		return jdbcTemplate.query(sql, new HolidayRowMapper());
	}
	
}
