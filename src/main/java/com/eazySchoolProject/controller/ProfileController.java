package com.eazySchoolProject.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.eazySchoolProject.model.Address;
import com.eazySchoolProject.model.Person;
import com.eazySchoolProject.model.Profile;
import com.eazySchoolProject.repository.PersonRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ProfileController {

	@Autowired
	private PersonRepository personRepo;
	
	@GetMapping("/displayProfile")
	public ModelAndView displayMessages(Model model, HttpSession session) {
		Person person = (Person) session.getAttribute("loggedInPerson");
		Profile profile = new Profile();
		profile.setName(person.getName());
		profile.setEmail(person.getEmail());
		profile.setMobileNumber(person.getMobileNumber());
		if (person.getAddress() != null && person.getAddress().getAddressId() > 0) {
			profile.setAddress1(person.getAddress().getAddress1());
			profile.setAddress2(person.getAddress().getAddress2());
			profile.setCity(person.getAddress().getCity());
			profile.setState(person.getAddress().getState());
			profile.setZipCode(person.getAddress().getZipCode());
		}
		ModelAndView modelAndView = new ModelAndView("profile.html");		
		modelAndView.addObject("profile", profile);
		return modelAndView;
	}
	
	@PostMapping("/updateProfile")
	public String updateProfile(@Valid @ModelAttribute("profile") Profile profile, Errors errors, HttpSession session) {
		
		if (errors.hasErrors()) {
			return "profile.html";
		}
		
		Person person = (Person) session.getAttribute("loggedInPerson");
		person.setName(profile.getName());
		person.setEmail(profile.getEmail());
		person.setMobileNumber(profile.getMobileNumber());
		
		if (person.getAddress() == null || !(person.getAddress().getAddressId() > 0)) {
			person.setAddress(new Address());
		}
		
		person.getAddress().setAddress1(profile.getAddress1());
		person.getAddress().setAddress2(profile.getAddress2());
		person.getAddress().setState(profile.getState());
		person.getAddress().setZipCode(profile.getZipCode());
		person.getAddress().setCity(profile.getCity());
		personRepo.save(person);
		session.setAttribute("loggedInPerson", person);
		return "redirect:/displayProfile";
		
	
	}
	
	
}
