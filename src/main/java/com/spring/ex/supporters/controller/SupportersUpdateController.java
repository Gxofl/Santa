package com.spring.ex.supporters.controller;

import java.text.SimpleDateFormat;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import com.spring.ex.supporters.model.SupportersBean;
import com.spring.ex.supporters.model.SupportersDao;

@Controller
public class SupportersUpdateController {
	
	private final String command = "/supporters/admin/update.su";
	private String getPage = "/supporters/supportersUpdateForm";
	private String gotoPage = "redirect:/supporters/user/detail.su"; //detail로 이동
	
	@Autowired
	SupportersDao sdao;
	
	
	//supportersDetailView.jsp(수정 버튼 클릭) -> update.su 요청(get방식) -> supportersUpdateForm.jsp로 이동
	@RequestMapping(value=command, method = RequestMethod.GET)
	public String update(@RequestParam("num") int num, Model model,
			@RequestParam("pageNumber") int pageNumber) {

		System.out.println("수정 서포터즈 num: "+num);
		

		SupportersBean supporters = sdao.getSupportersByNum(num);
		//System.out.println("수정 서포터즈 deadline: "+supporters.getDeadline()); //2023-06-24 00:00:00.0		
		//System.out.println("수정 서포터즈 startdate: "+supporters.getStartdate()); //2023-06-27 00:00:00.0
		
		model.addAttribute("supporters", supporters);
		model.addAttribute("pageNumber",pageNumber);

		return getPage;
	}
	
	
	//서포터즈 객체와 페이지번호를 받음
	//supportersUpdateForm.jsp(수정 버튼 클릭) -> detail.su 요청(post방식) -> 수정 성공하면 -> detail.su 재요청
	@RequestMapping(value=command, method = RequestMethod.POST)
	public ModelAndView update(@RequestParam(value="pageNumber",required = false) String pageNumber,
			@ModelAttribute("supporters") @Valid SupportersBean supporters, BindingResult result) {

		//System.out.println("수정 num: "+supporters.getNum());
		//System.out.println("수정 서포터즈명: "+supporters.getSupportersname());
		//System.out.println("수정 모집마감일(deadline): "+supporters.getDeadline());
		//System.out.println("수정 활동시작일(startdate): "+supporters.getStartdate());
		//System.out.println("수정 활동종료일(enddate): "+supporters.getEnddate());
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("num", supporters.getNum()); //번호도 보내줘야함!
		mav.addObject("pageNumber", pageNumber);

		System.out.println("result.hasErrors(): "+result.hasErrors());
		if(result.hasErrors()) { //에러 있음
			mav.setViewName(getPage);
		}
		else {//에러 없음
			int cnt = sdao.updateSupporter(supporters);
			System.out.println("수정 cnt: "+ cnt);

			if(cnt > 0) {  // DB 테이블에서 수정 성공
				mav.setViewName(gotoPage); //상세보기 페이지로
			}else { //수정 실패
				mav.setViewName(getPage); //수정폼으로
			}			
		}		
		return mav;	
	}//
	
	
}
