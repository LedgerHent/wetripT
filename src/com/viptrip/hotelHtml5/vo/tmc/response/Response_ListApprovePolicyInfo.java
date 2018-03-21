package com.viptrip.hotelHtml5.vo.tmc.response;

import java.util.List;

import etuf.v1_0.model.v2.base.Response_Base;

public class Response_ListApprovePolicyInfo extends Response_Base{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6490871517037490619L;

	private List<ApprovalInfo> data;
	
	public static class ApprovalInfo{
		/**
		 * 审批流程id	整型数字
		 */
		private int id;
		/**
		 * 审批流程名称	字符串
		 */
		private String name;
		/**
		 * 审批流程描述	字符串
		 */
		private String desc;
		/**
		 * 业务类型	整型数字， 1-国内机票|2-国际机票|3-国内酒店|4-国际酒店|5-火车票|6-签证|7-租车
		 */
		private int businessType;
		/**
		 * 审批类型	整型数字， 1-常规审批|2-超规审批
		 */
		private int approvalType;
		/**
		 * 匹配类型，数字越大，优先级越高	整型数字，1-按企业|2-按分组|3-按部门|4-按员工
		 */
		private int matchType;
		/**
		 * 匹配值	整型数字，分别对应企业id，分组id，部门id，员工id
		 */
		private int matchValue;
		/**
		 * 匹配值对应的名称	字符串，企业、分组、部门、员工名称
		 */
		private String matchName;
		
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		public int getBusinessType() {
			return businessType;
		}
		public void setBusinessType(int businessType) {
			this.businessType = businessType;
		}
		public int getApprovalType() {
			return approvalType;
		}
		public void setApprovalType(int approvalType) {
			this.approvalType = approvalType;
		}
		public int getMatchType() {
			return matchType;
		}
		public void setMatchType(int matchType) {
			this.matchType = matchType;
		}
		public int getMatchValue() {
			return matchValue;
		}
		public void setMatchValue(int matchValue) {
			this.matchValue = matchValue;
		}
		public String getMatchName() {
			return matchName;
		}
		public void setMatchName(String matchName) {
			this.matchName = matchName;
		}
		
	}

	public List<ApprovalInfo> getData() {
		return data;
	}

	public void setData(List<ApprovalInfo> data) {
		this.data = data;
	}
	
}
