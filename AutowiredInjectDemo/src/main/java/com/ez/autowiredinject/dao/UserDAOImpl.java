package com.ez.autowiredinject.dao;

import org.springframework.stereotype.Repository;

/**
 * @Classname UserDAOImpl
 * @Description TODO
 * @Author Elon.Zhang
 * @Date 2024/6/6
 */
@Repository("userDAOImpl")
public class UserDAOImpl implements UserDAO{

	@Override
	public String getUserByUsernameAndPassword(String username, String password) {

		return "SignIn: " + username + " " + password;
	}

	@Override
	public String insertUser(String username, String password) {

		return "SignUp: " + username + " " + password;
	}
}
