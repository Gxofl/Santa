package com.spring.ex.board.controller;

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

import com.spring.ex.board.model.BoardBean;
import com.spring.ex.board.model.BoardDao;
import com.spring.ex.utility.Paging;

@Controller
public class BoardAdminListController {
	
	//관리자 페이지 - 공지사항 관리 페이지
	private final String command = "/board/admin/list.br";
	private final String getPage = "admin/board/admin_boardList";

	@Autowired
	BoardDao bdao;


	@RequestMapping(value=command)
	public ModelAndView doAction(HttpServletRequest request,
			@RequestParam(value="whatColumn",required = false) String whatColumn,
			@RequestParam(value="keyword",required = false) String keyword,
			@RequestParam(value="pageNumber",required = false) String pageNumber,
			Principal principal) {

		Map<String, String> map = new HashMap<String, String>();
		map.put("whatColumn",whatColumn);
		map.put("keyword", keyword);
		
		int totalcount = bdao.getTotalCount(map);
		String url = request.getContextPath()+command;
		
		Paging pageInfo = new Paging(pageNumber,"5",totalcount,url,whatColumn,keyword,null);
		
		List<BoardBean> lists = bdao.getAllBoard(map,pageInfo);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("lists", lists);
		mav.addObject("pageInfo", pageInfo);
		mav.addObject("pageNumber", pageNumber);
		mav.addObject("paramMap", map);
		mav.setViewName(getPage);
		
		return mav;
	}
	
	
	
}
