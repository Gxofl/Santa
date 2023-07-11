package com.spring.ex.login.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.users.model.UsersBean;

/*
security-context.xml
- login-page="/login/loginPage.lg" 
- login-processing-url="/login.lg"
- authentication-failure-url="/login/loginPage.lg?err=true"

로그인 페이지 URL 띄우기 -> command(/login/loginPage.lg)
로그인 성공- > /main.lg
권한이 없는 페이지로 가려고할 때 ->commandDenied ("/login/accessDenied.lg")

login-processing-url에 설정된 URL(/login.lg)로 전송된 로그인 요청은 Spring Security에서 처리함, requestMapping이 필요없는 것임.

로그인이 성공하면 /main.lg로 자동으로 이동
 */
@Controller
public class LoginController {
	
	private final String command ="/login/all/loginPage.lg";
	private final String commandDenied ="/login/accessDenied.lg";
	private String gotoPage = "/login/loginPage";
	private String getDeniedPage = "/login/accessDenied";
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	//로그인 페이지 URL 띄우기 -> command(/login/loginPage.lg)
	@RequestMapping(value=command)
	public ModelAndView loginPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(gotoPage);// /login/loginPage
		return mav; 
		
	}//loginPage
	
	
	//권한이 없는 페이지로 가려고할 때
	@RequestMapping(value=commandDenied)
	public ModelAndView accessDeniedPage() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(getDeniedPage);// /login/accessDenied
		return mav; 
		
	}//accessDeniedPage
	
}//LoginController