package com.spring.ex.notice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.ex.notice.model.NoticeDao;

@Controller
public class NoticeDeleteController {
	
	private final String command = "/notice/admin/delete.no";
	private final String gotoPage = "redirect:/notice/all/list.no";
	
	@Autowired
	NoticeDao ndao;
	
	
	//noticeDetailView.jsp에서 '삭제'버튼 클릭 -> confirm에서 '확인'버튼을 클릭 -> 번호와 페이지번호를 갖고 옴
	//-> 삭제 성공시 list.no 재요청
	@RequestMapping(command)
	public String delete(@RequestParam("num") int num, Model model,
			@RequestParam("pageNumber") int pageNumber) {
		
		//System.out.println("삭제할 num: "+num);
		
		int cnt = ndao.deleteNotice(num);
		model.addAttribute("pageNumber", pageNumber);
			
		return gotoPage;
	}
	
	
}
