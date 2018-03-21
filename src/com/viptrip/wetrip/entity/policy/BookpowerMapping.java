package com.viptrip.wetrip.entity.policy;
// default package

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * BookpowerMapping entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="BOOKPOWER_MAPPING")

public class BookpowerMapping  implements java.io.Serializable {


    // Fields    

     private BookpowerMappingId id;
     
     private Bookpower bookpower;


    // Constructors

    /** default constructor */
    public BookpowerMapping() {
    }

    
    /** full constructor */
    public BookpowerMapping(BookpowerMappingId id, Bookpower bookpower) {
        this.id = id;
        this.bookpower = bookpower;
    }

   
    // Property accessors
    @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="bookpowerId", column=@Column(name="BOOKPOWER_ID", nullable=false, precision=10, scale=0) ), 
        @AttributeOverride(name="personId", column=@Column(name="PERSON_ID", nullable=false, precision=10, scale=0) ), 
        @AttributeOverride(name="personType", column=@Column(name="PERSON_TYPE", nullable=false, precision=2, scale=0) ) } )

    public BookpowerMappingId getId() {
        return this.id;
    }
    
    public void setId(BookpowerMappingId id) {
        this.id = id;
    }
    
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="BOOKPOWER_ID", nullable=false, insertable=false, updatable=false)
    public Bookpower getBookpower() {
        return this.bookpower;
    }
    
    public void setBookpower(Bookpower bookpower) {
        this.bookpower = bookpower;
    }
   








}