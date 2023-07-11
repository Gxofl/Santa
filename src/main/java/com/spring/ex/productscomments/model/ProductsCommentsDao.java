package com.spring.ex.productscomments.model;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.ex.crewcomments.model.CrewCommentsBean;

@Component
public class ProductsCommentsDao {
	//private final String namespace="productscomments.ProductsCommentsBean";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	public List<ProductsCommentsBean> getProductsCommentsByIdx(int idx) {
		List<ProductsCommentsBean> comments = new ArrayList<ProductsCommentsBean>();
		comments = sqlSessionTemplate.selectList("GetProductsCommentsByIdx", idx);
		return comments;
	}

	public int insertProductsComments(ProductsCommentsBean pcmt_bean) {
		int cnt = -1;
		cnt = sqlSessionTemplate.insert("InsertProductsComments", pcmt_bean);
		return cnt;
	}
	
	public int replyProductsComments(ProductsCommentsBean pcmt_bean) {
		//1. 원래 댓글 re_step 수정
		int cnt = -1;
		cnt = sqlSessionTemplate.update("UpdateOrginProductsComment", pcmt_bean);
		if(cnt != -1) { // 수정 성공
			//2. 답 댓글 insert
			int re_step = pcmt_bean.getRestep()+1;
			pcmt_bean.setRestep(re_step);
			int re_level = pcmt_bean.getRelevel()+1;
			pcmt_bean.setRelevel(re_level);
			
			cnt = sqlSessionTemplate.insert("InsertReplyProductsComments", pcmt_bean);
		}
		return cnt;
	}

	public int deleteProductsComments(ProductsCommentsBean pcmt_bean) {
		int cnt = -1;
		cnt = sqlSessionTemplate.update("DeleteProductsComments", pcmt_bean); // 삭제된 댓글입니다라고 보이기
		return cnt;
	}

	public int updateProductsComments(ProductsCommentsBean pcmt_bean) {
		int cnt = -1;
		cnt = sqlSessionTemplate.update("UpdateProductsComments",pcmt_bean);
		return cnt;
	}

	public String getProductsCommentsOrgWriter(int ref) {
		String orgwriter = "";
		orgwriter = sqlSessionTemplate.selectOne("GetProductsCommentsOrgWriter", ref);
		return orgwriter;
	}
	
	
	
}
