package com.stu.jps.test.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stu.jps.test.dao.TestMapper;

@Controller
@RequestMapping("/testController")
public class TestController {

	@Autowired
	private TestMapper testMapper;
	
	@RequestMapping("/testMethod")
	@ResponseBody
	public String test() {
		List<Map> result=testMapper.queryAll();
		return result.toString();
	}
}
