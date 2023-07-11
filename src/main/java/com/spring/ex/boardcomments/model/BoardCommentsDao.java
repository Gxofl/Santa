package com.spring.ex.boardcomments.model;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.ex.board.model.BoardBean;

@Component
public class BoardCommentsDao {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	
	public int insertBoardComments(BoardCommentsBean bcmt) {		
		int cnt = -1; 
		cnt = sqlSessionTemplate.insert("InsertBoardComments", bcmt); 
		System.out.println();
		return cnt;
	}

	public List<BoardCommentsBean> getAllBoardComments() {
		List<BoardCommentsBean> lists = new ArrayList<BoardCommentsBean>();
		lists = sqlSessionTemplate.selectList("GetAllBoardComments");
		System.out.println("boardcomments lists.size(): "+lists.size());
		return lists;
	}

	public List<BoardCommentsBean> getBoardCommentsByBnum(int bnum) {
		List<BoardCommentsBean> comments = new ArrayList<BoardCommentsBean>();
		comments = sqlSessionTemplate.selectList("GetBoardCommentsByBnum", bnum);
		return comments;
	}

	public int deleteBoardComments(BoardCommentsBean boardcomments) {
		int cnt = -1;
		cnt = sqlSessionTemplate.update("DeleteBoardComments", boardcomments);
		return cnt;
	}

	public int replyBoardComments(BoardCommentsBean boardcomments) {
		//1. 원래 댓글 re_step을 수정
		int cnt = -1;
		cnt = sqlSessionTemplate.update("UpdateOrginComments", boardcomments);
		if(cnt != -1) { // 수정 성공
			//2. 답 댓글 insert
			int re_step = boardcomments.getRestep()+1;
			boardcomments.setRestep(re_step);
			int re_level = boardcomments.getRelevel()+1;
			boardcomments.setRelevel(re_level);
			cnt = sqlSessionTemplate.insert("InsertReplyBoardComments", boardcomments);
		}
		return cnt;
	}

	public int updateBoardComments(BoardCommentsBean boardcomments) {
		int cnt = -1;
		cnt = sqlSessionTemplate.update("UpdateBoardComments",boardcomments);
		return cnt;
	}

	public String getBoardCommentsOrgWriter(int ref) {
		String orgwriter = "";
		orgwriter = sqlSessionTemplate.selectOne("GetBoardCommentsOrgWriter", ref);
		return orgwriter;
	}
	
	
	
}
