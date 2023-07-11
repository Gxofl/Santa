package com.spring.ex.admin.supporters.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.supporters.model.SupportersBean;
import com.spring.ex.supporters.model.SupportersDao;
import com.spring.ex.supportersapply.model.SupportersApplyBean;
import com.spring.ex.supportersapply.model.SupportersApplyDao;

@Controller
public class SupportersSepaAdminController {
	
	//관리자페이지 - 서포터즈 관리의 '서포터즈 별 조회'를 클릭하면 이곳으로 이동 : Search 버튼 지워서 이제 이 컨트롤러는 필요없음
	private final String command = "/supportersapply/admin/supporters.sua";
	private String getPage = "admin/supporters/admin_supporters_separate";
	
	@Autowired
	SupportersApplyDao sadao;
	
	@Autowired
	SupportersDao sdao;
	
	
	//넘어온 서포터즈에 신청한 '모든 회원'을 조회함
	@RequestMapping(value=command)
	public ModelAndView doAction(HttpServletRequest request,
			@RequestParam(value="keyword",required = false) String keyword) {

		System.out.println("keyword: "+keyword); //검색한 서포터즈명

		Map<String, String> map = new HashMap<String, String>();
		map.put("keyword", keyword);
					
		List<SupportersBean> suList = sdao.getSupportersListAll(map); //서포터즈명 다 가져오기(select박스에 넣어야함)
		
		/*
		for(int i=0; i < suList.size(); i++) {
			System.out.println(suList.get(i).getSupportersname()); //존재하는 서포터즈명 나열
		}*/
		
		List<SupportersApplyBean> lists = sadao.getApplicantList(map);//해당 서포터즈에 신청한 모든 회원들을 조회
		//조회 목록 : 해당 회원의 "id, 이름, 등급, 신청일, 성별, 생년월일"

		ModelAndView mav = new ModelAndView();
		mav.addObject("suLists", suList); //서포터즈
		mav.addObject("lists", lists); //회원
		mav.setViewName(getPage);

		return mav;
	}
	
	
	
}
