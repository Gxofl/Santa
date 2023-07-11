package com.spring.ex.boardcomments.model;

import org.hibernate.validator.constraints.NotBlank;

public class BoardCommentsBean {
	
	private int num;
	private int bnum;
	private int ref;
	private int relevel;
	private int restep;
	private String isdeleted;
	private String userid;	
	@NotBlank(message="댓글내용을 입력해야합니다.")
	private String content;
	private String regdate;
	private String isSecret;
	
	private String orgwriter; // 최상위 원댓글 작성자 (DB에는 작성 필요 없음, 비밀댓글 열람시 필요)
	
	// 댓글 목록의 ajax에서 필요
	private String pageNumber;
		
	
	public String getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}
	public String getOrgwriter() {
		return orgwriter;
	}

	public void setOrgwriter(String orgwriter) {
		this.orgwriter = orgwriter;
	}	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getBnum() {
		return bnum;
	}
	public void setBnum(int bnum) {
		this.bnum = bnum;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getRelevel() {
		return relevel;
	}
	public void setRelevel(int relevel) {
		this.relevel = relevel;
	}
	public int getRestep() {
		return restep;
	}
	public void setRestep(int restep) {
		this.restep = restep;
	}
	public String getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(String isdeleted) {
		this.isdeleted = isdeleted;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getIsSecret() {
		return isSecret;
	}

	public void setIsSecret(String isSecret) {
		this.isSecret = isSecret;
	}	
	
	
}
