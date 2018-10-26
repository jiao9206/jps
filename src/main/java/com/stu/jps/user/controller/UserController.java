package com.stu.jps.user.controller;

import org.apache.shiro.SecurityUtils;
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
		try {
			Subject subject=SecurityUtils.getSubject();
			UsernamePasswordToken token=new UsernamePasswordToken("JiaoPeng","123");
			subject.login(token);
		}catch(Exception e) {
			
		}
		ModelAndView mv=new ModelAndView("login/loginSuccess");
		return mv;
	}
}
