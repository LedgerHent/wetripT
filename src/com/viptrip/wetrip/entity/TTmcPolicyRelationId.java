package com.viptrip.wetrip.entity;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * TTmcPolicyRelationId entity. @author MyEclipse Persistence Tools
 */
@Embeddable

public class TTmcPolicyRelationId  implements java.io.Serializable {


    // Fields    

     private Long policyId;
     private Long flowid;
     private Integer businessType;


    // Constructors

    /** default constructor */
    public TTmcPolicyRelationId() {
    }

    
    /** full constructor */
    public TTmcPolicyRelationId(Long policyId, Long flowid, Integer businessType) {
        this.policyId = policyId;
        this.flowid = flowid;
        this.businessType = businessType;
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

    @Column(name="BUSINESS_TYPE", nullable=false, precision=5, scale=0)

    public Integer getBusinessType() {
        return this.businessType;
    }
    
    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof TTmcPolicyRelationId) ) return false;
		 TTmcPolicyRelationId castOther = ( TTmcPolicyRelationId ) other; 
         
		 return ( (this.getPolicyId()==castOther.getPolicyId()) || ( this.getPolicyId()!=null && castOther.getPolicyId()!=null && this.getPolicyId().equals(castOther.getPolicyId()) ) )
 && ( (this.getFlowid()==castOther.getFlowid()) || ( this.getFlowid()!=null && castOther.getFlowid()!=null && this.getFlowid().equals(castOther.getFlowid()) ) )
 && ( (this.getBusinessType()==castOther.getBusinessType()) || ( this.getBusinessType()!=null && castOther.getBusinessType()!=null && this.getBusinessType().equals(castOther.getBusinessType()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getPolicyId() == null ? 0 : this.getPolicyId().hashCode() );
         result = 37 * result + ( getFlowid() == null ? 0 : this.getFlowid().hashCode() );
         result = 37 * result + ( getBusinessType() == null ? 0 : this.getBusinessType().hashCode() );
         return result;
   }   





}