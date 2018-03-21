package com.viptrip.intlAirticket.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * TIntlComment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_INTL_COMMENT")
public class TIntlComment implements java.io.Serializable {

	// Fields

	private Long commentId;
	private String passengerName;
	private String commentMsg;
	private String commentDate;
	private Long OId;

	// Constructors

	/** default constructor */
	public TIntlComment() {
	}

	/** minimal constructor */
	public TIntlComment(Long OId) {
		this.OId = OId;
	}

	/** full constructor */
	public TIntlComment(String passengerName, String commentMsg,
			String commentDate, Long OId) {
		this.passengerName = passengerName;
		this.commentMsg = commentMsg;
		this.commentDate = commentDate;
		this.OId = OId;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_INTL_COMMENT")
	@SequenceGenerator(name="SEQ_INTL_COMMENT",allocationSize=1,initialValue=100, sequenceName="SEQ_INTL_COMMENT")
	@Column(name = "COMMENT_ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getCommentId() {
		return this.commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	@Column(name = "PASSENGER_NAME", length = 20)
	public String getPassengerName() {
		return this.passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	@Column(name = "COMMENT_MSG", length = 4000)
	public String getCommentMsg() {
		return this.commentMsg;
	}

	public void setCommentMsg(String commentMsg) {
		this.commentMsg = commentMsg;
	}

	@Column(name = "COMMENT_DATE", length = 100)
	public String getCommentDate() {
		return this.commentDate;
	}

	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}

	@Column(name = "O_ID", nullable = false, precision = 22, scale = 0)
	public Long getOId() {
		return this.OId;
	}

	public void setOId(Long OId) {
		this.OId = OId;
	}

}