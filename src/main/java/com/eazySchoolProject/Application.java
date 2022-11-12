package com.eazySchoolProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.eazySchoolProject.repository")
@EntityScan("com.eazySchoolProject.model")
@EnableJpaAuditing(auditorAwareRef = "auditawareimpl")

public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
