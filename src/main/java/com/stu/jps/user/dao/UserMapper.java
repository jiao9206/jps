package com.stu.jps.user.dao;

import org.springframework.stereotype.Repository;

import com.stu.jps.user.entity.User;

@Repository
public interface UserMapper {

	/**
	 * �����û��������ѯ�û�ʵ��
	 * @param username
	 * @param password
	 * @return
	 */
	User queryUser(String username,String password);
}
