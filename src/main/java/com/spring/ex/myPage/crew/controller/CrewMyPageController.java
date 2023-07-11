package com.spring.ex.myPage.crew.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.crew.model.CrewBean;
import com.spring.ex.crew.model.CrewDao;

@Controller
public class CrewMyPageController {
	private final String command1 = "/crew/user/myPage1.cr"; // 1일크루
	private final String command2 = "/crew/user/myPage2.cr"; // 정기크루
	private String getPage = "myPage/crew/myCrewCheck";
	
	@Autowired
	CrewDao cdao;
	
	// 마이페이지 1일 크루 조회
	@RequestMapping(command1)
	public ModelAndView doAction1(Principal principal) {
		ModelAndView mav = new ModelAndView();
		
		String loginId = principal.getName(); // 내 아이디
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("loginId",loginId);
		//map.put("loginId","%"+loginId+"%");
		map.put("large",1); // 1일 크루임을 분류
		
		
		List<CrewBean> myCrewList = cdao.getMyPageCrew(map);
		List<CrewBean> myJoinCrewList = cdao.getMyPageJoinCrew(map); // 가입한 크루 목록
		
		mav.addObject("myCrewList", myCrewList);
		mav.addObject("myJoinCrewList",myJoinCrewList); 
		mav.addObject("large", 1);
		
		mav.setViewName(getPage);
		return mav;
	}
	
	// 마이페이지 정기 크루 조회
	@RequestMapping(command2)
	public ModelAndView doAction2(Principal principal) {
		ModelAndView mav = new ModelAndView();
		
		String loginId = principal.getName(); // 내 아이디
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("loginId",loginId);
		//map.put("loginId","%"+loginId+"%");
		map.put("large",2); // 정기 크루임을 분류
		
		List<CrewBean> myCrewList = cdao.getMyPageCrew(map); // 내가 만든 크루 목록
		List<CrewBean> myJoinCrewList = cdao.getMyPageJoinCrew(map); // 가입한 크루 목록
		
		mav.addObject("myCrewList", myCrewList);
		mav.addObject("myJoinCrewList",myJoinCrewList); 
		mav.addObject("large", 2);
		
		mav.setViewName(getPage);
		return mav;
	}
	
}
