package com.spring.ex.qna.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.qna.model.QnaBean;
import com.spring.ex.qna.model.QnaDao;

@Controller
public class QnaInsertAnswerController {
	
	//요청 값을 담은 변수
	private final String command = "/qna/admin/insertAnswer.qna";
	//이동할 jsp 페이지 변수
	private String getPage = "/qna/qnaInsertAnswerForm";
	//redirect할 요청 변수
	private String gotoPage = "redirect:/qna/all/list.qna";
	
	@Autowired
	QnaDao qdao;
	
	@RequestMapping(value=command, method=RequestMethod.GET)
	public ModelAndView doAction(
			HttpSession session,
			@RequestParam(value="pageNumber", required = false) String pageNumber,
			@RequestParam(value="qnanum") int qnanum,
			Principal principal
			) {
		
		//로그인 정보 출력 테스트
		//System.out.println(principal.getName());

		//ModelAndView 객체 생성
		ModelAndView mav = new ModelAndView(); 
		
		if(principal.getName() == null) {
			//로그인 정보가 없으면
			//로그인 페이지로 넘어가서 다시 list.qna 요청으로 가도록 세션 설정
			session.setAttribute("destination",gotoPage);
			
			//다시 리스트로 돌아갈 때 원래의 페이지 넘버 넘기기
			mav.addObject("pageNumber",pageNumber);
			//로그인 페이지로 이동하도록 뷰 설정
			mav.setViewName("redirect:/login/loginPage.lg");
		}else {
			//로그인 정보가 있으면
			
			//qnanum을 이용해 해당 글의 내용을 qnaBean에 저장
			QnaBean qnaBean = qdao.getQnaByNum(qnanum);
			
			//mav에 해당 내용이 저장된 qnaBean 넘겨주기
			mav.addObject("qnaBean",qnaBean);
			//답변 작성 폼 페이지로 이동
			mav.setViewName(getPage);
		}
		return mav;
	}
	
	//qnaInsertAnswerForm에서 => 질문등록 버튼 클릭시
		@RequestMapping(value=command, method=RequestMethod.POST)
		public ModelAndView doAction(
				@RequestParam(value="pageNumber", required = false) String pageNumber,
				@RequestParam(value="qnaanswer") String qnaanswer,
				@RequestParam(value="qnanum") String qnanum
				) {
			
			//ModelAndView 객체 생성
			ModelAndView mav = new ModelAndView(); 
			
			//map 객체 생성
			Map<String, String> map = new HashMap<String, String>();
			
			//넘어온 파라미터를 map에 저장
			map.put("qnaanswer", qnaanswer);
			map.put("qnanum", qnanum);
			
			//qdao의 insertQuestion 메서드 결과를 cnt에 저장
			int cnt = qdao.insertAnswer(map);
				
			if(cnt > 0) {
				//update 성공 시
					
				//다시 원래 목록으로 돌아가기 위해 페이지 정보 넘기기
				mav.addObject("pageNumber",pageNumber);				
				//뷰 설정, list.qna를 다시 요청
				mav.setViewName(gotoPage);
			} else {
				//update 실패 시

				//다시 원래 페이지로 돌아가기 위해 페이지 정보 넘기기
				mav.addObject("pageNumber",pageNumber);	
				
				//입력했던 정보가 남아있도록 num과 answer 정보 넘기기
				mav.addObject("qnanum",qnanum);				
				mav.addObject("qnaanswer",qnaanswer);	
				
				//뷰 설정, 다시 qnaInsertAnswerForm으로 돌아감
				mav.setViewName(getPage);
			}//if~else end
			    
		//mav 반환
		return mav;
	}
}
