package com.spring.ex.qna.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.ex.utility.Paging;

@Component
public class QnaDao {
	
	//Qna namespace
	//private String namespace = "qna.QnaBean";
	
	//Sqlsessiontemplate 객체 생성
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public QnaDao() {
		System.out.println("QnaDao() 생성자");
	}//QnaDao 생성자 end
	
	//전체 목록 구하기
	public List<QnaBean> getAllQna(Map<String, String> map, Paging pageInfo) {
		RowBounds rowBounds = new RowBounds(pageInfo.getOffset(), pageInfo.getLimit());
		List<QnaBean> qnaList = sqlSessionTemplate.selectList("GetAllQna",map,rowBounds);
		System.out.println("dao qnsList : "+qnaList);
		return qnaList;
	}//getAllQna end
	
	//전체 갯수 구하기
	public int getQnaTotalCount(Map<String, String> map) {
		int cnt = 0;
		cnt = sqlSessionTemplate.selectOne("GetQnaTotalCount",map);
		System.out.println("cnt : "+cnt);
		return cnt;
	}//getQnaTotalCount end

	//새로운 qna 삽입
	public int insertQuestion(QnaBean qnaBean) {
		//int cnt = sqlSessionTemplate.insert(namespace+".InsertQ",qnaBean);
		int cnt = sqlSessionTemplate.insert("InsertQuestion",qnaBean);
		return cnt;
	}//insertQna end
	
	//선택한 qna 삭제
	public int deleteQna(int qnanum) {
		int cnt = sqlSessionTemplate.delete("DeleteQna",qnanum);
		return cnt;
	}//deleteQna end

	//qnanum으로 하나의 칼럼 검색
	public QnaBean getQnaByNum(int qnanum) {
		QnaBean qnaBean = sqlSessionTemplate.selectOne("GetQnaByNum",qnanum);
		return qnaBean;
	}
	
	//답변 등록/수정
	public int insertAnswer(Map<String, String> map) {
		int cnt = sqlSessionTemplate.update("InsertAnswer",map);
		return cnt;
	}

	public int updateQuestion(QnaBean qnaBean) {
		int cnt = sqlSessionTemplate.update("UpdateQuestion",qnaBean);
		return cnt;
	}
	
}
