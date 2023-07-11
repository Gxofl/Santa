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
public class StampApplyInfoController {

	private final String command = "/stamp/all/applyinfo.stp";
	private String getPage = "stamp/stampApplyInfo";
	
	@Autowired
	StampDao sdao; 
	
	@RequestMapping(value = command, method = RequestMethod.GET)
	public ModelAndView doAction() {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName(getPage);
		return mav;
	}
	
}
