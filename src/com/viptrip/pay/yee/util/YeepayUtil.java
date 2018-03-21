package com.viptrip.pay.yee.util;

/*import com.epos.facade.DigestUtilFacade;
import com.epos.util.Configuration;*/
import com.epos.facade.DigestUtilFacade;
import com.viptrip.util.StringUtil;
import jodd.util.URLDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by selfwhisper on 0006 2018/2/6.
 */
public class YeepayUtil {

    private static final Logger log = LoggerFactory.getLogger(YeepayUtil.class);
    //p0_Cmd,p1_MerId,p2_Order,p3_Amt,p4_Cur,p5_Pid,p6_Pcat,p7_Pdesc,p8_Url,p9_SAF,pa_MP,pd_FrpId,pr_NeedResponse,prisk_TerminalCode,prisk_Param

    public static String getHmac(Map<String,String> params,String[] seq){
        String result = null;
        if(null!=params && params.size()>0){
            String hmacKey = YeepayConfig.YEE_KEY;
            StringBuffer str = new StringBuffer();
            for (int i = 0; i < seq.length; i++) {
                String s = params.get(seq[i]);
                if(null==s){
                    s="";
                }
                str.append(s);
            }
            result = DigestUtil.hmacSign(str.toString(), hmacKey);
        }
        return result;
    }

    public static String getHmacSafe(Map<String,String> params,String[] seq){
        String result = null;
        if(null!=params && params.size()>0){
            String hmacKey = YeepayConfig.YEE_KEY;
            StringBuffer hmac_safe = new StringBuffer();
            for (int i = 0; i < seq.length; i++) {
                String s = params.get(seq[i]);
                if(StringUtil.isNotEmpty(s)){
                    hmac_safe.append(s).append("#");
                }
            }
            if(hmac_safe.length()>0){
                hmac_safe = hmac_safe.deleteCharAt(hmac_safe.length()-1);
            }
            result = DigestUtil.hmacSign(hmac_safe.toString(), hmacKey);
        }
        return result;
    }

    /*public static Map<String, String> makeHmac(Map params) throws Exception {
        String hmacKey = YeepayConfig.YEE_KEY;
        String merchantID = Configuration.getInstance().getValue("merchantID");
        String cmd = (String)params.get("p0_Cmd");
        if(StringUtil.isEmpty(cmd)) {
            throw new Exception("p0_Cmd is null");
        } else {
            String requestHmacOrder = Configuration.getInstance().getValue(cmd + "_RequestHmacOrder");
            if(params.get("p1_MerId") == null) {
                params.put("p1_MerId", merchantID);
            }

            if(params == null) {
                throw new Exception("params is null");
            } else if(!StringUtil.isEmpty(hmacKey) && !StringUtil.isEmpty(merchantID)) {
                if(StringUtil.isEmpty(requestHmacOrder)) {
                    throw new Exception("requestHmacOrder is null");
                } else {
                    params = DigestUtilFacade.addHmac(requestHmacOrder.split(","), params, hmacKey);
                    return params;
                }
            } else {
                throw new Exception("keyValue or eposReqUrl or merchantID is null");
            }
        }
    }
*/
    public static String parseQueryString(Map params, String charSet) {
        if(params != null && params.keySet().size() != 0) {
            StringBuffer queryString = new StringBuffer(2000);
            Iterator i = params.keySet().iterator();

            while(i.hasNext()) {
                String key = String.valueOf(i.next());
                Object obj = params.get(key);
                String value = "";
                if(obj != null) {
                    value = obj.toString();
                }

                try {
                    value = URLEncoder.encode(value, charSet);
                } catch (UnsupportedEncodingException var8) {
                    log.info("encode url error: " + var8.getMessage());
                }

                queryString.append(key);
                queryString.append("=");
                queryString.append(value);
                queryString.append("&");
            }

            String result = queryString.toString();
            if(result.endsWith("&")) {
                result = result.substring(0, result.length() - 1);
            }

            return result;
        } else {
            return "";
        }
    }

    public static String getRequestURL(String baseUrl,Map<String,String> params,String[] encodeAttrs){
        String result = null;
        if(StringUtil.isNotEmpty(baseUrl) && null!=params && params.size()>0 && null!= encodeAttrs && encodeAttrs.length>0){
            String hmac = YeepayUtil.getHmac(params,encodeAttrs);
            String hmac_safe=  YeepayUtil.getHmacSafe(params,encodeAttrs);
            params.put("hmac",hmac);
            params.put("hmac_safe",hmac_safe);
            String param = parseQueryString(params, "gbk");
            result = YeepayConfig.D_YEE_URL_PAY  + "?" + param;
        }
        return result;
    }

    public static boolean verifyHmac(Map<String,String> params,String[] arr, String hmacFromYeepay){
        String keyValue = YeepayConfig.YEE_KEY;
        StringBuffer sourceData	= new StringBuffer();
        for(int i = 0; i < arr.length; i++) {
            sourceData.append(params.get(arr[i])==null?"":params.get(arr[i]));
        }
        String localHmac=DigestUtil.hmacSign(sourceData.toString(),keyValue);
        return (localHmac.equals(hmacFromYeepay) ? true : false);
    }


    public static void main(String[] args) {
        String url="http://61.51.80.138:58899/wetrip/unipay/ntf.act?type=42&url=http%3A%2F%2F61.51.80.138%3A58899%2Fwetrip%2Funipay%2Ftest_ntf.act&p1_MerId=10012414518&r0_Cmd=Buy&r1_Code=1&r2_TrxId=915293204701292I&r3_Amt=0.01&r4_Cur=RMB&r5_Pid=%D2%D7%B1%A6%D6%A7%B8%B6%CF%C2%B5%A5%B2%E2%CA%D4&r6_Order=1517968193992&r7_Uid=&r8_MP=&r9_BType=1&ru_Trxtime=20180207095038&ro_BankOrderId=NCPAY20180207095036319991&rb_BankId=CCBCREDIT-NET&rp_PayDate=20180207095038&rq_CardNo=&rq_SourceFee=0.0&rq_TargetFee=0.0&hmac=b87f28e0d7ce6b5784b437f2eb61b0f4&hmac_safe=e0f496a86613ec7b3a7fba5d64aa2354";

        System.out.println(URLDecoder.decode(url));
    }
}
