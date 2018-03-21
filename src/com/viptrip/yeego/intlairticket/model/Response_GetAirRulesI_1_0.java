package com.viptrip.yeego.intlairticket.model;

import java.util.Hashtable;

import org.codehaus.jackson.annotate.JsonProperty;

import com.viptrip.yeego.model.Response_Base;

public class Response_GetAirRulesI_1_0 extends Response_Base {
	@JsonProperty("T")//退改签
	public Hashtable<String,Hashtable<String,Object[]>> airRules;
}
