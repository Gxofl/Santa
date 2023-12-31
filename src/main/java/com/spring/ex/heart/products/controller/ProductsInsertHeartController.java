package com.spring.ex.heart.products.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.ex.heart.products.model.ProductsHeartBean;
import com.spring.ex.heart.products.model.ProductsHeartDao;


@Controller
public class ProductsInsertHeartController {
	
	private final String command = "/heart/user/products/insertHeart.ht";
	
	@Autowired
	ProductsHeartDao phdao;
	
	//상품게시판 상세보기 productsDetailView.jsp(input_userId,input_idx) -> 좋아요(empty_heart) 아이콘 클릭 (ajax 요청)
	//empty_heart -> heart
	@RequestMapping(value=command, method = RequestMethod.POST)
	@ResponseBody
	public String ProductInsertHeart(
			@RequestParam("input_userId") 	String 	input_userId,
			@RequestParam("input_num") 		int 	input_num	) {
		
		ProductsHeartBean phBean = new ProductsHeartBean();
		phBean.setProductsNum(input_num);
		phBean.setUserId(input_userId);
		int cnt = -1;
		String result = "";
		try {
			cnt = phdao.productsInsertHeart(phBean);
			System.out.println("productInsertHeart cnt : " + cnt);
			
			if(cnt == 1) { 
				result =  "success";
			}else {
				result =  "fail";
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("productInsertHeart result : " + result);
		return result;
		
	}//ProductInsertHeart
	
}//ProductsInsertHeartController
