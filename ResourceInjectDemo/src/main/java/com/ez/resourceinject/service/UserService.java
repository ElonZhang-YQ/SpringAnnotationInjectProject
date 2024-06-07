package com.ez.resourceinject.service;

/**
 * @Classname UserService
 * @Description TODO
 * @Author Elon.Zhang
 * @Date 2024/6/7
 */
public interface UserService {

	String userSignIn(String username, String password);

	String userSignUp(String username, String password);

}
