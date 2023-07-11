package com.spring.ex.crew.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.ex.utility.Paging;

@Component("CrewDao")
public class CrewDao {
	//private final String namespace = "crew.CrewBean";
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public int checkCrewname(String input_name) {
		int cnt = -1;
		//cnt = sqlSessionTemplate.selectOne(namespace+".CheckCrewname", input_name);
		cnt = sqlSessionTemplate.selectOne("CheckCrewname", input_name);
		return cnt;
	}

	public int insertCrew(CrewBean cb) {
		int cnt = -1;
		//cnt = sqlSessionTemplate.insert(namespace+".InsertCrew", cb);
		cnt = sqlSessionTemplate.insert("InsertCrew", cb);
		return cnt;
	}

	public List<CrewBean> getCrewById(String loginId) {
		List<CrewBean> cb = new ArrayList<CrewBean>();
		System.out.println("crewdao 아이디 : "+loginId);
		cb = sqlSessionTemplate.selectList("GetCrewById", loginId);
//		for(int i=0; i<cb.size(); i++) {
//			System.out.println("crewdao 크루이름 : "+cb.get(i).getCrewname());
//			System.out.println("crewdao 설명 : "+cb.get(i).getCrewcontents());
//			System.out.println("crewdao 크루멤버 : "+cb.get(i).getCrewmember());
//			System.out.println("crewdao large : "+cb.get(i).getLarge());
//			System.out.println("crewdao small : "+cb.get(i).getSmall());
//			System.out.println("crewdao num : "+cb.get(i).getCrewnum());
//			System.out.println("crewdao limit : "+cb.get(i).getCrewlimit());
//			System.out.println("crewdao now : "+cb.get(i).getCrewnow());
//		}
		return cb;
	}

	public int updateCrew(CrewBean cb) {
		int cnt = -1;
		//cnt = sqlSessionTemplate.update(namespace+".UpdateCrew", cb);
		cnt = sqlSessionTemplate.update("UpdateCrew", cb);
		return cnt;
	}

	public int getAllCrewCount() {
		int allcrew_count = 0;
		allcrew_count = sqlSessionTemplate.selectOne("GetAllCrewCount");
		return allcrew_count;
	}

	public int getMountainCrewCount() {
		int mountaincrew_count = 0;
		mountaincrew_count = sqlSessionTemplate.selectOne("GetMountainCrewCount");
		return mountaincrew_count;
	}

	public int getPloggingCrewCount() {
		int ploggingcrew_count = 0;
		ploggingcrew_count = sqlSessionTemplate.selectOne("GetPloggingCrewCount");
		return ploggingcrew_count;
	}

	public int getFinishCrewCount() {
		int finishcrew_count = 0;
		finishcrew_count = sqlSessionTemplate.selectOne("GetFinishCrewCount");
		return finishcrew_count;
	}
	
	public List<CrewBean> getJoinCrewById(String loginId) {
		List<CrewBean> join_crew = new ArrayList<CrewBean>();
		String login_Id = "%"+loginId+"%";
		join_crew = sqlSessionTemplate.selectList("GetJoinCrewById", login_Id);
		return join_crew;
	}

	public List<CrewBean> getMyPageCrew(Map<String, Object> map) {
		List<CrewBean> myCrewList = new ArrayList<CrewBean>();
		myCrewList = sqlSessionTemplate.selectList("GetMyPageCrew", map);
		return myCrewList;
	}

	public List<CrewBean> getMyPageJoinCrew(Map<String, Object> map) {
		List<CrewBean> myJoinCrewList = new ArrayList<CrewBean>();
		String loginId = (String)map.get("loginId");
		map.put("loginId", "%"+loginId+"%");
		myJoinCrewList = sqlSessionTemplate.selectList("GetMyPageJoinCrew", map); 
		return myJoinCrewList;
	}

	public List<CrewBean> get1DayCrew(Map<String, Object> map, Paging pageInfo) {
		List<CrewBean> crewList = new ArrayList<CrewBean>();
		RowBounds rowBounds = new RowBounds(pageInfo.getOffset(), pageInfo.getLimit());
		crewList = sqlSessionTemplate.selectList("Get1DayCrew", map, rowBounds);
		return crewList;
	}

	public List<CrewBean> getRegularCrew(Map<String, Object> map, Paging pageInfo) {
		List<CrewBean> crewList = new ArrayList<CrewBean>();
		RowBounds rowBounds = new RowBounds(pageInfo.getOffset(), pageInfo.getLimit());
		crewList = sqlSessionTemplate.selectList("GetRegularCrew", map, rowBounds);
		return crewList;
	}

	public int get1DayCrewTotalCount(Map<String, Object> map) {
		int totalCount = 0;
		totalCount = sqlSessionTemplate.selectOne("Get1DayCrewTotalCount", map);
		return totalCount;
	}

	public int getRegularCrewTotalCount(Map<String, Object> map) {
		int totalCount = 0;
		totalCount = sqlSessionTemplate.selectOne("GetRegularCrewTotalCount", map);
		return totalCount;
	}

	public List<CrewBean> getJoinRegularCrewById(String login_Id) {
		List<CrewBean> crewList = new ArrayList<CrewBean>();
		String loginId = "%"+login_Id+"%";
		crewList = sqlSessionTemplate.selectList("GetJoinRegularCrewById", loginId);
		return crewList;
	}

	public int updateCrewState(String crewname) {
		int cnt = -1;
		cnt = sqlSessionTemplate.update("UpdateCrewStateByCrewName", crewname);
		return cnt;
	}
	
}
