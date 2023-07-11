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
import com.spring.ex.utility.Paging;

@Controller
public class MountainDetailController {

		//요청 값을 담은 변수
		private final static String command = "/mountain/all/detail.mnt";
		//이동할 jsp 페이지 변수
		private String getPage = "/mountain/mountainDetailView";
		
		@Autowired
		MountainDao mdao;
		
			//사용자-상단 메뉴에서 산별정보 클릭 시 요청 발생=>mountainList.jsp로 이동
			@RequestMapping(value=command)
			public ModelAndView doAction(
					@RequestParam(value="mountainnum") int mountainnum,
					@RequestParam(value="pageNumber", required = false) String pageNumber,
					HttpServletRequest request, Principal principal
					) {
				
				//ModelAndView 객체 생성
				ModelAndView mav = new ModelAndView();
				
				//높이 평균 구하기
				//무조건 모든 칼럼의 리스트를 넘기는 메서드
				List<MountainBean> allMountain = mdao.getAllMountainFirst();
				
				int total=0;
				for(MountainBean result : allMountain) {
					total = total + Integer.parseInt(result.getMountainheight());
					System.out.println("total 누적 : "+total);
				}
				int avg = total/allMountain.size();
				
				//전체 튜플 갯수를 구해 변수에 저장, map에 저장된 조건으로도 검색
				MountainBean mountainBean = mdao.getMountainByNum(mountainnum);
				
				//mav에 저장해서 넘길 값 설정
				mav.addObject("mountainBean", mountainBean);
				mav.addObject("avg", avg);
				mav.addObject("pageNumber", pageNumber);
				mav.addObject("principal", principal);
				
				//뷰 설정
				mav.setViewName(getPage);
				
				//mav 반환, mountainList.jsp로 이동
				return mav;
			}//doAction end
	
}
