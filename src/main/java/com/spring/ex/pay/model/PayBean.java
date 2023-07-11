package com.spring.ex.pay.model;

import java.sql.Date;

public class PayBean {
	private int paynum;
	private int prdnum;
	private String buyer;
	private String prdname; // 상품명 (DB컬럼에는 없음)
	private int price;
	private String state; // 0:승인대기 1:관리자 승인완료 2:본인 결제취소 3:관리자 결제취소
	private Date regdate; // 결제일
	private Date aprvdate; // 관리자 결제승인일
	private String payment; // 결제 방식
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPrdname() {
		return prdname;
	}
	public void setPrdname(String prdname) {
		this.prdname = prdname;
	}
	public Date getAprvdate() {
		return aprvdate;
	}
	public void setAprvdate(Date aprvdate) {
		this.aprvdate = aprvdate;
	}
	public int getPaynum() {
		return paynum;
	}
	public void setPaynum(int paynum) {
		this.paynum = paynum;
	}
	public int getPrdnum() {
		return prdnum;
	}
	public void setPrdnum(int prdnum) {
		this.prdnum = prdnum;
	}
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	
	
}
