package com.imooc.security.core.qq.connect;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Template;

import com.imooc.security.core.social.qq.api.QQ;
import com.imooc.security.core.social.qq.api.QQImpl;

public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {
	
	private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
	private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

	public QQServiceProvider(String appId, String appSecret) {
		super(new OAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
	}

	private String appId;
	
	@Override
	public QQ getApi(String accessToken) {
		return new QQImpl(accessToken, appId);
	}

}
