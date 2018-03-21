package com.viptrip.hotel.model;

import com.viptrip.hotel.model.base.Request_Base;
import com.viptrip.hotel.model.page.Page;

@SuppressWarnings("serial")
public class Request_GetOrgLikeName extends Request_Base{
    //企业名称关键字
    public String nameKey;
    public Page page;
}
