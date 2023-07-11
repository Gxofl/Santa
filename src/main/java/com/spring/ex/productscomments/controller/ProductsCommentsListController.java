package com.spring.ex.productscomments.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.ex.productscomments.model.ProductsCommentsBean;
import com.spring.ex.productscomments.model.ProductsCommentsDao;

@Controller
public class ProductsCommentsListController {
	private final String command = "/productscomments/user/list.pcmt";
	
	@Autowired
	ProductsCommentsDao pcmt_dao;
	
	@RequestMapping(value=command, method=RequestMethod.POST)
	@ResponseBody
	public List<ProductsCommentsBean> doAction(
			@RequestParam("idx") int idx,
			@RequestParam("pageNumber") String pageNumber){
		
		List<ProductsCommentsBean> comments = new ArrayList<ProductsCommentsBean>();
		comments = pcmt_dao.getProductsCommentsByIdx(idx);
		
		for(int i=0; i<comments.size(); i++) { // 페이지 넣기
			comments.get(i).setPageNumber(pageNumber);
			
			// 비밀댓글 열람을 위해 원댓글자
			int ref = comments.get(i).getRef();
			String orgwriter = pcmt_dao.getProductsCommentsOrgWriter(ref);
			comments.get(i).setOrgwriter(orgwriter);
		}
		
		return comments;
	}
}
