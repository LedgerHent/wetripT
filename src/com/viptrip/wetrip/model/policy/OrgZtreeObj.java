package com.viptrip.wetrip.model.policy;

public class OrgZtreeObj {
	private Integer id;
	private Integer pid;
	private String name;
	private Integer type;//类型 1为组织构架 2为企业用户 
	
	
	
	public OrgZtreeObj() {
		super();
	}
	
	public OrgZtreeObj(Integer id, Integer pid, String name, Integer type) {
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.type = type;
	}
	
	public boolean orgParentsOfUser(OrgZtreeObj other){
		boolean result = false;
		if(null!=other&&1==this.type&&2==other.getType()){
			result = this.getId().equals(other.getPid());
		}
		return result;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	

}
