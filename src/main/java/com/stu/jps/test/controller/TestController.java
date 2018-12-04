package com.stu.jps.test.controller;

import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stu.jps.test.entity.Test;
import com.stu.jps.test.service.ITestService;

@Controller
@RequestMapping("/testController")
public class TestController {

	private static Logger log=LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	private ITestService testService;
	
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private IdentityService identityService;
    
	@RequestMapping("/testMethod")
	@ResponseBody
	public String test(String a) {
		List<Test> list;
		try {
			list=testService.queryAll();
			log.trace("test logback trace:中文---------------------------------------------");
			log.debug("test logback debug:中文---------------------------------------------");
			log.info("test logback info:中文---------------------------------------------");
			log.warn("test logback warn:中文---------------------------------------------");
			log.error("test logback error:中文---------------------------------------------");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "System Exception!";
		}
		return "result:"+list.toString();
	}
	
	@RequestMapping("/activiti")
	@ResponseBody
	public String activiti(){
//		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("PCApply");
//		Task task=taskService.createTaskQuery().taskCandidateUser("jiaopeng").singleResult();
//		taskService.claim(task.getId(), "jiaopeng");
//		taskService.complete(task.getId());
		//创建用户
//		User user=identityService.newUser("panxiaoyue");
//		user.setFirstName("Pan");
//		user.setLastName("XiaoYue");
//		user.setEmail("931642903@qq.com");
//		identityService.saveUser(user);
		
		//创建组
//		Group group=identityService.newGroup("leader");
//		group.setName("部门经理");
//		group.setType("assignment");
//		identityService.saveGroup(group);
		
		//建立用户和组之间的关系
//		identityService.createMembership("panxiaoyue", "leader");
		List<ProcessDefinition> list=repositoryService.createProcessDefinitionQuery().list();
		
		return "success";
	}
}
