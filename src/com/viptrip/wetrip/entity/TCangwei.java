package com.viptrip.wetrip.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * 舱位表的实体类
 * @author lrc
 *
 */
@Entity
@Table(name="T_CANGWEI")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class TCangwei extends AuditableEntity implements java.io.Serializable, Cloneable  {

	private static final long serialVersionUID = 3513060935825398284L;

	private Long ctid;
	
	private String airline;
	
	private Date starttime;
	
	private String cangwei;
	
	private double price;
	
	private double taxfee;
	
	private double fueltax;
	
	private Date inserttime;
	
	private String orgcity; //起飞城市，存放三字代码
	
	private String detcity; //目的城市，存放三字代码
	
	private double discountrate = 0.0;  //折扣率
	
	private String seatsLeft;
	
    private String cangweiDesc;//舱位说明
    
    private double yprice = 0.0;  //Y舱票面价（即Y舱全价）
    
    private String classtype; //舱位等级，1-经济舱，2-公务舱，3-头等舱  李荣春 增加
    
    private String isfull;    //该舱位是否为全价舱（0-否，1-是）  李荣春 增加
    
    private Double aprice;  //如果该舱位是公务舱，这里放公务舱全价舱的价格  李荣春 增加
    
    private Double fprice;  //如果该舱位是头等舱，这里放头等舱全价舱的价格  李荣春 增加
    
    private String rulecode;  //正常情况下放的是舱位代码，如果和舱位代码不一样，说明是特价舱位
    // Constructors
	
	public TCangwei() {
    }

    @Id 
    @Column(name="CTID", unique=true, nullable=false, precision=22, scale=0)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_CANGWEI")
	@SequenceGenerator(name="SEQ_CANGWEI",allocationSize=1,initialValue=1, sequenceName="SEQ_CANGWEI")
	public Long getCtid() {
		return ctid;
	}

	public void setCtid(Long ctid) {
		this.ctid = ctid;
	}

    @Column(name="AIRLINE", length=10)
	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

    @Column(name="STARTTIME")
	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

    @Column(name="CANGWEI", length=2)
	public String getCangwei() {
		return cangwei;
	}

	public void setCangwei(String cangwei) {
		this.cangwei = cangwei;
	}

    @Column(name="PRICE", precision=12)
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

    @Column(name="TAXFEE", precision=12)
	public double getTaxfee() {
		return taxfee;
	}

	public void setTaxfee(double taxfee) {
		this.taxfee = taxfee;
	}

    @Column(name="FUELTAX", precision=12)
	public double getFueltax() {
		return fueltax;
	}

	public void setFueltax(double fueltax) {
		this.fueltax = fueltax;
	}

    @Column(name="INSERTTIME")
	public Date getInserttime() {
		return inserttime;
	}

	public void setInserttime(Date inserttime) {
		this.inserttime = inserttime;
	}
	
	@Column(name="ORGCITY", length=4)
	public String getOrgcity() {
		return orgcity;
	}

	public void setOrgcity(String orgcity) {
		this.orgcity = orgcity;
	}
	
	@Column(name="DETCITY", length=4)
	public String getDetcity() {
		return detcity;
	}

	public void setDetcity(String detcity) {
		this.detcity = detcity;
	}

    @Column(name="DISCOUNTRATE", precision=12)

    public double getDiscountrate() {
        return this.discountrate;
    }
    
    public void setDiscountrate(double discountrate) {
        this.discountrate = discountrate;
    }
    
    @Column(name="SEATSLEFT", precision=12)
    public String getSeatsLeft() {
		return seatsLeft;
	}
   
	public void setSeatsLeft(String seatsLeft) {
		this.seatsLeft = seatsLeft;
	}


    @Column(name="CANGWEI_DESC")
	public String getCangweiDesc() {
		return cangweiDesc;
	}

	public void setCangweiDesc(String cangweiDesc) {
		this.cangweiDesc = cangweiDesc;
	}
	
	
	@Column(name="YPRICE", precision=12)
	public double getYprice() {
		return yprice;
	}

	public void setYprice(double yprice) {
		this.yprice = yprice;
	}
	
	@Column(name="CLASSTYPE", length=1)
	public String getClasstype() {
		return classtype;
	}

	public void setClasstype(String classtype) {
		this.classtype = classtype;
	}

	@Column(name="ISFULL", length=1)
	public String getIsfull() {
		return isfull;
	}

	public void setIsfull(String isfull) {
		this.isfull = isfull;
	}

	@Column(name="APRICE")
	public Double getAprice() {
		return aprice;
	}

	public void setAprice(Double aprice) {
		this.aprice = aprice;
	}

	@Column(name="FPRICE")
	public Double getFprice() {
		return fprice;
	}

	public void setFprice(Double fprice) {
		this.fprice = fprice;
	}

	@Column(name="RULECODE")
	public String getRulecode() {
		return rulecode;
	}

	public void setRulecode(String rulecode) {
		this.rulecode = rulecode;
	}

	@Override
	public Object clone(){
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
}

