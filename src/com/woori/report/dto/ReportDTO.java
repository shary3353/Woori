package com.woori.report.dto;

public class ReportDTO {
	/*report*/
	private int r_idx;
	private String content;
	private String reporter_id;
	private String target_id;
	private int Status;
	private String r_date;
	private String subject;
	/*Report_Categories*/
	private int rc_idx;
	private String category;
	
	
	
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getR_idx() {
		return r_idx;
	}
	public void setR_idx(int r_idx) {
		this.r_idx = r_idx;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReporter_id() {
		return reporter_id;
	}
	public void setReporter_id(String reporter_id) {
		this.reporter_id = reporter_id;
	}
	public String getTarget_id() {
		return target_id;
	}
	public void setTarget_id(String target_id) {
		this.target_id = target_id;
	}
	
	public String getR_date() {
		return r_date;
	}
	public void setR_date(String r_date) {
		this.r_date = r_date;
	}
	public int getRc_idx() {
		return rc_idx;
	}
	public void setRc_idx(int rc_idx) {
		this.rc_idx = rc_idx;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	
	
}
