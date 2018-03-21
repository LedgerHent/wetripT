package com.viptrip.pay.ali.vo;

import java.util.Date;

public class AlipayRespBean implements IAliResp{
	
	protected String buyer_id;
	protected String trade_no;
	protected Date notify_time;
	protected String subject;
	protected String sign_type;
	protected String notify_type;
	protected String out_trade_no;
	protected String trade_status;
	protected String sign;
	protected String buyer_email;
	protected Double total_fee;
	protected String seller_id;
	protected String notify_id;
	protected String seller_email;
	protected String payment_type;
	
	
	@Override
	public Double getTotalFee() {
		return total_fee;
	}
	@Override
	public String getTradeNo() {
		return trade_no;
	}
	@Override
	public String getOrderNo() {
		return out_trade_no;
	}
	@Override
	public Date getPayTime() {
		return notify_time;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((buyer_email == null) ? 0 : buyer_email.hashCode());
		result = prime * result
				+ ((buyer_id == null) ? 0 : buyer_id.hashCode());
		result = prime * result
				+ ((notify_id == null) ? 0 : notify_id.hashCode());
		result = prime * result
				+ ((notify_time == null) ? 0 : notify_time.hashCode());
		result = prime * result
				+ ((notify_type == null) ? 0 : notify_type.hashCode());
		result = prime * result
				+ ((out_trade_no == null) ? 0 : out_trade_no.hashCode());
		result = prime * result
				+ ((payment_type == null) ? 0 : payment_type.hashCode());
		result = prime * result
				+ ((seller_email == null) ? 0 : seller_email.hashCode());
		result = prime * result
				+ ((seller_id == null) ? 0 : seller_id.hashCode());
		result = prime * result + ((sign == null) ? 0 : sign.hashCode());
		result = prime * result
				+ ((sign_type == null) ? 0 : sign_type.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result
				+ ((total_fee == null) ? 0 : total_fee.hashCode());
		result = prime * result
				+ ((trade_no == null) ? 0 : trade_no.hashCode());
		result = prime * result
				+ ((trade_status == null) ? 0 : trade_status.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlipayRespBean other = (AlipayRespBean) obj;
		if (buyer_email == null) {
			if (other.buyer_email != null)
				return false;
		} else if (!buyer_email.equals(other.buyer_email))
			return false;
		if (buyer_id == null) {
			if (other.buyer_id != null)
				return false;
		} else if (!buyer_id.equals(other.buyer_id))
			return false;
		if (notify_id == null) {
			if (other.notify_id != null)
				return false;
		} else if (!notify_id.equals(other.notify_id))
			return false;
		if (notify_time == null) {
			if (other.notify_time != null)
				return false;
		} else if (!notify_time.equals(other.notify_time))
			return false;
		if (notify_type == null) {
			if (other.notify_type != null)
				return false;
		} else if (!notify_type.equals(other.notify_type))
			return false;
		if (out_trade_no == null) {
			if (other.out_trade_no != null)
				return false;
		} else if (!out_trade_no.equals(other.out_trade_no))
			return false;
		if (payment_type == null) {
			if (other.payment_type != null)
				return false;
		} else if (!payment_type.equals(other.payment_type))
			return false;
		if (seller_email == null) {
			if (other.seller_email != null)
				return false;
		} else if (!seller_email.equals(other.seller_email))
			return false;
		if (seller_id == null) {
			if (other.seller_id != null)
				return false;
		} else if (!seller_id.equals(other.seller_id))
			return false;
		if (sign == null) {
			if (other.sign != null)
				return false;
		} else if (!sign.equals(other.sign))
			return false;
		if (sign_type == null) {
			if (other.sign_type != null)
				return false;
		} else if (!sign_type.equals(other.sign_type))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		if (total_fee == null) {
			if (other.total_fee != null)
				return false;
		} else if (!total_fee.equals(other.total_fee))
			return false;
		if (trade_no == null) {
			if (other.trade_no != null)
				return false;
		} else if (!trade_no.equals(other.trade_no))
			return false;
		if (trade_status == null) {
			if (other.trade_status != null)
				return false;
		} else if (!trade_status.equals(other.trade_status))
			return false;
		return true;
	}
	public String getBuyer_id() {
		return buyer_id;
	}
	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}
	public String getTrade_no() {
		return trade_no;
	}
	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}
	public Date getNotify_time() {
		return notify_time;
	}
	public void setNotify_time(Date notify_time) {
		this.notify_time = notify_time;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getSign_type() {
		return sign_type;
	}
	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
	public String getNotify_type() {
		return notify_type;
	}
	public void setNotify_type(String notify_type) {
		this.notify_type = notify_type;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getTrade_status() {
		return trade_status;
	}
	public void setTrade_status(String trade_status) {
		this.trade_status = trade_status;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getBuyer_email() {
		return buyer_email;
	}
	public void setBuyer_email(String buyer_email) {
		this.buyer_email = buyer_email;
	}
	public Double getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(Double total_fee) {
		this.total_fee = total_fee;
	}
	public String getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}
	public String getNotify_id() {
		return notify_id;
	}
	public void setNotify_id(String notify_id) {
		this.notify_id = notify_id;
	}
	public String getSeller_email() {
		return seller_email;
	}
	public void setSeller_email(String seller_email) {
		this.seller_email = seller_email;
	}
	public String getPayment_type() {
		return payment_type;
	}
	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}
	
}
