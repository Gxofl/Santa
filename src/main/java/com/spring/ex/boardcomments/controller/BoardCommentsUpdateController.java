package com.spring.ex.boardcomments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.boardcomments.model.BoardCommentsBean;
import com.spring.ex.boardcomments.model.BoardCommentsDao;

@Controller
public class BoardCommentsUpdateController {
	
	private final String command = "/boardcomments/user/update.bcmt";
	private String gotoPage = "redirect:/board/user/detail.br";
	
	
	@Autowired
	BoardCommentsDao bcmt_dao;
	
	
	//crewboardDetailView.jsp에서 댓글의 수정 버튼(num, pageNumber, bnum, content) 4개를 갖고 요청함
	@RequestMapping(value=command, method=RequestMethod.POST)
	public ModelAndView doAction(BoardCommentsBean boardcomments,
			@RequestParam("pageNumber") String pageNumber) {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("num", boardcomments.getBnum()); // 게시글 num
		mav.addObject("pageNumber", pageNumber);
		
		int cnt = bcmt_dao.updateBoardComments(boardcomments);
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
