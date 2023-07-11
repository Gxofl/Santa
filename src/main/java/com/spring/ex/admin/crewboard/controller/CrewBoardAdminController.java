package com.spring.ex.admin.crewboard.controller;

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
import com.spring.ex.utility.Paging;

@Controller
public class CrewBoardAdminController {
	private final String command = "/crewboard/admin/list.bdcr";
	private String getPage = "admin/crew/admin_Crewboard";
	
	@Autowired
	CrewBoardDao cb_dao;
	
	/*
	 관리자페이지 크루모집글 관리 => admin/crew/admin_Crewboard.jsp
	 */
	@RequestMapping(command)
	public ModelAndView doAction(@RequestParam(value="whatColumn", required=false) String whatColumn,
			@RequestParam(value="keyword", required=false) String keyword,
			@RequestParam(value="pageNumber", required=false) String pageNumber,
			HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("whatColumn", whatColumn);
		map.put("keyword", "%"+keyword+"%");
		
		int totalCount = cb_dao.getCrewBoardTotalCount(map);
		String url = request.getContextPath()+command;
		Paging pageInfo = new Paging(pageNumber, "5", totalCount, url, whatColumn, keyword, null);
		
		List<CrewBoardBean> crewboard_list = cb_dao.getAllCrewboard(map, pageInfo);
		
		mav.addObject("crewboard_list", crewboard_list);
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName(getPage);
		
		return mav;
	}
}
