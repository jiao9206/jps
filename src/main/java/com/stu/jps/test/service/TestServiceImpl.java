package com.stu.jps.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stu.jps.test.dao.TestMapper;

@Service
public class TestServiceImpl implements ITestService{

	@Autowired
	private TestMapper testMapper;
	
	public void update() throws Exception{
		testMapper.save1();
//		int i=1/0;
		testMapper.save2();
	}

}
