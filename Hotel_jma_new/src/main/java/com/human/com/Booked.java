package com.human.com;

public class Booked {
	int bookcode;
	int roomcode;
	int person;
	String checkin;
	String checkout;
	String name;
	String mobile;
	int type;
	int howmany;
	int max_howmany;
	public Booked() {}
	public Booked(int bookcode, int roomcode, int person, String checkin, String checkout, String name, String mobile,
			int type, int howmany, int max_howmany) {
	
		this.bookcode = bookcode;
		this.roomcode = roomcode;
		this.person = person;
		this.checkin = checkin;
		this.checkout = checkout;
		this.name = name;
		this.mobile = mobile;
		this.type = type;
		this.howmany = howmany;
		this.max_howmany = max_howmany;
	}
	public int getBookcode() {
		return bookcode;
	}
	public void setBookcode(int bookcode) {
		this.bookcode = bookcode;
	}
	public int getRoomcode() {
		return roomcode;
	}
	public void setRoomcode(int roomcode) {
		this.roomcode = roomcode;
	}
	public int getPerson() {
		return person;
	}
	public void setPerson(int person) {
		this.person = person;
	}
	public String getCheckin() {
		return checkin;
	}
	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}
	public String getCheckout() {
		return checkout;
	}
	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getHowmany() {
		return howmany;
	}
	public void setHowmany(int howmany) {
		this.howmany = howmany;
	}
	public int getMax_howmany() {
		return max_howmany;
	}
	public void setMax_howmany(int max_howmany) {
		this.max_howmany = max_howmany;
	}
	
	
}
