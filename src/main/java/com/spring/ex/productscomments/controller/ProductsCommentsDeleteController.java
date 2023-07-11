package com.spring.ex.productscomments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.productscomments.model.ProductsCommentsBean;
import com.spring.ex.productscomments.model.ProductsCommentsDao;


@Controller
public class ProductsCommentsDeleteController {
	private final String command = "/productscomments/user/delete.pcmt";
	private String gotoPage = "redirect:/products/user/detail.prd";
	
	@Autowired
	ProductsCommentsDao pcmt_dao;
	
	/*
	 productsDetailView.jsp에서 댓글 삭제버튼 클릭(num, pageNumber, idx) => /products/user/detail.prd 요청
	 */
	@RequestMapping(command)
	public ModelAndView doAction(@RequestParam("num") int num,
			@RequestParam("pageNumber") String pageNumber,
			@RequestParam("idx") int idx) {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("num", idx); // 게시글 num
		mav.addObject("pageNumber", pageNumber); // 페이지
		
		// 댓글의 num, deleteyn = 'Y' 담아가기
		ProductsCommentsBean pcmt_bean = new ProductsCommentsBean();
		pcmt_bean.setNum(num);
		pcmt_bean.setDeleteyn("Y");
		
		int cnt = pcmt_dao.deleteProductsComments(pcmt_bean);
		if(cnt != -1) {
			System.out.println("댓글 삭제 성공");
			mav.setViewName(gotoPage);
		}else {
			System.out.println("댓글 삭제 실패");
			mav.setViewName(gotoPage);
		}
		
		return mav;
	}
}
