package com.eazySchoolProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.eazySchoolProject.model.Courses;

@Repository
@RepositoryRestResource(path="courses")
public interface CoursesRepository extends JpaRepository<Courses, Integer> {
	
	public List<Courses> findByOrderByNameDesc();
	
	public List<Courses> findByOrderByName();

}
