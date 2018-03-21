package com.viptrip.wetrip.pay.alipay.vo;

import java.util.Date;

public interface IAliResp {
	Double getTotalFee();
	String getTradeNo();
	String getOrderNo();
	Date getPayTime();
}
