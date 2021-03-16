package com.woori.login.dto;

public class C_LoginDTO {
	
	private String cid;
	private String pw;
	private String name;
	private String gender;
	private String email;
	private int birthday;
	private String phone;
	
	public String getcId() {
		return cid;
	}
	public void setcId(String cid) {
		this.cid = cid;
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
	public int getbirthday() {
		return birthday;
	}
	public void setbirthday(int birthday) {
		this.birthday =birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getgender() {
		return gender;
	}
	public void setgender(String gender) {
		this.gender = gender;
	}
	public String getphone() {
		return phone;
	}
	public void setphone(String phone) {
		this.phone = phone;
	}
}


