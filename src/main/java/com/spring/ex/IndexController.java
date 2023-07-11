package com.spring.ex;

import java.security.Principal;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.users.model.UsersBean;
import com.spring.ex.users.model.UsersDao;

/*
security-context.xml에서 default-target-url="/main.do" 
	-> default-target-url : 로그인 후 보여질 페이지(성공적으로 로그인 이후 이동한 URL)

*/
@Controller
public class IndexController {
	
	private final String command = "/users/all/main.lg";
	private String gotoPage = "index";
	
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
	UsersDao usersDao;
	
	@RequestMapping(value = command, method = RequestMethod.GET)
	public ModelAndView indexPage(Locale locale, Model model, Principal principal) {
		logger.info("logger info입니다.", locale);
		
		String getUserId = principal.getName();			
		// Principal은 자바의 표준 시큐리티 기술로, 로그인이 된 상태라면 계정 정보를 담고있다.
		
		UsersBean userInfo = usersDao.getInfo(getUserId);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		ModelAndView mav = new ModelAndView();
		mav.addObject("serverTime", formattedDate );
		mav.addObject("userInfo", userInfo);
		
		mav.setViewName(gotoPage); // index
		return mav; 
	}//indexPage
	
}//IndexController
