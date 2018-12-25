package com.stu.jps.activiti.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.stu.jps.activiti.entity.Leave;

public interface IActivitiService {

	/**
	 * 创建流程
	 * @param leave
	 * @return
	 */
	public Map<String,Object> saveAndSend(Leave leave,HttpServletRequest rep);
	/**
	 * 查询当前用户的待办任务
	 * @param page
	 * @param limit
	 * @return
	 */
	Map<String,Object> queryToDoList(int page,int limit,HttpServletRequest req);
}
