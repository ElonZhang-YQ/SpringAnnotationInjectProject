package com.ez.resourceinject.dao;

import org.springframework.stereotype.Repository;

/**
 * @Classname UserDAOImpl2
 * @Description TODO
 * @Author Elon.Zhang
 * @Date 2024/6/7
 */
// @Repository("userDAOImpl")
public class UserDAOImpl2 implements UserDAO{

	@Override
	public String findUserByUsernameAndPassword(String username, String password) {

		return "UserDAOImpl2 SignIn";
	}

	@Override
	public String insertUser(String username, String password) {

		return "UserDAOImpl2 SignUp";
	}
}
