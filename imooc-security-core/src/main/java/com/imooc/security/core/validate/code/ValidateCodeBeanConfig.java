package com.imooc.security.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.imooc.security.core.properties.SecurityProperties;

@Configuration
public class ValidateCodeBeanConfig {
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Bean
	@ConditionalOnMissingBean(name="imageCodeGenerator")
	public ValidateCodeGenerator imageCodeGenerator() {
		ImageCodeGenerator generator = new ImageCodeGenerator();
		generator.setSecurityProperties(securityProperties);
		return generator;
	}

}
