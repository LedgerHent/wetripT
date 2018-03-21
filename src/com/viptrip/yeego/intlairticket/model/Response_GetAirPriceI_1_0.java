package com.viptrip.yeego.intlairticket.model;


import org.apache.commons.collections.map.HashedMap;
import org.codehaus.jackson.annotate.JsonProperty;

import com.viptrip.yeego.model.Response_Base;

@SuppressWarnings("serial")
public class Response_GetAirPriceI_1_0 extends Response_Base {
	@JsonProperty("Q")//机场
	public	HashedMap intlCheckPrice;

	public HashedMap getIntlCheckPrice() {
		return intlCheckPrice;
	}

	public void setIntlCheckPrice(HashedMap intlCheckPrice) {
		this.intlCheckPrice = intlCheckPrice;
	}

	
	
	
}
