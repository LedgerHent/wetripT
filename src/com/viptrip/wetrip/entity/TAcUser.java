package com.viptrip.wetrip.entity;

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
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.google.gson.GsonBuilder;



/**
 * @author Administrator
 *
 */
@Entity
@Table(name = "T_AC_USER", uniqueConstraints = @UniqueConstraint(columnNames = "LOGINNAME"))
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
//@Cacheable(true)
@Cache(region="common", usage = CacheConcurrencyStrategy.READ_WRITE)
public class TAcUser extends AuditableEntity implements java.io.Serializable {
	private static final long serialVersionUID = 3943803077747565669L;
	private Long userid;      //用户表ID
	private String username;	//人员姓名
	private String usercode;	//人员编码
	private String password;	//加密密码
	private String loginname;	//登陆名
	private String sex;			//性别
	private Date birthday;		//出生日期
	private String email;		//邮箱
	private Long orgid;			//机构ID
	private String phone;		//手机号码
	private String cellphone;	//电话号码
	private String address;		//地址
	private String usertype;	//用户类型
	private String plainpassword;//未加密密码
	private String status;		 //状态（0－初始 1－正常 2－挂起 3－锁定 4－停用）
	private String bankName;	//开户行
	private String bankAccount; //银行帐号
	private String invoiceagingdate; //记帐起始日期类型
	private String depno;  //部门编号
	private String idcard;		//身份证
	private String extf1;		
	private String extf2;
	private String extf3;
	private String extf4;
	private String extf5;
	private Date pwdModifyTime;	//密码修改时间
	private Long pwdWrongCount; //密码错误次数
	private Date lockTime;		//锁定时间
	private String allowlogin;   //能否登录系统

	/***2013-10-31 添加护照信息    李国强 *************************************/
	private String passport;	//护照号
	private String passportEn;	//护照英文名
	private Date passportVal;	//护照有效期开始
	private Date passportEnd;	//护照有效期结束
	private String nationality;	//国籍三字码
	private String otherNum;	//其他证件号
	/**2013-11-1添加**/
	private String employeeId;     //员工编号
	private String duty;           //职务
	private String mileage;        //里程卡号，不限制录入内容
	private Date passportbyrthday; //护照出生日期
	/*********************************************************************/
	
	
	/********2014-2-7王晴添加*********************************/
	private String hxz;      //回乡证
	private String hxzname;  //回乡证姓名
	private String gatxz;    //港澳通行证
	private String gatxzname; //港澳通行证姓名     
	private String jgz;     //军官证
	private String jgzname; //军官证姓名
	private String rtz;     //入台证
	private String rtzname; //入台证姓名
	private String dlwltxz; //大陆往来通行证
	private String dlwltxzname; //大陆往来通行证姓名
	
	private String isbiller;  //是否账单接收人，在用户维护的功能中进行配置
	
	/******************************************************/
	
	private String publicgatxz;     //因公港澳通行证 wlh 增加2014 05 29
	private String publicgatxzname;  //因公港澳通行证名字  2014 05 29 
	private String passportenpublic; //因公护照英文名   2014 05 29
	private String passportpublic;   //因公护照号   2014 05 29
	private Date  passportbirthdaypublic;  //因公护照出生日期  2014 05 29
	private Date passportendpublic;  // 因公护照有效期结束  2014 05 29
	private String nationalitypublic; //因公护照国籍三字码     2014 05 29
	private String subscribeorg;  //员工可为哪些部门进行预订的选项 2014 06 30
	private String plane;         //办公电话     2014 07 25
	private String verifyorg;     //可审核部门  2014 08 20 
	private String nationalityTwo;				//国籍二字码
	private String nationalityTwoPublic;		//因公护照国籍二字码
	
	private Double highestDiscount;//预订机票最高折扣 
	
	private String balanceRemind; //预付款提醒：0短信 1邮件
	
	private Date lastLoginTime;
	 
	
	// private List<TAcRole> roleList = Lists.newArrayList(); //鏈夊簭鐨勫叧鑱斿璞￠泦鍚�
	// Constructors

	/** default constructor */
	public TAcUser() {
	}

	public TAcUser(Long userid) {
		this.userid = userid;
	}
	
	/** minimal constructor */
	public TAcUser(Long userid, String username, String loginname, Long orgid,
			String usertype) {
		this.userid = userid;
		this.username = username;
		this.loginname = loginname;
		this.orgid = orgid;
		this.usertype = usertype;
	}

