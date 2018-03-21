package com.viptrip.wetrip.entity.policy;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * BookpowerMappingId entity. @author MyEclipse Persistence Tools
 */
@Embeddable

public class BookpowerMappingId  implements java.io.Serializable {


    // Fields    

     private Integer bookpowerId;
     private Integer personId;
     private Integer personType;


    // Constructors

    /** default constructor */
    public BookpowerMappingId() {
    }

    
    /** full constructor */
    public BookpowerMappingId(Integer bookpowerId, Integer personId, Integer personType) {
        this.bookpowerId = bookpowerId;
        this.personId = personId;
        this.personType = personType;
    }

   
    // Property accessors

    @Column(name="BOOKPOWER_ID", nullable=false, precision=10, scale=0)

    public Integer getBookpowerId() {
        return this.bookpowerId;
    }
    
    public void setBookpowerId(Integer bookpowerId) {
        this.bookpowerId = bookpowerId;
    }

    @Column(name="PERSON_ID", nullable=false, precision=10, scale=0)

    public Integer getPersonId() {
        return this.personId;
    }
    
    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    @Column(name="PERSON_TYPE", nullable=false, precision=2, scale=0)

    public Integer getPersonType() {
        return this.personType;
    }
    
    public void setPersonType(Integer personType) {
        this.personType = personType;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof BookpowerMappingId) ) return false;
		 BookpowerMappingId castOther = ( BookpowerMappingId ) other; 
         
		 return ( (this.getBookpowerId()==castOther.getBookpowerId()) || ( this.getBookpowerId()!=null && castOther.getBookpowerId()!=null && this.getBookpowerId().equals(castOther.getBookpowerId()) ) )
 && ( (this.getPersonId()==castOther.getPersonId()) || ( this.getPersonId()!=null && castOther.getPersonId()!=null && this.getPersonId().equals(castOther.getPersonId()) ) )
 && ( (this.getPersonType()==castOther.getPersonType()) || ( this.getPersonType()!=null && castOther.getPersonType()!=null && this.getPersonType().equals(castOther.getPersonType()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getBookpowerId() == null ? 0 : this.getBookpowerId().hashCode() );
         result = 37 * result + ( getPersonId() == null ? 0 : this.getPersonId().hashCode() );
         result = 37 * result + ( getPersonType() == null ? 0 : this.getPersonType().hashCode() );
         return result;
   }   





}