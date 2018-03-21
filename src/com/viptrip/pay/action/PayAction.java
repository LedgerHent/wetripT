package com.viptrip.pay.action;


import com.abc.pay.client.TrxException;
import com.abc.pay.client.ebus.PaymentResult;
import com.alipay.api.internal.util.AlipaySignature;
import com.viptrip.base.action.BaseAction;
import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.pay.PayConfig;
import com.viptrip.pay.ali.util.AliConfig;
import com.viptrip.pay.ali.vo.AliPayNTFBeanNew;
import com.viptrip.pay.entity.PayInfo;
import com.viptrip.pay.entity.PayNotify;
import com.viptrip.pay.service.IPayInfoService;
import com.viptrip.pay.service.Pay;
import com.viptrip.pay.service.impl.PayInfoService;
import com.viptrip.pay.task.Notify;
import com.viptrip.pay.task.NotifyTask;
import com.viptrip.pay.task.PayUtil;
import com.viptrip.pay.vo.*;
import com.viptrip.pay.wx.api.bean.WXRtnBiz;
import com.viptrip.pay.wx.util.PayCommonUtil;
import com.viptrip.pay.wx.util.WxConfig;
import com.viptrip.pay.wx.util.XMLParser;
import com.viptrip.pay.yee.util.YeepayConfig;
import com.viptrip.pay.yee.util.YeepayUtil;
import com.viptrip.util.JSON;
import com.viptrip.util.StringUtil;
import etuf.v1_0.model.base.output.OutputResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 统一支付接口
 * Created by selfwhisper on 0009 2017/11/9.
 */
@Controller("unipayAction")
@RequestMapping("/unipay")
public class PayAction extends BaseAction{

    private static Logger log = LoggerFactory.getLogger(PayAction.class);

    @Resource(name = "unipayService")
    private Pay pay;

    @Resource(name = "unipayInfoService")
    private IPayInfoService service;

