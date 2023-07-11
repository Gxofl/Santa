package com.spring.ex.myPage.supportersapply.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.supportersapply.model.SupportersApplyBean;
import com.spring.ex.supportersapply.model.SupportersApplyDao;

@Controller
public class SupportersMyPageController {
	
	//마이페이지 내 서포터즈의 '활동중'과 '활동종료'를 조회하는 컨트롤러
	//서포터즈 '전체' 조회는 SupportersApplyListController에서 함	
	private final String command1 = "/supportersapply/user/myPage1.sua"; //활동중
	private final String command2 = "/supportersapply/user/myPage2.sua"; //활동종료
	private String getPage1 = "myPage/supporters/mySupporters_ing"; //활동중
	private String getPage2 = "myPage/supporters/mySupporters_end"; //활동종료
	
	@Autowired
	SupportersApplyDao sadao;
	
	
	//마이페이지 서포터즈의 '활동중' 조회
	@RequestMapping(command1)
	public ModelAndView doAction1(Principal principal) {
		
		ModelAndView mav = new ModelAndView();
		String loginId = principal.getName(); // 내 아이디

		Map<String, Object> map = new HashMap<String,Object>();
		map.put("loginId", loginId);
		
		//활동중 : 활동시작일 <= 오늘 <= 활동종료일
		List<SupportersApplyBean> applybean = sadao.getMySupportersIng(loginId); 

		mav.addObject("applybean", applybean);
		mav.setViewName(getPage1);
		return mav;
	}
	
	
	//마이페이지 서포터즈의 '활동중' 조회
	@RequestMapping(command2)
	public ModelAndView doAction2(Principal principal) {

		ModelAndView mav = new ModelAndView();
		String loginId = principal.getName(); // 내 아이디

		Map<String, Object> map = new HashMap<String,Object>();
		map.put("loginId", loginId);

		//활동종료 : 오늘 > 활동종료일
		List<SupportersApplyBean> applybean = sadao.getMySupportersEnd(loginId); 

		mav.addObject("applybean", applybean);
		mav.setViewName(getPage2);
		return mav;
	}
	
	
}
