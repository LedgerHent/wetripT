package com.viptrip.wetrip.entity;
// default package

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * TTmcPolicyDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="T_TMC_POLICY_DETAIL"
)
public class TTmcPolicyDetail  implements java.io.Serializable {


    // Fields    

     private TTmcPolicyDetailId id;
     private Integer businessType;
     private Integer bookingRemindType;
     private Integer displayRemindType;
     private Integer auditRemindType;
     private String policyDetailValue;


    // Constructors

    /** default constructor */
    public TTmcPolicyDetail() {
    }

	/** minimal constructor */
    public TTmcPolicyDetail(TTmcPolicyDetailId id) {
        this.id = id;
    }
    
    /** full constructor */
    public TTmcPolicyDetail(TTmcPolicyDetailId id, Integer businessType, Integer bookingRemindType, Integer displayRemindType, Integer auditRemindType, String policyDetailValue) {
        this.id = id;
        this.businessType = businessType;
        this.bookingRemindType = bookingRemindType;
        this.displayRemindType = displayRemindType;
        this.auditRemindType = auditRemindType;
        this.policyDetailValue = policyDetailValue;
    }

   
    // Property accessors
    @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="policyId", column=@Column(name="POLICY_ID", nullable=false, precision=12, scale=0) ), 
        @AttributeOverride(name="flowid", column=@Column(name="FLOWID", nullable=false, precision=12, scale=0) ) } )

    public TTmcPolicyDetailId getId() {
        return this.id;
    }
    
    public void setId(TTmcPolicyDetailId id) {
        this.id = id;
    }
    
    @Column(name="BUSINESS_TYPE", precision=5, scale=0)

    public Integer getBusinessType() {
        return this.businessType;
    }
    
    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }
    
    @Column(name="BOOKING_REMIND_TYPE", precision=5, scale=0)

    public Integer getBookingRemindType() {
        return this.bookingRemindType;
    }
    
    public void setBookingRemindType(Integer bookingRemindType) {
        this.bookingRemindType = bookingRemindType;
    }
    
    @Column(name="DISPLAY_REMIND_TYPE", precision=5, scale=0)

    public Integer getDisplayRemindType() {
        return this.displayRemindType;
    }
    
    public void setDisplayRemindType(Integer displayRemindType) {
        this.displayRemindType = displayRemindType;
    }
    
    @Column(name="AUDIT_REMIND_TYPE", precision=5, scale=0)

    public Integer getAuditRemindType() {
        return this.auditRemindType;
    }
    
    public void setAuditRemindType(Integer auditRemindType) {
        this.auditRemindType = auditRemindType;
    }
    
    @Column(name="POLICY_DETAIL_VALUE", length=0)

    public String getPolicyDetailValue() {
        return this.policyDetailValue;
    }
    
    public void setPolicyDetailValue(String policyDetailValue) {
        this.policyDetailValue = policyDetailValue;
    }
   








}