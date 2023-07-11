package com.spring.ex.supporters.controller;

import java.security.Principal;
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

import com.spring.ex.heart.products.model.ProductsHeartBean;
import com.spring.ex.heart.supporters.model.SupportersHeartBean;
import com.spring.ex.heart.supporters.model.SupportersHeartDao;
import com.spring.ex.supporters.model.SupportersBean;
import com.spring.ex.supporters.model.SupportersDao;
import com.spring.ex.utility.Paging;

@Controller
public class SupportersListController {
	
	private final String command = "/supporters/all/list.su";
	private String getPage = "supporters/supportersList"; 
	
	
	@Autowired
	SupportersDao sdao;
	
	//좋아요
	@Autowired
	SupportersHeartDao shdao;
		
	@RequestMapping(value=command)
	public ModelAndView doAction(HttpServletRequest request,
			@RequestParam(value="whatColumn",required = false) String whatColumn,
			@RequestParam(value="keyword",required = false) String keyword,
			@RequestParam(value="pageNumber",required = false) String pageNumber,
			Principal principal) {
		
		ModelAndView mav = new ModelAndView();
		
		System.out.println("whatColumn: "+whatColumn);
		System.out.println("keyword: "+keyword);
		System.out.println("pageNumber: "+pageNumber);
		
		int[] supporters_count = new int[4];
		supporters_count[0] = sdao.getAllSupportersCount(); // 총 크루 수 
		supporters_count[1] = sdao.getIngSupportersCount(); // 모집중
		supporters_count[2] = sdao.getCompleteSupportersCount(); // 모집완료
		supporters_count[3] = sdao.getClosedSupportersCount();	// 모집기한 마감
		mav.addObject("supporters_count", supporters_count);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("whatColumn", whatColumn);
		map.put("keyword","%"+keyword+"%");
		
		int totalcount = sdao.getTotalSupportersCount(map);
		String url = request.getContextPath()+command;
		
		Paging pageInfo = new Paging(pageNumber,"5",totalcount,url,whatColumn,keyword,null);
		
		List<SupportersBean> lists = sdao.getAllSupporters(map,pageInfo);
		
		
		/* 좋아요 */
		List<SupportersHeartBean> shList = new ArrayList<SupportersHeartBean>();
		SupportersHeartBean shBean = new SupportersHeartBean();
		String loginId = "";
		try {
			loginId = principal.getName();
			shBean.setUserId(loginId);		//사용자아이디
			shList = shdao.getAllSupportersHeart(loginId);
			System.out.println("shList : " + shList);
			//현재 게시글의 좋아요 수 확인
		}catch (Exception e) {
			e.printStackTrace();
		}
		//좋아요
		
		mav.addObject("shList", shList);
		mav.addObject("lists", lists);
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName(getPage);
		
		return mav;
	}
	
	
	
}
