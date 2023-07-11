package com.spring.ex.admin.users.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.users.model.UsersBean;
import com.spring.ex.users.model.UsersDao;
import com.spring.ex.utility.Paging;

@Controller
public class UsersListController {

	private final String command = "/users/admin/usersList.us";
	private String gotoPage = "/admin/users/usersList";
	
	@Autowired
	UsersDao udao;
	
	@RequestMapping(value = command)
	public ModelAndView usersList(
			@RequestParam(value="whatColumn", required=false) String whatColumn,
			@RequestParam(value="keyword"	, required=false) String keyword,
			@RequestParam(value="pageNumber", required=false) String pageNumber,
			HttpServletRequest request, Principal principal	) {
		
		ModelAndView mav = new ModelAndView();
		List<UsersBean> usersList = null;
		Paging pageInfo = null;
		
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("whatColumn", whatColumn);
			if ("user_role".equals(whatColumn)) {
			    if ("일".equals(keyword) || "반".equals(keyword) || "일반".equals(keyword)) {
			        map.put("keyword", "r01");
			    } else if ("우".equals(keyword) || "수".equals(keyword) || "우수".equals(keyword)) {
			        map.put("keyword", "r02");
			    } else if ("관".equals(keyword) || "리".equals(keyword) || "자".equals(keyword) 
			    			|| "관리자".equals(keyword)) {
			        map.put("keyword", "r99");
			    } 
			} else {
				if (keyword == null) {
	                keyword = ""; // 검색어가 null인 경우 빈 문자열로 설정
	            }
			    map.put("keyword", "%" + keyword + "%");
			    
			}

			System.out.println("whatColumn : " + whatColumn);
			System.out.println("keyword : " + keyword);
			
			int totalCount = udao.getUserTotalCount(map);
			String url = request.getContextPath()+command;
			pageInfo = new Paging(pageNumber, "5", totalCount, url, whatColumn, keyword, null);
			
			usersList = udao.getAllUsers(map, pageInfo);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject("usersList", usersList);
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName(gotoPage);
		return mav;
	}//usersList
	
}//UsersListController
