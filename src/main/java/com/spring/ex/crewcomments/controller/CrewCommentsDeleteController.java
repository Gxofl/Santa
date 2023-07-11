package com.spring.ex.crewcomments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.crewcomments.model.CrewCommentsBean;
import com.spring.ex.crewcomments.model.CrewCommentsDao;

@Controller
public class CrewCommentsDeleteController {
	private final String command = "/crewcomments/user/delete.ccmt";
	private String gotoPage = "redirect:/crewboard/user/detail.bdcr";
	
	@Autowired
	CrewCommentsDao cmt_dao;
	
	/*
	 crewboardDetailView.jsp에서 댓글 삭제버튼 클릭(num, pageNumber, idx) => /crewboard/user/detail.bdcr 요청
	 */
	@RequestMapping(command)
	public ModelAndView doAction(@RequestParam("num") int num,
			@RequestParam("pageNumber") String pageNumber,
			@RequestParam("idx") int idx) {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("num", idx); // 게시글 num
		mav.addObject("pageNumber", pageNumber); // 페이지
		
		// 댓글의 num, deleteyn = 'Y' 담아가기
		CrewCommentsBean cmt_bean = new CrewCommentsBean();
		cmt_bean.setNum(num);
		cmt_bean.setDeleteyn("Y");
		
		int cnt = cmt_dao.deleteCrewComments(cmt_bean);
		if(cnt != -1) {
			System.out.println("댓글 삭제 성공");
			mav.setViewName(gotoPage);
		}else {
			System.out.println("댓글 삭제 실패");
			mav.setViewName(gotoPage);
		}
		
		return mav;
	}
}
