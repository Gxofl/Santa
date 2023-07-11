package com.spring.ex.supporters.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.supporters.model.SupportersBean;
import com.spring.ex.supporters.model.SupportersDao;

@Controller
public class SupportersDeleteController {
	
	private final String command = "/supporters/admin/delete.su";
	private final String gotoPage = "redirect:/supporters/all/list.su";
	
	@Autowired
	SupportersDao sdao;
	
	
	@RequestMapping(command) 
	public ModelAndView delete(@RequestParam("num") int num, Model model,
			@RequestParam("pageNumber") int pageNumber) {
		
		ModelAndView mav = new ModelAndView();
		System.out.println("삭제할 서포터즈 num: "+num);		
		
		int cnt = sdao.deleteSupporters(num); //서포터즈 삭제
				
		if(cnt != -1) {
			sdao.deleteSupportersHistory(num); //state=1로 바꿈(삭제된 서폿은 마이페이지에서 조회 안되도록)
			System.out.println("서포터즈 삭제 성공");					
		}else {
			System.out.println("서포터즈 삭제 실패");
		}			
		mav.addObject("pageNumber", pageNumber);		
		mav.setViewName(gotoPage);
		return mav;
	}//
	
	
}
