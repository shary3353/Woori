package com.woori.wish.dto;

import java.sql.Date;

public class WishDTO {
	private int wish_idx;
	private String w_date;
	private String cId;
	private String p_idx;
	
	public int getWish_idx() {
		return wish_idx;
	}
	public void setWish_idx(int wish_idx) {
		this.wish_idx = wish_idx;
	}
	public String getW_date() {
		return w_date;
	}
	public void setW_date(String w_date) {
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
