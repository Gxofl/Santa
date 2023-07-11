package com.spring.ex.crewboard.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.crew.model.CrewBean;
import com.spring.ex.crew.model.CrewDao;
import com.spring.ex.crewboard.model.CrewBoardBean;
import com.spring.ex.crewboard.model.CrewBoardDao;
import com.spring.ex.heart.crewboard.model.CrewBoardHeartBean;
import com.spring.ex.heart.crewboard.model.CrewBoardHeartDao;


@Controller
public class CrewBoardDetailController { 
	
	//private final String command = "/detail.bdcr";
	
	private final String command = "/crewboard/user/detail.bdcr";
	private String getPage = "/crewboard/crewboardDetailView";
	
	@Autowired
	CrewBoardDao cbdao;
	
	@Autowired
	CrewDao cdao;
	
	//좋아요
	@Autowired
	CrewBoardHeartDao cbhdao;
	
	//crewboardList.jsp(제목클릭)에서 요청(num, pageNumber) => crewboardDetailView.jsp
	@RequestMapping(value=command)
	public ModelAndView doAction(@RequestParam(value="num") int num, 
			@RequestParam(value="pageNumber", required=false) String pageNumber,
			@RequestParam(value="crewname", required=false) String crewname,
			Principal principal,
			HttpServletResponse response) {
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = null;
		
		ModelAndView mav = new ModelAndView();
	
		mav.addObject("pageNumber", pageNumber); 		// 페이지
		mav.addObject("loginId", principal.getName()); // 로그인 아이디
		
		String loginId = principal.getName();
		List<CrewBean> join_crew = cdao.getJoinCrewById(loginId); // 가입한 크루
		mav.addObject("join_crew", join_crew);
		
		// 가입한 정기 크루 가져오기
		List<CrewBean> join_regular_crew = cdao.getJoinRegularCrewById(loginId);
		mav.addObject("join_regular_crew", join_regular_crew);
		
		CrewBoardBean cbb = cbdao.getCrewboardByNum(num);
		if(num == 0) { // 마이페이지에서 모집글 눌렀을때
			cbb = cbdao.getCrewboardByCrewname(crewname);
			if(cbb == null) { // 모집글 작성 안했을때
				try {
					out = response.getWriter();
					out.println("<script>alert('작성된 모집글이 없습니다');history.go(-1);</script>");
					out.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		/* 좋아요 */
		int getHeartCnt = 0;
		int getHeartTotal = 0;
		try {
			CrewBoardHeartBean cbhBean = new CrewBoardHeartBean();
			cbhBean.setCrewboardNum(num);	//게시글번호
			cbhBean.setUserId(loginId);		//사용자아이디
			
			//crewBoardCheckHeart : 현재 로그인한 사용자(loginId)의 해당 게시글(num) 좋아요 클릭여부
			//getHeartCnt : 좋아요 여부 전달 1:좋아요클릭, 0:좋아요클릭X
			getHeartCnt = cbhdao.crewBoardCheckHeart(cbhBean);
			
			//현재 게시글의 좋아요 수 확인
			getHeartTotal = cbhdao.getcrewBoardHeartTotal(cbhBean);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		mav.addObject("cbb", cbb);
		//좋아요
		mav.addObject("getHeartCnt",getHeartCnt);
		mav.addObject("getHeartTotal",getHeartTotal);
		mav.setViewName(getPage);
		return mav;
	}
	
}
