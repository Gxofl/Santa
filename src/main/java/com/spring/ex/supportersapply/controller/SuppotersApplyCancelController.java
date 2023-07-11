package com.spring.ex.supportersapply.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.ex.supportersapply.model.SupportersApplyBean;
import com.spring.ex.supportersapply.model.SupportersApplyDao;

@Controller
public class SuppotersApplyCancelController {
		
		
		private final String command = "/supportersapply/user/apply_cancel.su";

		@Autowired
		SupportersApplyDao sadao;
		
		
		//supportersDetailView.jsp에서 '취소하기' 버튼을 누르면, '서포터즈 글번호'를 갖고 이곳으로 
		@RequestMapping(value=command)
		@ResponseBody
		public String doAction(@RequestParam("num") int num, Principal principal) {
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("num", String.valueOf(num)); //num을 int로 받아서 String로 변환해줘야 함
			map.put("loginId", principal.getName()); //사용자의 id를 loginId 변수에 넣음
			
			SupportersApplyBean applyBean = new SupportersApplyBean(); //객체 생성
			applyBean.setApplicantid(principal.getName());
			applyBean.setJoinnum(num); //
			applyBean.setNum(num);//SupportersApplyBean의 num에 서포터즈 번호 담기 => discountSupportersCount()에서 필요
			
			int cnt = -1;
			cnt = sadao.getApplyCount(map); //우선 해당 서포터즈에 신청한 이력이 있는 사람의 경우만 취소처리가 되어야 하므로 갯수(cnt)를 리턴함
			if(cnt > 0) { //cnt가 1이면 해당 서포터즈에 신청내역이 있고, 1이 아니라면 신청한 이력이 없음				
				sadao.cancelSupportersApply(map); //취소 처리함
				sadao.discountSupportersCount(applyBean); //해당 서포터즈의 신청인원 -1 하기
				return "YES";
			}else {
				//신청한 이력이 없는 사람은 신청 이력이 없다고 alert을 띄우기 위해 NO을 리턴함
				return "NO";
			}
		}
	
}
