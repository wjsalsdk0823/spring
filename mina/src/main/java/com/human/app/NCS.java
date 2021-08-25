package com.human.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ncs")
public class NCS {


	@RequestMapping("/student")
	public String viewClass() {
		//....
		return "viewClass";
	}
}
