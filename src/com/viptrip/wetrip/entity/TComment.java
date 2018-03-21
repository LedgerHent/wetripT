package com.viptrip.wetrip.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * TComment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_COMMENT")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class TComment implements java.io.Serializable {

	// Fields

	private Long commentId;
	private String passengerName;
	private String commentMsg;
	private String commentDate;
	private Long OId;
	// Constructors

	/** default constructor */
	public TComment() {
	}

	/** minimal constructor */
	public TComment(Long OId) {
		this.OId = OId;
	}

	/** full constructor */
	public TComment(String passengerName, String commentMsg,
			String commentDate, Long OId) {
		this.passengerName = passengerName;
		this.commentMsg = commentMsg;
		this.commentDate = commentDate;
		this.OId = OId;
	}

	// Property accessors
	
	/*@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_COMMENT")
	@SequenceGenerator(name="SEQ_COMMENT",allocationSize=1,initialValue=1, sequenceName="SEQ_COMMENT")*/
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_COMMENT")
	@SequenceGenerator(name="SEQ_COMMENT",allocationSize=1,initialValue=1, sequenceName="SEQ_COMMENT")
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

	@Column(name = "COMMENT_MSG", length = 500)
	public String getCommentMsg() {
		return this.commentMsg;
	}

	public void setCommentMsg(String commentMsg) {
		this.commentMsg = commentMsg;
	}
	@Column(name = "COMMENT_DATE", length = 30)
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