package com.viptrip.wetrip.entity;
// default package

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * PolicyTicketRule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="POLICY_TICKET_RULE" )
public class PolicyTicketRule  implements java.io.Serializable {


    // Fields    

     private PolicyTicketRuleId id;
     private Integer ruletype;
     private String rules;
     private Double upperLimit;
     private Double lowerLimit;


    // Constructors

    /** default constructor */
    public PolicyTicketRule() {
    }

	/** minimal constructor */
    public PolicyTicketRule(PolicyTicketRuleId id) {
        this.id = id;
    }
    
    /** full constructor */
    public PolicyTicketRule(PolicyTicketRuleId id, Integer ruletype, String rules, Double upperlimit, Double lowerlimit) {
        this.id = id;
        this.ruletype = ruletype;
        this.rules = rules;
        this.upperLimit = upperlimit;
        this.lowerLimit = lowerlimit;
    }

   
    // Property accessors
    @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="ruleid", column=@Column(name="RULEID", nullable=false, precision=6, scale=0) ), 
        @AttributeOverride(name="flowid", column=@Column(name="FLOWID", nullable=false, precision=6, scale=0) ) } )

    public PolicyTicketRuleId getId() {
        return this.id;
    }
    
    public void setId(PolicyTicketRuleId id) {
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

    public Double getUpperLimit() {
        return this.upperLimit;
    }
    
    public void setUpperLimit(Double upperlimit) {
        this.upperLimit = upperlimit;
    }
    
    @Column(name="LOWERLIMIT", precision=8)

    public Double getLowerLimit() {
        return this.lowerLimit;
    }
    
    public void setLowerLimit(Double lowerlimit) {
        this.lowerLimit = lowerlimit;
    }
   








}