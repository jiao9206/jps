package com.stu.jps.user.service;

import com.stu.jps.user.entity.User;

public interface IUserService {

	/**
	 * 根据用户名和密码查询是否存在此用户
	 * @param username
	 * @param password
	 * @return
	 */
	User queryUser(String username,String password);
}
