package com.spring.ex.admin.crewboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.crewboard.model.CrewBoardDao;

@Controller
public class CrewBoardDeleteAdminController {
	private final String command = "/crewboard/admin/delete.bdcr";
	private String gotoPage = "redirect:/crewboard/admin/list.bdcr";
	
	@Autowired
	CrewBoardDao cb_dao;
	
	//관리자페이지에서 크루모집글 선택삭제 => /crewboard/admin/list.bdcr 요청
	@RequestMapping(command)
	public ModelAndView doAction(@RequestParam("num") String num) {
		ModelAndView mav = new ModelAndView();
		
		String[] crewboard_num = num.split(",");
		int cnt = cb_dao.deleteCrewboardByAdmin(crewboard_num);
		if(cnt != -1) {
			System.out.println("관리자 권한으로 크루게시글 삭제성공");
		}else {
			System.out.println("관리자 권한으로 크루게시글 삭제실패");
		}
		
		mav.setViewName(gotoPage);
		return mav;
	}
}
