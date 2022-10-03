package com.eazySchoolProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
		.mvcMatchers("login").permitAll()
		.mvcMatchers("/dashboard").authenticated()
		.mvcMatchers("/home").authenticated()
		.mvcMatchers("/holidays/**").permitAll()
		.mvcMatchers("/contact").permitAll()
		.mvcMatchers("/saveMsg").permitAll()
		.mvcMatchers("/courses").authenticated()
		.mvcMatchers("/about").permitAll();
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
	public InMemoryUserDetailsManager userDetailService() {
		
		UserDetails user = User.withDefaultPasswordEncoder()
				.username("user")
				.password("12345")
				.roles("USER")
				.build();
		
		UserDetails admin = User.withDefaultPasswordEncoder()
				.username("admin")
				.password("54321")
				.roles("ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(user,admin);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
