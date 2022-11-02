package com.eazySchoolProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice(annotations = Controller.class)
@Slf4j
public class GlobalExceptionController {
	
	@ExceptionHandler
	public ModelAndView exceptionHandler(Exception exception) {
		
		ModelAndView errorPage = new ModelAndView();
		errorPage.setViewName("error");
		errorPage.addObject("errormsg", exception.getMessage());
		return errorPage;
		
	}
	
	

}
