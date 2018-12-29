package com.stu.jps.activiti.userAndGroupManage;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.GroupIdentityManager;

public class CustomGroupEntityManagerFactory implements SessionFactory{

	private CustomGroupEntityManager customGroupEntityManager;
	
	public void setCustomGroupEntityManager(CustomGroupEntityManager customGroupEntityManager) {
		this.customGroupEntityManager = customGroupEntityManager;
	}

	public Class<?> getSessionType() {
		// TODO Auto-generated method stub
		return GroupIdentityManager.class;
	}

	public Session openSession() {
		// TODO Auto-generated method stub
		return customGroupEntityManager;
	}

}
