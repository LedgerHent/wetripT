package com.viptrip.intlAirticket.model.flightModels;
/**
 * 国际订单列表航司对象
 * @author jetty
 *
 */
public class IntlOrderListCarrier {
    
    private String code;//   二字码
    
    private String name;//   名称
    
    private String shortName;//  简称
    

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
    
    
}
