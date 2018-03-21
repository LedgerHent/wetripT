package com.viptrip.wetrip.entity;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class OrgPolicyTicketId  implements java.io.Serializable {


    // Fields    

     private Long orgid;
     private Integer ruleid;


    // Constructors

     public OrgPolicyTicketId() {
     }
    
    /** full constructor */
    public OrgPolicyTicketId(Long orgid, Integer ruleid) {
        this.orgid = orgid;
        this.ruleid = ruleid;
    }

   
    // Property accessors

    @Column(name="ORGID", nullable=false, precision=12, scale=0)

    public Long getOrgid() {
        return this.orgid;
    }
    
    public void setOrgid(Long orgid) {
        this.orgid = orgid;
    }

    @Column(name="RULEID", nullable=false, precision=12, scale=0)

    public Integer getRuleid() {
        return this.ruleid;
    }
    
    public void setRuleid(Integer ruleid) {
        this.ruleid = ruleid;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof OrgPolicyTicketId) ) return false;
		 OrgPolicyTicketId castOther = ( OrgPolicyTicketId ) other; 
         
		 return ( (this.getOrgid()==castOther.getOrgid()) || ( this.getOrgid()!=null && castOther.getOrgid()!=null && this.getOrgid().equals(castOther.getOrgid()) ) )
 && ( (this.getRuleid()==castOther.getRuleid()) || ( this.getRuleid()!=null && castOther.getRuleid()!=null && this.getRuleid().equals(castOther.getRuleid()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getOrgid() == null ? 0 : this.getOrgid().hashCode() );
         result = 37 * result + ( getRuleid() == null ? 0 : this.getRuleid().hashCode() );
         return result;
   }   





}