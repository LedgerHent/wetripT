package com.viptrip.wetrip.entity;
import java.math.BigDecimal;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;


/**
 * AbstractOrgSettlement entity provides the base persistence definition of the OrgSettlement entity. @author MyEclipse Persistence Tools
 */
//@MappedSuperclass
@Entity
@Table(name = "ORG_SETTLEMENT")
public  class OrgSettlement  implements java.io.Serializable {


    // Fields    

     private OrgSettlementId id;
     private Long ruletype;


  
    // Property accessors
    @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="orgid", column=@Column(name="ORGID", nullable=false, precision=22, scale=0) ), 
        @AttributeOverride(name="sid", column=@Column(name="SID", nullable=false, precision=22, scale=0) ), 
        @AttributeOverride(name="flowid", column=@Column(name="FLOWID", nullable=false, precision=22, scale=0) ) } )

    public OrgSettlementId getId() {
        return this.id;
    }
    
    public void setId(OrgSettlementId id) {
        this.id = id;
    }
    
    @Column(name="RULETYPE", precision=22, scale=0)

    public Long getRuletype() {
        return this.ruletype;
    }
    
    public void setRuletype(Long ruletype) {
        this.ruletype = ruletype;
    }

	@Override
	public String toString() {
		return "OrgSettlement [id=" + id + ", ruletype=" + ruletype
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
   








}