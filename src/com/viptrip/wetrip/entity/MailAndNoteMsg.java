package com.viptrip.wetrip.entity;




public class MailAndNoteMsg  {

	/**
	 * 此模型存放邮件发送和短信发送中所需的字段，不需要存到数据库
	 */
	private static final long serialVersionUID = 1436544546345L;
    private String sender;//发件人名字
    private String recevier;//收件人,可能是一组人
    private String contact;//联系方式
    private String title;//标题
    private MessageModel text;//内容
    private String mail;//邮箱
    private String orderno;
    private String senderid;//发件人id
    private String recevierid;//收件人id,可能是一组人,多个时以逗号分隔
    private String recipient;
    
    
	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getRecevier() {
		return recevier;
	}

	public void setRecevier(String recevier) {
		this.recevier = recevier;
	}

	public String getSenderid() {
		return senderid;
	}

	public void setSenderid(String senderid) {
		this.senderid = senderid;
	}

	public String getRecevierid() {
		return recevierid;
	}

	public void setRecevierid(String recevierid) {
		this.recevierid = recevierid;
	}

	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}


	public MessageModel getText() {
		return text;
	}

	public void setText(MessageModel text) {
		this.text = text;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String toString(){
		
		return null;
		
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	

}
