package com.eazySchoolProject.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class DashboardController {
	
	@RequestMapping("/dashboard")
	public String displayDashboard(Model model, Authentication auth) {
		
		model.addAttribute("username", auth.getName());
		model.addAttribute("roles",auth.getAuthorities().toString());
		return "dashboard.html";
	}

}
