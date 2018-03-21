package com.viptrip.wetrip.pay.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.*;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.viptrip.pay.entity.PayInfo;
import com.viptrip.pay.service.Pay;
import com.viptrip.pay.vo.E;
import com.viptrip.pay.vo.PayResp;
import com.viptrip.pay.wx.api.bean.WXJSAPIPayRtn;
import com.viptrip.wetrip.action.BaseAction4Wechat;
import com.viptrip.wetrip.entity.Userbinding;
import com.viptrip.wetrip.pay.PayConfig;
import com.viptrip.wetrip.pay.vo.NtfBean;
import com.viptrip.wetrip.pay.vo.RtnBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.viptrip.base.action.BaseAction;
import com.viptrip.resource.Const;
import com.viptrip.util.DateUtil;
import com.viptrip.util.JSON;
import com.viptrip.util.PropertiesUtils;
import com.viptrip.util.StringUtil;
import com.viptrip.wetrip.entity.TOrder;
import com.viptrip.wetrip.pay.AliConfig;
import com.viptrip.wetrip.pay.WxConfig;
import com.viptrip.wetrip.pay.alipay.service.AliPayService;
import com.viptrip.wetrip.pay.alipay.vo.AliPayNTFBeanNew;
import com.viptrip.wetrip.pay.alipay.vo.AliPayNTFBeanOld;
import com.viptrip.wetrip.pay.alipay.vo.AliPayRTNBeanOld;
import com.viptrip.wetrip.pay.alipay.vo.AliResp;
import com.viptrip.wetrip.pay.vo.PayRtnBean;
import com.viptrip.wetrip.pay.weixin.api.bean.WXMwebPayRtn;
import com.viptrip.wetrip.pay.weixin.api.bean.WXRtnBiz;
import com.viptrip.wetrip.pay.weixin.api.util.PayCommonUtil;
import com.viptrip.wetrip.pay.weixin.api.util.XMLParser;
import com.viptrip.wetrip.pay.weixin.service.WXPayService;
import com.viptrip.wetrip.service.IGetOrderService;
import com.viptrip.wetrip.service.IPayService;
/**
 * 支付
 * @author selfwhisper
 *
 */
@Controller
@RequestMapping("/pay")
public class PayAction extends BaseAction{
	private static Logger log = LoggerFactory.getLogger(PayAction.class);
	
	private static String pay_return = "pay/pay_return";
	private static String wx_rtn_redirect = "pay/wx_pay_return_redirect";
	
	private static final String useOldInterface;
	
	private static final String default_useOldInterface = "true";
	
	private static final String domain;
	private static final String default_domain = "viptrip365.com";
	
	
	static{
		String useOldInterface1;
		String domain1;
		try {
			useOldInterface1 = PropertiesUtils.getProperty(Const.PRO_PAY_USE_OLD, Const.FILE_PAY_CONFIG);
			domain1 = PropertiesUtils.getProperty(Const.PRO_WX_DOMAIN, Const.FILE_PAY_CONFIG);
		} catch (IOException e) {
			useOldInterface1 = default_useOldInterface;
			domain1 = default_domain;
			log.error(StringUtil.getExceptionStr(e));
		}
		useOldInterface = useOldInterface1;
		domain = domain1;
	}
	
	@Resource
	private AliPayService aliService;
	
	@Resource
	IPayService payService;
	
	@Resource
	IGetOrderService orderService;
	
	@Resource
	private WXPayService wxPayService;

	@Resource
	private Pay pay;



	/**
	 * 支付宝支付
	 * @param orderId
	 * @param price
	 * @param title
	 * @param body
	 */
	@RequestMapping("/alipay.act")
	@ResponseBody
	public void aliPay(String orderId,Double price,String title,String body){
		/*try {
			aliService.wapPay(resp, orderId, price, title, body, baseURL);
		} catch (Exception e) {
			try {
				resp.sendRedirect(baseURL + AliConfig.URL_RETURN_PAY);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			log.error(StringUtil.getExceptionStr(e));
		}*/
		String ip = StringUtil.getIpAddr(req);
		try {
			pay.onlinePay(req,resp, E.PayType.支付宝H5,orderId,price,title,body,baseURL + PayConfig.NOTIFY_URL,baseURL +  PayConfig.RETURN_URL,ip);
		} catch (IOException e) {
			log.error(StringUtil.getLogInfo(null,null,StringUtil.getExceptionStr(e)));
		}
	}

