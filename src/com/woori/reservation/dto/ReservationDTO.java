package com.woori.reservation.dto;

import java.sql.Date;

public class ReservationDTO {
	
	private int r_idx;
	private String visit_date;
	private String r_date;
	private String cId;
	private int p_idx;
	
	private int rs_idx;
	private String status;
	
	public int getR_idx() {
		return r_idx;
	}
	public void setR_idx(int r_idx) {
		this.r_idx = r_idx;
	}
	public String getVisit_date() {
		return visit_date;
	}
	public void setVisit_date(String visit_date) {
		this.visit_date = visit_date;
	}
	public String getR_date() {
		return r_date;
	}
	public void setR_date(String r_date) {
		this.r_date = r_date;
	}
	public String getcId() {
		return cId;
	}
	public void setcId(String cId) {
		this.cId = cId;
	}
	public int getP_idx() {
		return p_idx;
	}
	public void setP_idx(int p_idx) {
		this.p_idx = p_idx;
	}
	public int getRs_idx() {
		return rs_idx;
	}
	public void setRs_idx(int rs_idx) {
		this.rs_idx = rs_idx;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	//
	
	
	
	
}
