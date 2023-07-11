package com.spring.ex.graph.model;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CrewGraphDao {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public int getAllCrewCount2() {
		int totalcrew = 0;
		totalcrew = sqlSessionTemplate.selectOne("GetAllCrewCount2");
		return totalcrew;
	}

	public int getOneDayCrewCount() {
		int onedaycrew = 0;
		onedaycrew = sqlSessionTemplate.selectOne("GetOneDayCrewCount");
		return onedaycrew;
	}

	public int getRegularCrewCount() {
		int regularcrew = 0;
		regularcrew = sqlSessionTemplate.selectOne("GetRegularCrewCount");
		return regularcrew;
	}

	public int getMountainCrewCount2() {
		int mountaincrew = 0;
		mountaincrew = sqlSessionTemplate.selectOne("GetMountainCrewCount2");
		return mountaincrew;
	}

	public int getPloggingCrewCount2() {
		int ploggincrew = 0;
		ploggincrew = sqlSessionTemplate.selectOne("GetPloggingCrewCount2");
		return ploggincrew;
	}
	
	
}
