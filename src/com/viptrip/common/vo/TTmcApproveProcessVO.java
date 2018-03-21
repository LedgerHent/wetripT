package com.viptrip.common.vo;

import java.util.List;

import com.viptrip.common.entity.TTmcApproveProcess;
import com.viptrip.common.entity.TTmcApproveTraveller;

public class TTmcApproveProcessVO extends TTmcApproveProcess {
	public List<TTmcApproveTraveller> traveller;
	public Integer approvalType;
	public Integer businessType;
	
	public List<TTmcApproveTraveller> getTraveller() {
		return traveller;
	}
	public void setTraveller(List<TTmcApproveTraveller> traveller) {
		this.traveller = traveller;
	}
	public Integer getApprovalType() {
		return approvalType;
	}
	public void setApprovalType(Integer approvalType) {
		this.approvalType = approvalType;
	}
	public Integer getBusinessType() {
		return businessType;
	}
	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}

}
