package com.spring.ex.pay.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.pay.model.PayBean;
import com.spring.ex.pay.model.PayDao;
import com.spring.ex.products.model.ProductsBean;
import com.spring.ex.products.model.ProductsDao;

@Controller
public class PayFailController {
	private final String command = "/pay/user/fail.pay";
	private String getPage = "pay/payFail";
	
	@Autowired
	ProductsDao prd_dao;
	
	@Autowired
	PayDao pay_dao;
	
	@RequestMapping(command)
	public ModelAndView doAction(@RequestParam("num") String numAndpageNumber,
			Principal principal) {
		ModelAndView mav = new ModelAndView();
		
		//get방식으로 넘길때 한가지밖에 안돼서 ,로 연결해서 하나로 넘긴다음 여기서 나눔
		String[] pa = numAndpageNumber.split(",");
		int prdnum = Integer.parseInt(pa[0]);
		String pageNumber = pa[1];
				
		mav.addObject("num", prdnum);
		mav.addObject("pageNumber", pageNumber);
				
				
		String buyer = principal.getName();
		//System.out.println("결제상품 num : "+prdnum);
		//System.out.println("구매자 : "+buyer);
				
		PayBean pay_bean = new PayBean();
		pay_bean.setPrdnum(prdnum);
		pay_bean.setBuyer(buyer);
		
		// 결제 내역 & 상품 정보 가져오기
		PayBean pay_result = pay_dao.getPayByPrdnum(pay_bean);
		ProductsBean pb = prd_dao.getProductsByNum(prdnum);
		
		mav.addObject("pay_result", pay_result);
		mav.addObject("pb", pb);
		
		mav.setViewName(getPage);
		return mav;
	}
//	// 오랜시간 로딩으로 결제 실패했을때
//	@RequestMapping(command)
//	public void doAction(HttpServletResponse response) {
//		
//		response.setContentType("text/html; charset=UTF-8");
//		PrintWriter out = null;
//		
//		try {
//			out = response.getWriter();
//			out.println("<script>alert('요청시간 초과로 결제 실패했습니다.');alert('다시 결제 시도해주세요');window.close();</script>");
//			out.flush();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}	
