package com.viptrip.warning.action;

//import com.google.gson.Gson;
//import com.viptrip.app.appInterface.common.base.RespBase;
//import com.viptrip.base.action.BaseAction;
//import com.viptrip.base.service.Page;
//import com.viptrip.base.util.Constants;
//import com.viptrip.base.util.StringUtil;
//import com.viptrip.base.util.Struts2Util;
//import com.viptrip.common.entity.TAcOrg;
//import com.viptrip.common.entity.TAcRole;
//import com.viptrip.common.entity.TAcUser;
//import com.viptrip.warning.entity.PayRevise;
//import com.viptrip.warning.entity.PayWarning;
//import com.viptrip.warning.entity.PayWarningPerson;
//import com.viptrip.warning.resource.MEnum;
//import com.viptrip.warning.service.WarningManager;
//import com.viptrip.warning.util.GlobalWarningUtil;
//import com.viptrip.warning.vo.MSM;
//import com.viptrip.warning.vo.QueryLimitationVo;
//import com.viptrip.warning.vo.QueryWaringVo;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.regex.Pattern;

public class WarningAction /*extends BaseAction*/{
	/*private WarningManager manager;

	private Page page;

	private Integer globalWarningStatus;
	private Double globalWarningValue;
	private String orgname;
	private Integer type;
	private Long orgid;
	private Long id;
	private List<PayWarningPerson> persons;
	private String uname;
	private Double max;
	private List<Integer> notify;
	private PayWarning warning;

	private boolean hasGlobalOP;//全局操作权限
	private boolean hasOrgWarningOP;//企业预警管理操作权限
	private boolean hasOrgLimitOP;//企业额度管理操作权限
	private boolean hasReviseRequestOP;//月结申请权限
	private boolean hasReviseApproveOP;//月结审批权限

	private String invalidDate;//添加临时额度时 失效日期
	private Integer temp;//为1时表示添加的是临时额度
	private Integer addType;//1为增加额度 2为减少额度
	private String comment;//说明
	private Integer result;//审批结果
	private PayRevise toBeApproval;
	private Map<Long,PayRevise> map = new HashMap<>();;

	private String[] currentPosition = new String[2];

	*//**
	 * 预警管理页面
	 * @return
	 *//*
	public String manage(){

		currentPosition[0] = "客户信息维护";
		currentPosition[1] = "临额提醒管理";

		queryManageList();

		hasPermission();

		return SUCCESS;
	}

	*//**
	 * 额度管理页面
	 * @return
	 *//*
	public String queryLimit(){
		currentPosition[0] = "客户信息维护";
		currentPosition[1] = "企业额度管理";

		manager.initPayLimitationData();//初始化额度管理表数据

		queryLimitationList();

		hasPermission();

		List<PayRevise> payRevises = manager.queryPayRevise(orgid, 1);
		if(null!=payRevises && payRevises.size()>0){

			for (PayRevise pr:payRevises
				 ) {
				if(null!=pr){
					map.put(pr.getOrgid(),pr);
				}
			}
		}

		return SUCCESS;
	}

	*//**
	 * 设置全局预警值
	 *//*
	public void globalSet(){
		RespBase result = new RespBase();
		if(null!=globalWarningValue&&globalWarningValue>0){
			if(null==globalWarningStatus){
				globalWarningStatus = 0;
			}
			GlobalWarningUtil.setGlobalWarningStatus(globalWarningStatus);
			GlobalWarningUtil.setGlobalWarningVal(globalWarningValue);
			result.success();
		}else{
			result.setMsg("预警值设置有误");
		}
		Struts2Util.renderJson(result);
	}

	private void queryManageList(){

		globalWarningStatus = GlobalWarningUtil.getGlobalWarningStatus();
		globalWarningValue = GlobalWarningUtil.getGlobalWarningVal();


		page = manager.queryOrgWarning(orgname,type,page);
		page.setTotalPages(page.getTotalPages());
		List result = page.getResult();
		if(null!=result && result.size()>0){
			List<QueryWaringVo> qlvs = new ArrayList<>();
			for(int i=0;i<result.size();i++){
				Object row = result.get(i);
				if(null!=row&&row.getClass().isArray()){
					Object[] objs = (Object[]) row;
					QueryWaringVo vo = new QueryWaringVo();
					if(objs[0]!=null && objs[0].getClass()== BigDecimal.class){
						BigDecimal orgid = (BigDecimal) objs[0];
						vo.setOrgid(orgid.longValue());
					}
					vo.setOrgname(objs[1].toString());
					if(objs[2]!=null && objs[2].getClass()== BigDecimal.class){
						BigDecimal orgid = (BigDecimal) objs[2];
						vo.setType(orgid.intValue());
					}

					if(objs[3]!=null && objs[3].getClass()== BigDecimal.class){
						BigDecimal orgid = (BigDecimal) objs[3];
						vo.setWarningValue(orgid.doubleValue());
					}
					qlvs.add(vo);
				}
			}
			page.setResult(qlvs);
		}
	}

	private void queryLimitationList(){

		page = manager.queryOrgLimitation(orgname,type,page);
		page.setTotalPages(page.getTotalPages());
		List result = page.getResult();
		if(null!=result && result.size()>0){
			List<QueryLimitationVo> qlvs = new ArrayList<>();
			for(int i=0;i<result.size();i++){
				Object row = result.get(i);
				if(null!=row&&row.getClass().isArray()){
					Object[] objs = (Object[]) row;
					QueryLimitationVo vo = new QueryLimitationVo();
					if(objs[0]!=null && objs[0].getClass()== BigDecimal.class){
						BigDecimal orgid = (BigDecimal) objs[0];
						vo.setOrgid(orgid.longValue());
					}
					vo.setOrgname(objs[1].toString());
					if(objs[2]!=null && objs[2].getClass()== BigDecimal.class){
						BigDecimal orgid = (BigDecimal) objs[2];
						vo.setCredit(orgid.doubleValue());
					}
					if(objs[3]!=null && objs[3].getClass()== BigDecimal.class){
						BigDecimal orgid = (BigDecimal) objs[3];
						vo.setType(orgid.intValue());
					}
					if(objs[4]!=null && objs[4].getClass()== BigDecimal.class){
						BigDecimal orgid = (BigDecimal) objs[4];
						vo.setLimitationValue(orgid.doubleValue());
					}
					qlvs.add(vo);
				}
			}
			page.setResult(qlvs);
		}
	}

	*//**
	 * 企业临额预警页面
	 * @return
	 *//*
	public String orgManage(){

		//page = manager.getCompanyUser(orgid, uname, page);

		//System.out.println("==============="+new Gson().toJson(page));
		warning = manager.getWarning(orgid, type);
		if(null!=warning){
			persons = manager.getWarningPersons(orgid,warning.getMethod());
			globalWarningStatus = warning.getState();
			globalWarningValue = warning.getWarningValue();

		}
		if(2==type){
			max = manager.getInitialCredit(orgid);
		}

		return SUCCESS;
	}

	*//**
	 * 设置企业临额预警
	 *//*
	public void orgSave(){
		RespBase respBase = new RespBase();
		try {
			Integer method = 0;
			if(null!=notify && notify.size()>0){
				if(notify.size()==1){
					method = notify.get(0);
				}else{
					for(Integer nty:notify){
						if(null!=nty){
							method += nty;
						}
					}
				}
			}else{
				method = 1;
			}
			Integer warningStatus = 0;
			if(null!=globalWarningStatus){
				warningStatus = globalWarningStatus;
			}
			Double warningValue = 0d;
			if(null!=globalWarningValue){
				warningValue = globalWarningValue;
			}

			manager.setWarning(orgid,warningValue,method,warningStatus,persons);
			respBase.success();
		}catch (Exception e){
			logger.error(StringUtil.getExceptionStr(e));
			respBase.setMsg("保存出错");
		}
		Struts2Util.renderJson(respBase);
	}

	*//**
	 * 查询可供选择的用户
	 * @return
	 *//*
	public String orgPerson(){

		page = manager.getCompanyUser(orgid, uname, page);
		return SUCCESS;
	}


	*//**
	 * 预付款充值页面
	 * @return
	 *//*
	public String depositAdd(){

		return SUCCESS;
	}

	*//**
	 * 预付款充值
	 * @return
	 *//*
	public void depositSave(){
		RespBase result = new RespBase();
		MSM msm = manager.changeLimitation(orgid,globalWarningValue, MEnum.Otype.手动调额,null);
		if(null!=msm){
			if(msm.isState()){
				result.success();
			}else{
				result.setMsg(msm.getMsg());
			}
		}
		Struts2Util.renderJson(result);
	}

	*//**
	 * 额度添加页面
	 * @return
	 *//*
	public String limitAdd(){

		return SUCCESS;
	}

	*//**
	 * 额度调整充值
	 * @return
	 *//*
	public void limitSave(){
		RespBase result = new RespBase();
		MEnum.Otype type = MEnum.Otype.手动调额;
		if(2==addType){
			globalWarningValue = -globalWarningValue;
		}
		if(null!=temp && 1==temp){
			type = MEnum.Otype.临时调额;
		}else{
			invalidDate = null;
		}
		MSM msm = manager.changeLimitation(orgid, globalWarningValue, type, invalidDate);
		if(null!=msm){
			if(msm.isState()){
				result.success();
			}else{
				result.setMsg(msm.getMsg());
			}
		}
		Struts2Util.renderJson(result);
	}

	*//**
	 * 设置初始额度页面
	 * @return
	 *//*
	public String toInitalLimit(){
		return SUCCESS;
	}

	*//**
	 * 设置初始额度
	 *//*
	public void setInitalLimit(){
		RespBase result = new RespBase();
		try {
			manager.setInitalLimit(orgid,globalWarningValue);
			result.success();
		}catch (Exception e){
			logger.error(StringUtil.getExceptionStr(e));
		}
		Struts2Util.renderJson(result);
	}

	*//**
	 * 月结回正
	 *//*
	public void reviseLimitation(){
		RespBase resp = new RespBase();
		try {
			manager.reviseMonLimitation(orgid);
			resp.success();
		}catch (Exception e){
			logger.error(StringUtil.getExceptionStr(e));
		}
		Struts2Util.renderJson(resp);
	}

	*//**
	 * 回款申请页面
	 * @return
	 *//*
	public String toRequest(){


		return SUCCESS;
	}

	*//**
	 * 回款申请
	 *//*
	public void saveRequest(){
		RespBase resp = new RespBase();
		try {
			//manager.reviseMonLimitation(orgid);
			TAcUser user = (TAcUser) getSessionAttribute(Constants.USER);
			if(null==orgid){
				resp.setMsg("企业id不能为空");
			}else if(StringUtil.isEmpty(comment)){
				resp.setMsg("说明不能为空");
			}else {

				List<PayRevise> payRevises = manager.queryPayRevise(orgid, 1);
				if(payRevises!=null && payRevises.size()>0){
					resp.setMsg("已经存在该企业的回款申请");
				}else{
					manager.saveRequest(orgid, user, comment);
					resp.success();
				}
			}
		}catch (Exception e){
			logger.error(StringUtil.getExceptionStr(e));
			resp.setMsg("异常");
		}
		Struts2Util.renderJson(resp);
	}

	*//**
	 * 回款审批页面
	 * @return
	 *//*
	public String toApproval(){
		if(null!=id){
			toBeApproval = manager.queryPayReviseById(id);
		}
		return SUCCESS;
	}

	*//**
	 * 回款审批
	 *//*
	public void saveApproval(){
		RespBase resp = new RespBase();
		try {
			//manager.reviseMonLimitation(orgid);
			TAcUser user = (TAcUser) getSessionAttribute(Constants.USER);
			if(null==id){
				resp.setMsg("审核id不能为空");
			}else if(result==null){
				resp.setMsg("审批结果不能为空");
			}else if(0==result && StringUtil.isEmpty(comment)){
				resp.setMsg("审批拒绝时，说明不能为空");
			}else{
				PayRevise payRevises = manager.queryPayReviseById(id);
				if(payRevises==null){
					resp.setMsg("找不到对应的回款申请");
				}else {
					manager.saveApproval(payRevises, user, result, comment);
					resp.success();
				}
			}
		}catch (Exception e){
			logger.error(StringUtil.getExceptionStr(e));
			resp.setMsg("异常");
		}
		Struts2Util.renderJson(resp);
	}

	*//**
	 * 是否有权限
	 *//*
	private void hasPermission(){
		TAcUser user = (TAcUser) getSessionAttribute(Constants.USER);
		List<TAcRole> roles = (List<TAcRole>) getSessionAttribute(Constants.ROLES);
		hasGlobalOP = hasRole(user,roles,Optype.GLOBAL);
		hasOrgWarningOP = hasRole(user,roles,Optype.WARNING);
		hasOrgLimitOP = hasRole(user,roles,Optype.LIMIT);
		hasReviseRequestOP = hasRole(user,roles,Optype.REVISE_REQUEST);
		hasReviseApproveOP = hasRole(user,roles,Optype.REVISE_APPROVAL);
	}

	*//**
	 * 全局权限
	 * @param user
	 * @param roles
	 * @return
	 *//*
	private boolean hasRole(TAcUser user,List<TAcRole> roles,Optype type){
		boolean result = false;
		String reg = null;
		switch (type) {
			case GLOBAL://全局管理
				reg = "(客维|超级管理员)+";
				break;
			case WARNING://预警管理
				reg = "(客维|超级管理员)+";
				break;
			case LIMIT://额度管理
				reg = "(财务|客维管理|超级管理员)+";
				break;
			case REVISE_REQUEST://回款申请
				reg = "(客维|超级管理员)+";
				break;
			case REVISE_APPROVAL://回款审批
				reg = "(财务|超级管理员)+";
				break;
		}
		if(null!=roles && roles.size()>0){
			for (TAcRole role:roles){
				String rolename = role.getRolename();
				result = Pattern.compile(reg).matcher(rolename).find();
				if(result){
					break;
				}
			}
		}
		return result;
	}

	private static enum Optype{
		GLOBAL,WARNING,LIMIT,REVISE_REQUEST,REVISE_APPROVAL
	}


	@Override
	protected void prepareModel() throws Exception {

	}

	@Override
	public Object getModel() {
		return null;
	}

	public void setManager(WarningManager manager) {
		this.manager = manager;
	}

	public WarningManager getManager() {
		return manager;
	}

	public String[] getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(String[] currentPosition) {
		this.currentPosition = currentPosition;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public Integer getGlobalWarningStatus() {
		return globalWarningStatus;
	}

	public void setGlobalWarningStatus(Integer globalWarningStatus) {
		this.globalWarningStatus = globalWarningStatus;
	}

	public Double getGlobalWarningValue() {
		return globalWarningValue;
	}

	public void setGlobalWarningValue(Double globalWarningValue) {
		this.globalWarningValue = globalWarningValue;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getOrgid() {
		return orgid;
	}

	public void setOrgid(Long orgid) {
		this.orgid = orgid;
	}

	public List<PayWarningPerson> getPersons() {
		return persons;
	}

	public void setPersons(List<PayWarningPerson> persons) {
		this.persons = persons;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public Double getMax() {
		return max;
	}

	public void setMax(Double max) {
		this.max = max;
	}

	public List<Integer> getNotify() {
		return notify;
	}

	public void setNotify(List<Integer> notify) {
		this.notify = notify;
	}

	public PayWarning getWarning() {
		return warning;
	}

	public void setWarning(PayWarning warning) {
		this.warning = warning;
	}

	public boolean isHasGlobalOP() {
		return hasGlobalOP;
	}

	public void setHasGlobalOP(boolean hasGlobalOP) {
		this.hasGlobalOP = hasGlobalOP;
	}

	public boolean isHasOrgWarningOP() {
		return hasOrgWarningOP;
	}

	public void setHasOrgWarningOP(boolean hasOrgWarningOP) {
		this.hasOrgWarningOP = hasOrgWarningOP;
	}

	public boolean isHasOrgLimitOP() {
		return hasOrgLimitOP;
	}

	public void setHasOrgLimitOP(boolean hasOrgLimitOP) {
		this.hasOrgLimitOP = hasOrgLimitOP;
	}

	public String getInvalidDate() {
		return invalidDate;
	}

	public void setInvalidDate(String invalidDate) {
		this.invalidDate = invalidDate;
	}

	public Integer getTemp() {
		return temp;
	}

	public void setTemp(Integer temp) {
		this.temp = temp;
	}

	public Integer getAddType() {
		return addType;
	}

	public void setAddType(Integer addType) {
		this.addType = addType;
	}

	public boolean isHasReviseRequestOP() {
		return hasReviseRequestOP;
	}

	public void setHasReviseRequestOP(boolean hasReviseRequestOP) {
		this.hasReviseRequestOP = hasReviseRequestOP;
	}

	public boolean isHasReviseApproveOP() {
		return hasReviseApproveOP;
	}

	public void setHasReviseApproveOP(boolean hasReviseApproveOP) {
		this.hasReviseApproveOP = hasReviseApproveOP;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public PayRevise getToBeApproval() {
		return toBeApproval;
	}

	public void setToBeApproval(PayRevise toBeApproval) {
		this.toBeApproval = toBeApproval;
	}

	public Map<Long, PayRevise> getMap() {
		return map;
	}

	public void setMap(Map<Long, PayRevise> map) {
		this.map = map;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}*/
}
