package com.spring.ex.myPage.pay.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.pay.model.PayBean;
import com.spring.ex.pay.model.PayDao;
import com.spring.ex.products.model.ProductsBean;
import com.spring.ex.products.model.ProductsDao;
import com.spring.ex.users.model.UsersDao;

@Controller
public class PayMyPageController {
	private final String command = "/pay/user/myPage.pay";
	private String getPage = "myPage/pay/myBuyPayCheck";
	
	@Autowired
	PayDao pay_dao;
	
	@Autowired
	ProductsDao prd_dao;
	
	@Autowired
	UsersDao u_dao;
	
	//마이페이지 nav에서 구매 결제내역 클릭
	@RequestMapping(command)
	public ModelAndView doAction(Principal principal) {
		
		ModelAndView mav = new ModelAndView();
		
		String loginId = principal.getName();
		List<PayBean> myPayList = pay_dao.getMyPagePay(loginId);
		mav.addObject("myPayList", myPayList);
		mav.setViewName(getPage);
		
		return mav;
	}
	
	// *-----------------------------------------------------------------* //
	private final String command2 = "/pay/user/myPage2.pay";
	private String getPage2 = "pay/payApproval";
	
	// 마이페이지 나의 구매 결제내역에서 승인여부 눌렀을때 => pay/payApproval.jsp
	@RequestMapping(command2)
	public ModelAndView doAction2(@RequestParam("prdnum") int prd_num,
			@RequestParam("paynum") int pay_num) {
		
		ModelAndView mav = new ModelAndView();
		
		// 결제 내역 & 상품 정보 가져오기
		PayBean payb = new PayBean();
		payb.setPrdnum(prd_num);
		PayBean pay_result = pay_dao.getPayByPaynum(pay_num);
		ProductsBean pb = prd_dao.getProductsByNum(prd_num);
					
		mav.addObject("pay_result", pay_result);
		mav.addObject("pb", pb);
					
		mav.setViewName(getPage2);
		
		return mav;
	}
	
	// *-----------------------------------------------------------------* //
	private final String command3 = "/pay/user/myPage_sell.pay";
	private String getPage3 = "myPage/pay/mySellPayCheck";
	
	// 마이페이지 nav 에서 판매 결제내역 클릭
	@RequestMapping(command3)
	public ModelAndView doAction3(Principal principal) {
		
		ModelAndView mav = new ModelAndView();
		
		//[판매] 거래 완료 상태 상품 가져오기
		String loginId = principal.getName();
		List<ProductsBean> sell_prd_list = prd_dao.getSellProductsById(loginId);
		
		//내 포인트 가져오기
		int point = u_dao.getPointByUserId(loginId);
		
		mav.addObject("Mypoint", point);
		mav.addObject("sell_prd_list", sell_prd_list);
		mav.setViewName(getPage3);
		
		return mav;
	}
	
}
