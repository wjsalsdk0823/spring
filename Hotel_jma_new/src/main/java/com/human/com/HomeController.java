package com.human.com;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private HttpSession session;
	@Autowired
	private SqlSession sqlSession;
	
	
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
	@RequestMapping(value="/signin",method = RequestMethod.POST,
			produces = "application/text; charset=utf8")
	public String newbie(HttpServletRequest hsr) {
		//디버깅
	    System.out.println("debug");
		String txtname=hsr.getParameter("txtname");
		System.out.println("name["+txtname+"]");
		String loginid=hsr.getParameter("login");
		System.out.println("name["+loginid+"]");
		String passcode=hsr.getParameter("passcode");
		System.out.println("name["+passcode+"]");
		iRoom room=sqlSession.getMapper(iRoom.class);
		room.doSignin(txtname,loginid,passcode);
		return "/login";
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
		//DB에서 유저확인:기존유저면 booking 없으면 home으로 
		iRoom room=sqlSession.getMapper(iRoom.class);
		int n=room.doCheckUser(userid,passcode);
		System.out.println(n);
		if(n>0) {
			HttpSession session=hsr.getSession();
			session.setAttribute("loginid",userid);		
			return "redirect:/booking";//RequestMapping의 경로 이동
		} else {
			return "home";
		}		
	}
	@RequestMapping(value="/booking",method = RequestMethod.GET)
	public String booking(HttpServletRequest hsr, Model model) {
		HttpSession session=hsr.getSession(true);
		String loginid=(String)session.getAttribute("loginid");	
		iRoom room=sqlSession.getMapper(iRoom.class);
	      iRoom room_type = sqlSession.getMapper(iRoom.class);
	      ArrayList<RoomType> roomtype=room.getRoomType();
	      model.addAttribute("list_type",roomtype);
	         return "booking";
			
	}
	@RequestMapping(value="/addBooking",method=RequestMethod.POST,
	         produces="application/text; charset=utf8")
	   @ResponseBody
	   public String addbooking(HttpServletRequest hsr) {
	      System.out.println("addbooking");
	      //int bookcode=Integer.parseInt(hsr.getParameter("bookcode"));
	      // System.out.println("bookcode");
	      int roomcode=Integer.parseInt(hsr.getParameter("roomcode"));	      
	      int person=Integer.parseInt(hsr.getParameter("person"));
	      String checkin=hsr.getParameter("checkin");	    	      
	      String checkout=hsr.getParameter("checkout");	     	      
	      String name=hsr.getParameter("name");	  	      
	      String mobile=hsr.getParameter("mobile");	    
	      iRoom room=sqlSession.getMapper(iRoom.class);
	      System.out.println(roomcode+","+person+","+checkin+","+checkout+","+name+","+mobile);
	      room.doAddBooking(roomcode,person,checkin,checkout,name,mobile);
	      return "ok";
	   }

	@RequestMapping("/room")
	public String room(HttpServletRequest hsr, Model model) {
		System.out.println("/room");
		HttpSession session=hsr.getSession();
		if(session.getAttribute("loginid")==null) {
			return "redirect:/login";
		}else {
			System.out.println("room.jsp");
		//여기서 인터페이스 호출하고 결과를 room.jsp에 전달
			iRoom room=sqlSession.getMapper(iRoom.class);
			
			/*
			 * ArrayList<Roominfo> roominfo=room.getRoomList();
			 * model.addAttribute("list",roominfo);
			 */		
			ArrayList<RoomType> roomtype=room.getRoomType();			
			model.addAttribute("list2",roomtype);
		return"room";
		}
	}
	@RequestMapping("/logout")
	public String logout(HttpServletRequest hsr) {
		HttpSession session=hsr.getSession();
		session.invalidate();
		return "redirect:/";
}
	@RequestMapping(value="/getRoomList",method = RequestMethod.POST,
			produces = "application/text; charset=utf8")
	@ResponseBody
	public String getRoomList(HttpServletRequest hsr) {
		iRoom room=sqlSession.getMapper(iRoom.class);
		ArrayList<Roominfo> roominfo=room.getRoomList();//camel notation 
		//찾아진 데이터로 joonarray만들기
		JSONArray ja = new JSONArray();
		for(int i=0;i<roominfo.size();i++) {
			JSONObject jo=new JSONObject();
			jo.put("roomcode", roominfo.get(i).getRoomcode());
			jo.put("roomname", roominfo.get(i).getRoomname());
			jo.put("typename", roominfo.get(i).getTypename());
			jo.put("howmany", roominfo.get(i).getHowmany());			
			jo.put("howmuch", roominfo.get(i).getHowmuch());
			ja.add(jo);
		}
		return ja.toString();
	}
	@RequestMapping(value="/geRoomList",method = RequestMethod.POST,
			produces = "application/text; charset=utf8")
	@ResponseBody
	public String geRoomList(HttpServletRequest hsr) {
		iRoom room=sqlSession.getMapper(iRoom.class);
		ArrayList<Roominfo> roominfo=room.getRoomList();//camel notation 
		//찾아진 데이터로 joonarray만들기
		JSONArray ja = new JSONArray();
		for(int i=0;i<roominfo.size();i++) {
			JSONObject jo=new JSONObject();
			jo.put("roomcode", roominfo.get(i).getRoomcode());
			jo.put("roomname", roominfo.get(i).getRoomname());
			jo.put("typename", roominfo.get(i).getTypename());
			jo.put("howmany", roominfo.get(i).getHowmany());			
			jo.put("howmuch", roominfo.get(i).getHowmuch());
			ja.add(jo);
		}
		return ja.toString();
}
	@RequestMapping(value="/deleteRoom",method = RequestMethod.POST,
			produces = "application/text; charset=utf8")
	@ResponseBody
	public String deleteRoom(HttpServletRequest hsr) {
		int roomcode=Integer.parseInt(hsr.getParameter("roomcode"));
		iRoom room=sqlSession.getMapper(iRoom.class);
		room.doDeleteRoom(roomcode);
		return  "ok";
}
	@RequestMapping(value="/doEmpty",method = RequestMethod.POST,
			produces = "application/text; charset=utf8")
	@ResponseBody
	public String doEmpty(HttpServletRequest hsr) {
		int bookcode=Integer.parseInt(hsr.getParameter("bookcode"));
		iRoom room=sqlSession.getMapper(iRoom.class);

		room.doEmpty(bookcode);
		return  "ok";
}
	@RequestMapping(value="/addRoom",method = RequestMethod.POST,
			produces = "application/text; charset=utf8")
	@ResponseBody
	public String addRoom(HttpServletRequest hsr) {
		String rname=hsr.getParameter("roomname");
		int rtype=Integer.parseInt(hsr.getParameter("roomtype"));
		int howmany=Integer.parseInt(hsr.getParameter("howmany"));
		int howmuch=Integer.parseInt(hsr.getParameter("howmuch"));
		iRoom room=sqlSession.getMapper(iRoom.class);
		room.doAddRoom(rname,rtype,howmany,howmuch);
		return "ok";
}
	@RequestMapping(value="/updateRoom",method = RequestMethod.POST,
			produces = "application/text; charset=utf8")
	@ResponseBody
	public String updateRoom(HttpServletRequest hsr) {
		iRoom room=sqlSession.getMapper(iRoom.class);
		int roomcode=Integer.parseInt(hsr.getParameter("roomcode"));				
		String roomname=hsr.getParameter("roomname");
		int roomtype=Integer.parseInt(hsr.getParameter("roomtype"));
		//System.out.println("roomcode ["+roomcode+"]");			
		room.doUpdateRoom(Integer.parseInt(hsr.getParameter("roomcode")),
		hsr.getParameter("roomname"),
		Integer.parseInt(hsr.getParameter("roomtype")),
		Integer.parseInt(hsr.getParameter("howmany")),
		Integer.parseInt(hsr.getParameter("howmuch")));		
		return "ok";
}
	@RequestMapping(value="/getBookingList",method=RequestMethod.POST,
	         produces="application/text; charset=utf8")
	   @ResponseBody
	   public String book(HttpServletRequest hsr) {
		
	      iRoom booking=sqlSession.getMapper(iRoom.class);
	      ArrayList<Book> getbookingList=booking.getBookingList();
	      JSONArray j1=new JSONArray();
	      for(int i = 0;i<getbookingList.size(); i++) {	    	  
	    	JSONObject jo1 = new JSONObject();
	    	jo1.put("bookcode",getbookingList.get(i).getBookcode());	    	
	    	jo1.put("roomcode",getbookingList.get(i).getRoomcode());	  
	    	jo1.put("person",getbookingList.get(i).getPerson());	
	    	jo1.put("checkin",getbookingList.get(i).getCheckin());	   
	    	jo1.put("checkout",getbookingList.get(i).getCheckout());
	    	jo1.put("name",getbookingList.get(i).getName());	  
	    	jo1.put("mobile",getbookingList.get(i).getMobile());
	    	j1.add(jo1);	    	
	      }
	      System.out.println(j1.toString());
	      return j1.toString();
     }	
	  @RequestMapping(value="/getBookedList",method=RequestMethod.POST,
	  produces="application/text; charset=utf8")
	  @ResponseBody public String Booked(HttpServletRequest hsr) { 
	  iRoom booking=sqlSession.getMapper(iRoom.class);
	  String checkin=hsr.getParameter("checkin"); 
	  String checkout=hsr.getParameter("checkout"); 
	  System.out.println(checkin); 
	  ArrayList<Booked> getbookedList=booking.getBookedList(checkin,checkout); 
	  JSONArray a1=new JSONArray();
	  System.out.println("a1"); 
	  for(int i = 0;i<getbookedList.size(); i++) {
	  System.out.println("arr"); JSONObject jo1 = new JSONObject();
	  jo1.put("bookcode",getbookedList.get(i).getBookcode());
	  jo1.put("roomcode",getbookedList.get(i).getRoomcode());
	  jo1.put("person",getbookedList.get(i).getPerson());
	  jo1.put("checkin",getbookedList.get(i).getCheckin());
	  jo1.put("checkout",getbookedList.get(i).getCheckout());
	  jo1.put("name",getbookedList.get(i).getName());
	  jo1.put("mobile",getbookedList.get(i).getMobile());
	  a1.add(jo1);
	  }
	return a1.toString();
      }  
	  @RequestMapping(value="/getNotbooked",method = RequestMethod.POST,
				produces = "application/text; charset=utf8")
		@ResponseBody
		public String getNotbooked(HttpServletRequest hsr) {
			iRoom room=sqlSession.getMapper(iRoom.class);
			String checkin=hsr.getParameter("checkin"); 
			String checkout=hsr.getParameter("checkout");
			System.out.println(checkin+","+checkout);
			ArrayList<Roominfo> roominfo=room.getNotbooked(checkin,checkout);//camel notation 
			//찾아진 데이터로 joonarray만들기
			JSONArray ja = new JSONArray();
			for(int i=0;i<roominfo.size();i++) {
				JSONObject jo=new JSONObject();
				jo.put("roomcode", roominfo.get(i).getRoomcode());
				jo.put("roomname", roominfo.get(i).getRoomname());
				jo.put("typename", roominfo.get(i).getTypename());
				jo.put("howmany", roominfo.get(i).getHowmany());			
				jo.put("howmuch", roominfo.get(i).getHowmuch());
				ja.add(jo);
			}
			return ja.toString();
		}
	  @RequestMapping(value="/doUpdate",method = RequestMethod.POST,
				produces = "application/text; charset=utf8")
		@ResponseBody
			public String update(HttpServletRequest hsr) {
			
			int bookcode=Integer.parseInt(hsr.getParameter("bookcode"));
			int person=Integer.parseInt(hsr.getParameter("person"));
			String name=hsr.getParameter("name");
			String mobile=hsr.getParameter("mobile"); 
			iRoom room=sqlSession.getMapper(iRoom.class);
			room.doUpdate(bookcode,person,name,mobile);
			return "ok";
	}
}	
