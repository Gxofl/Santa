package com.spring.ex.admin.supporters.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.supporters.model.SupportersDao;



@Controller
public class SupportersAdminDeleteController {
	
	private final String command = "/supporters/admin/deleteadmin.su";
	private String gotoPage = "redirect:/supporters/admin/adminlist.su";
	
	@Autowired
	SupportersDao sdao;
	
	@RequestMapping(command)
	public ModelAndView deleteboard(@RequestParam("num") String num) {
		
		String[] supporters_num = num.split(",");
		int cnt = sdao.deleteSupportersByAdmin(supporters_num);
		if(cnt != -1) {
			System.out.println("관리자 권한으로 서포터즈 모집글 삭제 성공");
		}else {
			System.out.println("관리자 권한으로 서포터즈 모집글 삭제 실패");
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName(gotoPage);
		return mav;
		
	}
	
	
	
}
