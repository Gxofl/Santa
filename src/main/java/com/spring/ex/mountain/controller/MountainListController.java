package com.spring.ex.mountain.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.mountain.model.MountainBean;
import com.spring.ex.mountain.model.MountainDao;
import com.spring.ex.stamp.model.StampBean;
import com.spring.ex.stamp.model.StampDao;
import com.spring.ex.utility.Paging;

@Controller
public class MountainListController {

	//요청 값을 담은 변수
	private final static String command = "/mountain/all/list.mnt";
	//이동할 jsp 페이지 변수
	private String getPage = "/mountain/mountainList";
	
	@Autowired
	MountainDao mdao;
	
	//인기 산을 가져오기 위한 stampdao
	@Autowired
	StampDao sdao;
	
		//사용자-상단 메뉴에서 산별정보 클릭 시 요청 발생=>mountainList.jsp로 이동
		@RequestMapping(value=command)
		public ModelAndView doAction(
				@RequestParam(value="category", required=false) String category,
				@RequestParam(value="whatColumn", required=false) String whatColumn,
				@RequestParam(value="keyword", required=false) String keyword,
				@RequestParam(value="pageNumber", required = false) String pageNumber,
				HttpServletRequest request, Principal principal
				) {
			
			System.out.println("category : "+category);
			
			//ModelAndView 객체 생성
			ModelAndView mav = new ModelAndView();
			
			//검색을 위해 map 객체에 검색 카테고리와 검색 키워드 값 저장
			Map<String, String> map = new HashMap<String, String>();
			map.put("whatColumn", whatColumn);
			map.put("category", category);
			map.put("keyword", "%"+keyword+"%");
			
			//전체 튜플 갯수를 구해 변수에 저장, map에 저장된 조건으로도 검색
			int totalCount = mdao.getTotalCount(map);
			
			System.out.println("tc:"+totalCount);
					
			//url을 변수에 저장
			String url = request.getContextPath() + command;
			
			//페이지 정보 가져오기
			Paging pageInfo = new Paging(pageNumber, "5", totalCount, url, whatColumn, keyword, null);
			System.out.println("pageInfo : "+pageInfo);
			
			//모든 mountain 목록을 list 객체에 저장, map과 pageInfo로 조건 설정
			List<MountainBean> mountainList = mdao.getAllMountain(map, pageInfo);
			System.out.println("mountainList : "+mountainList);
			
			//높이 평균 구하기
			//무조건 모든 칼럼의 리스트를 넘기는 메서드
			List<MountainBean> allMountain = mdao.getAllMountainFirst();
			
			int total=0;
			for(MountainBean result : allMountain) {
				total = total + Integer.parseInt(result.getMountainheight());
				System.out.println("total 누적 : "+total);
			}
			int avg = total/allMountain.size();
			
			//인기산을 가져오기 위한 인증 리스트(산 번호, 인증 갯수)
			List<StampBean> certList  = sdao.getCertCountByMountainNum();
			
			//mav에 저장해서 넘길 값 설정
			mav.addObject("certList", certList);
			mav.addObject("mountainList", mountainList);
			mav.addObject("avg", avg);
			mav.addObject("pageInfo", pageInfo);
			mav.addObject("principal", principal);
			
			//뷰 설정
			mav.setViewName(getPage);
			
			//mav 반환, mountainList.jsp로 이동
			return mav;
		}//doAction end
	
}//class