	/**
	 * 支付宝支付
	 * @param orderId
	 * @param price
	 * @param title
	 * @param body
	 */
	@RequestMapping("/alipay_old.act")
	@ResponseBody
	public void aliPay_old(String orderId,Double price,String title,String body){
		try {
			aliService.wapPay(resp, orderId, price, title, body, baseURL);
		} catch (Exception e) {
			try {
				resp.sendRedirect(baseURL + AliConfig.URL_RETURN_PAY);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			log.error(StringUtil.getExceptionStr(e));
		}
	}

	public static void main(String[] args){
		/*String str = "技术测试_最高级客维_客服经理_财务经理 ";
		String reg = "(技术测试_最高级客维_客服经理_财务经理)+";
		System.out.print(Pattern.compile(reg).matcher(str).find());*/

		/*TreeMap<String,Object> map = new TreeMap<>();
		map.put("appId",123456);
		map.put("timeStamp","Fsdfsd");
		map.put("nonceStr","fdsfdsffdg");
		map.put("signType","MD5");
		map.put("package","prepay_id=12346784FDS");

		String s = JSON.get().toJson(map);
		System.out.println(s);*/
		System.out.println(UUID.randomUUID().toString().replaceAll("-",""));

	}

	@RequestMapping("/JSAPI.act")
	@ResponseBody
	public PayResp jsPay(String orderId, Double price, String title, String body){
		if(log.isDebugEnabled()){
			log.debug(StringUtil.getLogInfo(null,null,"微信公众号支付请求参数为：orderId=" + orderId + ",price=" + price.doubleValue() + ",title=" + title + ",body=" + body));
		}
		PayResp result = new PayResp(0,"失败",null);
		String ip = StringUtil.getIpAddr(req);
		Userbinding userbinding = BaseAction4Wechat.GetUserBindingInfo(req.getSession());
		if(null!=userbinding){
			Map<String,String> headers = StringUtil.getHeaders(req);
			if(log.isDebugEnabled()){
				log.debug(StringUtil.getLogInfo(null,null,"headers=" + JSON.get().toJson(headers)));
			}
			title = userbinding.getOpenid(); //获取用户openid
			String rtn = pay.onlinePay(E.PayType.微信公众号,orderId,price,title,body,baseURL + PayConfig.NOTIFY_URL,baseURL +  PayConfig.RETURN_URL,ip,headers);
			if(StringUtil.isNotEmpty(rtn)){
				WXJSAPIPayRtn wxjsapiPayRtn = JSON.get().fromJson(rtn, WXJSAPIPayRtn.class);
				if(null!=wxjsapiPayRtn && wxjsapiPayRtn.isSuccess()){
					String appid = wxjsapiPayRtn.getAppid();
					String nonce_str = wxjsapiPayRtn.getNonce_str();
					String prepay_id = wxjsapiPayRtn.getPrepay_id();
					String timeStamp = new Date().getTime() + "";
					String signType =  WxConfig.SIGNTYPE.MD5.val();
					TreeMap<String,Object> map = new TreeMap<>();
					map.put("appId",appid);
					map.put("timeStamp",timeStamp);
					map.put("nonceStr",nonce_str);
					map.put("signType",signType);
					map.put("package","prepay_id="+prepay_id);
					String sign = PayCommonUtil.createSign(WxConfig.CHARSET.UTF8.val(), map, WxConfig.KEY);
					map.put("paySign",sign);
					result = new PayResp(1,"成功",map);
				}else{
					result.setMsg(wxjsapiPayRtn.getReturn_msg());
				}
			}
		}else{
			result.setMsg("获取微信OPENID失败");
		}
		if(log.isDebugEnabled()){
			log.debug(StringUtil.getLogInfo(null,null,"微信公众号支付返回结果为：" + JSON.get().notEscapeHTML(true).toJson(result)));
		}
		return result;
	}

	/**
	 * 微信支付
	 * @param orderId
	 * @param price
	 * @param title
	 * @param body
	 */
	@RequestMapping("/wxpay.act")
	@ResponseBody
	public void wxPay(String orderId,Double price,String title,String body){
		/*try {
			String ip = StringUtil.getIpAddr(req);
			WXMwebPayRtn mwebPay = wxPayService.mwebPay(orderId, price, title, body, ip, baseURL);
			if(log.isDebugEnabled()){
				log.debug("微信支付返回结果为：" + JSON.get().notEscapeHTML(true).toJson(mwebPay));
			}
			if(null!=mwebPay){
				String returnStr = null;
				if("SUCCESS".equals(mwebPay.getReturn_code())&&"SUCCESS".equals(mwebPay.getResult_code())){
					if(log.isDebugEnabled()){
						log.debug("微信统一下单返回地址为：" + mwebPay.getMweb_url());
						log.debug("跳转地址为：" + PayCommonUtil.buildEncodeRedirectURL(mwebPay.getMweb_url(), baseURL + WxConfig.RETURN_URL_MWEB));
					}
					returnStr = PayCommonUtil.buildEncodeRedirectURL(mwebPay.getMweb_url(), baseURL + WxConfig.RETURN_URL_MWEB + "?out_trade_no=" + orderId);
//					returnStr = HttpRequest.post(PayCommonUtil.buildEncodeRedirectURL(mwebPay.getMweb_url(), baseURL + WxConfig.RETURN_URL_MWEB)).header("Referer", domain).body();
				}else{
					if(StringUtil.isNotEmpty(mwebPay.getMweb_url())){
						returnStr = PayCommonUtil.buildEncodeRedirectURL(mwebPay.getMweb_url(), baseURL + WxConfig.RETURN_URL_MWEB + "?out_trade_no=" + orderId);
//						returnStr = HttpRequest.post(PayCommonUtil.buildEncodeRedirectURL(mwebPay.getMweb_url(), baseURL + WxConfig.RETURN_URL_MWEB)).header("Referer", domain).body();
					}else{
						returnStr = mwebPay.getErr_code_des();
						resp.getWriter().write(returnStr);
					}
				}
				if(StringUtil.isNotEmpty(returnStr)&&returnStr.indexOf("http")>=0){
					resp.sendRedirect(returnStr);
				}
			}
		} catch (Exception e) {
			try {
				resp.sendRedirect(baseURL + AliConfig.URL_RETURN_PAY);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			log.error(StringUtil.getExceptionStr(e));
		}*/

		String ip = StringUtil.getIpAddr(req);
		try {
			pay.onlinePay(req,resp, E.PayType.微信H5,orderId,price,title,body,baseURL + PayConfig.NOTIFY_URL,baseURL +  PayConfig.RETURN_URL,ip);
		} catch (IOException e) {
			log.error(StringUtil.getLogInfo(null,null,StringUtil.getExceptionStr(e)));
		}
	}

	/**
	 * 微信支付
	 * @param orderId
	 * @param price
	 * @param title
	 * @param body
	 */
	@RequestMapping("/wxpay_old.act")
	@ResponseBody
	public void wxPay_old(String orderId,Double price,String title,String body){
		try {
			String ip = StringUtil.getIpAddr(req);
			WXMwebPayRtn mwebPay = wxPayService.mwebPay(orderId, price, title, body, ip, baseURL);
			if(log.isDebugEnabled()){
				log.debug("微信支付返回结果为：" + JSON.get().notEscapeHTML(true).toJson(mwebPay));
			}
			if(null!=mwebPay){
				String returnStr = null;
				if("SUCCESS".equals(mwebPay.getReturn_code())&&"SUCCESS".equals(mwebPay.getResult_code())){
					if(log.isDebugEnabled()){
						log.debug("微信统一下单返回地址为：" + mwebPay.getMweb_url());
						log.debug("跳转地址为：" + PayCommonUtil.buildEncodeRedirectURL(mwebPay.getMweb_url(), baseURL + WxConfig.RETURN_URL_MWEB));
					}
					returnStr = PayCommonUtil.buildEncodeRedirectURL(mwebPay.getMweb_url(), baseURL + WxConfig.RETURN_URL_MWEB + "?out_trade_no=" + orderId);
//					returnStr = HttpRequest.post(PayCommonUtil.buildEncodeRedirectURL(mwebPay.getMweb_url(), baseURL + WxConfig.RETURN_URL_MWEB)).header("Referer", domain).body();
				}else{
					if(StringUtil.isNotEmpty(mwebPay.getMweb_url())){
						returnStr = PayCommonUtil.buildEncodeRedirectURL(mwebPay.getMweb_url(), baseURL + WxConfig.RETURN_URL_MWEB + "?out_trade_no=" + orderId);
//						returnStr = HttpRequest.post(PayCommonUtil.buildEncodeRedirectURL(mwebPay.getMweb_url(), baseURL + WxConfig.RETURN_URL_MWEB)).header("Referer", domain).body();
					}else{
						returnStr = mwebPay.getErr_code_des();
						resp.getWriter().write(returnStr);
					}
				}
				if(StringUtil.isNotEmpty(returnStr)&&returnStr.indexOf("http")>=0){
					resp.sendRedirect(returnStr);
				}
			}
		} catch (Exception e) {
			try {
				resp.sendRedirect(baseURL + AliConfig.URL_RETURN_PAY);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			log.error(StringUtil.getExceptionStr(e));
		}
	}


	/**
	 * 统一回跳
	 * @param bean
	 * @return
	 */
	@RequestMapping("/rtn.act")
	public String rtn(RtnBean bean){
		boolean success = false;
		PayRtnBean result = new PayRtnBean();
		if(null!=bean){
			String orderId = bean.getOrderId();
			TOrder order = orderService.getOrderById(Long.parseLong(orderId));
			String orderNo = null;
			if(null!=order){
				orderNo = order.getOrderno();
			}
			result = new PayRtnBean(orderId, orderNo, null, bean.getAmount().toString(),bean.getMsg());
			PayResp payResp = pay.queryPayStatus(orderId);
			if(null!=payResp && 2==payResp.getCode()){
				PayInfo data = (PayInfo) payResp.getData();
				result.setTradeNo(data.getPayno());
				success = true;
			}
		}else{
			result.setMsg("支付失败");
		}
		addAttr("success", success);
		addAttr("para", result);

		return pay_return;
	}

	/**
	 * 公众号支付统一回跳
	 * @param orderId
	 * @return
	 */
	@RequestMapping("/jsapi_rtn.act")
	public String jsapi_rtn(String orderId){
		boolean success = false;
		PayRtnBean result = new PayRtnBean();
		if(StringUtil.isNotEmpty(orderId)){
			TOrder order = orderService.getOrderById(Long.parseLong(orderId));
			String orderNo = null;
			if(null!=order){
				orderNo = order.getOrderno();
			}
			PayResp payResp = pay.queryPayStatus(orderId);
			if(null!=payResp && 2==payResp.getCode()){
				success = true;
				PayInfo data = (PayInfo) payResp.getData();
				result = new PayRtnBean(orderId, orderNo, data.getPayno(), data.getAmount().toString(),"成功");
			}
		}
		addAttr("success", success);
		addAttr("para", result);

		return pay_return;
	}

	/**
	 * 统一通知
	 * @param bean
	 */
	@RequestMapping("/ntf.act")
	@ResponseBody
	public void ntf(NtfBean bean){
		if(null!=bean){
			Integer payStatus = bean.getPayStatus();
			if(1==payStatus){
				PayResp payResp = pay.queryPayStatus(bean.getOrderId());
				if(null!=payResp && 2==payResp.getCode()){
					log.info("支付成功，订单号:" + bean.getOrderId() + ",支付金额为：" + Double.toString(bean.getAmount()) + ",交易号为：" + bean.getPayNo());
					try {
						String payTypeStr = "UN"; //未知
						Integer payType = bean.getPayType();
						if(null!=payType){
							if(1 == payType/10){//支付宝
								payTypeStr = "AP";
							}
							if(2 == payType/10){
								payTypeStr = "WP";//微信
							}
						}
						payService.paySuccessCheckOrderStatus(1, Long.parseLong(bean.getOrderId()), DateUtil.SDF_yyyy_MM_dd_HH_mm_ss.parse(bean.getPayTime()), bean.getPayNo(), payTypeStr);
					} catch (ParseException e) {
						log.error(StringUtil.getLogInfo(null,null,StringUtil.getExceptionStr(e)));
					}
				}
			}
		}
		try {
			ajaxWrite("SUCCESS");
		} catch (IOException e) {
			log.error(StringUtil.getLogInfo(null,null,StringUtil.getExceptionStr(e)));
		}
	}


	/**
	 * 支付宝支付回跳页面
	 */
	@RequestMapping("/alirtn.act")
	public String aliRtnAction(RtnBean bean){
		boolean success = false;
		PayRtnBean result = new PayRtnBean();
		if(null!=bean){
			String orderId = bean.getOrderId();
			TOrder order = orderService.getOrderById(Long.parseLong(orderId));
			String orderNo = null;
			if(null!=order){
				orderNo = order.getOrderno();
			}
			result = new PayRtnBean(orderId, orderNo, null, bean.getAmount().toString(),bean.getMsg());
			PayResp payResp = pay.queryPayStatus(orderId);
			if(null!=payResp && 2==payResp.getCode()){
				success = true;
			}
		}else{
			result.setMsg("支付失败");
		}
		addAttr("success", success);
		addAttr("para", result);

		return pay_return;
	}


	/**
	 * 支付宝支付回跳页面
	 */
	@RequestMapping("/alirtn_old.act")
	public String aliRtnAction_old(String out_trade_no,String trade_no){
		if("true".equals(useOldInterface)){
			AliPayRTNBeanOld returnBean = aliService.getReturnBeanOld(req);

			TOrder order = orderService.getOrderById(Long.parseLong(out_trade_no));

			/*addAttr("orderNo",order.getOrderno());
			addAttr("para", returnBean);*/
			String orderNo = null;
			if(null!=order){
				orderNo = order.getOrderno();
			}
			PayRtnBean result = new PayRtnBean(out_trade_no, orderNo, returnBean.getTradeNo(), Double.toString(order.getTotalPrice()),returnBean.getTrade_status());
			addAttr("para", result);

			boolean success = false;
			if(null!=returnBean && "T".equals(returnBean.getIs_success())){
				success = true;
			}
			addAttr("success", success);
		}else{

			boolean success = false;

			Map<String,String> params = aliService.getParam(req);
			boolean signVerified;
			try {
//				AlipaySignature.rsaCheckV2(params, publicKey, charset, signType)
				signVerified = AlipaySignature.rsaCheckV1(params, AliConfig.ALI_PUBLIC_KEY, AliConfig.CHARSET.UTF8.value(), AliConfig.SIGN_TYPE);
				if(signVerified) {
					AliResp returnBean = aliService.getReturnBean(req);

					TOrder order = orderService.getOrderById(Long.parseLong(out_trade_no));
					String orderNo = null;
					if(null!=order){
						orderNo = order.getOrderno();
					}
					PayRtnBean result = new PayRtnBean(out_trade_no, orderNo, returnBean.getTradeNo(), Double.toString(order.getTotalPrice()),null);
					addAttr("para", result);
					success = true;
				}
			} catch (AlipayApiException e) {
				success = false;
				log.error(StringUtil.getExceptionStr(e));
			}
			addAttr("success", success);
		}
		return pay_return;
	}

	
	/**
	 * 支付宝支付通知页面
	 */
	@RequestMapping("/alintf.act")
	@ResponseBody
	public void aliNtfAction(NtfBean bean){
		if(null!=bean){
			Integer payStatus = bean.getPayStatus();
			if(1==payStatus){
				PayResp payResp = pay.queryPayStatus(bean.getOrderId());
				if(null!=payResp && 2==payResp.getCode()){
					log.info("支付成功，订单号:" + bean.getOrderId() + ",支付金额为：" + Double.toString(bean.getAmount()) + ",交易号为：" + bean.getPayNo());
					try {
						payService.paySuccessCheckOrderStatus(1, Long.parseLong(bean.getOrderId()), DateUtil.SDF_yyyy_MM_dd_HH_mm_ss.parse(bean.getPayTime()), bean.getPayNo(), "AP");
					} catch (ParseException e) {
						log.error(StringUtil.getLogInfo(null,null,StringUtil.getExceptionStr(e)));
					}
				}
			}
		}
		try {
			ajaxWrite("SUCCESS");
		} catch (IOException e) {
			log.error(StringUtil.getLogInfo(null,null,StringUtil.getExceptionStr(e)));
		}

	}


	/**
	 * 支付宝支付通知页面
	 */
	@RequestMapping("/alintf_old.act")
	@ResponseBody
	public void aliNtfAction_old(String out_trade_no,String trade_no,String trade_status){
		if("true".equals(useOldInterface)){
			AliPayNTFBeanOld notifyBean = aliService.getNotifyBeanOld(req);
			if(null!=notifyBean){
				log.info("支付成功，订单号:" + notifyBean.getOut_trade_no() + ",支付金额为：" + notifyBean.getTotal_fee() + ",交易号为：" + notifyBean.getTrade_no());

				payService.paySuccessCheckOrderStatus(1, Long.parseLong(out_trade_no), notifyBean.getGmt_payment(), trade_no, "AP");
				try {
					resp.getWriter().write("success");
				} catch (IOException e) {
					log.error(StringUtil.getExceptionStr(e));
				}
			}
		}else{
			AliPayNTFBeanNew notifyBean = aliService.getNotifyBean(req);

			//获取支付宝POST过来反馈信息
			Map<String,String> params = aliService.getParam(req);

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
						payService.paySuccessCheckOrderStatus(1, Long.parseLong(out_trade_no), notifyBean.getGmt_payment(), trade_no, "AP");
					}
					ajaxWrite("success");

				}else {//验证失败
					ajaxWrite("fail");
				}
			} catch (AlipayApiException e) {
				log.equals(StringUtil.getExceptionStr(e));
			} catch (IOException e) {
				log.equals(StringUtil.getExceptionStr(e));
			}

		}
	}

	
	/**
	 * 微信支付通知页面
	 */
	@RequestMapping("/wxntf.act")
	@ResponseBody
	public void wxNtfAction(){
		String resultStr = getReqXMLStr(req);
		if(log.isDebugEnabled()){
			log.debug("微信支付通知返回结果为： " + resultStr);
		}
		SortedMap<String, Object> map = XMLParser.getSortedMapFromXML(resultStr, WxConfig.CHARSET.UTF8.val());
		boolean flag = PayCommonUtil.isTenpaySign(WxConfig.CHARSET.UTF8.val(), map, WxConfig.KEY);
		
		if(flag){
			try {
				payService.paySuccessCheckOrderStatus(1, Long.parseLong((String)map.get("out_trade_no")), DateUtil.SDF_yyyyMMddHHmmss.parse((String)map.get("time_end")), (String)map.get("transaction_id"), "WP");
			} catch (Exception e) {
				log.error(StringUtil.getExceptionStr(e));
			} 
		}
		
		try {
			ajaxWrite(PayCommonUtil.convertToXML(new WXRtnBiz("SUCCESS", "OK")));
		} catch (IOException e) {
			log.error(StringUtil.getExceptionStr(e));
		}
	}

	/**
	 * 微信支付通知页面
	 */
	@RequestMapping("/wxntf_old.act")
	@ResponseBody
	public void wxNtfAction_old(){
		String resultStr = getReqXMLStr(req);
		if(log.isDebugEnabled()){
			log.debug("微信支付通知返回结果为： " + resultStr);
		}
		SortedMap<String, Object> map = XMLParser.getSortedMapFromXML(resultStr, WxConfig.CHARSET.UTF8.val());
		boolean flag = PayCommonUtil.isTenpaySign(WxConfig.CHARSET.UTF8.val(), map, WxConfig.KEY);

		if(flag){
			try {
				payService.paySuccessCheckOrderStatus(1, Long.parseLong((String)map.get("out_trade_no")), DateUtil.SDF_yyyyMMddHHmmss.parse((String)map.get("time_end")), (String)map.get("transaction_id"), "WP");
			} catch (Exception e) {
				log.error(StringUtil.getExceptionStr(e));
			}
		}

		try {
			ajaxWrite(PayCommonUtil.convertToXML(new WXRtnBiz("SUCCESS", "OK")));
		} catch (IOException e) {
			log.error(StringUtil.getExceptionStr(e));
		}
	}
	
	/**
	 * 微信支付跳转
	 * @param out_trade_no
	 * @return
	 */
	@RequestMapping("/wxrtnReal.act")
	public String wxRtnAction(String out_trade_no){
		TOrder order = orderService.getOrderById(Long.parseLong(out_trade_no));
		String orderNo = null;
		boolean success = false;
		if(null!=order){
			orderNo = order.getOrderno();
			success = "4".equals(order.getOrderStatus())?true:false;
		}
		PayRtnBean result = new PayRtnBean(out_trade_no, orderNo, null, Double.toString(order.getTotalPrice()),success?"已支付":getOrderStatusDesc(order.getOrderStatus()));
		addAttr("para", result);
		addAttr("success",success);
		return pay_return;
	}

	/**
	 * 微信支付跳转
	 * @param out_trade_no
	 * @return
	 */
	@RequestMapping("/wxrtnReal_old.act")
	public String wxRtnAction_old(String out_trade_no){
		TOrder order = orderService.getOrderById(Long.parseLong(out_trade_no));
		String orderNo = null;
		boolean success = false;
		if(null!=order){
			orderNo = order.getOrderno();
			success = "4".equals(order.getOrderStatus())?true:false;
		}
		PayRtnBean result = new PayRtnBean(out_trade_no, orderNo, null, Double.toString(order.getTotalPrice()),success?"已支付":getOrderStatusDesc(order.getOrderStatus()));
		addAttr("para", result);
		addAttr("success",success);
		return pay_return;
	}
	
	/**
	 * 微信支付跳到中转页
	 * @param out_trade_no
	 * @return
	 */
	@RequestMapping("/wxrtn.act")
	public String wxRtnActionRedirect(String out_trade_no){
		
//		String resultStr = getReqXMLStr(req);
//		if(log.isDebugEnabled()){
//			log.debug("微信支付通知返回结果为： " + resultStr);
//		}
//		SortedMap<String, Object> map = XMLParser.getSortedMapFromXML(resultStr, WxConfig.CHARSET.UTF8.val());
		//Map<String, String[]> map = req.getParameterMap();
//		log.info("==========================================");
//		for(String key:map.keySet()){
//			log.info("key=" + key +",value=" + JSON.get().toJson(map.get(key)));
//		}
//		log.info("==========================================");
		
		addAttr("orderId", out_trade_no);
		addAttr("url", baseURL + "pay/wxrtnReal.act?out_trade_no=" + out_trade_no);
		return wx_rtn_redirect;
	}


	/**
	 * 微信支付跳到中转页
	 * @param out_trade_no
	 * @return
	 */
	@RequestMapping("/wxrtn_old.act")
	public String wxRtnActionRedirect_old(String out_trade_no){

//		String resultStr = getReqXMLStr(req);
//		if(log.isDebugEnabled()){
//			log.debug("微信支付通知返回结果为： " + resultStr);
//		}
//		SortedMap<String, Object> map = XMLParser.getSortedMapFromXML(resultStr, WxConfig.CHARSET.UTF8.val());
		//Map<String, String[]> map = req.getParameterMap();
//		log.info("==========================================");
//		for(String key:map.keySet()){
//			log.info("key=" + key +",value=" + JSON.get().toJson(map.get(key)));
//		}
//		log.info("==========================================");

		addAttr("orderId", out_trade_no);
		addAttr("url", baseURL + "pay/wxrtnReal_old.act?out_trade_no=" + out_trade_no);
		return wx_rtn_redirect;
	}


	/**
	 * 读取request请求xml字符串
	 * @param req
	 * @return
	 */
	private String getReqXMLStr(HttpServletRequest req){
		StringBuffer resultStr = new StringBuffer();
		try {
			BufferedReader reader = req.getReader();
			String line = null;
			while ((line = reader.readLine()) != null) {
				resultStr.append(line);
			}
			reader.close();
		} catch (Exception e) {
			log.error(StringUtil.getExceptionStr(e));
		}
		return resultStr.toString();
	}
	
	
	private String getOrderStatusDesc(String status){
		String result = null;
		//1-待审核，2-待支付，3-已审核，4-已支付，5-已取消，7-已删除，8-补录，9-已提交（针对机加酒订单，预订提交后的第一个状态
		switch (status) {
		case "1":
			result = "待审核";
			break;
		case "2":
			result = "待支付";
			break;
		case "3":
			result = "已审核";
			break;
		case "4":
			result = "已支付";
			break;
		case "5":
			result = "已取消";
			break;
		case "7":
			result = "已删除";
			break;
		case "8":
			result = "补录";
			break;
		case "9":
			result = "已提交";
			break;

		}
		return result;
	}
	
}
