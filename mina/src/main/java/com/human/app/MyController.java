package com.human.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {
	
	  @RequestMapping(value="/contactus",method=RequestMethod.GET) public String
	  method1(Model model) { model.addAttribute("mobile","0100000000"); return
	  "contactus"; }
	/*
	 * @RequestMapping("/contactus") public ModelAndView method2() { ModelAndView
	 * mav = new ModelAndView(); mav.addObject("mobile","123456789");
	 * mav.setViewName("contactus");//jsp이름 return mav; }
	 */
}


//myCompany Title
//회사 연락처는 ....입니다