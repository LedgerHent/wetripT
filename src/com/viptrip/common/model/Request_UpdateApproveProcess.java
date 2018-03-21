package com.viptrip.common.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.viptrip.common.entity.TTmcApproveTraveller;
import com.viptrip.wetrip.model.base.Request_Base;

public class Request_UpdateApproveProcess extends Request_OrderBase {	
	public ApproveAuditModel audit;

	public ApproveAuditModel getAudit() {
		return audit;
	}

	public void setAudit(ApproveAuditModel audit) {
		this.audit = audit;
	}	
	
	
	
}
