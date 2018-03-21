package com.viptrip.hotel.model.sendEmail4Hotel;

import java.util.List;

public class SendEmail4Hotel {

	public Integer type;//邮件类型,0-通知类,1-审核类
	
	public String memo;//短信描述，比如预订提醒，酒店确认提醒，支付通知等等
	
	public List<String> emails;//收件邮箱序列
	
	public String orderNo;//业务订单号，显示的订单号
	
	public String subject;//邮件主题
	
	public String content;//邮件内容
}
