package com.woori.member.dto;

import java.sql.Date;

public class SellerDTO {
	private String sid;		//사업자아이디
	private String pw;		//비밀번호
	private String name;	//이름
	private String gender;	//성별
	private String store_call;//매장번호
	private Date birthday;	//생일
	private String s_phone;	//개인번호
	private String email;	//이메일
	
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getStore_call() {
		return store_call;
	}
	public void setStore_call(String store_call) {
		this.store_call = store_call;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getS_phone() {
		return s_phone;
	}
	public void setS_phone(String s_phone) {
		this.s_phone = s_phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
