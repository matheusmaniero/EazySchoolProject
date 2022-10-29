package com.eazySchoolProject.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.eazySchoolProject.model.Person;
import com.eazySchoolProject.repository.CoursesRepository;
import com.eazySchoolProject.repository.PersonRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("student")
public class StudentController {
	
	@Autowired
	private PersonRepository personRepo;
	
	@Autowired
	private CoursesRepository coursesRepo;
	
	@GetMapping("/displayCourses")
	public ModelAndView displayCourses(Model model, HttpSession session ) {
		Person person = (Person)session.getAttribute("loggedInPerson");
		ModelAndView modelAndView = new ModelAndView("courses_enrolled.html");
		modelAndView.addObject("person",person);
		
		return modelAndView;
		
		
		
	}

	
}
