package com.stu.jps.test.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.stu.jps.test.entity.Test;

@Repository
public interface TestMapper {
	
	public List<Test> queryAll();
	
	public void save1();
	
	public void save2();
}
