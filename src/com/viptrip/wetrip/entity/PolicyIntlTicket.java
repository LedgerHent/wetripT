package com.viptrip.wetrip.entity;
// default package

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * PolicyIntlTicket entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="POLICY_INTL_TICKET"
    ,schema="VIPTRIP1024"
)

public class PolicyIntlTicket  implements java.io.Serializable {


    // Fields    

     private PolicyIntlTicketId id;
     private Integer policytype;
     private Integer overproofbook;
     private Integer overproofaudit;
     private Integer overproofshow;
     private Integer state;


    // Constructors

    /** default constructor */
    public PolicyIntlTicket() {
    }

	/** minimal constructor */
    public PolicyIntlTicket(PolicyIntlTicketId id) {
        this.id = id;
    }
    
    /** full constructor */
    public PolicyIntlTicket(PolicyIntlTicketId id, Integer policytype, Integer overproofbook, Integer overproofaudit, Integer overproofshow, Integer state) {
        this.id = id;
        this.policytype = policytype;
        this.overproofbook = overproofbook;
        this.overproofaudit = overproofaudit;
        this.overproofshow = overproofshow;
        this.state = state;
    }

   
    // Property accessors
    @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="orgid", column=@Column(name="ORGID", nullable=false, precision=12, scale=0) ), 
        @AttributeOverride(name="ruleid", column=@Column(name="RULEID", nullable=false, precision=12, scale=0) ) } )

    public PolicyIntlTicketId getId() {
        return this.id;
    }
    
    public void setId(PolicyIntlTicketId id) {
        this.id = id;
    }
    
    @Column(name="POLICYTYPE", precision=6, scale=0)

    public Integer getPolicytype() {
        return this.policytype;
    }
    
    public void setPolicytype(Integer policytype) {
        this.policytype = policytype;
    }
    
    @Column(name="OVERPROOFBOOK", precision=6, scale=0)

    public Integer getOverproofbook() {
        return this.overproofbook;
    }
    
    public void setOverproofbook(Integer overproofbook) {
        this.overproofbook = overproofbook;
    }
    
    @Column(name="OVERPROOFAUDIT", precision=6, scale=0)

    public Integer getOverproofaudit() {
        return this.overproofaudit;
    }
    
    public void setOverproofaudit(Integer overproofaudit) {
        this.overproofaudit = overproofaudit;
    }
    
    @Column(name="OVERPROOFSHOW", precision=6, scale=0)

    public Integer getOverproofshow() {
        return this.overproofshow;
    }
    
    public void setOverproofshow(Integer overproofshow) {
        this.overproofshow = overproofshow;
    }
    
    @Column(name="STATE", precision=6, scale=0)

    public Integer getState() {
        return this.state;
    }
    
    public void setState(Integer state) {
        this.state = state;
    }
   








}