package com.imooc.security.browser.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.security.browser.support.SimpleResponse;
import com.imooc.security.core.properties.LoginType;
import com.imooc.security.core.properties.SecurityProperties;

@Component("imoocAuthenticationFailureHandler")
//public class ImoocAuthenticationFailureHandler implements AuthenticationFailureHandler {
public class ImoocAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SecurityProperties securityProperties;
	
	// jackson objectMapper
	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException)
			throws IOException, ServletException {
		logger.info("登录失败");		
		if (LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.
					writeValueAsString(new SimpleResponse(authenticationException.getMessage())));
		} else {
			super.onAuthenticationFailure(request, response, authenticationException);			
		}
	}

}
