package com.spring.ex.notice.controller;

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

import com.spring.ex.notice.model.NoticeBean;
import com.spring.ex.notice.model.NoticeDao;

@Controller
public class NoticeUpdateController {

	private final String command = "/notice/admin/update.no";
	private final String getPage = "/notice/noticeUpdateForm";
	private final String gotoPage = "redirect:/notice/all/detail.no";

	@Autowired
	NoticeDao ndao;


	//noticeDetailView.jsp(수정 버튼 클릭) -> update.no (get방식)요청 -> noticeUpdateForm.jsp로 이동
	@RequestMapping(value=command, method=RequestMethod.GET)
	public String update(@RequestParam("num") int num,
			@RequestParam("pageNumber") int pageNumber, Model model) {

		System.out.println("수정 num: "+num);
		System.out.println("수정 pageNumber: "+pageNumber);

		NoticeBean notice = ndao.getNoticeByNum(num);
		model.addAttribute("notice", notice);
		model.addAttribute("pageNumber",pageNumber);

		return getPage;
	}
	
	
	//noticeUpdateForm.jsp(수정 버튼 클릭) -> detail.no (post방식)요청 -> 수정 성공 -> detail.no 재요청
	@RequestMapping(value=command, method = RequestMethod.POST)
	public ModelAndView update(@ModelAttribute("notice") @Valid NoticeBean notice, BindingResult result,
			@RequestParam("pageNumber") String pageNumber,
			@RequestParam("num") String num) {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageNumber", pageNumber);
		mav.addObject("num", num); // 게시글 num 넘기기
		
		System.out.println("result.hasErrors(): "+result.hasErrors());
		if(result.hasErrors()) { //에러 있음
			mav.setViewName(getPage);
		}
		else {//에러 없음
			int cnt = ndao.updateNotice(notice);
			System.out.println("수정 cnt: "+ cnt);
			
			if(cnt != -1) { //수정 성공
				mav.setViewName(gotoPage);
			}else {//수정 실패
				mav.setViewName(getPage);
			}			
		}		
		return mav;
	}
	
	
	
}
