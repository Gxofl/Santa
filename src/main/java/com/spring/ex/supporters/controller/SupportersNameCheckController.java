package com.spring.ex.supporters.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.ex.supporters.model.SupportersDao;


@Controller
public class SupportersNameCheckController {
	
	private final String command = "/supporters/admin/supportersname_check.su";

	@Autowired
	SupportersDao sdao;
	
	//ajax함수로 서포터즈명 중복체크
	@RequestMapping(value=command, method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> doAction(@RequestParam("input_name") String input_name) {
		Map<String, Object> rtrnMap = new HashMap<>();

		int cnt = sdao.checkSupportersname(input_name);
		
		if(cnt >= 1) {
			rtrnMap.put("code", "300");
			rtrnMap.put("msg", "이미 존재하는 이름입니다.");
		}else {
			rtrnMap.put("code", "200");
			rtrnMap.put("msg", "사용 가능한 이름입니다.");
		}
		
		return rtrnMap;
	}
	
	
}//
