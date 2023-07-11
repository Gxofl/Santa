package com.spring.ex.boardcomments.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.boardcomments.model.BoardCommentsBean;
import com.spring.ex.boardcomments.model.BoardCommentsDao;


@Controller
public class BoardCommentsReplyController {
	
	private final String command = "/boardcomments/user/reply.bcmt";
	private String gotoPage = "redirect:/board/user/detail.br";
	
	@Autowired
	BoardCommentsDao bcmt_dao;
	
	
	//boardDetailView.jsp에서 댓글의 답글달기를 클릭시 요청
	@RequestMapping(value = command, method=RequestMethod.POST)
	public ModelAndView doAction(BoardCommentsBean boardcomments, Principal principal) {

		ModelAndView mav = new ModelAndView();
		mav.addObject("num", boardcomments.getBnum()); 
		//댓글 번호 num으로 모델설정해서 보냄 - 왜냐면 BoardDetailController에서 num으로 받으므로
		mav.addObject("pageNumber", boardcomments.getPageNumber()); // 페이지

		boardcomments.setUserid(principal.getName()); //로그인 아이디 값 넣기

		int cnt = bcmt_dao.replyBoardComments(boardcomments);
		if(cnt != -1) {
			System.out.println("대댓글 달기 성공");
		}else {
			System.out.println("대댓글 달기 실패");
		}

		mav.setViewName(gotoPage);
		return mav;
	}
	
	
}
