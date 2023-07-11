package com.spring.ex.heart.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.ex.heart.board.model.BoardHeartBean;
import com.spring.ex.heart.board.model.BoardHeartDao;

@Controller
public class BoardInsertHeartController {
	
	private final String command = "/heart/user/board/insertHeart.ht";
	
	@Autowired
	BoardHeartDao bhdao;
	
	//게시판 상세보기 boardDetailView.jsp(input_userId,input_num) -> 좋아요(empty_heart) 아이콘 클릭 (ajax 요청)
	//empty_heart -> heart
	@RequestMapping(value=command, method = RequestMethod.POST)
	@ResponseBody
	public String BoardInsertHeart(
			@RequestParam("input_userId") 	String 	input_userId,
			@RequestParam("input_num") 		int 	input_num	) {
		
		BoardHeartBean bhBean = new BoardHeartBean();
		bhBean.setBoardNum(input_num);
		bhBean.setUserId(input_userId);
		int cnt = -1;
		String result = "";
		try {
			cnt = bhdao.boardInsertHeart(bhBean);
			System.out.println("boardInsertHeart cnt : " + cnt);
			
			if(cnt == 1) { 
				result =  "success";
			}else {
				result =  "fail";
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("boardInsertHeart result : " + result);
		return result;
		
	}//BoardInsertHeart
	
}//BoardInsertHeartController
