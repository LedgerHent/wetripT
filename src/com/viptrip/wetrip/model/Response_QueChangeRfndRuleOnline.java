package com.viptrip.wetrip.model;

import com.viptrip.wetrip.model.base.Response_Base;

public class Response_QueChangeRfndRuleOnline extends Response_Base{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3772100596008983243L;
	/**
	 * 返回状态 0：允许在线退票或改期；1：不允许在线退票或改期
	 */
	public int returnStatic;
}
