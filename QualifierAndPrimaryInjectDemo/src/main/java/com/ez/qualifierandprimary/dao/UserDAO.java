package com.ez.qualifierandprimary.dao;

/**
 * @Classname UserDAO
 * @Description TODO
 * @Author Elon.Zhang
 * @Date 2024/6/10
 */
public interface UserDAO {

	String findUserByUsernameAndPassword(String username, String password);

	String insertUser(String username, String password);

}
