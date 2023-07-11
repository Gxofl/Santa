package com.spring.ex.pay.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.pay.model.PayBean;
import com.spring.ex.pay.model.PayDao;
import com.spring.ex.products.model.ProductsDao;

@Controller
public class PayCancelController {
	private final String command = "/pay/user/cancel.pay";
	
	@Autowired
	PayDao pay_dao;
	
	@Autowired
	ProductsDao prd_dao;
	
	// 카카오페이 결제시 취소버튼 눌렀을때 => alert() 띄우고 결제창 닫음
	@RequestMapping(command)
	public void doAction(HttpServletRequest request, HttpServletResponse response) {
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = null;
		
		try {
			out = response.getWriter();
			out.println("<script>alert('결제가 취소되었습니다.');window.close();</script>");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private final String command2 = "/pay/user/cancel2.pay";
	private String gotoPage = "redirect:/pay/user/myPage.pay";
	
	//결제내역에서 결제취소 버튼을 눌렀을때
	@RequestMapping(command2)
	public ModelAndView doAction(@RequestParam("paynum") int paynum,
			HttpServletResponse response) {
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = null;
		
		ModelAndView mav = new ModelAndView();
		
		// 결제했던 상품을 판매완료 -> 판매중으로 상태 재변경하기 위해 삭제전에 해당 상품번호 담아놓음
		PayBean pay_bean = pay_dao.getPayByPaynum(paynum);
		int prdnum = pay_bean.getPrdnum(); // 상품번호
		
		//pay state 값 변경 (본인취소는 2)
		int cnt = pay_dao.updatePayStateByMySelf(paynum);
		if(cnt != -1) { // DB삭제 성공
			
			int result = prd_dao.reUpdateProductsState(prdnum); 
			if(result != -1) {
				System.out.println("판매중으로 변경완료");
			}else {
				System.out.println("판매중으로 변경실패");
			}
			
			try {
				out = response.getWriter();
				out.println("<script>alert('결제가 취소되었습니다.');location.href='/pay/user/myPage.pay';</script>");
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			mav.setViewName(gotoPage);
			
		}else { // DB삭제 실패
			try {
				out = response.getWriter();
				out.println("<script>alert('결제취소를 실패했습니다.');history.go(-1);</script>");
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return mav;
	}
}
