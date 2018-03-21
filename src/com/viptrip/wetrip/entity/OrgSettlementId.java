package com.viptrip.wetrip.entity;
// default package

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;


/**
 * OrgSettlementId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
@MappedSuperclass
public class OrgSettlementId  implements java.io.Serializable {


    // Fields    

     private Long orgid;
     private Long sid;
     private Long flowid;


    // Constructors

    /** default constructor */
    public OrgSettlementId() {
    }

    
    /** full constructor */
    public OrgSettlementId(Long orgid, Long sid, Long flowid) {
        this.orgid = orgid;
        this.sid = sid;
        this.flowid = flowid;
    }

   
    // Property accessors

    @Column(name="ORGID", nullable=false, precision=22, scale=0)

    public Long getOrgid() {
        return this.orgid;
    }
    
    public void setOrgid(Long orgid) {
        this.orgid = orgid;
    }

    @Column(name="SID", nullable=false, precision=22, scale=0)

    public Long getSid() {
        return this.sid;
    }
    
    public void setSid(Long sid) {
        this.sid = sid;
    }

    @Column(name="FLOWID", nullable=false, precision=22, scale=0)

    public Long getFlowid() {
        return this.flowid;
    }
    
    public void setFlowid(Long flowid) {
        this.flowid = flowid;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof OrgSettlementId) ) return false;
		 OrgSettlementId castOther = ( OrgSettlementId ) other; 
         
		 return ( (this.getOrgid()==castOther.getOrgid()) || ( this.getOrgid()!=null && castOther.getOrgid()!=null && this.getOrgid().equals(castOther.getOrgid()) ) )
 && ( (this.getSid()==castOther.getSid()) || ( this.getSid()!=null && castOther.getSid()!=null && this.getSid().equals(castOther.getSid()) ) )
 && ( (this.getFlowid()==castOther.getFlowid()) || ( this.getFlowid()!=null && castOther.getFlowid()!=null && this.getFlowid().equals(castOther.getFlowid()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getOrgid() == null ? 0 : this.getOrgid().hashCode() );
         result = 37 * result + ( getSid() == null ? 0 : this.getSid().hashCode() );
         result = 37 * result + ( getFlowid() == null ? 0 : this.getFlowid().hashCode() );
         return result;
   }   





}