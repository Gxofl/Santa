package com.spring.ex.myPage.users.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.users.model.UsersBean;
import com.spring.ex.users.model.UsersDao;

/*
로그인한 사용자가 회원정보관리-비밀번호변경 클릭 
-> 기존 비밀번호 입력부분과 일치하면, 변경하고자하는 비밀번호로 update
*/

@Controller
public class UsersUpdatePasswordController {

	private final String command = "/users/user/usersPasswordUpdate.us";
	private String getPage = "/myPage/users/usersUpdatePassword";
	
	@Autowired
	UsersDao udao;
	
	//마이페이지에서 회원정보관리-비밀번호변경 클릭했을 때, 아이디를 포함한 화면띄우기
	@RequestMapping(value = command, method = RequestMethod.GET)
	public ModelAndView usersUpdatePassword(Principal principal) {
		ModelAndView mav = new ModelAndView();
		String getUserId = principal.getName();
		mav.addObject("userId", getUserId);
		mav.setViewName(getPage);
		return mav;
	}//usersUpdatePassword - 사용자가 비밀번호 변경을 입력할 페이지
	
	
	
	//회원가입 signUp.jsp(userId) -> 아이디 중복체크 버튼 클릭 (ajax 요청)
	@RequestMapping(value=command, method = RequestMethod.POST)
	@ResponseBody
	public String usersUpdatePassword (
			Principal principal, 
			@RequestParam("input_password")		String input_password,
			@RequestParam("input_password_new")	String input_password_new	) {
		
		UsersBean usersBean = new UsersBean();
		String getUserId = principal.getName();
		usersBean.setUserId(getUserId);
		usersBean.setPassword(input_password);
		usersBean.setPasswordNew(input_password_new);
		int cnt = udao.usersUpdatePassword(usersBean);
		System.out.println("controller cnt : " + cnt);
		String result = "";
		if(cnt == 1) { 
			//입력한 비밀번호와 DB의 비밀번호가 동일 ->  1
			result =  "YES";
		}else {
			//입력한 비밀번호와 DB의 비밀번호가 동일X ->  0 
			result =  "NO";
		}
		System.out.println("result : " + result);
		return result;
	}//usersInfoCheck - 사용자가 입력한 값 DB와 비교
}//UsersUpdatePasswordController
