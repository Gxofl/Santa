package com.spring.ex.board.controller;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.board.model.BoardBean;
import com.spring.ex.board.model.BoardDao;

@Controller
public class BoardDeleteController {
	
	//private final String command =   "delete.br";
	private final String command = "/board/user/delete.br";
	private final String gotoPage = "redirect:/board/all/list.br";
	
	@Autowired
	BoardDao bdao;
	
	@Autowired
	ServletContext servletContext;
	
	
	//boardDetailView.jsp에서 '삭제'버튼 클릭 -> confirm에서 '확인'버튼을 클릭 -> 번호와 페이지번호를 갖고 옴
	//-> 삭제 성공시 목록보기로 컨트롤러 재요청함
	@RequestMapping(command) 
	public ModelAndView delete(@RequestParam("num") int num, Model model,
			@RequestParam("pageNumber") int pageNumber) {
		ModelAndView mav = new ModelAndView();
		System.out.println("삭제할 num: "+num);		
		
		BoardBean board = bdao.getBoardByNum(num); //board에는 이미지의 파일명도 들어 있음
		//삭제하기 전에 Bean을 가져오는걸 먼저 해야함
		
		int cnt = bdao.deleteBoard(num); //DB 삭제
		
		if(cnt != -1) {
			System.out.println("DB 삭제 성공");
			
			//resources 폴더의 경로
			String deletePath = servletContext.getRealPath("/resources/images/board"); 
			System.out.println("deletePath: " +deletePath+"\\"+board.getImage()); 
			
			
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
			
			
			//이미지 파일 가져오는 경로
			File prdImage = new File(deletePath+ File.separator+ board.getImage());
			File prdImage_local = new File(str+ File.separator+ board.getImage());
			
			//긴 파일 경로를 File 객체에 담아서 파일로 만든다
			boolean flag = prdImage.delete(); // 웹서버 폴더 파일 삭제
			boolean flag2 = prdImage_local.delete(); // 임시폴더 파일 삭제
			
			if(flag == true) {
				System.out.println("이미지 삭제 성공");
			}
			
			if(flag2 == true) {
				System.out.println("임시 폴더 이미지 삭제 성공");
			}
			
		}else { // DB 삭제 실패
			System.out.println("DB 삭제 실패");
		}
		
		mav.addObject("pageNumber", pageNumber);	
		mav.addObject("num", num);	
		mav.setViewName(gotoPage);
		return mav;
	}//
	
	
}
