package com.spring.ex.admin.products.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.products.model.ProductsDao;

@Controller
public class ProductsDeleteAdminController {
	private final String command = "/products/admin/delete.prd";
	private String gotoPage = "redirect:/products/admin/list.prd";
	
	@Autowired
	ProductsDao prd_dao;
	
	@RequestMapping(command)
	public ModelAndView doAction(@RequestParam("num") String num) {
		
		String[] products_num = num.split(",");
		int cnt = prd_dao.deleteProductsByAdmin(products_num);
		if(cnt != -1) {
			System.out.println("관리자 권한으로 거래게시판 글 삭제 성공");
		}else {
			System.out.println("관리자 권한으로 거래게시판 글 삭제 실패");
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName(gotoPage);
		return mav;
	}
}
