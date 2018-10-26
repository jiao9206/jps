package com.stu.jps.user.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

	/**
	 * µÇÂ½Ò³Ãæ
	 * @return
	 */
	@RequestMapping("/login")
	public ModelAndView userLogin() {
		return new ModelAndView("login/login");
	}
	/**
	 * µÇÂ½ÈÏÖ¤
	 * @return
	 */
	@RequestMapping("/doLogin")
	public ModelAndView doLogin(String username,String password) {
		ModelAndView mv=new ModelAndView("login/loginSuccess");
		try {
			Subject subject=SecurityUtils.getSubject();
			UsernamePasswordToken token=new UsernamePasswordToken(username,password);
			subject.login(token);
		}catch(IncorrectCredentialsException e) {
			//ÃÜÂë´íÎó
			mv=new ModelAndView("login/login");
			mv.addObject("message","Password Erro !");
			return mv;
		}catch(LockedAccountException e) {
			//ÕË»§±»Ëø¶¨
			mv=new ModelAndView("login/login");
			mv.addObject("message","Account Locked !");
			return mv;
		}catch(Exception e) {
			mv=new ModelAndView("login/login");
			mv.addObject("message","System Exception !");
			return mv;
		}
		return mv;
	}
}
