package com.viptrip.pay.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alipay.api.response.AlipayTradeRefundResponse;
import com.sun.org.apache.regexp.internal.RE;
import com.viptrip.pay.PayConfig;
import com.viptrip.pay.abc.vo.*;
import com.viptrip.pay.ali.util.AliConfig;
import com.viptrip.pay.entity.PayInfo;
import com.viptrip.pay.entity.PayRefundInfo;
import com.viptrip.pay.service.impl.PayInfoService;
import com.viptrip.pay.service.impl.PayRefundInfoService;
import com.viptrip.pay.vo.E;
import com.viptrip.pay.vo.PayRefundResp;
import com.viptrip.pay.vo.PayResp;
import com.viptrip.pay.wx.api.bean.WXMwebRefundRtn;
import com.viptrip.pay.yee.service.YeepayService;
import com.viptrip.pay.yee.vo.YeeRefundVo;
import com.viptrip.util.JSON;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.viptrip.autoissue.service.AutoTicketService;
import com.viptrip.intlAirticket.entity.TIntlOrder;
import com.viptrip.intlAirticket.service.impl.IntlOrderService;
import com.viptrip.pay.abc.service.AbcPayService;
import com.viptrip.pay.ali.service.AliPayService;
import com.viptrip.pay.wx.service.WXPayService;
import com.viptrip.util.StringUtil;
import com.viptrip.wetrip.dao.ext.ComDao;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.entity.TOrder;
import com.viptrip.wetrip.entity.TPnr;
import com.viptrip.wetrip.entity.TTravelItinerary;
import com.viptrip.wetrip.service.IGetOrderService;
import org.springframework.util.Assert;

@Service("unipayService")
@Transactional
public class Pay {

	private static Logger logger = LoggerFactory.getLogger(Pay.class);
	
	@Resource
	private IGetOrderService orderService;
	
	@Resource
	private IntlOrderService intlOrderService;
	
	@Resource
	private ComDao cdao;
	
	@Resource
    private AutoTicketService autoTicketService;
	
	@Resource(name = "ali_unipay")
	private AliPayService alipay;
	
	@Resource(name = "wx_unipay")
	private WXPayService wx;
	
	@Resource(name = "abc_unipay")
	private AbcPayService abc;

	@Resource(name = "yeepay_unipay")
	private YeepayService yeepay;

	@Resource(name = "unipayInfoService")
	private IPayInfoService service;

	@Resource
	private PayRefundInfoService refundInfoService;
	/**
	 * 在线支付
	 * @param req
	 * @param resp
	 * @param payType 支付方式  
	 * 				  11-支付宝H5	12-支付宝扫码付	13-支付宝APP	 14-支付宝PC支付
	 * 				  21-微信H5 	22-微信扫描支付	23-微信APP	24-微信刷卡支付	25-微信公众号支付  26-小程序支付
	 * 				  31-农行H5  32-农行APP 33-农行PC
	 * 				  41-易宝H5	42-易宝PC 43-易宝扫码
	 * @param orderId 订单主键
	 * @param price 支付金额 单位元
	 * @param title 标题 如果为公众号支付，传openId
	 * @param body 主题内容
	 * @param notifyURL 通知URL
	 * @param returnURL 返回URL
	 * @param ip IP地址 微信支付参数
	 * @throws IOException
	 */
	@Transactional
	public void onlinePay(HttpServletRequest req, HttpServletResponse resp, E.PayType payType, String orderId, Double price, String title, String body, String notifyURL, String returnURL, String ip) throws IOException{
		Map<String,String> headers = StringUtil.getHeaders(req);
		String result = null;
		if(null!=req && null!=resp && null!=payType && StringUtil.isNotEmpty(orderId) && null!=price && StringUtil.isNotEmpty(title) && StringUtil.isNotEmpty(body)){
			if(StringUtil.isEmpty(ip)){
				ip = StringUtil.getIpAddr(req);
			}
			result = onlinePay(payType,orderId,price,title,body,notifyURL,returnURL,ip,headers);
			switch (payType) {
				case 支付宝扫码付://扫码付
//					form = alipay.wapPay(orderId, price, title, body, returnURL, notifyURL);
//					resp.getWriter().write(form);
					break;
				case 支付宝H5://支付宝H5
				case 支付宝APP://APP
				case 支付宝PC://PC
					redirectOrWrite(result,resp);
					break;
				case 微信H5://微信H5
					redirectOrWrite(result,resp);
					break;
				case 微信公众号:

					break;
				case 农行H5:
				case 农行APP:
				case 农行PC:

					break;
				case 易宝H5:
					break;
				case 易宝PC:
					redirectOrWrite(result,resp);
					break;
			}
		}else{
			resp.getWriter().write("参数不全");
		}
	}

