package com.viptrip.wetrip.entity;
// default package

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * PolicyIntlTicketRule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="POLICY_INTL_TICKET_RULE"
    ,schema="VIPTRIP1024"
)

public class PolicyIntlTicketRule  implements java.io.Serializable {


    // Fields    

     private PolicyIntlTicketRuleId id;
     private Integer ruletype;
     private String rules;
     private Double upperlimit;
     private Double lowerlimit;


    // Constructors

    /** default constructor */
    public PolicyIntlTicketRule() {
    }

	/** minimal constructor */
    public PolicyIntlTicketRule(PolicyIntlTicketRuleId id) {
        this.id = id;
    }
    
    /** full constructor */
    public PolicyIntlTicketRule(PolicyIntlTicketRuleId id, Integer ruletype, String rules, Double upperlimit, Double lowerlimit) {
        this.id = id;
        this.ruletype = ruletype;
        this.rules = rules;
        this.upperlimit = upperlimit;
        this.lowerlimit = lowerlimit;
    }

   
    // Property accessors
    @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="ruleid", column=@Column(name="RULEID", nullable=false, precision=12, scale=0) ), 
        @AttributeOverride(name="flowid", column=@Column(name="FLOWID", nullable=false, precision=6, scale=0) ) } )

    public PolicyIntlTicketRuleId getId() {
        return this.id;
    }
    
    public void setId(PolicyIntlTicketRuleId id) {
        this.id = id;
    }
    
    @Column(name="RULETYPE", precision=6, scale=0)

    public Integer getRuletype() {
        return this.ruletype;
    }
    
    public void setRuletype(Integer ruletype) {
        this.ruletype = ruletype;
    }
    
    @Column(name="RULES", length=4000)

    public String getRules() {
        return this.rules;
    }
    
    public void setRules(String rules) {
        this.rules = rules;
    }
    
    @Column(name="UPPERLIMIT", precision=8)

    public Double getUpperlimit() {
        return this.upperlimit;
    }
    
    public void setUpperlimit(Double upperlimit) {
        this.upperlimit = upperlimit;
    }
    
    @Column(name="LOWERLIMIT", precision=8)

    public Double getLowerlimit() {
        return this.lowerlimit;
    }
    
    public void setLowerlimit(Double lowerlimit) {
        this.lowerlimit = lowerlimit;
    }
   








}