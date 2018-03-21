package com.viptrip.wetrip.model.flight;

import java.io.Serializable;
import java.util.List;

import com.viptrip.base.annotation.Description;
import com.viptrip.wetrip.model.flight.type.CabinType;
import com.viptrip.wetrip.model.flight.type.TripType;

public class ReqData_GetReschedueFlightList implements Serializable{

    private static final long serialVersionUID = -966426523966258972L;
    @Description("航程类型")
    public Integer tripType;
    @Description("航程信息")
    public List<ReqData_GetFlightList_TripInfo> tripInfo;
    @Description("出发仓位类型")
    public Integer cabinType;
    @Description("航空公司二字码")
    public String airline;
    @Description("乘客信息")
    public List<ReqData_GetFlightList_Passenger> passenger;
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((airline == null) ? 0 : airline.hashCode());
        result = prime * result
                + ((cabinType == null) ? 0 : cabinType.hashCode());
        result = prime * result
                + ((passenger == null) ? 0 : passenger.hashCode());
        result = prime * result
                + ((tripInfo == null) ? 0 : tripInfo.hashCode());
        result = prime * result
                + ((tripType == null) ? 0 : tripType.hashCode());
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
        ReqData_GetFlightList other = (ReqData_GetFlightList) obj;
        if (airline == null) {
            if (other.airline != null)
                return false;
        } else if (!airline.equals(other.airline))
            return false;
        if (cabinType == null) {
            if (other.cabinType != null)
                return false;
        } else if (!cabinType.equals(other.cabinType))
            return false;
        if (passenger == null) {
            if (other.passenger != null)
                return false;
        } else if (!passenger.equals(other.passenger))
            return false;
        if (tripInfo == null) {
            if (other.tripInfo != null)
                return false;
        } else if (!tripInfo.equals(other.tripInfo))
            return false;
        if (tripType == null) {
            if (other.tripType != null)
                return false;
        } else if (!tripType.equals(other.tripType))
            return false;
        return true;
    }
    public Integer getTripType() {
        return tripType;
    }
    public void setTripType(TripType tripType) {
        this.tripType = tripType.getCode();
    }
    public void setTripType(Integer tripType) {
        this.tripType = tripType;
    }
    public List<ReqData_GetFlightList_TripInfo> getTripInfo() {
        return tripInfo;
    }
    public void setTripInfo(List<ReqData_GetFlightList_TripInfo> tripInfo) {
        this.tripInfo = tripInfo;
    }
    public Integer getCabinType() {
        return cabinType;
    }
    public void setCabinType(CabinType cabinType) {
        this.cabinType = cabinType.getCode();
    }
    public void setCabinType(Integer cabinType) {
        this.cabinType = cabinType;
    }
    public String getAirline() {
        return airline;
    }
    public void setAirline(String airline) {
        this.airline = airline;
    }
    public List<ReqData_GetFlightList_Passenger> getPassenger() {
        return passenger;
    }
    public void setPassenger(List<ReqData_GetFlightList_Passenger> passenger) {
        this.passenger = passenger;
    }
    

}
