package com.spring.ex.users.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.ex.products.model.ProductsBean;
import com.spring.ex.utility.Paging;

@Component("UsersDao")
public class UsersDao {
	
private static final Logger logger = LoggerFactory.getLogger(UsersDao.class);
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	//getInfo : 로그인한 사용자의 정보가져오기
	public UsersBean getInfo(String userId) {
		UsersBean loginInfo = new UsersBean();
		loginInfo.setUserId(userId);
		loginInfo = sqlSessionTemplate.selectOne("login.getInfo", loginInfo);
		return loginInfo;
		
	}//getInfo
	
	//signUp : 회원가입
	public int signUp(UsersBean usersBean) {
		int cnt = -1;
		cnt = sqlSessionTemplate.insert("users.signUp", usersBean);
		return cnt;
		
	}//signUp
	
	//UserIdCheck : 회원가입페이지에서 아이디중복여부 체크
	public int userIdCheck(String input_UserId) {
		int cnt = -1;
		cnt = sqlSessionTemplate.selectOne("users.userIdCheck", input_UserId);
		//userId가 있으면 1, 없으면 0
		return cnt;
		
	}//UserIdCheck
	
	//findUserId : 아이디 찾기페이지에서 submit 클릭하여, 사용자가 입력한 정보가 users에 있는지 확인
	public String findUserId(UsersBean param_usersBean) {
	    UsersBean usersBean = null;
	    usersBean = sqlSessionTemplate.selectOne("users.findUserId", param_usersBean);
	    String result = "NO"; 
	    if (usersBean != null && usersBean.getUserId() != null) {
	    	result = usersBean.getUserId();
	    } 
	    System.out.println("findUserId result : " + result);
	    return result;
	    
	}//findUserId
	
	//findPassword : 비밀번호 찾기페이지에서 submit 클릭하여, 사용자가 입력한 정보가 users에 있는지 확인
	public String findPassword(UsersBean param_usersBean) {
	    UsersBean usersBean = null;
	    usersBean = sqlSessionTemplate.selectOne("users.findPassword", param_usersBean);
	    String result = "NO"; 
	    if (usersBean != null && usersBean.getPassword() != null) {
	    	result =  usersBean.getPassword();
	    } 
	    System.out.println("findPassword result : " + result);
	    return result;
	    
	}//findPassword
	
