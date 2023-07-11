package com.spring.ex.supportersapply.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.supportersapply.model.SupportersApplyBean;
import com.spring.ex.supportersapply.model.SupportersApplyDao;

@Controller
public class SupportersApplyListController {
	
	private final String command = "/supportersapply/user/list.sua";
	private String getPage = "myPage/supporters/mySupportersList"; 
	
	@Autowired
	SupportersApplyDao sadao;
	
	
	//마이페이지에서 내 서포터즈-전체를 클릭하면 이곳으로 와서 전체를 조회함
	@RequestMapping(command)
	public ModelAndView doAction1(Principal principal) {
		
		ModelAndView mav = new ModelAndView();		
		String loginId = principal.getName(); //내 아이디
		
		List<SupportersApplyBean> applybean = sadao.getMySupporters(loginId);
				
		mav.addObject("applybean", applybean);
		mav.setViewName(getPage);
		return mav;
	}
	
	
	
}
