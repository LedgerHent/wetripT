package com.viptrip.wetrip.entity;
// default package

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * TTmcPolicy entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="T_TMC_POLICY"
)

public class TTmcPolicy  implements java.io.Serializable {


    // Fields    

     private Long policyId;
     private String policyName;
     private Long createUserId;
     private String createUserName;
     private Date createTime;
     private Long lastUpdUserId;
     private String lastUpdUserName;
     private Date lastUpdDate;
     private Long enterpId;
     private String enterpName;
     private Date validDateFrom;
     private Date validDateTo;
     private Integer state;


    // Constructors

    /** default constructor */
    public TTmcPolicy() {
    }

	/** minimal constructor */
    public TTmcPolicy(String policyName) {
        this.policyName = policyName;
    }
    
    /** full constructor */
    public TTmcPolicy(String policyName, Long createUserId, String createUserName, Date createTime, Long lastUpdUserId, String lastUpdUserName, Date lastUpdDate, Long enterpId, String enterpName, Date validDateFrom, Date validDateTo, Integer state) {
        this.policyName = policyName;
        this.createUserId = createUserId;
        this.createUserName = createUserName;
        this.createTime = createTime;
        this.lastUpdUserId = lastUpdUserId;
        this.lastUpdUserName = lastUpdUserName;
        this.lastUpdDate = lastUpdDate;
        this.enterpId = enterpId;
        this.enterpName = enterpName;
        this.validDateFrom = validDateFrom;
        this.validDateTo = validDateTo;
        this.state = state;
    }

   
    // Property accessors
    @Id @GeneratedValue
    @Column(name="POLICY_ID", unique=true, nullable=false, precision=12, scale=0)

    public Long getPolicyId() {
        return this.policyId;
    }
    
    public void setPolicyId(Long policyId) {
        this.policyId = policyId;
    }
    
    @Column(name="POLICY_NAME", nullable=false, length=200)

    public String getPolicyName() {
        return this.policyName;
    }
    
    public void setPolicyName(String policyName) {
        this.policyName = policyName;
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

    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
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
    
    @Column(name="LAST_UPD_DATE", length=11)

    public Date getLastUpdDate() {
        return this.lastUpdDate;
    }
    
    public void setLastUpdDate(Date lastUpdDate) {
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
    
    @Column(name="VALID_DATE_FROM", length=7)

    public Date getValidDateFrom() {
        return this.validDateFrom;
    }
    
    public void setValidDateFrom(Date validDateFrom) {
        this.validDateFrom = validDateFrom;
    }
    
    @Column(name="VALID_DATE_TO", length=7)

    public Date getValidDateTo() {
        return this.validDateTo;
    }
    
    public void setValidDateTo(Date validDateTo) {
        this.validDateTo = validDateTo;
    }
    
    @Column(name="STATE", precision=5, scale=0)

    public Integer getState() {
        return this.state;
    }
    
    public void setState(Integer state) {
        this.state = state;
    }
   








}