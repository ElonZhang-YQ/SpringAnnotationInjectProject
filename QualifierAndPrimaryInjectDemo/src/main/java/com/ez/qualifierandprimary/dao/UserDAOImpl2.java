package com.ez.qualifierandprimary.dao;

import org.springframework.stereotype.Repository;

/**
 * @Classname UserDAOImpl2
 * @Description TODO
 * @Author Elon.Zhang
 * @Date 2024/6/10
 */
@Repository("userDAOImpl2")
public class UserDAOImpl2 implements UserDAO{

	@Override
	public String findUserByUsernameAndPassword(String username, String password) {

		return "User：" + username + "， Login from DAO2";
	}

	@Override
	public String insertUser(String username, String password) {

		return "User：" + username + "， Regis from DAO2";
	}
}
