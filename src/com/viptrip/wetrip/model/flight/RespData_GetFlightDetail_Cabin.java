package com.viptrip.wetrip.model.flight;

import java.io.Serializable;

import com.viptrip.base.annotation.Description;
import com.viptrip.wetrip.model.flight.type.CabinType;

/**
 * 舱位信息
 * @author selfwhisper
 *
 */
public class RespData_GetFlightDetail_Cabin implements Serializable{
	private static final long serialVersionUID = 6794354312453377785L;
	@Description("仓位Key")
	public String mapKey;
	@Description("价格")
	public Double price;
	@Description("优惠价格")
	public Double rebatePrice;
	@Description("价格来源")
	public String priceSource;
	@Description("折扣")
	public Double discount;
	@Description("仓位等级")
	public Integer cabinType;
	@Description("仓位代码")
	public String cabin;
	@Description("仓位名称")
	public String cabinName;
	@Description("剩余票量")
	public Integer remain;
	@Description("票规")
	public RespData_GetFlightDetail_TicketRule ticketRule;
	
    @Description("超标类型")
    public String excessinfo;//超标类型： 1：不是最低价超标  2：金额在预定权限超标  3：折扣在预定权限超标  4：舱位不在预定权限超标
    //  5：  航司不在预定权限超标  6：航线不在预定权限超标 7：航班不在预定权限超标
    @Description("显示规则") 
    public String showRule;//10：隐藏  20：正常显示 21：超标显示
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cabin == null) ? 0 : cabin.hashCode());
		result = prime * result
				+ ((cabinName == null) ? 0 : cabinName.hashCode());
		result = prime * result
				+ ((cabinType == null) ? 0 : cabinType.hashCode());
		result = prime * result
				+ ((discount == null) ? 0 : discount.hashCode());
		result = prime * result + ((mapKey == null) ? 0 : mapKey.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result
				+ ((priceSource == null) ? 0 : priceSource.hashCode());
		result = prime * result
				+ ((rebatePrice == null) ? 0 : rebatePrice.hashCode());
		result = prime * result + ((remain == null) ? 0 : remain.hashCode());
		result = prime * result
				+ ((ticketRule == null) ? 0 : ticketRule.hashCode());
		result = prime * result
                + ((showRule == null) ? 0 : showRule.hashCode());
		result = prime * result
                + ((excessinfo == null) ? 0 : excessinfo.hashCode());
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
		RespData_GetFlightDetail_Cabin other = (RespData_GetFlightDetail_Cabin) obj;
		if (cabin == null) {
			if (other.cabin != null)
				return false;
		} else if (!cabin.equals(other.cabin))
			return false;
		if (cabinName == null) {
			if (other.cabinName != null)
				return false;
		} else if (!cabinName.equals(other.cabinName))
			return false;
		if (cabinType == null) {
			if (other.cabinType != null)
				return false;
		} else if (!cabinType.equals(other.cabinType))
			return false;
		if (discount == null) {
			if (other.discount != null)
				return false;
		} else if (!discount.equals(other.discount))
			return false;
		if (mapKey == null) {
			if (other.mapKey != null)
				return false;
		} else if (!mapKey.equals(other.mapKey))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (priceSource == null) {
			if (other.priceSource != null)
				return false;
		} else if (!priceSource.equals(other.priceSource))
			return false;
		if (rebatePrice == null) {
			if (other.rebatePrice != null)
				return false;
		} else if (!rebatePrice.equals(other.rebatePrice))
			return false;
		if (remain == null) {
			if (other.remain != null)
				return false;
		} else if (!remain.equals(other.remain))
			return false;
		if (ticketRule == null) {
			if (other.ticketRule != null)
				return false;
		} else if (!ticketRule.equals(other.ticketRule))
			return false;
		if (excessinfo == null) {
            if (other.excessinfo != null)
                return false;
        } else if (!excessinfo.equals(other.excessinfo))
            return false;
		if (showRule == null) {
            if (other.showRule != null)
                return false;
        } else if (!showRule.equals(other.showRule))
            return false;
		return true;
	}
	
	public String getMapKey() {
		return mapKey;
	}
	public void setMapKey(String mapKey) {
		this.mapKey = mapKey;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getRebatePrice() {
		return rebatePrice;
	}
	public void setRebatePrice(Double rebatePrice) {
		this.rebatePrice = rebatePrice;
	}
	public String getPriceSource() {
		return priceSource;
	}
	public void setPriceSource(String priceSource) {
		this.priceSource = priceSource;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
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
	public String getCabin() {
		return cabin;
	}
	public void setCabin(String cabin) {
		this.cabin = cabin;
	}
	public String getCabinName() {
		return cabinName;
	}
	public void setCabinName(String cabinName) {
		this.cabinName = cabinName;
	}
	public Integer getRemain() {
		return remain;
	}
	public void setRemain(Integer remain) {
		this.remain = remain;
	}
	public RespData_GetFlightDetail_TicketRule getTicketRule() {
		return ticketRule;
	}
	public void setTicketRule(RespData_GetFlightDetail_TicketRule ticketRule) {
		this.ticketRule = ticketRule;
	}
    public String getExcessinfo() {
        return excessinfo;
    }
    public void setExcessinfo(String excessinfo) {
        this.excessinfo = excessinfo;
    }
    public String getShowRule() {
        return showRule;
    }
    public void setShowRule(String showRule) {
        this.showRule = showRule;
    }
	
	
}
