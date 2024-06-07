package com.ez.autowiredinject.dao;

/**
 * @Classname UserDAO
 * @Description TODO
 * @Author Elon.Zhang
 * @Date 2024/6/6
 */
public interface UserDAO {

	String getUserByUsernameAndPassword(String username, String password);

	String insertUser(String username, String password);

}
