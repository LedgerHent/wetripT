package com.viptrip.intlAirticket.model.base;

import com.viptrip.base.annotation.Description;

public class Request_UserId extends Request_Base{
	private static final long serialVersionUID = -6972921664328469184L;
	@Description("用户后台唯一标识")
	public long userId;

	
	public Request_UserId(Long userId) {
		this.userId = userId;
	}
	public Request_UserId() {
		super();
	}
	//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
//		return result;
//	}
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Request_UserId other = (Request_UserId) obj;
//		if (userId == null) {
//			if (other.userId != null)
//				return false;
//		} else if (!userId.equals(other.userId))
//			return false;
//		return true;
//	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
}
