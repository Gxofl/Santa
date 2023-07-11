package com.spring.ex.admin.pay.controller;

import java.io.IOException;
import java.io.PrintWriter;

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
import com.spring.ex.users.model.UsersDao;

@Controller
public class PayApprovalAdminController {
	private final String command = "/pay/admin/approval.pay";
	private String gotoPage = "redirect:/pay/admin/waitlist.pay";
	
	@Autowired
	PayDao pay_dao;
	
	@Autowired
	UsersDao u_dao;
	
	@Autowired
	ProductsDao prd_dao;
	
	/* 
	 관리자 결제 승인 대기내역에서 승인버튼 클릭 => admin_waitpay.jsp
	 */
	@RequestMapping(command)
	public ModelAndView doAction(@RequestParam("paynum") int paynum,
			HttpServletResponse response) {
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = null;
		
		ModelAndView mav = new ModelAndView();
		
		int cnt = pay_dao.updatePayState(paynum);
		if(cnt != -1) { // DB 수정 성공
			System.out.println("관리자가 결제 승인함");
			
			try {
				out = response.getWriter();
				out.println("<script>alert('결제 승인완료 되었습니다');</script>");
				//out.flush(); -- 설정한 setViewName으로 넘어가지 않음
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			/* 판매자 포인트 지급하기 */
			// 1.구매 상품 정보 알아오기
			PayBean pb = pay_dao.getPayByPaynum(paynum);
			int prd_num = pb.getPrdnum();
			ProductsBean prd_bean = prd_dao.getProductsByNum(prd_num);
			String seller = prd_bean.getSeller();
			
			//2. 판매자 포인트 수정
			int point = Integer.parseInt(prd_bean.getPrice());
			double point2 = point * 0.1;
			String cal_point = String.valueOf(point2);
			prd_bean.setPrice(cal_point);
			
			int point_result = u_dao.updatePoint(prd_bean);
			if(point_result != -1) {
				System.out.println(seller+" 포인트 적립완료");
			}else {
				System.out.println(seller+" 포인트 적립실패");
			}
		}else {
			System.out.println("관리자가 결제 승인 실패함");
			try {
				out = response.getWriter();
				out.println("<script>alert('결제 승인 실패했습니다');</script>");
				//out.flush(); -- 설정한 setViewName으로 넘어가지 않음
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		mav.setViewName(gotoPage);
		return mav;
	}
}
