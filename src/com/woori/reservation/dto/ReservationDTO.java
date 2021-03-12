package com.woori.reservation.dto;

import java.sql.Date;

public class ReservationDTO {
	/*예약 Reservation*/
	private int r_idx;//예약번호pk
	private Date visit_date;//예약날짜
	private Date r_date;//신청날짜sysdate
	private String cId;//구매자아이디
	private int p_idx;//상품번호
	/*예약상황 Reservation_status*/
	private int rs_idx;//예약상황번호pk-fk
	private String status;//예약상황종류
	
	//
	public int getR_idx() {
		return r_idx;
	}
	public void setR_idx(int r_idx) {
		this.r_idx = r_idx;
	}
	public Date getVisit_date() {
		return visit_date;
	}
	public void setVisit_date(Date visit_date) {
		this.visit_date = visit_date;
	}
	public Date getR_date() {
		return r_date;
	}
	public void setR_date(Date r_date) {
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
	
	
	
}
