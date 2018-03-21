package com.viptrip.intlAirticketPC.model.base;

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
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
}
