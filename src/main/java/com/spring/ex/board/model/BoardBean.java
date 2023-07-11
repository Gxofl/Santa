package com.spring.ex.board.model;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import com.spring.ex.heart.board.model.BoardHeartBean;

public class BoardBean {
	
	private int num;
	private String userid;
	
	@NotBlank(message="카테고리를 선택하세요")
	private String category;
	
	@NotBlank(message="글제목을 입력하세요")
	private String subject;
	
	@NotBlank(message="내용을 입력하세요")
	private String content;
	private String regdate;
	private String readcount;
	
	@NotEmpty(message="이미지는 한 개 이상 업로드 해야합니다.")
	private String image;
	
	private int boardcommentscount; //댓글수 출력하기위한 칼럼
	
	/*BoardHeart 매핑 */
    private List<BoardHeartBean> boardHeartList; 
    
	//////////	
	private MultipartFile upload;
	
	private String upload2; //수정할때 기존 이미지를 담는 변수(수정할때 웹서버폴더에서 기존이미지를 지워야 하므로)
		
	public MultipartFile getUpload() {
		return upload;
	}
	public void setUpload(MultipartFile upload) {
		if(upload.isEmpty()) { // 수정시 파일 선택을 안했을때 (기존이미지 그대로 업로드)
			System.out.println("uploadEmpty()");
			this.upload = upload;
			this.image = this.getUpload2();
		}else {
			System.out.println("setUpload()");
			this.upload = upload;
			
			String fileName = upload.getOriginalFilename();
			System.out.println("fileName : "+fileName);
			
			this.image = fileName;
		}
	}
	
	public String getUpload2() {
		return upload2;
	}
	public void setUpload2(String upload2) {
		this.upload2 = upload2;
	}
	//////////

	
	public int getNum() {
		return num;
	}

	public int getBoardcommentscount() {
		return boardcommentscount;
	}

	public void setBoardcommentscount(int boardcommentscount) {
		this.boardcommentscount = boardcommentscount;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getReadcount() {
		return readcount;
	}

	public void setReadcount(String readcount) {
		this.readcount = readcount;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	/* BoardHeart 매핑 */
	public List<BoardHeartBean> getBoardHeartList() {
		return boardHeartList;
	}

	public void setBoardHeartList(List<BoardHeartBean> boardHeartList) {
		this.boardHeartList = boardHeartList;
	}
	
	
}