	//getUsersByUserId : 마이페이지에 사용자 정보 띄우기
	public UsersBean getUsersByUserId(String getUserId) {
		UsersBean usersBean = null;
		try {
			usersBean = sqlSessionTemplate.selectOne("users.getUsersByUserId", getUserId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usersBean;
		
	}//getUsersByUserId
	
	//getUsersByPassword : 마이페이지에서 수정 클릭 시 사용자의 비밀번호를 다시 확인
	public int getUsersByPassword(UsersBean usersBean) {
		int cnt = -1;
		cnt = sqlSessionTemplate.selectOne("users.getUsersByPassword", usersBean);
		//입력한 비밀번호와 DB의 비밀번호가 동일하면 1, 동일하지않으면 0 return
		System.out.println("getUsersByPassword cnt : "+cnt);
		return cnt;
		
	}//getUsersByPassword
	
	//usersUpdate : 사용자 비밀번호를 확인 후 마이페이지수정페이지에서 사용자가 수정요청한 값 update
	public int usersUpdate(UsersBean usersBean) {
		int cnt = -1;
		try {
			cnt = sqlSessionTemplate.update("users.usersUpdate", usersBean);
			System.out.println("usersUpdate cnt : "+cnt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
		
	}//usersUpdate
	
	//usersUpdatePassword : 사용자 기존 비밀번호 확인 후 새로운 비밀번호로 update
	public int usersUpdatePassword(UsersBean usersBean) {
		int cnt = -1;
		System.out.println(usersBean.getUserId());
		System.out.println(usersBean.getPassword());
		System.out.println(usersBean.getPasswordNew());
		cnt = sqlSessionTemplate.update("users.usersUpdatePassword", usersBean);
		System.out.println("usersUpdatePassword cnt : "+cnt);
		return cnt;
		
	}//usersUpdatePassword
	
	//usersWithdrawal : 사용자 탈퇴(status Y -> N) update
	public int usersWithdrawal(String getUserId) {
		int cnt = -1;
		cnt = sqlSessionTemplate.update("users.usersWithdrawal", getUserId);
		System.out.println("usersWithdrawal cnt : "+cnt);
		return cnt;
		
	}//usersWithdrawal
	
	//getUserTotalCount : 관리자를 포함한 상태값이 Y인 회원 검색결과
	public int getUserTotalCount(Map<String, String> map) {
		int totalCount = sqlSessionTemplate.selectOne("users.getUserTotalCount", map);
		return totalCount;
	}//getUserTotalCount
	
	
	//getAllUsers : 회원 목록(관리자 포함한, 상태값Y인 회원의 목록)
	public List<UsersBean> getAllUsers(Map<String, String> map, Paging pageInfo) {
		List<UsersBean> userList = new ArrayList<UsersBean>();
		RowBounds rowBounds = new RowBounds(pageInfo.getOffset(), pageInfo.getLimit());
		userList = sqlSessionTemplate.selectList("users.getAllUsers", map, rowBounds);
		return userList;
		
	}//getAllUsers
	
	//getUserWithdrawalTotalCount : 관리자를 포함한 상태값이 N인 회원 검색결과
	public int getUserWithdrawalTotalCount(Map<String, String> map) {
		int totalCount = sqlSessionTemplate.selectOne("users.getUserWithdrawalTotalCount", map);
		return totalCount;
		
	}//getUserWithdrawalTotalCount
	
		
	//getAllUserWithdrawalList : 탈퇴 회원 목록(상태값N인 회원의 목록)
	public List<UsersBean> getAllUserWithdrawalList(Map<String, String> map, Paging pageInfo) {
		List<UsersBean> usersWithdrawalList = new ArrayList<UsersBean>();
		RowBounds rowBounds = new RowBounds(pageInfo.getOffset(), pageInfo.getLimit());
		usersWithdrawalList = sqlSessionTemplate.selectList("users.getAllUserWithdrawal", map, rowBounds);
		return usersWithdrawalList;
		
	}//getAllUserWithdrawalList
	
	//getAdminTotalCount : 관리자 검색결과
	public int getAdminTotalCount(Map<String, String> map) {
		int totalCount = sqlSessionTemplate.selectOne("users.getAdminTotalCount", map);
		return totalCount;
	}//getAdminTotalCount
	
	//getAllAdminList : 관리자 목록
	public List<UsersBean> getAllAdminList(Map<String, String> map, Paging pageInfo) {
		List<UsersBean> adminList = new ArrayList<UsersBean>();
		RowBounds rowBounds = new RowBounds(pageInfo.getOffset(), pageInfo.getLimit());
		adminList = sqlSessionTemplate.selectList("users.getAllAdmin", map, rowBounds);
		return adminList;
	}//getAllAdminList
	
	//adminInsert : 관리자페이지에서 관리자등록
	public int adminInsert(UsersBean usersBean) {
		int cnt = -1;
		cnt = sqlSessionTemplate.insert("users.adminInsert", usersBean);
		return cnt;
		
	}//adminInsert
	
	//usersUpdateUserRole : 관리자페이지에서 사용자(관리자포함) 등급변경
	public int usersUpdateUserRole(UsersBean usersBean) {
		int cnt = -1;
		try {
			System.out.println(usersBean.getUserId());
			System.out.println(usersBean.getUserRole());
			cnt = sqlSessionTemplate.update("users.updateUserRole", usersBean);
		    System.out.println("updateUserRole cnt : " + cnt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	    return cnt;
	    
	}//usersUpdateUserRole

	/* 가영 입력 */
	//updatePoint : 관리자 결제 승인시 판매자 포인트 적립
	public int updatePoint(ProductsBean prd_bean) {
		int cnt = -1;
		cnt = sqlSessionTemplate.update("users.updatePoint", prd_bean);
		return cnt;
	}//updatePoint

	//getPoingByUserId : 마이페이지 내 포인트
	public int getPointByUserId(String UserId) {
		int point = -1;
		point = sqlSessionTemplate.selectOne("users.getPointByUserId", UserId);
		return point;
	}

	
	public List<Map<String, Object>> getAgeStatistics(Map<String, Integer> ageRange) {
		List<Map<String, Object>> ageStatistics = new ArrayList<>();
		Map<String, Object> statistics1 = new HashMap<>();
	    statistics1.put("age", 10);
	    statistics1.put("count", 100);
	    ageStatistics.add(statistics1);

	    Map<String, Object> statistics2 = new HashMap<>();
	    statistics2.put("age", 20);
	    statistics2.put("count", 150);
	    ageStatistics.add(statistics2);

	    return ageStatistics;
	}


	public List<HashMap<String, Object>> userStats1(HashMap<String, Object> param) {
		List<HashMap<String, Object>> ageStatistics = new ArrayList<>();
		ageStatistics = sqlSessionTemplate.selectList("users.stats1", param);
		
		return ageStatistics;
	}


}//UsersDAO
