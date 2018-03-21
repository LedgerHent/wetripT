package com.viptrip.hotelHtml5.vo.tmc;

public class TmcOrderShortMsgHistroryVo extends TmcOrderShortMsgHistrory {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3577157973310203580L;

	private String templateName;
	private String sendStateName;
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public String getSendStateName() {
		return sendStateName;
	}
	public void setSendStateName(String sendStateName) {
		this.sendStateName = sendStateName;
	}
	
}
