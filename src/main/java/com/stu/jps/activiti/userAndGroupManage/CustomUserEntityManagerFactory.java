package com.stu.jps.activiti.userAndGroupManage;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.UserIdentityManager;

public class CustomUserEntityManagerFactory implements SessionFactory{

	private CustomUserEntityManager customUserEntityManager;
	
	public void setCustomUserEntityManager(CustomUserEntityManager customUserEntityManager) {
        this.customUserEntityManager = customUserEntityManager;
    }
	
	public Class<?> getSessionType() {
		return UserIdentityManager.class;
	}

	public Session openSession() {
		return customUserEntityManager;
	}

}
