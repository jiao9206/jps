package com.stu.jps.activiti.userAndGroupManage;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stu.jps.activiti.dao.UserEntityMapper;
import com.stu.jps.activiti.entity.GroupEntity;

@Component
public class CustomGroupEntityManager extends GroupEntityManager{

	@Autowired
	private UserEntityMapper userEntityMapper;
	
	@Override
	public List<Group> findGroupsByUser(String userId){
		List<Group> groupList=new ArrayList<Group>();
		List<GroupEntity> groupEntityList=userEntityMapper.findGroupEntityListById(userId);
		for(GroupEntity g:groupEntityList) {
			groupList.add(g);
		}
		return groupList;
	}
}
