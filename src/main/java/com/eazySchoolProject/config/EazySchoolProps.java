package com.eazySchoolProject.config;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

@Component("eazySchoolProps")
@Data
@ConfigurationProperties(prefix="eazyschool")
@Validated
public class EazySchoolProps {
	
	@Min(value=5, message="must be between 5 and 25")
	@Max(value=25, message="must be between 5 and 25")
	private int pageSize;
	
	private Map<String,String> contact;
	
	private List<String> branches;

}
