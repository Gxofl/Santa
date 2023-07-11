package com.spring.ex.heart.products.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.ex.products.model.ProductsBean;

@Component("ProductsHeartDao")
public class ProductsHeartDao {
		
	private static final Logger logger = LoggerFactory.getLogger(ProductsHeartDao.class);
		
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	//productCheckHeart :	상품게시판의 상세보기클릭하면 해당 게시글의 좋아요 상태 확인
	public int productsCheckHeart(ProductsHeartBean phBean) {
		int cnt = -1;
		try {
			cnt = sqlSessionTemplate.selectOne("productsHeart.checkHeart", phBean);
			System.out.println("Dao - productCheckHeart  cnt : " + cnt);
			if(cnt == 1) {
				System.out.println("좋아요 누름");
			} else {
				System.out.println("좋아요 안누름");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}//productCheckHeart

		
	//productInsertHeart : 상품게시판의 상세보기에서 좋아요를 클릭하면 좋아요 insert
	public int productsInsertHeart(ProductsHeartBean phBean) {
		int cnt = -1;
		try {
			cnt = sqlSessionTemplate.insert("productsHeart.insertHeart", phBean);
			System.out.println("Dao - productInsertHeart cnt : " + cnt);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}//productInsertHeart
	
	//productsBoardDeleteHeart : 상품게시판의 상세보기에서 좋아요를 클릭하면 좋아요 delete(좋아요 취소)
	public int productsBoardDeleteHeart(ProductsHeartBean phBean) {
		int cnt = -1;
		cnt = sqlSessionTemplate.delete("productsHeart.deleteHeart", phBean);
		System.out.println("Dao - productsDeleteHeart cnt : " + cnt);
		return cnt;
		
	}//productsDeleteHeart

	//getproductsHeartTotal : 게시글의 좋아요 수 count
	public int getProductsHeartTotal(ProductsHeartBean phBean) {
		int cnt = -1;
		cnt = sqlSessionTemplate.selectOne("productsHeart.getProductsHeartTotal", phBean);
		System.out.println("Dao - getProductsHeartTotal cnt : " + cnt);
		return cnt;
		
	}//getProductsHeartTotal

	//getAllProductsHeart : 좋아요한 게시글 list
	public List<ProductsHeartBean> getAllProductsHeart(String getUserId) {
		List<ProductsHeartBean> productsHeartList = null;
		productsHeartList = sqlSessionTemplate.selectList("productsHeart.getAllProductsHeart", getUserId);
		for(int i=0; i<productsHeartList.size(); i++) {
			System.out.println("list " + i + " : "  + productsHeartList.get(i));
			System.out.println("====getAllProductsHeart====");
		}
		return productsHeartList;
		
	}//getAllProductsHeart
	
	//getAllProductsHeart_Map : 좋아요한 게시글 list
	public List<ProductsBean> getAllProductsHeart_Map(String getUserId) {
	    List<ProductsBean> productsHeartList = null;
	    try {
	    	productsHeartList = sqlSessionTemplate.selectList("productsHeart.getAllProductsHeart_Map", getUserId);
	    }catch (Exception e) {
	    	System.out.println("====getAllProductsHeart_Map====");
			e.printStackTrace();
		}
	    
	    return productsHeartList;
	}//getAllProductsHeart_Map
	

}//ProductsHeartDao
