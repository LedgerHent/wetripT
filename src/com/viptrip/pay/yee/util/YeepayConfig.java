package com.viptrip.pay.yee.util;

import com.viptrip.resource.Const;
import com.viptrip.util.PropertiesUtils;
import com.viptrip.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by selfwhisper on 0006 2018/2/6.
 */
public class YeepayConfig {
    private static Logger log = LoggerFactory.getLogger(YeepayConfig.class);


    public static final String YEE_MERID;
    public static final String YEE_KEY;
    public static final String YEE_URL_PAY;
    public static final String YEE_URL_QUERY;
    public static final String YEE_URL_REFUND;
    public static final String YEE_URL_REFUNDQUERY;
    public static final String YEE_URL_ORDERCANCLE;

    public static final String D_YEE_MERID = "10012414518";
    public static final String D_YEE_KEY = "12TiI13X6v214JA87hlY5G4m18I5t42078FTjnx58DMGAd33E5v60682e199";
    public static final String D_YEE_URL_PAY = "https://www.yeepay.com/app-merchant-proxy/node";
    public static final String D_YEE_URL_QUERY = "https://cha.yeepay.com/app-merchant-proxy/command";
    public static final String D_YEE_URL_REFUND = "https://cha.yeepay.com/app-merchant-proxy/command";
    public static final String D_YEE_URL_REFUNDQUERY = "https://www.yeepay.com/app-merchant-proxy/node";
    public static final String D_YEE_URL_ORDERCANCLE = "https://cha.yeepay.com/app-merchant-proxy/command";


    public static final String[] req_pay = new String[] {"p0_Cmd", "p1_MerId", "p2_Order","p3_Amt", "p4_Cur", "p5_Pid", "p6_Pcat", "p7_Pdesc",
            "p8_Url", "p9_SAF", "pa_MP","pb_ServerNotifyUrl", "pd_FrpId", "pm_Period", "pn_Unit", "pr_NeedResponse",
            "pt_UserName", "pt_PostalCode", "pt_Address", "pt_TeleNo", "pt_Mobile", "pt_Email","pt_LeaveMessage"};
    public static final String[] req_refund 	= {"p0_Cmd", "p1_MerId", "p2_Order", "pb_TrxId", "p3_Amt", "p4_Cur", "p5_Desc"};

    public static final String[] rtn_pay = new String[]{"p1_MerId", "r0_Cmd", "r1_Code", "r2_TrxId", "r3_Amt", "r4_Cur", "r5_Pid", "r6_Order", "r7_Uid", "r8_MP", "r9_BType" };
    public static final String[] rtn_refund 	= {"p0_Cmd", "p1_MerId", "p2_Order", "pb_TrxId", "p3_Amt", "p4_Cur", "p5_Desc"};




    static {
        String YEE_MERID1 = null;
        String YEE_KEY1 = null;
        String YEE_URL_PAY1 = null;
        String YEE_URL_QUERY1 = null;
        String YEE_URL_REFUND1 = null;
        String YEE_URL_REFUNDQUERY1 = null;
        String YEE_URL_ORDERCANCLE1 = null;

        try {
            YEE_MERID1 = PropertiesUtils.getProperty(Const.PRO_YEE_MERID, Const.FILE_PAY_CONFIG);
            YEE_KEY1 = PropertiesUtils.getProperty(Const.PRO_YEE_KEY, Const.FILE_PAY_CONFIG);
            YEE_URL_PAY1 = PropertiesUtils.getProperty(Const.PRO_YEE_URL_PAY, Const.FILE_PAY_CONFIG);
            YEE_URL_QUERY1 = PropertiesUtils.getProperty(Const.PRO_YEE_URL_QUERY, Const.FILE_PAY_CONFIG);
            YEE_URL_REFUND1 = PropertiesUtils.getProperty(Const.PRO_YEE_URL_REFUND, Const.FILE_PAY_CONFIG);
            YEE_URL_REFUNDQUERY1 = PropertiesUtils.getProperty(Const.PRO_YEE_URL_REFUNDQUERY, Const.FILE_PAY_CONFIG);
            YEE_URL_ORDERCANCLE1 = PropertiesUtils.getProperty(Const.PRO_YEE_URL_ORDERCANCLE, Const.FILE_PAY_CONFIG);
        }catch (Exception e){
            YEE_MERID1 = D_YEE_MERID;
            YEE_KEY1 = D_YEE_KEY;
            YEE_URL_PAY1 = D_YEE_URL_PAY;
            YEE_URL_QUERY1 = D_YEE_URL_QUERY;
            YEE_URL_REFUND1 = D_YEE_URL_REFUND;
            YEE_URL_REFUNDQUERY1 = D_YEE_URL_REFUNDQUERY;
            YEE_URL_ORDERCANCLE1 = D_YEE_URL_ORDERCANCLE;
            log.info("加载易宝支付配置文件失败\r\n" + StringUtil.getExceptionStr(e));
            log.info("加载易宝宝支付配置文件失败,使用默认配置");
        }
        YEE_MERID = YEE_MERID1;
        YEE_KEY = YEE_KEY1;
        YEE_URL_PAY = YEE_URL_PAY1;
        YEE_URL_QUERY = YEE_URL_QUERY1;
        YEE_URL_REFUND = YEE_URL_REFUND1;
        YEE_URL_REFUNDQUERY = YEE_URL_REFUNDQUERY1;
        YEE_URL_ORDERCANCLE = YEE_URL_ORDERCANCLE1;
    }


}
