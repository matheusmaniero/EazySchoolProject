package com.eazySchoolProject.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eazySchoolProject.model.Person;
import com.eazySchoolProject.repository.PersonRepository;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class DashboardController {
	
	@Autowired
	PersonRepository personRepo;
	
	@RequestMapping("/dashboard")
	public String displayDashboard(Model model, Authentication auth, HttpSession session) {
		Person person = personRepo.getByEmail(auth.getName());
		model.addAttribute("username", person.getName());
		model.addAttribute("roles",auth.getAuthorities().toString());
		session.setAttribute("loggedInPerson", person);
		return "dashboard.html";
	}

}
