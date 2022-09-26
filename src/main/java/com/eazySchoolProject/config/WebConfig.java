package com.eazySchoolProject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	
	/*
	 * 
	 * since we are creating static pages, without any business logic we can simple
	 * use this Interface to avoid creating unnecessary view controllers. 
	 * 
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		
		registry.addViewController("/courses").setViewName("courses");
		registry.addViewController("/about").setViewName("about");
		
		
	}
	
	
	
	
}