	public TAcUser(String username, String loginname, Long orgid,
			String phone, String sex, Date birthday, String email) {
		this.username = username;
		this.loginname = loginname;
		this.orgid = orgid;
		this.phone = phone;
		this.sex = sex;
		this.birthday = birthday;
		this.email = email;
	}

	/** full constructor */
	public TAcUser(Long userid, String username, String usercode,
			String password, String loginname, String sex, Date birthday,
			String email, Long orgid, String phone, String cellphone,
			String address, String usertype, String plainpassword,
			String status, String bankName, String bankAccount, String idcard,
			String extf1, String extf2, String extf3, String extf4) {
		this.userid = userid;
		this.username = username;
		this.usercode = usercode;
		this.password = password;
		this.loginname = loginname;
		this.sex = sex;
		this.birthday = birthday;
		this.email = email;
		this.orgid = orgid;
		this.phone = phone;
		this.cellphone = cellphone;
		this.address = address;
		this.usertype = usertype;
		this.plainpassword = plainpassword;
		this.status = status;
		this.bankName = bankName;
		this.bankAccount = bankAccount;
		this.idcard = idcard;
		this.extf1 = extf1;
		this.extf2 = extf2;
		this.extf3 = extf3;
		this.extf4 = extf4;
	}

	// Property accessors
	@Id
	@Column(name = "USERID", unique = true, nullable = false, precision = 10, scale = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_AC_USER")
	@SequenceGenerator(name = "SEQ_AC_USER", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_AC_USER")
	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	@Column(name = "USERNAME", length = 150)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "USERCODE", length = 40)
	public String getUsercode() {
		return this.usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	@Column(name = "PASSWORD", length = 40)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "LOGINNAME", unique = true, length = 40)
	public String getLoginname() {
		return this.loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	@Column(name = "SEX", length = 2)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BIRTHDAY", length = 7)
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "EMAIL", length = 40)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "ORGID", nullable = false, precision = 10, scale = 0)
	public Long getOrgid() {
		return this.orgid;
	}

	public void setOrgid(Long orgid) {
		this.orgid = orgid;
	}

