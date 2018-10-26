package com.stu.jps.user.dao;

import org.springframework.stereotype.Repository;

import com.stu.jps.user.entity.User;

@Repository
public interface UserMapper {

	/**
	 * 根据用户名密码查询用户实体
	 * @param username
	 * @param password
	 * @return
	 */
	User queryUser(String username,String password);
}
