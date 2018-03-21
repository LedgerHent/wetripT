package com.viptrip.base.action;
/**
 * 异步返回实体
 * @author selfwhisper
 *
 */
public class AjaxResp {
	
	private Integer status;
	private String msg;
	private Object data;
	
	public AjaxResp() {
	}
	
	public AjaxResp(AjaxStatus status) {
		this(status,null);
	}
	
	public AjaxResp(AjaxStatus status, Object data) {
		this.status = status.getCode();
		this.msg = status.msg();
		this.data = data;
	}
	
	public AjaxResp(Integer status) {
		this(status,null);
	}
	
	public AjaxResp(Integer status, Object data) {
		this.status = status;
		this.data = data;
	}
	
	public void success(Object data){
		this.status = AjaxStatus.success.getCode();
		this.msg = AjaxStatus.success.msg();
		this.data = data;
	}
	
	public void fail(Object data){
		this.status = AjaxStatus.fail.getCode();
		this.msg = AjaxStatus.fail.msg();
		this.data = data;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
