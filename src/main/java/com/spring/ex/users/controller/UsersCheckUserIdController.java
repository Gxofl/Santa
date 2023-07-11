package com.spring.ex.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.users.model.UsersDao;

/* 아이디 중복여부 체크하는 컨트롤러 */
@Controller
public class UsersCheckUserIdController {

	private final String command = "/login/all/userId_check.us";

	@Autowired
	UsersDao udao;
	
	//회원가입 signUp.jsp(userId) -> 아이디 중복체크 버튼 클릭 (ajax 요청)
	@RequestMapping(value=command)
	@ResponseBody
	public String UserIdCheck(@RequestParam("input_UserId") String input_UserId) {
		int cnt = -1;
		cnt = udao.userIdCheck(input_UserId.trim());//공백제거한 값으로 전달
		System.out.println("UsersUserIdCheck cnt : " + cnt);
		String result = "";
		if(cnt == 1) { 
			// 이미 존재하는 아이디
			result =  "NO";
		}else {
			result =  "YES";
		}
		return result;
		
	}//UserIdCheck - DB에서 아이디 중복여부 확인
	
}//UsersCheckUserIdController
