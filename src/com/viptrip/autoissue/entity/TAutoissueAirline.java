package com.viptrip.autoissue.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TAutoissueAirline entity. @author MyEclipse Persistence Tools
 * 按航司设置自动出票大开关
 */
@Entity
@Table(name = "T_AUTOISSUE_AIRLINE")
public class TAutoissueAirline implements java.io.Serializable {

    // Fields

    /**
     * 
     */
    private static final long serialVersionUID = 1396222815299653674L;

    private Long autoissueairlineid;

    private String air2char;
    
    private String airName;

    private String status;

    private Date updatetime;

    // Constructors

    /** default constructor */
    public TAutoissueAirline() {
    }

    /** minimal constructor */
    public TAutoissueAirline(String air2char, String status) {
        this.air2char = air2char;
        this.status = status;
    }

    /** full constructor */
    public TAutoissueAirline(String air2char, String status, Date updatetime) {
        this.air2char = air2char;
        this.status = status;
        this.updatetime = updatetime;
    }

    // Property accessors
    @Id
    @Column(name = "AUTOISSUEAIRLINEID", unique = true, nullable = false, precision = 22, scale = 0)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_AUTOISSUEAIRLINE")
    @SequenceGenerator(name="SEQ_AUTOISSUEAIRLINE",allocationSize=1,initialValue=1, sequenceName="SEQ_AUTOISSUEAIRLINE")
    public Long getAutoissueairlineid() {
        return this.autoissueairlineid;
    }

    public void setAutoissueairlineid(Long autoissueairlineid) {
        this.autoissueairlineid = autoissueairlineid;
    }

    @Column(name = "AIR2CHAR", nullable = false, length = 2)
    public String getAir2char() {
        return this.air2char;
    }

    public void setAir2char(String air2char) {
        this.air2char = air2char;
    }

    @Column(name = "AIRNAME", nullable = true, length = 50)
    public String getAirName() {
        return airName;
    }

    public void setAirName(String airName) {
        this.airName = airName;
    }

    @Column(name = "STATUS", nullable = false, length = 1)
    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATETIME", length = 7)
    public Date getUpdatetime() {
        return this.updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

}