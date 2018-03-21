package com.viptrip.wetrip.model;

import com.viptrip.wetrip.model.base.Request_Base;

public class Request_QueFee4ChangeRfndRule extends Request_Base{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6206748100626495802L;

	public String cangwei;
	public String air2char;
	public String type;//1：退票，2：改期
	public String date;//起飞时间
	public int discount;//折扣
	public Double price;//机票价格
	public int changeNum;//改期次数--当前改期次数
	public Long userId;//用户id
	
}
