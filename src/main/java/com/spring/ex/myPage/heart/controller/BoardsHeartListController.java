package com.spring.ex.myPage.heart.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.ex.board.model.BoardBean;
import com.spring.ex.crewboard.model.CrewBoardBean;
import com.spring.ex.heart.board.model.BoardHeartDao;
import com.spring.ex.heart.crewboard.model.CrewBoardHeartDao;
import com.spring.ex.heart.products.model.ProductsHeartDao;
import com.spring.ex.heart.supporters.model.SupportersHeartDao;
import com.spring.ex.products.model.ProductsBean;
import com.spring.ex.supporters.model.SupportersBean;

@Controller
public class BoardsHeartListController {

	private final String command = "/heart/user/boardAll/getAllHeartList.ht";
	private String gotoPage = "/myPage/boardAll/boardsHeartList";
	
	//크루게시판
	@Autowired
	private CrewBoardHeartDao cbhdao;
	//게시판
	@Autowired
	private BoardHeartDao bhdao;
	//서포터즈게시판
	@Autowired
	private SupportersHeartDao shdao;
	//거래게시판
	@Autowired
	private ProductsHeartDao phdao;
	
	@RequestMapping(value = command)
	public ModelAndView BoardsHeartList(Principal principal) {
	    ModelAndView mav = new ModelAndView();
	    String getUserId = principal.getName();
	    System.out.println("myPage getUserId: " + getUserId);
	    
	    //크루게시판에서 좋아요 클릭한 게시글 확인
	    List<CrewBoardBean> crewBoardHeartList = null;
	    CrewBoardBean cbhBean = null;

	    try {
	        cbhBean = new CrewBoardBean();
	        crewBoardHeartList = cbhdao.getAllCrewBoardHeart_Map(getUserId);
	        System.out.println("crewBoardHeartList.size() : "+ crewBoardHeartList.size());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    //게시판에서 좋아요 클릭한 게시글 확인
	    List<BoardBean> BoardHeartList = null;
	    BoardBean bhBean = null;

	    try {
	    	bhBean = new BoardBean();
	        BoardHeartList = bhdao.getAllBoardHeart_Map(getUserId);
	        System.out.println("BoardHeartList.size() : "+ BoardHeartList.size());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    //서포터즈 게시판에서 좋아요 클릭한 게시글 확인
	    List<SupportersBean> SupportersHeartList = null;
	    SupportersBean shBean = null;

	    try {
	    	shBean = new SupportersBean();
	    	//SupportersHeartList = shdao.getAllSupportersHeart_Map(getUserId);
	        //System.out.println("SupportersHeartList.size() : "+ SupportersHeartList.size());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    //거래 게시판에서 좋아요 클릭한 게시글 확인
	    List<ProductsBean> ProductsHeartList = null;
	    ProductsBean phBean = null;

	    try {
	    	phBean = new ProductsBean();
	    	//ProductsHeartList = phdao.getAllProductsHeart_Map(getUserId);
	       // System.out.println("ProductsHeartList.size() : "+ ProductsHeartList.size());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    
	    //크루게시판
	    mav.addObject("cbhList", crewBoardHeartList);
	    //게시판
	    mav.addObject("bhList", BoardHeartList);
	    //서포터즈
	    mav.addObject("shList", SupportersHeartList);
	 	//거래
	    mav.addObject("phList", ProductsHeartList);
	    mav.setViewName(gotoPage);
	    return mav;
	}
	

}//BoardsHeartListController
