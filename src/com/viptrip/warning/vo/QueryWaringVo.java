package com.viptrip.warning.vo;

/**
 * Created by selfwhisper on 0031 2017/8/31.
 */
public class QueryWaringVo {

    private Long orgid;
    private String orgname;
    private Integer type;
    private Double warningValue;

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

    public Double getWarningValue() {
        return warningValue;
    }

    public void setWarningValue(Double warningValue) {
        this.warningValue = warningValue;
    }
}
