package com.viptrip.wetrip.entity;
// default package

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * TTmcPolicyRelation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="T_TMC_POLICY_RELATION"
)

public class TTmcPolicyRelation  implements java.io.Serializable {


    // Fields    

     private TTmcPolicyRelationId id;
     private Long key;
     private Integer state;
     private String extend;
     private String tableName;
     private String columnName;
     private String extendColumnNames;


    // Constructors

    /** default constructor */
    public TTmcPolicyRelation() {
    }

	/** minimal constructor */
    public TTmcPolicyRelation(TTmcPolicyRelationId id) {
        this.id = id;
    }
    
    /** full constructor */
    public TTmcPolicyRelation(TTmcPolicyRelationId id, Long key, Integer state, String extend, String tableName, String columnName, String extendColumnNames) {
        this.id = id;
        this.key = key;
        this.state = state;
        this.extend = extend;
        this.tableName = tableName;
        this.columnName = columnName;
        this.extendColumnNames = extendColumnNames;
    }

   
    // Property accessors
    @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="policyId", column=@Column(name="POLICY_ID", nullable=false, precision=12, scale=0) ), 
        @AttributeOverride(name="flowid", column=@Column(name="FLOWID", nullable=false, precision=12, scale=0) ), 
        @AttributeOverride(name="businessType", column=@Column(name="BUSINESS_TYPE", nullable=false, precision=5, scale=0) ) } )

    public TTmcPolicyRelationId getId() {
        return this.id;
    }
    
    public void setId(TTmcPolicyRelationId id) {
        this.id = id;
    }
    
    @Column(name="KEY", precision=12, scale=0)

    public Long getKey() {
        return this.key;
    }
    
    public void setKey(Long key) {
        this.key = key;
    }
    
    @Column(name="STATE", precision=5, scale=0)

    public Integer getState() {
        return this.state;
    }
    
    public void setState(Integer state) {
        this.state = state;
    }
    
    @Column(name="EXTEND", length=200)

    public String getExtend() {
        return this.extend;
    }
    
    public void setExtend(String extend) {
        this.extend = extend;
    }
    
    @Column(name="TABLE_NAME", length=200)

    public String getTableName() {
        return this.tableName;
    }
    
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    
    @Column(name="COLUMN_NAME", length=200)

    public String getColumnName() {
        return this.columnName;
    }
    
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
    
    @Column(name="EXTEND_COLUMN_NAMES", length=1000)

    public String getExtendColumnNames() {
        return this.extendColumnNames;
    }
    
    public void setExtendColumnNames(String extendColumnNames) {
        this.extendColumnNames = extendColumnNames;
    }
   








}