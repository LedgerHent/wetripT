package com.viptrip.wetrip.model.flight;

import java.io.Serializable;
import java.util.List;

import com.viptrip.base.annotation.Description;
import com.viptrip.wetrip.model.flight.type.PassengerType;

/**
 * 乘客信息
 * @author selfwhisper
 *
 */
public class ReqData_GetFlightList_Passenger implements Serializable{
	private static final long serialVersionUID = -1658740053412305037L;
	
	@Description("乘客类型")
	public Integer type;
	
	@Description("乘客数量")
	public Integer count;
	
	@Description("乘客人集合")
	public List<ReqData_GetFlightList_PassengerMessage> passengers;
	
	
	
	
	public ReqData_GetFlightList_Passenger() {
	}

	public ReqData_GetFlightList_Passenger(Integer type, Integer count) {
		this.type = type;
		this.count = count;
	}

	public ReqData_GetFlightList_Passenger(Integer type, Integer count,List<ReqData_GetFlightList_PassengerMessage> passengers) {
        this.type = type;
        this.count = count;
        this.passengers=passengers;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((count == null) ? 0 : count.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		ReqData_GetFlightList_Passenger other = (ReqData_GetFlightList_Passenger) obj;
		if (count == null) {
			if (other.count != null)
				return false;
		} else if (!count.equals(other.count))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	public Integer getType() {
		return type;
	}
	
	public void setType(PassengerType type) {
		this.type = type.getCode();
	}
	
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}

    public List<ReqData_GetFlightList_PassengerMessage> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<ReqData_GetFlightList_PassengerMessage> passengers) {
        this.passengers = passengers;
    }
	
	
}
