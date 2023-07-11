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
public class SupportersDeleteHeartController {
	
private final String command = "/heart/user/supporters/deleteHeart.ht";
	
	@Autowired
	SupportersHeartDao shdao;
	
	//서포터즈 게시판 상세보기 input_num.jsp(input_userId,input_num) -> 좋아요(heart) 아이콘 클릭 (ajax 요청)
	//heart -> empty_heart 
	@RequestMapping(value=command, method = RequestMethod.POST)
	@ResponseBody
	public String SupportersDeleteHeart(
			@RequestParam("input_userId") 	String 	input_userId,
			@RequestParam("input_num") 		int 	input_num		) {
		
		SupportersHeartBean shBean = new SupportersHeartBean();
		shBean.setSupportersNum(input_num);
		shBean.setUserId(input_userId);
		
		int cnt = -1;
		String result = "";
		try {
			cnt = shdao.supportersDeleteHeart(shBean);
			System.out.println("supportersDeleteHeart cnt : " + cnt);
			
			if(cnt == 1) { 
				result =  "success";
			}else {
				result =  "fail";
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("supportersDeleteHeart result : " + result);
		return result;
		
	}//SupportersDeleteHeart
	
}//SupportersDeleteHeartController