	private void redirectOrWrite(String result,HttpServletResponse resp) throws IOException{
		if(StringUtil.isNotEmpty(result)){
			boolean matches = Pattern.compile("((http|ftp|https)://)(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,5})*(/[a-zA-Z0-9\\&%_\\./-~-]*)?").matcher(result).matches();
			if(matches){
				resp.sendRedirect(result);
			}else{
				resp.setContentType("text/html;charset=" + AliConfig.CHARSET.UTF8.value());
				resp.getWriter().write(result);
			}
		}else{
			resp.getWriter().write("参数不全");
		}
	}

	/**
	 * 在线支付 重载
	 * @param payType 支付方式
	 * 				  11-支付宝H5	12-支付宝扫码付	13-支付宝APP	 14-支付宝PC支付
	 * 				  21-微信H5 	22-微信扫描支付	23-微信APP	24-微信刷卡支付	25-微信公众号支付  26-小程序支付
	 * 				  31-农行H5  32-农行APP 33-农行PC
	 * 				  41-易宝H5	42-易宝PC 43-易宝扫码
	 * @param orderId 订单主键
	 * @param price 支付金额 单位元
	 * @param title 标题 如果为公众号支付，传openId
	 * @param body 主题内容
	 * @param notifyURL 通知URL
	 * @param returnURL 返回URL
	 * @param ip IP地址 微信支付参数
	 * @throws IOException
	 */
	@Transactional
	public String onlinePay(E.PayType payType, String orderId, Double price, String title, String body, String notifyURL, String returnURL, String ip,Map<String,String> headers){
		String result = null;
		if("true".equals(PayConfig.isTest)){
			price = 0.01;
		}
		if(null!=payType){
			MPaymentRequest mpr = null;
			String form = null;
			boolean existSuccessPayInfo = service.existCantPayRecode(orderId); // 是否存在不能支付的记录
			if(!existSuccessPayInfo){ //不存在成功支付记录
				PayInfo pi = service.getPayInfo(orderId,payType.getCode(), E.UnipayStatus.Waiting.getCode());
				Double amount = 100*price;
				if(null==pi){
					pi = new PayInfo(orderId, amount.intValue(),title,body,null,notifyURL,returnURL,payType.getCode());
					pi.setStatus(E.UnipayStatus.Waiting.getCode());
					service.update(pi);
				}else{
					pi.setAmount(amount.intValue());
					pi.setSubject(title);
					pi.setBody(body);
					pi.setNotifyUrl(notifyURL);
					pi.setReturnUrl(returnURL);
					service.update(pi);
				}

				try {
					switch (payType) {
                        case 支付宝H5://支付宝H5
                            notifyURL= PayConfig.url + "unipay/ntf.act?type=" + payType.getCode().intValue() + "&url=" + URLEncoder.encode(notifyURL,"utf-8");
                            returnURL= PayConfig.url + "unipay/rtn.act?type=" + payType.getCode().intValue() + "&url=" + URLEncoder.encode(returnURL,"utf-8");
                            form = alipay.wapPay(orderId, price, title, body, returnURL, notifyURL);
                            result = form;
                            break;
                        case 支付宝扫码付://扫码付
    //					form = alipay.wapPay(orderId, price, title, body, returnURL, notifyURL);
    //					resp.getWriter().write(form);

                            break;
                        case 支付宝APP://APP
                            notifyURL= PayConfig.url + "unipay/ntf.act?type=" + payType.getCode().intValue() + "&url=" + URLEncoder.encode(notifyURL,"utf-8");
                            returnURL= PayConfig.url + "unipay/rtn.act?type=" + payType.getCode().intValue() + "&url=" + URLEncoder.encode(returnURL,"utf-8");
                            form = alipay.appPay(orderId, price, title, body, returnURL, notifyURL);
                            result = form;
                            break;
                        case 支付宝PC://PC
                            notifyURL= PayConfig.url + "unipay/ntf.act?type=" + payType.getCode().intValue() + "&url=" + URLEncoder.encode(notifyURL,"utf-8");
                            returnURL= PayConfig.url + "unipay/rtn.act?type=" + payType.getCode().intValue() + "&url=" + URLEncoder.encode(returnURL,"utf-8");
                            form = alipay.pagePay(orderId, price, title, body, returnURL, notifyURL);
                            result = form;
                            break;
                        case 微信H5://微信H5
                            notifyURL= PayConfig.url + "unipay/ntf.act";
                            returnURL= PayConfig.url + "unipay/rtn.act?type=" + payType.getCode().intValue() + "&url=" + URLEncoder.encode(returnURL,"utf-8");
                            //ip = StringUtil.isEmpty(ip)?StringUtil.getIpAddr(req):ip;
                            result = wx.mwebPay(orderId, price, title, body, ip, returnURL, notifyURL,headers);
                            break;
						case 微信APP:
							notifyURL= PayConfig.url + "unipay/ntf.act";
							returnURL= PayConfig.url + "unipay/rtn.act?type=" + payType.getCode().intValue() + "&url=" + URLEncoder.encode(returnURL,"utf-8");
							Map<String, String> map = wx.appPay(orderId, price, body, ip, notifyURL, headers);
							if(null!=map&&map.size()>0){
								result = JSON.get().notEscapeHTML(true).toJson(map);
							}
							break;
						case 微信公众号:
							notifyURL= PayConfig.url + "unipay/ntf.act";
							result = wx.JSAPIPay(orderId,price,body,ip,notifyURL,title,headers);
							break;
                        case 农行H5:
                            notifyURL= PayConfig.url + "unipay/ntf.act?type=" + payType.getCode().intValue() + "&url=" + URLEncoder.encode(notifyURL,"utf-8");
                            returnURL= PayConfig.url + "unipay/rtn.act?type=" + payType.getCode().intValue() + "&url=" + URLEncoder.encode(returnURL,"utf-8");
                            notifyURL += "&rtnurl=" + URLEncoder.encode(returnURL,"utf-8");
                            mpr = new H5Request(orderId, price, title, notifyURL);
                            result = abc.unipay( mpr);
                            break;
                        case 农行APP:
                            notifyURL= PayConfig.url + "unipay/ntf.act?type=" + payType.getCode().intValue() + "&url=" + URLEncoder.encode(notifyURL,"utf-8");
                            returnURL= PayConfig.url + "unipay/rtn.act?type=" + payType.getCode().intValue() + "&url=" + URLEncoder.encode(returnURL,"utf-8");
                            notifyURL += "&rtnurl=" + URLEncoder.encode(returnURL,"utf-8");
                            mpr = new AppRequest(orderId, price, title, notifyURL);
                            result = abc.unipay( mpr);
                            break;
                        case 农行PC:
                            notifyURL= PayConfig.url + "unipay/ntf.act?type=" + payType.getCode().intValue() + "&url=" + URLEncoder.encode(notifyURL,"utf-8");
                            returnURL= PayConfig.url + "unipay/rtn.act?type=" + payType.getCode().intValue() + "&url=" + URLEncoder.encode(returnURL,"utf-8");
                            notifyURL += "&rtnurl=" + URLEncoder.encode(returnURL,"utf-8");
                            mpr = new PcRequest(orderId, price, title, notifyURL);
                            result = abc.unipay( mpr);
                            break;
						case 易宝H5:

                            break;
						case 易宝PC:
							notifyURL= PayConfig.url + "unipay/ntf.act?type=" + payType.getCode().intValue() + "&url=" + URLEncoder.encode(notifyURL,"utf-8");
							returnURL= PayConfig.url + "unipay/rtn.act?type=" + payType.getCode().intValue() + "&url=" + URLEncoder.encode(returnURL,"utf-8");
							result = yeepay.pagePay(orderId,price,title,returnURL,notifyURL);
//							result = yeepay.pagePay(orderId,price,title,returnURL,notifyURL);
                            break;
                    }
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
					result = "订单状态错误";
				}
			}else{
				//存在成功支付记录
				result = "订单状态错误";
			}
		}
		if (logger.isDebugEnabled()){
			logger.debug(StringUtil.getLogInfo(null,null,"调用统一支付接口返回值为：" + result));
		}
		return result;
	}


