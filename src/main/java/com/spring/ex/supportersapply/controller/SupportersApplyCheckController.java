package com.spring.ex.supportersapply.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.ex.supportersapply.model.SupportersApplyDao;

@Controller
public class SupportersApplyCheckController {
	
	private final String command = "/supportersapply/user/apply_check.su";

	@Autowired
	SupportersApplyDao sadao;  
	
	
	//서포터즈 게시글 상세보기에서 '신청하기'버튼 클릭시 '글번호'를 갖고 요청을 통해 이동함(ajax함수 사용)
	@RequestMapping(value=command)
	@ResponseBody
	public String doAction(@RequestParam("num") String num, Principal principal) {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("num", num);
		map.put("loginId", principal.getName()); //loginId 변수에 로그인한 id 담기
		
		System.out.println("SupportersApplyCheckController의 map: "+map); //{loginId=kim123, num=6}
		
		int cnt = -1;
		cnt = sadao.getApplyCount(map); //우선 해당 서포터즈에 신청을 안한 회원만 신청을 할 수 있으므로 갯수(cnt)를 리턴함
		System.out.println("SupportersApplyCheckController의 cnt: "+cnt);
		//cnt가 1이면 해당 서포터즈에 신청내역이 있고, 1이 아니라면 신청한 이력이 없음				
		if(cnt > 0) { //해당 서포터즈에 이미 신청했음
			return "YES";
		}else { //신청 안함
			return "NO";
		}
	}
}
