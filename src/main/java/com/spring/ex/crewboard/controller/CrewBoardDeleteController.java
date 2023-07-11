package com.spring.ex.crewboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.crewboard.model.CrewBoardDao;

@Controller
public class CrewBoardDeleteController {
	private final String command = "/crewboard/user/delete.bdcr";
	private String gotoPage = "redirect:/crewboard/all/list.bdcr";
	
	@Autowired
	CrewBoardDao cb_dao;
	
	/*
	 crewboardDetailView.jsp 삭제버튼 클릭(num, pageNumber) => /crewboard/all/list.bdcr 요청
	 */
	@RequestMapping(value=command, method=RequestMethod.GET)
	public ModelAndView doAction(@RequestParam("num") int crewboardnum,
			@RequestParam("pageNumber") String pageNumber) {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageNumber", pageNumber); // 페이지 설정
		mav.addObject("num", crewboardnum); // 게시글 num 설정
		
		int cnt = -1;
		cnt = cb_dao.deleteCrewboardByNum(crewboardnum); 
		if(cnt != -1) {
			System.out.println("크루보드 게시글 삭제 성공");
		}else {
			System.out.println("크루보드 게시글 삭제 실패");
			gotoPage = "redirect:/crewboard/user/detail.bdcr"; // 삭제 실패시 다시 상세보기로 돌아가야함
		}
		mav.setViewName(gotoPage);
		return mav;
	}
}
