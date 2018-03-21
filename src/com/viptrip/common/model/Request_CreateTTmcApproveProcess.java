package com.viptrip.common.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.viptrip.common.entity.TTmcApproveTraveller;
import com.viptrip.wetrip.model.base.Request_Base;

public class Request_CreateTTmcApproveProcess extends Request_Base {
	public Long approvalId;	            
	public TTmcApproveProcessInfo data;
	
	
	public TTmcApproveProcessInfo getData() {
		return data;
	}
	public void setData(TTmcApproveProcessInfo data) {
		this.data = data;
	}
	public Long getApprovalId() {
		return approvalId;
	}
	public void setApprovalId(Long approvalId) {
		this.approvalId = approvalId;
	}
	
	
}
