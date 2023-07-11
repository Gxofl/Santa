package com.spring.ex.stamp.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.stamp.model.StampBean;
import com.spring.ex.stamp.model.StampDao;
import com.spring.ex.users.model.UsersBean;
import com.spring.ex.users.model.UsersDao;

@Controller
public class StampUpdateApplyController {
	//요청 값을 담은 변수
		private final String command = "/stamp/admin/updateapply.stp";
		
		//redirect할 요청 변수
		private String gotoPage = "redirect:/stamp/admin/adminlist.stp";
		
		//qnaDao 객체 생성
		@Autowired
		StampDao sdao;
		@Autowired
		UsersDao udao;
		
		@RequestMapping(value=command)
		public ModelAndView doAction(
				@RequestParam(value="userid") String userid,
				@RequestParam(value="stampnum") String stampnum,
				@RequestParam(value="stampapply") String stampapply,
				@RequestParam(value="pageNumber", required = false) String pageNumber,
				HttpSession session, Principal principal, HttpServletRequest request
				) {
			
			System.out.println("넘어온 apply 값 : "+stampapply);
			
			ModelAndView mav = new ModelAndView();
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("stampnum", stampnum);
			map.put("stampapply", stampapply);
			
			if(principal.getName() == null) {
				//로그인 정보가 없으면
				//로그인 페이지로 넘어가서 다시 list.mnt 요청으로 돌아오도록 세션 설정
				session.setAttribute("destination", gotoPage);
				
				//다시 원래 페이지로 돌아가기 위해 페이지 정보 넘기기            
				mav.addObject("pageNumber",pageNumber);
				//로그인 페이지로 이동하도록 뷰 설정
				mav.setViewName("redirect:/loginForm");
			}else {
				//로그인 정보가 있으면
				//인증 상태 업데이트
				int cnt = sdao.updateApply(map);
				if(cnt > 0) {
					//업데이트 성공 시
					System.out.println(stampapply+" : 업데이트 성공");
					
					//업데이트 성공 시 id 기준 승인 상태인 인증 내역 불러옴
					List<StampBean> applyCount = sdao.getApplyCountGroupById();
					System.out.println("applyCount"+applyCount);
					
					//불러온 인증 내역이 null이 아니면
					if(!applyCount.isEmpty()) {
						System.out.println("인증내역 null 아님");
						//인증 내역의 크기 만큼 비교
						for(int i=0;i<applyCount.size();i++) {
							//만약 현재 아이디가 인증 내역의 아이디와 같고
							//apply갯수가 10이면
							if(userid.equals(applyCount.get(i).getUsersid()) && applyCount.get(i).getApplycount() == 10 ) {
								UsersBean usersBean = new UsersBean();
								
								//usersBean에 아이디와 변경할 등급 저장
								usersBean.setUserId(userid);
								usersBean.setUserRole("r02");
								
								//bean을 넘겨 등급을 업데이트
								int cnt2 = udao.usersUpdateUserRole(usersBean);
								if(cnt2>0) {
									System.out.println("등급 업 성공");
								}else {
									System.out.println("등급 업 실패");
								}//if~else
							}//if
						}//for
					}//if
					
					
					//다시 원래 페이지로 돌아가기 위해 페이지 정보 넘기기
					mav.addObject("pageNumber",pageNumber);
					//목록으로 이동
					mav.setViewName(gotoPage);
				}else {
					System.out.println(stampapply+" : 업데이트 실패");
					

					//다시 원래 페이지로 돌아가기 위해 페이지 정보 넘기기
					mav.addObject("pageNumber",pageNumber);
					//목록으로 이동
					mav.setViewName(gotoPage);
				}
			}
			
			return mav;
		}
}
