package com.krry.action;

public class ShopMsg {

	private String username;
	private String number;
	
	public ShopMsg() {
		// TODO Auto-generated constructor stub
	}
	
	public ShopMsg(String username,String number){
		this.username = username;
		this.number = number;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	
}
