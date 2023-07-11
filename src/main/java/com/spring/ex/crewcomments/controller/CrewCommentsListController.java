package com.spring.ex.crewcomments.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.ex.crewcomments.model.CrewCommentsBean;
import com.spring.ex.crewcomments.model.CrewCommentsDao;

@Controller
public class CrewCommentsListController {
	
	//private final String command = "/list.ccmt";
	private final String command = "/crewcomments/user/list.ccmt";
	
	@Autowired
	CrewCommentsDao ccmt_dao;
	
	@RequestMapping(value=command)
	@ResponseBody
	public List<CrewCommentsBean> doAction(
			@RequestParam("idx") int idx, 
			@RequestParam("pageNumber") String pageNumber){
		
		//System.out.println("idx:"+idx);
		List<CrewCommentsBean> comments = new ArrayList<CrewCommentsBean>();
		comments = ccmt_dao.getCrewCommentsByIdx(idx);
		//System.out.println("comments size : "+comments.size());
		
		for(int i=0; i<comments.size(); i++) { // 페이지 넣기
			comments.get(i).setPageNumber(pageNumber);
			
			// 비밀댓글 열람을 위해 원댓글자
			int ref = comments.get(i).getRef();
			String orgwriter = ccmt_dao.getCrewCommentsOrgWriter(ref);
			comments.get(i).setOrgwriter(orgwriter);
		}
		
		return comments;
	}
}
