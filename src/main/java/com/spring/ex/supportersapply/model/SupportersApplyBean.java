package com.spring.ex.supportersapply.model;

import java.sql.Date;
import java.util.List;

import com.spring.ex.heart.supporters.model.SupportersHeartBean;

public class SupportersApplyBean {
	
	private int num; 
	private String supportersname; //서포터즈명
	private String applicantid; //신청회원의 아이디
	private int state; //신청상태 ( 0:신청, 1:취소 )
	private int target; //모집대상
	private String applydate; //신청일
	private String startdate; //활동시작일
	private String enddate; //활동종료일
	private int joinnum; //supporters의 num과 같은값
	
	//DB에는 없는 멤버변수
	private String userId;
	private String name;
	private String userRole;
	private String gender;
	private String birth;
	//DB에는 없는 멤버변수
	
	/*
	supporters_apply의 join_num과 supporters의 num이 같은값이다.
	supporters 1 : n supporters_apply 
	이렇게 1:n 관계를 가지는거(왜냐하면 하나의 서포터즈에 여러회원이 신청할 수 있으므로)	
	*/
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getSupportersname() {
		return supportersname;
	}
	public void setSupportersname(String supportersname) {
		this.supportersname = supportersname;
	}
	public String getApplicantid() {
		return applicantid;
	}
	public void setApplicantid(String applicantid) {
		this.applicantid = applicantid;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getTarget() {
		return target;
	}
	public void setTarget(int target) {
		this.target = target;
	}
	public String getApplydate() {
		return applydate;
	}
	public void setApplydate(String applydate) {
		this.applydate = applydate;
	}
	public int getJoinnum() {
		return joinnum;
	}
	public void setJoinnum(int joinnum) {
		this.joinnum = joinnum;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	
	
	
}
