package com.woori.report.dto;

public class ReportDTO {
	/*report*/
	private int r_idx;//신고목록번호pk
	private String content;//신고내용
	private String reporter_id;//신고자ID
	private String target_id;//신고대상자ID
	private String Status;//신고처리상태
	/*Report_Categories*/
	private int rc_idx;//신고카테고리번호pk-fk
	private String category;//신고카테고리내용
	
	//
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
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
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
