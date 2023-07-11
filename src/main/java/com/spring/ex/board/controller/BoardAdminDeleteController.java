package com.spring.ex.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.board.model.BoardDao;

@Controller
public class BoardAdminDeleteController {
	
	private final String command = "/board/admin/deleteadmin.br";
	private String gotoPage = "redirect:/board/admin/list.br";
	
	@Autowired
	BoardDao bdao;
	
	@RequestMapping(command)
	public ModelAndView deleteboard(@RequestParam("num") String num) {
		
		String[] board_num = num.split(",");
		int cnt = bdao.deleteBoardByAdmin(board_num);
		if(cnt != -1) {
			System.out.println("관리자 권한으로 자유 게시글 삭제 성공");
		}else {
			System.out.println("관리자 권한으로 자유 게시글 삭제 실패");
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName(gotoPage);
		return mav;
		
	}
	
	
	
}
