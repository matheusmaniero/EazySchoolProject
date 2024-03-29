package com.eazySchoolProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		/*
		 * defines in wich page will be need to log in
		 * 
		 */
		http.csrf().ignoringAntMatchers("/saveMsg").ignoringAntMatchers("/public/**").ignoringAntMatchers("/api/**").ignoringAntMatchers("/data-api/**").
		ignoringAntMatchers("/eazyschool/actuator").and()
		.authorizeRequests()
		.mvcMatchers("/displayProfile").authenticated()
		.mvcMatchers("/updateProfile").authenticated()
		.mvcMatchers("/displayMessages").hasRole("ADMIN")
		.mvcMatchers("/eazyschool/actuator").hasRole("ADMIN")
		.mvcMatchers("/admin/**").hasRole("ADMIN")
		.mvcMatchers("/login").permitAll()
		.mvcMatchers("/dashboard").authenticated()
		.mvcMatchers("/home").permitAll()
		.mvcMatchers("/api/**").authenticated()
		.mvcMatchers("/data-api/**").authenticated()
		.mvcMatchers("/holidays/**").permitAll()
		.mvcMatchers("/contact").permitAll()
		.mvcMatchers("/saveMsg").permitAll()
		.mvcMatchers("/courses").permitAll()
		.mvcMatchers("/about").permitAll()
		.mvcMatchers("/student/**").hasRole("STUDENT")
		.mvcMatchers("/public/**").permitAll();
		http.formLogin().loginPage("/login")
		.defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll()
		.and().logout().logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll();		
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
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
