package com.viptrip.wetrip.model;

import com.viptrip.wetrip.model.base.Response_Base;

public class Response_QueFee4ChangeRfndRule extends Response_Base{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3772100596008983243L;
	/**
	 * 返回状态 0：允许在线预订；1：不允许在线预订
	 */
	public Double price;
	public Double servicePrice;
}
