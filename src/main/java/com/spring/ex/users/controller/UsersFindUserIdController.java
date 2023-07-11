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

/* 아이디 찾기 컨트롤러 */
@Controller
public class UsersFindUserIdController {
	
	private final String command = "/login/all/findUserId.lg";
	private String getPage = "/users/findUserId";
	private String gotoPage = "/login/loginPage";
	
	@Autowired
	UsersDao udao;
	
	// 로그인페이지(loginPage.jsp) or 비밀번호 찾기 페이지(findPassword.jsp) -> 아이디 찾기 클릭
	@RequestMapping(value = command, method = RequestMethod.GET)
	public ModelAndView findUserId() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(getPage); // /users/findUserId
		return mav;
	}//findUserId - 화면 띄우기
	
	// 아이디찾기 findUserId.jsp(name,email,phone) -> submit 클릭
	@RequestMapping(value = command, method = RequestMethod.POST)
	@ResponseBody
	public String findUserId(
			@RequestParam(value = "input_name"	, required = true) String input_name,
			@RequestParam(value = "input_email"	, required = true) String input_email,
			@RequestParam(value = "input_phone"	, required = true) String input_phone){
		
		UsersBean usersBean = new UsersBean();
		usersBean.setName(input_name);
		usersBean.setEmail(input_email);
		usersBean.setPhone(input_phone);
		 
		String findUserId_result = udao.findUserId(usersBean);
		System.out.println("findUserId_result : " + findUserId_result);
		if(findUserId_result != null || ! findUserId_result.equals("null")) { 
			return findUserId_result;
		}else {
			return "NO";
		}
	}//findUserId - submit 클릭
	
}//UsersFindUserIdController
