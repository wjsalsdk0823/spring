package com.human.com;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private HttpSession session;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	@RequestMapping("/")
	public String login(HttpServletRequest hsr, Model model) {
		return "home";
		
	}
	@RequestMapping("/login")
	public String login1(HttpServletRequest hsr, Model model) {
		return "login";
	}
	@RequestMapping("/newbie")
	public String newbie(HttpServletRequest hsr, Model model) {
		return "newbie";
	}
	@RequestMapping("/viewinfo")
	public String show(@RequestParam String userid, @RequestParam String passcode, Model model) {
		model.addAttribute("login", userid);	
		model.addAttribute("newbie", passcode);
		return "viewinfo";
	}
	@RequestMapping("/newinfo")
	public String sss(@RequestParam String name,@RequestParam String id, @RequestParam String pass,
					@RequestParam String mobile, Model model) {
		model.addAttribute("n",name);
		model.addAttribute("i",id);
		model.addAttribute("p",pass);
		model.addAttribute("m",mobile);
		return "newinfo";
	}
	@RequestMapping(value="/check_user",method = RequestMethod.POST)
	public String check_user(HttpServletRequest hsr,Model model) {
		String userid=hsr.getParameter("userid");
		String passcode=hsr.getParameter("passcode");	
		HttpSession session=hsr.getSession();
		session.setAttribute("loginid",userid);		
		return "redirect:/booking";
	}
	@RequestMapping(value="/booking",method = RequestMethod.GET)
	public String booking(HttpServletRequest hsr) {
		HttpSession session=hsr.getSession();
		String loginid=(String)session.getAttribute("loginid");
		if(loginid.equals("wjsalsdk")) {
			return "booking";
		}else {
		return "redirect:/login";
	}
}
	@RequestMapping("/room")
	public String room(HttpServletRequest hsr) {
		HttpSession session=hsr.getSession();
		if(session.getAttribute("loginid")==null) {
			return "redirect:/login";
		}else {
		return"room";
	}
}
	@RequestMapping("/logout")
	public String logout(HttpServletRequest hsr) {
		HttpSession session=hsr.getSession();
		session.invalidate();
		return "redirect:/";
}
}	
