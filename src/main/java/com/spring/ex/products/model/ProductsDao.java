package com.spring.ex.products.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.ex.utility.Paging;

@Component
public class ProductsDao {
	//private final String namespace ="products.ProductsBean";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	public int insertProducts(ProductsBean pb) {
		int cnt = -1;
		//cnt = sqlSessionTemplate.insert(namespace+".InsertProducts", pb);
		cnt = sqlSessionTemplate.insert("InsertProducts", pb);
		return cnt;
	}

	public List<ProductsBean> getAllProducts(Map<String, Object> map, Paging pageInfo) {
		List<ProductsBean> plist = new ArrayList<ProductsBean>();
		//plist = sqlSessionTemplate.selectList(namespace+".GetAllProducts");
		RowBounds rowBounds = new RowBounds(pageInfo.getOffset(), pageInfo.getLimit());
		plist = sqlSessionTemplate.selectList("GetAllProducts", map , rowBounds);
		return plist;
	}

	public ProductsBean getProductsByNum(int num) {
		// 1. 조회수 증가
		int cnt = -1;
		cnt = sqlSessionTemplate.update("UpdateProductsReadCount", num);
		if(cnt != -1) {
			System.out.println("조회수 증가 성공");
		}else {
			System.out.println("조회수 증가 실패");
		}
		
		// 2. products 정보 가져오기
		ProductsBean pb = new ProductsBean();
		pb = sqlSessionTemplate.selectOne("GetProductsByNum",num);
		return pb;
	}

	public int getProductsTotalCount(Map<String,Object> map) {
		int totalCount = 0;
		totalCount = sqlSessionTemplate.selectOne("GetProductsTotalCount",map);
		return totalCount;
	}

	public int updateProductsState(int productsnum) {
		int cnt = -1;
		cnt = sqlSessionTemplate.update("UpdateProductsState", productsnum);
		return cnt;
	}

	public int updateProducts(ProductsBean pb) {
		int cnt = -1;
		cnt = sqlSessionTemplate.update("UpdateProducts", pb);
		return cnt;
	}

	public int deleteProductsByNum(int productsnum) {
		int cnt = -1;
		cnt = sqlSessionTemplate.delete("DeleteProductsByNum", productsnum);
		return cnt;
	}

	public int reUpdateProductsState(int productsnum) {
		int cnt = -1;
		cnt = sqlSessionTemplate.update("REUpdateProducts", productsnum);
		return cnt;
	}

	public List<ProductsBean> getSellProductsById(String loginId) {
		List<ProductsBean> sell_prd_list = new ArrayList<ProductsBean>();
		sell_prd_list = sqlSessionTemplate.selectList("GetSellProductsById", loginId);
		return sell_prd_list;
	}
	
	// 관리자 페이지에서 다중삭제
	public int deleteProductsByAdmin(String[] productsnum) {
		int cnt = -1;
		for(int i=0; i<productsnum.length; i++) {
			cnt += sqlSessionTemplate.delete("DeleteProductsByAdmin", productsnum[i]);
		}
		return cnt;
	}

	public List<ProductsBean> getAllProductsByAdmin(Map<String, Object> map, Paging pageInfo) {
		List<ProductsBean> plist = new ArrayList<ProductsBean>();
		//plist = sqlSessionTemplate.selectList(namespace+".GetAllProducts");
		RowBounds rowBounds = new RowBounds(pageInfo.getOffset(), pageInfo.getLimit());
		plist = sqlSessionTemplate.selectList("GetAllProductsByAdmin", map , rowBounds);
		return plist;
	}

	public List<ProductsBean> getAllProductsById(Map<String,Object> map) {
		List<ProductsBean> mylist = new ArrayList<ProductsBean>();
		mylist = sqlSessionTemplate.selectList("GetAllProductsById", map);
		return mylist;
	}

	public int getProductsTotalCount_Mypage(Map<String, Object> map) {
		int totalCount = 0;
		totalCount = sqlSessionTemplate.selectOne("GetProductsTotalCount_Mypage",map);
		return totalCount;
	}
	
}
