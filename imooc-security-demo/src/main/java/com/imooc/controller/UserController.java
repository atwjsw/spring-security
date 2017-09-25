package com.imooc.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.dto.User;
import com.imooc.dto.User.UserDetailView;
import com.imooc.dto.User.UserSimpleView;
import com.imooc.dto.UserQueryCondition;

@RestController
@RequestMapping("/user")
public class UserController {
	
//	@RequestMapping(value = "/user", method=RequestMethod.GET)
//	@GetMapping(value = "/user")
	@GetMapping
	@JsonView(UserSimpleView.class)
//	public List<User> query(@RequestParam(value="username", required=false, defaultValue="tom") String nickname) {
	public List<User> query(UserQueryCondition userQueryCondition, @PageableDefault(page=2, size=17, sort="username,asc") Pageable pageable) {
		System.out.println(ReflectionToStringBuilder.toString(userQueryCondition, ToStringStyle.MULTI_LINE_STYLE));
		System.out.println(ReflectionToStringBuilder.toString(pageable, ToStringStyle.MULTI_LINE_STYLE));
		List<User> users = new ArrayList<>();
		users.add(new User());
		users.add(new User());
		users.add(new User());
		return users;	
	}
	
//	@RequestMapping(value="/user/{id:\\d+}", method=RequestMethod.GET)
//	@GetMapping(value="/user/{id:\\d+}")
	@GetMapping(value="/{id:\\d+}")
	@JsonView(UserDetailView.class)
	public User getInfo(@PathVariable(name="id")  String userId) {
		User user = new User();
		user.setUsername("tom");
		return user;
	}
}
