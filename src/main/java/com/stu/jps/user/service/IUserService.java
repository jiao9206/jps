package com.stu.jps.user.service;

import com.stu.jps.user.entity.User;

public interface IUserService {

	/**
	 * �����û����������ѯ�Ƿ���ڴ��û�
	 * @param username
	 * @param password
	 * @return
	 */
	User queryUser(String username,String password);
}
