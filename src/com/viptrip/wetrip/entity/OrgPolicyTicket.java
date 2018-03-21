package com.viptrip.wetrip.entity;
// default package

import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name="ORG_POLICY_TICKET")
public class OrgPolicyTicket  implements java.io.Serializable {



     private OrgPolicyTicketId id;
     private Integer policycontrol;
     private Integer policyType;
     private Integer orgruletype;
     private String orgrules;
     private Integer overproofbook;
     private Integer overproofaudit;
     private Integer overproofshow;
     private Integer overproofdefault;
     private Date validdatefrom;
     private Date validdateto;
     private String operator;
     private Date updatetime;
     private Integer state;

    /** full constructor */
    public OrgPolicyTicket(OrgPolicyTicketId id, Integer policycontrol, Integer policytype, Integer orgruletype, String orgrules, Integer overproofbook, Integer overproofaudit, Integer overproofshow, Integer overproofdefault, Date validdatefrom, Date validdateto, String operator, Date updatetime, Integer state) {
        this.id = id;
        this.policycontrol = policycontrol;
        this.policyType = policytype;
        this.orgruletype = orgruletype;
        this.orgrules = orgrules;
        this.overproofbook = overproofbook;
        this.overproofaudit = overproofaudit;
        this.overproofshow = overproofshow;
        this.overproofdefault = overproofdefault;
        this.validdatefrom = validdatefrom;
        this.validdateto = validdateto;
        this.operator = operator;
        this.updatetime = updatetime;
        this.state = state;
    }

   
    
    public OrgPolicyTicket(){
    }
    // Property accessors
    @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="orgid", column=@Column(name="ORGID", nullable=false, precision=12, scale=0) ), 
        @AttributeOverride(name="ruleid", column=@Column(name="RULEID", nullable=false, precision=6, scale=0) ) } )

    public OrgPolicyTicketId getId() {
        return this.id;
    }
    
    public void setId(OrgPolicyTicketId id) {
        this.id = id;
    }
    
    @Column(name="POLICYCONTROL", precision=6, scale=0)

    public Integer getPolicycontrol() {
        return this.policycontrol;
    }
    
    public void setPolicycontrol(Integer policycontrol) {
        this.policycontrol = policycontrol;
    }
    
    @Column(name="POLICYTYPE", precision=6, scale=0)

    public Integer getPolicyType() {
        return this.policyType;
    }
    
    public void setPolicyType(Integer policyType) {
        this.policyType = policyType;
    }
    
    @Column(name="ORGRULETYPE", precision=6, scale=0)

    public Integer getOrgruletype() {
        return this.orgruletype;
    }
    
    public void setOrgruletype(Integer orgruletype) {
        this.orgruletype = orgruletype;
    }
    
    @Column(name="ORGRULES", length=4000)

    public String getOrgrules() {
        return this.orgrules;
    }
    
    public void setOrgrules(String orgrules) {
        this.orgrules = orgrules;
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
    
    @Column(name="OVERPROOFDEFAULT", precision=6, scale=0)

    public Integer getOverproofdefault() {
        return this.overproofdefault;
    }
    
    public void setOverproofdefault(Integer overproofdefault) {
        this.overproofdefault = overproofdefault;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="VALIDDATEFROM", length=7)

    public Date getValiddatefrom() {
        return this.validdatefrom;
    }
    
    public void setValiddatefrom(Date validdatefrom) {
        this.validdatefrom = validdatefrom;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="VALIDDATETO", length=7)

    public Date getValiddateto() {
        return this.validdateto;
    }
    
    public void setValiddateto(Date validdateto) {
        this.validdateto = validdateto;
    }
    
    @Column(name="OPERATOR", length=50)

    public String getOperator() {
        return this.operator;
    }
    
    public void setOperator(String operator) {
        this.operator = operator;
    }
    @Temporal(TemporalType.TIMESTAMP) 
    @Column(name="UPDATETIME", length=20)
    public Date getUpdatetime() {
        return this.updatetime;
    }
    
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
    
    @Column(name="STATE", precision=6, scale=0)

    public Integer getState() {
        return this.state;
    }
    
    public void setState(Integer state) {
        this.state = state;
    }

















}