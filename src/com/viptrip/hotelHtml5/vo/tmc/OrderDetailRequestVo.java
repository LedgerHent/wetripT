package com.viptrip.hotelHtml5.vo.tmc;

public class OrderDetailRequestVo extends OrderInfoRequestVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6358962225076777345L;
	
	@Deprecated
	private String hotelBookingJson;
	
	/**
	 * 可预订权限
	 */
	private String bookRightType;
	/**
	 * 企业是否审核	0-不需要，1-需要
	 */
	private String enterpverify;
	/**
	 * 企业项目编号是否必填，或不展示	项目号状态，0-不显示，1-选填，2-必填
	 */
	private String enterpProjectNo;
	
	private int adultCount;
	private int childCount;
	
	private String phk;
	
	/**
	 * 确认是否预订
	 */
	private boolean confirmBooking;

	/**
	 *审批政策匹配的个数 
	 */
	private int plicyRuleListLength;
	
	/**
	 * 入住天数
	 */
	private Integer nightCounts;
	/**
	 * 酒店模板
	 */
	private String pageTempletFlag;
	/**
	 * 洲ID
	 */
	private String deltaId;
	
	//----------------------------------------------三期差旅管控---------------------------------
	private ApprovalDetail approvalDetail;

	public String getBookRightType() {
		return bookRightType;
	}

	public void setBookRightType(String bookRightType) {
		this.bookRightType = bookRightType;
	}

	public String getEnterpverify() {
		return enterpverify;
	}

	public void setEnterpverify(String enterpverify) {
		this.enterpverify = enterpverify;
	}

	public String getEnterpProjectNo() {
		return enterpProjectNo;
	}

	public void setEnterpProjectNo(String enterpProjectNo) {
		this.enterpProjectNo = enterpProjectNo;
	}

	public int getAdultCount() {
		return adultCount;
	}

	public void setAdultCount(int adultCount) {
		this.adultCount = adultCount;
	}

	public int getChildCount() {
		return childCount;
	}

	public void setChildCount(int childCount) {
		this.childCount = childCount;
	}

	public String getPhk() {
		return phk;
	}

	public void setPhk(String phk) {
		this.phk = phk;
	}

	public boolean isConfirmBooking() {
		return confirmBooking;
	}

	public void setConfirmBooking(boolean confirmBooking) {
		this.confirmBooking = confirmBooking;
	}

	public int getPlicyRuleListLength() {
		return plicyRuleListLength;
	}

	public void setPlicyRuleListLength(int plicyRuleListLength) {
		this.plicyRuleListLength = plicyRuleListLength;
	}

	public String getPageTempletFlag() {
		return pageTempletFlag;
	}

	public void setPageTempletFlag(String pageTempletFlag) {
		this.pageTempletFlag = pageTempletFlag;
	}

	public String getDeltaId() {
		return deltaId;
	}

	public void setDeltaId(String deltaId) {
		this.deltaId = deltaId;
	}

	public Integer getNightCounts() {
		return nightCounts;
	}

	public void setNightCounts(Integer nightCounts) {
		this.nightCounts = nightCounts;
	}

	public ApprovalDetail getApprovalDetail() {
		return approvalDetail;
	}

	public void setApprovalDetail(ApprovalDetail approvalDetail) {
		this.approvalDetail = approvalDetail;
	}
	
}
