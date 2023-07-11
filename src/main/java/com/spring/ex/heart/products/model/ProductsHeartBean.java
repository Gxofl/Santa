package com.spring.ex.heart.products.model;

import com.spring.ex.products.model.ProductsBean;

public class ProductsHeartBean {
		
    private int heartId;
	private int productsNum;
	private String userId;
	private String heartDate;
	
	/* Products 매핑 */
    private ProductsBean products;

	//getter & setter
	public int getHeartId() {
		return heartId;
	}

	public void setHeartId(int heartId) {
		this.heartId = heartId;
	}

	public int getProductsNum() {
		return productsNum;
	}

	public void setProductsNum(int productsNum) {
		this.productsNum = productsNum;
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

	public ProductsBean getProducts() {
		return products;
	}

	public void setProducts(ProductsBean products) {
		this.products = products;
	} 
    
	
}//CrewBoardHeartBean
