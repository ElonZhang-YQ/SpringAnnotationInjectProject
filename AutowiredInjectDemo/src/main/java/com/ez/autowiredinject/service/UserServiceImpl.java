package com.ez.autowiredinject.service;

import com.ez.autowiredinject.dao.UserDAO;
import com.ez.autowiredinject.dao.UserDAOImpl2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @Classname UserServiceImpl
 * @Description TODO
 * @Author Elon.Zhang
 * @Date 2024/6/6
 */
@Service
public class UserServiceImpl implements UserService {

	/**
	 * 此时只有一个UserDAO的实现类，并且UserDAOImpl类添加@Repository注解，可以从IOC容器中拿到UserDAOImpl。
	 * 此时@Autowired注解成功从IOC容器拿到bean，并完成注释注入
	 */
	// @Autowired
	// private UserDAO userDAO;

	/**
	 * 此时有两个UserDAO的实现类，分别是UserDAOImpl和UserDAOImpl2，并且两个实现类全部添加@Repository注解
	 * 这种情况下，IDEA已经报错，userDAO下标红线。
	 * 因为：@Autowired注解是根据type进行注入，此时在IOC容器中UserDAO类型的bean对象有两个，@Autowired无法判断是那个bean可以进行注入
	 */
	@Autowired
	@Qualifier(value = "userDAOImpl2")
	private UserDAO userDAO;

	@Override
	public String signIn(String username, String password) {

		return userDAO.getUserByUsernameAndPassword(username, password);
	}

	@Override
	public String signUp(String username, String password) {

		return userDAO.insertUser(username, password);
	}
}
