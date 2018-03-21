package com.viptrip.wetrip.model;

import com.viptrip.wetrip.model.base.Request_Base;

@SuppressWarnings("serial")
public class Request_UnbindUser extends Request_Base {
	public String uuid;
	public long userId;
}
