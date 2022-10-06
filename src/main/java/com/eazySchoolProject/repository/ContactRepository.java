package com.eazySchoolProject.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.eazySchoolProject.model.Contact;
import com.eazySchoolProject.rowmappers.ContactRowMapper;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class ContactRepository {
	
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public ContactRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public int saveContactMsg(Contact contact) {
		
		String sql = "INSERT INTO CONTACT_MSG (NAME,MOBILE_NUM,EMAIL,SUBJECT,MESSAGE,STATUS,CREATED_AT,CREATED_BY) VALUES (?,?,?,?,?,?,?,?)";
		return jdbcTemplate.update(sql, contact.getName(),contact.getMobileNum(),contact.getEmail(),contact.getSubject(),contact.getMessage(),contact.getStatus(),contact.getCreatedAt(),contact.getCreatedBy());
	}
	
	public List<Contact> findMsgsWithStatus(String status){
		
		String sql = "SELECT * FROM CONTACT_MSG WHERE STATUS = ?";
		return jdbcTemplate.query(sql,new PreparedStatementSetter() {
			public void setValues(PreparedStatement preparedStatement) throws SQLException{
				preparedStatement.setString(1,status);
			}
			
		}, new ContactRowMapper());
		
		
		
	}
	
	public int updateMsgStatus(int contactId, String status, String updatedBy) {
		
		String sql = "UPDATE CONTACT_MSG SET STATUS = ?, UPDATED_BY = ?, UPDATED_AT = ? WHERE CONTACT_ID = ?";
		return jdbcTemplate.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement preparedStatement) throws  SQLException{
				preparedStatement.setString(1, status);
				preparedStatement.setString(2, updatedBy);
				preparedStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
				preparedStatement.setInt(4, contactId);
			}
		});
		
		
	}
	
	
	
	
	
	
	
	

}