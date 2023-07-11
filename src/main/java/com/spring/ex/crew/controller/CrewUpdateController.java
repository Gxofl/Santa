package com.spring.ex.crew.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.crew.model.CrewBean;
import com.spring.ex.crew.model.CrewDao;
import com.spring.ex.crewboard.model.CrewBoardBean;
import com.spring.ex.crewboard.model.CrewBoardDao;


//1.crewboardList.jsp(신청하기)클릭시 요청
//2.crewboardDetailView.jsp(신청하기)클릭시 요청
@Controller
public class CrewUpdateController {
	
	//private final String command = "/update.cr";
	private final String command = "/crew/user/update.cr";
	private String getPage = "";
	private String gotoPage = "redirect:/crewboard/user/detail.bdcr";
	
	@Autowired
	CrewDao cdao;
	@Autowired
	CrewBoardDao cbdao;
	
	/* 
	 crewboardDetailView.jsp에서 신청하기 버튼 클릭 => /crewboard/user/detail.bdcr 요청 
	 */
	@RequestMapping(value=command, method=RequestMethod.GET)
	public ModelAndView doAction(
			@RequestParam("crewname") String crewname,
			@RequestParam("pageNumber") String pageNumber,
			@RequestParam("loginId") String loginId,
			@RequestParam("num") int num) {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageNumber", pageNumber); // 페이지
		mav.addObject("num", num); // 크루모집 게시글 번호
		
		mav.setViewName(gotoPage);
		
		//회원일때(정기크루일때 : 이미 가입한 정기크루가 있으면 가입안됨)
		//크루이름과 본인아이디 가져가야함.(CrewBean에 담아감 crewname, crewmember)
		CrewBean cb = new CrewBean();
		cb.setCrewname(crewname);
		cb.setCrewmember(","+loginId); // ,를 같이 붙여서 넣어주기
		
		int cnt = cdao.updateCrew(cb);
		if(cnt != -1) {// 가입성공
			// 1. 정원이 다 찼으면 모집완료로 바꾸기
			CrewBoardBean cbb = cbdao.getCrewboardByCrewname(crewname);
			if(cbb.getCrewnow()==cbb.getCrewlimit()) { //정원이 다 찼으면
				int result = cbdao.updateCrewboardState(crewname);
				if(result != -1) {
					System.out.println("모집완료 수정 성공");
					
					//2. 크루 state도 바꾸기
					int result2 = cdao.updateCrewState(crewname);
					if(result2 != -1) {
						System.out.println("크루 모집완료로 수정 성공");
					}else {
						System.out.println("크루 모집완료로 수정 실패");
					}
				}else {
					System.out.println("모집완료 수정 실패");
				}
			}
			// 2. 마이페이지 크루가입 내역으로 가기
			
		}else { //가입실패
			//원래의 페이지로 돌아가기
		}
	
		return mav;
	}
	
	
	/* 
	 crewboardList.jsp에서 신청하기 버튼 클릭 => /crewboard/all/list.bdcr 요청 
	 */
	@RequestMapping(value=command, method=RequestMethod.POST)
	public ModelAndView doAction(
			CrewBean cb, 
			@RequestParam("pageNumber") String pageNumber,
			Principal principal) {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageNumber", pageNumber); // 페이지
		
		gotoPage = "redirect:/crewboard/all/list.bdcr";
		mav.setViewName(gotoPage);
		
		cb.setCrewmember(","+principal.getName()); // 크루멤버에 넣기
		
		int cnt = cdao.updateCrew(cb);
		if(cnt != -1) {// 가입성공
			// 1. 정원이 다 찼으면 모집완료로 바꾸기
			CrewBoardBean cbb = cbdao.getCrewboardByCrewname(cb.getCrewname());
			if(cbb.getCrewnow()==cbb.getCrewlimit()) { //정원이 다 찼으면
				int result = cbdao.updateCrewboardState(cb.getCrewname());
				if(result != -1) {
					System.out.println("모집완료 수정 성공");
					
					//2. 크루 state도 바꾸기
					int result2 = cdao.updateCrewState(cb.getCrewname());
					if(result2 != -1) {
						System.out.println("크루 모집완료로 수정 성공");
					}else {
						System.out.println("크루 모집완료로 수정 실패");
					}
				}else {
					System.out.println("모집완료 수정 실패");
				}
			}
			// 2. 마이페이지 크루가입 내역으로 가기
			
		}else { //가입실패
			//원래의 페이지로 돌아가기
		}
	
		return mav;
	}
}
