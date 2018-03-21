package com.viptrip.wetrip.model;

import java.util.List;

import com.viptrip.base.annotation.Description;
import com.viptrip.wetrip.model.base.Request_UserId;
import com.viptrip.wetrip.model.flight.ReqData_GetFlightList_Passenger;
import com.viptrip.wetrip.model.flight.ReqData_GetFlightList_PassengerMessage;
/**
 * 航班详情查询请求信息
 * @author selfwhisper
 *
 */
public class Request_GetFlightDetail extends Request_UserId{
	private static final long serialVersionUID = -7440213542212726072L;
	
	@Description("航班的Key")
	public String mapKey;
	
	@Description("出行方式 1=因公 2=因私")
    public Integer tripWay;
	
	@Description("乘机人信息List")
	public List<ReqData_GetFlightList_PassengerMessage> passengers;
	
	
	public Request_GetFlightDetail(String mapKey,Long userId) {
		super(userId);
		this.mapKey = mapKey;
	}
	public Request_GetFlightDetail(String mapKey,Long userId,List<ReqData_GetFlightList_PassengerMessage> passengers) {
        super(userId);
        this.mapKey = mapKey;
        this.passengers=passengers;
    }
	
	public Request_GetFlightDetail(String mapKey,Long userId,Integer tripWay,List<ReqData_GetFlightList_PassengerMessage> passengers) {
        super(userId);
        this.mapKey = mapKey;
        this.tripWay = tripWay;
        this.passengers = passengers;
    }
	public Request_GetFlightDetail() {
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((mapKey == null) ? 0 : mapKey.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request_GetFlightDetail other = (Request_GetFlightDetail) obj;
		if (mapKey == null) {
			if (other.mapKey != null)
				return false;
		} else if (!mapKey.equals(other.mapKey))
			return false;
		return true;
	}
	
	public String getMapKey() {
		return mapKey;
	}
	public void setMapKey(String mapKey) {
		this.mapKey = mapKey;
	}
    public List<ReqData_GetFlightList_PassengerMessage> getPassengers() {
        return passengers;
    }
    public void setPassengers(List<ReqData_GetFlightList_PassengerMessage> passengers) {
        this.passengers = passengers;
    }
    public Integer getTripWay() {
        return tripWay;
    }
    public void setTripWay(Integer tripWay) {
        this.tripWay = tripWay;
    }
	
	
	
}
