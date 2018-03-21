package com.viptrip.hotel.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.viptrip.base.action.BaseAction;
import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.hotel.entity.HotelNotify;
import com.viptrip.hotel.entity.HotelPayInfo;
import com.viptrip.hotel.service.IHotelNotifyService;
import com.viptrip.hotel.service.IHotelPayInfoService;
import com.viptrip.hotel.task.NotifyTask;
import com.viptrip.hotel.task.PayUtil;
import com.viptrip.util.JSON;
import com.viptrip.util.StringUtil;
import com.viptrip.wetrip.pay.AliConfig;
import com.viptrip.wetrip.pay.alipay.vo.AliPayNTFBeanNew;
@Controller
@RequestMapping("/hotelPay")
public class PayNotifyAction extends BaseAction{
	
	private static Logger log = LoggerFactory.getLogger(PayNotifyAction.class);
	
	@Resource
	private IHotelPayInfoService hpiService;
	@Resource
	private IHotelNotifyService hnService;
	
	@RequestMapping("/ali_return.act")
	@ResponseBody
	public void ali_return(String out_trade_no,String trade_no){
		Map<String,String> params = getParam(req);
		boolean signVerified;
		try {
			signVerified = AlipaySignature.rsaCheckV1(params, AliConfig.ALI_PUBLIC_KEY, AliConfig.CHARSET.UTF8.value(), AliConfig.SIGN_TYPE);
			
			if(signVerified){
				HotelPayInfo hpi = hpiService.getHotelPayInfoByOrderno(out_trade_no);
				if(null!=hpi){
					Map<String, Object> map = PayUtil.getNotifyParamMap(hpi, 1, 1, 0, null);
					try {
						String str = PayUtil.getEncryptParamStr(map);
						resp.sendRedirect(hpi.getReturnUrl() + "?data=" + str);
						
					} catch (IOException e) {
						log.error(StringUtil.getExceptionStr(e));
					}
				}
			}
			
			log.debug("signVerified=" + signVerified);
		
		} catch (AlipayApiException e) {
			log.error(StringUtil.getExceptionStr(e));
		}
		log.debug("return params:" + JSON.get().toJson(params));
	}
	
	
	@RequestMapping("/ali_notify.act")
	@ResponseBody
	public void ali_notify(String out_trade_no,String trade_no,String trade_status){
		AliPayNTFBeanNew notifyBean = getNotifyBean(req);
		
		//获取支付宝POST过来反馈信息
		Map<String,String> params = getParam(req);
		
		boolean signVerified;
		try {
			signVerified = AlipaySignature.rsaCheckV1(params, AliConfig.ALI_PUBLIC_KEY, AliConfig.CHARSET.UTF8.value(), AliConfig.SIGN_TYPE);
			//——请在这里编写您的程序（以下代码仅作参考）——
			
			/* 实际验证过程建议商户务必添加以下校验：
				1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
				2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
				3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
				4、验证app_id是否为该商户本身。
			 */
			if(signVerified) {//验证成功
				if(trade_status.equals("TRADE_FINISHED")){//
					//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
					
					//注意：
					//退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
				}else if (trade_status.equals("TRADE_SUCCESS")){
					//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
					
					//注意：
					//付款完成后，支付宝系统发送该交易状态通知
					log.info("支付成功，订单号:" + notifyBean.getOut_trade_no() + ",支付金额为：" + notifyBean.getTotal_amount() + ",交易号为：" + notifyBean.getTrade_no());
					HotelPayInfo hpi = hpiService.getHotelPayInfoByOrderno(out_trade_no);
					if(null!=hpi){
						HotelNotify notify = new HotelNotify(out_trade_no, 1, hpi.getNotifyUrl());
						hnService.save(notify);
						hpi.setPayno(notifyBean.getTrade_no());
						hpi.setStatus(2);//支付完成
						hpi.setPayTime(notifyBean.getGmt_payment());
						hpi.setPayerno(notifyBean.getBuyer_id());
						hpiService.update(hpi);
						Map<String, Object> paramMap = PayUtil.getNotifyParamMap(hpi, 2, 1, 1, "成功");
						
						Map<String,Object> map = new HashMap<>();
						map.put("data", PayUtil.getEncryptParamStr(paramMap));
						new NotifyTask(notify, ApplicationContextHelper.getInstance().getBean(IHotelNotifyService.class), map).beginSend();//发送通知
					}
					//payService.paySuccessCheckOrderStatus(1, Long.parseLong(out_trade_no), notifyBean.getGmt_payment(), trade_no, "AP");
				}
				ajaxWrite("success");
				
			}else {//验证失败
				ajaxWrite("fail");
			}
		} catch (AlipayApiException e) {
			log.error(StringUtil.getExceptionStr(e));
		} catch (IOException e) {
			log.error(StringUtil.getExceptionStr(e));
		}
	}
	
	@RequestMapping("/wx_notify.act")
	public void wx_notify(){
		
		
	}
	
	private AliPayNTFBeanNew getNotifyBean(HttpServletRequest req){
		Map<String, String> params = getParam(req);
		if(log.isDebugEnabled()){
			log.debug("getNotifyBean所有返回参数：" + JSON.get().toJson(params));
		}
		AliPayNTFBeanNew bean = JSON.get().fromJson(JSON.get().toJson(params), AliPayNTFBeanNew.class);
		return bean;
	}
	
	/**
	 * 获取请求参数
	 * @param req
	 * @return
	 */
	private Map<String,String> getParam(HttpServletRequest req){
		Map<String,String> params = new HashMap<String,String>();
		Map<String, String[]> requestParams = req.getParameterMap();
		for (Iterator<?> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			/*//乱码解决，这段代码在出现乱码时使用
			try {
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				log.error(StringUtil.getExceptionStr(e));
			}*/
			params.put(name, valueStr);
		}
		return params;
	}
	
	
}
