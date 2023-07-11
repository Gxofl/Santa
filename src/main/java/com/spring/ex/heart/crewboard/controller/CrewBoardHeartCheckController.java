package com.spring.ex.heart.crewboard.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.ex.heart.crewboard.model.CrewBoardHeartBean;
import com.spring.ex.heart.crewboard.model.CrewBoardHeartDao;

@Controller
public class CrewBoardHeartCheckController {

	private final String command = "/heart/user/crewboard/checkHeart.ht";
	
	@Autowired
	CrewBoardHeartDao cdao;
	
	//크루게시판 상세보기 crewboardDetailView.jsp(input_userId,input_idx)가 로드될 때 게시글의 좋아요 상태 확인
	@RequestMapping(value=command, method = RequestMethod.POST)
	@ResponseBody
	public String CrewBoardCheckHeart(
	  @RequestParam("input_userId") String	input_userId,
	  @RequestParam("input_idx") 	int 	input_idx	) {
        
		CrewBoardHeartBean cbhBean = new CrewBoardHeartBean();
		cbhBean.setCrewboardNum(input_idx);
		cbhBean.setUserId(input_userId);
		
		int cnt = -1;
		String result = "";
		try {
			cnt = cdao.crewBoardCheckHeart(cbhBean);
			
			if(cnt == 1) { 
	 			result =  "check";
			}else {
				result =  "fail";
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("crewBoardCheckHeart result : " + result);
		return result;
		
	}//CrewBoardCheckHeart
	
}//CrewBoardHeartCheckController

