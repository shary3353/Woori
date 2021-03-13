package com.woori.black.dto;

public class S_BlackDTO {
	private int B_IDX;
	private String reason;
	private String sid;
	private String admin_ID;
	private int stack;
	private int isBlack;
	
	public int getB_IDX() {
		return B_IDX;
	}
	public void setB_IDX(int b_IDX) {
		B_IDX = b_IDX;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getAdmin_ID() {
		return admin_ID;
	}
	public void setAdmin_ID(String admin_ID) {
		this.admin_ID = admin_ID;
	}
	public int getStack() {
		return stack;
	}
	public void setStack(int stack) {
		this.stack = stack;
	}
	public int getIsBlack() {
		return isBlack;
	}
	public void setIsBlack(int isBlack) {
		this.isBlack = isBlack;
	}
	
	
}
