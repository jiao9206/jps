package com.stu.jps.activiti.dao;

import org.springframework.stereotype.Repository;

import com.stu.jps.activiti.entity.Leave;

@Repository
public interface ActivitiMapper {

	/**
	 * 通过主键查询实体
	 * @param id
	 * @return
	 */
	public Leave get(String id);
	/**
	 * 保存数据
	 * @param leave
	 */
	public void save(Leave leave);
}