	/**
	 * 在线退款
	 * @param payType 支付方式
	 * 				  11-支付宝H5	12-支付宝扫码付	13-支付宝APP	 14-支付宝PC支付
	 * 				  21-微信H5 	22-微信扫描支付	23-微信APP	24-微信刷卡支付	25-微信公众号支付  26-小程序支付
	 * 				  31-农行H5  32-农行APP 33-农行PC
	 * @param orderId
	 * 					订单主键
	 * @param refundNo
	 * 					退款号
	 * @param refundAmount
	 * 					退款金额
	 * @param refundReason
	 * 					退款金额
	 * @return
	 */
	public PayRefundResp onlineRefund(Integer payType, String orderId, String refundNo, Double refundAmount, String refundReason,Map<String,String> headers){
		PayRefundResp result = new PayRefundResp();
		try {
			Assert.notNull(orderId);
			Assert.notNull(refundNo);
			Assert.notNull(refundAmount);
			Assert.notNull(refundReason);

			PayRefundInfo payRefundInfo = refundInfoService.queryExistRefundInfo(orderId, refundNo,payType);
			if(null==payRefundInfo){
				PayInfo payInfo = refundInfoService.getChangePayInfos(orderId, refundNo,payType);
				if(null!=payInfo){
					Integer amount = payInfo.getAmount();
					Double refundedAoumt = refundInfoService.getRefundedAoumt(payInfo.getId());
					Double ra = ((refundAmount==null?0d:refundAmount)*100 + (refundedAoumt==null?0d:refundedAoumt));
					if(amount>=ra){
						result = refund(payInfo.getType(),payInfo.getOrderno(), refundNo, amount/100D, refundAmount, refundReason,headers);
						if(result.isSuccess()){
							refundAmount = ((refundAmount==null?0d:refundAmount)*100);
							Integer changeFlag = orderId.indexOf("_")>0?1:0;
							payRefundInfo = new PayRefundInfo(payInfo.getId(),refundNo,changeFlag,refundAmount.intValue(),result.getRefundTime());
							refundInfoService.save(payRefundInfo);

							//更新总的退款金额以及退款时间
							payInfo.setRefundTime(result.getRefundTime());
							payInfo.setRefundAmount(ra.intValue());
							service.update(payInfo);
						}
					}else{
						result.setMsg("退款金额有误");
					}
				}else{
					result.setMsg("该订单号状态错误");
				}
			}else{
				result.setMsg("该退款号已经存在退款记录");
			}
		}catch (Exception e){
			logger.error(StringUtil.getLogInfo(null,null,"在线退款发生异常，参数为payType=" + payType + ",orderId=" + orderId + ",refundNo=" + refundNo +",refundAmount=" + refundAmount +",refundReason=" + refundReason + "\r\n" + StringUtil.getExceptionStr(e)));
			result.setMsg("退款参数不全");
		}
		return result;
	}

