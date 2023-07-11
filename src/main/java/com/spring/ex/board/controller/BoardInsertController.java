package com.spring.ex.board.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.board.model.BoardBean;
import com.spring.ex.board.model.BoardDao;

@Controller
public class BoardInsertController {
	
	private final String command = "/board/user/insert.br";
	private String getPage = "/board/boardInsertForm";
	private String gotoPage = "redirect:/board/all/list.br";
	
	@Autowired
	BoardDao bdao;
	
	@Autowired
	ServletContext servletContext; 
	
	
	//boardList.jsp(목록에서 글쓰기 버튼 클릭) -> insert.br 요청(get방식) -> boardInsertForm.jsp로 이동
	@RequestMapping(value=command, method = RequestMethod.GET) //폼 요청
	public ModelAndView insert(@RequestParam(value="pageNumber",required = false) String pageNumber,
			Principal principal) {
		
		ModelAndView mav = new ModelAndView();
		
		String userId = principal.getName(); 
		//List<BoardBean> board = bdao.getBoardById(userId);
		
		mav.addObject("userId", userId);
		mav.addObject("pageNumber", pageNumber);
		mav.setViewName(getPage);
		return mav;
	}
	
	
	//boardInsertForm.jsp(글쓰기 버튼 클릭) -> detail.br 요청(post방식) -> 삽입성공시 boardList.jsp로 이동
	@RequestMapping(value=command, method = RequestMethod.POST)
	public ModelAndView insert(@ModelAttribute("board") @Valid BoardBean board,
			BindingResult result, HttpServletRequest request, 
			Principal principal) {
		
		String uploadPath = servletContext.getRealPath("/resources/images/board");
		System.out.println("uploadPath: "+uploadPath);
		File destination = new File(uploadPath + File.separator + board.getUpload().getOriginalFilename());
		System.out.println("destination: "+destination);
		MultipartFile multi = board.getUpload();
		
		ModelAndView mav = new ModelAndView();
		board.setUserid(principal.getName());
		String userId = principal.getName();	
		mav.addObject("userId", userId);
		
		//String str = "c:/tempUpload/board";
		//File destination_local = new File(str + File.separator + multi.getOriginalFilename());
		
		System.out.println("result.hasErrors(): "+result.hasErrors()); 
		if(result.hasErrors()) { //에러 있음
			mav.setViewName(getPage);
		}
		else { //에러 없음
			
			/* 사용자 OS 확인 */
			String osName = System.getProperty("os.name").toLowerCase();
			System.out.println("OS name : " + osName);
	    
			String str = "";
			if (osName.contains("win")) 
			{
				System.out.println("사용자 OS - Window ");
				str = "C:/tempUpload/board";
			} 

			else if (osName.contains("mac"))   {
			  	System.out.println("사용자 OS - MAC ");
			  	str = "/Users/ol7roeo/Documents/tempUpload/board"; 
			} 			
			
			File destination_local = new File(str + File.separator + multi.getOriginalFilename());
			
			int cnt = bdao.insertBoard(board);
			System.out.println("cnt: "+cnt);
			
			if(cnt != -1) { //삽입 성공
				try {
					multi.transferTo(destination);
					FileCopyUtils.copy(destination, destination_local);
					
				} catch (IllegalStateException e) {				
					e.printStackTrace();
				} catch (IOException e) {				
					e.printStackTrace();
				} 
				mav.setViewName(gotoPage);
			}else { //삽입 실패
				mav.setViewName(getPage);	
			}			
		}//else
		
		return mav;
	}//
	
	
	
}
