package com.woori.login.dto;

public class S_LoginDTO {

	private String sid;
	private String pw;
	private String name;
	private String gender;
	private String store_call;
	private int birthday;
	private String phone;
	private String email;
	
	public String getsId() {
		return sid;
	}
	public void setsId(String sid) {
		this.sid = sid;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getname() {
		return name;
	}
	public void setname(String name) {
		this.name = name;
	}
	public String getgender() {
		return gender;
	}
	public void setgender(String gender) {
		this.gender = gender;
	}
	public String getstore_call() {
		return store_call;
	}
	public void setstore_call(String store_call) {
		this.store_call = store_call;
	}
	public int getbirthday() {
		return birthday;
	}
	public void setbirthday(int birthday) {
		this.birthday = birthday;
	}
	public String getphone() {
		return phone;
	}
	public void setphone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}

