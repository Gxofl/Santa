package com.spring.ex.pay.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.ex.products.model.ProductsBean;
import com.spring.ex.products.model.ProductsDao;

@Controller
public class PayController {
	private final String command = "/pay/user/kakaopay.pay";
	
	@Autowired
	ProductsDao prd_dao;
	
	//productsDetailView.jsp 구매하기 버튼 클릭 
	@RequestMapping(value=command)
	@ResponseBody
	public String doAction(HttpServletRequest request,
			@RequestParam("num") int products_num,
			@RequestParam("pageNumber") String pageNumber) {
		
		try {
			URL address = new URL("https://kapi.kakao.com/v1/payment/ready");
			HttpURLConnection conn = (HttpURLConnection) address.openConnection(); // 서버연결
			
			conn.setRequestMethod("POST"); // method 설정
			
			// 헤더 설정
			conn.setRequestProperty("Authorization", "KakaoAK c01069fce86ca75e8d2867322bb7f575");
			conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			
			conn.setDoOutput(true); // 서버에 내보낼게 있으면 true 기본으로 false로 설정되어 있음
			
			//파라미터 설정시 자신의 port번호가 필요해서
			String port = String.valueOf(request.getServerPort());
			System.out.println("port번호 : "+port);
			
			ProductsBean pb = prd_dao.getProductsByNum(products_num);
			String name = pb.getName(); // 구매하려는 상품명
			String name2 = URLEncoder.encode(name, "UTF-8"); // 상품명이 한글일시 깨져보임 => URL Encoding 설정
			String price = pb.getPrice(); // 가격
			
			// 파라미터 설정 localhost:xx 자신의 port번호 입력
			String param = "cid=TC0ONETIME&partner_order_id=partner_order_id&partner_user_id=partner_user_id";
			param += "&item_name="+name2+"&quantity=1&total_amount="+price+"&vat_amount=200&tax_free_amount=0";
			param += "&approval_url=http://localhost:"+port+"/pay/user/approval.pay?num="+products_num+","+pageNumber; // 정상적으로 승인
			param += "&fail_url=http://localhost:"+port+"/pay/user/fail.pay?num="+products_num+","+pageNumber; // 결제 실패
			param += "&cancel_url=http://localhost:"+port+"/pay/user/cancel.pay?num="+products_num+","+pageNumber; // 결제중 취소버튼 클릭
			
			OutputStream giver = conn.getOutputStream(); // 서버로 넘기는 역할
			DataOutputStream data_giver = new DataOutputStream(giver); // 데이터를 넘기는 역할
			
			// 데이터를 바이트형식으로 주고받기 때문에 writeBytes()를 통해 파라미터 변환
			data_giver.writeBytes(param);
			data_giver.close(); // close를 호출하면 자동으로 flush가 된다
			
			int resultcode = conn.getResponseCode(); // 200만이 정상 작동
			
			//데이터를 받는 역할
			InputStream take;
			if(resultcode == 200) { // 200만이 정상 작동
				take = conn.getInputStream();
			}else { // 에러났을때
				take = conn.getErrorStream();
			}
			
			//받아온 데이터를 읽는 역할
			InputStreamReader reader = new InputStreamReader(take);
			
			//받아온 데이터를 형변환 하는 역할 (Byte 형식이기 때문에 필요)
			BufferedReader changer = new BufferedReader(reader);
			
			//문자열로 형변환한 상태
			return changer.readLine();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "{\"result\":\"NO\"}";
	}
}
