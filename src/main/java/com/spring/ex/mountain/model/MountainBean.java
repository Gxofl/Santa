package com.spring.ex.mountain.model;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public class MountainBean {
	
	//mountain 식별번호
	private int mountainnum;
	
	//mountain 이름
	@NotBlank(message = "산 이름을 입력하세요.")
	private String mountainname;
	
	//mountain 높이(단위 m)
	@NotBlank(message = "산 높이를 입력하세요.")
	private String mountainheight;
	
	//mountain 지역(목록에서는 동까지 표시)
	@NotBlank(message = "산 지역을 입력하세요.")
	private String mountainlocal;
	
	//mountain 상세설명
	@NotBlank(message = "상세 내용을 입력하세요.")
	private String mountaincontent;
	
	//mountain 사진
	private String mountainimage;

	//mountain 100대 명산
	private String mountaingreat;

	//이미지 업로드를 위한 객체, 변수 생성
	private MultipartFile upload;
	private String upload2;
	
	//setter, getter 메서드
	//업로드
	public MultipartFile getUpload() {
		return upload;
	}
	public void setUpload(MultipartFile upload) {
		System.out.println("setUpload()");
		this.upload = upload;
		
		String fileName = upload.getOriginalFilename();
		System.out.println("filename : "+fileName);
		
		this.mountainimage = fileName;
	}
	public String getUpload2() {
		return upload2;
	}
	public void setUpload2(String upload2) {
		this.upload2 = upload2;
	}
	//업로드 end
	
	public int getMountainnum() {
		return mountainnum;
	}

	public void setMountainnum(int mountainnum) {
		this.mountainnum = mountainnum;
	}

	public String getMountainname() {
		return mountainname;
	}

	public void setMountainname(String mountainname) {
		this.mountainname = mountainname;
	}

	public String getMountainheight() {
		return mountainheight;
	}

	public void setMountainheight(String mountainheight) {
		this.mountainheight = mountainheight;
	}

	public String getMountainlocal() {
		return mountainlocal;
	}

	public void setMountainlocal(String mountainlocal) {
		this.mountainlocal = mountainlocal;
	}

	public String getMountaincontent() {
		return mountaincontent;
	}

	public void setMountaincontent(String mountaincontent) {
		this.mountaincontent = mountaincontent;
	}

	public String getMountainimage() {
		return mountainimage;
	}

	public void setMountainimage(String mountainimage) {
		this.mountainimage = mountainimage;
	}
	
	public String getMountaingreat() {
		return mountaingreat;
	}

	public void setMountaingreat(String mountaingreat) {
		this.mountaingreat = mountaingreat;
	}
	
}
