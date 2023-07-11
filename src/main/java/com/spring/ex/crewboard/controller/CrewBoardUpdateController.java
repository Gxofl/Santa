package com.spring.ex.crewboard.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.crewboard.model.CrewBoardBean;
import com.spring.ex.crewboard.model.CrewBoardDao;
 
@Controller
public class CrewBoardUpdateController {
	private final String command = "/crewboard/user/update.bdcr";
	private String getPage = "/crewboard/crewboardUpdateForm";
	private String gotoPage = "redirect:/crewboard/user/detail.bdcr";
	
	@Autowired
	CrewBoardDao cb_dao;
	
	/*
	 crewboardDetailView.jsp 에서 수정버튼 클릭시 요청(num, pageNumber) => crewboardUpdateForm.jsp
	 */
	@RequestMapping(value=command, method=RequestMethod.GET)
	public ModelAndView doAction(@RequestParam("num") int crewboardnum,
			@RequestParam("pageNumber") String pageNumber) {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageNumber", pageNumber); // 페이지 설정
		
		CrewBoardBean cbb = cb_dao.getCrewboardByNum(crewboardnum);
		mav.addObject("cbb", cbb);
		mav.setViewName(getPage);
		
		return mav;
	}
	
	
	/*
	 crewboardUpdateForm.jsp 수정버튼 클릭시 요청 => /crewboard/user/detail.bdcr 요청
	 */
	@RequestMapping(value=command, method=RequestMethod.POST)
	public ModelAndView doAction(@ModelAttribute("cbb") @Valid CrewBoardBean cbb,
			BindingResult result,
			@RequestParam("pageNumber") String pageNumber,
			@RequestParam("crewboardnum") int num) { 
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageNumber", pageNumber); // 페이지 설정
		mav.addObject("num", num); // 게시글 num 설정
		
		if(result.hasErrors()) { // 에러발생
			mav.setViewName(getPage);
		}else {
			int cnt = -1;
			cnt = cb_dao.updateCrewboard(cbb); // DB 수정
			if(cnt != -1) {
				System.out.println("크루보드 수정 성공");
			}else {
				System.out.println("크루보드 수정 실패");
			}
			mav.setViewName(gotoPage);
		}
		return mav;
	}
}
