package com.spring.ex.heart.supporters.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.ex.heart.supporters.model.SupportersHeartBean;
import com.spring.ex.heart.supporters.model.SupportersHeartDao;

@Controller
public class SupportersHeartCheckController {

	private final String command = "/heart/user/supporters/checkHeart.ht";
	
	@Autowired
	SupportersHeartDao shdao;
	
	//서포터즈 게시판 상세보기 supportersDetailView.jsp(input_userId,input_num)가 로드될 때 게시글의 좋아요 상태 확인
	@RequestMapping(value=command, method = RequestMethod.POST)
	@ResponseBody
	public String SupportersCheckHeart(
			@RequestParam("input_userId") 	String 	input_userId,
			@RequestParam("input_num") 		int 	input_num		) {
        
		SupportersHeartBean shBean = new SupportersHeartBean();
		shBean.setSupportersNum(input_num);
		shBean.setUserId(input_userId);
		
		int cnt = -1;
		String result = "";
		try {
			cnt = shdao.supportersCheckHeart(shBean);
			
			if(cnt == 1) { 
	 			result =  "check";
			}else {
				result =  "fail";
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("supportersCheckHeart result : " + result);
		return result;
		
	}//SupportersCheckHeart
	
}//SupportersHeartCheckController

