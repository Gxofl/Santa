package com.spring.ex.crewcomments.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.crewcomments.model.CrewCommentsBean;
import com.spring.ex.crewcomments.model.CrewCommentsDao;

@Controller
public class CrewCommentsReplyController {
	
	//private final String command = "/reply.ccmt";
	private final String command = "/crewcomments/user/reply.ccmt";
	private String getPage ="";
	private String gotoPage = "redirect:/crewboard/user/detail.bdcr";
	
	@Autowired
	CrewCommentsDao ccmt_dao;
	
	//crewboardDetail.jsp에서 댓글 답글달기 클릭시 요청
	@RequestMapping(value = command, method=RequestMethod.POST)
	public ModelAndView doAction(CrewCommentsBean ccmt_bean, Principal principal) {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("num", ccmt_bean.getIdx()); // 게시글 정보 모델설정
		mav.addObject("pageNumber", ccmt_bean.getPageNumber()); // 페이지
		
		ccmt_bean.setWriter(principal.getName()); // 로그인 아이디 값 넣기
		
		int cnt = ccmt_dao.replyCrewComments(ccmt_bean);
		if(cnt != -1) {
			System.out.println("댓글 답글 달기 성공");
		}else {
			System.out.println("댓글 답글 달기 실패");
		}
		
		mav.setViewName(gotoPage);
		return mav;
	}
}
