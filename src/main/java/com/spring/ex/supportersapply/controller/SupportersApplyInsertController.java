package com.spring.ex.supportersapply.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.supporters.model.SupportersBean;
import com.spring.ex.supporters.model.SupportersDao;
import com.spring.ex.supportersapply.model.SupportersApplyBean;
import com.spring.ex.supportersapply.model.SupportersApplyDao;

@Controller
public class SupportersApplyInsertController {
	
	private final String command = "/supportersapply/user/insert.sua";
	//private String gotoPage = "redirect:/supporters/user/detail.su"; 
	
	@Autowired
	SupportersApplyDao sadao;
	
	@Autowired
	SupportersDao sdao;
	
		
	//서포터즈 상세보기에서 '신청하기' 버튼을 누르면 post방식 요청을 통해 이곳으로 이동함(서포터즈명과 서포터즈번호가 넘어옴)
	@ResponseBody
	@RequestMapping(value=command, method = RequestMethod.POST)
	public Map<String, Object> insert(HttpServletResponse response,
			Principal principal, 
			@RequestParam("supportersname") String supportersname,
			@RequestParam("num") int num) {				
		
		Map<String, Object> rtrnMap = new HashMap<>();
		//ModelAndView mav = new ModelAndView();
		
		SupportersApplyBean applyBean = new SupportersApplyBean(); //객체생성
		applyBean.setApplicantid(principal.getName()); //사용자의 id 가져와서 applicantid 변수에 넣기
		applyBean.setJoinnum(num); //supporters_apply의 join_num과 supporters의 num은 같은값이므로
		applyBean.setNum(num);
		applyBean.setSupportersname(supportersname);
		
		SupportersBean supporters = sdao.getSupportersByNum(num); 
		//넘어온 서포터즈 번호에 해당하는 레코드 한줄 가져오기 => 왜냐하면 startdate와 enddate가 필요해서
		if(supporters != null) { //만약 서포터즈 정보를 못가져왔을때 applyBean에 넣게되면 null값을 참조했다는 오류가 뜬다. 
			//그래서 값을 가져온게 있는지 먼저 체크하는 것이당
			applyBean.setStartdate(supporters.getStartdate()); //SupportersBean의 startdate가져와서 SupportersApplyBean의 startdate에 넣기
			applyBean.setEnddate(supporters.getEnddate());
		}
		
		System.out.println("start date: " +applyBean.getStartdate());
		System.out.println("end date: " +applyBean.getEnddate());
		
		int cnt = sadao.insertSupportersApply(applyBean); //이제 삽입(insert)하기
		System.out.println("apply insert의 cnt: "+cnt);

		if(cnt == 1) {//insert 성공 -> 신청 성공함
			sadao.addSupportersCount(applyBean); //해당 서포터즈의 신청인원(applicant) +1 하기
			rtrnMap.put("status", "YES");
		}
		else {//insert 실패 -> 신청 실패함
			rtrnMap.put("status", "NO");	
		}			
		return rtrnMap;		
	}
	
	
}
