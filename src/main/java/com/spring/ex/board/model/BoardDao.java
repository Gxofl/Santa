package com.spring.ex.board.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.ex.utility.Paging;

@Component
public class BoardDao {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	private String namespace = "board.BoardBean";

	
	public int getTotalCount(Map<String, String> map) {
		int cnt = -1;
		cnt = sqlSessionTemplate.selectOne(namespace+".GetTotalCount", map);		
		return cnt;
	}

	public List<BoardBean> getAllBoard(Map<String, String> map, Paging pageInfo) {
		List<BoardBean> lists = new ArrayList<BoardBean>();
		
		RowBounds rowBounds = new RowBounds(pageInfo.getOffset(),pageInfo.getLimit());		
		lists = sqlSessionTemplate.selectList(namespace+".GetAllBoard",map,rowBounds);
		System.out.println("board lists.size(): "+lists.size());
		
		return lists;
	}

	public int insertBoard(BoardBean board) {
		int cnt = -1;	
		System.out.println("category: "+board.getCategory());
		cnt = sqlSessionTemplate.insert(namespace+".InsertBoard", board);
		System.out.println("cnt: "+cnt);
		return cnt;
	}

	public int updateCount(int num) {
		int cnt = -1;
		cnt = sqlSessionTemplate.update(namespace+".UpdateCount", num);
		return cnt;
	}

	public BoardBean getBoardByNum(int num) {
		BoardBean board = null;
		board = sqlSessionTemplate.selectOne(namespace+".GetBoardByNum", num);		
		return board;
	}

	public int deleteBoard(int num) {
		int cnt = -1;		
		cnt = sqlSessionTemplate.delete(namespace+".DeleteBoard",num);
		return cnt;
	}

	public int updateBoard(BoardBean board) {
		int cnt = -1;
		cnt = sqlSessionTemplate.update(namespace+".UpdateBoard", board);
		return cnt;
	}
	
	//관리자 페이지 - 다중삭제
	public int deleteBoardByAdmin(String[] board_num) {
		int cnt = -1;
		for(int i=0; i<board_num.length; i++) {
			cnt += sqlSessionTemplate.delete("DeleteBoardByAdmin", board_num[i]);
		}
		return cnt;
	}

	
}
