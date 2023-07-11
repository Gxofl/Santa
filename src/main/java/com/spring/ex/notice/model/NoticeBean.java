package com.spring.ex.notice.model;

import java.sql.Date;

import org.hibernate.validator.constraints.NotBlank;

public class NoticeBean {
	
	private int num;
	private String userid;
	
	@NotBlank(message="글제목을 입력하세요.")
	private String subject;
	
	@NotBlank(message="내용을 입력하세요")
	private String content;
	private Date regdate;
	private String readcount;
	
	
	public NoticeBean() {
		super();
	}
	
	public NoticeBean(int num, String userid, String subject, String content, Date regdate, String readcount) {
		super();
		this.num = num;
		this.userid = userid;
		this.subject = subject;
		this.content = content;
		this.regdate = regdate;
		this.readcount = readcount;
	}
	
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
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
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getReadcount() {
		return readcount;
	}
	public void setReadcount(String readcount) {
		this.readcount = readcount;
	}
	
	
	
	
}
