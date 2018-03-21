package com.viptrip.hotel.model;

import com.viptrip.hotel.model.base.Request_Base;
import com.viptrip.hotel.model.page.Page;

@SuppressWarnings("serial")
public class Request_GetOrgLikeContact extends Request_Base{
    //联系人关键字
    public String nameKey;
    public Page page;
 
}
