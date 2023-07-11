package com.spring.ex.supportersapply.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.ex.supporters.model.SupportersBean;
import com.spring.ex.utility.Paging;


@Component
public class SupportersApplyDao {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	
	//SupportersApplyInsertController(신청하기 버튼) : insert 작업
	public int insertSupportersApply(SupportersApplyBean supportersapply) {
		int cnt = -1;	
		cnt = sqlSessionTemplate.insert("InsertSupportersApply", supportersapply);		
		return cnt;
	}
	
	//SupportersApplyInsertController(신청하기 버튼) : 해당 서포터즈의 신청인원 +1 하기
	public int addSupportersCount(SupportersApplyBean supportersapply) {
		int cnt = -1;	
		cnt = sqlSessionTemplate.update("addSupportersCount", supportersapply);		
		return cnt;
	}
		
	//1. SupportersApplyCheckController(신청하기 버튼)
	//2. SupportersApplyCheckController(취소하기 버튼) : 조건이 일치하는 레코드의 갯수를 리턴함. 신청내역이 있으면 1이 리턴됨
	public int getApplyCount(Map<String, String> map) {
		//System.out.println("dao의 map: "+map);
		int cnt = -1;
		cnt = sqlSessionTemplate.selectOne("getApplyCount", map);		
		return cnt;
	}
	
	//SuppotersApplyCancelController(취소하기 버튼) : 신청상태(state=0)인 건만 취소 처리함(state=1)
	public int cancelSupportersApply(Map<String, String> map) {
		int cnt = -1;	
		cnt = sqlSessionTemplate.update("cancelSupportersApply", map);		
		return cnt;
	}	
	
	//SuppotersApplyCancelController(취소하기 버튼) : 해당 서포터즈의 신청인원 -1 하기
	public int discountSupportersCount(SupportersApplyBean supportersapply) {
		int cnt = -1;	
		cnt = sqlSessionTemplate.update("discountSupportersCount", supportersapply);		
		return cnt;
	}
	
	//SupportersApplyListController : 마이페이지에서 내 서포터즈 '전체' 조회
	public List<SupportersApplyBean> getMySupporters(String loginId) {
		List<SupportersApplyBean> mysupportersList = new ArrayList<SupportersApplyBean>();
		mysupportersList = sqlSessionTemplate.selectList("GetMySupporters", loginId); 
		return mysupportersList;
	}
	
	//SupportersMyPageController : 마이페이지에서 내 서포터즈 '활동중' 조회
	public List<SupportersApplyBean> getMySupportersIng(String loginId) {
		List<SupportersApplyBean> mysupportersList = new ArrayList<SupportersApplyBean>();
		mysupportersList = sqlSessionTemplate.selectList("GetMySupportersIng", loginId); 
		return mysupportersList;
	}

	//SupportersMyPageController : 마이페이지에서 내 서포터즈 '활동종료' 조회
	public List<SupportersApplyBean> getMySupportersEnd(String loginId) {
		List<SupportersApplyBean> mysupportersList = new ArrayList<SupportersApplyBean>();
		mysupportersList = sqlSessionTemplate.selectList("GetMySupportersEnd", loginId); 
		return mysupportersList;
	}	
	
	//SupportersSepaAdminController : 관리자페이지에서 '서포터즈 별 신청회원'을 조회
	public List<SupportersApplyBean> getApplicantList(Map<String, String> map) {
		List<SupportersApplyBean> mysupportersList = new ArrayList<SupportersApplyBean>();
		mysupportersList = sqlSessionTemplate.selectList("GetApplicantList", map); 
		return mysupportersList;
	}
	

	
	
}
