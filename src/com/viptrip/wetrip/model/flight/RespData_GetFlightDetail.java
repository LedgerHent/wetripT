package com.viptrip.wetrip.model.flight;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.viptrip.base.annotation.Description;
import com.viptrip.wetrip.model.flight.RespData_GetFlightDetail_Cabin;
import com.viptrip.wetrip.model.flight.RespData_GetFlightDetail_Stop;

/**
 * 机票详情查询返回结果
 * @author selfwhisper
 *
 */
public class RespData_GetFlightDetail implements Serializable{
	private static final long serialVersionUID = 8188990981933557093L;
	@Description("航班号")
	public String flightNo;
	@Description("航空公司")
	public String airlineCode;
	@Description("共享航班号")
	public String shareFlightNo;
	@Description("共享航空公司二字码")
	public String shareAirlineCode;
	@Description("起飞日期时间")
	public Date depDateTime;
	@Description("到达日期时间")
	public Date arrDateTime;
	@Description("起飞机场")
	public String depAirport;
	@Description("到达机场")
	public String arrAirport;
	@Description("出发航站楼")
	public String depPointAT;
	@Description("到达航站楼")
	public String arrPointAT;
	@Description("F舱价格")
	public Double fPrice;
	@Description("C舱价格")
	public Double cPrice;
	@Description("Y舱价格")
	public Double yPrice;
	@Description("机场建设费")
	public Double airportTax;
	@Description("燃油费")
	public Double fuelSurTax;
	@Description("机型")
	public String planeType;
	@Description("舱位信息")
	public List<RespData_GetFlightDetail_Cabin> cabins;
	@Description("经停信息")
	public List<RespData_GetFlightDetail_Stop> stops;
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((airlineCode == null) ? 0 : airlineCode.hashCode());
		result = prime * result
				+ ((airportTax == null) ? 0 : airportTax.hashCode());
		result = prime * result
				+ ((arrAirport == null) ? 0 : arrAirport.hashCode());
		result = prime * result
				+ ((arrDateTime == null) ? 0 : arrDateTime.hashCode());
		result = prime * result
				+ ((arrPointAT == null) ? 0 : arrPointAT.hashCode());
		result = prime * result + ((cPrice == null) ? 0 : cPrice.hashCode());
		result = prime * result + ((cabins == null) ? 0 : cabins.hashCode());
		result = prime * result
				+ ((depAirport == null) ? 0 : depAirport.hashCode());
		result = prime * result
				+ ((depDateTime == null) ? 0 : depDateTime.hashCode());
		result = prime * result
				+ ((depPointAT == null) ? 0 : depPointAT.hashCode());
		result = prime * result + ((fPrice == null) ? 0 : fPrice.hashCode());
		result = prime * result
				+ ((flightNo == null) ? 0 : flightNo.hashCode());
		result = prime * result
				+ ((fuelSurTax == null) ? 0 : fuelSurTax.hashCode());
		result = prime * result
				+ ((planeType == null) ? 0 : planeType.hashCode());
		result = prime
				* result
				+ ((shareAirlineCode == null) ? 0 : shareAirlineCode.hashCode());
		result = prime * result
				+ ((shareFlightNo == null) ? 0 : shareFlightNo.hashCode());
		result = prime * result + ((stops == null) ? 0 : stops.hashCode());
		result = prime * result + ((yPrice == null) ? 0 : yPrice.hashCode());
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
		RespData_GetFlightDetail other = (RespData_GetFlightDetail) obj;
		if (airlineCode == null) {
			if (other.airlineCode != null)
				return false;
		} else if (!airlineCode.equals(other.airlineCode))
			return false;
		if (airportTax == null) {
			if (other.airportTax != null)
				return false;
		} else if (!airportTax.equals(other.airportTax))
			return false;
		if (arrAirport == null) {
			if (other.arrAirport != null)
				return false;
		} else if (!arrAirport.equals(other.arrAirport))
			return false;
		if (arrDateTime == null) {
			if (other.arrDateTime != null)
				return false;
		} else if (!arrDateTime.equals(other.arrDateTime))
			return false;
		if (arrPointAT == null) {
			if (other.arrPointAT != null)
				return false;
		} else if (!arrPointAT.equals(other.arrPointAT))
			return false;
		if (cPrice == null) {
			if (other.cPrice != null)
				return false;
		} else if (!cPrice.equals(other.cPrice))
			return false;
		if (cabins == null) {
			if (other.cabins != null)
				return false;
		} else if (!cabins.equals(other.cabins))
			return false;
		if (depAirport == null) {
			if (other.depAirport != null)
				return false;
		} else if (!depAirport.equals(other.depAirport))
			return false;
		if (depDateTime == null) {
			if (other.depDateTime != null)
				return false;
		} else if (!depDateTime.equals(other.depDateTime))
			return false;
		if (depPointAT == null) {
			if (other.depPointAT != null)
				return false;
		} else if (!depPointAT.equals(other.depPointAT))
			return false;
		if (fPrice == null) {
			if (other.fPrice != null)
				return false;
		} else if (!fPrice.equals(other.fPrice))
			return false;
		if (flightNo == null) {
			if (other.flightNo != null)
				return false;
		} else if (!flightNo.equals(other.flightNo))
			return false;
		if (fuelSurTax == null) {
			if (other.fuelSurTax != null)
				return false;
		} else if (!fuelSurTax.equals(other.fuelSurTax))
			return false;
		if (planeType == null) {
			if (other.planeType != null)
				return false;
		} else if (!planeType.equals(other.planeType))
			return false;
		if (shareAirlineCode == null) {
			if (other.shareAirlineCode != null)
				return false;
		} else if (!shareAirlineCode.equals(other.shareAirlineCode))
			return false;
		if (shareFlightNo == null) {
			if (other.shareFlightNo != null)
				return false;
		} else if (!shareFlightNo.equals(other.shareFlightNo))
			return false;
		if (stops == null) {
			if (other.stops != null)
				return false;
		} else if (!stops.equals(other.stops))
			return false;
		if (yPrice == null) {
			if (other.yPrice != null)
				return false;
		} else if (!yPrice.equals(other.yPrice))
			return false;
		return true;
	}
	public String getFlightNo() {
		return flightNo;
	}
	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}
	public String getAirlineCode() {
		return airlineCode;
	}
	public void setAirlineCode(String airlineCode) {
		this.airlineCode = airlineCode;
	}
	public String getShareFlightNo() {
		return shareFlightNo;
	}
	public void setShareFlightNo(String shareFlightNo) {
		this.shareFlightNo = shareFlightNo;
	}
	public String getShareAirlineCode() {
		return shareAirlineCode;
	}
	public void setShareAirlineCode(String shareAirlineCode) {
		this.shareAirlineCode = shareAirlineCode;
	}
	public Date getDepDateTime() {
		return depDateTime;
	}
	public void setDepDateTime(Date depDateTime) {
		this.depDateTime = depDateTime;
	}
	public Date getArrDateTime() {
		return arrDateTime;
	}
	public void setArrDateTime(Date arrDateTime) {
		this.arrDateTime = arrDateTime;
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
	public String getDepPointAT() {
		return depPointAT;
	}
	public void setDepPointAT(String depPointAT) {
		this.depPointAT = depPointAT;
	}
	public String getArrPointAT() {
		return arrPointAT;
	}
	public void setArrPointAT(String arrPointAT) {
		this.arrPointAT = arrPointAT;
	}
	public Double getfPrice() {
		return fPrice;
	}
	public void setfPrice(Double fPrice) {
		this.fPrice = fPrice;
	}
	public Double getcPrice() {
		return cPrice;
	}
	public void setcPrice(Double cPrice) {
		this.cPrice = cPrice;
	}
	public Double getyPrice() {
		return yPrice;
	}
	public void setyPrice(Double yPrice) {
		this.yPrice = yPrice;
	}
	public Double getAirportTax() {
		return airportTax;
	}
	public void setAirportTax(Double airportTax) {
		this.airportTax = airportTax;
	}
	public Double getFuelSurTax() {
		return fuelSurTax;
	}
	public void setFuelSurTax(Double fuelSurTax) {
		this.fuelSurTax = fuelSurTax;
	}
	public String getPlaneType() {
		return planeType;
	}
	public void setPlaneType(String planeType) {
		this.planeType = planeType;
	}
	public List<RespData_GetFlightDetail_Cabin> getCabins() {
		return cabins;
	}
	public void setCabins(List<RespData_GetFlightDetail_Cabin> cabins) {
		this.cabins = cabins;
	}
	public List<RespData_GetFlightDetail_Stop> getStops() {
		return stops;
	}
	public void setStops(List<RespData_GetFlightDetail_Stop> stops) {
		this.stops = stops;
	}
	
}
