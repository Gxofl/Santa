package com.spring.ex.stamp.controller;

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

import com.spring.ex.stamp.model.CertBean;
import com.spring.ex.stamp.model.StampBean;
import com.spring.ex.stamp.model.StampDao;
import com.spring.ex.utility.Paging;

@Controller
public class StampAdminListController {
	//요청 값을 담은 변수
		private final static String command = "/stamp/admin/adminlist.stp";
		//이동할 jsp 페이지 변수
		private String getPage = "/stamp/stampAdminList";
		
		@Autowired
		StampDao sdao;
		
			//관리자 메뉴에서 스탬프 관리 클릭 시 요청 발생=>mountainList.jsp로 이동
			@RequestMapping(value=command)
			public ModelAndView doAction(
					@RequestParam(value="whatColumn", required=false) String whatColumn,
					@RequestParam(value="keyword", required=false) String keyword,
					@RequestParam(value="pageNumber", required = false) String pageNumber,
					HttpServletRequest request, Principal principal
					) {
				
				//ModelAndView 객체 생성
				ModelAndView mav = new ModelAndView();
				
				//검색을 위해 map 객체에 검색 카테고리와 검색 키워드 값 저장
				Map<String, String> map = new HashMap<String, String>();
				map.put("whatColumn", whatColumn);
				map.put("keyword", "%"+keyword+"%");
				
				//전체 튜플 갯수를 구해 변수에 저장, map에 저장된 조건으로도 검색
				int totalCount = sdao.getStampTotalCount(map); 
				System.out.println("tc:"+totalCount);
						
				//url을 변수에 저장
				String url = request.getContextPath() + command;
				//페이지 정보 가져오기
				Paging pageInfo = new Paging(pageNumber, "5", totalCount, url, whatColumn, keyword, null);
				System.out.println("pageInfo : "+pageInfo);
				
				//모든 목록을 list 객체에 저장, map과 pageInfo로 조건 설정
				List<StampBean> stampList = sdao.getAllStamp(map, pageInfo);
				System.out.println("mountainLists : "+stampList);
				
				//스탬프 갯수를 출력하기 위해 전체 컬럼에서 id 기준으로 apply가 승인상태(1)인 컬럼 갯수 그룹으로 리스트에 저장
				List<StampBean> applyCount = sdao.getApplyCountGroupById();
				System.out.println("applyCount"+applyCount);
				int zero = 1;
				if(applyCount.isEmpty()) {
					System.out.println("applyCount null");
					zero = 0;
				}
				
				//mav에 저장해서 넘길 값 설정
				mav.addObject("zero", zero);
				mav.addObject("applyCount", applyCount);
				mav.addObject("stampList", stampList);
				mav.addObject("pageInfo", pageInfo);
				mav.addObject("principal", principal);
				
				//뷰 설정
				mav.setViewName(getPage);
				
				//mav 반환, stampCertList.jsp로 이동
				return mav;
			}//doAction end
}
