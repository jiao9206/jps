package com.stu.jps.webSocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/webSocket")
public class WebSocketController {

	
	@RequestMapping("/todo")
	public ModelAndView goPage() {
		ModelAndView mv=new ModelAndView("/webSocket/webSocket");
		return mv;
	}
}
