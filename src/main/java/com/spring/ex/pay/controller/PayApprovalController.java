package com.spring.ex.pay.controller;

import java.security.Principal;

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
public class PayApprovalController {
	private final String command = "/pay/user/approval.pay";
	private String getPage = "pay/payApproval";
	private String gotoPage = "/products/user/detail.prd";
	
	@Autowired
	PayDao pay_dao;
	
	@Autowired
	ProductsDao prd_dao;
	
	// 카카오페이 결제 완료시(num,pageNumber) => payApproval.jsp or detail.prd
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
		int cnt = pay_dao.insertPay(pay_bean);
		
		if(cnt != -1) {
			System.out.println("pay DB 삽입 성공");
			
			// 판매 완료로 바꾸기
			int state_result = prd_dao.updateProductsState(prdnum);
			if(state_result != -1) {
				System.out.println("판매완료로 변경됨");
			}else {
				System.out.println("판매완료로 변경실패");
			}
			
			// 결제 내역 & 상품 정보 가져오기
			PayBean pay_result = pay_dao.getPayByPrdnum(pay_bean);
			ProductsBean pb = prd_dao.getProductsByNum(prdnum);
			
			mav.addObject("pay_result", pay_result);
			mav.addObject("pb", pb);
			
			mav.setViewName(getPage);
		}else {
			System.out.println("pay DB 삽입 실패");
			
			mav.setViewName(gotoPage); // 다시 상품 상세보기로 돌아감
		}
		return mav;
	}
}
