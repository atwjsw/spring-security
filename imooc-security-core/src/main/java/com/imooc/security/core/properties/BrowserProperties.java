package com.imooc.security.core.properties;

public class BrowserProperties {
	
	// 默认值，如果application.properties未配置，则使用以下值
	private String loginPage = "/imooc-signin.html";
	
	private LoginType loginType = LoginType.JSON;
	
	private int rememberMeSeconds = 3600;

	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}

	public LoginType getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginType loginType) {
		this.loginType = loginType;
	}

	public int getRememberMeSeconds() {
		return rememberMeSeconds;
	}

	public void setRememberMeSeconds(int rememberMeSeconds) {
		this.rememberMeSeconds = rememberMeSeconds;
	}		
}
