package com.viptrip.wetrip.entity;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * PolicyIntlTicketRuleId entity. @author MyEclipse Persistence Tools
 */
@Embeddable

public class PolicyIntlTicketRuleId  implements java.io.Serializable {


    // Fields    

     private Long ruleid;
     private Integer flowid;


    // Constructors

    /** default constructor */
    public PolicyIntlTicketRuleId() {
    }

    
    /** full constructor */
    public PolicyIntlTicketRuleId(Long ruleid, Integer flowid) {
        this.ruleid = ruleid;
        this.flowid = flowid;
    }

   
    // Property accessors

    @Column(name="RULEID", nullable=false, precision=12, scale=0)

    public Long getRuleid() {
        return this.ruleid;
    }
    
    public void setRuleid(Long ruleid) {
        this.ruleid = ruleid;
    }

    @Column(name="FLOWID", nullable=false, precision=6, scale=0)

    public Integer getFlowid() {
        return this.flowid;
    }
    
    public void setFlowid(Integer flowid) {
        this.flowid = flowid;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof PolicyIntlTicketRuleId) ) return false;
		 PolicyIntlTicketRuleId castOther = ( PolicyIntlTicketRuleId ) other; 
         
		 return ( (this.getRuleid()==castOther.getRuleid()) || ( this.getRuleid()!=null && castOther.getRuleid()!=null && this.getRuleid().equals(castOther.getRuleid()) ) )
 && ( (this.getFlowid()==castOther.getFlowid()) || ( this.getFlowid()!=null && castOther.getFlowid()!=null && this.getFlowid().equals(castOther.getFlowid()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getRuleid() == null ? 0 : this.getRuleid().hashCode() );
         result = 37 * result + ( getFlowid() == null ? 0 : this.getFlowid().hashCode() );
         return result;
   }   





}