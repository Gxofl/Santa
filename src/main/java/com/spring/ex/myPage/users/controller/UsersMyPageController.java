package com.spring.ex.myPage.users.controller;

import java.security.Principal;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.users.model.UsersBean;
import com.spring.ex.users.model.UsersDao;

@Controller
public class UsersMyPageController {
	
	private final String command = "/users/user/mypage.us";
	private String getPage = "myPage/users/myPage";
	
	@Autowired
	UsersDao udao;
	
	//nav에서 마이페이지 클릭 -> 마이페이지 홈(myPage.jsp)
	@RequestMapping(value = command, method = RequestMethod.GET)
	public ModelAndView myPage(Principal principal) {
		ModelAndView mav = new ModelAndView();
		String getUserId = principal.getName();
		UsersBean usersBean = udao.getUsersByUserId(getUserId);
		
		String[] genderArr = {"남자", "여자"};
		String getUserRole = usersBean.getUserRole();
		mav.addObject("getUserRole", getUserRole);
		mav.addObject("genderArr", genderArr);
		mav.addObject("usersBean", usersBean);
		mav.setViewName(getPage);
		return mav;
	}//myPage - 마이페이지 화면띄우기
	
}//UsersMyPageController
