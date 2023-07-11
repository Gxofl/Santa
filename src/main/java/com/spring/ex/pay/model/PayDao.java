package com.spring.ex.pay.model;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PayDao {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	public int insertPay(PayBean pay_bean) {
		int cnt = -1;
		cnt = sqlSessionTemplate.insert("InsertPay", pay_bean);
		return cnt;
	}

	public PayBean getPayByPrdnum(PayBean pay_bean) {
		PayBean pay_result = sqlSessionTemplate.selectOne("GetPayByPrdnum", pay_bean);
		return pay_result;
	}
	
	public PayBean getPayByPaynum(int paynum) {
		PayBean pay_result = sqlSessionTemplate.selectOne("GetPayByPaynum", paynum);
		return pay_result;
	}

	public List<PayBean> getMyPagePay(String loginId) {
		List<PayBean> myPayList = new ArrayList<PayBean>();
		myPayList = sqlSessionTemplate.selectList("GetMyPagePay", loginId);
		return myPayList;
	}

	public List<PayBean> getWaitPay() {
		List<PayBean> waitPayList = new ArrayList<PayBean>();
		waitPayList = sqlSessionTemplate.selectList("GetWaitPay");
		return waitPayList;
	}

	public int updatePayState(int paynum) {
		int cnt = -1;
		cnt = sqlSessionTemplate.update("UpdatePayState", paynum);
		return cnt;
	}

	public List<PayBean> getFinishPay() {
		List<PayBean> finishPayList = new ArrayList<PayBean>();
		finishPayList = sqlSessionTemplate.selectList("GetFinishPay");
		return finishPayList;
	}

	public int updatePayStateByMySelf(int paynum) {
		int cnt = -1;
		cnt = sqlSessionTemplate.update("UpdatePayStateByMySelf", paynum);
		return cnt;
	}

	public int updatePayStateByAdmin(int paynum) {
		int cnt = -1;
		cnt = sqlSessionTemplate.update("UpdatePayStateByAdmin", paynum);
		return cnt;
	}

	public List<PayBean> getCancelPay() {
		List<PayBean> cancelPayList = new ArrayList<PayBean>();
		cancelPayList = sqlSessionTemplate.selectList("GetCancelPay");
		return cancelPayList;
	}
}
