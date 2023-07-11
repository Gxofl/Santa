package com.spring.ex.supporters.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.supporters.model.SupportersBean;
import com.spring.ex.supporters.model.SupportersDao;

@Controller
public class SupportersInsertController {
	
	private final String command = "/supporters/admin/insert.su";
	private String getPage = "/supporters/supportersInsertForm";
	private String gotoPage = "redirect:/supporters/all/list.su";
	
	@Autowired
	SupportersDao sdao;
	
	
	@RequestMapping(value=command, method=RequestMethod.GET) //폼요청
	public ModelAndView doAction(
			@RequestParam(value="pageNumber",required = false) String pageNumber, 
			Principal principal) {
		
		ModelAndView mav = new ModelAndView();
		String userId = principal.getName(); 
		
		mav.addObject("userId", userId);
		mav.addObject("pageNumber", pageNumber);
		mav.setViewName(getPage);
		return mav;
	}
	
	
	//supportersInsertForm.jsp(글쓰기 버튼) -> detail.su 요청(post방식) -> 삽입성공시 supportersList.jsp로 이동
	@RequestMapping(value=command, method = RequestMethod.POST)
	public ModelAndView insert(@ModelAttribute("supporters") @Valid SupportersBean supporters,
			BindingResult result, HttpServletRequest request,
			Principal principal) {
		
		ModelAndView mav = new ModelAndView();
		supporters.setWriter(principal.getName());
		
		System.out.println("result.hasErrors(): "+result.hasErrors()); 
		if(result.hasErrors()) { //에러 있음
			mav.setViewName(getPage);
		}
		else { //에러 없음
			int cnt = sdao.insertSupporters(supporters);
			System.out.println("cnt: "+cnt);
			
			if(cnt != -1) {//insert 성공
				mav.setViewName(gotoPage);
			}
			else {//insert 실패
				mav.setViewName(getPage);	
			}			
		}
		return mav;		
	}//
	
	
	
}
