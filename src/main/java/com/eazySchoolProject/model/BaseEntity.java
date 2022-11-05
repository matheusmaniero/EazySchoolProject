package com.eazySchoolProject.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
	
	@CreatedDate
	@Column(updatable = false)
	@JsonIgnore
	private LocalDateTime createdAt;
	@CreatedBy
	@Column(updatable = false)
	@JsonIgnore
	private String createdBy;
	@LastModifiedDate
	@Column(insertable = false)
	@JsonIgnore
	private LocalDateTime updatedAt;
	@LastModifiedBy
	@Column(insertable = false)
	@JsonIgnore
	private String updatedBy;

}
