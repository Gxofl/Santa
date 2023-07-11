package com.spring.ex.products.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.products.model.ProductsBean;
import com.spring.ex.products.model.ProductsDao;

@Controller
public class ProductsInsertController {
	
	//private final String command = "/insert.prd";
	private final String command = "/products/user/insert.prd";
	private String getPage = "/products/productsInsertForm";
	private String gotoPage = "redirect:/products/all/list.prd";
	
	@Autowired
	ProductsDao pdao;
	
	@Autowired
	ServletContext servletContext;
	
	@RequestMapping(value=command, method=RequestMethod.GET)
	public ModelAndView doAction() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(getPage);
		return mav;
	}
	
	//productsInsertForm.jsp에서 등록버튼 클릭시 요청
	@RequestMapping(value=command, method=RequestMethod.POST)
	public ModelAndView doAction(
			@ModelAttribute("pb") @Valid ProductsBean pb,
			BindingResult result,
			MultipartHttpServletRequest mtfRequest,
			HttpServletRequest request,
			Principal principal) {
		ModelAndView mav = new ModelAndView();
		pb.setSeller(principal.getName()); // 아이디 챙겨가기
		
		
		if(result.hasErrors()) {
			mav.setViewName(getPage);
		}else {
			/* 다중 파일 업로드 */
			List<MultipartFile> fileList = mtfRequest.getFiles("upload");
			String uploadpath = request.getRealPath("/resources/images/products"); // 웹 서버 폴더
			
			/* 사용자 OS 확인 */
			String osName = System.getProperty("os.name").toLowerCase();
			System.out.println("OS name : " + osName);
	    
			String str = "";
			if (osName.contains("win")) 
			{
				System.out.println("사용자 OS - Window ");
				str = "C:/tempUpload/products";
			} 

			else if (osName.contains("mac"))   {
			  	System.out.println("사용자 OS - MAC ");
			  	str = "/Users/ol7roeo/Documents/tempUpload/products"; 
			} 
			
			String filename = "";
			
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

	            String safeFile = uploadpath + File.separator + originFileName;
	            
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
	        }
			pb.setImage(filename); // 파일명 담기
			
			/* DB insert 작업 */
			int cnt = pdao.insertProducts(pb);
			if(cnt != -1) { // 상품등록 성공
				mav.setViewName(gotoPage);
			}else { // 상품등록 실패
				mav.setViewName(getPage);
			}
		}
		return mav;
	}
}
