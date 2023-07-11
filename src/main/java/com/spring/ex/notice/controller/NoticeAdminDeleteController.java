package com.spring.ex.notice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.notice.model.NoticeDao;

@Controller
public class NoticeAdminDeleteController {
	
	private final String command = "/notice/admin/deleteadmin.no";
	private String gotoPage = "redirect:/notice/admin/list.no";
	
	@Autowired
	NoticeDao ndao;
	
	@RequestMapping(command)
	public ModelAndView deletenotice(@RequestParam("num") String num) {
		
		String[] notice_num = num.split(",");
		int cnt = ndao.deleteNoticeByAdmin(notice_num);
		if(cnt != -1) {
			System.out.println("관리자 권한으로 공지사항 게시글 삭제 성공");
		}else {
			System.out.println("관리자 권한으로 공지사항 게시글 삭제 실패");
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName(gotoPage);
		return mav;
		
	}
	
	
	
}
