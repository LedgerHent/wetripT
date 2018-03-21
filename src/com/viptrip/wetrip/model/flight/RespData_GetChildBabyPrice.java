package com.viptrip.wetrip.model.flight;

import java.io.Serializable;
import java.util.List;

import com.viptrip.wetrip.model.flight.RespData_GetChildBabyPrice_PriceInfo;

/**
 * 儿童婴儿票价查询返回结果
 * @author selfwhisper
 *
 */
public class RespData_GetChildBabyPrice implements Serializable{
	private static final long serialVersionUID = 5133899841416885711L;
	public String mapKey;
	public List<RespData_GetChildBabyPrice_PriceInfo> priceInfo;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mapKey == null) ? 0 : mapKey.hashCode());
		result = prime * result
				+ ((priceInfo == null) ? 0 : priceInfo.hashCode());
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
		RespData_GetChildBabyPrice other = (RespData_GetChildBabyPrice) obj;
		if (mapKey == null) {
			if (other.mapKey != null)
				return false;
		} else if (!mapKey.equals(other.mapKey))
			return false;
		if (priceInfo == null) {
			if (other.priceInfo != null)
				return false;
		} else if (!priceInfo.equals(other.priceInfo))
			return false;
		return true;
	}
	public String getMapKey() {
		return mapKey;
	}
	public void setMapKey(String mapKey) {
		this.mapKey = mapKey;
	}
	public List<RespData_GetChildBabyPrice_PriceInfo> getPriceInfo() {
		return priceInfo;
	}
	public void setPriceInfo(List<RespData_GetChildBabyPrice_PriceInfo> priceInfo) {
		this.priceInfo = priceInfo;
	}
	
}
