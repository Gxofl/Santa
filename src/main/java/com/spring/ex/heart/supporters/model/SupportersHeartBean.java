package com.spring.ex.heart.supporters.model;

import com.spring.ex.supporters.model.SupportersBean;
import com.spring.ex.supportersapply.model.SupportersApplyBean;

public class SupportersHeartBean {
		
    private int heartId;
	private int supportersNum;
	private String userId;
	private String heartDate;
	
	/* SupportersBean 매핑 */
    private SupportersBean supporters;

	//getter & setter
	public int getHeartId() {
		return heartId;
	}

	public void setHeartId(int heartId) {
		this.heartId = heartId;
	}

	public int getSupportersNum() {
		return supportersNum;
	}

	public void setSupportersNum(int supportersNum) {
		this.supportersNum = supportersNum;
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
	/* SupportersBean 매핑 */
	public SupportersBean getSupporters() {
		return supporters;
	}

	public void setSupporters(SupportersBean supporters) {
		this.supporters = supporters;
	}
	
	
	
}//CrewBoardHeartBean
