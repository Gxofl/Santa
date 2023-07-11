package com.spring.ex.products.model;

import java.sql.Date;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import com.spring.ex.heart.products.model.ProductsHeartBean;

public class ProductsBean {
	private int productsnum;
	
	@NotBlank(message = "구분을 선택하세요")
	private String kind;
	
	@NotBlank(message = "상품명을 입력하세요")
	private String name;
	
	@NotBlank(message = "상품설명을 입력하세요")
	private String info;
	
	@NotBlank(message = "가격을 입력하세요")
	private String price;
	
	@NotEmpty(message = "이미지는 한 개 이상 업로드 해야합니다.")
	private String image;
	private String seller;
	private Date inputdate;
	private int state;
	private int readcount;
	
	// 마이페이지에 승인결제여부, 구매자(products DB 컬럼엔 없음)
	private int paystate;
	private String buyer;
	
	/* ProductsHeart 매핑 */
    private List<ProductsHeartBean> productsHeartList; 
    
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	public int getPaystate() {
		return paystate;
	}
	public void setPaystate(int paystate) {
		this.paystate = paystate;
	}

	//crewboardList에 댓글개수 출력하기 위해서
	private int comments;
	
	public int getComments() {
		return comments;
	}
	public void setComments(int comments) {
		this.comments = comments;
	}
	
	//////////
	
	private MultipartFile upload;
	
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
			
//			// insert폼 업로드 한거 upload3에 담아놓기
//			setUpload3(upload);
//			System.out.println("upload3에 upload담김");
		}
	}
	
	// insert 유효성 검사시 원래 업로드했던 객체
	private MultipartFile upload3;
	
	public MultipartFile getUpload3() {
		return upload3;
	}
	
	public void setUpload3(MultipartFile upload3) {
		this.upload3 = upload3;
		
		String fileName = upload3.getOriginalFilename();
		this.image = fileName;
		this.upload2 = image;
		System.out.println();
	}
	
	private String upload2; // 수정폼을 띄울때 기존이미지를 담을 변수
	
	public String getUpload2() {
		return upload2;
	}
	public void setUpload2(String upload2) {
		this.upload2 = upload2;
	}
	/////////
	
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public int getProductsnum() {
		return productsnum;
	}
	public void setProductsnum(int productsnum) {
		this.productsnum = productsnum;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	public Date getInputdate() {
		return inputdate;
	}
	public void setInputdate(Date inputdate) {
		this.inputdate = inputdate;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	/* ProductsHeart 매핑 */
	public List<ProductsHeartBean> getProductsHeartList() {
		return productsHeartList;
	}
	public void setProductsHeartList(List<ProductsHeartBean> productsHeartList) {
		this.productsHeartList = productsHeartList;
	}
	
	
}
