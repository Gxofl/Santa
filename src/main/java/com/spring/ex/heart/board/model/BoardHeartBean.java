package com.spring.ex.heart.board.model;

import com.spring.ex.board.model.BoardBean;

public class BoardHeartBean {
		
    private int heartId;
	private int boardNum;
	private String userId;
	private String heartDate;
	
	/* Board 매핑 */
    private BoardBean board;

 	//getter & setter
	public int getHeartId() {
		return heartId;
	}

	public void setHeartId(int heartId) {
		this.heartId = heartId;
	}

	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
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

	public BoardBean getBoard() {
		return board;
	}

	public void setBoard(BoardBean board) {
		this.board = board;
	} 
    
	
}//CrewBoardHeartBean
