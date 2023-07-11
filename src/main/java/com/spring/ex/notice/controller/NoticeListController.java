package com.spring.ex.notice.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.notice.model.NoticeBean;
import com.spring.ex.notice.model.NoticeDao;
import com.spring.ex.utility.Paging;

@Controller
public class NoticeListController {
	
	private final String command = "/notice/all/list.no";
	private final String getPage = "/notice/noticeList";
	
	@Autowired
	NoticeDao ndao;
	
	
	@RequestMapping(value=command)
	public ModelAndView doAction(HttpServletRequest request,
			@RequestParam(value="whatColumn",required = false) String whatColumn,
			@RequestParam(value="keyword",required = false) String keyword,
			@RequestParam(value="pageNumber",required = false) String pageNumber,
			Principal principal) {
		
		System.out.println("whatColumn: "+whatColumn);
		System.out.println("keyword: "+keyword);
		System.out.println("pageNumber: "+pageNumber);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("whatColumn",whatColumn);
		map.put("keyword","%"+keyword+"%");
		
		int totalcount = ndao.getTotalCount(map);
		String url = request.getContextPath()+command;
		
		Paging pageInfo = new Paging(pageNumber,"5",totalcount,url,whatColumn,keyword,null);
		
		List<NoticeBean> lists = ndao.getAllNotice(map,pageInfo);
		
		ModelAndView mav = new ModelAndView();
		//String loginId = principal.getName();
		//mav.addObject("loginId", loginId);
		mav.addObject("lists", lists);
		mav.addObject("pageInfo", pageInfo);
		mav.addObject("pageNumber", pageNumber);
		mav.setViewName(getPage);
		
		return mav;
	}
	
}
