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
로그인한 사용자가 회원정보관리-회원탈퇴 클릭 -> 비밀번호로 사용자 확인
*/

@Controller
public class UsersWithdrawalCheckController {
	
	private final String command = "/users/user/usersWithdrawalCheck.us";
	private String getPage = "/myPage/users/usersWithdrawal";
	
	@Autowired
	UsersDao udao;
	
	//마이페이지에서 회원정보관리-회원탈퇴 클릭했을 때, 아이디를 포함한 화면띄우기
	@RequestMapping(value = command, method = RequestMethod.GET)
	public ModelAndView usersWithdrawal(Principal principal) {
		ModelAndView mav = new ModelAndView();
		String getUserId = principal.getName();
		mav.addObject("userId", getUserId);
		mav.setViewName(getPage);
		return mav;
	}//usersWithdrawal
	
	//usersWithdrawal에서 넘어온 사용자입력값 확인
	@RequestMapping(value = command, method = RequestMethod.POST)
	@ResponseBody
	public String usersWithdrawalCheck (Principal principal, 
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
		
	
}//UsersWithdrawalController