	/**
	 * 在线退款
	 * @param orderId
	 * 					订单主键
	 * @param refundNo
	 * 					退款号
	 * @param refundAmount
	 * 					退款金额
	 * @param refundReason
	 * 					退款金额
	 * @return
	 */
	public PayRefundResp onlineRefund(String orderId, String refundNo, Double refundAmount, String refundReason,Map<String,String> headers){
		return onlineRefund(null,orderId,refundNo,refundAmount,refundReason,headers);
	}


	/**
	 * 取消在线支付
	 * @param payType
	 * @param orderId
	 * @return
	 */
	public PayResp cancleOnlinePay(Integer payType,String orderId){
		PayResp result = new PayResp(0,"成功",null);
		if(StringUtil.isNotEmpty(orderId)){
			List<Object> param = new ArrayList<>();
			String hql = "from PayInfo where orderno=? ";
			param.add(orderId);
			if(null!=payType){
				hql += " and type=?" ;
				param.add(payType);
			}
			List<PayInfo> list = cdao.queryForList(hql, param.toArray());
			if(null!=list){
				for(PayInfo pi:list){
					if(E.UnipayStatus.Waiting.getCode().equals(pi.getStatus())){//待支付
						pi.setStatus(E.UnipayStatus.Close.getCode());
						service.update(pi);

						//想支付端发送取消支付请求
						try {
							cancleOnlinePay2Server(pi.getType(),pi.getOrderno());
						}catch (Exception e){
							logger.error(StringUtil.getLogInfo(null,null,"向支付方发送取消支付请求发生异常\r\n" + StringUtil.getExceptionStr(e)));
						}
					}
				}
			}else {
				result = new PayResp(1,"没有查找到在线支付记录",null);
			}
		}
		if (logger.isDebugEnabled()){
			logger.info(StringUtil.getLogInfo(null,null,"取消在线支付,参数payType=" + payType + ",orderId=" + orderId + ",返回结果为：" + JSON.get().toJson(result)));
		}
		return result;
	}


