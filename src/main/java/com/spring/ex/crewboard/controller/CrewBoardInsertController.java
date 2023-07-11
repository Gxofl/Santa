
package com.spring.ex.crewboard.controller;

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
import com.spring.ex.crewboard.model.CrewBoardBean;
import com.spring.ex.crewboard.model.CrewBoardDao;

@Controller
public class CrewBoardInsertController {
	
	//private final String command = "/insert.bdcr";
	private final String command = "/crewboard/user/insert.bdcr";
	private String getPage ="/crewboard/crewboardInsertForm";
	private String gotoPage = "redirect:/crewboard/all/list.bdcr";
	
	@Autowired
	CrewDao cdao;
	
	@Autowired
	CrewBoardDao cb_dao;
	
	//crewboardList.jsp에서 요청(글쓰기 버튼클릭) -> crewboardInsertForm.jsp
	@RequestMapping(value=command, method=RequestMethod.GET)
	public ModelAndView doAction(Principal principal, HttpServletResponse response) {
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = null;
		
		ModelAndView mav = new ModelAndView();
		
		//내가 만든 크루 정보(로그인한 아이디로) 가져가야한다.
		String getUserId = principal.getName(); 
		List<CrewBean> myCrew = cdao.getCrewById(getUserId);
		
		if(myCrew.size()==0) { // 만든 크루가 없다면
			try {
				out = response.getWriter();
				String alert = "<script>alert('만든 크루가 없습니다'); ";
				alert += "var insertcrew = confirm('크루를 만드시겠습니까?'); ";
				alert += "if(insertcrew == true){location.href='/crew/user/insert.cr'}";
				alert += "else{history.go(-1)};";
				alert += "</script>";
				out.println(alert);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		mav.addObject("loginId", getUserId);
		mav.addObject("myCrew", myCrew);
		mav.setViewName(getPage);
		
		return mav;
	}
	
	//crewboardInsertForm.jsp(등록버튼) -> 크루모집 게시판 등록
	@RequestMapping(value=command, method=RequestMethod.POST)
	public ModelAndView doAction2(
			@ModelAttribute("cbb") @Valid CrewBoardBean cbb
			,BindingResult result, Principal principal,
			HttpServletResponse response) {
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = null;
		
		ModelAndView mav = new ModelAndView();
		String getUserId = principal.getName();			
		// Principal은 자바의 표준 시큐리티 기술로, 로그인이 된 상태라면 계정 정보를 담고있다.
		//내가 만든 크루 정보(로그인한 아이디로) 가져가야한다.
		
		List<CrewBean> myCrew = cdao.getCrewById(getUserId);
		mav.addObject("loginId", getUserId);
		mav.addObject("myCrew", myCrew);
		
		if(result.hasErrors()) {
			//System.out.println("에러 발생");
			
			mav.setViewName(getPage);
		}else {
			
			/* 이미 크루모집 게시글을 쓴 크루라면 alert 띄우기 */
			boolean cb_check = cb_dao.checkCrewboard(cbb.getCrewname());
			if(cb_check == true) { // 이미 게시글을 써서 모집중인 크루였음
				System.out.println("이미 등록된 게시글이 있는 크루");
				//mav.addObject("cbb", cbb);
				try {
					out = response.getWriter();
					out.println("<script>alert('이미 등록된 게시글이 있는 크루 입니다');history.go(-1);</script>");
					out.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				mav.setViewName(getPage);
				
			}else { // 처음 등록하는 크루 모집
				int cnt = -1;
				cnt = cb_dao.insertCrewboard(cbb);
				if(cnt != -1) { //등록 성공
					mav.setViewName(gotoPage);
				}else {//등록 실패
					mav.setViewName(getPage);
				}
			}
		}
		return mav;
	}
}
