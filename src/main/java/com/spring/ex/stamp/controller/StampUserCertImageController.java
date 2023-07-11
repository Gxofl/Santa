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
public class StampUserCertImageController {
	
	private final String command = "/stamp/user/usercertimage.stp";
	private String getPage = "stamp/stampUserCertImage";
	
	@Autowired
	StampDao sdao;
	
	@RequestMapping(value = command, method = RequestMethod.GET)
	public ModelAndView doAction(
			@RequestParam(value="stampreject", required=false) String stampreject,
			@RequestParam("userid") String userid,
			@RequestParam(value="mountainname", required=false) String mountainname,
			@RequestParam("stampimage") String stampimage
			) {
		ModelAndView mav = new ModelAndView();
		
		//가져온 이미지 이름을 모델앤뷰로 넘겨주기
		mav.addObject("stampreject",stampreject);
		mav.addObject("userId",userid);
		mav.addObject("mountainName",mountainname);
		mav.addObject("stampImage",stampimage);
		mav.setViewName(getPage);
		return mav;
	}
}
