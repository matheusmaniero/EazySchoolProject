package com.eazySchoolProject.audit;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class EazySchoolnfoContributor implements InfoContributor {

	@Override
	public void contribute(Builder builder) {
		
		Map<String,String> eazyMap = new HashMap<>();
		eazyMap.put("App Name", "Eazy School");
		eazyMap.put("App Description", "Eazy School Webm Application for Students and Admin");
		eazyMap.put("App Version", "1.0.0");
		eazyMap.put("Contact Email", "info@eazyschool.com");
		eazyMap.put("Contact Phone", "012345678");
		builder.withDetail("eazyschool-info", eazyMap);
		
		
		
	}

}
