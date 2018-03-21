package com.viptrip.hotelHtml5.vo.tmc;

import java.util.List;

public class ApprovalDetail {
	/**
	 * 审批流程id 整型数字
	 */
	private Integer id;
	/**
	 * 审批流程名称 字符串
	 */
	private String name;
	/**
	 * 审批流程描述 字符串
	 */
	private String desc;
	/**
	 * 业务类型 整型数字， 1-国内机票|2-国际机票|3-国内酒店|4-国际酒店|5-火车票|6-签证|7-租车
	 */
	private Integer businessType;
	/**
	 * 审批类型 整型数字， 1-常规审批|2-超规审批
	 */
	private Integer approvalType;
	/**
	 * 审批类别 整型数字，1-同级审批|2-逐级审批
	 */
	private Integer type;

	private List<ApprovalFlow> approvals;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Integer getBusinessType() {
		return businessType;
	}

	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}

	public Integer getApprovalType() {
		return approvalType;
	}

	public void setApprovalType(Integer approvalType) {
		this.approvalType = approvalType;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<ApprovalFlow> getApprovals() {
		return approvals;
	}

	public void setApprovals(List<ApprovalFlow> approvals) {
		this.approvals = approvals;
	}

	public static class ApprovalFlow {
		/**
		 * 审批顺序号 整型数字，数字代表审批级别，如1表示一级审批
		 */
		private Integer flowId;
		/**
		 * 审批方式 整型数字，1-waitOne|2-waitAll|3-waitAny
		 */
		private Integer type;
		/**
		 * 同时审批人数 整型数字
		 */
		private Integer count;
		/**
		 * 审批人 JSONArray
		 */
		private List<Auditor> auditors;

		public Integer getFlowId() {
			return flowId;
		}

		public void setFlowId(Integer flowId) {
			this.flowId = flowId;
		}

		public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
			this.type = type;
		}

		public Integer getCount() {
			return count;
		}

		public void setCount(Integer count) {
			this.count = count;
		}

		public List<Auditor> getAuditors() {
			return auditors;
		}

		public void setAuditors(List<Auditor> auditors) {
			this.auditors = auditors;
		}

	}

	public static class Auditor {
		/**
		 * 审批人id 整型数字
		 */
		private String id;
		/**
		 * 审批人姓名 字符串
		 */
		private String name;
		/**
		 * 审批人手机号 字符串
		 */
		private String mobile;
		/**
		 * 审批人邮箱 字符串
		 */
		private String email;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
	}
}
