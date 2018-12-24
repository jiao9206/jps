package com.stu.jps.user.service;

import com.stu.jps.user.entity.User;

public interface IUserService {

	/**
	 * 查询用户
	 * @param username
	 * @param password
	 * @return
	 */
	User queryUser(String username,String password);
}
