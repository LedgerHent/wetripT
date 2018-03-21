package com.viptrip.wetrip.model;

import java.util.Arrays;

import com.viptrip.wetrip.model.base.Request_UserId;
/**
 * 儿童婴儿票价查询请求参数
 * @author selfwhisper
 *
 */
public class Request_GetChildBabyPrice extends Request_UserId{
	private static final long serialVersionUID = 5206497159260961487L;
	
	public String[] mapKeys;
	public Integer type;// 1儿童 ,2婴儿
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Arrays.hashCode(mapKeys);
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Request_GetChildBabyPrice other = (Request_GetChildBabyPrice) obj;
		if (!Arrays.equals(mapKeys, other.mapKeys))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	public String[] getMapKeys() {
		return mapKeys;
	}
	public void setMapKeys(String[] mapKeys) {
		this.mapKeys = mapKeys;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	
}
