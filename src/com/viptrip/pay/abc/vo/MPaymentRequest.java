package com.viptrip.pay.abc.vo;


import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;

import com.abc.pay.client.JSON;
import com.abc.pay.client.ebus.PaymentRequest;
import com.viptrip.pay.abc.api.resource.ResultCode;

public abstract class MPaymentRequest extends PaymentRequest implements IMPR{
	
	@SuppressWarnings("unchecked")
	protected MPaymentRequest() {
		super();
		Date date = new Date();
		//以下为订单属性
		this.dicOrder.put("PayTypeID","ImmediatePay");//交易类型
		this.dicOrder.put("OrderDate", new SimpleDateFormat("YYYY/MM/dd").format(date));
		this.dicOrder.put("OrderTime", new SimpleDateFormat("HH:mm:ss").format(date));
		this.dicOrder.put("CurrencyCode", 156);//交易币种
		this.dicOrder.put("InstallmentMark", 0);//分期标识
		this.dicOrder.put("CommodityType", "0203");//商品种类
		
		//以下为支付请求对象属性
		this.dicRequest.put("PaymentType", "A");//支付类型
		this.dicRequest.put("NotifyType", "1");//通知方式  后台通知
	}
	
	@SuppressWarnings("unchecked")
	protected MPaymentRequest(String orderNo,Double amount,String productName,String notifyURL){
		this();
		//以下为订单明细属性
		LinkedHashMap<String, String> orderitem = new LinkedHashMap<String, String>();
		orderitem.put("ProductName", productName);//商品名称
		this.orderitems.put(1, orderitem);
		this.dicOrder.put("OrderNo", orderNo);//订单号
		this.dicOrder.put("OrderAmount", new DecimalFormat("0.##").format(amount));//金额
		
		this.dicRequest.put("ResultNotifyURL", notifyURL);//通知地址
	}
	
	/**
	 * 发送请求
	 * @return
	 */
	public Resp<String> request(){
		Resp<String> result = new Resp<String>();
		JSON json = this.postRequest();
		if(null!=json){
			String code = json.GetKeyValue("ReturnCode");
			String url = json.GetKeyValue("PaymentURL");
			String msg = json.GetKeyValue("ErrorMessage");
			if(ResultCode.C0000 == ResultCode.convert(code)){//成功
				result.success(url);
			}else{//失败
				result.fail(msg);
			}
		}else{
			result.fail("返回结果为空");
		}
		return result;
	}
}
