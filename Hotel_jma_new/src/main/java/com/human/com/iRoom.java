package com.human.com;

import java.util.ArrayList;

public interface iRoom {
	ArrayList<Roominfo> getRoomList();
	ArrayList<Book> getBookingList();
	ArrayList<Booked> getBookedList(String checkin, String checkout);
	ArrayList<RoomType> getRoomType();
	void doDeleteRoom(int roomcode);
	
	void doEmpty(int bookcode);
	
	void doAddRoom(String roomcode,int roomtype, int howmany, int howmuch);
	void doUpdateRoom(int roomcode, String roomname, int roomtype, int howmany, int howmuch);
	void doSignin(String name,String loginid, String passcode);
	int doCheckUser(String userid,String passcode);
	void doAddBooking(int roomcode, int person,String checkin, String checkout,String name, String mobile);
}
