package com.spring.ex.notice.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.notice.model.NoticeBean;
import com.spring.ex.notice.model.NoticeDao;

@Controller
public class NoticeInsertController {

	private final String command = "/notice/admin/insert.no";
	private final String getPage = "/notice/noticeInsertForm";
	private final String gotoPage = "redirect:/notice/all/list.no";

	@Autowired
	NoticeDao ndao;

	
	//noticeList.jsp(목록에서 글쓰기 버튼 클릭) -> insert.no 요청(get방식) -> noticeInsertForm.jsp로 이동
	@RequestMapping(value=command, method=RequestMethod.GET)
	public ModelAndView insert(Principal principal, @RequestParam("pageNumber") int pageNumber) {
		
		ModelAndView mav = new ModelAndView();
		
		String userId = principal.getName(); 		
		mav.addObject("userId", userId);
		mav.addObject("pageNumber", pageNumber);
		mav.setViewName(getPage);
		return mav;
		
	}


	//noticeInsertForm.jsp(글쓰기 버튼 클릭) -> detail.no 요청(post방식) -> 삽입성공시 list.no 요청
	@RequestMapping(value=command, method=RequestMethod.POST)
	public ModelAndView insert(@ModelAttribute("notice") @Valid NoticeBean notice, BindingResult result,
			Principal principal) {

		ModelAndView mav = new ModelAndView();
		notice.setUserid(principal.getName());
		String userId = principal.getName();	
		mav.addObject("userId", userId);
		//mav.addObject("pageNumber", pageNumber);

		System.out.println("result.hasErrors(): "+result.hasErrors());
		if(result.hasErrors()) { //에러 있음
			mav.setViewName(getPage);			
		}
		else { //에러 없음
			int cnt = ndao.insertNotice(notice);

			if(cnt != -1) {//insert 성공
				mav.setViewName(gotoPage);
			}else {//insert 실패
				mav.setViewName(getPage);	
			}
		}		
		return mav;
	}//

}