    /**
     * 在线支付
     * @param payType 支付方式
     * 				  11-支付宝H5	12-支付宝扫码付	13-支付宝APP	 14-支付宝PC支付
     * 				  21-微信H5 	22-微信扫描支付	23-微信APP	24-微信刷卡支付	25-微信公众号支付	26-小程序支付
     * 				  31-农行H5  32-农行APP 33-农行PC
     * 				  41-易宝
     * @param orderId 订单主键
     * @param price 支付金额 单位元
     * @param title 标题
     * @param body 主题内容
     * @param notifyURL 通知URL
     * @param returnURL 返回URL
     */
    @RequestMapping("/pay.act")
    @ResponseBody
    public void pay(Integer payType, String orderId, Double price, String title, String body, String notifyURL, String returnURL, String ip){
        if(StringUtil.isEmpty(ip)){
            ip = StringUtil.getIpAddr(req);
        }
       /* com.viptrip.pay.controller.Pay pay = new com.viptrip.pay.controller.Pay();
        Request_Pay request_pay = new Request_Pay(payType, orderId, price, title, body, notifyURL, returnURL, ip);*/
//        OutputResult<Response_Pay, String> response_payStringOutputResult = pay.ClientRequest(request_pay, Response_Pay.class);
        String form = pay.onlinePay(E.PayType.as(payType), orderId, price, title, body, notifyURL, returnURL, ip, null);
//        if(response_payStringOutputResult.IsSucceed()){
//            Response_Pay resultObj = response_payStringOutputResult.getResultObj();
//            String form = resultObj.form;
            try {
                boolean matches = Pattern.compile("((http|ftp|https)://)(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,5})*(/[a-zA-Z0-9\\&%_\\./-~-]*)?").matcher(form).matches();
                if(matches){
                    resp.sendRedirect(form);
                }else{
                    resp.setContentType("text/html;charset=" + AliConfig.CHARSET.UTF8.value());
                    resp.getWriter().write(form);
                }
            } catch (IOException e) {
                log.error(StringUtil.getLogInfo(null,null,StringUtil.getExceptionStr(e)));
                try {
                    resp.getWriter().write(StringUtil.getLogInfo(null,null,StringUtil.getExceptionStr(e)));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
//        }
    }

    @RequestMapping("/refund.act")
    @ResponseBody
    public PayRefundResp refund(Integer payType, String orderId, String refundNo, Double refundAmount, String refundReason){
        Map<String,String> headers = StringUtil.getHeaders(req);
        if(null == payType){
            return pay.onlineRefund(orderId, refundNo, refundAmount, refundReason, headers);
        }else{
            return pay.onlineRefund(payType, orderId, refundNo, refundAmount, refundReason, headers);
        }
    }

    /*@RequestMapping("/query.act")
    @ResponseBody
    public void query(Integer payType,String orderId){
        pay.query(payType,orderId);
    }*/

    @RequestMapping("/queryStatus.act")
    @ResponseBody
    public PayResp queryStatus(Integer payType, String orderId){
        if (null==payType){
            return  pay.queryPayStatus(orderId);
        }else{
            return pay.queryPayStatus(payType,orderId);
        }
    }

    /**
     * 回跳统一处理
     * @param type 支付方式
     * 				  11-支付宝H5	12-支付宝扫码付	13-支付宝APP	 14-支付宝PC支付
     * 				  21-微信H5 	22-微信扫描支付	23-微信APP	24-微信刷卡支付	25-微信公众号支付	26-小程序支付
     * 				  31-农行H5  32-农行APP 33-农行PC
     * 			      41-易宝
     * @param url
     */
    @RequestMapping("/rtn.act")
    @ResponseBody
    public void rtn(Integer type,String url){
        log.debug(StringUtil.getLogInfo(null,null,"支付完成跳转：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date())));
        log.debug(StringUtil.getLogInfo(null,null,"type=" + type));
        log.debug(StringUtil.getLogInfo(null,null,"url=" + url));
        String orderNo = null;
        String amount = null;
        if(StringUtil.isNotEmpty(url)){
            try {
                url = URLDecoder.decode(url,PayConfig.charset);
            } catch (UnsupportedEncodingException e) {
                log.error(StringUtil.getLogInfo(null,null,StringUtil.getExceptionStr(e)));
            }
        }
        if(null!=type && E.PayType.支付宝APP.getCode()/10==(type/10)){//支付宝
            Map<String, String> aliReqMap = pay.getRequestMap(req,null);
            aliReqMap.remove("type");
            aliReqMap.remove("url");
            log.debug("支付回跳统一处理[支付宝]返回值为:" + JSON.get().toJson(aliReqMap));
            orderNo = aliReqMap.get("out_trade_no");
            if(11==type){
                amount = aliReqMap.get("total_amount");
            }
            if(14==type){
                amount = aliReqMap.get("total_amount");
            }
        }
        if(null!=type  && E.PayType.微信H5.getCode()/10==(type/10)){ //微信
            if(21==type){
                orderNo = req.getParameter("out_trade_no");
                amount = req.getParameter("total_amount");
            }
        }
        if(null!=type && E.PayType.农行H5.getCode()/10==(type/10)){//农行支付
            orderNo = req.getParameter("out_trade_no");
            amount = req.getParameter("total_amount");
        }
        if(null!=type && E.PayType.易宝H5.getCode()/10==(type/10)){//易宝支付
            String r9_bType = req.getParameter("r9_BType");
            log.debug(StringUtil.getLogInfo(null,null,"易宝支付返回，类型为" + r9_bType));
            if("1".equalsIgnoreCase(r9_bType)){ //同步跳转
                orderNo = req.getParameter("r6_Order");
                amount = req.getParameter("r3_Amt");
            }else{ //异步通知
                yeePayNotifyHandle(type);
                return ;
            }
        }
        try {
            url += (url.indexOf("?")>=0?"&":"?") + "orderId=" + orderNo + "&amount=" + (amount==null?"":amount) + "&payType=" + type.intValue();
            if(StringUtil.isNotEmpty(orderNo) && null!=type){
                PayResp payResp = pay.queryPayStatus(type, orderNo);
                if(null!=payResp){
                    url += "&code=" + payResp.getCode() + "&msg=" + payResp.getMsg();
                }
            }
            if(log.isDebugEnabled()){
                log.debug("支付回跳地址为：" + url);
            }
            resp.sendRedirect(url);
        } catch (IOException e) {
            log.error(StringUtil.getLogInfo(null,null,StringUtil.getExceptionStr(e)));
        }

    }


    /**
     * 通知统一处理
     * @param type 支付方式
     * 				  11-支付宝H5	12-支付宝扫码付	13-支付宝APP	 14-支付宝PC支付
     * 				  21-微信H5 	22-微信扫描支付	23-微信APP	24-微信刷卡支付	25-微信公众号支付	26-小程序支付
     * 				  31-农行H5  32-农行APP 33-农行PC
     * 			      41-易宝H5 42-易宝PC 43-易宝扫码
     * @param url
     */
    @RequestMapping("/ntf.act")
    @ResponseBody
    public void ntf(Integer type,String url){
        log.debug(StringUtil.getLogInfo(null,null,"支付完成跳转：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date())));
        String orderNo = null;
        log.debug(StringUtil.getLogInfo(null,null,"type=" + type));
        log.debug(StringUtil.getLogInfo(null,null,"url=" + url));
        if(StringUtil.isNotEmpty(url)){
            try {
                url = URLDecoder.decode(url, PayConfig.charset);
            } catch (UnsupportedEncodingException e) {
                log.error(StringUtil.getLogInfo(null,null,StringUtil.getExceptionStr(e)));
            }
        }
        if(null!=type && 1==(type/10)){//支付宝
            Map<String, String> aliReqMap = pay.getRequestMap(req,null);
            aliReqMap.remove("type");
            aliReqMap.remove("url");
            log.debug("支付通知统一处理[支付宝]返回值为:" + JSON.get().toJson(aliReqMap));
            AliPayNTFBeanNew bean = JSON.get().fromJson(JSON.get().toJson(aliReqMap), AliPayNTFBeanNew.class);
            orderNo = bean.getOut_trade_no();
            try {
                boolean signVerified = AlipaySignature.rsaCheckV1(aliReqMap, AliConfig.ALI_PUBLIC_KEY, AliConfig.CHARSET.UTF8.value(), AliConfig.SIGN_TYPE);
                log.debug("支付通知统一处理[支付宝]校验结果为:" + signVerified);
                if(signVerified){ //校验通过
                    if("TRADE_SUCCESS".equals(bean.getTrade_status())){
                        PayInfo payInfo = pay.queryPayInfo(type, orderNo);
                        sendNotify(orderNo,type,bean.getTotal_amount(),bean.getTrade_no(),bean.getGmt_payment(),bean.getBuyer_id());
                    }
                }
                resp.getWriter().write("success");
            } catch (Exception e) {
                log.error(StringUtil.getLogInfo(null,null,StringUtil.getExceptionStr(e)));
            }
        }
        if(null==type){ //微信 微信异步通知不能带参数
            String resultStr = getReqXMLStr(req);
            if(log.isDebugEnabled()){
                log.debug("微信支付通知返回结果为： " + resultStr);
            }
            SortedMap<String, Object> map = XMLParser.getSortedMapFromXML(resultStr, WxConfig.CHARSET.UTF8.val());
            boolean flag = PayCommonUtil.isTenpaySign(WxConfig.CHARSET.UTF8.val(), map, WxConfig.KEY);
            if(flag){//校验通过
//                <result_code><![CDATA[SUCCESS]]></result_code>
//                <return_code><![CDATA[SUCCESS]]></return_code>
                String resultCode = (String) map.get("result_code");
                String returnCode = (String) map.get("return_code");
                if("SUCCESS".equals(resultCode) && "SUCCESS".equals(returnCode)){
                    orderNo = (String) map.get("out_trade_no");
                    Integer amount = (Integer) map.get("total_fee");
                    String tradeNo = (String) map.get("transaction_id");
                    //String payTime = (String) map.get("time_end");
                    Date payTime = null;
                    try{
                        payTime = new SimpleDateFormat("yyyyMMddHHmmss").parse((String) map.get("time_end"));
                    }catch (Exception e){
                        log.error(StringUtil.getLogInfo(null,null,StringUtil.getExceptionStr(e)));
                    }
                    String tradeType = (String) map.get("trade_type");
                    String openId = (String) map.get("openid");
                    if("MWEB".equals(tradeType)){
                        type = 21;
                    }
                    if("NATIVE".equals(tradeType)){
                        type = 22;
                    }
                    if("APP".equals(tradeType)){
                        type = 23;
                    }
                    if("JSAPI".equals(tradeType)){
                        type = 25;
                    }
                    sendNotify(orderNo,type,amount/100D,tradeNo,payTime,openId);
                }

            }
            try {
                ajaxWrite(PayCommonUtil.convertToXML(new WXRtnBiz("SUCCESS", "OK")));
            } catch (IOException e) {
                log.error(StringUtil.getLogInfo(null,null,StringUtil.getExceptionStr(e)));
            }
        }
        if(null!=type  && 3==(type/10)){ //农行
            //1、取得MSG参数，并利用此参数值生成支付结果对象
            String msg = req.getParameter("MSG");
            String rtnurl = req.getParameter("rtnurl");
            if(StringUtil.isNotEmpty(rtnurl)){
                try {
                    rtnurl = URLDecoder.decode(rtnurl,PayConfig.charset);
                } catch (UnsupportedEncodingException e) {
                    log.error(StringUtil.getLogInfo(null,null,StringUtil.getExceptionStr(e)));
                }
            }
            try {
                PaymentResult tResult = new PaymentResult(msg);
                Double amount = null;
                if(null!=tResult){
                    if(tResult.isSuccess()){
                        orderNo = tResult.getValue("OrderNo");
                        String amountStr = tResult.getValue("Amount");
                        String batchNo = tResult.getValue("BatchNo");
                        String voucherNo = tResult.getValue("VoucherNo");
                        String hostDate = tResult.getValue("HostDate");
                        String houtTime = tResult.getValue("HoutTime");
                        String iRspRef = tResult.getValue("iRspRef");
                        try {
                            amount = Double.parseDouble(amountStr);
                        } catch (NumberFormatException e) {
                            log.error(StringUtil.getLogInfo(null,null,StringUtil.getExceptionStr(e)));
                        }
                        Date payTime = null;
                        try{
                            payTime = new SimpleDateFormat("yyyy/MM/ddHHmmss").parse(hostDate+houtTime);
                        }catch (Exception e){
                            log.error(StringUtil.getLogInfo(null,null,StringUtil.getExceptionStr(e)));
                        }
                        sendNotify(orderNo,type,amount,iRspRef,payTime,voucherNo);

                    }else{
                        String returnCode = tResult.getReturnCode();
                        String errorMessage = tResult.getErrorMessage();
                    }
                }
                try {
                    if(StringUtil.isNotEmpty(rtnurl)){
                        rtnurl += "&out_trade_no=" + orderNo + "&total_amount=" + (amount==null?"":amount.doubleValue());
                        resp.sendRedirect("abc_redirect.act?rtnurl=" + URLEncoder.encode(rtnurl,PayConfig.charset));
                    }
                } catch (IOException e) {
                    log.error(StringUtil.getLogInfo(null,null,StringUtil.getExceptionStr(e)));
                }
            } catch (TrxException e) {
                log.error(StringUtil.getLogInfo(null,null,StringUtil.getExceptionStr(e)));
            }
            try {
                resp.getWriter().write("SUCCESS");
            } catch (IOException e) {
                log.error(StringUtil.getLogInfo(null,null,StringUtil.getExceptionStr(e)));
            }
        }
        if(null!=type && E.PayType.易宝H5.getCode()/10==type/10){
            yeePayNotifyHandle(type);
        }

    }

    /**
     * 农行跳转中转页面
     * @param rtnurl
     * @return
     */
    @RequestMapping("/abc_redirect.act")
    public String abc_redirect(String rtnurl){
        System.out.println(StringUtil.getLogInfo(null,null,"rtnurl=" + rtnurl));
        //uni_abc_rtn.jsp
        if(StringUtil.isNotEmpty(rtnurl)){
            try {
                rtnurl = URLDecoder.decode(rtnurl,PayConfig.charset);
            } catch (UnsupportedEncodingException e) {
                log.error(StringUtil.getLogInfo(null,null,StringUtil.getExceptionStr(e)));
            }
        }
        addAttr("rtnurl",rtnurl);
        return "pay/uni_abc_rtn";
    }

    /**
     * 校验是否重复支付 发送通知
     * @param orderNo
     * @param payType
     * @param amount
     * @param payno
     * @param payTime
     * @param payerNo
     */
    private void sendNotify(String orderNo, Integer payType,Double amount, String payno, Date payTime, String payerNo){

        //查找已经支付成功的记录
        PayInfo successPayInfo = null;
        List<PayInfo> list = service.query(orderNo,E.UnipayStatus.Success.getCode());
        if(null==list || list.size()<=0){
            list = service.query(orderNo,E.UnipayStatus.Finished.getCode());
        }
        if(null!=list&&list.size()>0){
            boolean flag = false;// 是否存在其他方式的支付成功记录
            for (PayInfo pi:list){
                if(payType != pi.getType()){
                    successPayInfo = pi;
                    flag = true;
                }
            }

            if(flag){ //只有当前通知 不等于 成功支付记录 才进行退款
                //退款
                PayRefundResp refund = pay.refund(payType, successPayInfo.getOrderno(), successPayInfo.getOrderno(), successPayInfo.getAmount()/100D, amount, "重复支付",null);
                if(null!=refund && refund.isSuccess()){
                    List<PayInfo> pays = service.query(orderNo,E.UnipayStatus.Waiting.getCode());
                    for(PayInfo pi:pays){
                        if(payType==pi.getType()){
                            pi.setStatus(E.UnipayStatus.Finished.getCode());
                            Double refundAmount = refund.getRefundAmount();
                            refundAmount = refundAmount==null?0:refundAmount*100;
                            pi.setRefundAmount(refundAmount.intValue());
                            pi.setRefundTime(refund.getRefundTime());
                            service.update(pi);
                            break;
                        }
                    }
                }
            }
        }else{
            //待支付
            List<PayInfo> pays = service.query(orderNo,E.UnipayStatus.Waiting.getCode());
            if(null!=pays && pays.size()>0){
                for(PayInfo payInfo:pays){
                    Double a = 100*amount;
                    if(payType.equals(payInfo.getType())){
                        if(payInfo.getAmount().intValue() == a.intValue()){//实际支付金额与待支付金额相同
                            payInfo.setStatus(E.UnipayStatus.Success.getCode());//设置
                            payInfo.setPayno(payno);
                            payInfo.setPayTime(payTime);
                            payInfo.setPayerno(payerNo);

//                          payInfo.setPayno(bean.getTrade_no());
//                          payInfo.setPayTime(bean.getGmt_payment());
//                          payInfo.setPayerno(bean.getBuyer_id());
                            service.update(payInfo);

                            Notify pn = service.getPayNotify(orderNo, 1);
                            if(null==pn){
                                try {
                                    pn = new PayNotify(orderNo,1,URLDecoder.decode(payInfo.getNotifyUrl(),PayConfig.charset));
                                    pn = service.update(pn);
                                } catch (UnsupportedEncodingException e) {
                                    log.error(StringUtil.getLogInfo(null,null,StringUtil.getExceptionStr(e)));
                                }
                            }
//                          Integer payType = payInfo.getType();
                            Map<String, Object> paramMap = PayUtil.getNotifyParamMap(payInfo, 2, payType, 1, "成功");
                            new NotifyTask(pn, ApplicationContextHelper.getInstance().getBean(PayInfoService.class), paramMap).beginSend();//发送通知
                        }
                    }else{
                        pay.cancleOnlinePay(payInfo.getType(),payInfo.getOrderno());
                        /*payInfo.setStatus(E.UnipayStatus.Close.getCode());//支付关闭
                        service.update(payInfo);*/
                    }
                }
            }

            //已经关闭的 如果payType一致 则说明是取消了支付之后进行付款的 需要进行退款
            pays = service.query(orderNo,E.UnipayStatus.Close.getCode());
            if(null!=pays && pays.size()>0){
                for (PayInfo pi:pays){
                    Double a = 100*amount;
                    if(payType.equals(pi.getType())){
                        pi.setPayno(payno);
                        pi.setPayTime(payTime);
                        pi.setPayerno(payerNo);
                        PayRefundResp refund = pay.refund(payType, pi.getOrderno(), pi.getOrderno(), pi.getAmount()/100D, amount, "无效支付",null);
                        if(null!=refund&&refund.isSuccess()){
                            Double refundAmount = refund.getRefundAmount();
                            Date refundTime = refund.getRefundTime();
                            pi.setRefundTime(refundTime);
                            pi.setRefundAmount(a.intValue());;
                            service.update(pi);
                        }
                    }
                }
            }
        }


    }

    /**
     * 易宝支付通知处理
     * @param payType
     */
    private void yeePayNotifyHandle(Integer payType){
        String orderNo = req.getParameter("r6_Order");
        String r1_code = req.getParameter("r1_Code");
        if(log.isDebugEnabled()){
            log.debug(StringUtil.getLogInfo(null,null,"易宝支付通知处理，payType=" + payType + ",orderNo=" + orderNo + ",r1_code="+r1_code));
        }
        if("1".equals(r1_code)){
            Map<String, String> requestMap = pay.getRequestMap(req, "GBK");
            boolean b = YeepayUtil.verifyHmac(requestMap, YeepayConfig.rtn_pay,requestMap.get("hmac"));
            if(b){ //校验来源
                Double r3_amt = Double.parseDouble(requestMap.get("r3_Amt"));
                String payno = requestMap.get("r2_TrxId"); //易宝交易流水号
                String bankOrderId = requestMap.get("ro_BankOrderId"); //银行订单号
                Date payTime = null;
                try {
                    payTime = new SimpleDateFormat("yyyyMMddHHmmss").parse(requestMap.get("rp_PayDate"));
                } catch (ParseException e) {
                    payTime = new Date();
                }
                PayInfo payInfo = pay.queryPayInfo(payType, orderNo);
                sendNotify(orderNo,payType,r3_amt,payno,payTime,bankOrderId);
            }
        }
        try {
            resp.getWriter().write("SUCCESS");
        } catch (IOException e) {
            log.error(StringUtil.getLogInfo(null,null,StringUtil.getExceptionStr(e)));
        }
        return;
    }


    /**
     * 回跳页面
     */
    @RequestMapping("/test_rtn.act")
    public String test_rtn(String orderId,Double amount,Integer code,String msg,Integer payType){
        log.debug(StringUtil.getLogInfo(null,null,"orderId=" + orderId));
        try {
            log.debug(StringUtil.getLogInfo(null,null,"amount=" + amount.doubleValue()));

        }catch (Exception e){

        }
        log.debug(StringUtil.getLogInfo(null,null,"code=" + code));
        log.debug(StringUtil.getLogInfo(null,null,"msg=" + msg));
        try {
            log.debug(StringUtil.getLogInfo(null,null,"payType=" + payType.intValue()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "test_rtn";
    }


    /**
     * 通知接收
     * @param type
     * @param url
     */
    @RequestMapping("/test_ntf.act")
    @ResponseBody
    public void test_ntf(Integer type,String url){
        log.debug(StringUtil.getLogInfo(null,null,"type=" + type));
        log.debug(StringUtil.getLogInfo(null,null,"url=" + url));
        try {
            resp.getWriter().write("success");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试
     */
    @RequestMapping("/test.act")
    @ResponseBody
    public void test(String rtnurl) throws IOException {
        System.out.println(StringUtil.getLogInfo(null,null,"rtnurl=" + rtnurl));
        resp.sendRedirect("abc_redirect.act?rtnurl=" + URLEncoder.encode(rtnurl,PayConfig.charset));
    }


    /**
     * 校验实际支付金额是否等于应该支付金额
     * @param py
     * @param payedAmount
     * @return
     */
    private boolean allAmountPayed(PayInfo py,Double payedAmount){
        boolean result = false;
        if(null!=py && payedAmount!=null&&payedAmount>0){
            payedAmount = payedAmount*100;
            result = py.getAmount().intValue()==payedAmount.intValue();
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
            log.error(StringUtil.getExceptionStr(e));
        }
        return resultStr.toString();
    }

}
