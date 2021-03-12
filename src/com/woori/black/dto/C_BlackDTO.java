package com.woori.black.dto;

public class C_BlackDTO {
	private int B_IDX;		//블랙리스트 pk
	private String reason;	//블락사유
	private String cid;		//구매자 아이디
	private String admin_ID;//관리자 아이디
	private int stack;		//블락횟수
	private int isBlack;	//블락 여부
	
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
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
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
