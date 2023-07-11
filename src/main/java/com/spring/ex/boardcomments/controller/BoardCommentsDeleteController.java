package com.spring.ex.boardcomments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.boardcomments.model.BoardCommentsBean;
import com.spring.ex.boardcomments.model.BoardCommentsDao;


@Controller
public class BoardCommentsDeleteController {
	
	private final String command = "/boardcomments/user/delete.bcmt";
	private String gotoPage = "redirect:/board/user/detail.br";
	
	@Autowired
	BoardCommentsDao bcmt_dao;
	
	
	@RequestMapping(command)
	public ModelAndView doAction(@RequestParam("num") int num,
			@RequestParam("pageNumber") String pageNumber,
			@RequestParam("bnum") int bnum) {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("num", bnum); // 게시글 num
		mav.addObject("pageNumber", pageNumber); //페이지
		
		// 댓글의 num, deleteyn='Y' 담아가기
		BoardCommentsBean boardcomments = new BoardCommentsBean();
		boardcomments.setNum(num); //게시글 번호 담는것
		boardcomments.setIsdeleted("Y");
		
		int cnt = bcmt_dao.deleteBoardComments(boardcomments); //bean을 넘기고 삭제하기
		if(cnt != -1) {
			System.out.println("댓글 삭제 성공");
			mav.setViewName(gotoPage);
		}else {
			System.out.println("댓글 삭제 실패");
			mav.setViewName(gotoPage);
		}
		
		return mav;
	}//
	
	
}
