package com.spring.ex.crewboard.model;

import java.sql.Date;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.spring.ex.heart.crewboard.model.CrewBoardHeartBean;

public class CrewBoardBean {
	private int crewboardnum;
	private int state;
	private int large;
	private String small;
	
	@NotBlank(message = "모집할 크루를 선택하세요")
	private String crewname;
	
	@NotBlank(message = "제목을 입력하세요")
	private String subject;
	private String writer;
	
	@NotBlank(message = "글 내용을 입력하세요")
	private String contents;
	private Date regdate;
	private int readcount;
	
	//crewboardList에 현재인원/전체인원 출력하기 위해 crew 테이블과 조인 하기 위해서
	private int crewlimit;
	private int crewnow;
	
	//crewboardList에 댓글개수 출력하기 위해서
	private int comments;
	
	/* CrewBoardHeart 매핑 */
    private List<CrewBoardHeartBean> crewBoardHeartList; 
    
    public int getComments() {
		return comments;
	}
	public void setComments(int comments) {
		this.comments = comments;
	}
	public int getCrewlimit() {
		return crewlimit;
	}
	public void setCrewlimit(int crewlimit) {
		this.crewlimit = crewlimit;
	}
	public int getCrewnow() {
		return crewnow;
	}
	public void setCrewnow(int crewnow) {
		this.crewnow = crewnow;
	}
	
	public int getCrewboardnum() {
		return crewboardnum;
	}
	public void setCrewboardnum(int crewboardnum) {
		this.crewboardnum = crewboardnum;
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
	public String getCrewname() {
		return crewname;
	}
	public void setCrewname(String crewname) {
		this.crewname = crewname;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
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
	/* CrewBoardHeart 매핑 */
	public CrewBoardHeartBean getCrewBoardHeartBean() {
		return getCrewBoardHeartBean();
	}
	public void setCrewBoardHeartBean(CrewBoardHeartBean crewBoardHeartBean) {
		crewBoardHeartBean = crewBoardHeartBean;
	}
	
    
}//CrewBoardBean
