package com.viptrip.wetrip.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

/**
 * TTravelPassenger entity. @author MyEclipse Persistence Tools
 */
@Entity
//@Table(name = "T_TRAVEL_PASSENGER", uniqueConstraints = @UniqueConstraint(columnNames = "ID"))
@Table(name = "T_TRAVEL_PASSENGER")
@org.hibernate.annotations.Entity(dynamicInsert = true,dynamicUpdate = true)
public class TTravelPassenger implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3828794429850831275L;
	private Long id;
	private Long userid;
	private String name;
	private String idtype;
	private String idnumber;
	private String mobilephone;
	private String email;
	private String passengerType;
	private Long dept;
	private String deptname;
	private String mileage;
	private String projectno;
	private String ename;
	private Date lastBookingTime;
	// Constructors

	/** default constructor */
	public TTravelPassenger() {
	}

	/** full constructor */
	public TTravelPassenger(Long userid, String name, String idtype,
			String idnumber, String mobilephone, String email,
			String passengerType, Long dept, String deptname, String mileage,
			String projectno,String ename,Date lastBookingTime) {
		this.userid = userid;
		this.name = name;
		this.idtype = idtype;
		this.idnumber = idnumber;
		this.mobilephone = mobilephone;
		this.email = email;
		this.passengerType = passengerType;
		this.dept = dept;
		this.deptname = deptname;
		this.mileage = mileage;
		this.projectno = projectno;
		this.ename= ename;
		this.lastBookingTime= lastBookingTime;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HIBERNATE_SEQUENCE")
	@SequenceGenerator(name = "HIBERNATE_SEQUENCE", allocationSize = 1, initialValue = 1, sequenceName = "HIBERNATE_SEQUENCE")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "USERID", precision = 10, scale = 0)
	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	@Column(name = "NAME")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "IDTYPE", length = 1)
	public String getIdtype() {
		return this.idtype;
	}

	public void setIdtype(String idtype) {
		this.idtype = idtype;
	}

	@Column(name = "IDNUMBER")
	public String getIdnumber() {
		return this.idnumber;
	}

	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}

	@Column(name = "MOBILEPHONE")
	public String getMobilephone() {
		return this.mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	@Column(name = "EMAIL")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "PASSENGER_TYPE")
	public String getPassengerType() {
		return this.passengerType;
	}

	public void setPassengerType(String passengerType) {
		this.passengerType = passengerType;
	}

	@Column(name = "DEPT", precision = 10, scale = 0)
	public Long getDept() {
		return this.dept;
	}

	public void setDept(Long dept) {
		this.dept = dept;
	}

	@Column(name = "DEPTNAME")
	public String getDeptname() {
		return this.deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	@Column(name = "MILEAGE")
	public String getMileage() {
		return this.mileage;
	}

	public void setMileage(String mileage) {
		this.mileage = mileage;
	}

	@Column(name = "PROJECTNO")
	public String getProjectno() {
		return this.projectno;
	}

	public void setProjectno(String projectno) {
		this.projectno = projectno;
	}
	@Column(name = "ename")
	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_BOOKING_TIME", length = 7)
	public Date getLastBookingTime() {
		return lastBookingTime;
	}

	public void setLastBookingTime(Date lastBookingTime) {
		this.lastBookingTime = lastBookingTime;
	}

}