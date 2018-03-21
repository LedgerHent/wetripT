package com.viptrip.wetrip.model.flight;

import java.io.Serializable;

import com.viptrip.base.annotation.Description;

/**
 * 航程信息
 * @author selfwhisper
 *
 */
public class ReqData_GetFlightList_TripInfo implements Serializable{
	private static final long serialVersionUID = 6684838704288483933L;
	@Description("航程编号")
	public Integer flowId;
	@Description("出发机场三字码")
	public String depAirport;
	@Description("到达机场三字码")
	public String arrAirport;
	@Description("出发日期")
	public String date;
	
	
	
	
	public ReqData_GetFlightList_TripInfo() {
	}
	
	/**
	 * 
	 * @param flowId 航程编号
	 * @param depAirport 出发城市
	 * @param arrAirport 到达城市
	 * @param date 出发日期
	 */
	public ReqData_GetFlightList_TripInfo(Integer flowId, String depAirport,
			String arrAirport, String date) {
		this.flowId = flowId;
		this.depAirport = depAirport;
		this.arrAirport = arrAirport;
		this.date = date;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((arrAirport == null) ? 0 : arrAirport.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result
				+ ((depAirport == null) ? 0 : depAirport.hashCode());
		result = prime * result + ((flowId == null) ? 0 : flowId.hashCode());
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
		ReqData_GetFlightList_TripInfo other = (ReqData_GetFlightList_TripInfo) obj;
		if (arrAirport == null) {
			if (other.arrAirport != null)
				return false;
		} else if (!arrAirport.equals(other.arrAirport))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (depAirport == null) {
			if (other.depAirport != null)
				return false;
		} else if (!depAirport.equals(other.depAirport))
			return false;
		if (flowId == null) {
			if (other.flowId != null)
				return false;
		} else if (!flowId.equals(other.flowId))
			return false;
		return true;
	}
	public Integer getFlowId() {
		return flowId;
	}
	public void setFlowId(Integer flowId) {
		this.flowId = flowId;
	}
	public String getDepAirport() {
		return depAirport;
	}
	public void setDepAirport(String depAirport) {
		this.depAirport = depAirport;
	}
	public String getArrAirport() {
		return arrAirport;
	}
	public void setArrAirport(String arrAirport) {
		this.arrAirport = arrAirport;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	
}
