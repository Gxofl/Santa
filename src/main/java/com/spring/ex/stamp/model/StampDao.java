package com.spring.ex.stamp.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.ex.utility.Paging;

@Component
public class StampDao {

	//Stamp namespace
	//private String namespace = "mountain.StampBean";
			
	//Sqlsessiontemplate 객체 생성
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
			
	//StampDao 생성자 start
	public StampDao() {
		System.out.println("StampDao() 생성자");
	}//StampDao 생성자 end

	public List<StampBean> getAllStampById(String userid) {
		List<StampBean> stampList = sqlSessionTemplate.selectList("GetAllStampById",userid);
		return stampList;
	}

	public String getUserRoleById(String userid) {
		String userRole = sqlSessionTemplate.selectOne("GetUserRoleById", userid);
		return userRole;
	}

	public List<CertBean> getCertListById(String userid) {
		List<CertBean> certList = sqlSessionTemplate.selectList("GetCertListById",userid);
		return certList;
	}

	public int GetNumByName(String mountainname) {
		String mountainnum = sqlSessionTemplate.selectOne("GetNumByName",mountainname);
		System.out.println("mountainnum : "+mountainnum);
		return Integer.parseInt(mountainnum);
	}

	public int insertStampCert(StampBean stampBean) {
		int cnt = sqlSessionTemplate.insert("InsertStampCert",stampBean);
		return cnt;
	}

	public int getStampTotalCount(Map<String, String> map) {
		int cnt = 0;
		cnt = sqlSessionTemplate.selectOne("GetStampTotalCount",map);
		return cnt;
	}

	public List<StampBean> getAllStamp(Map<String, String> map, Paging pageInfo) {
		RowBounds rowBounds = new RowBounds(pageInfo.getOffset(), pageInfo.getLimit());
		List<StampBean> stampList = sqlSessionTemplate.selectList("GetAllStamp", map, rowBounds);
		return stampList;
	}

	public int updateApply(Map<String, Object> map) {
		int cnt = sqlSessionTemplate.update("UpdateApply", map);
		return cnt;
	}

	public CertBean getCertByNum(String stampnum) {
		CertBean certBean = sqlSessionTemplate.selectOne("GetCertByNum",stampnum);
		return certBean;
	}

	public int updateReject(Map<String, Object> map) {
		int cnt = sqlSessionTemplate.update("UpdateReject", map);
		return cnt;
	}

	public int updateMountainExist(String data) {
		int cnt = sqlSessionTemplate.update("UpdateMountainExist", data);
		return cnt;
	}

	public List<StampBean> getCertCountByMountainNum() {
		List<StampBean> certCount = sqlSessionTemplate.selectList("GetCertCountByMountainNum");
		return certCount;
	}

	public List<StampBean> getApplyCountGroupById() {
		List<StampBean> applyCount = sqlSessionTemplate.selectList("GetApplyCountGroupById");
		return applyCount;
	}

	public List<StampBean> getApplyListById(String usersid) {
		List<StampBean> applyList = sqlSessionTemplate.selectList("GetApplyListById",usersid);
		return applyList;
	}
	
	//delete요청을 받은 num에 해당하는 인증내역이 있는지 체크
	public List<CertBean> getCertListByNum(String check) {
		List<CertBean> certCheck = sqlSessionTemplate.selectList("GetCertListByNum",check);
		return certCheck;
	}

}
