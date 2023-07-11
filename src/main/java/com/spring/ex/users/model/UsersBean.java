package com.spring.ex.users.model;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public class UsersBean {
	private final String input_message = " 필수입력 사항입니다.";
	private final String choice_message = " 필수선택 사항입니다.";
	
	private int userNum;
	@Pattern(regexp = "^(?=.*[a-z0-9])[a-z0-9]{5,16}$", message = "아이디는 영문 5자~16자로 작성해야합니다.")
	private String userId;
	
	@Pattern(regexp = "^(?=.*[a-z0-9])[a-z0-9]{5,16}$", message = "비밀번호는 영문 5자~16자로 작성해야합니다.")
	private String password;

	@NotBlank(message = "이름은" + input_message)
	private String name;
	
	@NotBlank(message = "생년월일은" + input_message)
	private String birth;
	
	@NotBlank(message = "성별은" + choice_message)
	private String gender;
	
	@NotBlank(message = "이메일은" + input_message)
	@Email(message = "올바른 이메일 형식이 아닙니다.") //@Email Null허용
	private String email;
	
	//@NotBlank(message = "연락처는" + input_message)
	@Pattern(regexp = "^01(?:0|1|[0-9])-(?:\\d{3}|\\d{4})-\\d{4}$", message = "올바른 연락처의 형태가 아닙니다.")
	private String phone;
	
	private String status;
	private String userRole;
	private String udate;
	
	@NotBlank(message = "주소는" + input_message)
	private String address;
	@NotBlank(message = "상세주소는" + input_message)
	private String addressSub;
	private String image;
	
	
	/*=======================================*/
	//비밀번호 변경 요청 시 확인을 위한 변수 생성
	private String passwordNew;
	
	public String getPasswordNew() {
		return passwordNew;
	}

	public void setPasswordNew(String passwordNew) {
		this.passwordNew = passwordNew;
	}


	/*=======================================*/
	//Users 테이블에 존재하지않고 이미지 업로드를 위한 변수 생성
	// upload : 이미지를 받아줄 수 있는 변수, File로 넣을 수 있도록 type을 MultipartFile 으로 설정
	private MultipartFile upload;
	
	//getUpload()를 호출하면 MultipartFile객체 return
	//정보가 upload에 객체형태로 들어감 // upload : 파일을 객체로 만든 정보가 담김 this.upload = upload;
	public MultipartFile getUpload() {
		return upload;
	}
	
	//사용자가 업로드하려고하는 파일이 upload에 객체형태로 들어감
	public void setUpload(MultipartFile upload) {
		this.upload = upload;
		// fileName : 확장자를 포함한 파일이름을 담는 변수 
		String fileName = upload.getOriginalFilename(); 
		// image : fileName값을 image 변수에 넣기 
		this.image = fileName; 
		
	}//setUpload
	
	
	// upload2 : 변경될 파일이름을 담는 변수
	private String upload2;
	
	public String getUpload2() {
		return upload2;
	}
	public void setUpload2(String upload2) {
		this.upload2 = upload2;
	}
	
	
	// 포인트 적립 관련 추가
	private int point;
	
	public int getPoint() {
		return point;
	}
	
	public void setPoint(int point) {
		this.point = point;
	}
	

	/*=======================================*/
	

	//getter & setter
	public int getUserNum() {
		return userNum;
	}
	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getUdate() {
		return udate;
	}
	public void setUdate(String udate) {
		this.udate = udate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddressSub() {
		return addressSub;
	}
	public void setAddressSub(String addressSub) {
		this.addressSub = addressSub;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	

}
