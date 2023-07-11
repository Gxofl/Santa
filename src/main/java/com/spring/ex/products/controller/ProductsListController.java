package com.spring.ex.products.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.heart.products.model.ProductsHeartBean;
import com.spring.ex.heart.products.model.ProductsHeartDao;
import com.spring.ex.products.model.ProductsBean;
import com.spring.ex.products.model.ProductsDao;
import com.spring.ex.utility.Paging;

@Controller
public class ProductsListController {

	//private final String command = "/list.prd";
	private final String command = "/products/all/list.prd";
	private String getPage = "products/productsList";

	@Autowired
	ProductsDao pdao;

	//좋아요
	@Autowired
	ProductsHeartDao phdao;

	/*
	 1. 상단 메뉴바에서 거래 클릭시 요청
	 2. productsInsertForm.jsp에서 등록시 요청
	 3. productsList.jsp에서 검색시 요청
	 4.	productsDetailView에서 목록보기시 요청
	 */
	@RequestMapping(value=command)
	public ModelAndView doAction(
			@RequestParam(value="whatColumn", required=false) String whatColumn,
			@RequestParam(value="keyword", required=false) String keyword,
			@RequestParam(value="pageNumber", required=false) String pageNumber,
			HttpServletRequest request, Principal principal,
			HttpServletResponse response) {

		ModelAndView mav = new ModelAndView();
		String loginId = "";
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = null;

		//검색어 설정
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("whatColumn", whatColumn);
		map.put("keyword", "%"+keyword+"%");

		if(whatColumn != null) {
			if(whatColumn.equals("loginId")) { //  내 판매글 보기 요청
				try {
					loginId = principal.getName();
					//System.out.println("내 판매글 보기 요청");
					map.put("keyword", principal.getName()); // keyword에 id
				}catch(NullPointerException e) { // 로그인 하지 않은 상태
					try {
						out = response.getWriter();
						out.println("<script>alert('로그인 후에 사용하실 수 있습니다.');history.go(-1);</script>");
						out.flush();
					} catch (IOException e2) {
						e.printStackTrace();
					}
				}
			}
		}

		//페이지 설정
		int totalCount = pdao.getProductsTotalCount(map);
		String url = request.getContextPath()+command;
		Paging pageInfo = new Paging(pageNumber, "6", totalCount, url, whatColumn, keyword, null);

		List<ProductsBean> plist = pdao.getAllProducts(map, pageInfo);

		/* 좋아요 */
		List<ProductsHeartBean> phList = new ArrayList<ProductsHeartBean>();
		ProductsHeartBean phBean = new ProductsHeartBean();

		try {
			loginId = principal.getName();
			phBean.setUserId(loginId);		//사용자아이디
			phList = phdao.getAllProductsHeart(loginId);
			System.out.println("phList : " + phList);
			//현재 게시글의 좋아요 수 확인
		}catch (Exception e) {
			e.printStackTrace();
		}
		//좋아요
		mav.addObject("phList", phList);
		mav.addObject("plist", plist); // 상품리스트
		mav.addObject("pageInfo", pageInfo); // 페이징
		//mav.addObject("loginId", principal.getName()); // 로그인 아이디 -- 챙길 필요없음 DetailController에서 챙기면 가능
		mav.setViewName(getPage);
		return mav;
	}
}