	private PayResp cancleOnlinePay2Server(Integer payType,String orderId){
		PayResp result = new PayResp(0,"成功",null);
		if(null!=payType && StringUtil.isNotEmpty(orderId)){
			switch (payType/10){
				case 1://支付宝
					alipay.payClose(orderId);
					break;
				case 2://微信
					wx.close(orderId,null);
					break;
				case 3://农行
					break;
				case 4://易宝
					break;
				default:
					break;
			}
		}
		return result;
	}


	/**
	 *	查询订单支付状态
	 * @param payType 支付方式
	 * 				  11-支付宝H5	12-支付宝扫码付	13-支付宝APP	 14-支付宝PC支付
	 * 				  21-微信H5 	22-微信扫描支付	23-微信APP	24-微信刷卡支付	25-微信公众号支付  26-小程序支付
	 * 				  31-农行H5  32-农行APP 33-农行PC
	 * @param orderId
	 * 					订单主键
	 * @return
	 * 		1-待支付 2-支付完成 3-支付关闭(超时或取消) 4-支付结束(退款完成)
	 */
	public PayResp queryPayStatus(Integer payType, String orderId){
		Integer  result = -1;
		PayInfo data = null;
		if(null!=payType && !StringUtil.isEmpty(orderId)){
			List<PayInfo> list = service.query(orderId, null);
			if(null!=list && list.size()>0){
				for(PayInfo pi:list){
					if(payType.intValue()==pi.getType()){
						result = pi.getStatus();
						data = pi;
						break;
					}
				}
			}
		}
		E.UnipayStatus unipayStatus = E.UnipayStatus.as(result);
		return new PayResp(unipayStatus.getCode(),unipayStatus.getMsg(),data);
	}

	/**
	 *	查询订单支付状态
	 * @param orderId
	 * 					订单主键
	 * @return
	 * 		1-待支付 2-支付完成 3-支付关闭(超时或取消) 4-支付结束(退款完成)
	 */
	public PayResp queryPayStatus(String orderId){
		Integer  result = -1;
		PayInfo data = null;
		if(StringUtil.isNotEmpty(orderId)){
			List<PayInfo> list = service.query(orderId, null);
			if(null!=list && list.size()>0){
				for(PayInfo pi:list){
					if(pi.getStatus().equals(E.UnipayStatus.Success.getCode())){
						result = E.UnipayStatus.Success.getCode();
						data = pi;
						break;
					}
					if(pi.getStatus().equals(E.UnipayStatus.Finished.getCode())){
						result = E.UnipayStatus.Finished.getCode();
						data = pi;
						break;
					}
					if(pi.getStatus().equals(E.UnipayStatus.Waiting.getCode())){
						result = E.UnipayStatus.Waiting.getCode();
						data = pi;
						break;
					}
				}
			}
		}
		E.UnipayStatus unipayStatus = E.UnipayStatus.as(result);
		return new PayResp(unipayStatus.getCode(),unipayStatus.getMsg(),data);
	}




