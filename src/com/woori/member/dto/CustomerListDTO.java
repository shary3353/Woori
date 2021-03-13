package com.woori.member.dto;

public class CustomerListDTO {
	private String cid;
	private int cntReport;
	private int cntBlock;
	private int isBlack;
	private String reg_date;
	
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public int getCntReport() {
		return cntReport;
	}
	public void setCntReport(int cntReport) {
		this.cntReport = cntReport;
	}
	public int getCntBlock() {
		return cntBlock;
	}
	public void setCntBlock(int cntBlock) {
		this.cntBlock = cntBlock;
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
