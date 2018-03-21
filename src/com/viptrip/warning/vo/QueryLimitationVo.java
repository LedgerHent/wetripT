package com.viptrip.warning.vo;

/**
 * Created by selfwhisper on 0031 2017/8/31.
 */
public class QueryLimitationVo {

    private Long orgid;
    private String orgname;
    private Integer type;
    private Double credit;
    private Double limitationValue;

    public Long getOrgid() {
        return orgid;
    }

    public void setOrgid(Long orgid) {
        this.orgid = orgid;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getLimitationValue() {
        return limitationValue;
    }

    public void setLimitationValue(Double limitationValue) {
        this.limitationValue = limitationValue;
    }
    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }
}
