package com.spring.ex.crewboard.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.ex.products.model.ProductsBean;
import com.spring.ex.utility.Paging;

@Component
public class CrewBoardDao {
	//private final String namespace = "crewboard.CrewBoardBean";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	public List<CrewBoardBean> getAllCrewboard(Map<String, Object> map, Paging pageInfo) {
		List<CrewBoardBean> lists = new ArrayList<CrewBoardBean>();
		RowBounds rowBounds = new RowBounds(pageInfo.getOffset(), pageInfo.getLimit());
		//lists = sqlSessionTemplate.selectList(namespace+".GetAllCrewboard", map, rowBounds);
		lists = sqlSessionTemplate.selectList("GetAllCrewboard", map, rowBounds);
		return lists;
	}

	public int insertCrewboard(CrewBoardBean cbb) {
		int cnt = -1;
		//cnt = sqlSessionTemplate.insert(namespace+".InsertCrewboard", cbb);
		cnt = sqlSessionTemplate.insert("InsertCrewboard", cbb);
		return cnt;
	}

	public CrewBoardBean getCrewboardByNum(int num) {
		// 1. 조회수 증가
		int cnt = -1;
		cnt = sqlSessionTemplate.update("UpdateReadCount", num);
		if(cnt != -1) {
			System.out.println("조회수 증가 성공");
		}else {
			System.out.println("조회수 증가 실패");
		}
		
		// 2. 게시물 가져오기
		CrewBoardBean cbb = sqlSessionTemplate.selectOne("GetCrewboardByNum", num);
		return cbb;
	}

	public CrewBoardBean getCrewboardByCrewname(String crewname) {
		//CrewBoardBean cbb = sqlSessionTemplate.selectOne(namespace+".GetCrewboardByCrewname", crewname);
		CrewBoardBean cbb = sqlSessionTemplate.selectOne("GetCrewboardByCrewname", crewname);
		return cbb;
	}

	public int updateCrewboardState(String crewname) {
		int cnt = -1;
		//cnt = sqlSessionTemplate.update(namespace+".UpdateCrewboardState", crewname);
		cnt = sqlSessionTemplate.update("UpdateCrewboardState", crewname);
		return cnt;
	}

	public int getCrewBoardTotalCount(Map<String, Object> map) {
		int totalCount = 0;
		totalCount = sqlSessionTemplate.selectOne("GetCrewBoardTotalCount", map);
		return totalCount;
	}

	public int updateCrewboard(CrewBoardBean cbb) {
		int cnt = -1;
		cnt = sqlSessionTemplate.update("UpdateCrewboard", cbb);
		return cnt;
	}

	public int deleteCrewboardByNum(int crewboardnum) {
		int cnt = -1;
		cnt = sqlSessionTemplate.delete("DeleteCrewboardByNum", crewboardnum);
		return cnt;
	}

	public boolean checkCrewboard(String crewname) {
		int cnt = 0;
		boolean result = false;
		cnt = sqlSessionTemplate.selectOne("CheckCrewboard", crewname);
		if(cnt == 1) { // 이미 있는 게시글
			result = true;
		}else {
			result = false;
		}
		return result;
	}

	public int deleteCrewboardByAdmin(String[] num) {
		int cnt = -1;
		for(int i=0; i<num.length; i++) {
			cnt += sqlSessionTemplate.delete("DeleteCrewBoardByAdmin", num[i]);
		}
		return cnt;
	}

	public int getCrewBoardTotalCount_Mypage(Map<String, Object> map) {
		int totalCount = 0;
		totalCount = sqlSessionTemplate.selectOne("GetCrewBoardTotalCount_Mypage", map);
		return totalCount;
	}

	public List<CrewBoardBean> getAllCrewBoardById(Map<String, Object> map, Paging pageInfo) {
		List<CrewBoardBean> lists = new ArrayList<CrewBoardBean>();
		RowBounds rowBounds = new RowBounds(pageInfo.getOffset(), pageInfo.getLimit());
		//lists = sqlSessionTemplate.selectList(namespace+".GetAllCrewboard", map, rowBounds);
		lists = sqlSessionTemplate.selectList("GetAllCrewboardById", map, rowBounds);
		return lists;
	}
	
	
}
