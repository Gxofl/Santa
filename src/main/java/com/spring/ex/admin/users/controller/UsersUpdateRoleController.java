package com.spring.ex.admin.users.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.users.model.UsersBean;
import com.spring.ex.users.model.UsersDao;

@Controller
public class UsersUpdateRoleController {

	private final String command = "/users/admin/usersUpdateRole.us";
	private String getPage = "/admin/users/usersUpdateRoleForm";

	@Autowired
	UsersDao udao;

	// 관리자페이지 회원목록에서 회원 아이디 클릭 -> 현재 저장된 사용자 정 띄우기
	@RequestMapping(value = command, method = RequestMethod.GET)
	public ModelAndView usersUpdateRole(
			@RequestParam(value = "userId", required = true) String userId) {
		ModelAndView mav = new ModelAndView();
		System.out.println("usersUpdateRole userId : " + userId);
		UsersBean usersBean = udao.getUsersByUserId(userId);
		mav.addObject("usersBean", usersBean);
		mav.setViewName(getPage);
		return mav;
	}// usersUpdateRole - 선택한 사용자의 정보띄우기

	// usersUpdateRoleForm.jsp에서 submit 클릭, (관리자가 선택한 사용자의 등급 변경)
	@RequestMapping(value = command, method = RequestMethod.POST)
	@ResponseBody
	public int usersUpdateRole(
	        @RequestParam(value = "input_userId", required = true) String input_userId,
	        @RequestParam(value = "select_userRole", required = true) String select_userRole) {

	    UsersBean usersBean = new UsersBean();
	    usersBean.setUserId(input_userId);
	    usersBean.setUserRole(select_userRole);

	    int updateUserRole_result = udao.usersUpdateUserRole(usersBean);
	    System.out.println("UpdateUserRole result : " + updateUserRole_result);
	    System.out.println("usersBean.getUserId() : " + usersBean.getUserId());
	    System.out.println("usersBean.getUserRole() : " + usersBean.getUserRole());

	    if (updateUserRole_result != -1) {
	        // 변경에 성공하면 -1이 아닌 값 return
	        System.out.println("success - updateUserRole_result : " + updateUserRole_result);
	        return updateUserRole_result;
	    } else {
	        // 변경에 실패하면 -1 return
	        return -1;
	    }
	}// usersUpdateRole - 관리자가 선택한 사용자의 등급 변경
	
}// UsersUpdateRoleController
