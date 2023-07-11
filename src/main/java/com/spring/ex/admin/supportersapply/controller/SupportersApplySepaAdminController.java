package com.spring.ex.admin.supportersapply.controller;

import java.util.ArrayList;
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

@Controller
public class SupportersApplySepaAdminController {
	
	
	
	//관리자페이지 - '회원 별 서포터즈 조회'
	private final String command = "/supporters/admin/user.su";
	private String getPage = "admin/supporters/admin_supporters_user";
		
	@Autowired
	SupportersDao sdao;
	
	
	//검색한 회원 id가 신청한 '모든 서포터즈'를 조회함
	@RequestMapping(value=command)
	public ModelAndView doAction(HttpServletRequest request,
			@RequestParam(value="keyword",required = false) String keyword) {
		
		System.out.println("keyword: "+keyword); //검색한 회원의 아이디
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("keyword", keyword);
		
		List<SupportersBean> lists = new ArrayList<SupportersBean>();
		if ( keyword != null) { //처음 조회할때는 키워드가 없음 - 키워드가 null일때는 쿼리로 가지 않도록해줘야 에러가 안남
			lists = sdao.getSupporterList(map); //해당 회원이 신청한 모든 서포터즈를 조회
		}			
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("lists", lists);
		mav.addObject("paramMap", map);
		mav.setViewName(getPage);
		
		return mav;
	}
	
}
