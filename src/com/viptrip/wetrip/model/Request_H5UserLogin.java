package com.viptrip.wetrip.model;

import com.viptrip.wetrip.model.base.Request_Base;
import com.viptrip.wetrip.model.userlogin.LoginMessage;


@SuppressWarnings("serial")
public class Request_H5UserLogin extends Request_Base{
//	public int versionId;//接口版本号。	数字，1.000000，最多六位小数
	public LoginMessage data;
}
