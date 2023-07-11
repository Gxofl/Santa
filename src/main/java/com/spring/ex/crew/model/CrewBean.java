package com.spring.ex.crew.model;

import java.sql.Date;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

public class CrewBean {
	
	@Min(value=1, message = "구분1을 선택하세요")
	private int large; // 1: 1일크루 , 2: 정기크루
	
	@NotBlank(message = "구분2를 선택하세요")
	private String small; // M: 등산, P: 플로깅
	
	private int crewnum;
	
	private String crewname;
	
	@NotBlank(message = "크루설명을 입력하세요")
	private String crewcontents;
	
	@Range(min=2, max=20, message="2명 ~ 20명만 가능합니다.")
	private String crewlimit;
	private int crewnow;
	private String crewmember;
	private String crewmanager;
	private Date regdate;
	private int state;
	
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getLarge() {
		return large;
	}
	public void setLarge(int large) {
		this.large = large;
	}
	public String getSmall() {
		return small;
	}
	public void setSmall(String small) {
		this.small = small;
	}
	public int getCrewnum() {
		return crewnum;
	}
	public void setCrewnum(int crewnum) {
		this.crewnum = crewnum;
	}
	public String getCrewname() {
		return crewname;
	}
	public void setCrewname(String crewname) {
		this.crewname = crewname;
	}
	public String getCrewcontents() {
		return crewcontents;
	}
	public void setCrewcontents(String crewcontents) {
		this.crewcontents = crewcontents;
	}
	public String getCrewlimit() {
		return crewlimit;
	}
	public void setCrewlimit(String crewlimit) {
		if(crewlimit == null) {
			this.crewlimit = "0";
		}else {
			this.crewlimit = crewlimit;
		}
	}
	public int getCrewnow() {
		return crewnow;
	}
	public void setCrewnow(int crewnow) {
		this.crewnow = crewnow;
	}
	public String getCrewmember() {
		return crewmember;
	}
	public void setCrewmember(String crewmember) {
		this.crewmember = crewmember;
	}
	public String getCrewmanager() {
		return crewmanager;
	}
	public void setCrewmanager(String crewmanager) {
		this.crewmanager = crewmanager;
	}
	
}
