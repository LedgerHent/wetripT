package com.viptrip.wetrip.model.flight;

import java.io.Serializable;

public class ReqData_GetFlightList_PassengerMessage implements Serializable {
   
    //selectorguser+=tacuser.getIdcard() +"_"+tacuser.getUserid()+"_"+tacuser.getOrgid()+"_"+org_.getOrgid().toString()+"_"+tacuser.getUsername();

    /**
     * 
     */
    private static final long serialVersionUID = -7025471829260736713L;

    public Long userid; // 用户表ID

    public String username; // 人员姓名

    public Long orgid; // 机构ID
    
    public Long deptid; //部门ID
    
    public Integer type;//证件类型 1-二代身份证|2-护照|3-海员证|4-回乡证|5-军官证|6-港澳通行证|7-台胞证|99-其他
    
    public String idcard;//证件号码

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getOrgid() {
        return orgid;
    }

    public void setOrgid(Long orgid) {
        this.orgid = orgid;
    }

    public Long getDeptid() {
        return deptid;
    }

    public void setDeptid(Long deptid) {
        this.deptid = deptid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

}
