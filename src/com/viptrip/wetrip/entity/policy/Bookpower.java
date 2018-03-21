package com.viptrip.wetrip.entity.policy;
// default package

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * Bookpower entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="BOOKPOWER")

public class Bookpower  implements java.io.Serializable {


    // Fields    

     private Integer bookpowerId;
     private Integer userId;
     private Integer traveltype;
     private Integer powerCode;
     private Set<BookpowerMapping> bookpowerMappings = new HashSet<BookpowerMapping>(0);


    // Constructors

    /** default constructor */
    public Bookpower() {
    }

	/** minimal constructor */
    public Bookpower(Integer userId, Integer traveltype, Integer powerCode) {
        this.userId = userId;
        this.traveltype = traveltype;
        this.powerCode = powerCode;
    }
    
    /** full constructor */
    public Bookpower(Integer userId, Integer traveltype, Integer powerCode, Set<BookpowerMapping> bookpowerMappings) {
        this.userId = userId;
        this.traveltype = traveltype;
        this.powerCode = powerCode;
        this.bookpowerMappings = bookpowerMappings;
    }

   
    // Property accessors
    @Id 
    @GeneratedValue(strategy=SEQUENCE, generator="generator")
    @SequenceGenerator(name="generator",allocationSize=1,initialValue=1,sequenceName="SEQ_BOOKPOWER")
    @Column(name="BOOKPOWER_ID", unique=true, nullable=false, precision=10, scale=0)
    public Integer getBookpowerId() {
        return this.bookpowerId;
    }
    
    public void setBookpowerId(Integer bookpowerId) {
        this.bookpowerId = bookpowerId;
    }
    
    @Column(name="USER_ID", nullable=false, precision=10, scale=0)
    public Integer getUserId() {
        return this.userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    @Column(name="TRAVELTYPE", nullable=false, precision=2, scale=0)
    public Integer getTraveltype() {
        return this.traveltype;
    }
    
    public void setTraveltype(Integer traveltype) {
        this.traveltype = traveltype;
    }
    
    @Column(name="POWER_CODE", nullable=false, precision=4, scale=0)
    public Integer getPowerCode() {
        return this.powerCode;
    }
    
    public void setPowerCode(Integer powerCode) {
        this.powerCode = powerCode;
    }
    
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="bookpower")
    public Set<BookpowerMapping> getBookpowerMappings() {
        return this.bookpowerMappings;
    }
    
    public void setBookpowerMappings(Set<BookpowerMapping> bookpowerMappings) {
        this.bookpowerMappings = bookpowerMappings;
    }
   








}