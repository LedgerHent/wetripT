package com.viptrip.wetrip.entity;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * TTmcPolicyDetailId entity. @author MyEclipse Persistence Tools
 */
@Embeddable

public class TTmcPolicyDetailId  implements java.io.Serializable {


    // Fields    

     private Long policyId;
     private Long flowid;


    // Constructors

    /** default constructor */
    public TTmcPolicyDetailId() {
    }

    
    /** full constructor */
    public TTmcPolicyDetailId(Long policyId, Long flowid) {
        this.policyId = policyId;
        this.flowid = flowid;
    }

   
    // Property accessors

    @Column(name="POLICY_ID", nullable=false, precision=12, scale=0)

    public Long getPolicyId() {
        return this.policyId;
    }
    
    public void setPolicyId(Long policyId) {
        this.policyId = policyId;
    }

    @Column(name="FLOWID", nullable=false, precision=12, scale=0)

    public Long getFlowid() {
        return this.flowid;
    }
    
    public void setFlowid(Long flowid) {
        this.flowid = flowid;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof TTmcPolicyDetailId) ) return false;
		 TTmcPolicyDetailId castOther = ( TTmcPolicyDetailId ) other; 
         
		 return ( (this.getPolicyId()==castOther.getPolicyId()) || ( this.getPolicyId()!=null && castOther.getPolicyId()!=null && this.getPolicyId().equals(castOther.getPolicyId()) ) )
 && ( (this.getFlowid()==castOther.getFlowid()) || ( this.getFlowid()!=null && castOther.getFlowid()!=null && this.getFlowid().equals(castOther.getFlowid()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getPolicyId() == null ? 0 : this.getPolicyId().hashCode() );
         result = 37 * result + ( getFlowid() == null ? 0 : this.getFlowid().hashCode() );
         return result;
   }   





}