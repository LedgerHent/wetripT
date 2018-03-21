package com.viptrip.hotel.model;

import com.viptrip.hotel.model.base.Request_Base;

@SuppressWarnings("serial")
public class Request_UserAuth4Hotel extends Request_Base{
	//登录令牌，未登录之前可传空
	public String token;
	//访问菜单的id，如果不是菜单页面，或者不是通过菜单方式进入的页面，验证授权时候，该字段传-1。此时后台不校验菜单权限。
    public Integer menuId;

}
