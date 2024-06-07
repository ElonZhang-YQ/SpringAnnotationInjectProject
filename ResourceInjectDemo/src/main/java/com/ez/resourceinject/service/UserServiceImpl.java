package com.ez.resourceinject.service;

import com.ez.resourceinject.dao.UserDAO;
import com.ez.resourceinject.dao.UserDAOImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @Classname UserServiceImpl
 * @Description TODO
 * @Author Elon.Zhang
 * @Date 2024/6/7
 */
@Service
public class UserServiceImpl implements UserService{

	// @Resource(name = "userDAOImpl2")
	@Resource(type = UserDAO.class)
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
