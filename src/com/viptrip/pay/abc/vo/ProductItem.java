package com.viptrip.pay.abc.vo;

/**
 * Created by selfwhisper on 0007 2017/11/7.
 */
public class ProductItem {

//out.println("SubMerName      = [" + (String)val.get("SubMerName") + "],");
//out.println("SubMerId      = [" + (String)val.get("SubMerId") + "],");
//out.println("SubMerMCC      = [" + (String)val.get("SubMerMCC") + "],");
//out.println("SubMerchantRemarks      = [" + (String)val.get("SubMerchantRemarks") + "],");
//out.println("ProductID      = [" + (String)val.get("ProductID") + "],");
//out.println("ProductName      = [" + (String)val.get("ProductName") + "],");
//out.println("UnitPrice      = [" + (String)val.get("UnitPrice") + "],");
//out.println("Qty      = [" + (String)val.get("Qty") + "],");
//out.println("ProductRemarks      = [" + (String)val.get("ProductRemarks") + "],");

    private String SubMerName;
    private String SubMerId;
    private String SubMerMCC;
    private String SubMerchantRemarks;
    private String ProductID;
    private String ProductName;
    private String UnitPrice;
    private String Qty;
    private String ProductRemarks;



    public String getSubMerName() {
        return SubMerName;
    }

    public void setSubMerName(String subMerName) {
        SubMerName = subMerName;
    }

    public String getSubMerId() {
        return SubMerId;
    }

    public void setSubMerId(String subMerId) {
        SubMerId = subMerId;
    }

    public String getSubMerMCC() {
        return SubMerMCC;
    }

    public void setSubMerMCC(String subMerMCC) {
        SubMerMCC = subMerMCC;
    }

    public String getSubMerchantRemarks() {
        return SubMerchantRemarks;
    }

    public void setSubMerchantRemarks(String subMerchantRemarks) {
        SubMerchantRemarks = subMerchantRemarks;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        UnitPrice = unitPrice;
    }

    public String getQty() {
        return Qty;
    }

    public void setQty(String qty) {
        Qty = qty;
    }

    public String getProductRemarks() {
        return ProductRemarks;
    }

    public void setProductRemarks(String productRemarks) {
        ProductRemarks = productRemarks;
    }
}
