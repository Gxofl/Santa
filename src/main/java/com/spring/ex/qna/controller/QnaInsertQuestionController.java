package com.spring.ex.qna.controller;


import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.qna.model.QnaBean;
import com.spring.ex.qna.model.QnaDao;

@Controller
public class QnaInsertQuestionController {
	
	//요청 값을 담은 변수
	private final String command = "/qna/user/insertQuestion.qna";
	//이동할 jsp 페이지 변수
	private String getPage = "/qna/qnaInsertQuestionForm";
	//redirect할 요청 변수
	private String gotoPage = "redirect:/qna/all/list.qna";
	
	//qnaDao 객체 생성
	@Autowired
	QnaDao qdao;
	
	//uploadPath를 가져오기 위한 ServletContext 객체 생성
	@Autowired
	ServletContext servletContext;
	
	//qna 목록 => 질문하기 버튼 클릭 시
	@RequestMapping(value=command, method=RequestMethod.GET)
	public ModelAndView doAction(
			@RequestParam(value="pageNumber", required = false) String pageNumber,
			HttpSession session,Principal principal
			) {
		
		//로그인 정보 출력 테스트
		//System.out.println("ID 정보 : "+principal.getName());

		//ModelAndView 객체 생성
		ModelAndView mav = new ModelAndView(); 
		
		if(principal.getName() == null) {
			//로그인 정보가 없으면
			//로그인 페이지로 넘어가서 다시 insert.qna 요청으로 돌아오도록 세션 설정
			session.setAttribute("destination","redirect:/insertq.qna");
			
			//다시 원래 페이지로 돌아가기 위해 페이지 정보 넘기기
			mav.addObject("pageNumber",pageNumber);
			//로그인 페이지로 이동하도록 뷰 설정
			mav.setViewName("redirect:/loginForm");
		}else {
			//로그인 정보가 있으면

			//다시 원래 페이지로 돌아가기 위해 페이지 정보 넘기기
			mav.addObject("pageNumber",pageNumber);
			//qna 작성 페이지로 이동
			mav.setViewName(getPage);
		}
		return mav;
	}
	
	//qnaInsertQuestionForm에서 => 질문등록 버튼 클릭시
	@RequestMapping(value=command, method=RequestMethod.POST)
	public ModelAndView doAction(
			MultipartHttpServletRequest mtfRequest,
			@RequestParam(value="pageNumber", required = false) String pageNumber,
			@ModelAttribute("qnaBean") @Valid QnaBean qnaBean,
			BindingResult result, HttpServletRequest request,HttpSession session,
			Principal principal
			) {
		
		//ModelAndView 객체 생성
		ModelAndView mav = new ModelAndView(); 

		if(result.hasErrors()) {
			//유효성 검사에 에러가 있으면
			System.out.println("유효성 검사 에러");
			
			//입력했던 정보가 남아있도록 Bean 정보 넘기기
			mav.addObject("qnaBean",qnaBean);
			//뷰 설정, 다시 qnaInsertQuestionForm으로 돌아감
			mav.setViewName(getPage);
		} else {
			//유효성 검사에 에러가 없으면
			System.out.println("유효성 검사 통과");
			
			//파일을 담을 리스트 객체 생성
			List<MultipartFile> fileList = mtfRequest.getFiles("upload");
			
			//웹서버 폴더
			String uploadPath = request.getRealPath("/resources/images/qna");

			//mkdir
			File Folder = new File(uploadPath);
			
			if (!Folder.exists()) {
				try{
				    Folder.mkdir(); //폴더 생성합니다.
				    System.out.println("폴더가 생성되었습니다.");
			        } 
			        catch(Exception e){
				    e.getStackTrace();
				}        
		         }else {
				System.out.println("이미 폴더가 생성되어 있습니다.");
			}
			
			/* 사용자 OS 확인 */
			String osName = System.getProperty("os.name").toLowerCase();
			System.out.println("OS name : " + osName);
	    
			String str = "";
			if (osName.contains("win")) 
			{
				System.out.println("사용자 OS - Window ");
				str = "C:/tempUpload/qna";
			} 

			else if (osName.contains("mac"))   {
			  	System.out.println("사용자 OS - MAC ");
			  	str = "/Users/ol7roeo/Documents/tempUpload/qna"; 
			} 
			
			String filename = "";
			int cnt = 0;
			
			for(int i=0 ; i<fileList.size(); i++) {
				// Bean 변수에 담기위해 파일명 적립
				if(i == fileList.size()-1) { // 마지막 순서
					filename += fileList.get(i).getOriginalFilename();
				}else {
					filename += fileList.get(i).getOriginalFilename()+","; 
				}
				
	            String originFileName = fileList.get(i).getOriginalFilename(); // 원본 파일 명
	           
	            long fileSize = fileList.get(i).getSize(); // 파일 사이즈

	            System.out.println("originFileName : " + originFileName);
	            System.out.println("fileSize : " + fileSize);

	            String safeFile = uploadPath + File.separator + originFileName;
	            
	            File destination = new File(safeFile);
	            File destinateion_local = new File(str + File.separator + originFileName);
	            
	            try {
	            	fileList.get(i).transferTo(destination); // 웹 서버로 업로드
	            	
	            	FileCopyUtils.copy(destination, destinateion_local); // 웹서버 폴더 => 임시폴더로 복사 
	            } catch (IllegalStateException e) {
	                e.printStackTrace();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }//for end
				//현재 로그인한 아이디과 파일명 setter로 Bean에 저장
				qnaBean.setUsersid(principal.getName());
				qnaBean.setQnaimage(filename);
			
			//qdao의 insertQuestion 메서드 결과를 cnt에 저장
			cnt = qdao.insertQuestion(qnaBean);
			
			if(cnt > 0) {
				//insert 성공 시
				
				//다시 목록으로 가기 위해 정보 넘기기
				mav.addObject("pageNumber",pageNumber);				
				//뷰 설정, list.qna를 다시 요청
				mav.setViewName(gotoPage);
			} else {
				//insert 실패 시

				//다시 원래 페이지로 돌아가기 위해 정보 넘기기
				mav.addObject("pageNumber",pageNumber);				
				mav.addObject("qnaBean",qnaBean);
				
				//뷰 설정, 다시 qnaInsertQForm으로 돌아감
				mav.setViewName(getPage);
			}//if~else end
		}//if~else end
		    
		//mav 반환
		return mav;
	}
	
}
