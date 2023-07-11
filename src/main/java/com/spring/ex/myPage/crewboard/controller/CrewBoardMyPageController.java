package com.spring.ex.myPage.crewboard.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.crewboard.model.CrewBoardBean;
import com.spring.ex.crewboard.model.CrewBoardDao;
import com.spring.ex.products.model.ProductsBean;
import com.spring.ex.utility.Paging;

@Controller
public class CrewBoardMyPageController {
	private final String command = "/crewboard/user/myPage.bdcr";
	private String getPage = "myPage/crewboard/myCrewboard";
	
	@Autowired
	CrewBoardDao cb_dao;
	
	/*
	 마이페이지에서 작성한 게시글(크루보드) 클릭시 => myCrewboard.jsp
	 */
	@RequestMapping(command)
	public ModelAndView doAction(Principal principal, HttpServletRequest request,
			@RequestParam(value="whatColumn", required=false) String whatColumn,
			@RequestParam(value="keyword", required=false) String keyword,
			@RequestParam(value="pageNumber", required=false) String pageNumber) {
		
		ModelAndView mav = new ModelAndView();
		String loginId = principal.getName();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("whatColumn", whatColumn);
		map.put("keyword", "%"+keyword+"%");
		map.put("loginId", loginId);
		
		int totalCount = cb_dao.getCrewBoardTotalCount_Mypage(map);
		String url = request.getContextPath()+command;
		
		Paging pageInfo = new Paging(pageNumber, "5", totalCount, url, whatColumn, keyword, null);
		
		List<CrewBoardBean> mylist = cb_dao.getAllCrewBoardById(map, pageInfo);
		mav.addObject("pageInfo", pageInfo);
		mav.addObject("mylist", mylist);
		mav.setViewName(getPage);
		return mav;
	}
}
