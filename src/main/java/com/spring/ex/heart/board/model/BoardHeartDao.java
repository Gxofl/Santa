package com.spring.ex.heart.board.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.ex.board.model.BoardBean;

@Component("BoardHeartDao")
public class BoardHeartDao {
		
	private static final Logger logger = LoggerFactory.getLogger(BoardHeartDao.class);
		
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	//boardCheckHeart :	게시판의 상세보기클릭하면 해당 게시글의 좋아요 상태 확인
	public int boardCheckHeart(BoardHeartBean bhBean) {
		int cnt = -1;
		try {
			cnt = sqlSessionTemplate.selectOne("boardHeart.checkHeart", bhBean);
			System.out.println("Dao - boardCheckHeart  cnt : " + cnt);
			if(cnt == 1) {
				System.out.println("좋아요 누름");
			} else {
				System.out.println("좋아요 안누름");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}//BoardCheckHeart

		
	//BoardInsertHeart : 게시판의 상세보기에서 좋아요를 클릭하면 좋아요 insert
	public int boardInsertHeart(BoardHeartBean bhBean) {
		int cnt = -1;
		try {
			cnt = sqlSessionTemplate.insert("boardHeart.insertHeart", bhBean);
			System.out.println("Dao - boardInsertHeart cnt : " + cnt);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}//boardInsertHeart
	
	//boardDeleteHeart : 게시판의 상세보기에서 좋아요를 클릭하면 좋아요 delete(좋아요 취소)
	public int boardDeleteHeart(BoardHeartBean bhBean) {
		int cnt = -1;
		cnt = sqlSessionTemplate.delete("boardHeart.deleteHeart", bhBean);
		System.out.println("Dao - BoardDeleteHeart cnt : " + cnt);
		return cnt;
		
	}//boardDeleteHeart

	//getBoardHeartTotal : 게시글의 좋아요 수 count
	public int getBoardHeartTotal(BoardHeartBean bhBean) {
		int cnt = -1;
		cnt = sqlSessionTemplate.selectOne("boardHeart.getBoardHeartTotal", bhBean);
		System.out.println("Dao - getBoardHeartTotal cnt : " + cnt);
		return cnt;
		
	}//getBoardHeartTotal

	//getAllBoardHeart : 좋아요한 게시글 list
	public List<BoardHeartBean> getAllBoardHeart(String getUserId) {
		List<BoardHeartBean> boardHeartList = null;
		boardHeartList = sqlSessionTemplate.selectList("boardHeart.getAllBoardHeart", getUserId);
		System.out.println("Dao - getAllBoardHeart getUserId : " + getUserId);
		System.out.println("Dao - getAllBoardHeart BoardHeartList : " + boardHeartList);
		for(int i=0; i<boardHeartList.size(); i++) {
			System.out.println("list " + i + " : "  + boardHeartList.get(i));
		}
		return boardHeartList;
		
	}//getAllBoardHeart
	
	//getAllBoardHeart_Map : 좋아요한 게시글 list
	public List<BoardBean> getAllBoardHeart_Map(String getUserId) {
	    List<BoardBean> boardHeartList = null;
	    try {
	    	boardHeartList = sqlSessionTemplate.selectList("boardHeart.getAllBoardHeart_Map", getUserId);
		    //System.out.println("Dao - getAllBoardHeart getUserId : " + getUserId);
		    //System.out.println("Dao - getAllBoardHeart BoardHeartList : " + BoardHeartList);
	    }catch (Exception e) {
			e.printStackTrace();
		}
	    
	    return boardHeartList;
	}//getAllBoardHeart_Map
	

}//BoardHeartDao
