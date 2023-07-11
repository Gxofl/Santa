
package com.spring.ex.admin.supporters.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.supporters.model.SupportersBean;
import com.spring.ex.supporters.model.SupportersDao;
import com.spring.ex.supportersapply.model.SupportersApplyBean;
import com.spring.ex.supportersapply.model.SupportersApplyDao;

@Controller
public class SupportersSepaAdminListController {

	//관리자페이지 - 서포터즈 관리의 '서포터즈 별 조회'를 클릭하면 이곳으로 이동
	private final String command = "/supportersapply/admin/supportersList.sua";
	private String getPage = "admin/supporters/admin_supporters_separate";

	@Autowired
	SupportersApplyDao sadao;

	@Autowired
	SupportersDao sdao;

	//넘어온 서포터즈에 신청한 '모든 회원'을 조회함 : select-option이 바뀔때마다, ajax함수를 통해
	@ResponseBody
	@RequestMapping(value=command)
	public Map<String, Object> doAction(HttpServletRequest request,
			@RequestParam(value="keyword",required = false) String keyword) {

		System.out.println("keyword: "+keyword); //검색한 서포터즈명

		Map<String, Object> rtrnMap = new HashMap<String, Object>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("keyword", keyword);

		List<SupportersApplyBean> lists = sadao.getApplicantList(map); //서포터즈에 신청한 모든 회원을 조회
		//조회 목록 : 해당 회원의 "id, 이름, 등급, 신청일, 성별, 생년월일"

		rtrnMap.put("suLists", lists);

		return rtrnMap;
	}
}

