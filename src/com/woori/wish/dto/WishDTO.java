package com.woori.wish.dto;

import java.sql.Date;

public class WishDTO {
	private int wish_idx;//목록번호pk
	private Date w_date;//추가한날짜
	private String cId;//구매자 아이디fk
	private String p_idx;//상품번호fk
	
	public int getWish_idx() {
		return wish_idx;
	}
	public void setWish_idx(int wish_idx) {
		this.wish_idx = wish_idx;
	}
	public Date getW_date() {
		return w_date;
	}
	public void setW_date(Date w_date) {
		this.w_date = w_date;
	}
	public String getcId() {
		return cId;
	}
	public void setcId(String cId) {
		this.cId = cId;
	}
	public String getP_idx() {
		return p_idx;
	}
	public void setP_idx(String p_idx) {
		this.p_idx = p_idx;
	}
	
}
