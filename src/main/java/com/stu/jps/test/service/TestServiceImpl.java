package com.stu.jps.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stu.jps.test.dao.TestMapper;
import com.stu.jps.test.entity.Test;

@Service
public class TestServiceImpl implements ITestService{

	@Autowired
	private TestMapper testMapper;
	
	public void update() throws Exception{
		testMapper.save1();
//		int i=1/0;
		testMapper.save2();
	}

	public List<Test> queryAll() throws Exception {
		// TODO Auto-generated method stub
		List<Test> list=testMapper.queryAll();
		return list;
	}

}
