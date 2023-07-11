package com.spring.ex.products.controller;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.products.model.ProductsBean;
import com.spring.ex.products.model.ProductsDao;

@Controller
public class ProductsDeleteController {
	private final String command = "/products/user/delete.prd";
	private String gotoPage = "redirect:/products/all/list.prd";
	
	@Autowired
	ProductsDao pdao;
	
	@Autowired
	ServletContext servletContext;
	
	/* 
	 1. productsDetailView.jsp에서 삭제버튼클릭(num) => 성공:/products/all/list.prd , 실패:/products/all/detail.prd 요청
	 */
	@RequestMapping(value=command, method=RequestMethod.GET)
	public ModelAndView doAction(@RequestParam("num") int productsnum,
			@RequestParam(value="pageNumber", required=false) String pageNumber) {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageNumber", pageNumber); // 페이지
		
		// 1. DB 삭제하기 전 미리 정보 담아놓기(파일 삭제를 위해서)
		ProductsBean pb = pdao.getProductsByNum(productsnum);
		
		// 2. DB 삭제
		int cnt = pdao.deleteProductsByNum(productsnum);
		
		if(cnt != -1) { // DB삭제 성공
			System.out.println("products DB 삭제 성공");
			
			/* 기존 파일 삭제 */
			String filePath = servletContext.getRealPath("/resources/images/products"); // 웹서버 폴더
			
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
			
			String[] delete_image = pb.getImage().split(","); // 여러개 파일 이름 배열로 만들기

			// 파일 삭제 반복문
			for(int i=0; i<delete_image.length; i++) {
				File delete_img = new File(filePath + File.separator + delete_image[i]); // 웹서버 폴더 파일 삭제
				File delete_img_local = new File(str + File.separator + delete_image[i]); // 임시폴더 파일 삭제
				boolean flag = delete_img.delete();
				boolean flag2 = delete_img_local.delete();
				if(flag == true) {
					System.out.println("웹서버 폴더 파일 삭제성공 ");
				}
				if(flag2 == true) {
					System.out.println("임시 폴더 파일 삭제성공 ");
				}
			}//for
			mav.setViewName(gotoPage);
			
		}else { // DB 삭제 실패
			System.out.println("products DB 삭제 실패");
			mav.addObject("num", productsnum);
			gotoPage = "redirect:/products/user/detail.prd"; // detail.prd 요청
			mav.setViewName(gotoPage);
		}
		return mav;
	}
}
