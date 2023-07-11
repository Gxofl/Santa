package com.spring.ex.crewboard.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.spring.ex.utility.Paging;


@Controller
public class CrewBoardListController {
	
	//private final String command = "/list.bdcr";
	private final String command = "/crewboard/all/list.bdcr";
	private final String getPage = "crewboard/crewboardList";
	
	@Autowired
	CrewBoardDao cbdao;
	
	@Autowired
	CrewDao cdao;
	
	//좋아요
	@Autowired
	CrewBoardHeartDao cbhdao;
		
	/*
	 1. crewInsertForm.jsp에서 크루등록완료 => list.bdcr 요청
	 2. crewboardList.jsp 에서 검색시 => list.bdcr 요청
	 3. 상단의 크루 메뉴 클릭시 요청 => list.bdcr 요청
	 4. crewboardDetailView.jsp에서 목록보기 버튼 클릭시 => list.bdcr 요청
	 5. crewboardList.jsp에서 최신순 클릭시 => list.bdcr 요청
	 6. crewboardList.jsp에서 인기순 클릭시 => list.bdcr 요청
	 6. crewboardList.jsp에서 댓글순 클릭시 => list.bdcr 요청
	 */
	@RequestMapping(value=command)
	public ModelAndView doAction(@RequestParam(value="whatColumn", required=false) String whatColumn,
			@RequestParam(value="keyword", required=false) String keyword,
			@RequestParam(value="pageNumber", required=false) String pageNumber,
			HttpServletRequest request, Principal principal) {
		
		ModelAndView mav = new ModelAndView();
		String loginId = "";
		
		try {
			loginId = principal.getName();
			if(loginId != null) { // 로그인 상태라면
				// 가입한 크루 목록 가져오기
				List<CrewBean> join_crew = cdao.getJoinCrewById(loginId);
				mav.addObject("loginId",loginId);
				mav.addObject("join_crew", join_crew);
				
				// 가입한 정기 크루 가져오기
				List<CrewBean> join_regular_crew = cdao.getJoinRegularCrewById(loginId);
				mav.addObject("join_regular_crew", join_regular_crew);
			}
		}catch(NullPointerException e) {
			
		}
		
		int[] crew_count = new int[4];
		crew_count[0] = cdao.getAllCrewCount(); // 총 크루 수 
		crew_count[1] = cdao.getMountainCrewCount(); // 모집중인 등산 크루수
		crew_count[2] = cdao.getPloggingCrewCount(); // 모집중인 플로깅 크루수
		crew_count[3] = cdao.getFinishCrewCount();	// 모집완료된 크루수
		mav.addObject("crew_count", crew_count);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("whatColumn", whatColumn);
		map.put("keyword", "%"+keyword+"%");
		
		int totalCount = cbdao.getCrewBoardTotalCount(map);
		String url = request.getContextPath()+command;
		Paging pageInfo = new Paging(pageNumber, "5", totalCount, url, whatColumn, keyword, null);
		
		List<CrewBoardBean> crewboard_list = cbdao.getAllCrewboard(map, pageInfo);
		
		/* 좋아요 */
		List<CrewBoardHeartBean> cbhList = new ArrayList<CrewBoardHeartBean>();
		CrewBoardHeartBean cbhBean = new CrewBoardHeartBean();

		try {
			cbhBean.setUserId(loginId);		//사용자아이디
			cbhList = cbhdao.getAllCrewBoardHeart(loginId);
			System.out.println("cbhList : " + cbhList);
			//현재 게시글의 좋아요 수 확인
		}catch (Exception e) {
			e.printStackTrace();
		}
		//좋아요
		mav.addObject("cbhList", cbhList);
		mav.addObject("crewboard_list", crewboard_list);
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName(getPage);
		
		return mav;
	}
}
