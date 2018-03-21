package com.viptrip.wetrip.entity;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * TTmcPolicyBusinessId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class TTmcPolicyBusinessId  implements java.io.Serializable {


    // Fields    

     private Long orgid;
     private Integer bussinesstype;


    // Constructors

    /** default constructor */
    public TTmcPolicyBusinessId() {
    }

    
    /** full constructor */
    public TTmcPolicyBusinessId(Long orgid, Integer bussinesstype) {
        this.orgid = orgid;
        this.bussinesstype = bussinesstype;
    }

   
    // Property accessors

    @Column(name="ORGID", nullable=false, precision=12, scale=0)

    public Long getOrgid() {
        return this.orgid;
    }
    
    public void setOrgid(Long orgid) {
        this.orgid = orgid;
    }

    @Column(name="BUSSINESSTYPE", nullable=false, precision=5, scale=0)

    public Integer getBussinesstype() {
        return this.bussinesstype;
    }
    
    public void setBussinesstype(Integer bussinesstype) {
        this.bussinesstype = bussinesstype;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof TTmcPolicyBusinessId) ) return false;
		 TTmcPolicyBusinessId castOther = ( TTmcPolicyBusinessId ) other; 
         
		 return ( (this.getOrgid()==castOther.getOrgid()) || ( this.getOrgid()!=null && castOther.getOrgid()!=null && this.getOrgid().equals(castOther.getOrgid()) ) )
 && ( (this.getBussinesstype()==castOther.getBussinesstype()) || ( this.getBussinesstype()!=null && castOther.getBussinesstype()!=null && this.getBussinesstype().equals(castOther.getBussinesstype()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getOrgid() == null ? 0 : this.getOrgid().hashCode() );
         result = 37 * result + ( getBussinesstype() == null ? 0 : this.getBussinesstype().hashCode() );
         return result;
   }   





}