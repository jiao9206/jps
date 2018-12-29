package com.stu.jps.activiti.userAndGroupManage;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.persistence.entity.IdentityInfoEntity;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stu.jps.activiti.dao.UserEntityMapper;
import com.stu.jps.activiti.entity.GroupEntity;
import com.stu.jps.activiti.entity.UserEntity;

@Component
public class CustomUserEntityManager extends UserEntityManager{

	@Autowired
	private UserEntityMapper userEntityMapper;
	
	@Override
	public User  findUserById(String userId) {
		UserEntity userEntity=userEntityMapper.findUserById(userId);
		return userEntity;
	}
	@Override
	public List<Group> findGroupsByUser(String userId){
		List<Group> groupList=new ArrayList<Group>();
		List<GroupEntity> groupEntityList=userEntityMapper.findGroupEntityListById(userId);
		for(GroupEntity g:groupEntityList) {
			groupList.add(g);
		}
		return groupList;
	}
	@Override
	public IdentityInfoEntity findUserInfoByUserIdAndKey(String userId, String key) {
		throw new RuntimeException("This method(findUserInfoByUserIdAndKey) is no implement.");
	}
	@Override
	public List<String> findUserInfoKeysByUserIdAndType(String userId, String type){
		throw new RuntimeException("This method(findUserInfoKeysByUserIdAndType) is no implement.");
	}
	@Override
	public Boolean checkPassword(String userId, String password) {
		return true;
	}
}
