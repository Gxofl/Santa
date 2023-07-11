package com.spring.ex.crewcomments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.crewcomments.model.CrewCommentsBean;
import com.spring.ex.crewcomments.model.CrewCommentsDao;

@Controller
public class CrewCommentsInsertController {
	
	//private final String command = "/insert.ccmt";
	private final String command = "/crewcomments/user/insert.ccmt";
	private String gotoPage = "redirect:/crewboard/user/detail.bdcr";
	
	@Autowired
	CrewCommentsDao ccmt_dao;
	
	//crewboardDetailView.jsp(글 상세보기) 댓글등록 클릭시 요청
	@RequestMapping(value=command, method=RequestMethod.POST)
	public ModelAndView doAction(CrewCommentsBean cmtb, 
			@RequestParam("pageNumber") String pageNumber) {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("num", cmtb.getIdx()); // 원글 번호
		mav.addObject("pageNumber", pageNumber);
		
		System.out.println("크루댓글 insert 컨트롤러 pageNumber : "+pageNumber);
		
		int cnt = -1;
		cnt = ccmt_dao.insertCrewComments(cmtb);
		
		if(cnt != -1) { //댓글등록 성공
			mav.setViewName(gotoPage);
		}else {//댓글등록 실패
			mav.setViewName(gotoPage);
		}
		return mav;
	}
}
