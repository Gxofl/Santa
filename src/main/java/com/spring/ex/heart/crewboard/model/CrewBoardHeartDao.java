package com.spring.ex.heart.crewboard.model;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.ex.crewboard.model.CrewBoardBean;
import com.spring.ex.users.model.UsersBean;
import com.spring.ex.users.model.UsersDao;

@Component("CrewBoardHeartDao")
public class CrewBoardHeartDao {
		
	private static final Logger logger = LoggerFactory.getLogger(CrewBoardHeartDao.class);
		
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	//crewBoardCheckHeart :	크루게시판의 상세보기클릭하면 해당 게시글의 좋아요 상태 확인
	public int crewBoardCheckHeart(CrewBoardHeartBean cbhBean) {
		int cnt = -1;
		try {
			cnt = sqlSessionTemplate.selectOne("crewboardHeart.checkHeart", cbhBean);
			System.out.println("Dao - crewBoardCheckHeart  cnt : " + cnt);
			if(cnt == 1) {
				System.out.println("좋아요 누름");
			} else {
				System.out.println("좋아요 안누름");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}//crewBoardCheckHeart

		
	//crewBoardInsertHeart : 크루게시판의 상세보기에서 좋아요를 클릭하면 좋아요 insert
	public int crewBoardInsertHeart(CrewBoardHeartBean cbhBean) {
		int cnt = -1;
		try {
			cnt = sqlSessionTemplate.insert("crewboardHeart.insertHeart", cbhBean);
			System.out.println("Dao - crewBoardInsertHeart cnt : " + cnt);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}//crewBoardInsertHeart
	
	//crewBoardDeleteHeart : 크루게시판의 상세보기에서 좋아요를 클릭하면 좋아요 delete(좋아요 취소)
	public int crewBoardDeleteHeart(CrewBoardHeartBean cbhBean) {
		int cnt = -1;
		cnt = sqlSessionTemplate.delete("crewboardHeart.deleteHeart", cbhBean);
		System.out.println("Dao - crewBoardDeleteHeart cnt : " + cnt);
		return cnt;
		
	}//crewBoardDeleteHeart

	//getcrewBoardHeartTotal : 게시글의 좋아요 수 count
	public int getcrewBoardHeartTotal(CrewBoardHeartBean cbhBean) {
		int cnt = -1;
		cnt = sqlSessionTemplate.selectOne("crewboardHeart.getcrewBoardHeartTotal", cbhBean);
		System.out.println("Dao - getcrewBoardHeartTotal cnt : " + cnt);
		return cnt;
		
	}//getcrewBoardHeartTotal

	//getAllCrewBoardHeart : 좋아요한 게시글 list
	public List<CrewBoardHeartBean> getAllCrewBoardHeart(String getUserId) {
		List<CrewBoardHeartBean> crewBoardHeartList = null;
		crewBoardHeartList = sqlSessionTemplate.selectList("crewboardHeart.getAllCrewBoardHeart", getUserId);
		System.out.println("Dao - getAllCrewBoardHeart getUserId : " + getUserId);
		System.out.println("Dao - getAllCrewBoardHeart crewBoardHeartList : " + crewBoardHeartList);
		for(int i=0; i<crewBoardHeartList.size(); i++) {
			System.out.println("list " + i + " : "  + crewBoardHeartList.get(i));
		}
		return crewBoardHeartList;
		
	}//getAllCrewBoardHeart
	
	//getAllCrewBoardHeart_Map : 좋아요한 게시글 list
	public List<CrewBoardBean> getAllCrewBoardHeart_Map(String getUserId) {
	    List<CrewBoardBean> crewBoardHeartList = null;
	    try {
	    	crewBoardHeartList = sqlSessionTemplate.selectList("crewboardHeart.getAllCrewBoardHeart_Map", getUserId);
		    //System.out.println("Dao - getAllCrewBoardHeart getUserId : " + getUserId);
		    //System.out.println("Dao - getAllCrewBoardHeart crewBoardHeartList : " + crewBoardHeartList);
	    }catch (Exception e) {
			e.printStackTrace();
		}
	    
	    return crewBoardHeartList;
	}//getAllCrewBoardHeart_Map

}//CrewBoardHeartDao
