package com.viptrip.common.model;

import java.util.List;

import com.viptrip.base.common.MyEnum.OrderTypeEnum;
import com.viptrip.intlAirticket.entity.TIntlTicketsRescheduled;
import com.viptrip.wetrip.entity.TUpdateDate;

public class RefundPriceModel {
	public String paymethod;
	public String orderNo;
	public Long companyId;
	public OrderTypeEnum bussiness;
	public String type;//1-出票  2:-改期
	public List<TUpdateDate> updateTickets;//如果是国内改期余额不足，该字段有值，则值是已经扣款的改期记录
	public List<TIntlTicketsRescheduled> intlUpdateTickets;//如果是国际改期余额不足，该字段有值，则值是已经扣款的改期记录
}
