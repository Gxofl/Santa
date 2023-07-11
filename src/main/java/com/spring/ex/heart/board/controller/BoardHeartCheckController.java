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
public class BoardHeartCheckController {

	private final String command = "/heart/user/board/checkHeart.ht";
	
	@Autowired
	BoardHeartDao bhdao;
	
	//크루게시판 상세보기 boardDetailView.jsp(input_userId,input_num)가 로드될 때 게시글의 좋아요 상태 확인
	@RequestMapping(value=command, method = RequestMethod.POST)
	@ResponseBody
	public String BoardCheckHeart(
			@RequestParam("input_userId") 	String 	input_userId,
			@RequestParam("input_num") 		int 	input_num		) {
        
		BoardHeartBean bhBean = new BoardHeartBean();
		bhBean.setBoardNum(input_num);
		bhBean.setUserId(input_userId);
		
		int cnt = -1;
		String result = "";
		try {
			cnt = bhdao.boardCheckHeart(bhBean);
			
			if(cnt == 1) { 
	 			result =  "check";
			}else {
				result =  "fail";
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("boardCheckHeart result : " + result);
		return result;
		
	}//BoardCheckHeart
	
}//BoardHeartCheckController

