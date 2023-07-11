package com.spring.ex.products.controller;

import java.io.File;
import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.products.model.ProductsBean;
import com.spring.ex.products.model.ProductsDao;

@Controller
public class ProductsUpdateController {
	private final String command = "/products/user/update.prd";
	private String getPage = "products/productsUpdateForm";
	private String gotoPage = "redirect:/products/user/detail.prd";

	@Autowired
	ProductsDao pdao;

	@Autowired
	ServletContext servletContext;

	/*
	 1. productsDetailView.jsp 판매완료 버튼 클릭시(num, state) => detail.prd 요청
	 2. productsDetailView.jsp 수정버튼 클릭시(num) => productsUpdateForm.jsp
	 */
	@RequestMapping(value=command, method=RequestMethod.GET)
	public ModelAndView doAction(
			@RequestParam("num") int productsnum,
			@RequestParam(value="state", required=false) String state,
			@RequestParam("pageNumber") String pageNumber) {

		ModelAndView mav = new ModelAndView();
		mav.addObject("pageNumber", pageNumber); // 페이지

		if(state == null) {
			System.out.println("글 수정 요청");
			ProductsBean pb = pdao.getProductsByNum(productsnum); // 해당 products 정보 가져오기
			pb.setUpload2(pb.getImage()); // 기존이미지 upload2 변수에 넣어놓고 pb 모델설정 하기
			mav.addObject("pb", pb);
			mav.setViewName(getPage);
		}else {
			if(state.equals("change")) { // 판매완료 수정 요청으로 넘어옴
				int cnt = pdao.updateProductsState(productsnum); // 판매완료로 수정
				mav.addObject("num", productsnum);
				mav.setViewName(gotoPage);
				if(cnt != -1) {
					System.out.println("판매 완료로 수정 성공");
				}else {
					System.out.println("판매 완료로 수정 실패");
				}
			}
		}
		return mav;
	}


	/*
	 1. productsUpdateForm.jsp에서 수정버튼 클릭시 => detail.prd 요청
	 */
	@RequestMapping(value=command, method=RequestMethod.POST)
	public ModelAndView doAction(
			@ModelAttribute("pb") @Valid ProductsBean pb, 
			BindingResult result,
			MultipartHttpServletRequest mtfRequest,
			HttpServletRequest request,
			@RequestParam("pageNumber") String pageNumber) {

		ModelAndView mav = new ModelAndView();
		mav.addObject("num", pb.getProductsnum());
		mav.addObject("pageNumber", pageNumber);

		/* 유효성 검사오류시 다시 form으로 돌아갈때 기존이미지 값이 사라지는 현상 방지*/
		if(pb.getImage()==null) {
			pb.setImage(pb.getUpload2());
		}

		if(result.hasErrors()) { //에러 발생
			mav.setViewName(getPage);

		}else { //에러 없음
			mav.setViewName(gotoPage);
			/* 이미지 수정안했을 때 */
			if(pb.getUpload().isEmpty()) {
				/* 글 수정 */
				int cnt = pdao.updateProducts(pb); // 글 DB 수정 
				if(cnt == 1) { // DB 수정 성공
					System.out.println("거래글 수정 성공");
				}else {
					System.out.println("거래글 수정 실패");
				}
			}
			
			/* 이미지 수정했을 때 */
			else { 
				/* 새로 업로드한 파일명 담기 */
				List<MultipartFile> fileList = mtfRequest.getFiles("upload");
				String filename = "";

				for(int i=0 ; i<fileList.size(); i++) {
					// Bean 변수에 담기위해 파일명 적립
					if(i == fileList.size()-1) { // 마지막 순서
						filename += fileList.get(i).getOriginalFilename();
					}else {
						filename += fileList.get(i).getOriginalFilename()+","; 
					} 
				}
				pb.setImage(filename);

				/* 글 수정 */
				int cnt = pdao.updateProducts(pb); // 글 DB 수정 
				if(cnt == 1) { // DB 수정 성공
					System.out.println("거래글 수정 성공");

					// 1. 기존 파일 삭제
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

					String filePath = servletContext.getRealPath("/resources/images/products");
					String[] delete_image = pb.getUpload2().split(","); // 여러개 파일 이름 배열로 만들기

					// 파일 삭제 반복문
					for(int i=0; i<delete_image.length; i++) {
						File delete_img = new File(filePath + File.separator + delete_image[i]); // 웹서버 폴더 파일 삭제
						File delete_img_local = new File(str + File.separator + delete_image[i]); // 임시폴더 파일 삭제
						boolean flag = delete_img.delete();
						boolean flag2 = delete_img_local.delete();
						if(flag == true) {
							System.out.println("웹서버 폴더 파일 삭제성공 ");
						}else if(flag2 == true) {
							System.out.println("임시 폴더 파일 삭제성공 ");
						}
					}


					//2. 새로운 이미지 업로드 반복문
					for(int i=0 ; i<fileList.size(); i++) {
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
							System.out.println("새로운 이미지 업로드");
						} catch (IllegalStateException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}// for
				}// 글 수정 if 끝
				else { // 글 수정 실패
					System.out.println("거래글 수정 실패");
					mav.setViewName(getPage);
				}
			}// 이미지 수정안함 else
		}// 에러없음 else

		return mav;
	}
}
