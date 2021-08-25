package com.human.app;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	/*
	 * @RequestMapping(value = "/", method = RequestMethod.GET) public String
	 * home1(Locale locale,Model m) { m.addAttribute("m_name","Jenny"); return
	 * "members"; }
	 */

	/*
	 * @RequestMapping(value = "/keeptouch", method = RequestMethod.GET) public
	 * String home(Locale locale, Model model) {
	 * logger.info("Welcome home! The client locale is {}.", locale);
	 * 
	 * Date date = new Date(); DateFormat dateFormat =
	 * DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
	 * 
	 * String formattedDate = dateFormat.format(date);
	 * 
	 * model.addAttribute("serverTime", formattedDate );
	 * model.addAttribute("myName","Jeon Min A");
	 * model.addAttribute("myAddress","Asan");
	 * model.addAttribute("myMobile","0101234567"); return "home"; 
	 }*/
	@ RequestMapping("/getinfo")
	public String getInfo() {
		return "getinfo";
	}
	@RequestMapping("/info")
	public String doInfo(HttpServletRequest hsr, Model model) {
		String uid=hsr.getParameter("userid");
		String addr=hsr.getParameter("address");
		System.out.println("uid="+uid);
		System.out.println("addr="+addr);
		model.addAttribute("loginid",uid);
		model.addAttribute("region",addr);
		return "viewinfo";
	}
	@RequestMapping("/choose")
	public String doChoose() {
		return "choose";
	}
	@RequestMapping("/selected")
	public String doJob(HttpServletRequest hsr,Model model) {
		String strPath=hsr.getParameter("path");
		if(strPath.equals("login")) {
			return "getinfo";
		} else if(strPath.equals("newbie")) {
			return "newbie";
		} else {
			return "choose";
		}
	}
}
