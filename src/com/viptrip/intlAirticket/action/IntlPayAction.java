package com.viptrip.intlAirticket.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.SortedMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.viptrip.pay.vo.E;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.viptrip.base.action.BaseAction;
import com.viptrip.intlAirticket.entity.TIntlOrder;
import com.viptrip.intlAirticket.resource.C;
import com.viptrip.intlAirticket.service.impl.IntlOrderService;
import com.viptrip.pay.ali.service.AliPayService;
import com.viptrip.pay.ali.util.AliConfig;
import com.viptrip.pay.ali.vo.AliPayNTFBeanNew;
import com.viptrip.pay.service.Pay;
import com.viptrip.pay.vo.PayRtnBean;
import com.viptrip.pay.wx.api.bean.WXRtnBiz;
import com.viptrip.pay.wx.util.PayCommonUtil;
import com.viptrip.pay.wx.util.WxConfig;
import com.viptrip.pay.wx.util.XMLParser;
import com.viptrip.util.DateUtil;
import com.viptrip.util.PropertiesUtils;
import com.viptrip.util.StringUtil;


/**
 * H5国际机票支付
 * @author selfwhisper
 *
 */
@Controller
@RequestMapping("/intlflight")
@Scope("prototype")
public class IntlPayAction extends BaseAction{
	
	private static Logger logger = LoggerFactory.getLogger(IntlPayAction.class);

	private static String rtn_redirect = "pay_return_redirect";
	private static String pay_return = "pay_return";
	
	private static String returnURL;
	private static String notifyURL_ali;
	private static String notifyURL_wx;
	
	static{
		try {
			returnURL = PropertiesUtils.getProperty(C.PRO_INTLPAY_URL_RTN, C.FILE_INTLPAY);
			notifyURL_ali = PropertiesUtils.getProperty(C.PRO_INTLPAY_URL_NTY_ALI, C.FILE_INTLPAY);
			notifyURL_wx = PropertiesUtils.getProperty(C.PRO_INTLPAY_URL_NTY_WX, C.FILE_INTLPAY);
		} catch (IOException e) {
			logger.error("加载国际机票支付配置文件失败\r\n" + StringUtil.getExceptionStr(e));
		}
	}
	
	@Resource
	private Pay pay;
	
	@Resource(name = "ali_unipay")
	private AliPayService aliService;
	
	@Resource
	private IntlOrderService orderService;
	
	
	/**
	 * 在线支付
	 * @param payType 支付方式  
	 * 				  11-支付宝H5	12-支付宝扫码付	13-支付宝APP	14-支付宝PC支付 
	 * 				  21-微信H5 	22-微信扫描支付	23-微信APP	24-微信刷卡支付	25-微信公众号支付	26-小程序支付
	 * @param orderId 订单主键
	 * @param price 支付金额 单位元
	 * @param title 标题
	 * @param body 主题内容
	 * 
	 */
	@ResponseBody
	@RequestMapping("/intlPay.act")
	public void pay(Integer payType,String orderId,Double price,String title,String body){
		
		try {
			String notifyURL = "";
			if(null!=payType){
				switch (payType/10) {
				case 1:
					notifyURL = baseURL + notifyURL_ali;
					break;
				case 2:
					notifyURL = baseURL + notifyURL_wx;
					break;
				}
			}
			String ip = StringUtil.getIpAddr(req);
			pay.onlinePay(req, resp, E.PayType.as(payType), orderId, price, title, body, returnURL, notifyURL,ip);
		} catch (IOException e) {
			logger.error("支付异常\r\n" + StringUtil.getExceptionStr(e));
			
			try {
				resp.getWriter().write("支付发生异常");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
		
	/**
	 * 支付宝支付通知
	 */
	@RequestMapping("/alintf.act")
	@ResponseBody
	public void aliNotify(String out_trade_no,String trade_no,String trade_status){
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
					logger.info("支付成功，订单号:" + notifyBean.getOut_trade_no() + ",支付金额为：" + notifyBean.getTotal_amount() + ",交易号为：" + notifyBean.getTrade_no());
					pay.paySuccessCheckOrderStatus(2, getUser(), Long.parseLong(out_trade_no), notifyBean.getGmt_payment(), trade_no, "AP");
					
				}
				ajaxWrite("success");
				
			}else {//验证失败
				ajaxWrite("fail");
			}
		} catch (AlipayApiException e) {
			logger.error(StringUtil.getExceptionStr(e));
		} catch (IOException e) {
			logger.error(StringUtil.getExceptionStr(e));
		}
	}
	
	/**
	 * 微信支付通知
	 */
	@RequestMapping("/wxntf.act")
	@ResponseBody
	public void wxNotify(){
		String resultStr = getReqXMLStr(req);
		if(logger.isDebugEnabled()){
			logger.debug("微信支付通知返回结果为： " + resultStr);
		}
		SortedMap<String, Object> map = XMLParser.getSortedMapFromXML(resultStr, WxConfig.CHARSET.UTF8.val());
		boolean flag = PayCommonUtil.isTenpaySign(WxConfig.CHARSET.UTF8.val(), map, WxConfig.KEY);
		
		if(flag){
			try {
				pay.paySuccessCheckOrderStatus(2, getUser(), Long.parseLong((String)map.get("out_trade_no")), DateUtil.SDF_yyyyMMddHHmmss.parse((String)map.get("time_end")), (String)map.get("transaction_id"), "WP");
			} catch (Exception e) {
				logger.error(StringUtil.getExceptionStr(e));
			} 
		}
		
		try {
			ajaxWrite(PayCommonUtil.convertToXML(new WXRtnBiz("SUCCESS", "OK")));
		} catch (IOException e) {
			logger.error(StringUtil.getExceptionStr(e));
		}
	}
	
	/**
	 * 支付回跳页
	 * @param out_trade_no
	 * @return
	 */
	@RequestMapping("/rtnReal.act")
	public String wxRtnAction(String out_trade_no){
		TIntlOrder order = orderService.getOrderById(Long.parseLong(out_trade_no));
		String orderNo = null;
		boolean success = false;
		if(null!=order){
			orderNo = order.getIntlOrderno();
			success = "4".equals(order.getIntlOrderStatus())?true:false;
		}
		PayRtnBean result = new PayRtnBean(out_trade_no, orderNo, null, Double.toString(order.getIntlOrdernoPrice()),success?"已支付":getOrderStatusDesc(order.getIntlOrderStatus()));
		addAttr("para", result);
		addAttr("success",success);
		return pay_return;
	}
	
	/**
	 * 支付回跳中转页
	 * @return
	 */
	@RequestMapping("/rtn.act")
	public String rtnActionRedirect(String out_trade_no){
		
		addAttr("orderId", out_trade_no);
		addAttr("url", baseURL + "intlflight/rtnReal.act?out_trade_no=" + out_trade_no);
		
		return rtn_redirect;
	}
	
	/**
	 * 获取订单状态
	 * @param status
	 * @return
	 */
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
			logger.error(StringUtil.getExceptionStr(e));
		}
		return resultStr.toString();
	}
	
}
