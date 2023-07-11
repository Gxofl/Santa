package com.spring.ex.productscomments.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.productscomments.model.ProductsCommentsBean;
import com.spring.ex.productscomments.model.ProductsCommentsDao;

@Controller
public class ProductsCommentsReplyController {
		//private final String command = "/reply.pcmt";
		private final String command = "/productscomments/user/reply.pcmt";
		private String getPage ="";
		private String gotoPage = "redirect:/products/user/detail.prd";
		
		@Autowired
		ProductsCommentsDao pcmt_dao;
		
		//crewboardDetail.jsp에서 댓글 답글달기 클릭시 요청
		@RequestMapping(value = command, method=RequestMethod.POST)
		public ModelAndView doAction(ProductsCommentsBean pcmt_bean, Principal principal) {
			
			ModelAndView mav = new ModelAndView();
			mav.addObject("num", pcmt_bean.getIdx()); // 게시글 정보 모델설정
			mav.addObject("pageNumber", pcmt_bean.getPageNumber()); // 페이지
			
			pcmt_bean.setWriter(principal.getName()); // 로그인 아이디 값 넣기
			
			int cnt = pcmt_dao.replyProductsComments(pcmt_bean);
			if(cnt != -1) {
				System.out.println("댓글 답글 달기 성공");
			}else {
				System.out.println("댓글 답글 달기 실패");
			}
			
			mav.setViewName(gotoPage);
			return mav;
		}
}
