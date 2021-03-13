package com.woori.login.dto;

public class A_LoginDTO {

	private String userid;
	private String password;
	
	
	//생성자
	public void MemberDTO() {
		
		
	}
	//getter,setter
	public void MemberDTO(String userid, String password) {
		
		this.userid = userid;
		this.password = password;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}


