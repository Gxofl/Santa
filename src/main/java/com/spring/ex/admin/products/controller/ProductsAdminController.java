package com.spring.ex.admin.products.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.products.model.ProductsBean;
import com.spring.ex.products.model.ProductsDao;
import com.spring.ex.utility.Paging;

@Controller
public class ProductsAdminController {
	private final String command = "/products/admin/list.prd";
	private String getPage = "admin/products/admin_products";
	
	@Autowired
	ProductsDao prd_dao;
	
	/*
	 관리자 페이지에서 거래게시판 관리 클릭시 => admin/products/admin_products.jsp
	 */
	@RequestMapping(command)
	public ModelAndView doAction(
			@RequestParam(value="whatColumn", required=false) String whatColumn,
			@RequestParam(value="keyword", required=false) String keyword,
			@RequestParam(value="pageNumber", required=false) String pageNumber,
			@RequestParam(value="kind", required=false) String kind,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("whatColumn", whatColumn);
		map.put("keyword", "%"+keyword+"%");
		map.put("kind", kind);
		
		//페이지 설정
		int totalCount = prd_dao.getProductsTotalCount(map);
		String url = request.getContextPath()+command;
		Paging pageInfo = new Paging(pageNumber, "5", totalCount, url, whatColumn, keyword, null);

		List<ProductsBean> plist = prd_dao.getAllProductsByAdmin(map, pageInfo);
		mav.addObject("plist", plist); // 상품리스트
		mav.addObject("pageInfo", pageInfo); // 페이징
		mav.addObject("kind", kind);
		//mav.addObject("loginId", principal.getName()); // 로그인 아이디 -- 챙길 필요없음 DetailController에서 챙기면 가능
		mav.setViewName(getPage);
		return mav;
		
		
	}
}
