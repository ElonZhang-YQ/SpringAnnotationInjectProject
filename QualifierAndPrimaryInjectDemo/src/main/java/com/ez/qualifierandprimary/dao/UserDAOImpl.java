package com.ez.qualifierandprimary.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * @Classname UserDAOImpl
 * @Description TODO
 * @Author Elon.Zhang
 * @Date 2024/6/10
 */
@Repository("userDAOImpl")
@Primary
public class UserDAOImpl implements UserDAO{

	@Override
	public String findUserByUsernameAndPassword(String username, String password) {

		return "User：" + username + "， Login from DAO";
	}

	@Override
	public String insertUser(String username, String password) {

		return "User：" + username + "， Regis from DAO";
	}
}
