package com.spring.ex.notice.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.ex.utility.Paging;

@Component
public class NoticeDao {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	private String namespace = "notice.NoticeBean";

	
	public int getTotalCount(Map<String, String> map) {
		int cnt = -1;
		cnt = sqlSessionTemplate.selectOne(namespace+".GetTotalCount", map);		
		return cnt;
	}

	public List<NoticeBean> getAllNotice(Map<String, String> map, Paging pageInfo) {
		List<NoticeBean> lists = new ArrayList<NoticeBean>();
		
		RowBounds rowBounds = new RowBounds(pageInfo.getOffset(),pageInfo.getLimit());		
		lists = sqlSessionTemplate.selectList("GetAllNotice",map,rowBounds);
		System.out.println("lists.size(): "+lists.size());
		
		return lists;
	}

	public int updateCount(int num) {
		int cnt = -1;
		cnt = sqlSessionTemplate.update(namespace+".UpdateCount", num);
		return cnt;
	}

	public NoticeBean getNoticeByNum(int num) {
		NoticeBean notice = null;
		notice = sqlSessionTemplate.selectOne(namespace+".GetNoticeByNum", num);		
		return notice;
	}

	public int deleteNotice(int num) {
		int cnt = -1;		
		cnt = sqlSessionTemplate.delete(namespace+".DeleteNotice",num);
		return cnt;
	}

	public int insertNotice(NoticeBean notice) {
		int cnt = -1;
		cnt = sqlSessionTemplate.insert(namespace+".InsertNotice", notice);
		return cnt;
	}

	public int updateNotice(NoticeBean notice) {
		int cnt = -1;
		cnt = sqlSessionTemplate.update(namespace+".UpdateNotice", notice);
		return cnt;
	}
	
	//관리자 페이지 : 다중삭제
	public int deleteNoticeByAdmin(String[] num) {
		int cnt = -1;
		for(int i=0; i<num.length; i++) {
			cnt += sqlSessionTemplate.delete("DeleteNoticeByAdmin", num[i]);
		}
		return cnt;
	}
	
}
