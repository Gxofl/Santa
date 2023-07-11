package com.spring.ex.supporters.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.ex.supportersapply.model.SupportersApplyBean;
import com.spring.ex.utility.Paging;

@Component
public class SupportersDao {
	
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	
	public int getTotalSupportersCount(Map<String, String> map) {
		int cnt = -1;
		cnt = sqlSessionTemplate.selectOne("GetTotalSupportersCount", map);		
		return cnt;
	}

	public List<SupportersBean> getAllSupporters(Map<String, String> map, Paging pageInfo) {
		List<SupportersBean> lists = new ArrayList<SupportersBean>();
		
		RowBounds rowBounds = new RowBounds(pageInfo.getOffset(),pageInfo.getLimit());		
		lists = sqlSessionTemplate.selectList("GetAllSupporters",map,rowBounds);
		System.out.println("supporters lists.size(): "+lists.size());
		
		return lists;
	}

	public int insertSupporters(SupportersBean supporters) {
		int cnt = -1;	
		cnt = sqlSessionTemplate.insert("InsertSupporters", supporters);
		return cnt;
	}

	public int checkSupportersname(String input_name) {
		return sqlSessionTemplate.selectOne("CheckSupportersname", input_name);
	}

	public int updateSupoCount(int num) { //조회수 1 중기
		int cnt = -1;
		cnt = sqlSessionTemplate.update("UpdateSupoCount", num);
		return cnt;
	}

	public SupportersBean getSupportersByNum(int num) {
		SupportersBean supporters = null;
		supporters = sqlSessionTemplate.selectOne("GetSupportersByNum", num);
		return supporters;
	}

	public int deleteSupporters(int num) {
		int cnt = -1;		
		cnt = sqlSessionTemplate.delete("DeleteSupporters",num);
		return cnt;
	}

	public int updateSupporter(SupportersBean supporters) {
		int cnt = -1;
		cnt = sqlSessionTemplate.update("UpdateSupporters", supporters);
		return cnt;
	}
	
	//SupportersApplySepaAdminController(관리자페이지) : 회원 별 신청한 모든 서포터즈를 조회
	public List<SupportersBean> getSupporterList(Map<String, String> map) { //map에는 keyword(회원의 id)가 들어있음
		List<SupportersBean> lists = new ArrayList<SupportersBean>();			
		lists = sqlSessionTemplate.selectList("GetSupporterList",map);
		return lists;
	}
	
	//서포터즈 삭제하면 state=1로 바꾸도록
	public int deleteSupportersHistory(int num) {
		int cnt = -1;		
		cnt = sqlSessionTemplate.delete("deleteSupportersHistory",num);
		return cnt;
	}
	
	//SupportersSepaAdminController(관리자페이지) : 서포터즈명 다 가져오기
	public List<SupportersBean> getSupportersListAll(Map<String, String> map) {
		List<SupportersBean> lists = new ArrayList<SupportersBean>();	
		lists = sqlSessionTemplate.selectList("GetSupportersListAll",map);
		return lists;
	}
	
	//관리자 페이지 : 다중삭제
	public int deleteSupportersByAdmin(String[] supporters_num) {
		int cnt = -1;
		for(int i=0; i<supporters_num.length; i++) {
			cnt += sqlSessionTemplate.delete("DeleteSupportersByAdmin", supporters_num[i]);
		}
		return cnt;
	}

	public int getAllSupportersCount() {
		int allsupo_count = 0;
		allsupo_count = sqlSessionTemplate.selectOne("GetAllSupportersCount");
		return allsupo_count;
	}

	public int getIngSupportersCount() {
		int ingsupo_count = 0;
		ingsupo_count = sqlSessionTemplate.selectOne("GetIngSupportersCount");
		return ingsupo_count;
	}

	public int getCompleteSupportersCount() {
		int completesupo_count = 0;
		completesupo_count = sqlSessionTemplate.selectOne("GetCompleteSupportersCount");
		return completesupo_count;
	}

	public int getClosedSupportersCount() {
		int closesupo_count = 0;
		closesupo_count = sqlSessionTemplate.selectOne("GetClosedSupportersCount");
		return closesupo_count;
	}
	
	

	
	
}



