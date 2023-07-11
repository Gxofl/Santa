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
 로그인한 사용자가 마이페이지페이지에서 회원정보관리-회원정보수정 클릭했을 때, 사용자 정보 노출 전 비밀번호를 먼저 확인하는 컨트롤러 
 */
@Controller
public class UsersInfoCheckController {
	
	private final String command = "/users/user/usersInfoCheck.us";
	private String getPage = "myPage/users/usersInfoCheck";
	
	@Autowired
	UsersDao udao;
	
	//마이페이지에서 회원정보관리-회원정보수정 클릭했을 때, usersInfoCheck로 비밀번호 확인
	@RequestMapping(value = command, method = RequestMethod.GET)
	public ModelAndView usersInfoCheck(Principal principal) {
		ModelAndView mav = new ModelAndView();
		String getUserId = principal.getName();
		mav.addObject("userId", getUserId);
		mav.setViewName(getPage);
		return mav;
	}//usersInfoCheck - 사용자가 비밀번호를 입력할 페이지
	
	//usersInfoCheck에서 넘어온 사용자입력값 확인
	@RequestMapping(value = command, method = RequestMethod.POST)
	@ResponseBody
	public String usersInfoCheck(Principal principal, 
			@RequestParam("input_password") String input_password) {
		UsersBean usersBean = new UsersBean();
		String getUserId = principal.getName();
		usersBean.setUserId(getUserId);
		usersBean.setPassword(input_password);
		
		int cnt = udao.getUsersByPassword(usersBean);
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
	
}//UsersPasswordCheck
