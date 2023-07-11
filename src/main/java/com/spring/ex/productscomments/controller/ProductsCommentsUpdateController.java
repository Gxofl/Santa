package com.spring.ex.productscomments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.productscomments.model.ProductsCommentsBean;
import com.spring.ex.productscomments.model.ProductsCommentsDao;


@Controller
public class ProductsCommentsUpdateController {
	private final String command = "/productscomments/user/update.pcmt";
	private String gotoPage = "redirect:/products/user/detail.prd";
	
	@Autowired
	ProductsCommentsDao pcmt_dao;
	
	/*
	 productsDetailView.jsp 댓글 수정버튼(num, pageNumber, idx, content) => /products/user/detail.prd 요청
	 */
	@RequestMapping(value=command, method=RequestMethod.POST)
	public ModelAndView doAction(ProductsCommentsBean pcmt_bean,
			@RequestParam("pageNumber") String pageNumber) {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("num", pcmt_bean.getIdx()); // 게시글 num
		mav.addObject("pageNumber", pageNumber); // 페이지
		
		int cnt = pcmt_dao.updateProductsComments(pcmt_bean);
		if(cnt != -1) {
			System.out.println("댓글 수정 성공");
			mav.setViewName(gotoPage);
		}else {
			System.out.println("댓글 수정 실패");
			mav.setViewName(gotoPage);
		}
		
		return mav;
	}
}
