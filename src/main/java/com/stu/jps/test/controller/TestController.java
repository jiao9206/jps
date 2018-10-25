package com.stu.jps.test.controller;

import java.util.List;

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
	
	@RequestMapping("/testMethod")
	@ResponseBody
	public String test(String a) {
		List<Test> list;
		try {
			list=testService.queryAll();
			log.info("test logback:"+list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "System Exception!";
		}
		return "result:"+list.toString();
	}
}
