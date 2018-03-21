package com.viptrip.wetrip.model.flight;

import java.io.Serializable;
import java.util.Date;

import com.viptrip.base.annotation.Description;
/**
 * 经停信息
 * @author selfwhisper
 *
 */
public class RespData_GetFlightDetail_Stop implements Serializable{
	private static final long serialVersionUID = -493375762089128083L;
	@Description("经停机场三字码")
	public String airportCode;
	@Description("到达经停机场时间")
	public Date arrDateTime;
	@Description("离开经停机场时间")
	public Date depDateTime;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((airportCode == null) ? 0 : airportCode.hashCode());
		result = prime * result
				+ ((arrDateTime == null) ? 0 : arrDateTime.hashCode());
		result = prime * result
				+ ((depDateTime == null) ? 0 : depDateTime.hashCode());
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
		RespData_GetFlightDetail_Stop other = (RespData_GetFlightDetail_Stop) obj;
		if (airportCode == null) {
			if (other.airportCode != null)
				return false;
		} else if (!airportCode.equals(other.airportCode))
			return false;
		if (arrDateTime == null) {
			if (other.arrDateTime != null)
				return false;
		} else if (!arrDateTime.equals(other.arrDateTime))
			return false;
		if (depDateTime == null) {
			if (other.depDateTime != null)
				return false;
		} else if (!depDateTime.equals(other.depDateTime))
			return false;
		return true;
	}
	public String getAirportCode() {
		return airportCode;
	}
	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}
	public Date getArrDateTime() {
		return arrDateTime;
	}
	public void setArrDateTime(Date arrDateTime) {
		this.arrDateTime = arrDateTime;
	}
	public Date getDepDateTime() {
		return depDateTime;
	}
	public void setDepDateTime(Date depDateTime) {
		this.depDateTime = depDateTime;
	}
	
	
}