	/**
	 * 支付成功更改支付状态
	 * @param flag 业务类别：1-国内机票 2-国际机票
	 * @param orderId 订单id
	 * @param payTime 支付时间
	 * @param tradeNo 交易号
	 * @param payType 支付方式  AP-为支付宝  WP-为微信
	 */
	@Transactional
	public void paySuccessCheckOrderStatus(Integer flag, TAcUser user,Long orderId,Date payTime,String tradeNo,String payType) {
		if(1==flag){//国内机票业务
			TOrder order = orderService.getOrderById(orderId);
			if(null!=order){
				String orderStatus = order.getOrderStatus();
					if("2".equals(orderStatus)){ //原状态为待支付
						order.setOrderStatus("4");
						order.setPayTime(payTime==null?new Date():payTime);
						order.setTradeNo(tradeNo);
						order.setPayMethodDetail(payType);
						if(null!=user){
							order.setPayId(user.getUserid() + "");
							order.setPayName(user.getUsername());
							order.setPayTel(user.getPhone());
							order.setPayEmail(user.getEmail());
						}

						Set<TPnr> tPnrs = order.getTPnrs();
						for (TPnr tPnr : tPnrs) {
							Set<TTravelItinerary> tTravelItineraries = tPnr.getTTravelItineraries();
							for (Iterator<TTravelItinerary> iterator = tTravelItineraries
									.iterator(); iterator.hasNext();) {
								TTravelItinerary tTravelItinerary = (TTravelItinerary) iterator
										.next();
								tTravelItinerary.setFlightStatus("11");//待出票
								cdao.executeUpdate(tTravelItinerary);
							}
						}
						cdao.executeUpdate(order);
						
						//自动出票逻辑，在线支付返回后，修改国内机票订单状态
						String autoMaticFlag=autoTicketService.getAutomaticTicket();
			            if(StringUtils.isNotEmpty(autoMaticFlag) && autoMaticFlag.equals("open")){
			                autoTicketService.addFromPay(order.getOrderno(), "1");
			            }
			            
					}
			}
		}else if(2==flag){//国际机票业务
			TIntlOrder order = intlOrderService.getOrderById(orderId);
			if(null!=order){
				String orderStatus = order.getIntlOrderStatus();
				if("2".equals(orderStatus)){
					order.setIntlOrderStatus("4");
					order.setPayTime(payTime==null?new Date():payTime);
					order.setTradeno(tradeNo);
					order.setPayMethodDetail(payType);
					if(null!=user){
						order.setPayId(user.getUserid() + "");
						order.setPayName(user.getUsername());
						order.setPayTel(user.getPhone());
						order.setPayEmail(user.getEmail());
					}
				}
				cdao.executeUpdate(order);
			}
		}
	}




	public PayInfo queryPayInfo(Integer payType, String orderId){
		PayInfo result = null;
		if(null!=payType && StringUtil.isNotEmpty(orderId)){
			result = service.getPayInfoByNoAndType(payType,orderId);
		}
		return result;
	}


