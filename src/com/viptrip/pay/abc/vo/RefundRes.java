package com.viptrip.pay.abc.vo;

/**
 * Created by selfwhisper on 0003 2017/11/3.
 */
public class RefundRes {
    private String OrderNo;
    private String NewOrderNo;
    private String TrxAmount;
    private String BatchNo;
    private String VoucherNo;
    private String HostDate;
    private String HostTime;
    private String iRspRef;

    public String getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(String orderNo) {
        OrderNo = orderNo;
    }

    public String getNewOrderNo() {
        return NewOrderNo;
    }

    public void setNewOrderNo(String newOrderNo) {
        NewOrderNo = newOrderNo;
    }

    public String getTrxAmount() {
        return TrxAmount;
    }

    public void setTrxAmount(String trxAmount) {
        TrxAmount = trxAmount;
    }

    public String getBatchNo() {
        return BatchNo;
    }

    public void setBatchNo(String batchNo) {
        BatchNo = batchNo;
    }

    public String getVoucherNo() {
        return VoucherNo;
    }

    public void setVoucherNo(String voucherNo) {
        VoucherNo = voucherNo;
    }

    public String getHostDate() {
        return HostDate;
    }

    public void setHostDate(String hostDate) {
        HostDate = hostDate;
    }

    public String getHostTime() {
        return HostTime;
    }

    public void setHostTime(String hostTime) {
        HostTime = hostTime;
    }

    public String getiRspRef() {
        return iRspRef;
    }

    public void setiRspRef(String iRspRef) {
        this.iRspRef = iRspRef;
    }
}
