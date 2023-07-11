package com.spring.ex.notice.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.notice.model.NoticeBean;
import com.spring.ex.notice.model.NoticeDao;

@Controller
public class NoticeDetailController {
	
	private final String command = "/notice/all/detail.no";
	private final String getPage = "/notice/noticeDetailView";
	
	@Autowired
	NoticeDao ndao;
	
	
	//1. noticeList.jsp(글제목 클릭) -> detail.no 요청 -> noticeDetailView.jsp로 이동
	//2. 수정하면 UpdateController을 통해 재요청함
	@RequestMapping(value=command, method=RequestMethod.GET)
	public ModelAndView detail(@RequestParam("num") int num, 
			@RequestParam("pageNumber") int pageNumber, Model model, Principal principal) {		
		
		int cnt = ndao.updateCount(num); //글 클릭하면 조회수 1 증가하도록
				
		NoticeBean notice = ndao.getNoticeByNum(num);
		
		ModelAndView mav = new ModelAndView();
		//String loginId = principal.getName();
		//mav.addObject("loginId", loginId);
		mav.addObject("notice", notice);
		mav.addObject("pageNumber", pageNumber);
		mav.setViewName(getPage);

		return mav;
	}
	
}