	@Column(name = "PHONE", length = 20)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "CELLPHONE", length = 20)
	public String getCellphone() {
		return this.cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	@Column(name = "ADDRESS", length = 100)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "USERTYPE", length = 1)
	public String getUsertype() {
		return this.usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	@Column(name = "PLAINPASSWORD", length = 40)
	public String getPlainpassword() {
		return this.plainpassword;
	}

	public void setPlainpassword(String plainpassword) {
		this.plainpassword = plainpassword;
	}

	@Column(name = "STATUS", length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "BANK_NAME", length = 200)
	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	@Column(name = "BANK_ACCOUNT", length = 100)
	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	// //澶氬澶氬畾涔�// @ManyToMany
	// //涓棿琛ㄥ畾涔�琛ㄥ悕閲囩敤榛樿鍛藉悕瑙勫垯
	// @JoinTable(name = "T_AC_USERROLE", joinColumns = { @JoinColumn(name =
	// "USERID") }, inverseJoinColumns = { @JoinColumn(name = "ROLEID") })
	// //Fecth绛栫暐瀹氫箟
	// @Fetch(FetchMode.SUBSELECT)
	// //闆嗗悎鎸塱d鎺掑簭
	// @OrderBy("roleid ASC")
	// public List<TAcRole> getRoleList() {
	// return roleList;
	// }
	//
	// public void setRoleList(List<TAcRole> roleList) {
	// this.roleList = roleList;
	// }
	// @Transient
	// @JsonIgnore
	// public String getRoleNames() {
	// return ConvertUtils.convertElementPropertyToString(roleList, "rolename",
	// ", ");
	// }

	@Column(name = "IDCARD", length = 20)
	public String getIdcard() {
		return this.idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	@Column(name = "EXTF1", length = 100)
	public String getExtf1() {
		return this.extf1;
	}

	public void setExtf1(String extf1) {
		this.extf1 = extf1;
	}

	@Column(name = "EXTF2", length = 100)
	public String getExtf2() {
		return this.extf2;
	}

	public void setExtf2(String extf2) {
		this.extf2 = extf2;
	}

	@Column(name = "EXTF3", length = 100)
	public String getExtf3() {
		return this.extf3;
	}

	public void setExtf3(String extf3) {
		this.extf3 = extf3;
	}

	@Column(name = "EXTF4", length = 100)
	public String getExtf4() {
		return this.extf4;
	}

	public void setExtf4(String extf4) {
		this.extf4 = extf4;
	}
	
	@Column(name = "EXTF5", length = 500)
	public String getExtf5() {
		return this.extf5;
	}

	public void setExtf5(String extf5) {
		this.extf5 = extf5;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PWD_MODIFY_TIME", length = 7)
	public Date getPwdModifyTime() {
		return pwdModifyTime;
	}

	public void setPwdModifyTime(Date pwdModifyTime) {
		this.pwdModifyTime = pwdModifyTime;
	}

	@Column(name = "PWD_WRONG_COUNT", precision = 10, scale = 0)
	public Long getPwdWrongCount() {
		return pwdWrongCount;
	}

	public void setPwdWrongCount(Long pwdWrongCount) {
		this.pwdWrongCount = pwdWrongCount;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LOCK_TIME", length = 7)
	public Date getLockTime() {
		return lockTime;
	}
	
	public void setLockTime(Date lockTime) {
		this.lockTime = lockTime;
	}
	
	/***********************************************************************************************/
	
	@Column(name = "PASSPORT", length = 20)
	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}
	@Column(name = "PASSPORTEN", length = 20)
	public String getPassportEn() {
		return passportEn;
	}

	public void setPassportEn(String passportEn) {
		this.passportEn = passportEn;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PASSPORTVAL", length = 7)
	public Date getPassportVal() {
		return passportVal;
	}

	public void setPassportVal(Date passportVal) {
		this.passportVal = passportVal;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PASSPORTEND", length = 7)
	public Date getPassportEnd() {
		return passportEnd;
	}

	public void setPassportEnd(Date passportEnd) {
		this.passportEnd = passportEnd;
	}
	@Column(name = "NATIONALITY", length = 20)
	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	@Column(name = "OTHERNUM", length = 20)
	public String getOtherNum() {
		return otherNum;
	}

	public void setOtherNum(String otherNum) {
		this.otherNum = otherNum;
	}
	@Column(name = "EMPLOYEEID", length = 20)//员工编号
	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	
	@Column(name = "DUTY", length = 20)//职务
	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}
	@Column(name = "MILEAGE", length = 200)//里程卡号
	public String getMileage() {
		return mileage;
	}

	public void setMileage(String mileage) {
		this.mileage = mileage;
	}
	@Column(name = "HXZ", length = 20)
	public String getHxz() {
		return hxz;
	}

	public void setHxz(String hxz) {
		this.hxz = hxz;
	}
	@Column(name = "HXZNAME",  length = 40)
	public String getHxzname() {
		return hxzname;
	}

	public void setHxzname(String hxzname) {
		this.hxzname = hxzname;
	}

	@Column(name = "GATXZ",  length = 20)
	public String getGatxz() {
		return gatxz;
	}

	public void setGatxz(String gatxz) {
		this.gatxz = gatxz;
	}

	@Column(name = "GATXZNAME", length = 40)
	public String getGatxzname() {
		return gatxzname;
	}

	public void setGatxzname(String gatxzname) {
		this.gatxzname = gatxzname;
	}
	
	@Column(name = "PUBLICGATXZ", length = 20)
	public String getPublicgatxz() {
		return publicgatxz;
	}

	public void setPublicgatxz(String publicgatxz) {
		this.publicgatxz = publicgatxz;
	}
	
	@Column(name = "PUBLICGATXZNAME", length = 40)
	public String getPublicgatxzname() {
		return publicgatxzname;
	}

	public void setPublicgatxzname(String publicgatxzname) {
		this.publicgatxzname = publicgatxzname;
	}

	@Column(name = "JGZ",  length = 20)
	public String getJgz() {
		return jgz;
	}

	public void setJgz(String jgz) {
		this.jgz = jgz;
	}

	@Column(name = "JGZNAME", length = 40)
	public String getJgzname() {
		return jgzname;
	}

	public void setJgzname(String jgzname) {
		this.jgzname = jgzname;
	}

	@Column(name = "RTZ", length = 20)
	public String getRtz() {
		return rtz;
	}

	public void setRtz(String rtz) {
		this.rtz = rtz;
	}

	@Column(name = "RTZNAME",  length = 40)
	public String getRtzname() {
		return rtzname;
	}

	public void setRtzname(String rtzname) {
		this.rtzname = rtzname;
	}

	@Column(name = "DLWLTXZ",  length = 20)
	public String getDlwltxz() {
		return dlwltxz;
	}

	public void setDlwltxz(String dlwltxz) {
		this.dlwltxz = dlwltxz;
	}
    @Column(name = "ALLOWLOGIN", length = 2)
	public String getAllowlogin() {
		return allowlogin;
	}

	public void setAllowlogin(String allowlogin) {
		this.allowlogin = allowlogin;
	}
	@Column(name = "DLWLTXZNAME", length = 40)
	public String getDlwltxzname() {
		return dlwltxzname;
	}

	public void setDlwltxzname(String dlwltxzname) {
		this.dlwltxzname = dlwltxzname;
	}
	
	@Column(name = "INVOICE_AGING_DATE", length = 5)
	public String getInvoiceagingdate() {
		return invoiceagingdate;
	}

	public void setInvoiceagingdate(String invoiceagingdate) {
		this.invoiceagingdate = invoiceagingdate;
	}
	@Column(name = "DEPNO", length = 40)
	public String getDepno() {
		return depno;
	}

	public void setDepno(String depno) {
		this.depno = depno;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PASSPORT_BIRTHDAY", length = 7)
	public Date getPassportbyrthday() {
		return passportbyrthday;
	}

	public void setPassportbyrthday(Date passportbyrthday) {
		this.passportbyrthday = passportbyrthday;
	}
	
	
	@Column(name = "ISBILLER", length = 2)
	public String getIsbiller() {
		return isbiller;
	}

	public void setIsbiller(String isbiller) {
		this.isbiller = isbiller;
	}
    
	@Column(name = "PASSPORTEN_PUBLIC", length = 100)
   public String getPassportenpublic() {
		return passportenpublic;
	}

	public void setPassportenpublic(String passportenpublic) {
		this.passportenpublic = passportenpublic;
	}
	@Column(name = "PASSPORT_PUBLIC", length = 100)
	public String getPassportpublic() {
		return passportpublic;
	}

	public void setPassportpublic(String passportpublic) {
		this.passportpublic = passportpublic;
	}
	@Column(name = "PASSPORT_BIRTHDAY_PUBLIC", length = 7)
	public Date getPassportbirthdaypublic() {
		return passportbirthdaypublic;
	}

	public void setPassportbirthdaypublic(Date passportbirthdaypublic) {
		this.passportbirthdaypublic = passportbirthdaypublic;
	}
	@Column(name = "PASSPORTEND_PUBLIC", length = 7)
	public Date getPassportendpublic() {
		return passportendpublic;
	}

	public void setPassportendpublic(Date passportendpublic) {
		this.passportendpublic = passportendpublic;
	}
	@Column(name = "NATIONALITY_PUBLIC", length =40)
	public String getNationalitypublic() {
		return nationalitypublic;
	}
	
	

	@Column(name = "LAST_LOGIN_TIME", length =40)
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public void setNationalitypublic(String nationalitypublic) {
		this.nationalitypublic = nationalitypublic;
	}
	@Column(name = "SUBSCRIBEORG", length =40)
    public String getSubscribeorg() {
		return subscribeorg;
	}
	public void setSubscribeorg(String subscribeorg) {
		this.subscribeorg = subscribeorg;
	}
	
	@Column(name = "PLANE", length =40)
    public String getPlane() {
		return plane;
	}

	public void setPlane(String plane) {
		this.plane = plane;
	}
	
	@Column(name = "VERIFYORG", length =40)
   public String getVerifyorg() {
		return verifyorg;
	}

	public void setVerifyorg(String verifyorg) {
		this.verifyorg = verifyorg;
	}
	
	@Column(name = "NATIONALITY_TWO", length =40)
	public String getNationalityTwo() {
		return nationalityTwo;
	}

	public void setNationalityTwo(String nationalityTwo) {
		this.nationalityTwo = nationalityTwo;
	}

	@Column(name = "NATIONALITY_TWO_PUBLIC", length =40)
	public String getNationalityTwoPublic() {
		return nationalityTwoPublic;
	}

	public void setNationalityTwoPublic(String nationalityTwoPublic) {
		this.nationalityTwoPublic = nationalityTwoPublic;
	}
	
	
	
	
	@Column(name = "HIGHEST_DISCOUNT", precision = 10)
	public Double getHighestDiscount() {
		return highestDiscount;
	}

	public void setHighestDiscount(Double highestDiscount) {
		this.highestDiscount = highestDiscount;
	}
	

/***********************************************************************************************/
	@Override
	public String toString() {
		return new GsonBuilder().create().toJson(this);
	}
	@Column(name = "BALANCE_REMIND", length = 2)
	public String getBalanceRemind() {
		return balanceRemind;
	}
	
	public void setBalanceRemind(String balanceRemind) {
		this.balanceRemind = balanceRemind;
	}
}