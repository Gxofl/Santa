package com.spring.ex.stamp.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.stamp.model.StampDao;

@Controller
public class StampUpdateRejectController {
	//요청 값을 담은 변수
		private final String command = "/stamp/admin/updatereject.stp";
		
		//redirect할 요청 변수
		private String gotoPage = "redirect:/stamp/admin/adminlist.stp";
		
		//qnaDao 객체 생성
		@Autowired
		StampDao sdao;
		@RequestMapping(value=command)
		public ModelAndView doAction(
				@RequestParam(value="stampnum") String stampnum,
				@RequestParam(value="stampreject") int stampreject,
				@RequestParam(value="pageNumber", required = false) String pageNumber,
				HttpSession session, Principal principal, HttpServletRequest request
				) {
			
			System.out.println("넘어온 reject 값 : "+stampreject);
			
			ModelAndView mav = new ModelAndView();
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("stampnum", stampnum);
			map.put("stampreject", stampreject);
			
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
				int cnt = sdao.updateReject(map);
				if(cnt > 0) {
					System.out.println(stampreject+" : 업데이트 성공");
					
					//다시 원래 페이지로 돌아가기 위해 페이지 정보 넘기기
					mav.addObject("pageNumber",pageNumber);
					//목록으로 이동
					mav.setViewName(gotoPage);
				}else {
					System.out.println(stampreject+" : 업데이트 실패");
					

					//다시 원래 페이지로 돌아가기 위해 페이지 정보 넘기기
					mav.addObject("pageNumber",pageNumber);
					//목록으로 이동
					mav.setViewName(gotoPage);
				}
			}
			
			return mav;
		}
}
