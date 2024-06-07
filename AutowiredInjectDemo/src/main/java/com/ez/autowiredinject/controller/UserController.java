package com.ez.autowiredinject.controller;

import com.ez.autowiredinject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname UserController
 * @Description TODO
 * @Author Elon.Zhang
 * @Date 2024/6/6
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/signIn")
	public String userSignIn(String username, String password) {

		return userService.signIn(username, password);
	}

	@RequestMapping("/signUp")
	public String userSignUp(String username, String password) {

		return userService.signUp(username, password);
	}
}
