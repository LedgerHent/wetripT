package com.viptrip.hotel.model;

import com.viptrip.hotel.model.base.Response_Base;

@SuppressWarnings("serial")
public class Response_GetBookRight extends Response_Base{
	
//	预订权限类别：0-不限制预订；1-仅允许给自己预订；2-仅允许给指定企业员工预订；
//	3-仅允许给常旅客预订；4-允许给自己和常旅客预订
	public Integer data;
}
