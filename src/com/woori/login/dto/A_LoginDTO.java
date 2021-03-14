package com.woori.login.dto;

public class A_LoginDTO {

	private String id;
	private String pw;
	
	
	//생성자
	public void MemberDTO() {
		
		
	}
	//getter,setter
	public void MemberDTO(String id, String pw) {
		
		this.id = id;
		this.pw = pw;
	}
	public String getid() {
		return id;
	}
	public void setid(String id) {
		this.id = id;
	}
	public String getpw() {
		return pw;
	}
	public void setpw(String pw) {
		this.pw = pw;
	}
	
	
	
}


