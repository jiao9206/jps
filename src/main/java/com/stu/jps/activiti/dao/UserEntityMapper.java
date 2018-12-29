package com.stu.jps.activiti.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.stu.jps.activiti.entity.GroupEntity;
import com.stu.jps.activiti.entity.UserEntity;

/**
 * 该接口为重写activiti用户和组信息专用
 * @author JiaoPeng
 *
 */
@Repository
public interface UserEntityMapper {

	/**
	 * 通过userId查询用户
	 * @param userId
	 * @return
	 */
	public UserEntity findUserById(String userId);
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public List<GroupEntity> findGroupEntityListById(String userId);
}
