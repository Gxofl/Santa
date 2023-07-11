package com.spring.ex.board.controller;

import java.security.Principal;
import java.util.ArrayList;
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
import com.spring.ex.heart.board.model.BoardHeartBean;
import com.spring.ex.heart.board.model.BoardHeartDao;
import com.spring.ex.utility.Paging;

@Controller
public class BoardListController {
	
	private final String command = "/board/all/list.br";
	private final String getPage = "/board/boardList";
	
	@Autowired
	BoardDao bdao;
	
	//좋아요
	@Autowired
	BoardHeartDao bhdao;
		
	@RequestMapping(value=command)
	public ModelAndView doAction(HttpServletRequest request, Principal principal,
			@RequestParam(value="whatColumn",required = false) String whatColumn,
			@RequestParam(value="keyword",required = false) String keyword,
			@RequestParam(value="pageNumber",required = false) String pageNumber) {
		
		ModelAndView mav = new ModelAndView();
		//String loginId = principal.getName();
		System.out.println("whatColumn: "+whatColumn);
		System.out.println("keyword: "+keyword);
		System.out.println("pageNumber: "+pageNumber);
		
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("whatColumn",whatColumn);
		map.put("keyword", keyword);
		
		int totalcount = bdao.getTotalCount(map);
		String url = request.getContextPath()+command;
		
		Paging pageInfo = new Paging(pageNumber,"5",totalcount,url,whatColumn,keyword,null);
		
		List<BoardBean> lists = bdao.getAllBoard(map,pageInfo);
		
		/* 좋아요 */
		List<BoardHeartBean> bhList = new ArrayList<BoardHeartBean>();
		BoardHeartBean bhBean = new BoardHeartBean();
		String loginId = "";
		try {
			loginId = principal.getName();
			bhBean.setUserId(loginId);		//사용자아이디
			bhList = bhdao.getAllBoardHeart(loginId);
			System.out.println("bhList : " + bhList);
			//현재 게시글의 좋아요 수 확인
		}catch (Exception e) {
			e.printStackTrace();
		}
		//좋아요
		mav.addObject("bhList", bhList);
		
		mav.addObject("lists", lists);
		mav.addObject("pageInfo", pageInfo);
		mav.addObject("pageNumber", pageNumber);
		mav.addObject("paramMap", map);
		mav.setViewName(getPage);
		
		return mav;
	}
	
}
