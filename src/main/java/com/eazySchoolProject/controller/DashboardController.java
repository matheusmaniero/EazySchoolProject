package com.eazySchoolProject.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
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
	private PersonRepository personRepo;
	
	@Value("${eazyschool.pagesize}")
	private int pageSize;
	
	@Value("${eazyschool.contact.successMsg}")
	private String msg;
	
	@Autowired
	private Environment env;
	
	@RequestMapping("/dashboard")
	public String displayDashboard(Model model, Authentication auth, HttpSession session) {
		Person person = personRepo.getByEmail(auth.getName());
		if (person.getEazyClass() != null && person.getEazyClass().getName() != null) {
			model.addAttribute("enrolledClass", person.getEazyClass().getName());
		}
		model.addAttribute("username", person.getName());
		model.addAttribute("roles",auth.getAuthorities().toString());
		session.setAttribute("loggedInPerson", person);
		logMessasges();
		return "dashboard.html";
	}
	
	
	/*
	 * 
	 *  only for loggin test
	 */

		private void logMessasges() {
			log.error("Error msg from Dashboard page");
			log.warn("Warn msg from Dashboard page");
			log.info("Info msg from Dashboard page");
			log.debug("Debug msg from Dashboard page");
			log.trace("Trace msg from Dashboard page");
			log.error("this log come from @Value annotation -> "+pageSize);
			log.error("this log come from @Value annotation -> "+msg);
			log.error("this log come from Environment Interface -> "+env.getProperty("eazyschool.contact.successMsg"));
			log.error("this log come from Environment Interface -> "+env.getProperty("eazyschool.pagesize"));
			log.error("this log come from Environment Interface -> "+env.getProperty("JAVA_HOME"));
		
		}
}
