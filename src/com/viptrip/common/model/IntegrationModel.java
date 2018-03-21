package com.viptrip.common.model;

import java.util.Date;

import com.viptrip.base.common.MyEnum.IntegralType;


public class IntegrationModel {
	public Long userid;
	public Long integration;//积分
	public IntegralType source;//
	public String orderNo;//
	
	public Integer businesstype;
	public Long companyId;
	public Integer useState;
	public Long consumeId;
	public String orgmane;//
	
	
	public Date issuedate;
	public Date expirydate;
	public Double money;
	
	
	
}
