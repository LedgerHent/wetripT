package com.viptrip.autoissue.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * TAutoissueSwitch entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_AUTOISSUE_SWITCH")
public class TAutoissueSwitch implements java.io.Serializable {

    // Fields

    /**
     * 
     */
    private static final long serialVersionUID = 3428080782835970316L;

    private Long switchId;

    private String switchName;

    private String switchDesc;

    private String air2chars;

    private String orgid;

    private String orgname;

    private String status;

    // Constructors

    /** default constructor */
    public TAutoissueSwitch() {
    }

    /** full constructor */
    public TAutoissueSwitch(String switchName, String switchDesc, String air2chars, String orgid, String orgname,
            String status) {
        this.switchName = switchName;
        this.switchDesc = switchDesc;
        this.air2chars = air2chars;
        this.orgid = orgid;
        this.orgname = orgname;
        this.status = status;
    }
    

    // Property accessors
    @Id
    @Column(name = "SWITCH_ID", unique = true, nullable = false, precision = 22, scale = 0)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_AUTOISSUESWITCH")
    @SequenceGenerator(name="SEQ_AUTOISSUESWITCH",allocationSize=1,initialValue=1, sequenceName="SEQ_AUTOISSUESWITCH")
    
    public Long getSwitchId() {
        return this.switchId;
    }

    public void setSwitchId(Long switchId) {
        this.switchId = switchId;
    }

    @Column(name = "SWITCH_NAME")
    public String getSwitchName() {
        return this.switchName;
    }

    public void setSwitchName(String switchName) {
        this.switchName = switchName;
    }

    @Column(name = "SWITCH_DESC")
    public String getSwitchDesc() {
        return this.switchDesc;
    }

    public void setSwitchDesc(String switchDesc) {
        this.switchDesc = switchDesc;
    }

    @Column(name = "AIR2CHARS")
    public String getAir2chars() {
        return this.air2chars;
    }

    public void setAir2chars(String air2chars) {
        this.air2chars = air2chars;
    }

    @Column(name = "ORGID")
    public String getOrgid() {
        return this.orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    @Column(name = "ORGNAME")
    public String getOrgname() {
        return this.orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    @Column(name = "STATUS", length = 1)
    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}