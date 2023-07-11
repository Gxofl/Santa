package com.spring.ex.admin.users.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.spring.ex.users.model.UsersBean;
import com.spring.ex.users.model.UsersDao;
import com.spring.ex.utility.Paging;

@Controller
public class UserStatisticsController {

	private final String command = "/users/admin/userStatistics.us";
	private final String command2 = "/users/admin/statsData1.us";
	private String gotoPage = "/admin/users/userStatistics";

	@Autowired
	UsersDao udao;

	//화면 띄우기
	@RequestMapping(value = command, method = RequestMethod.GET)
	public ModelAndView userStatistics(HttpServletRequest request, Principal principal) {

		ModelAndView mav = new ModelAndView();
		List<UsersBean> usersList = null;

		try {
			Map<String, String> map = new HashMap<String, String>();
		} catch (Exception e) {
			e.printStackTrace();
		}

		mav.addObject("usersList", usersList);
		mav.setViewName(gotoPage);
		return mav;

	}
	// userStatistics
	
	//통계
	@RequestMapping(value=command2, method = RequestMethod.POST)
	@ResponseBody
	public List<HashMap<String, Object>> UserIdCheck(HttpServletRequest request, Principal principal) {
		
		List<HashMap<String, Object>> resultObj = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> param = new HashMap<String, Object>();
		
		resultObj = udao.userStats1(param);
		if(resultObj == null) {
			resultObj = new ArrayList<HashMap<String,Object>>();
		}
		return resultObj;
		
	}

}
// UserStatisticsController
