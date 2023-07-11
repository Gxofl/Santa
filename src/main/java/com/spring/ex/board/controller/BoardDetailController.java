package com.spring.ex.board.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.board.model.BoardBean;
import com.spring.ex.board.model.BoardDao;
import com.spring.ex.heart.board.model.BoardHeartBean;
import com.spring.ex.heart.board.model.BoardHeartDao;


@Controller
public class BoardDetailController {
	
	private final String command = "/board/user/detail.br";
	private final String getPage = "/board/boardDetailView";
	
	@Autowired
	BoardDao bdao;
	
	//좋아요
	@Autowired
	BoardHeartDao bhdao;
	
	//1. boardList.jsp(글제목 클릭) -> detail.br 요청 -> boardDetailView.jsp로 이동
	//2. boardDetailView.jsp에서 댓글등록 클릭시 insert.bcmt을 요청하면, 
	//BoardCommentsInsertController에서 redirect:/detail.br을 거쳐서 이곳으로 넘어옴(command객체, num, 페이지번호)
	@RequestMapping(value=command, method=RequestMethod.GET)
	public ModelAndView detail(@RequestParam("num") int num, 
			@RequestParam(value="pageNumber",required = false) String pageNumber, 
			Principal principal) {
		
		ModelAndView mav = new ModelAndView();
		
		int cnt = bdao.updateCount(num); //해당글 클릭하면 조회수 1 증가하도록
				
		BoardBean board = bdao.getBoardByNum(num);//넘어온 게시글번호에 해당하는 board 레코드 한줄 가져오기
		/* 좋아요 */
		String loginId = principal.getName();
		int getHeartCnt = 0;
		int getHeartTotal = 0;
		try {
			BoardHeartBean bhBean = new BoardHeartBean();
			bhBean.setBoardNum(num);	//게시글번호
			bhBean.setUserId(loginId);		//사용자아이디
			System.out.println();
			//crewBoardCheckHeart : 현재 로그인한 사용자(loginId)의 해당 게시글(num) 좋아요 클릭여부
			//getHeartCnt : 좋아요 여부 전달 1:좋아요클릭, 0:좋아요클릭X
			getHeartCnt = bhdao.boardCheckHeart(bhBean);
			
			//현재 게시글의 좋아요 수 확인
			getHeartTotal = bhdao.getBoardHeartTotal(bhBean);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject("board", board);
		mav.addObject("pageNumber",pageNumber);
		mav.addObject("userid", principal.getName()); //로그인 아이디

		//좋아요
		mav.addObject("getHeartCnt",getHeartCnt);
		mav.addObject("getHeartTotal",getHeartTotal);
		mav.setViewName(getPage);
		
		return mav;
	}
	
	
}
