package com.viptrip.wetrip.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;



/**
 * TPnr entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_PNR")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class TPnr extends AuditableEntity  implements Cloneable,java.io.Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8709955193657718770L;
	// Fields

	private Long pnrId;
	private TOrder TOrder;
	private String pnrstatus;
	private String airlineCompany;
	private String originCity;
	private String destinationCity;
	private Date flightDate;
	private Double taxPrice;
	private String airline;
	private String cangwei;
	private String pnr;
	private Double fueltax;
	private Date originTime;
	private Date destinationTime;
	private String planetype;
	private String orgterm;
	private String detterm;
	private String flighttype;
	private long supplierId;
	private Double assurancePrice;
	private Double discountPrice;
	private Double total;
	private Set<TTravelItinerary> TTravelItineraries = new HashSet<TTravelItinerary>(
			0);
	private String bigplait;
	private String reason;
	private Double enterprisrdiscount;
	private Long ticketcount;
	private Date zwcpdate;//最晚出票时间
	private Double ticketYPrice;//Y舱全价,代码中已经被借用存票面价
	private Double ticketFPrice;//头等舱价格，代码中已经被视为存相关舱位等级的全价
	private Double taxes;//税金
	private String isorgdealprice;  //是否企业协议价
	private String suppliername;  //供应商name
	private String ffstr;   //经停字符串
	
	private Double lowestPrice;  //当日最低价格
	
	private String airlineshare;  //航班代码共享
	// Constructors

	
	private double ZNum;
	private double proxyFee;
	/** default constructor */
	public TPnr() {
	}

	/** minimal constructor */
	public TPnr(TOrder TOrder, String pnrstatus) {
		this.TOrder = TOrder;
		this.pnrstatus = pnrstatus;
	}

	/** full constructor */
	public TPnr(TOrder TOrder, String pnrstatus, String airlineCompany,
			String originCity, String destinationCity, Timestamp flightDate,
			Double taxPrice, String airline, String cangwei, String pnr,
			Double fueltax, Timestamp originTime, Timestamp destinationTime,
			String planetype, String orgterm, String detterm,
			String flighttype, long supplierId, Double assurancePrice,
			Set<TTravelItinerary> TTravelItineraries,String airlineshare) {
		this.TOrder = TOrder;
		this.pnrstatus = pnrstatus;
		this.airlineCompany = airlineCompany;
		this.originCity = originCity;
		this.destinationCity = destinationCity;
		this.flightDate = flightDate;
		this.taxPrice = taxPrice;
		this.airline = airline;
		this.cangwei = cangwei;
		this.pnr = pnr;
		this.fueltax = fueltax;
		this.originTime = originTime;
		this.destinationTime = destinationTime;
		this.planetype = planetype;
		this.orgterm = orgterm;
		this.detterm = detterm;
		this.flighttype = flighttype;
		this.supplierId = supplierId;
		this.assurancePrice = assurancePrice;
		this.TTravelItineraries = TTravelItineraries;
		this.airlineshare = airlineshare;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_TPnr")
	@SequenceGenerator(name="SEQ_TPnr",allocationSize=1,initialValue=1, sequenceName="SEQ_TPnr")
	@Column(name = "PNR_ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getPnrId() {
		return this.pnrId;
	}

	public void setPnrId(Long pnrId) {
		this.pnrId = pnrId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "O_ID", nullable = false)
	public TOrder getTOrder() {
		return this.TOrder;
	}

	public void setTOrder(TOrder TOrder) {
		this.TOrder = TOrder;
	}

	@Column(name = "PNRSTATUS", length = 2)
	public String getPnrstatus() {
		return this.pnrstatus;
	}

	public void setPnrstatus(String pnrstatus) {
		this.pnrstatus = pnrstatus;
	}

	@Column(name = "AIRLINE_COMPANY", length = 4)
	public String getAirlineCompany() {
		return this.airlineCompany;
	}

	public void setAirlineCompany(String airlineCompany) {
		this.airlineCompany = airlineCompany;
	}

	@Column(name = "ORIGIN_CITY", length = 4)
	public String getOriginCity() {
		return this.originCity;
	}

	public void setOriginCity(String originCity) {
		this.originCity = originCity;
	}

	@Column(name = "DESTINATION_CITY", length = 4)
	public String getDestinationCity() {
		return this.destinationCity;
	}

	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FLIGHT_DATE", length = 7)
	public Date getFlightDate() {
		return this.flightDate;
	}

	public void setFlightDate(Date flightDate) {
		this.flightDate = flightDate;
	}

	@Column(name = "TAX_PRICE", precision = 10)
	public Double getTaxPrice() {
		return this.taxPrice;
	}

	public void setTaxPrice(Double taxPrice) {
		this.taxPrice = taxPrice;
	}

	@Column(name = "AIRLINE", length = 12)
	public String getAirline() {
		return this.airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	@Column(name = "CANGWEI", length = 2)
	public String getCangwei() {
		return this.cangwei;
	}

	public void setCangwei(String cangwei) {
		this.cangwei = cangwei;
	}

	@Column(name = "PNR", length = 30)
	public String getPnr() {
		return this.pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	@Column(name = "FUELTAX", precision = 10)
	public Double getFueltax() {
		return this.fueltax;
	}

	public void setFueltax(Double fueltax) {
		this.fueltax = fueltax;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ORIGIN_TIME", length = 7)
	public Date getOriginTime() {
		return this.originTime;
	}

	public void setOriginTime(Date originTime) {
		this.originTime = originTime;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DESTINATION_TIME", length = 7)
	public Date getDestinationTime() {
		return this.destinationTime;
	}

	public void setDestinationTime(Date destinationTime) {
		this.destinationTime = destinationTime;
	}

	@Column(name = "PLANETYPE", length = 10)
	public String getPlanetype() {
		return this.planetype;
	}

	public void setPlanetype(String planetype) {
		this.planetype = planetype;
	}

	@Column(name = "ORGTERM", length = 10)
	public String getOrgterm() {
		return this.orgterm;
	}

	public void setOrgterm(String orgterm) {
		this.orgterm = orgterm;
	}

	@Column(name = "DETTERM", length = 10)
	public String getDetterm() {
		return this.detterm;
	}

	public void setDetterm(String detterm) {
		this.detterm = detterm;
	}

	@Column(name = "FLIGHTTYPE", length = 2)
	public String getFlighttype() {
		return this.flighttype;
	}

	public void setFlighttype(String flighttype) {
		this.flighttype = flighttype;
	}

	@Column(name = "SUPPLIER_ID", precision = 22, scale = 0)
	public Long getSupplierId() {
		return this.supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	@Column(name = "ASSURANCE_PRICE", precision = 10)
	public Double getAssurancePrice() {
		return this.assurancePrice;
	}

	public void setAssurancePrice(Double assurancePrice) {
		this.assurancePrice = assurancePrice;
	}
	
	@Column(name = "DISCOUNT_PRICE", precision = 10)
	public Double getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(Double discountPrice) {
		this.discountPrice = discountPrice;
	}
	
	@Column(name = "TOTAL", precision = 10)
	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "TPnr")
	public Set<TTravelItinerary> getTTravelItineraries() {
		return this.TTravelItineraries;
	}

	public void setTTravelItineraries(Set<TTravelItinerary> TTravelItineraries) {
		this.TTravelItineraries = TTravelItineraries;
	}

	@Column(name = "BIGPLAI", length = 30)
	public String getBigplait() {
		return bigplait;
	}

	public void setBigplait(String bigplait) {
		this.bigplait = bigplait;
	}

	@Column(name = "REASON", length = 50)
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	@Column(name = "ENTERPRISE_DISCOUNT", precision = 10)
	public Double getEnterprisrdiscount() {
		return enterprisrdiscount;
	}

	public void setEnterprisrdiscount(Double enterprisrdiscount) {
		this.enterprisrdiscount = enterprisrdiscount;
	}

	@Column(name = "TICKET_COUNT", precision = 22, scale = 0)
	public Long getTicketcount() {
		return ticketcount;
	}

	public void setTicketcount(Long ticketcount) {
		this.ticketcount = ticketcount;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ZWCPDATE", length = 7)
	public Date getZwcpdate() {
		return zwcpdate;
	}

	public void setZwcpdate(Date zwcpdate) {
		this.zwcpdate = zwcpdate;
	}
	
	
	
	@Column(name = "TICKET_Y_PRICE", precision = 10)
	public Double getTicketYPrice() {
		return ticketYPrice;
	}

	public void setTicketYPrice(Double ticketYPrice) {
		this.ticketYPrice = ticketYPrice;
	}

	@Column(name = "TICKET_F_PRICE", precision = 10)
	public Double getTicketFPrice() {
		return ticketFPrice;
	}

	public void setTicketFPrice(Double ticketFPrice) {
		this.ticketFPrice = ticketFPrice;
	}

	public void setSupplierId(long supplierId) {
		this.supplierId = supplierId;
	}
	@Column(name = "TAXES", precision = 10)
	public Double getTaxes() {
		return taxes;
	}

	public void setTaxes(Double taxes) {
		this.taxes = taxes;
	}
	
	@Column(name = "ISORGDEALPRICE", length = 2)
	public String getIsorgdealprice() {
		return isorgdealprice;
	}

	public void setIsorgdealprice(String isorgdealprice) {
		this.isorgdealprice = isorgdealprice;
	}
	@Column(name = "SUPPLIER_NAME", length = 100)
	public String getSuppliername() {
		return suppliername;
	}

	public void setSuppliername(String suppliername) {
		this.suppliername = suppliername;
	}

	@Column(name = "FFSTR", length = 100)
	public String getFfstr() {
		return ffstr;
	}

	public void setFfstr(String ffstr) {
		this.ffstr = ffstr;
	}

	@Column(name = "LOWEST_PRICE", precision = 10)
	public Double getLowestPrice() {
		return lowestPrice;
	}

	public void setLowestPrice(Double lowestPrice) {
		this.lowestPrice = lowestPrice;
	}

	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Transient
	public double getZNum() {
		return ZNum;
	}

	public void setZNum(double zNum) {
		ZNum = zNum;
	}
	@Transient
	public double getProxyFee() {
		return proxyFee;
	}

	public void setProxyFee(double proxyFee) {
		this.proxyFee = proxyFee;
	}
	 @Column(name="AIRLINESHARE", length=10)

	    public String getAirlineshare() {
	        return this.airlineshare;
	    }
	    
	    public void setAirlineshare(String airlineshare) {
	        this.airlineshare = airlineshare;
	    }
		
		
	    
	    
	    
	    
	  
}