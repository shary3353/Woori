package com.woori.question.dto;

import java.sql.Date;

public class QuestionDTO {
	/*Question*/
	private int q_idx;
	private String subject;
	private String content;
	private String s_answer;
	private int q_pw;
	private String q_reg_date;
	private String cid;
	private String sid;
	/*Q_Categories*/
	private int qc_idx;
	private String category;
	private String p_name;
	
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
	public String getQ_reg_date() {
		return q_reg_date;
	}
	public void setQ_reg_date(String q_reg_date) {
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
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	
}
