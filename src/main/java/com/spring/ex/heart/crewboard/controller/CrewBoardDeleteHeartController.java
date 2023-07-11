package com.spring.ex.heart.crewboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.ex.heart.crewboard.model.CrewBoardHeartBean;
import com.spring.ex.heart.crewboard.model.CrewBoardHeartDao;

@Controller
public class CrewBoardDeleteHeartController {
	
private final String command = "/heart/user/crewboard/deleteHeart.ht";
	
	@Autowired
	CrewBoardHeartDao cbhdao;
	
	//크루게시판 상세보기 crewboardDetailView.jsp(input_userId,input_idx) -> 좋아요(heart) 아이콘 클릭 (ajax 요청)
	//heart -> empty_heart 
	@RequestMapping(value=command, method = RequestMethod.POST)
	@ResponseBody
	public String CrewBoardDeleteHeart(
			@RequestParam("input_userId") 	String input_userId,
			@RequestParam("input_idx") 		int input_idx	) {
		
		CrewBoardHeartBean cbhBean = new CrewBoardHeartBean();
		cbhBean.setCrewboardNum(input_idx);
		cbhBean.setUserId(input_userId);
		
		int cnt = -1;
		String result = "";
		try {
			cnt = cbhdao.crewBoardDeleteHeart(cbhBean);
			System.out.println("CrewBoardDeleteHeart cnt : " + cnt);
			
			if(cnt == 1) { 
				result =  "success";
			}else {
				result =  "fail";
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("CrewBoardDeleteHeart result : " + result);
		return result;
		
	}//CrewBoardUpdateHeart
	
}//CrewBoardDeleteHeartController
