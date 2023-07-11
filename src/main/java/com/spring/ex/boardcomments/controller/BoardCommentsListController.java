package com.spring.ex.boardcomments.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.boardcomments.model.BoardCommentsBean;
import com.spring.ex.boardcomments.model.BoardCommentsDao;

@Controller
public class BoardCommentsListController {
	
	private final String command = "/boardcomments/user/list.bcmt";
	//private String getPage = "/board/boardDetailView";
	
	@Autowired
	BoardCommentsDao bcmt_dao;
	
	
	//댓글 작성하면 insert.bcmt를 거쳐 페이지번호와 bean을 가지고 이곳으로 이동함
	@RequestMapping(value=command, method=RequestMethod.POST)
	@ResponseBody
	public List<BoardCommentsBean> doAction(
			@RequestParam("idx") int idx,
			@RequestParam("pageNumber") String pageNumber){
		
		System.out.println("bnum:"+ idx);
		
		List<BoardCommentsBean> boardcomments = new ArrayList<BoardCommentsBean>();
		boardcomments = bcmt_dao.getBoardCommentsByBnum(idx);
		
		System.out.println("boardcomments size: "+boardcomments.size());
		
		for(int i=0; i<boardcomments.size(); i++) { // 페이지 넣기
			boardcomments.get(i).setPageNumber(pageNumber);
			
			// 비밀댓글 열람을 위해 원댓글자
			int ref = boardcomments.get(i).getRef();
			System.out.println(">>>>>>>" + ref);
			String orgwriter = bcmt_dao.getBoardCommentsOrgWriter(ref);
			System.out.println(">>>>>>>" + orgwriter);
			boardcomments.get(i).setOrgwriter(orgwriter);
		}
		
		return boardcomments;
	}
	
}
