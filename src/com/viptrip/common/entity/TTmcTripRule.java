package com.viptrip.common.entity;
// default package

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * TTmcTripRule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="T_TMC_TRIP_RULE"
)

public class TTmcTripRule  implements java.io.Serializable {


    // Fields    

     private Long dbid;
     private Integer ruleType;
     private Long ruleTypeId;
     private Integer orgRuleType;
     private String orgRule;
     private Long createUserId;
     private String createUserName;
     private Timestamp createTime;
     private Long lastUpdUserId;
     private String lastUpdUserName;
     private Timestamp lastUpdDate;
     private Long enterpId;
     private String enterpName;
     private Integer state;


    // Constructors

    /** default constructor */
    public TTmcTripRule() {
    }

    
    /** full constructor */
    public TTmcTripRule(Integer ruleType, Long ruleTypeId, Integer orgRuleType, String orgRule, Long createUserId, String createUserName, Timestamp createTime, Long lastUpdUserId, String lastUpdUserName, Timestamp lastUpdDate, Long enterpId, String enterpName, Integer state) {
        this.ruleType = ruleType;
        this.ruleTypeId = ruleTypeId;
        this.orgRuleType = orgRuleType;
        this.orgRule = orgRule;
        this.createUserId = createUserId;
        this.createUserName = createUserName;
        this.createTime = createTime;
        this.lastUpdUserId = lastUpdUserId;
        this.lastUpdUserName = lastUpdUserName;
        this.lastUpdDate = lastUpdDate;
        this.enterpId = enterpId;
        this.enterpName = enterpName;
        this.state = state;
    }

   
    // Property accessors
    @Id @GeneratedValue
    
    @Column(name="DBID", unique=true, nullable=false, precision=12, scale=0)

    public Long getDbid() {
        return this.dbid;
    }
    
    public void setDbid(Long dbid) {
        this.dbid = dbid;
    }
    
    @Column(name="RULE_TYPE", precision=5, scale=0)

    public Integer getRuleType() {
        return this.ruleType;
    }
    
    public void setRuleType(Integer ruleType) {
        this.ruleType = ruleType;
    }
    
    @Column(name="RULE_TYPE_ID", precision=12, scale=0)

    public Long getRuleTypeId() {
        return this.ruleTypeId;
    }
    
    public void setRuleTypeId(Long ruleTypeId) {
        this.ruleTypeId = ruleTypeId;
    }
    
    @Column(name="ORG_RULE_TYPE", precision=5, scale=0)

    public Integer getOrgRuleType() {
        return this.orgRuleType;
    }
    
    public void setOrgRuleType(Integer orgRuleType) {
        this.orgRuleType = orgRuleType;
    }
    
    @Column(name="ORG_RULE", length=4000)

    public String getOrgRule() {
        return this.orgRule;
    }
    
    public void setOrgRule(String orgRule) {
        this.orgRule = orgRule;
    }
    
    @Column(name="CREATE_USER_ID", precision=12, scale=0)

    public Long getCreateUserId() {
        return this.createUserId;
    }
    
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }
    
    @Column(name="CREATE_USER_NAME", length=32)

    public String getCreateUserName() {
        return this.createUserName;
    }
    
    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }
    
    @Column(name="CREATE_TIME", length=7)

    public Timestamp getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="LAST_UPD_USER_ID", precision=12, scale=0)

    public Long getLastUpdUserId() {
        return this.lastUpdUserId;
    }
    
    public void setLastUpdUserId(Long lastUpdUserId) {
        this.lastUpdUserId = lastUpdUserId;
    }
    
    @Column(name="LAST_UPD_USER_NAME", length=32)

    public String getLastUpdUserName() {
        return this.lastUpdUserName;
    }
    
    public void setLastUpdUserName(String lastUpdUserName) {
        this.lastUpdUserName = lastUpdUserName;
    }
    
    @Column(name="LAST_UPD_DATE", length=7)

    public Timestamp getLastUpdDate() {
        return this.lastUpdDate;
    }
    
    public void setLastUpdDate(Timestamp lastUpdDate) {
        this.lastUpdDate = lastUpdDate;
    }
    
    @Column(name="ENTERP_ID", precision=12, scale=0)

    public Long getEnterpId() {
        return this.enterpId;
    }
    
    public void setEnterpId(Long enterpId) {
        this.enterpId = enterpId;
    }
    
    @Column(name="ENTERP_NAME", length=200)

    public String getEnterpName() {
        return this.enterpName;
    }
    
    public void setEnterpName(String enterpName) {
        this.enterpName = enterpName;
    }
    
    @Column(name="STATE", precision=5, scale=0)

    public Integer getState() {
        return this.state;
    }
    
    public void setState(Integer state) {
        this.state = state;
    }
   








}