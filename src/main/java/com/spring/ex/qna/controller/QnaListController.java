package com.spring.ex.qna.controller;

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

import com.spring.ex.qna.model.QnaBean;
import com.spring.ex.qna.model.QnaDao;
import com.spring.ex.utility.Paging;

@Controller
public class QnaListController {
	
	//요청 값을 담은 변수
	private final String command = "/qna/all/list.qna";
	//이동할 jsp 페이지 변수
	private String getPage = "/qna/qnaList";
	
	@Autowired
	QnaDao qdao;
	
	//사용자-상단 메뉴에서 Q&A 클릭 시 요청 발생=>qnaList.jsp로 이동
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
		int totalCount = qdao.getQnaTotalCount(map);
		
		System.out.println("tc:"+totalCount);
				
		//url을 변수에 저장
		String url = request.getContextPath() +"/"+ command;
		
		//페이지 정보 가져오기
		Paging pageInfo = new Paging(pageNumber, "5", totalCount, url, whatColumn, keyword, null);
		System.out.println("pageInfo : "+pageInfo);
		
		
		//모든 Qna 목록을 list 객체에 저장, map과 pageInfo로 조건 설정
		List<QnaBean> qnaLists = qdao.getAllQna(map, pageInfo);
		System.out.println("qnaLists : "+qnaLists);
		
		//mav에 저장해서 넘길 값 설정
		mav.addObject("qnaLists", qnaLists);
		mav.addObject("pageInfo", pageInfo);
		mav.addObject("principal", principal);
		
		//뷰 설정
		mav.setViewName(getPage);
		
		//mav 반환, qnaList.jsp로 이동
		return mav;
	}
}