package com.viptrip.pay.ali.vo;

import java.util.Date;

public interface IAliResp {
	Double getTotalFee();
	String getTradeNo();
	String getOrderNo();
	Date getPayTime();
}
