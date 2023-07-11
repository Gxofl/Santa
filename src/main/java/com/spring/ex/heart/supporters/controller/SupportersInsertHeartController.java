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
public class SupportersInsertHeartController {
	
	private final String command = "/heart/user/supporters/insertHeart.ht";
	
	@Autowired
	SupportersHeartDao shdao;
	
	//서포터즈 게시판 상세보기 supportersDetailView.jsp(input_userId,input_num) -> 좋아요(empty_heart) 아이콘 클릭 (ajax 요청)
	//empty_heart -> heart
	@RequestMapping(value=command, method = RequestMethod.POST)
	@ResponseBody
	public String SupportersInsertHeart(
			@RequestParam("input_userId") 	String 	input_userId,
			@RequestParam("input_num") 		int 	input_num	) {
		System.out.println("====check1====");
		SupportersHeartBean shBean = new SupportersHeartBean();
		shBean.setSupportersNum(input_num);
		shBean.setUserId(input_userId);
		System.out.println(shBean.getSupportersNum());
		System.out.println(shBean.getUserId());
		int cnt = -1;
		String result = "";
		try {
			System.out.println("====check2====");
			cnt = shdao.supportersInsertHeart(shBean);
			System.out.println("supportersInsertHeart cnt : " + cnt);
			
			if(cnt == 1) { 
				result =  "success";
			}else {
				result =  "fail";
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("supportersInsertHeart result : " + result);
		return result;
		
	}//SupportersInsertHeart
	
}//SupportersInsertHeartController
