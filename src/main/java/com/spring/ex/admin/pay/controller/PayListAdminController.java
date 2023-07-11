package com.spring.ex.admin.pay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.pay.model.PayBean;
import com.spring.ex.pay.model.PayDao;

@Controller
public class PayListAdminController {
	
	// 결제 승인 대기 내역들
	private final String command1 = "/pay/admin/waitlist.pay";
	private String getPage1 = "admin/pay/admin_waitpay";
	
	@Autowired
	PayDao pay_dao;
	
	@RequestMapping(command1)
	public ModelAndView doAction() {
		ModelAndView mav = new ModelAndView();
		
		List<PayBean> paylist = pay_dao.getWaitPay();
		
		mav.addObject("paylist", paylist);
		mav.setViewName(getPage1);
		return mav;
	}
	
	
	// 결제 승인 완료 내역들 (판매자 포인트 지급완료)
	private final String command2 = "/pay/admin/finishlist.pay";
	private String getPage2 = "admin/pay/admin_finishpay";
	
	/* 
	 관리자 결제 승인 완료내역 클릭 => admin_finishpay.jsp
	 */
	@RequestMapping(command2)
	public ModelAndView doAction2() {
		ModelAndView mav = new ModelAndView();
		
		List<PayBean> finishlist = pay_dao.getFinishPay();
		mav.addObject("finish_paylist", finishlist);
		mav.setViewName(getPage2);
		return mav;
	}
	
	// 결제 취소 내역(구매자 본인요청, 관리자 승인거절)
	private final String command3 = "/pay/admin/cancellist.pay";
	private String getPage3 = "admin/pay/admin_cancelpay";
	@RequestMapping(command3)
	public ModelAndView doAction3() {
		ModelAndView mav = new ModelAndView();
		
		List<PayBean> cancellist = pay_dao.getCancelPay();
		mav.addObject("cancel_paylist", cancellist);
		mav.setViewName(getPage3);
		return mav;
	}
}
