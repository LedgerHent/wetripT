package com.viptrip.autoissue.entity;

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
/**
 * 自动出票记录表
 * @author jh
 *
 */
@Entity
@Table(name = "T_AUTO_TICKET")
public class TAutoTicket implements java.io.Serializable {
	
	private Long id;//id
	
	private	String orderNo;//订单号
	
	private Long orderId;//订单ID
	
	private Double price;//票面价
	
	private String clientCode;//大客户编码
	
	private Integer status;//状态
	
	private String message;//备注信息
	
	private Date createTime;//记录创建时间
	
	private Date updateTime;//记录修改时间

	private Double purchasePrice;//采购价
	
	
	public TAutoTicket() {
	}

	
	public TAutoTicket(Long id, String orderNo, Long orderId, Double price,
			String clientCode, Integer status, String message, Date createTime,
			Date updateTime) {
		this.id = id;
		this.orderNo = orderNo;
		this.orderId = orderId;
		this.price = price;
		this.clientCode = clientCode;
		this.status = status;
		this.message = message;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_T_AUTO_TICKET")
    @SequenceGenerator(name = "SEQ_T_AUTO_TICKET", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_T_AUTO_TICKET")
	@Column(name = " ID ",unique=true,nullable=false,precision=10,scale=0 )
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column( name ="ORDERNO",length=40 )
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Column( name="ORDERID",length=10)
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	@Column( name ="PRICE",length=10)
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column( name ="CLIENTCODE",length=50)
	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	@Column( name ="STATUS",length=2)
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column( name ="MESSAGE",length=1000)
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATETIME", length = 7)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATETIME", length = 7)
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	
	@Column( name ="PURCHASEPRICE",length=10)
	public Double getPurchasePrice() {
		return purchasePrice;
	}


	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	
	
	
	
	

}
