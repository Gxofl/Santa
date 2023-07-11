package com.spring.ex.graph.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.spring.ex.graph.model.CrewGraphBean;
import com.spring.ex.graph.model.CrewGraphDao;

@Controller
public class CrewGraphController {
	private final String command = "/crewgraph/admin/crew.grp";
	
	@Autowired
	CrewGraphDao cg_dao;
	
	/*
	 크루통계 ajax 
	 */
	@ResponseBody
	@RequestMapping(command)
	public List<CrewGraphBean> doAction() {
		CrewGraphBean cgb = new CrewGraphBean();
		int totalcrew = cg_dao.getAllCrewCount2();
		int onedaycrew = cg_dao.getOneDayCrewCount();
		int regularcrew = cg_dao.getRegularCrewCount();
		int mountaincrew = cg_dao.getMountainCrewCount2();
		int ploggingcrew = cg_dao.getPloggingCrewCount2();
		
		cgb.setTotalcrew(totalcrew);
		cgb.setOnedaycrew(onedaycrew);
		cgb.setRegularcrew(regularcrew);
		cgb.setMountaincrew(mountaincrew);
		cgb.setPloggingcrew(ploggingcrew);
		
		List<CrewGraphBean> list = new ArrayList<CrewGraphBean>();
		list.add(cgb);
		
//		Gson gson = new Gson();
//		JsonArray jArray = new JsonArray();
//		
//		Iterator<CrewGraphBean> it = list.iterator();
//		while(it.hasNext()) {
//			CrewGraphBean cgb = it.next();
//			JsonObject object = new JsonObject();
//			
//		}
		return list;
	}
}
