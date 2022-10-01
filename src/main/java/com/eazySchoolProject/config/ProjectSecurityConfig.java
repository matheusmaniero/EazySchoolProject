package com.eazySchoolProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		/*
		 * defines in wich page will be need to log in
		 * 
		 */
		http.csrf().disable().authorizeRequests()
		.mvcMatchers("/home").permitAll()
		.mvcMatchers("/holidays/**").permitAll()
		.mvcMatchers("/contact").permitAll()
		.mvcMatchers("/saveMsg").permitAll()
		.mvcMatchers("/courses").authenticated()
		.mvcMatchers("/about").permitAll();
		http.formLogin();
		http.httpBasic();
		return http.build();
		
		
		/*
		 * 
		 *  deny all users
		 * 
		 *
		
		http.authorizeRequests().anyRequest().denyAll();
		http.formLogin();
		http.httpBasic();
		return http.build();
		
		*/
		
	
	}

}
