package com.ez.autowiredinject.service;

/**
 * @Classname UserService
 * @Description TODO
 * @Author Elon.Zhang
 * @Date 2024/6/6
 */
public interface UserService {

	String signIn(String username, String password);

	String signUp(String username, String password);

}
