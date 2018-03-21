package com.viptrip.wetrip.vo;

public class UserInfo {
	public String id;//乘机人id
	public String userType;//乘机人类型 ：1:企业员工   2:常旅客
	public String passengerType;//乘机人类型 ：1:成人   2:儿童  3：婴儿
	public String userName;//乘机人名字
	public String userIdType;//乘机人证件类型
	public String userIdTypeStr;//乘机人证件类型中文
	public String userId;//乘机人证件号
	public String userPhone;//乘机人电话
	public String costAttachId;//乘机人费用归属
	public String insuraceId;//保险id
	public String isSaleInsuance;//是否预定保险  0：是   1：否
	public String insuancePrice;//保险卖价
	public String project;//项目号
	public String email;//邮箱
	public String costAttachName;//部门名字
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserIdType() {
		return userIdType;
	}
	public void setUserIdType(String userIdType) {
		this.userIdType = userIdType;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getCostAttachId() {
		return costAttachId;
	}
	public void setCostAttachId(String costAttachId) {
		this.costAttachId = costAttachId;
	}
	public String getInsuraceId() {
		return insuraceId;
	}
	public void setInsuraceId(String insuraceId) {
		this.insuraceId = insuraceId;
	}
	public String getIsSaleInsuance() {
		return isSaleInsuance;
	}
	public void setIsSaleInsuance(String isSaleInsuance) {
		this.isSaleInsuance = isSaleInsuance;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getInsuancePrice() {
		return insuancePrice;
	}
	public void setInsuancePrice(String insuancePrice) {
		this.insuancePrice = insuancePrice;
	}
	public String getUserIdTypeStr() {
		return userIdTypeStr;
	}
	public void setUserIdTypeStr(String userIdTypeStr) {
		this.userIdTypeStr = userIdTypeStr;
	}
	public String getPassengerType() {
		return passengerType;
	}
	public void setPassengerType(String passengerType) {
		this.passengerType = passengerType;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCostAttachName() {
		return costAttachName;
	}
	public void setCostAttachName(String costAttachName) {
		this.costAttachName = costAttachName;
	}
	
	
}
