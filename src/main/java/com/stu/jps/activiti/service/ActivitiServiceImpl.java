package com.stu.jps.activiti.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stu.jps.activiti.dao.ActivitiMapper;
import com.stu.jps.activiti.entity.Leave;
import com.stu.jps.user.entity.User;

@Service
public class ActivitiServiceImpl implements IActivitiService {
	
	@Autowired
	private ActivitiMapper activitiMapper;
	@Autowired
	private IdentityService identityService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	/**
	 * 创建流程
	 */
	public Map<String,Object> saveAndSend(Leave leave,HttpServletRequest rep){
		Map<String,Object> result=new HashMap<String,Object>();
		try {
			User user=(User)rep.getSession().getAttribute("user");
			String bussinessId=UUID.randomUUID().toString().replaceAll("-", "");
			//开启流程
			Map<String,Object> variables=new HashMap<String,Object>();
			identityService.setAuthenticatedUserId(user.getUser_name());
			ProcessInstance pi=runtimeService.startProcessInstanceByKey("leave",bussinessId,variables);
			String processInstanceId=pi.getProcessInstanceId();
			//保存业务数据
			leave.setId(bussinessId);
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			leave.setApplyTime(sf.format(new Date()));
			leave.setUserId(user.getUser_name());
			leave.setProcessInstanceId(processInstanceId);
			activitiMapper.save(leave);
			result.put("flag", true);
		}catch(Exception e) {
			e.printStackTrace();
			result.put("flag", false);
		}
		return result;
	}
	/**
	 * 查询待办任务
	 */
	public Map<String,Object> queryToDoList(int page,int limit,HttpServletRequest req){
		Map<String,Object> result=new HashMap<String,Object>();
		List<Leave> data=new ArrayList<Leave>();
		try {
			User user=(User)req.getSession().getAttribute("user");
			List<Task> todoList=taskService.createTaskQuery().taskCandidateUser(user.getUser_name()).listPage((page-1)*limit, limit);
			for(Task task:todoList) {
				String processInstanceId=task.getProcessInstanceId();
				ProcessInstance processInstance=runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
				String bussinessId=processInstance.getBusinessKey();
				Leave leave=activitiMapper.get(bussinessId);
				data.add(leave);
			}
			result.put("data", data);
			result.put("code", 0);
			result.put("count", taskService.createTaskQuery().taskCandidateUser(user.getUser_name()).list().size());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
