package com.spring.ex.myPage.products.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.products.model.ProductsBean;
import com.spring.ex.products.model.ProductsDao;
import com.spring.ex.utility.Paging;

@Controller
public class ProductsMyPageController {
	private final String command ="/products/user/myPage.prd";
	private String getPage = "myPage/products/myProducts";
	
	@Autowired
	ProductsDao prd_dao;
	
	/*
	 마이페이지에서 작성한 게시글(거래게시판) 클릭시 => myProducts.jsp
	 */
	@RequestMapping(command)
	public ModelAndView doAction(Principal principal, HttpServletRequest request,
			@RequestParam(value="whatColumn", required=false) String whatColumn,
			@RequestParam(value="keyword", required=false) String keyword,
			@RequestParam(value="pageNumber", required=false) String pageNumber) {
		
		ModelAndView mav = new ModelAndView();
		String loginId = principal.getName();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("whatColumn", whatColumn);
		map.put("keyword", "%"+keyword+"%");
		map.put("loginId", loginId);
		
		int totalCount = prd_dao.getProductsTotalCount_Mypage(map);
		String url = request.getContextPath()+command;
		
		Paging pageInfo = new Paging(pageNumber, "5", totalCount, url, whatColumn, keyword, null);
		
		List<ProductsBean> mylist = prd_dao.getAllProductsById(map);
		mav.addObject("pageInfo", pageInfo);
		mav.addObject("mylist", mylist);
		mav.setViewName(getPage);
		return mav;
	}
}
