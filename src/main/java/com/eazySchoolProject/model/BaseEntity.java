package com.eazySchoolProject.model;

import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;

import lombok.Data;

@Data
@MappedSuperclass
public class BaseEntity {
	
	private LocalDateTime createdAt;
	private String createdBy;
	private LocalDateTime updatedAt;
	private String updatedBy;

}
