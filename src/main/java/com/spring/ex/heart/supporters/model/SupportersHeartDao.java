package com.spring.ex.heart.supporters.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.ex.supporters.model.SupportersBean;


@Component("SupportersHeartDao")
public class SupportersHeartDao {
		
	private static final Logger logger = LoggerFactory.getLogger(SupportersHeartDao.class);
		
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	//supportersCheckHeart : 게시판의 상세보기클릭하면 해당 게시글의 좋아요 상태 확인
	public int supportersCheckHeart(SupportersHeartBean shBean) {
		int cnt = -1;
		try {
			cnt = sqlSessionTemplate.selectOne("supportersHeart.checkHeart", shBean);
			if(cnt == 1) {
				System.out.println("좋아요 누름");
			} else {
				System.out.println("좋아요 안누름");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}//supportersCheckHeart

		
	//supportersInsertHeart : 게시판의 상세보기에서 좋아요를 클릭하면 좋아요 insert
	public int supportersInsertHeart(SupportersHeartBean shbean) {
		int cnt = -1;
		try {
			cnt = sqlSessionTemplate.insert("supportersHeart.insertHeart", shbean);

		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}//supportersInsertHeart
	
	//supportersDeleteHeart : 게시판의 상세보기에서 좋아요를 클릭하면 좋아요 delete(좋아요 취소)
	public int supportersDeleteHeart(SupportersHeartBean shbean) {
		int cnt = -1;
		cnt = sqlSessionTemplate.delete("supportersHeart.deleteHeart", shbean);
		System.out.println("Dao - supportersDeleteHeart cnt : " + cnt);
		return cnt;
		
	}//supportersDeleteHeart

	//getSupportersHeartTotal : 게시글의 좋아요 수 count
	public int getSupportersHeartTotal(SupportersHeartBean shbean) {
		int cnt = -1;
		cnt = sqlSessionTemplate.selectOne("supportersHeart.getSupportersHeartTotal", shbean);
		System.out.println("Dao - getSupportersHeartTotal cnt : " + cnt);
		return cnt;
		
	}//getSupportersHeartTotal

	//getAllSupportersHeart : 좋아요한 게시글 list
	public List<SupportersHeartBean> getAllSupportersHeart(String getUserId) {
		List<SupportersHeartBean> supportersHeartList = null;
		supportersHeartList = sqlSessionTemplate.selectList("supportersHeart.getAllSupportersHeart", getUserId);
		
		for(int i=0; i<supportersHeartList.size(); i++) {
			System.out.println("list " + i + " : "  + supportersHeartList.get(i));
		}
		return supportersHeartList;
		
	}//getAllSupportersHeart
	
	//getAllSupportersHeart_Map : 좋아요한 게시글 list
	public List<SupportersBean> getAllSupportersHeart_Map(String getUserId) {
	    List<SupportersBean> supportersHeartList = null;
	    try {
	        supportersHeartList = sqlSessionTemplate.selectList("supportersHeart.getAllSupportersHeart_Map", getUserId);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return supportersHeartList;

	}//getAllSupportersHeart_Map
	

}//SupportersHeartDao
