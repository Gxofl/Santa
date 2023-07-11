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
public class CrewCommentsUpdateController {
	private final String command = "/crewcomments/user/update.ccmt";
	private String gotoPage = "redirect:/crewboard/user/detail.bdcr";
	
	@Autowired
	CrewCommentsDao cmt_dao;
	
	/*
	 crewboardDetailView.jsp 댓글 수정버튼(num, pageNumber, idx, content) => /crewboard/user/detail.bdcr 요청
	 */
	@RequestMapping(value=command, method=RequestMethod.POST)
	public ModelAndView doAction(CrewCommentsBean ccmt_bean,
			@RequestParam("pageNumber") String pageNumber) {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("num", ccmt_bean.getIdx()); // 게시글 num
		mav.addObject("pageNumber", pageNumber); // 페이지
		
		int cnt = cmt_dao.updateCrewComments(ccmt_bean);
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
