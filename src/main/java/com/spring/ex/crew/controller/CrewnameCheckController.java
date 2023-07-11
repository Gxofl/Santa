package com.spring.ex.crew.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.ex.crew.model.CrewDao;

@Controller
public class CrewnameCheckController {
	
	//private final String command = "/crewname_check.cr";
	private final String command = "/crew/user/crewname_check.cr";

	@Autowired
	CrewDao cdao;
	
	//CrewInsertForm.jsp(중복체크 ajax)에서 요청
	@RequestMapping(value=command)
	@ResponseBody
	public String doAction(@RequestParam("input_name") String input_name) {
		int cnt = -1;
		cnt = cdao.checkCrewname(input_name);
		if(cnt == 1) { // 이미 있는 이름
			return "NO";
		}else {
			return "YES";
		}
	}
	
}//CrewnameCheckController
