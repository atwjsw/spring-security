package com.imooc.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService, SocialUserDetailsService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private PasswordEncoder passwordEncoder;

	// different types of user repository can be injected
	// @Autowired
	// UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("表单登录用户名：" + username);
		return buildUser(username);
	}
	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
		logger.info("社交登录用户Id：" + userId);
		return buildUser(userId);
	}	
	private SocialUserDetails buildUser(String userId) {
		// 根据用户Id查找用户信息, using injected user repository
		// 根据查找到的用户信息判断用户是否被冻结, 实际项目中false应使用用户数据判断得来
		String encodedPassword = passwordEncoder.encode("123456");
		logger.info("encoded database password: " + encodedPassword);		
		return new SocialUser(userId, encodedPassword, true, true, true, true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_USER"));
	}
}
