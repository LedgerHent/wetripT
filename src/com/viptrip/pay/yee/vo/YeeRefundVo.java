package com.viptrip.pay.yee.vo;

/**
 * Created by selfwhisper on 0007 2018/2/7.
 */
public class YeeRefundVo {
    /*
    r0_Cmd=RefundOrd
    r1_Code=1
    r2_TrxId=517261290115464I
    r4_Order=1234
    r3_Amt=0.01
    rf_fee=0.0
    r4_Cur=RMB
    hmac=da57108bd840d3042016891597f14afc
    hmac_safe=09c41dd8224da328d63e8a937d3bc9d4
     */

    private String r0_Cmd;
    private Integer r1_Code;
    private String r2_TrxId;
    private String r4_Order;
    private Double r3_Amt;
    private Double rf_fee;
    private String r4_Cur;
    private String hmac;
    private String hmac_safe;

    public String getR0_Cmd() {
        return r0_Cmd;
    }

    public void setR0_Cmd(String r0_Cmd) {
        this.r0_Cmd = r0_Cmd;
    }

    public Integer getR1_Code() {
        return r1_Code;
    }

    public void setR1_Code(Integer r1_Code) {
        this.r1_Code = r1_Code;
    }

    public String getR2_TrxId() {
        return r2_TrxId;
    }

    public void setR2_TrxId(String r2_TrxId) {
        this.r2_TrxId = r2_TrxId;
    }

    public String getR4_Order() {
        return r4_Order;
    }

    public void setR4_Order(String r4_Order) {
        this.r4_Order = r4_Order;
    }

    public Double getR3_Amt() {
        return r3_Amt;
    }

    public void setR3_Amt(Double r3_Amt) {
        this.r3_Amt = r3_Amt;
    }

    public Double getRf_fee() {
        return rf_fee;
    }

    public void setRf_fee(Double rf_fee) {
        this.rf_fee = rf_fee;
    }

    public String getR4_Cur() {
        return r4_Cur;
    }

    public void setR4_Cur(String r4_Cur) {
        this.r4_Cur = r4_Cur;
    }

    public String getHmac() {
        return hmac;
    }

    public void setHmac(String hmac) {
        this.hmac = hmac;
    }

    public String getHmac_safe() {
        return hmac_safe;
    }

    public void setHmac_safe(String hmac_safe) {
        this.hmac_safe = hmac_safe;
    }
}
