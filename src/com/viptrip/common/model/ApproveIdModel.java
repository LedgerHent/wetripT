package com.viptrip.common.model;

public class ApproveIdModel {
	
	public String approvalId;

	public ApproveIdModel(String approvalId) {
		super();
		this.approvalId = approvalId;
	}



	@Override
	public int hashCode() {
		int result = 17;
		result = result * 31 + approvalId.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == this){
			return true;
		}
		if(!(obj instanceof ApproveIdModel)){
			return false;
		}
		ApproveIdModel mol = (ApproveIdModel) obj;

		return mol.approvalId.equals(approvalId);
	}
}
