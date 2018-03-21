package com.viptrip.wetrip.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Userbinding entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="USERBINDING")

public class Userbinding  implements java.io.Serializable {


    // Fields    

     private String openid;
     private Long careState;
     private String mobile;
     private String unionid;
     private Long bindState;
     private Long userId;
     private Timestamp bindtime;
     private Timestamp caretime;
     private String paid;


    // Constructors

    /** default constructor */
    public Userbinding() {
    }

	/** minimal constructor */
    public Userbinding(String openid) {
        this.openid = openid;
    }
    
    /** full constructor */
    public Userbinding(String openid, Long careState, String mobile, String unionid, Long bindState, Long userId, Timestamp bindtime, Timestamp caretime, String paid) {
        this.openid = openid;
        this.careState = careState;
        this.mobile = mobile;
        this.unionid = unionid;
        this.bindState = bindState;
        this.userId = userId;
        this.bindtime = bindtime;
        this.caretime = caretime;
        this.paid = paid;
    }

   
    // Property accessors
    @Id 
    
    @Column(name="OPENID", unique=true, nullable=false, length=200)

    public String getOpenid() {
        return this.openid;
    }
    
    public void setOpenid(String openid) {
        this.openid = openid;
    }
    
    @Column(name="CARE_STATE", precision=10, scale=0)

    public Long getCareState() {
        return this.careState;
    }
    
    public void setCareState(Long careState) {
        this.careState = careState;
    }
    
    @Column(name="MOBILE", length=20)

    public String getMobile() {
        return this.mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    @Column(name="UNIONID", length=200)

    public String getUnionid() {
        return this.unionid;
    }
    
    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
    
    @Column(name="BIND_STATE", precision=10, scale=0)

    public Long getBindState() {
        return this.bindState;
    }
    
    public void setBindState(Long bindState) {
        this.bindState = bindState;
    }
    
    @Column(name="USER_ID", precision=10, scale=0)

    public Long getUserId() {
        return this.userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    @Column(name="BINDTIME", length=7)

    public Timestamp getBindtime() {
        return this.bindtime;
    }
    
    public void setBindtime(Timestamp bindtime) {
        this.bindtime = bindtime;
    }
    
    @Column(name="CARETIME", length=7)

    public Timestamp getCaretime() {
        return this.caretime;
    }
    
    public void setCaretime(Timestamp caretime) {
        this.caretime = caretime;
    }
    
    @Column(name="PAID", length=200)

    public String getPaid() {
        return this.paid;
    }
    
    public void setPaid(String paid) {
        this.paid = paid;
    }
   








}