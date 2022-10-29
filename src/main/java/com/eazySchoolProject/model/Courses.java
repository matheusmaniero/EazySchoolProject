package com.eazySchoolProject.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Courses extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native",strategy="native")
	private int courseId;
	
	private String name;
	
	private String fees;
	

	@ManyToMany(mappedBy = "courses", fetch= FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Set<Person> persons;
	
}
