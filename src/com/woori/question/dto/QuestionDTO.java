package com.woori.question.dto;

import java.sql.Date;

public class QuestionDTO {
	/*Question*/
	private int q_idx;//문의글번호pk
	private String subject;//문의제목
	private String content;//문의내용
	private String s_answer;//답변
	private int q_pw;//글비밀번호
	private Date q_reg_date;//문의날짜
	private String cid;//구매자아이디fk
	private String sid;//판매자아이디fk
	/*Q_Categories*/
	private int qc_idx;//문의카테고리번호pk-fk
	private String category;//카테고리이름
	
	//
	public int getQ_idx() {
		return q_idx;
	}
	public void setQ_idx(int q_idx) {
		this.q_idx = q_idx;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getS_answer() {
		return s_answer;
	}
	public void setS_answer(String s_answer) {
		this.s_answer = s_answer;
	}
	public int getQ_pw() {
		return q_pw;
	}
	public void setQ_pw(int q_pw) {
		this.q_pw = q_pw;
	}
	public Date getQ_reg_date() {
		return q_reg_date;
	}
	public void setQ_reg_date(Date q_reg_date) {
		this.q_reg_date = q_reg_date;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public int getQc_idx() {
		return qc_idx;
	}
	public void setQc_idx(int qc_idx) {
		this.qc_idx = qc_idx;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
}
