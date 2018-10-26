package com.stu.jps.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stu.jps.user.entity.User;
import com.stu.jps.user.service.IUserService;

@Component
public class ShiroRealm extends AuthorizingRealm{

	private static Logger log=LoggerFactory.getLogger(ShiroRealm.class);
	
	@Autowired
	private IUserService userService;
	
	/**
	 * 角色和权限
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		return null;
	}

	/**
	 * 登陆认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		String username=(String)arg0.getPrincipal();
		String password=new String((char[])arg0.getCredentials());
		User user=userService.queryUser(username, password);
		if(user==null) {
			log.debug("no user:"+username);
			//用户不存在
			//throw new UnknownAccountException();
			//用户名密码不匹配
			throw new IncorrectCredentialsException();
		}else if("1".equals(user.getStatus())) {
			log.debug("user:"+username+" is locked!");
			//账户被锁定
			throw new LockedAccountException();
		}else {
			return new SimpleAuthenticationInfo(username,password,getName());
		}
	}

}
