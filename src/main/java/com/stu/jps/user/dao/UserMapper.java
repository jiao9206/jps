package com.stu.jps.user.dao;

import java.util.Map;

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
	User queryUser(Map map);
}
