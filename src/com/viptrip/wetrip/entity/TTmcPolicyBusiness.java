package com.viptrip.wetrip.entity;
// default package

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * TTmcPolicyBusiness entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="T_TMC_POLICY_BUSINESS")
public class TTmcPolicyBusiness  implements java.io.Serializable {
    // Fields    
     private TTmcPolicyBusinessId id;
     private Integer policycontrol;
     private Integer overproofdefault;
     private Integer overproofaudit;
     private Integer overproofbook;
     private Integer overproofshow;
    // Constructors

    /** default constructor */
    public TTmcPolicyBusiness() {
    }

	/** minimal constructor */
    public TTmcPolicyBusiness(TTmcPolicyBusinessId id) {
        this.id = id;
    }
    
    /** full constructor */
    public TTmcPolicyBusiness(TTmcPolicyBusinessId id, Integer policycontrol, Integer overproofdefault, Integer overproofaudit, Integer overproofbook, Integer overproofshow) {
        this.id = id;
        this.policycontrol = policycontrol;
        this.overproofdefault = overproofdefault;
        this.overproofaudit = overproofaudit;
        this.overproofbook = overproofbook;
        this.overproofshow = overproofshow;
    }

   
    // Property accessors
    @EmbeddedId
    @AttributeOverrides( {
        @AttributeOverride(name="orgid", column=@Column(name="ORGID", nullable=false, precision=12, scale=0) ), 
        @AttributeOverride(name="bussinesstype", column=@Column(name="BUSSINESSTYPE", nullable=false, precision=5, scale=0) ) } )
    public TTmcPolicyBusinessId getId() {
        return this.id;
    }
    
    public void setId(TTmcPolicyBusinessId id) {
        this.id = id;
    }
    
    @Column(name="POLICYCONTROL", precision=5, scale=0)
    public Integer getPolicycontrol() {
        return this.policycontrol;
    }
    
    public void setPolicycontrol(Integer policycontrol) {
        this.policycontrol = policycontrol;
    }
    
    @Column(name="OVERPROOFDEFAULT", precision=5, scale=0)

    public Integer getOverproofdefault() {
        return this.overproofdefault;
    }
    
    public void setOverproofdefault(Integer overproofdefault) {
        this.overproofdefault = overproofdefault;
    }
    
    @Column(name="OVERPROOFAUDIT", precision=5, scale=0)

    public Integer getOverproofaudit() {
        return this.overproofaudit;
    }
    
    public void setOverproofaudit(Integer overproofaudit) {
        this.overproofaudit = overproofaudit;
    }
    
    @Column(name="OVERPROOFBOOK", precision=5, scale=0)

    public Integer getOverproofbook() {
        return this.overproofbook;
    }
    
    public void setOverproofbook(Integer overproofbook) {
        this.overproofbook = overproofbook;
    }
    
    @Column(name="OVERPROOFSHOW", precision=5, scale=0)

    public Integer getOverproofshow() {
        return this.overproofshow;
    }
    
    public void setOverproofshow(Integer overproofshow) {
        this.overproofshow = overproofshow;
    }
   








}