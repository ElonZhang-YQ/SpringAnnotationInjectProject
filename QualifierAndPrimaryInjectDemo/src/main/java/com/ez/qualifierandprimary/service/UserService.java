package com.ez.qualifierandprimary.service;

/**
 * @Classname UserService
 * @Description TODO
 * @Author Elon.Zhang
 * @Date 2024/6/10
 */
public interface UserService {

	String userSignIn(String username, String password);

	String userSignUp(String username, String password);

}