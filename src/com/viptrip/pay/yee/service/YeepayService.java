package com.viptrip.pay.yee.service;

import com.github.kevinsawicki.http.HttpRequest;
import com.viptrip.pay.abc.vo.Resp;
import com.viptrip.pay.entity.PayInfo;
import com.viptrip.pay.service.IPayInfoService;
import com.viptrip.pay.yee.util.YeepayConfig;
import com.viptrip.pay.yee.util.YeepayUtil;
import com.viptrip.pay.yee.vo.YeeRefundVo;
import com.viptrip.util.JSON;
import com.viptrip.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by selfwhisper on 0006 2018/2/6.
 */
@Service("yeepay_unipay")
public class YeepayService {
    @Resource(name = "unipayInfoService")
    private IPayInfoService payInfoService;

    private static final Logger log = LoggerFactory.getLogger(YeepayService.class);
    /**
     *
     * @param orderId
     * @param price
     * @param title
     * @param returnURL
     * @param notifyURL
     * @return
     */
    public String pagePay(String orderId,Double price,String title,String returnURL,String notifyURL){
        String result = null;
        if(StringUtil.isNotEmpty(orderId) && null!=price && price>0 && StringUtil.isNotEmpty(title)){
            Map<String, String> hm = new HashMap<String, String>();
            hm.put("p0_Cmd", "Buy");// //业务类型
            hm.put("p1_MerId",YeepayConfig.YEE_MERID);
            hm.put("p2_Order", orderId);// 商户订单号
            hm.put("p3_Amt", new DecimalFormat("0.##").format(price));// 消费金额
            hm.put("p4_Cur", "CNY");// 交易币种
            hm.put("p5_Pid", title);// 商品名称
            hm.put("p8_Url", returnURL);// 接收支付结果地址
            hm.put("p9_SAF", "0");
            hm.put("pb_ServerNotifyUrl", notifyURL); //异步通知
            hm.put("pd_FrpId", "EPOS-NET");// 银行编码
            result = YeepayUtil.getRequestURL(YeepayConfig.YEE_URL_PAY, hm, YeepayConfig.req_pay);
        }else{
            result = "参数不全";
        }
        return result;
    }

    /**
     *
     * @param orderId
     * @param refundNo
     * @param totalAmount
     * @param refundAmount
     * @param refundReason
     * @return
     */
    public Resp<YeeRefundVo> refund(String orderId, String refundNo, Double totalAmount, Double refundAmount, String refundReason){
        Resp<YeeRefundVo> result = new Resp<>();
        if(StringUtil.isNotEmpty(orderId)&&StringUtil.isNotEmpty(refundNo)&&null!=refundAmount&&refundAmount>0&&StringUtil.isNotEmpty(refundReason)){
            Map<String, String> hm = new HashMap<String, String>();
            hm.put("p0_Cmd", "RefundOrd");//业务类型
            hm.put("p1_MerId",YeepayConfig.YEE_MERID);
            hm.put("p2_Order", refundNo);// 退款请求编号
            PayInfo payInfo = payInfoService.getSuccessPayInfo(orderId);
            if(null!=payInfo){
                hm.put("pb_TrxId", payInfo.getPayno()); //易宝交易流水号
            }
            hm.put("p3_Amt", new DecimalFormat("0.##").format(refundAmount)); //退款金额
            hm.put("p4_Cur", "CNY"); //交易币种
            hm.put("p5_Desc", refundReason); //退款说明
            String url = YeepayUtil.getRequestURL(YeepayConfig.YEE_URL_REFUND, hm, YeepayConfig.req_refund);
            log.debug("在线退款url="+url);
            HttpRequest request = HttpRequest.post(url);
            int code = request.code();
            if(200==code){
                Map<String,String> map = new HashMap<>();
                BufferedReader reader = request.bufferedReader();
                String str = null;
                try {
                    while((str = reader.readLine()) != null){
                        if(str.indexOf("=")>0){
                            String[] split = str.split("=");
                            if(split.length==1){
                                map.put(split[0],"");
                            }else if(split.length==2){
                                map.put(split[0],split[1]);
                            }
                        }
                    }
                    log.debug("r=" + JSON.get().notEscapeHTML(true).toJson(map));
                    YeeRefundVo refundVo = JSON.get().fromJson(JSON.get().toJson(map), YeeRefundVo.class);
                    if(null!=refundVo&&null!=refundVo.getR1_Code()&&1==refundVo.getR1_Code()){
                        result.success(refundVo);
                    }else{
                        result.fail("在线退款失败，易宝返回值为：" + JSON.get().notEscapeHTML(true).toJson(map));
                    }
                } catch (Exception e) {
                    log.error(StringUtil.getExceptionStr(e));
                    result.fail("在线退款失败,发生异常");
                }
            }else{
                result.fail("在线退款失败,网络代码为" + code);
            }
        }else{
            result.fail("在线退款失败，参数不完整");
        }

        return result;
    }



}
