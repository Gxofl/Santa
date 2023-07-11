package com.spring.ex.supporters.model;

import java.sql.Date;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import com.spring.ex.heart.supporters.model.SupportersHeartBean;

public class SupportersBean {
	
	private int num;
	private String writer;
	
	@NotBlank(message="서포터즈명을 입력하세요")
	private String supportersname;
	
	private int state; //(DB)테이블에는 없는 변수, 0:모집중 1:모집완료 2:모집기한 마감을 의미함
	
	private String deadline; //모집마감일 : 오늘보다 이전 날짜를 선택할 수 없음
	
	@NotBlank(message="모집대상을 선택하세요.")
	private String target;
	
	@Range(min=5, max=50, message="5명 ~ 50명만 가능합니다.")
	private String limit; //모집인원
	
	private int applicant; //신청인원
	
	private String startdate; //활동 시작일 : 모집마감일(deadline)보다 이전 날짜를 선택할 수 없음
	
	private String enddate; //활동 종료일 : 활동시작일(startdate)보다 이전 날짜를 선택할 수 없음
	
	@NotBlank(message="내용을 입력하세요")
	private String content;
	private Date regdate;
	private int readcount;
	
	//DB에는 없는 변수
	private String applicantid;
	
	/*SupportersHeart 매핑 */
    private List<SupportersHeartBean> supportersHeartList; 
	
    
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}
	public int getApplicant() {
		return applicant;
	}
	public void setApplicant(int applicant) {
		this.applicant = applicant;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getSupportersname() {
		return supportersname;
	}
	public void setSupportersname(String supportersname) {
		this.supportersname = supportersname;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
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
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public String getApplicantid() {
		return applicantid;
	}
	public void setApplicantid(String applicantid) {
		this.applicantid = applicantid;
	}
	/*SupportersHeart 매핑 */
	public List<SupportersHeartBean> getSupportersHeartList() {
		return supportersHeartList;
	}
	public void setSupportersHeartList(List<SupportersHeartBean> supportersHeartList) {
		this.supportersHeartList = supportersHeartList;
	}
	
	
}
