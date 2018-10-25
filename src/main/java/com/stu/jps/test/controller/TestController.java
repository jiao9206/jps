package com.stu.jps.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stu.jps.test.service.ITestService;

@Controller
@RequestMapping("/testController")
public class TestController {

	@Autowired
	private ITestService testService;
	
	@RequestMapping("/testMethod")
	@ResponseBody
	public String test(String a) {
		try {
			testService.update();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "System Exception!";
		}
		return "ÄãºÃ";
	}
}
