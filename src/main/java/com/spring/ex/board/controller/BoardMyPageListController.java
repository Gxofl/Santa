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
public class BoardMyPageListController {
	
	//마이페이지 - 나의 자유게시판 작성글 보기
	private final String command = "/board/user/myPage.br";
	private String getPage = "myPage/board/myBoard";
	
	@Autowired
	BoardDao bdao;
	
	//마이페이지에서 작성한 게시글(자유) 클릭시 => myBoard.jsp로 이동
	@RequestMapping(value=command)
	public ModelAndView doAction(HttpServletRequest request, Principal principal,
			@RequestParam(value="whatColumn",required = false) String whatColumn,
			@RequestParam(value="keyword",required = false) String keyword,
			@RequestParam(value="pageNumber",required = false) String pageNumber) {
		
		ModelAndView mav = new ModelAndView();
		String loginId = principal.getName();
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("whatColumn",whatColumn);
		map.put("keyword", keyword);
		map.put("loginId", loginId);
		
		int totalcount = bdao.getTotalCount(map);
		String url = request.getContextPath()+command;
		
		Paging pageInfo = new Paging(pageNumber,"5",totalcount,url,whatColumn,keyword,null);
		
		List<BoardBean> lists = bdao.getAllBoard(map,pageInfo);
		
		mav.addObject("lists", lists);
		mav.addObject("pageInfo", pageInfo);
		mav.addObject("pageNumber", pageNumber);
		mav.addObject("paramMap", map);
		mav.setViewName(getPage);
		
		return mav;
	}
	
	
}
