package com.ez.qualifierandprimary.service;

import com.ez.qualifierandprimary.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @Classname UserServiceImpl
 * @Description TODO
 * @Author Elon.Zhang
 * @Date 2024/6/10
 */
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	// @Qualifier("userDAOImpl2")
	private UserDAO userDAO;

	@Override
	public String userSignIn(String username, String password) {

		return userDAO.findUserByUsernameAndPassword(username, password);
	}

	@Override
	public String userSignUp(String username, String password) {

		return userDAO.insertUser(username, password);
	}
}
