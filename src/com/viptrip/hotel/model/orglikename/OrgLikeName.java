package com.viptrip.hotel.model.orglikename;

public class OrgLikeName {
	//企业id
    public Integer id;
    //企业名称
    public String name;
    //联系人姓名
    public String contactName;
    //联系人电话
    public String contactTel;
    //审核状态，0-不需要，1-需要
    public Integer verify;
    //项目号状态0-不显示，1-选填，2-必填
    public Integer projectNo;
    //酒店业务短信状态，1-自动发送，2-手动发送
    public Integer sms;
    //业务城市
    public String localCity;
    //酒店业务邮件状态，1-自动发送，2-手动发送
    public Integer email;
    
}
