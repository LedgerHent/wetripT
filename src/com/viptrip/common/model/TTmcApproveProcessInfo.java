package com.viptrip.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class TTmcApproveProcessInfo implements Serializable {
	public Integer businessType;
	public Integer orderid;
	public String orderno;
	public Long orgId;
	public String orgName;
	public Integer travelType;
	public Integer payType;
	public String productName;
	public String productDetail;
	public String extend;
	public BigDecimal amount;
	
	public Date bookTime;
	public String status;
	public List<ApproveProcessTravellerMode> traveller;	
	public BookerInfo booker;
	public Integer approvalType;
	public Integer processState;
	
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public Integer getBusinessType() {
		return businessType;
	}
	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}
	public Integer getOrderid() {
		return orderid;
	}
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}	
	public Integer getTravelType() {
		return travelType;
	}
	public void setTravelType(Integer travelType) {
		this.travelType = travelType;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDetail() {
		return productDetail;
	}
	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}
	public String getExtend() {
		return extend;
	}
	public void setExtend(String extend) {
		this.extend = extend;
	}
	
	public Date getBookTime() {
		return bookTime;
	}
	public void setBookTime(Date bookTime) {
		this.bookTime = bookTime;
	}
	
	public List<ApproveProcessTravellerMode> getTraveller() {
		return traveller;
	}
	public void setTraveller(List<ApproveProcessTravellerMode> traveller) {
		this.traveller = traveller;
	}
	public BookerInfo getBooker() {
		return booker;
	}
	public void setBooker(BookerInfo booker) {
		this.booker = booker;
	}
	public Integer getApprovalType() {
		return approvalType;
	}
	public void setApprovalType(Integer approvalType) {
		this.approvalType = approvalType;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getProcessState() {
		return processState;
	}
	public void setProcessState(Integer processState) {
		this.processState = processState;
	}	
	

}
