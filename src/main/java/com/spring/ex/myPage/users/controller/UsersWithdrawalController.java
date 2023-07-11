package com.spring.ex.myPage.users.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.users.model.UsersBean;
import com.spring.ex.users.model.UsersDao;

/*
로그인한 사용자가 회원정보관리-회원탈퇴 클릭 시 비밀번호 확인 후
-> status 값을 'Y' -> 'N'으로 변경
*/

@Controller
public class UsersWithdrawalController {
	
	private final String command = "/users/user/usersWithdrawal.us";
	
	@Autowired
	UsersDao udao;
	
	// 비밀번호 확인 후, alert에서 탈퇴하시겠습니까에 Yes눌렀을 때
	@RequestMapping(value = command)
	public ModelAndView usersWithdrawal(Principal principal, 
			HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		UsersBean usersBean = new UsersBean();
		String getUserId = principal.getName();
		usersBean.setUserId(getUserId);
		
		int cnt = udao.usersWithdrawal(getUserId);
		System.out.println("controller cnt : " + cnt);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(cnt == 1) { 
			//status Y -> N 수정 성공
			out.println("<script>alert('탈퇴되었습니다. 자동 로그아웃 됩니다.');location.href='/logout';</script>");
			
		}else {
			//status Y -> N 수정 실패
			out.println("<script>alert('탈퇴 오류가 발생했습니다.');location.href='/users/user/usersWithdrawalCheck.us';</script>");
		}
		out.flush();
		return mav;
	}//usersWithdrawal 
		
}//UsersWithdrawalController
