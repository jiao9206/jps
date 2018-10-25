package com.stu.jps.test.service;

import java.util.List;

import com.stu.jps.test.entity.Test;

public interface ITestService {

	public void update() throws Exception;
	
	public List<Test> queryAll() throws Exception;

}
