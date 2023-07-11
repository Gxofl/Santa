package com.spring.ex.admin.crew.controller;

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
import com.spring.ex.graph.model.CrewGraphBean;
import com.spring.ex.graph.model.CrewGraphDao;
import com.spring.ex.utility.Paging;

@Controller
public class CrewAdminController {
	private final String command = "/crew/admin/list.cr";
	private String getPage = "admin/crew/adminAllcrew";
	
	@Autowired
	CrewDao cdao;
	
	@Autowired
	CrewGraphDao cg_dao;
	
	// 관리자 페이지 크루 통계
	@RequestMapping(command)
	public ModelAndView doAction() {
		ModelAndView mav = new ModelAndView();
		
		CrewGraphBean cgb = new CrewGraphBean();
		int totalcrew = cg_dao.getAllCrewCount2();
		int onedaycrew = cg_dao.getOneDayCrewCount();
		int regularcrew = cg_dao.getRegularCrewCount();
		int mountaincrew = cg_dao.getMountainCrewCount2();
		int ploggingcrew = cg_dao.getPloggingCrewCount2();
		
		cgb.setTotalcrew(totalcrew);
		cgb.setOnedaycrew(onedaycrew);
		cgb.setRegularcrew(regularcrew);
		cgb.setMountaincrew(mountaincrew);
		cgb.setPloggingcrew(ploggingcrew);
		
		mav.addObject("list", cgb);
		mav.setViewName(getPage);
		return mav;
	}
	
	
	/* ------------------------------------------------------------- */
	
	
	// 관리자 페이지 일일크루
	private final String command1 = "/crew/admin/list1.cr";
	private final String getPage1 = "admin/crew/admincrew1";
	
	@RequestMapping(command1)
	public ModelAndView doAction(
			@RequestParam(value="small", required=false) String small,
			@RequestParam(value="whatColumn", required=false) String whatColumn,
			@RequestParam(value="keyword", required=false) String keyword,
			@RequestParam(value="pageNumber", required=false) String pageNumber,
			HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		
		// 검색창은 크루명, 크루장
		// 모집중 / 모집완료
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("small", small); // 전체 or 등산 or 플로깅
		map.put("whatColumn", whatColumn); // 크루장 or 크루명
		map.put("keyword", "%"+keyword+"%"); // 검색어
		
		if(small!=null) {
			if(small.equals("M")) {
				mav.addObject("small", small);
			}else if(small.equals("P")) {
				mav.addObject("small", small);
			}
		}
		
		int totalCount = cdao.get1DayCrewTotalCount(map);
		String url = request.getContextPath()+command1;
		
		Paging pageInfo = new Paging(pageNumber, "5", totalCount, url, whatColumn, keyword, null);
		
		List<CrewBean> crewList = cdao.get1DayCrew(map, pageInfo);
		mav.addObject("crewList", crewList);
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName(getPage1);
		return mav;
	}
	
	
	/* ------------------------------------------------------------- */
	
	//관리자 페이지 정기크루
	private final String command2 = "/crew/admin/list2.cr";
	private final String getPage2 = "admin/crew/admincrew2";
	
	@RequestMapping(command2)
	public ModelAndView doAction2(
			@RequestParam(value="small", required=false) String small,
			@RequestParam(value="whatColumn", required=false) String whatColumn,
			@RequestParam(value="keyword", required=false) String keyword,
			@RequestParam(value="pageNumber", required=false) String pageNumber,
			HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		
		// 검색창은 크루명, 크루장
		// 모집중 / 모집완료
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("small", small); // 전체 or 등산 or 플로깅
		map.put("whatColumn", whatColumn); // 크루장 or 크루명
		map.put("keyword", "%"+keyword+"%"); // 검색어
		
		if(small!=null) {
			if(small.equals("M")) {
				mav.addObject("small", small);
			}else if(small.equals("P")) {
				mav.addObject("small", small);
			}
		}
		
		int totalCount = cdao.getRegularCrewTotalCount(map);
		String url = request.getContextPath()+command2;
		
		Paging pageInfo = new Paging(pageNumber, "5", totalCount, url, whatColumn, keyword, null);
		
		List<CrewBean> crewList = cdao.getRegularCrew(map, pageInfo);
		mav.addObject("pageInfo", pageInfo);
		mav.addObject("crewList", crewList);
		mav.setViewName(getPage2);
		return mav;
	}
}
