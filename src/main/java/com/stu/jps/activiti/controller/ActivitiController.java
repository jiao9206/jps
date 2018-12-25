package com.stu.jps.activiti.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.stu.jps.activiti.entity.Leave;
import com.stu.jps.activiti.service.IActivitiService;

@Controller
@RequestMapping("/activiti")
public class ActivitiController {

    @Autowired
    private RepositoryService repositoryService;
    
    @Autowired
    private IActivitiService activitiService;
    
    
	/**
	 * 流程管理导航
	 * @return
	 */
	@RequestMapping("/processMgt")
	public ModelAndView processMgt() {
		ModelAndView mv=new ModelAndView("/activiti/processmgt/processlist");
		return mv;
	}
	
	/**
	 * 待办事项
	 * @return
	 */
	@RequestMapping("/todo")
	public ModelAndView todo() {
		ModelAndView mv=new ModelAndView("/activiti/processmgt/todoList");
		return mv;
	}
	/**
	 * 查询已经部署的流程
	 */
	@RequestMapping("/queryProcessList")
	@ResponseBody
	public Map<String,Object> queryProcessList(int page,int limit) {
		List<ProcessDefinition> processDefinitionList=repositoryService.createProcessDefinitionQuery().listPage((page-1)*limit, limit);
		List<Map<String,Object>> data=new ArrayList<Map<String,Object>>();
		for(int i=0;i<processDefinitionList.size();i++) {
			ProcessDefinition p=processDefinitionList.get(i);
			Map<String,Object> item=new HashMap<String,Object>();
			item.put("id", p.getId());
			item.put("deploymentId", p.getDeploymentId());
			item.put("name", p.getName());
			item.put("key", p.getKey());
			item.put("version", p.getVersion());
			item.put("resourceName", p.getResourceName());
			item.put("diagramResourceName", p.getDiagramResourceName());
			data.add(item);
		}
		Map<String,Object> resultMap=new HashMap<String,Object>();
		resultMap.put("data", data);
		resultMap.put("count", repositoryService.createProcessDefinitionQuery().list().size());
		resultMap.put("code", 0);
		return resultMap;
	}
	
	
	/**
	 * 查询待办列表
	 * @param page
	 * @param limit
	 * @return
	 */
	@RequestMapping("/queryTodoList")
	@ResponseBody
	public Map<String,Object>  queryTodoList(int page,int limit,HttpServletRequest req){
		Map<String,Object> result=new HashMap<String,Object>();
		try {
			result=activitiService.queryToDoList(page, limit,req);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 查看XML资源
	 * @param id
	 * @param resourceName
	 * @param rep
	 */
	@RequestMapping("/viewXml")
	public void viewXml(String id,String resourceName,HttpServletResponse rep) {
		try {
			resourceName=resourceName.replaceAll(",", "\\\\");
			ProcessDefinitionQuery pdq=repositoryService.createProcessDefinitionQuery();
			ProcessDefinition pd=pdq.processDefinitionId(id).singleResult();
			InputStream in=repositoryService.getResourceAsStream(pd.getDeploymentId(), resourceName);
			StringBuffer sb=new StringBuffer();
			byte[] b=new byte[1024];
			int len=-1;
			while((len=in.read(b))!=-1) {
				sb.append(new String(b,0,len));
			}
			String xmlStr=sb.toString();
			rep.getWriter().write("#");
			rep.getWriter().write(xmlStr);
			in.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 查看图片资源
	 * @param id
	 * @param diagramResourceName
	 * @param rep
	 */
	@RequestMapping("/viewPic")
	public void viewPic(String id,String diagramResourceName,HttpServletResponse rep) {
		try {
			diagramResourceName=diagramResourceName.replaceAll(",", "\\\\");
			ProcessDefinitionQuery pdq=repositoryService.createProcessDefinitionQuery();
			ProcessDefinition pd=pdq.processDefinitionId(id).singleResult();
			InputStream in=repositoryService.getResourceAsStream(pd.getDeploymentId(), diagramResourceName);
			byte[] b=new byte[1024];
			int len=-1;
			while((len=in.read(b,0,1024))!=-1) {
				rep.getOutputStream().write(b,0,len);
			}
			in.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 删除已经部署的流程
	 * @param deploymentId
	 * @return
	 */
	@RequestMapping("/delProcess")
	@ResponseBody
	public Map<String,Object> delProcess(String deploymentId) {
		Map<String,Object> result=new HashMap<String,Object>();
		try {
			repositoryService.deleteDeployment(deploymentId,true);
			result.put("flag", true);
		}catch(Exception e) {
			e.printStackTrace();
			result.put("flag", false);
			result.put("message", "System Exception!");
			return result;
		}
		return result;
	}
	
	/**
	 * 流程开启页面
	 * @param id
	 * @return
	 */
	@RequestMapping("/createPage")
	public ModelAndView createPage(String id) {
		ModelAndView mv=new ModelAndView("/activiti/processmgt/leave_create");
		mv.addObject("id",id);
		return mv;
	}
	
	/**
	 * 开启流程
	 * @param leave
	 * @return
	 */
	@RequestMapping("/createAndSend")
	@ResponseBody
	public Map<String,Object> createAndSend(Leave leave,HttpServletRequest req){
		Map<String,Object> result=new HashMap<String,Object>();
		try {
			activitiService.saveAndSend(leave, req);
			result.put("flag", true);
		}catch(Exception e) {
			result.put("flag", false);
			e.printStackTrace();
		}
		return result;
	}
}
