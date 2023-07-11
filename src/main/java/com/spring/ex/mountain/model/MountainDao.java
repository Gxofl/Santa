package com.spring.ex.mountain.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.ex.qna.model.QnaBean;
import com.spring.ex.utility.Paging;

@Component
public class MountainDao {

	//Mountain namespace
	//private String namespace = "mountain.MountainBean";
		
	//Sqlsessiontemplate 객체 생성
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
		
	//MountainDao 생성자 start
	public MountainDao() {
		System.out.println("MountainDao() 생성자");
	}//MountainDao 생성자 end

	public List<MountainBean> getAllMountainFirst() {
		List<MountainBean> mountainLists = sqlSessionTemplate.selectList("GetAllMountainFirst");
		return mountainLists;
	}

	public int getTotalCount(Map<String, String> searchMap) {
		int cnt = 0;
		cnt = sqlSessionTemplate.selectOne("GetMountainTotalCount",searchMap);
		return cnt;
	}

	public List<MountainBean> getAllMountain(Map<String, String> map, Paging pageInfo) {
		RowBounds rowBounds = new RowBounds(pageInfo.getOffset(), pageInfo.getLimit());
		List<MountainBean> mountainLists = sqlSessionTemplate.selectList("GetAllMountain",map,rowBounds);
		System.out.println("dao mountainLists : "+mountainLists);
		return mountainLists;
	}

	public int deleteMountain(String deleteNum) {
		int cnt = sqlSessionTemplate.delete("DeleteMountain",deleteNum);
		return cnt;
	}

	public int insertMountain(MountainBean mountainBean) {
		int cnt = sqlSessionTemplate.insert("InsertMountain",mountainBean);
		return cnt;
	}

	public int updateMountain(MountainBean mountainBean) {
		int cnt = sqlSessionTemplate.update("UpdateMountain",mountainBean);
		return cnt;
	}

	public MountainBean getMountainByNum(int mountainnum) {
		MountainBean mountainBean = sqlSessionTemplate.selectOne("GetMountainByNum", mountainnum);
		return mountainBean;
	}

	public int insertApi(MountainBean mb) {
		int cnt = sqlSessionTemplate.insert("InsertApi",mb);
		System.out.println("cnt : "+cnt);
		return cnt;
	}

	public List<MountainBean> getAllMountainName() {
		List<MountainBean> mountainNames = sqlSessionTemplate.selectList("GetAllMountainName");
		return mountainNames;
	}

	
}
