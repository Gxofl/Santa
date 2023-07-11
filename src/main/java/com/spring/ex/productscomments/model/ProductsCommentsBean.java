package com.spring.ex.productscomments.model;

public class ProductsCommentsBean {
	private int num;
	private int idx;
	private String writer;
	private String content;
	private int ref;
	private int relevel;
	private int restep;
	private String regdate;
	private String deleteyn;
	private String isSecret;
	private String orgwriter; // 최상위 원댓글 작성자 (DB에는 작성 필요 없음, 비밀댓글 열람시 필요)
	
	public String getOrgwriter() {
		return orgwriter;
	}
	public void setOrgwriter(String orgwriter) {
		this.orgwriter = orgwriter;
	}
	public String getIsSecret() {
		return isSecret;
	}
	public void setIsSecret(String isSecret) {
		this.isSecret = isSecret;
	}
	// 댓글 리스트 ajax 로딩시 필요함
	private String pageNumber;
	
	public String getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getRelevel() {
		return relevel;
	}
	public void setRelevel(int relevel) {
		this.relevel = relevel;
	}
	public int getRestep() {
		return restep;
	}
	public void setRestep(int restep) {
		this.restep = restep;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDeleteyn() {
		return deleteyn;
	}
	public void setDeleteyn(String deleteyn) {
		this.deleteyn = deleteyn;
	}
}
