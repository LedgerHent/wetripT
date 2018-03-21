package com.viptrip.yeego.intlairticket.model;
/**
 * 请求参数里 大客户协议编码对象
 * @author wjt
 */

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class NegotiatedFareCodeModule {
	@XmlAttribute(name="Carrier")
	public String carrier;
	
	@XmlValue
	public String CarrierValue;
	
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public void setCarrierValue(String carrierValue) {
		CarrierValue = carrierValue;
	}
	
}
