package com.eazySchoolProject.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.eazySchoolProject.model.EazyClass;
import com.eazySchoolProject.model.Person;
import com.eazySchoolProject.repository.EazyClassRepository;
import com.eazySchoolProject.repository.PersonRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("admin")
public class AdminController {
	
	@Autowired
	private EazyClassRepository classRepo;
	
	@Autowired
	private PersonRepository personRepo;

	@RequestMapping("displayClasses")
	public ModelAndView displayClasses(Model model) {
		List<EazyClass> eazyClasses = classRepo.findAll();		
		ModelAndView modelAndView = new ModelAndView("classes.html");
		modelAndView.addObject("eazyClasses", eazyClasses);
		modelAndView.addObject("eazyClass", new EazyClass());
		return modelAndView;
		
		
	}
	
	@PostMapping("/addNewClass")
	public ModelAndView addNewClass(Model model, @ModelAttribute("eazyClass") EazyClass eazyClass) {
		
		classRepo.save(eazyClass);
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
		return modelAndView;
	
	}
	
	
	@RequestMapping("/deleteClass")
	public ModelAndView deleteClass(Model model, @RequestParam int id) {
		
		Optional<EazyClass> eazyClass = classRepo.findById(id);
		for (Person person : eazyClass.get().getPersons()) {
			person.setEazyClass(null);
			personRepo.save(person);
			
		}
		
		classRepo.deleteById(id);
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
		return modelAndView;
		
	}
	
	 @GetMapping("/displayStudents")
	    public ModelAndView displayStudents(Model model, @RequestParam int classId, HttpSession session,
	                                        @RequestParam(value = "error", required = false) String error) {
	        String errorMessage = null;
	        ModelAndView modelAndView = new ModelAndView("students.html");
	        Optional<EazyClass> eazyClass = classRepo.findById(classId);
	        modelAndView.addObject("eazyClass",eazyClass.get());
	        modelAndView.addObject("person",new Person());
	        session.setAttribute("eazyClass",eazyClass.get());
	        if(error != null) {
	            errorMessage = "Invalid Email entered!!";
	            modelAndView.addObject("errorMessage", errorMessage);
	        }
	        return modelAndView;
	    }
	
	@PostMapping("/addStudent")
	public ModelAndView addStudent(Model model, @ModelAttribute ("person") Person person,
			HttpSession session) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		Person personEntity = personRepo.getByEmail(person.getEmail());
		EazyClass eazyClass = (EazyClass) session.getAttribute("eazyClass");
		
		if (personEntity == null || !(personEntity.getPersonId() > 0)) {
			
			modelAndView.setViewName("redirect:/admin/displayStudents?classId="+eazyClass.getClassId()
			+"&error=true");
			
			return modelAndView;
		
		}
		
		personEntity.setEazyClass(eazyClass);
		personRepo.save(personEntity);
		eazyClass.getPersons().add(personEntity);
		classRepo.save(eazyClass);
		
		modelAndView.setViewName("redirect:/admin/displayStudents?classId="+eazyClass.getClassId());
		return modelAndView;
		
		
	}
	
	@GetMapping("/deleteStudent")
	public ModelAndView deleteStudent(Model model, @RequestParam int personId, HttpSession session) {
		
		EazyClass eazyClass = (EazyClass) session.getAttribute("eazyClass");
		Optional<Person> person = personRepo.findById(personId);
		person.get().setEazyClass(null);
		eazyClass.getPersons().remove(person);
		classRepo.save(eazyClass);
		session.setAttribute("eazyClass", eazyClass);
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayStudents?classId="+eazyClass.getClassId());
	
		return modelAndView;
		
	}
	
	
}
