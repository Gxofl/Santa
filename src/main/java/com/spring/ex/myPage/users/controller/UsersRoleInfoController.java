package com.spring.ex.myPage.users.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//마이페이지에서 등급안내버튼 클릭 시 팝업창으로 getPage_info 띄우기
@Controller
public class UsersRoleInfoController {
	
	private final String command_info = "/users/user/userRole_info.us";
	private String getPage_info = "myPage/users/userRole_info";
	
	
	@RequestMapping(value = command_info, method = RequestMethod.GET)
	public ModelAndView userRoleInfo() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(getPage_info);
		return mav;
	}//userRoleInfo - 등급안내 팝업창띄우기
}//UsersRoleInfoController
