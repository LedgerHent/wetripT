package com.viptrip.wetrip.model.flight;

import java.io.Serializable;

import com.viptrip.base.annotation.Description;
/**
 * 票规
 * @author selfwhisper
 *
 */
public class RespData_GetFlightDetail_TicketRule implements Serializable{
	private static final long serialVersionUID = 3709805325639587464L;
	@Description("退票规定")
	public String refund;
	@Description("改期规定")
	public String endorsement;
	@Description("签转规定")
	public String change;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((change == null) ? 0 : change.hashCode());
		result = prime * result
				+ ((endorsement == null) ? 0 : endorsement.hashCode());
		result = prime * result + ((refund == null) ? 0 : refund.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RespData_GetFlightDetail_TicketRule other = (RespData_GetFlightDetail_TicketRule) obj;
		if (change == null) {
			if (other.change != null)
				return false;
		} else if (!change.equals(other.change))
			return false;
		if (endorsement == null) {
			if (other.endorsement != null)
				return false;
		} else if (!endorsement.equals(other.endorsement))
			return false;
		if (refund == null) {
			if (other.refund != null)
				return false;
		} else if (!refund.equals(other.refund))
			return false;
		return true;
	}
	
	public String getRefund() {
		return refund;
	}
	public void setRefund(String refund) {
		this.refund = refund;
	}
	public String getEndorsement() {
		return endorsement;
	}
	public void setEndorsement(String endorsement) {
		this.endorsement = endorsement;
	}
	public String getChange() {
		return change;
	}
	public void setChange(String change) {
		this.change = change;
	}
	
	
}
