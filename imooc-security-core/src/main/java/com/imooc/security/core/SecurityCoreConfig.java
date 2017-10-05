package com.imooc.security.core;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.imooc.security.core.properties.SecurityProperties;

@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		// BCryptPasswordEncoder is an encoder provided by Spring, encrypted using salt
		// same password string such as "123456" encrypted will be different each time it is encrypted
		return new BCryptPasswordEncoder();
	}

}
