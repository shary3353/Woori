package com.woori.member.dto;

public class SellerListDTO {
	private String sid;
	private int cntReport;
	private int cntBlack;
	private int isBlack;
	private String reg_date;
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public int getCntReport() {
		return cntReport;
	}
	public void setCntReport(int cntReport) {
		this.cntReport = cntReport;
	}
	public int getCntBlack() {
		return cntBlack;
	}
	public void setCntBlack(int cntBlack) {
		this.cntBlack = cntBlack;
	}
	public int getIsBlack() {
		return isBlack;
	}
	public void setIsBlack(int isBlack) {
		this.isBlack = isBlack;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	
	
	

}
