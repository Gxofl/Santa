package com.spring.ex.graph.model;

public class CrewGraphBean {
	private int totalcrew; // 전체 등록된 크루
	private int onedaycrew; // 전체 1일 크루
	private int regularcrew; // 전체 정기 크루
	private int ploggingcrew; // 전체 플로깅 크루
	private int mountaincrew; // 전체 등산 크루
	
	public int getTotalcrew() {
		return totalcrew;
	}
	public void setTotalcrew(int totalcrew) {
		this.totalcrew = totalcrew;
	}
	public int getOnedaycrew() {
		return onedaycrew;
	}
	public void setOnedaycrew(int onedaycrew) {
		this.onedaycrew = onedaycrew;
	}
	public int getRegularcrew() {
		return regularcrew;
	}
	public void setRegularcrew(int regularcrew) {
		this.regularcrew = regularcrew;
	}
	public int getPloggingcrew() {
		return ploggingcrew;
	}
	public void setPloggingcrew(int ploggingcrew) {
		this.ploggingcrew = ploggingcrew;
	}
	public int getMountaincrew() {
		return mountaincrew;
	}
	public void setMountaincrew(int mountaincrew) {
		this.mountaincrew = mountaincrew;
	}
	
	
}
