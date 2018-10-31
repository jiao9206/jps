package com.stu.jps.shiro;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
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
		String username=((User)arg0.getPrimaryPrincipal()).getUser_name();
		Set<String> role=new HashSet<String>();
		Set<String> permiss=new HashSet<String>();
		if("admin".equals(username)) {//admin拥护管理员权限
			role.add("admin");
			role.add("ordinary");
			permiss.add("add");
			permiss.add("delete");
			permiss.add("edit");
			permiss.add("view");
		}else {
			role.add("ordinary");
			permiss.add("add");
			permiss.add("delete");
			permiss.add("edit");
			permiss.add("view");
		}
		SimpleAuthorizationInfo o=new SimpleAuthorizationInfo();
		o.setRoles(role);
		o.setStringPermissions(permiss);
		return o;
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
			return new SimpleAuthenticationInfo(user,password,getName());
		}
	}

}
