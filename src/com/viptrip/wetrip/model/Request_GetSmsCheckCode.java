package com.viptrip.wetrip.model;

import com.viptrip.wetrip.model.base.MobileAndCheckCode;
import com.viptrip.wetrip.model.base.Request_Base;

@SuppressWarnings("serial")
public class Request_GetSmsCheckCode extends Request_Base {
	public MobileAndCheckCode data;
}
