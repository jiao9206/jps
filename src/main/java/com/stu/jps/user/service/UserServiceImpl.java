package com.stu.jps.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stu.jps.user.dao.UserMapper;
import com.stu.jps.user.entity.User;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;
	
	public User queryUser(String username, String password) {
		return userMapper.queryUser(username, password);
	}

}
