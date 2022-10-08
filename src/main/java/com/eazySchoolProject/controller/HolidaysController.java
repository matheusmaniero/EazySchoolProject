package com.eazySchoolProject.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.eazySchoolProject.model.Holiday;
import com.eazySchoolProject.service.HolidayService;

@Controller
public class HolidaysController {
	
	public final HolidayService hs;
	
	@Autowired
	public HolidaysController(HolidayService hs) {
		this.hs = hs;
	}
	@GetMapping("/holidays/{display}")
	public String displayHolidays(@PathVariable String display,Model model) {
		
		if (display != null && display.equals("all")) {
			model.addAttribute("festival",true);
			model.addAttribute("federal",true);
		}else if (display != null && display.equals("festival")) {
			model.addAttribute("festival",true);
			
		}else if (display != null && display.equals("federal")) {
			model.addAttribute("federal",true);
		}
		
		
		List<Holiday> holidays = hs.getHolidays();
				 
		
		
		Holiday.Type[] types = Holiday.Type.values();
		
		for (Holiday.Type type : types) {
			model.addAttribute(type.toString(),
					(holidays.stream().filter(holiday -> holiday.getType().equals(type)).collect(Collectors.toList())));
		}
				
		
		return "holidays.html";
		
	}

}
