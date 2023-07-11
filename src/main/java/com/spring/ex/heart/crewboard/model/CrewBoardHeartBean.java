package com.spring.ex.heart.crewboard.model;

import com.spring.ex.crewboard.model.CrewBoardBean;

public class CrewBoardHeartBean {
		
    private int heartId;
	private int crewboardNum;
	private String userId;
	private String heartDate;
	
	/* CrewBoard 매핑 */
    private CrewBoardBean crewBoard; 
    
	//getter & setter
	public int getHeartId() {
		return heartId;
	}
	public void setHeartId(int heartId) {
		this.heartId = heartId;
	}
	public int getCrewboardNum() {
		return crewboardNum;
	}
	public void setCrewboardNum(int crewboardNum) {
		this.crewboardNum = crewboardNum;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getHeartDate() {
		return heartDate;
	}
	public void setHeartDate(String heartDate) {
		this.heartDate = heartDate;
	}
	/* CrewBoard 매핑 */
	public CrewBoardBean getCrewBoard() {
		return crewBoard;
	}
	
	public void setCrewBoard(CrewBoardBean crewBoard) {
		this.crewBoard = crewBoard;
	}
	
	
}//CrewBoardHeartBean
