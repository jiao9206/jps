package com.stu.jps.user.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stu.jps.user.dao.UserMapper;
import com.stu.jps.user.entity.User;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;
	
	public User queryUser(String username, String password) {
		Map param=new HashMap();
		param.put("username", username);
		param.put("password", password);
		return userMapper.queryUser(param);
	}

}
