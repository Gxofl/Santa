package com.spring.ex.qna.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.qna.model.QnaDao;

@Controller
public class QnaDeleteController {

		//요청 값을 담은 변수
		private final String command = "/qna/user/deleteQna.qna";
		
		//redirect할 요청 변수
		private String gotoPage = "redirect:/qna/all/list.qna";
		
		//qnaDao 객체 생성
		@Autowired
		QnaDao qdao;
		
		//qna 목록 => 현재 글 삭제하기 버튼 클릭 시
		@RequestMapping(value=command, method=RequestMethod.GET)
		public ModelAndView doAction(
				@RequestParam(value="qnanum") int qnanum,
				@RequestParam(value="pageNumber", required = false) String pageNumber,
				HttpSession session, Principal principal
				) {
			
			//로그인 정보 출력 테스트
			//System.out.println("ID 정보 : "+principal.getName());

			//ModelAndView 객체 생성
			ModelAndView mav = new ModelAndView(); 
			
			if(principal.getName() == null) {
				//로그인 정보가 없으면
				//로그인 페이지로 넘어가서 다시 list.qna 요청으로 돌아오도록 세션 설정
				session.setAttribute("destination", gotoPage);
				
				//다시 원래 페이지로 돌아가기 위해 페이지 정보 넘기기
				mav.addObject("pageNumber",pageNumber);
				//로그인 페이지로 이동하도록 뷰 설정
				mav.setViewName("redirect:/loginForm");
			}else {
				//로그인 정보가 있으면
				
				//dao의 delete메서드로 num값을 넘겨 해당 컬럼 삭제, int로 결과 전송
				int cnt = qdao.deleteQna(qnanum);
				
				if(cnt > 0) {
					//삭제 성공 시
					System.out.println("삭제 성공");
					
					//다시 원래 페이지로 돌아가기 위해 페이지 정보 넘기기
					mav.addObject("pageNumber",pageNumber);
					//qna 목록으로 이동
					mav.setViewName(gotoPage);
				}else {
					//삭제 실패 시
					System.out.println("삭제 실패");

					//다시 원래 페이지로 돌아가기 위해 정보 넘기기
					mav.addObject("pageNumber",pageNumber);
					//qna 목록으로 이동
					mav.setViewName(gotoPage);
				}
			}
			return mav;
		}
	
}
