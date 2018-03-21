package com.viptrip.common.entity;
// default package

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * TTmcAreaInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="T_TMC_AREA_INFO")

public class TTmcAreaInfo  implements java.io.Serializable {


    // Fields    

     private Long areaId;
     private String cnName;
     private String cnShortname;
     private String enName;
     private String enShortname;
     private String alias;
     private String pyName;
     private String abName;
     private Boolean lv;
     private Integer parentId;
     private String createUser;
     private Timestamp createDatetime;
     private String modifyUser;
     private Timestamp modifyDatetime;
     private String deleteFlag;
     private String dictItemValue;


    // Constructors

    /** default constructor */
    public TTmcAreaInfo() {
    }

    
    /** full constructor */
    public TTmcAreaInfo(String cnName, String cnShortname, String enName, String enShortname, String alias, String pyName, String abName, Boolean lv, Integer parentId, String createUser, Timestamp createDatetime, String modifyUser, Timestamp modifyDatetime, String deleteFlag, String dictItemValue) {
        this.cnName = cnName;
        this.cnShortname = cnShortname;
        this.enName = enName;
        this.enShortname = enShortname;
        this.alias = alias;
        this.pyName = pyName;
        this.abName = abName;
        this.lv = lv;
        this.parentId = parentId;
        this.createUser = createUser;
        this.createDatetime = createDatetime;
        this.modifyUser = modifyUser;
        this.modifyDatetime = modifyDatetime;
        this.deleteFlag = deleteFlag;
        this.dictItemValue = dictItemValue;
    }

   
    // Property accessors
    @Id @GeneratedValue
    
    @Column(name="AREA_ID", unique=true, nullable=false, precision=10, scale=0)

    public Long getAreaId() {
        return this.areaId;
    }
    
    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }
    
    @Column(name="CN_NAME", length=64)

    public String getCnName() {
        return this.cnName;
    }
    
    public void setCnName(String cnName) {
        this.cnName = cnName;
    }
    
    @Column(name="CN_SHORTNAME", length=20)

    public String getCnShortname() {
        return this.cnShortname;
    }
    
    public void setCnShortname(String cnShortname) {
        this.cnShortname = cnShortname;
    }
    
    @Column(name="EN_NAME", length=60)

    public String getEnName() {
        return this.enName;
    }
    
    public void setEnName(String enName) {
        this.enName = enName;
    }
    
    @Column(name="EN_SHORTNAME", length=20)

    public String getEnShortname() {
        return this.enShortname;
    }
    
    public void setEnShortname(String enShortname) {
        this.enShortname = enShortname;
    }
    
    @Column(name="ALIAS", length=64)

    public String getAlias() {
        return this.alias;
    }
    
    public void setAlias(String alias) {
        this.alias = alias;
    }
    
    @Column(name="PY_NAME", length=100)

    public String getPyName() {
        return this.pyName;
    }
    
    public void setPyName(String pyName) {
        this.pyName = pyName;
    }
    
    @Column(name="AB_NAME", length=20)

    public String getAbName() {
        return this.abName;
    }
    
    public void setAbName(String abName) {
        this.abName = abName;
    }
    
    @Column(name="LV", precision=1, scale=0)

    public Boolean getLv() {
        return this.lv;
    }
    
    public void setLv(Boolean lv) {
        this.lv = lv;
    }
    
    @Column(name="PARENT_ID", precision=8, scale=0)

    public Integer getParentId() {
        return this.parentId;
    }
    
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
    
    @Column(name="CREATE_USER", length=40)

    public String getCreateUser() {
        return this.createUser;
    }
    
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    
    @Column(name="CREATE_DATETIME", length=7)

    public Timestamp getCreateDatetime() {
        return this.createDatetime;
    }
    
    public void setCreateDatetime(Timestamp createDatetime) {
        this.createDatetime = createDatetime;
    }
    
    @Column(name="MODIFY_USER", length=40)

    public String getModifyUser() {
        return this.modifyUser;
    }
    
    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }
    
    @Column(name="MODIFY_DATETIME", length=7)

    public Timestamp getModifyDatetime() {
        return this.modifyDatetime;
    }
    
    public void setModifyDatetime(Timestamp modifyDatetime) {
        this.modifyDatetime = modifyDatetime;
    }
    
    @Column(name="DELETE_FLAG", length=1)

    public String getDeleteFlag() {
        return this.deleteFlag;
    }
    
    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
    
    @Column(name="DICT_ITEM_VALUE", length=10)

    public String getDictItemValue() {
        return this.dictItemValue;
    }
    
    public void setDictItemValue(String dictItemValue) {
        this.dictItemValue = dictItemValue;
    }
   








}