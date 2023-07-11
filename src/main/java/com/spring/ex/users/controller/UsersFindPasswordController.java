package com.spring.ex.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.users.model.UsersBean;
import com.spring.ex.users.model.UsersDao;

/* 비밀번호 찾기 컨트롤러 */
@Controller
public class UsersFindPasswordController {
	
	private final String command = "/login/all/findPassword.lg";
	private String getPage = "/users/findPassword";
	private String gotoPage = "/login/loginPage";
	
	@Autowired
	UsersDao udao;
	
	//로그인페이지(loginPage.jsp) or 아이디 찾기 페이지(findUserId.jsp) -> 아이디 찾기 클릭
	@RequestMapping(value = command, method = RequestMethod.GET)
	public ModelAndView findPassword() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(getPage); // /users/findPassword
		return mav;
	}//findPassword - 화면 띄우기
	
	
	//비밀번호찾기 findPassword.jsp(userId,name,email,phone) -> submit 클릭
	@RequestMapping(value = command, method = RequestMethod.POST)
	@ResponseBody
	public String findPassword(
			@RequestParam(value = "input_userId", required = true) String input_userId,
			@RequestParam(value = "input_name"	, required = true) String input_name,
			@RequestParam(value = "input_email"	, required = true) String input_email,
			@RequestParam(value = "input_phone"	, required = true) String input_phone){
		
		UsersBean usersBean = new UsersBean();
		usersBean.setUserId(input_userId);
		usersBean.setName(input_name);
		usersBean.setEmail(input_email);
		usersBean.setPhone(input_phone);
		 
		String findPassword_result = udao.findPassword(usersBean);
		System.out.println("findPassword_result : " + findPassword_result);
		if(findPassword_result != "null" || ! findPassword_result.equals("null")) { 
			return findPassword_result;
		}else {
			return "NO";
		}
		
	}//findPassword - submit 클릭
	
}//UsersFindPasswordController