	/**
	 *
	 * @param payType 支付方式
	 * 				  11-支付宝H5	12-支付宝扫码付	13-支付宝APP	14-支付宝PC支付
	 * 				  21-微信H5 	22-微信扫描支付	23-微信APP	24-微信刷卡支付	25-微信公众号支付	26-小程序支付
	 * 				  31-农行H5  32-农行APP 33-农行PC
	 * 				  41-易宝
	 * @param orderId
	 * @param refundNo
	 * @param refundAmount
	 * @param refundReason
	 */
	public PayRefundResp refund(Integer payType, String orderId, String refundNo, Double totalAmount, Double refundAmount, String refundReason,Map<String,String> headers){
		PayRefundResp result = new PayRefundResp();
		if(null!=payType){
			switch (payType/10){
				case 1://支付宝
					AlipayTradeRefundResponse refund = alipay.refund(orderId, refundNo, refundAmount, refundReason);
					if(null!=refund){
						if(refund.isSuccess()){
							String refundFee = refund.getRefundFee();
							Double amount = Double.parseDouble(refundFee);
							Date date = refund.getGmtRefundPay();
							result.success(amount,date);
							refundSuccessUpdateInfo(orderId, payType, refundAmount);
						}else{
							result.setMsg(refund.getSubMsg());
						}
					}
					break;
				case 2:
					WXMwebRefundRtn wxMwebRefundRtn = wx.refund(orderId, refundNo, totalAmount, refundAmount, refundReason,headers);
					if(null!=wxMwebRefundRtn && wxMwebRefundRtn.isSuccess()){
						Integer refund_fee = wxMwebRefundRtn.getRefund_fee();
						Double amount = refund_fee==null?0D:refund_fee/100D;
						result.success(amount,null);
						refundSuccessUpdateInfo(orderId, payType, refundAmount);
					}else if(null!=wxMwebRefundRtn){
						result.setMsg(wxMwebRefundRtn.getReturn_msg());
					}
					break;
				case 3:
					Resp<RefundRes> resp = abc.refund(orderId, refundNo, refundAmount);
					if(null!=resp&&resp.isSuccess()){
						RefundRes data = resp.getData();
						String timeStr = data.getHostDate() + " " + data.getHostTime();
						Date time = null;
						try {
							time = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss").parse(timeStr);
						} catch (ParseException e) {
							e.printStackTrace();
						}
						result.success(refundAmount,time);
						refundSuccessUpdateInfo(orderId, payType, refundAmount);
					}else if(null!=resp){
						result.setMsg(resp.getMsg());
					}
					break;
				case 4:
					Resp<YeeRefundVo> refund1 = yeepay.refund(orderId, refundNo, totalAmount, refundAmount, refundReason);
					if(refund1.isSuccess()){
						YeeRefundVo data = refund1.getData();
						result.success(data.getR3_Amt(),new Date());
						refundSuccessUpdateInfo(orderId, payType, refundAmount);
					}else{
						result.setMsg(refund1.getMsg());
					}
					break;
			}
		}
		return result;
	}

	/**
	 * 退款成功更新记录
	 * @param orderId
	 * @param payType
	 * @param refundAmount
	 */
	private void refundSuccessUpdateInfo(String orderId,Integer payType,Double refundAmount){
		PayInfo payInfo = service.getPayInfo(orderId, payType, E.UnipayStatus.Success.getCode());
		if(null!=payInfo){
			payInfo.setStatus(E.UnipayStatus.Finished.getCode());
			payInfo.setRefundTime(new Date());
			refundAmount = refundAmount==null?0:refundAmount*100;
			payInfo.setRefundAmount(refundAmount.intValue());
			service.update(payInfo);
		}
	}

	/**
	 * 获取阿里请求参数
	 * @param req
	 * @return
	 */
	public Map<String,String> getRequestMap(HttpServletRequest req,String charset){
		Map<String,String> result = new HashMap<>();
		if(null!=req){
			if("GET".equalsIgnoreCase(req.getMethod()) && StringUtil.isNotEmpty(charset)){
				String queryString = req.getQueryString();
				if(StringUtil.isNotEmpty(queryString)){
					String[] ps = queryString.split("&");
					String[] p = null;
					for(String param : ps){
						p = param.split("=");
						try {
							if(null!=p && p.length==2 && StringUtil.isNotEmpty(p[0]) &&StringUtil.isNotEmpty(p[1])){
								result.put(p[0], URLDecoder.decode(p[1], charset));
							}
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					}
				}
			}else{
				Map<String, String[]> requestParams = req.getParameterMap();
				for (Iterator<?> iter = requestParams.keySet().iterator(); iter.hasNext();) {
					String name = (String) iter.next();
					String[] values = (String[]) requestParams.get(name);
					String valueStr = "";
					for (int i = 0; i < values.length; i++) {
						valueStr = (i == values.length - 1) ? valueStr + values[i]
								: valueStr + values[i] + ",";
					}
					result.put(name, valueStr);
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		String s = "http://www.163.com";
		boolean matches = Pattern.compile("((http|ftp|https)://)(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,5})*(/[a-zA-Z0-9\\&%_\\./-~-]*)?").matcher(s).matches();
		System.out.println(matches);
	}


}
