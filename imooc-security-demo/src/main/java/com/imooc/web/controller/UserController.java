package com.imooc.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.Soundbank;
import javax.validation.Valid;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.dto.User;
import com.imooc.dto.User.UserDetailView;
import com.imooc.dto.User.UserSimpleView;
import com.imooc.dto.UserQueryCondition;
import com.imooc.exception.UserNotExistException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@DeleteMapping(value = "/{id:\\d+}")
	public void delete(@PathVariable String id) {
		System.out.println(id);
	}
	
	@PutMapping(value = "/{id:\\d+}")
	public User update(@Valid @RequestBody User user, BindingResult errors) {
		if (errors.hasErrors()) {
			errors.getAllErrors().stream().forEach(error -> 
				{
					FieldError fieldError = (FieldError) error;					
//					System.out.println(fieldError.getField() + " " + fieldError.getDefaultMessage());
					System.out.println(fieldError.getDefaultMessage());
				});
		}
		System.out.println(ReflectionToStringBuilder.toString(user, ToStringStyle.MULTI_LINE_STYLE));		
		user.setId("1");
		return user;
	}
		
	@PostMapping
//	public User create(@Valid @RequestBody User user, BindingResult errors) {
	public User create(@Valid @RequestBody User user) {
//		if (errors.hasErrors()) {
//			errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
//		}
		System.out.println(ReflectionToStringBuilder.toString(user, ToStringStyle.MULTI_LINE_STYLE));		
		user.setId("1");
		return user;
	}
	
	@GetMapping
	@JsonView(UserSimpleView.class)
	@ApiOperation(value = "用户查询服务")
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
	
	@GetMapping(value="/{id:\\d+}")
	@JsonView(UserDetailView.class)
	public User getInfo(@ApiParam("用户id") @PathVariable(name="id")  String userId) {
//		throw new RuntimeException("User not exist");
//		throw new UserNotExistException(userId);
		System.out.println("进入getInfo服务");
		User user = new User();
		user.setUsername("tom");
		return user;
	}
}

