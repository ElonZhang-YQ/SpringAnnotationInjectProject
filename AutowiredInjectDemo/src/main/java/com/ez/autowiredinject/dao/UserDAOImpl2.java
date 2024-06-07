package com.ez.autowiredinject.dao;

import org.springframework.stereotype.Repository;

/**
 * @Classname UserDAOImpl2
 * @Description TODO
 * @Author Elon.Zhang
 * @Date 2024/6/7
 */
@Repository("userDAOImpl2")
public class UserDAOImpl2 implements UserDAO{

	@Override
	public String getUserByUsernameAndPassword(String username, String password) {

		return "UserDAOImpl2 signIn";
	}

	@Override
	public String insertUser(String username, String password) {

		return "UserDAOImpl2 signUp";
	}
}
