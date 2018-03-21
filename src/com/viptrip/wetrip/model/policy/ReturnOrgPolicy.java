package com.viptrip.wetrip.model.policy;

import java.util.List;
import java.util.Map;

import com.viptrip.wetrip.entity.policy.TOrgPolicyTicketManage;
import com.viptrip.wetrip.entity.policy.TPolicyTicket;


public class ReturnOrgPolicy {
	public Map<String,List<TOrgPolicyTicketManage>> tOrgPolicyTicketManages;//企业国内机票政策管理
	public Map<String,List<TPolicyTicket>> tPolicyTickets;//国内机票差旅政策
	public String returnStr;//(默认为0)1:乘机人信息错误   2:企业国内机票政策管理未开启或未维护！  
	                        //3:国内机票差旅政策未开启或未维护！ 4:企业差旅政策管理未开启或未维护,5:查询失败
	
	/**
	 * (默认为0)1:乘机人信息错误   2:企业国内机票政策管理未开启或未维护！  3:国内机票差旅政策未开启或未维护！ 4:企业差旅政策管理未开启或未维护,5:查询失败
	 * @return
	 */
	public String getReturnStr() {
		return returnStr;
	}
	public void setReturnStr(String returnStr) {
		this.returnStr = returnStr;
	}
	/**
	 * 企业国内机票政策管理
	 * @return
	 */
	public Map<String, List<TOrgPolicyTicketManage>> gettOrgPolicyTicketManages() {
		return tOrgPolicyTicketManages;
	}
	public void settOrgPolicyTicketManages(
			Map<String, List<TOrgPolicyTicketManage>> tOrgPolicyTicketManages) {
		this.tOrgPolicyTicketManages = tOrgPolicyTicketManages;
	}
	/**
	 * 国内机票差旅政策
	 * @return
	 */
	public Map<String, List<TPolicyTicket>> gettPolicyTickets() {
		return tPolicyTickets;
	}
	public void settPolicyTickets(Map<String, List<TPolicyTicket>> tPolicyTickets) {
		this.tPolicyTickets = tPolicyTickets;
	}
	
	
}
