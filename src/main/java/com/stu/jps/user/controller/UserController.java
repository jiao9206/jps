package com.stu.jps.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/user")
public class UserController {

	/**
	 * 登陆页面
	 * @return
	 */
	@RequestMapping("/login")
	public ModelAndView userLogin() {
		return new ModelAndView("login/login");
	}
	/**
	 * 登陆认证
	 * @return
	 */
	@RequestMapping("/doLogin")
	@ResponseBody
	public Map<String,String> doLogin(String formData) {
		Map<String,String> m=new HashMap<String,String>();
		try {
			JSONObject o=JSON.parseObject(formData);
			String username=o.getString("username");
			String password=o.getString("password");
			Subject subject=SecurityUtils.getSubject();
			UsernamePasswordToken token=new UsernamePasswordToken(username,password);
			subject.login(token);
		}catch(IncorrectCredentialsException e) {
			//密码错误
			m.put("flag", "fail");
			m.put("msg", "Password Erro !");
			return m;
		}catch(LockedAccountException e) {
			//账户被锁定
			m.put("flag", "fail");
			m.put("msg", "Account Locked !");
			return m;
		}catch(Exception e) {
			m.put("flag", "fail");
			m.put("msg", "System Exception !");
			return m;
		}
		m.put("flag", "success");
		return m;
	}
	/**
	 * 登陆成功后，跳转主页
	 * @return
	 */
	@RequestMapping("/loginSuccess")
	public ModelAndView loginSuccess() {
		return new ModelAndView("login/loginSuccess");
	}
}
