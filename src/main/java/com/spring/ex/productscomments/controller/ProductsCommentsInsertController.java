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
public class ProductsCommentsInsertController {
	//private final String command = "/insert.pcmt";
	private final String command = "/productscomments/user/insert.pcmt";
	private String gotoPage = "redirect:/products/user/detail.prd";

	@Autowired
	ProductsCommentsDao pcmt_dao;

	//crewboardDetailView.jsp(글 상세보기) 댓글등록 클릭시 요청
	@RequestMapping(value=command, method=RequestMethod.POST)
	public ModelAndView doAction(ProductsCommentsBean pcmt_bean,
			@RequestParam("pageNumber") String pageNumber) {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("num", pcmt_bean.getIdx()); // 원글 번호
		mav.addObject("pageNumber", pageNumber); // 페이지

		int cnt = -1;
		cnt = pcmt_dao.insertProductsComments(pcmt_bean);

		if(cnt != -1) { //댓글등록 성공
			mav.setViewName(gotoPage);
		}else {//댓글등록 실패
			mav.setViewName(gotoPage);
		}
		return mav;
	}
}
