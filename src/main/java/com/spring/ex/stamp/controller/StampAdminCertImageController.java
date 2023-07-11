package com.spring.ex.stamp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.stamp.model.CertBean;
import com.spring.ex.stamp.model.StampDao;

@Controller
public class StampAdminCertImageController {
	
	private final String command = "/stamp/admin/admincertimage.stp";
	private String getPage = "stamp/stampAdminCertImage";
	
	@Autowired
	StampDao sdao;
	
	@RequestMapping(value = command, method = RequestMethod.GET)
	public ModelAndView doAction(
			@RequestParam("stampnum") String stampnum
			) {
		ModelAndView mav = new ModelAndView();
		
		//인증 리스트의 반복문 안에서 가져온 스탬프넘버로 이미지 이름을 가져옴
		CertBean certBean = sdao.getCertByNum(stampnum);
		System.out.println("certBean : "+certBean);
		
		//가져온 이미지 이름을 모델앤뷰로 넘겨주기
		mav.addObject("certBean",certBean);
		mav.setViewName(getPage);
		return mav;
	}
}
