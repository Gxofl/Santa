package com.spring.ex.board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class BoardUpdateController {
	
	private final String command = "/board/user/update.br";
	private String getPage = "/board/boardUpdateForm";
	private String gotoPage = "redirect:/board/user/detail.br"; //detail로 이동
	
	@Autowired
	BoardDao bdao;
	
	@Autowired
	ServletContext servletContext;
	
	
	//boardDetailView.jsp(수정 버튼 클릭) -> update.br (get방식)요청 -> boardUpdateForm.jsp로 이동
	@RequestMapping(value=command, method = RequestMethod.GET)
	public String update(@RequestParam("num") int num, Model model,
			@RequestParam("pageNumber") int pageNumber) {
		
		System.out.println("수정 num: "+num);
		
		BoardBean board = bdao.getBoardByNum(num);
		
		model.addAttribute("board", board);
		model.addAttribute("pageNumber",pageNumber);
		
		return getPage;
	}
	
	
	//boardUpdateForm.jsp(수정 버튼 클릭) -> detail.br (post방식)요청 -> 수정 성공 -> list.br 재요청
	@RequestMapping(value=command, method = RequestMethod.POST)
	public ModelAndView update(@RequestParam(value="pageNumber",required = false) String pageNumber,
			@ModelAttribute("board") @Valid BoardBean board, BindingResult result) {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("num", board.getNum()); //번호도 보내줘야함
		mav.addObject("pageNumber", pageNumber);
		
		if(board.getImage().equals("")) { //업로드할 이미지를 선택하지 않았다면
			board.setImage(board.getUpload2()); //기존 이미지에 넣은걸 다시 가져와야한다.
		} //유효성검사에 에러가 나서 다시 수정폼으로 가게되면, 기존 이미지가 폼에서 사라지는 문제도 해결됨
		
		
		System.out.println("result.hasErrors(): "+result.hasErrors());
		if(result.hasErrors()) { //에러 있음
			mav.setViewName(getPage);
		}
		else {//에러 없음
			
			String filePath = servletContext.getRealPath("/resources/images/board");
			File deleteImage = new File(filePath +File.separator+board.getUpload2()); //삭제하려는 이미지
			File destination = new File(filePath + File.separator + board.getUpload().getOriginalFilename()); //수정하려는(업로드할) 이미지
			
			int cnt = bdao.updateBoard(board);
			System.out.println("수정 cnt: "+ cnt);
			
			if(cnt > -1) {  // DB 테이블에서 수정 성공
				boolean flag = deleteImage.delete(); //기존이미지 삭제
				System.out.println("삭제: " +flag);
				
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
				
				MultipartFile multi = board.getUpload();
				try {
					multi.transferTo(destination); //진짜 업로드					
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				mav.setViewName(gotoPage);
			}else { //수정 실패
				mav.setViewName(getPage);
			}			
		}		
		return mav;	
	}//
	
	
}
