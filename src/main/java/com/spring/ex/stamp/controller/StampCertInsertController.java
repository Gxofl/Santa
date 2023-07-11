package com.spring.ex.stamp.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.mountain.model.MountainBean;
import com.spring.ex.mountain.model.MountainDao;
import com.spring.ex.stamp.model.StampBean;
import com.spring.ex.stamp.model.StampDao;

@Controller
public class StampCertInsertController {

	//요청 값을 담은 변수
			private final String command = "/stamp/user/insert.stp";
			//이동할 jsp 페이지 변수
			private String getPage = "stamp/stampCertInsertForm";
			//redirect할 요청 변수
			private String gotoPage = "redirect:/stamp/user/list.stp";
			
			//MountainDao 객체 생성
			@Autowired
			StampDao sdao;

			@Autowired
			MountainDao mdao;
			
			//uploadPath를 가져오기 위한 ServletContext 객체 생성
			@Autowired
			ServletContext servletContext;
			
			//stamp 목록 => 완등인증 버튼 클릭 시
			@RequestMapping(value=command, method=RequestMethod.GET)
			public ModelAndView doAction(
					HttpSession session,Principal principal
					) {
				
				//로그인 정보 출력 테스트
				System.out.println("ID 정보 : "+principal.getName());

				//ModelAndView 객체 생성
				ModelAndView mav = new ModelAndView(); 
				
				if(principal.getName() == null) {
					//로그인 정보가 없으면
					//로그인 페이지로 넘어가서 다시 insert.qna 요청으로 돌아오도록 세션 설정
					session.setAttribute("destination","redirect:/stamp/user/list.stp");
					
					//로그인 페이지로 이동하도록 뷰 설정
					mav.setViewName("redirect:/loginForm");
				}else {
					//로그인 정보가 있으면
					
					List<MountainBean> mountainNames = mdao.getAllMountainName();
					
					System.out.println("가져온 이름 확인 : "+mountainNames.get(1).getMountainnum());
					
					//mountain_num과 mountain_name을 넘겨줌
					mav.addObject("mountainNames",mountainNames);
					//인증 신청 작성 페이지로 이동
					mav.setViewName(getPage);
				}
				return mav;
			}
			
			//stampCertInsert에서 => 등록 버튼 클릭시
			@RequestMapping(value=command, method=RequestMethod.POST)
			public ModelAndView doAction(
					@ModelAttribute("stampBean") @Valid StampBean stampBean,
					BindingResult result, HttpServletRequest request,HttpSession session,
					Principal principal
					) {
				
				System.out.println("mountainnum : "+stampBean.getMountainnum());
				
				//ModelAndView 객체 생성
				ModelAndView mav = new ModelAndView();
				//select-box 안을 채울 내용 호출
				List<MountainBean> mountainNames = mdao.getAllMountainName();
				
				//현재 로그인된 아이디를 bean에 저장
				stampBean.setUsersid(principal.getName());

				if(result.hasErrors()) {
					//유효성 검사에 에러가 있으면
					System.out.println("유효성 검사 에러");
					
					//mountain_num과 mountain_name을 넘겨줌
					mav.addObject("mountainNames",mountainNames);
					//입력했던 정보가 남아있도록 정보 넘기기
					mav.addObject("stampBean", stampBean);
					//뷰 설정, 다시 mountainInsertForm으로 돌아감
					mav.setViewName(getPage);
				} else {
					//유효성 검사에 에러가 없으면
					System.out.println("유효성 검사 통과");

					//파일 업로드 start
					//웹서버 폴더
					String uploadPath = request.getRealPath("/resources/images/stamp/cert");
					System.out.println("uploadPath:"+uploadPath);
					
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
						str = "C:/tempUpload/stamp/cert";
					} 

					else if (osName.contains("mac"))   {
					  	System.out.println("사용자 OS - MAC ");
					  	str = "/Users/ol7roeo/Documents/tempUpload/stamp/cert"; 
					} 
					

					File destination = new File(uploadPath+File.separator+stampBean.getUpload().getOriginalFilename());
					System.out.println("destination: "+destination);
					
					MultipartFile multi = stampBean.getUpload();
					
					File destination_local = new File(str + File.separator + multi.getOriginalFilename());

					try {
						multi.transferTo(destination);
						FileCopyUtils.copy(destination, destination_local);
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					//파일 업로드 end
					
					stampBean.setStampapply(0);//인증 상태 대기
					stampBean.setStampreject(0);//거절 사유 없음
					
					//인증 신청한 회원의 승인 상태인 인증 내역 조회 (아이디, 산번호, 인증상태)
					List<StampBean> applyList = sdao.getApplyListById(stampBean.getUsersid());
					
					//신청한 인증 정보가 이미 목록에 존재하는지 반복해서 비교, 만약 있다면 stampBaen에 거절과 거절 사유 저장 후 반복문 나가기
					for(int i = 0;i<applyList.size();i++) {
						if(stampBean.getMountainnum() == applyList.get(i).getMountainnum()) {
							stampBean.setStampapply(2);//인증 상태를 거절로
							stampBean.setStampreject(2); //거절 사유를 중복 인증 신청으로
							break;
						}
					}
					
					//결과를 cnt에 저장
					int cnt = sdao.insertStampCert(stampBean);
					
					if(cnt > 0) {
						//insert 성공 시
						System.out.println("insert성공");
						
						//뷰 설정, list.stp를 다시 요청
						mav.setViewName(gotoPage);
					} else {
						//insert 실패 시
						System.out.println("insert실패");
						mav.addObject("stampBean",stampBean);
						
						//뷰 설정, 다시 stmapCertInsertForm으로 돌아감
						mav.setViewName(getPage);
					}//if~else end
				}//if~else end
				    
				//mav 반환
				return mav;
			}
}
 