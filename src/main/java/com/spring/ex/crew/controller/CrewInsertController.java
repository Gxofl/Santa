package com.spring.ex.crew.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.crew.model.CrewBean;
import com.spring.ex.crew.model.CrewDao;

@Controller
public class CrewInsertController {
	//private final String command = "/insert.cr";
	private final String command = "/crew/user/insert.cr";
	private String getPage = "/crew/crewInsertForm";
	private String gotoPage = "redirect:/crewboard/all/list.bdcr"; //list없어서 안나오는 상태
	
	@Autowired
	CrewDao cdao;
	
	//메뉴에서 크루신청 클릭시 -> crewInsertForm.jsp로감
	@RequestMapping(value=command, method=RequestMethod.GET)
	public ModelAndView doAction(Principal principal) {
		
		ModelAndView mav = new ModelAndView();
		//1. 비회원일때
		
		//2. 회원일때
		String loginId = principal.getName();
		mav.addObject("loginId", loginId);
		mav.setViewName(getPage);
		return mav;
	}
	
	//crewInsertForm.jsp(등록하기 클릭) -> list.bdcr 요청
	@RequestMapping(value=command, method=RequestMethod.POST)
	public ModelAndView doAction(@ModelAttribute("cb") @Valid CrewBean cb,
			BindingResult result,
			Principal principal, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView();
		String loginId = principal.getName();
		mav.addObject("loginId", loginId);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = null;
		
		if(result.hasErrors()) { //유효성 검사 에러일때
			mav.setViewName(getPage);
		}else {
			/* 이미 만든 정기 크루가 있는지 확인 */
			boolean flag = false;
			if(cb.getLarge() == 2) { // 선택한 구분1이 정기크루라면
				List<CrewBean> myCrew = cdao.getCrewById(loginId); // 아이디로 내가 만든 크루목록 가져옴
				for(CrewBean crew : myCrew) {
					if(crew.getLarge() == 2 && cb.getSmall().equals(crew.getSmall())) { // 정기크루고 선택한 구분2와 같다면
						flag = true;
					}
				}
			}
			
			if(flag == true) { // 이미 만든 정기 크루가 있음
				// alert 띄우기
				try {
					String small = "";
					if(cb.getSmall().equals("M")){
						small = "등산";
					}else {
						small = "플로깅";
					}
					out = response.getWriter();
					out.println("<script>alert('이미 등록한 정기 "+small+" 크루가 있습니다');history.go(-1);</script>");
					out.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				mav.setViewName(getPage);
				
			}else {
				int cnt = -1;
				cnt = cdao.insertCrew(cb);
				if(cnt != -1) {
					//크루 모집게시판으로 감
					mav.setViewName(gotoPage);
				}else {
					mav.setViewName(getPage);
				}
			}
		}
		return mav;
	}
}
