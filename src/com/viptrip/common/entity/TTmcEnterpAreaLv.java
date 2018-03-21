package com.viptrip.common.entity;
// default package

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * TTmcEnterpAreaLv entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="T_TMC_ENTERP_AREA_LV")

public class TTmcEnterpAreaLv  implements java.io.Serializable {


    // Fields    

     private String dbid;
     private String enterpId;
     private String enterpName;
     private String lineLv;
     private String cityList;
     private String createUserId;
     private String createUserName;
     private Timestamp createTime;
     private String lastUpdUserId;
     private String lastUpdUserName;
     private Timestamp lastUpdDate;
     private String delFlag;
     private String lineLvName;


    // Constructors

    /** default constructor */
    public TTmcEnterpAreaLv() {
    }

    
    /** full constructor */
    public TTmcEnterpAreaLv(String enterpId, String enterpName, String lineLv, String cityList, String createUserId, String createUserName, Timestamp createTime, String lastUpdUserId, String lastUpdUserName, Timestamp lastUpdDate, String delFlag, String lineLvName) {
        this.enterpId = enterpId;
        this.enterpName = enterpName;
        this.lineLv = lineLv;
        this.cityList = cityList;
        this.createUserId = createUserId;
        this.createUserName = createUserName;
        this.createTime = createTime;
        this.lastUpdUserId = lastUpdUserId;
        this.lastUpdUserName = lastUpdUserName;
        this.lastUpdDate = lastUpdDate;
        this.delFlag = delFlag;
        this.lineLvName = lineLvName;
    }

   
    // Property accessors
    @Id @GeneratedValue
    
    @Column(name="DBID", unique=true, nullable=false, length=32)

    public String getDbid() {
        return this.dbid;
    }
    
    public void setDbid(String dbid) {
        this.dbid = dbid;
    }
    
    @Column(name="ENTERP_ID", length=32)

    public String getEnterpId() {
        return this.enterpId;
    }
    
    public void setEnterpId(String enterpId) {
        this.enterpId = enterpId;
    }
    
    @Column(name="ENTERP_NAME", length=256)

    public String getEnterpName() {
        return this.enterpName;
    }
    
    public void setEnterpName(String enterpName) {
        this.enterpName = enterpName;
    }
    
    @Column(name="LINE_LV", length=16)

    public String getLineLv() {
        return this.lineLv;
    }
    
    public void setLineLv(String lineLv) {
        this.lineLv = lineLv;
    }
    
    @Column(name="CITY_LIST", length=4000)

    public String getCityList() {
        return this.cityList;
    }
    
    public void setCityList(String cityList) {
        this.cityList = cityList;
    }
    
    @Column(name="CREATE_USER_ID", length=32)

    public String getCreateUserId() {
        return this.createUserId;
    }
    
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }
    
    @Column(name="CREATE_USER_NAME", length=32)

    public String getCreateUserName() {
        return this.createUserName;
    }
    
    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }
    
    @Column(name="CREATE_TIME", length=11)

    public Timestamp getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="LAST_UPD_USER_ID", length=32)

    public String getLastUpdUserId() {
        return this.lastUpdUserId;
    }
    
    public void setLastUpdUserId(String lastUpdUserId) {
        this.lastUpdUserId = lastUpdUserId;
    }
    
    @Column(name="LAST_UPD_USER_NAME", length=32)

    public String getLastUpdUserName() {
        return this.lastUpdUserName;
    }
    
    public void setLastUpdUserName(String lastUpdUserName) {
        this.lastUpdUserName = lastUpdUserName;
    }
    
    @Column(name="LAST_UPD_DATE", length=11)

    public Timestamp getLastUpdDate() {
        return this.lastUpdDate;
    }
    
    public void setLastUpdDate(Timestamp lastUpdDate) {
        this.lastUpdDate = lastUpdDate;
    }
    
    @Column(name="DEL_FLAG", length=1)

    public String getDelFlag() {
        return this.delFlag;
    }
    
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
    
    @Column(name="LINE_LV_NAME", length=20)

    public String getLineLvName() {
        return this.lineLvName;
    }
    
    public void setLineLvName(String lineLvName) {
        this.lineLvName = lineLvName;
    }
   








}