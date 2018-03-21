package com.viptrip.yeego.intlairticket.model;

import java.util.Hashtable;

import org.codehaus.jackson.annotate.JsonProperty;

import com.viptrip.yeego.model.Response_Base;

public class Response_QueryWebFlightsI_1_0 extends Response_Base {
	@JsonProperty("P")//机场
	public	Hashtable<String, String[]> airPortsHt;
	
	@JsonProperty("A")//航空公司
	public Hashtable<String, String[]> airLines;
	
	@JsonProperty("J")//机型代码
	public Hashtable<String, String[]> airType;
	
	@JsonProperty("R")//仓位级别描述
	public Hashtable<String, String> cangweiLevelDesc;
	
	@JsonProperty("F")//航段
	public Hashtable<String,Hashtable<String,Object[]>> flights;
	
	@JsonProperty("H")//航段价格列表
	public Hashtable<String, Hashtable<String,Hashtable<String,Object[]>>> flgithPrices;
	
	
	
}
