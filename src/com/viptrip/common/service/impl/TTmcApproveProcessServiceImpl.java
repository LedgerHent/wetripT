package com.viptrip.common.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.viptrip.base.common.support.PageObject;
import com.viptrip.common.constants.TmcConstant;
import com.viptrip.common.dao.TTmcApproveProcessDao;
import com.viptrip.common.entity.TTmcApproveFlow;
import com.viptrip.common.entity.TTmcApproveProcess;
import com.viptrip.common.entity.TTmcApproveProcessLable;
import com.viptrip.common.entity.TTmcApproveProcessLableId;
import com.viptrip.common.entity.TTmcApproveRecord;
import com.viptrip.common.entity.TTmcApproveRecordId;
import com.viptrip.common.entity.TTmcApproveTraveller;
import com.viptrip.common.entity.TTmcApproveTravellerId;
import com.viptrip.common.model.ApproveAuditModel;
import com.viptrip.common.model.ApproveAuditorModel;
import com.viptrip.common.model.ApproveLevelModel;
import com.viptrip.common.model.ApproveProcessTravellerMode;
import com.viptrip.common.model.BookerInfo;
import com.viptrip.common.model.Request_CallbackApproval;
import com.viptrip.common.model.Request_CreateTTmcApproveProcess;
import com.viptrip.common.model.Request_DeleteApprovalProcess;
import com.viptrip.common.model.Request_GetApprovalProcess;
import com.viptrip.common.model.Request_ListApprovalPending;
import com.viptrip.common.model.Request_UpdateApproveProcess;
import com.viptrip.common.model.Response_CallbackApproval;
import com.viptrip.common.model.Response_GetApprovalProcess;
import com.viptrip.common.model.Response_ListApprovalPending;
import com.viptrip.common.model.TTmcApproveProcessInfo;
import com.viptrip.common.service.TTmcApproveProcessService;
import com.viptrip.common.vo.TTmcApproveProcessVO;
import com.viptrip.common.vo.sqlmap.ApproveAuditVO;
import com.viptrip.hotel.service.IComService;
import com.viptrip.hotelHtml5.util.EncryptHelper;
import com.viptrip.hotelHtml5.util.HttpUtils;
import com.viptrip.resource.Const;
import com.viptrip.util.PropertiesUtils;
import com.viptrip.wetrip.model.Request_GetApprovalDetail;
import com.viptrip.wetrip.model.policy.Res_ApprovalsDetail;

@Service
@Transactional
public class TTmcApproveProcessServiceImpl implements TTmcApproveProcessService {
	private static Logger logger = LoggerFactory.getLogger(TTmcApproveProcessServiceImpl.class);
	@Resource
	private TTmcApproveProcessDao tTmcApproveProcessDao;
	@Autowired
	private IComService iComService;
	
	public String saveTTmcApproveProcess(Request_CreateTTmcApproveProcess request_CreateTTmcApproveProcess) {
		TTmcApproveProcessVO tTmcApproveProcessVO=new TTmcApproveProcessVO();
		BeanUtils.copyProperties(request_CreateTTmcApproveProcess.data, tTmcApproveProcessVO);
		tTmcApproveProcessVO.setApprovePolicyId(request_CreateTTmcApproveProcess.approvalId);
		tTmcApproveProcessVO.setBookerId(request_CreateTTmcApproveProcess.data.booker.id);
		tTmcApproveProcessVO.setBookerName(request_CreateTTmcApproveProcess.data.booker.name);
		tTmcApproveProcessVO.setBookerEmail(request_CreateTTmcApproveProcess.data.booker.email);
		tTmcApproveProcessVO.setBookerMobile(request_CreateTTmcApproveProcess.data.booker.mobile);
		tTmcApproveProcessVO.setEnterpId(request_CreateTTmcApproveProcess.data.orgId);
		tTmcApproveProcessVO.setEnterpName(request_CreateTTmcApproveProcess.data.orgName);
		tTmcApproveProcessVO.setAomunt(request_CreateTTmcApproveProcess.data.amount);
		tTmcApproveProcessVO.setState(request_CreateTTmcApproveProcess.data.status);
		List<ApproveProcessTravellerMode> travellers=request_CreateTTmcApproveProcess.data.traveller;
		List<TTmcApproveTraveller> travellerList=null;
		if (travellers !=null && travellers.size()>0) {
			travellerList=new ArrayList<TTmcApproveTraveller>();
			for (int i=0;i<travellers.size();i++) {
				TTmcApproveTraveller tempTTmcApproveTraveller=new TTmcApproveTraveller();				
				tempTTmcApproveTraveller.setTravellerId(travellers.get(i).id);
				tempTTmcApproveTraveller.setTravellerName(travellers.get(i).name);
				tempTTmcApproveTraveller.setTravellerEmail(travellers.get(i).email);
				tempTTmcApproveTraveller.setTravellerMobile(travellers.get(i).mobile);
				tempTTmcApproveTraveller.setOrgId(travellers.get(i).orgId);
				tempTTmcApproveTraveller.setOrgName(travellers.get(i).orgName);
				tempTTmcApproveTraveller.setCostId(travellers.get(i).costId);
				tempTTmcApproveTraveller.setCostName(travellers.get(i).costName);
				travellerList.add(tempTTmcApproveTraveller);
			}
			tTmcApproveProcessVO.setTraveller(travellerList);			
		}
		String approveProcessId=this.saveTTmcApproveProcess(tTmcApproveProcessVO);
		return approveProcessId;		
	}
	
	public String saveTTmcApproveProcess(TTmcApproveProcessVO tTmcApproveProcessVO) {
		TTmcApproveProcess tTmcApproveProcess=new TTmcApproveProcess();
		BeanUtils.copyProperties(tTmcApproveProcessVO, tTmcApproveProcess);	
		String jsonStr=JSONObject.toJSONString(tTmcApproveProcess);
		logger.info("saveTTmcApproveProcess0:"+jsonStr);		
		//System.out.println("saveTTmcApproveProcess0:"+jsonStr);
		tTmcApproveProcess.setProcessState(TmcConstant.APPROVE_STATE.WAITING);
		tTmcApproveProcess.setProcessLevel(1);
		tTmcApproveProcessDao.executeSave(tTmcApproveProcess);		
		long approveProcessId=tTmcApproveProcess.getApproveProcessId();
		logger.info("saveTTmcApproveProcess1:"+approveProcessId);
		//System.out.println("saveTTmcApproveProcess1:"+approveProcessId);
		
		Request_GetApprovalDetail request_GetApprovalDetail=new Request_GetApprovalDetail();
		request_GetApprovalDetail.approvalId=Integer.valueOf(tTmcApproveProcess.getApprovePolicyId().toString());
		Res_ApprovalsDetail res_ApprovalsDetail=iComService.getApprovalDetail(request_GetApprovalDetail);
		logger.info("saveTTmcApproveProcess2:"+JSONObject.toJSONString(res_ApprovalsDetail));
		for (int i=1;i<3;i++) {			
			TTmcApproveProcessLable tTmcApproveProcessLable=new TTmcApproveProcessLable();
			TTmcApproveProcessLableId tTmcApproveProcessLableId=new TTmcApproveProcessLableId();
			tTmcApproveProcessLableId.setApproveProcessId(approveProcessId);
			tTmcApproveProcessLableId.setLableType(i);
			if (i==1) {
				tTmcApproveProcessLableId.setLableValue(tTmcApproveProcessVO.businessType);
			} else if (i==2) {
				tTmcApproveProcessLableId.setLableValue(tTmcApproveProcessVO.approvalType);
			}
			tTmcApproveProcessLable.setId(tTmcApproveProcessLableId);			
			tTmcApproveProcessDao.executeSave(tTmcApproveProcessLable);
		}
		if (tTmcApproveProcessVO.traveller != null && tTmcApproveProcessVO.traveller.size()>0) {	
			int j=1;
			for (TTmcApproveTraveller tTmcApproveTraveller:tTmcApproveProcessVO.traveller) {				
				TTmcApproveTravellerId tTmcApproveTravellerId=new TTmcApproveTravellerId();
				tTmcApproveTravellerId.setApproveProcessId(approveProcessId);
				tTmcApproveTravellerId.setFlowid(j);
				tTmcApproveTraveller.setId(tTmcApproveTravellerId);
				tTmcApproveProcessDao.executeSave(tTmcApproveTraveller);
				j++;
			}			
		}		
		return String.valueOf(approveProcessId);		
	}   
	
	
    public String updateApproveProcess(Request_UpdateApproveProcess updateApproveProcess) {
    	logger.info("updateApproveProcess request:"+JSONObject.toJSONString(updateApproveProcess));
    	Request_GetApprovalProcess requestGetApprovalProcess=new Request_GetApprovalProcess();
    	requestGetApprovalProcess.orderno=updateApproveProcess.orderno;
    	requestGetApprovalProcess.businessType=updateApproveProcess.businessType;
    	Response_GetApprovalProcess response_GetApprovalProcess= this.getApprovalProcess(requestGetApprovalProcess);
    	Response_GetApprovalProcess recentlyApprovalPending=produceRecentlyApprovalPending(response_GetApprovalProcess);
    			//
    	if (recentlyApprovalPending!=null && (recentlyApprovalPending.state.intValue()==TmcConstant.APPROVE_STATE.WAITING.intValue() || 
    			recentlyApprovalPending.state.intValue()==TmcConstant.APPROVE_STATE.APPROVING.intValue())) {
    		if (recentlyApprovalPending.approvals!=null && recentlyApprovalPending.approvals.size()>0) {
    			ApproveLevelModel approveLevelModel=recentlyApprovalPending.approvals.get(0);
    			if (approveLevelModel.auditors!=null && approveLevelModel.auditors.size()>0) {
    				List<ApproveAuditModel> auditors=approveLevelModel.auditors;
    				for (ApproveAuditModel approveAuditModel:auditors) {
    					//判断实际审核人与理论审核人是否一致
    					if (updateApproveProcess.audit.id.longValue()==approveAuditModel.id.longValue()) {
    						List<TTmcApproveFlow> approveFlowList=tTmcApproveProcessDao.queryForList("select aFlow from "
    				    			+ "TTmcApproveFlow aFlow where aFlow.id.approvePolicyId=? and aFlow.id.approveLevel=? and aFlow.approveUserId=?", 
    				    			new Object[]{recentlyApprovalPending.id,approveLevelModel.approveLevel,approveAuditModel.id});
    						int flowid=0;
    						int approveFlowid=0;
    						if (approveFlowList!=null && approveFlowList.size()>0) {
    							flowid=approveFlowList.get(0).getId().getFlowid();
    						}
    						for(int j=0;j<response_GetApprovalProcess.approvals.size();j++) {
    							ApproveLevelModel tempApproveLevelModel=response_GetApprovalProcess.approvals.get(j);
    						//for(ApproveLevelModel tempApproveLevelModel:response_GetApprovalProcess.approvals) {
    							if (tempApproveLevelModel.approveLevel.intValue()==approveLevelModel.approveLevel.intValue()) {
    								int i=0;
    								for (int k=0;k<tempApproveLevelModel.auditors.size();k++) { 
    									ApproveAuditModel tempApproveAuditModel=tempApproveLevelModel.auditors.get(k);
    								//for (ApproveAuditModel tempApproveAuditModel:tempApproveLevelModel.auditors) {    									
    									if (tempApproveAuditModel.state!=null && (tempApproveAuditModel.state==11 || tempApproveAuditModel.state==12)) {
    										i++;
    									}
    									if (updateApproveProcess.audit.id.longValue()==tempApproveAuditModel.id.longValue()) {    										
    										tempApproveAuditModel=updateApproveProcess.audit; 
    										tempApproveLevelModel.auditors.set(k, updateApproveProcess.audit);
    										response_GetApprovalProcess.approvals.set(j, tempApproveLevelModel);
    									}
    								}
    								approveFlowid=i;
    							}
    						}
    						Response_GetApprovalProcess newRecentlyApprovalPending=produceRecentlyApprovalPending(response_GetApprovalProcess);
    						TTmcApproveProcess tTmcApproveProcess=(TTmcApproveProcess)tTmcApproveProcessDao.queryForObject("select aProcess from "
    				    			+ "TTmcApproveProcess aProcess where aProcess.orderno=?", new Object[]{updateApproveProcess.orderno});
    						tTmcApproveProcess.setProcessState(newRecentlyApprovalPending.state);
    						tTmcApproveProcess.setProcessLevel(newRecentlyApprovalPending.currentApproveLevel);
    						logger.info("tTmcApproveProcess:"+JSONObject.toJSONString(tTmcApproveProcess));
    						tTmcApproveProcessDao.executeSave(tTmcApproveProcess);
    						
    						TTmcApproveRecord tTmcApproveRecord=new TTmcApproveRecord(); 
    						TTmcApproveRecordId tTmcApproveRecordId=new TTmcApproveRecordId();
    						tTmcApproveRecordId.setApproveProcessId(tTmcApproveProcess.getApproveProcessId());
    						tTmcApproveRecordId.setApproveLevel(approveLevelModel.approveLevel);
    						tTmcApproveRecordId.setFlowid(flowid);
    						tTmcApproveRecord.setId(tTmcApproveRecordId);
    						tTmcApproveRecord.setApproveFlowid(approveFlowid+1);
    						tTmcApproveRecord.setApproveTime(new java.util.Date());
    						tTmcApproveRecord.setApproveType(updateApproveProcess.audit.type);
    						tTmcApproveRecord.setState(updateApproveProcess.audit.state);
    						tTmcApproveRecord.setMemo(updateApproveProcess.audit.memo);
    						tTmcApproveRecord.setOperatorId(updateApproveProcess.audit.id);
    						tTmcApproveRecord.setOperatorName(updateApproveProcess.audit.name);
    						tTmcApproveRecord.setOperatorMobile(updateApproveProcess.audit.mobile);
    						tTmcApproveRecord.setOperatorEmail(updateApproveProcess.audit.email); 
    						logger.info("tTmcApproveRecord:"+JSONObject.toJSONString(tTmcApproveRecord));
    						tTmcApproveProcessDao.executeSave(tTmcApproveRecord);
    						if (newRecentlyApprovalPending.state.intValue()==TmcConstant.APPROVE_STATE.PASS || 
    								newRecentlyApprovalPending.state.intValue()==TmcConstant.APPROVE_STATE.REFUSE) {
    							this.callbackApproval(updateApproveProcess.businessType, updateApproveProcess.orderno, newRecentlyApprovalPending);    							
    						}
    					}    					
    				}    				
    			}    			
    		}    		
    	}    	
    	return "updateApproveProcess";		
	}
    
    public String deleteApprovalProcess(Request_DeleteApprovalProcess request_DeleteApprovalProcess) {
    	TTmcApproveProcess tTmcApproveProcess=(TTmcApproveProcess)tTmcApproveProcessDao.queryForObject("select aProcess from "
    			+ "TTmcApproveProcess aProcess where aProcess.orderno=?", new Object[]{request_DeleteApprovalProcess.orderno});
		//tTmcApproveProcess.setProcessState(newRecentlyApprovalPending.state);
		//tTmcApproveProcess.setProcessLevel(newRecentlyApprovalPending.currentApproveLevel);
    	if (tTmcApproveProcess.getProcessState().intValue()==TmcConstant.APPROVE_STATE.APPROVING.intValue() || 
    			tTmcApproveProcess.getProcessState().intValue()==TmcConstant.APPROVE_STATE.WAITING.intValue()) {
    		tTmcApproveProcess.setProcessState(TmcConstant.APPROVE_STATE.CANCEL);
    		tTmcApproveProcessDao.executeSave(tTmcApproveProcess);
    	}
    	
    	
    	return "deleteApprovalProcess";        
    }

	@Override
	public Response_ListApprovalPending listApprovalPending(Request_ListApprovalPending requestApproval) {
		// TODO Auto-generated method stub,305347l
		Response_ListApprovalPending responseApproval=new Response_ListApprovalPending();
		StringBuffer queryHQLStr=new StringBuffer();
		if (requestApproval.state==null || requestApproval.state==0) {
			queryHQLStr.append("select aProcess from ");
			queryHQLStr.append("TTmcApproveProcess aProcess,TTmcApproveFlow aFlow where aProcess.approvePolicyId=aFlow.id.approvePolicyId and ");
		    queryHQLStr.append("aFlow.approveUserId=? and aProcess.processLevel=aFlow.id.approveLevel and (aProcess.processState=1 or aProcess.processState=2) and ");
			queryHQLStr.append("not exists (select 1 from TTmcApproveRecord aRecord  ");
			queryHQLStr.append("where aRecord.id.approveProcessId=aProcess.approveProcessId and aProcess.approvePolicyId=aFlow.id.approvePolicyId ");
			queryHQLStr.append("and aRecord.id.flowid=aFlow.id.flowid and aRecord.id.approveLevel=aFlow.id.approveLevel) ");		
		} else if (requestApproval.state==1)  {
			queryHQLStr.append("select new TTmcApproveProcess(aProcess.approveProcessId,aProcess.approvePolicyId,aProcess.businessType,"
					+ "aProcess.orderid,aProcess.orderno,aProcess.enterpId,aProcess.enterpName,aProcess.travelType,"
					+ "aProcess.payType,aProcess.productName,aProcess.productDetail,aProcess.extend,aProcess.aomunt,aProcess.bookTime,"
					+ "aProcess.bookerId,aProcess.bookerName,aProcess.bookerMobile,aProcess.bookerEmail,aProcess.state,"
					+ "aProcess.processLevel,aRecord.state) from ");
			//queryHQLStr.append("select aProcess from ");
			queryHQLStr.append("TTmcApproveProcess aProcess,TTmcApproveFlow aFlow,TTmcApproveRecord aRecord where aProcess.approvePolicyId=aFlow.id.approvePolicyId and ");
			queryHQLStr.append("aFlow.approveUserId=? and ");			
			queryHQLStr.append("aRecord.id.approveProcessId=aProcess.approveProcessId and aProcess.approvePolicyId=aFlow.id.approvePolicyId and ");
			queryHQLStr.append("aRecord.id.flowid=aFlow.id.flowid and aRecord.id.approveLevel=aFlow.id.approveLevel ");
		} else if (requestApproval.state==2)  {			
			queryHQLStr.append("select distinct aProcess from ");
			queryHQLStr.append("TTmcApproveProcess aProcess,TTmcApproveFlow aFlow,TTmcApproveRecord aRecord where aProcess.approvePolicyId=aFlow.id.approvePolicyId and ");
			queryHQLStr.append("aFlow.approveUserId=? and ");			
			queryHQLStr.append("aRecord.id.approveProcessId=aProcess.approveProcessId and aProcess.approvePolicyId=aFlow.id.approvePolicyId and ");
			queryHQLStr.append("aRecord.id.flowid=aFlow.id.flowid and aRecord.id.approveLevel=aFlow.id.approveLevel ");
		}		
		//List<TTmcApproveProcess> approvalPendingList= tTmcApproveProcessDao.queryForList(queryHQLStr,new Object[]{requestApproval.auditorId});		
		ArrayList parameterLists=new ArrayList<Object>();
		parameterLists.add(requestApproval.auditorId);
		if (StringUtils.isNotEmpty(requestApproval.orderno)) {
			queryHQLStr.append("and aProcess.orderno=? ");	
			parameterLists.add(requestApproval.orderno);			
		}
		
		if (StringUtils.isNotEmpty(requestApproval.travellerName) || StringUtils.isNotEmpty(requestApproval.costName)) {
			queryHQLStr.append("and exists (select 1 from TTmcApproveTraveller aTraveller where ");
			queryHQLStr.append("aProcess.approveProcessId=aTraveller.id.approveProcessId ");	
			if (StringUtils.isNotEmpty(requestApproval.travellerName)) {
				queryHQLStr.append(" and aTraveller.travellerName like ? ");
				parameterLists.add("%"+requestApproval.travellerName+"%");			
			}
			if (StringUtils.isNotEmpty(requestApproval.costName)) {
				queryHQLStr.append(" and aTraveller.costName like ? ");	
				parameterLists.add("%"+requestApproval.costName+"%");			
			}
			queryHQLStr.append(")");	
		}		
		queryHQLStr.append(" order by aProcess.bookTime desc");
		Object[] parameterObjects=new Object[parameterLists.size()];
		for (int i=0;i<parameterObjects.length;i++) {
			parameterObjects[i]=parameterLists.get(i);			
		}
		
		PageObject<TTmcApproveProcess> pageObjectt=tTmcApproveProcessDao.queryForPagList(requestApproval.page.index, requestApproval.page.size, queryHQLStr.toString(),parameterObjects);
		List<TTmcApproveProcess> approvalPendingList=pageObjectt.getPageList();
		if (approvalPendingList!= null && approvalPendingList.size()>0) {
			List<TTmcApproveProcessInfo> approveProcessInfoList =new ArrayList<TTmcApproveProcessInfo>();
			for (TTmcApproveProcess tempTTmcApproveProcess:approvalPendingList) {
				TTmcApproveProcessInfo tTmcApproveProcessInfo=new TTmcApproveProcessInfo();
				BookerInfo bookerInfo =new BookerInfo();
				BeanUtils.copyProperties(tempTTmcApproveProcess, tTmcApproveProcessInfo);
				tTmcApproveProcessInfo.setOrgId(tempTTmcApproveProcess.getEnterpId());
				tTmcApproveProcessInfo.setOrgName(tempTTmcApproveProcess.getEnterpName());
				bookerInfo.setId(tempTTmcApproveProcess.getBookerId());
				bookerInfo.setName(tempTTmcApproveProcess.getBookerName());
				bookerInfo.setMobile(tempTTmcApproveProcess.getBookerMobile());
				bookerInfo.setEmail(tempTTmcApproveProcess.getBookerEmail());
				tTmcApproveProcessInfo.setBooker(bookerInfo);		
				
				List<TTmcApproveTraveller> travellerList= tTmcApproveProcessDao.queryForList("select traveller from "
						+ "TTmcApproveTraveller traveller where traveller.id.approveProcessId=?",new Object[]{tempTTmcApproveProcess.getApproveProcessId()});
				List<ApproveProcessTravellerMode> travellers=null;
				if (travellerList!=null && travellerList.size()>0) {
					travellers=new ArrayList<ApproveProcessTravellerMode>();
					for (TTmcApproveTraveller tTmcApproveTraveller:travellerList) {
						ApproveProcessTravellerMode approveProcessTravellerMode=new ApproveProcessTravellerMode();
						approveProcessTravellerMode.id=tTmcApproveTraveller.getTravellerId();
						approveProcessTravellerMode.name=tTmcApproveTraveller.getTravellerName();
						approveProcessTravellerMode.mobile=tTmcApproveTraveller.getTravellerMobile();
					    approveProcessTravellerMode.email=tTmcApproveTraveller.getTravellerEmail();
					    approveProcessTravellerMode.costId=tTmcApproveTraveller.getCostId();
					    approveProcessTravellerMode.costName=tTmcApproveTraveller.getCostName();
					    approveProcessTravellerMode.orgId=tTmcApproveTraveller.getOrgId();
					    approveProcessTravellerMode.orgName=approveProcessTravellerMode.getOrgName();
					    travellers.add(approveProcessTravellerMode);
					}					
				}				
				tTmcApproveProcessInfo.setTraveller(travellers);
				approveProcessInfoList.add(tTmcApproveProcessInfo);				
			}	
			responseApproval.list=approveProcessInfoList;
			responseApproval.page.count=pageObjectt.getDataCount();
		}
		
		return responseApproval;
	}

	@Override
	public Response_GetApprovalProcess getApprovalProcess(
			Request_GetApprovalProcess requestApproval) {
		Response_GetApprovalProcess responseApprovalProcess=new Response_GetApprovalProcess();
		List<ApproveLevelModel> approvals=null;
		String querySqlStr="select aPolicy.APPROVE_POLICY_ID,aPolicy.APPROVE_POLICY_NAME,"
				+ "aPolicy.APPROVE_TYPE,aPolicy.APPROVE_MODE,aProcess.STATE,aProcess.PROCESS_LEVEL,"
				+ "aProcess.PROCESS_STATE,aProcess.APPROVE_PROCESS_ID from T_TMC_APPROVE_PROCESS aProcess,t_tmc_approve_policy aPolicy "
				+ "where aProcess.approve_policy_id=aPolicy.approve_policy_id and aProcess.orderno=?";		
		List<HashMap> objList=tTmcApproveProcessDao.queryBySQLForObject(querySqlStr, new Object[]{requestApproval.orderno},HashMap.class);
		if (objList!=null && objList.size()>0) {			
			Map tempMap=objList.get(0);
			Long approvePolicyId=Long.valueOf(tempMap.get("APPROVE_POLICY_ID").toString());
			responseApprovalProcess.id=approvePolicyId;
			responseApprovalProcess.name=tempMap.get("APPROVE_POLICY_NAME").toString();
			responseApprovalProcess.type=Integer.valueOf(tempMap.get("APPROVE_TYPE").toString());
			responseApprovalProcess.mode=Integer.valueOf(tempMap.get("APPROVE_MODE").toString());
			responseApprovalProcess.currentApproveLevel=(tempMap.get("PROCESS_LEVEL")==null?null:Integer.valueOf(tempMap.get("PROCESS_LEVEL").toString()));
			responseApprovalProcess.state=(tempMap.get("PROCESS_STATE")==null?null:Integer.valueOf(tempMap.get("PROCESS_STATE").toString()));
			
			String auditorCountSqlStr="select aFlow.APPROVE_LEVEL,count(*) AUDITOR_COUNT "
					+ "from t_tmc_approve_flow aFlow where  aFlow.approve_policy_id=? "
					+ "group by aFlow.Approve_Level order by aFlow.APPROVE_LEVEL asc";
			List<HashMap> auditorCountList=tTmcApproveProcessDao.queryBySQLForObject(auditorCountSqlStr, new Object[]{approvePolicyId},HashMap.class);
			if (auditorCountList!=null && auditorCountList.size()>0) {
				approvals=new ArrayList<ApproveLevelModel>();
				
				for (Map tempAuditorCounMap:auditorCountList) {
					ApproveLevelModel approveLevelModel=new ApproveLevelModel();					
					approveLevelModel.count=Integer.valueOf(tempAuditorCounMap.get("AUDITOR_COUNT").toString());
					approveLevelModel.approveLevel=Integer.valueOf(tempAuditorCounMap.get("APPROVE_LEVEL").toString());
					if (approveLevelModel.approveLevel.intValue()<responseApprovalProcess.currentApproveLevel.intValue()) {
						approveLevelModel.state=TmcConstant.APPROVE_STATE.PASS;
					} else if (approveLevelModel.approveLevel.intValue()==responseApprovalProcess.currentApproveLevel.intValue()) {
						approveLevelModel.state=responseApprovalProcess.state;
					} else {
						approveLevelModel.state=TmcConstant.APPROVE_STATE.WAITING;
					}
					List<ApproveAuditModel> auditors=getApproveFlowList(approvePolicyId, approveLevelModel.approveLevel,
							Long.valueOf(tempMap.get("APPROVE_PROCESS_ID").toString()));
					approveLevelModel.auditors=auditors;
					
					approvals.add(approveLevelModel);
				}
				responseApprovalProcess.approvals=approvals;				
			}
			
			
			/*
			String auditModelHQLStr="select aFlow from TTmcApproveFlow aFlow left join TTmcApproveRecord aRecord "
					+ "where (aFlow.id.approveLevel=aRecord.id.approveLevel and aFlow.id.flowid=aRecord.id.flowid) and "
					+ " aFlow.id.approvePolicyId=? order by aRecord.id.flowid asc,aFlow.id.flowid asc";
			List<Object> approveFlowList=tTmcApproveProcessDao.queryForList(auditModelHQLStr, new Object[]{responseApprovalProcess.id});
			List<TTmcApproveFlow> approveFlowList=tTmcApproveProcessDao.queryForList("select aFlow from "
	    			+ "TTmcApproveFlow aFlow where aFlow.id.approvePolicyId=?", new Object[]{responseApprovalProcess.id});
			List<TTmcApproveRecord> approveRecordList=tTmcApproveProcessDao.queryForList("select aRecord from "
	    			+ "TTmcApproveRecord aRecord where aRecord.id.approveProcessId=?", new Object[]{Long.valueOf((tempMap.get("APPROVE_PROCESS_ID").toString()))});
			approvals=new ArrayList<ApproveLevelModel>();
			for (TTmcApproveFlow tempTTmcApproveFlow:approveFlowList) {
				ApproveLevelModel approveLevelModel=new ApproveLevelModel();				
			}*/
			
			
		}
		return responseApprovalProcess;
	}
    
    private List<ApproveAuditModel> getApproveFlowList(Long approvePolicyId,Integer approveLevel,Long approveProcessId) {
    	List<ApproveAuditModel> approveAuditList=null;
    	Object requestObjects[]=null;
    	String approveLevelWhere="";
    	if (approveLevel==null) {
    		approveLevelWhere="";
    		requestObjects=new Object[]{approveProcessId,approvePolicyId};
    	} else {
    		approveLevelWhere="and aFlow.Approve_Level=?";
    		requestObjects=new Object[]{approveLevel,approveProcessId,approvePolicyId,approveLevel};    		
    	}
    	String auditModelSqlStr="select aFlow.Approve_Level,aFlow.Flowid,aFlow.Approve_User_Id,aFlow.Approve_User_Name,"
				+ "aFlow.Approve_User_Mobile,aFlow.Approve_User_Email,aRecord.Approve_Flowid,aRecord.Operator_Id,"
				+ "aRecord.Operator_Name,aRecord.Operator_Mobile,aRecord.Operator_Email,aRecord.Memo,aRecord.State,"
				+ "aRecord.Approve_Type,aRecord.Approve_Time from t_tmc_approve_flow aFlow left join t_tmc_approve_record aRecord "
				+ "on (aFlow.Approve_Level=aRecord.Approve_Level and aFlow.Flowid=aRecord.Flowid "+approveLevelWhere+" and aRecord.approve_process_id=?) "
				+ "where aFlow.Approve_Policy_Id=?  "+approveLevelWhere+" order by aFlow.Approve_Level asc,aRecord.Approve_Flowid asc,aFlow.Flowid asc";
    	
		List<ApproveAuditVO> approveAuditVOList=tTmcApproveProcessDao.queryBySQLForObject(auditModelSqlStr, requestObjects, ApproveAuditVO.class);
		if (approveAuditVOList!=null && approveAuditVOList.size()>0) {
			approveAuditList=new ArrayList<ApproveAuditModel>();
			for (int i=0;i<approveAuditVOList.size();i++) {
				ApproveAuditVO approveAuditVO=(ApproveAuditVO)approveAuditVOList.get(i);
				ApproveAuditModel approveAuditModel=new ApproveAuditModel();
				approveAuditModel.id=approveAuditVO.getAPPROVE_USER_ID().longValue();
				approveAuditModel.name=approveAuditVO.getAPPROVE_USER_NAME();
				approveAuditModel.mobile=approveAuditVO.getAPPROVE_USER_MOBILE();
				approveAuditModel.email=approveAuditVO.getAPPROVE_USER_EMAIL();
				approveAuditModel.type=approveAuditVO.getAPPROVE_TYPE()==null?null:approveAuditVO.getAPPROVE_TYPE().intValue();
				approveAuditModel.state=approveAuditVO.getSTATE()==null?1:approveAuditVO.getSTATE().intValue();
				approveAuditModel.memo=approveAuditVO.getMEMO();
				approveAuditModel.datetime=approveAuditVO.getAPPROVE_TIME()==null?null:approveAuditVO.getAPPROVE_TIME().toString();
				
				ApproveAuditorModel approveAuditorModel=new ApproveAuditorModel();
				approveAuditorModel.id=approveAuditVO.getOPERATOR_ID()==null?null:approveAuditVO.getOPERATOR_ID().longValue();
				approveAuditorModel.name=approveAuditVO.getOPERATOR_NAME()==null?null:approveAuditVO.getOPERATOR_NAME();
				approveAuditorModel.mobile=approveAuditVO.getOPERATOR_MOBILE()==null?null:approveAuditVO.getOPERATOR_MOBILE();
				approveAuditorModel.email=approveAuditVO.getOPERATOR_EMAIL()==null?null:approveAuditVO.getOPERATOR_EMAIL();
				approveAuditModel.operator=approveAuditorModel;
				approveAuditList.add(approveAuditModel);				
			}			
		}		
		return approveAuditList;
    }
    
    public Response_GetApprovalProcess getRecentlyApprovalPending(Request_GetApprovalProcess requestApproval) {
    	Response_GetApprovalProcess approvalProcessResponse=getApprovalProcess(requestApproval); 
    	return produceRecentlyApprovalPending(approvalProcessResponse);
    }   	
    
    private Response_GetApprovalProcess produceRecentlyApprovalPending(Response_GetApprovalProcess approvalProcessResponse) {
    	Response_GetApprovalProcess approvalProcessPendingResponse=new Response_GetApprovalProcess();    
    	approvalProcessPendingResponse.id=approvalProcessResponse.id;
    	approvalProcessPendingResponse.state=approvalProcessResponse.state;
    	approvalProcessPendingResponse.name=approvalProcessResponse.name;
    	approvalProcessPendingResponse.currentApproveLevel=approvalProcessResponse.currentApproveLevel;
    	if (approvalProcessResponse.state==TmcConstant.APPROVE_STATE.WAITING.intValue() || 
    			approvalProcessResponse.state==TmcConstant.APPROVE_STATE.APPROVING.intValue()) { 
    		List<ApproveLevelModel> approvalPendingApprovals=new ArrayList<ApproveLevelModel>();
    		List<ApproveLevelModel> approvals=approvalProcessResponse.approvals;
    		if (approvals!=null && approvals.size()>0) {  
    			int approveLevelRecentlyStatus=0;
    			boolean hasRrecentlyAuditors=false;
    			int approveLeveAgreeCount=0;
    			for (ApproveLevelModel tempApproveLevelModel:approvals) { 
    				approveLevelRecentlyStatus=tempApproveLevelModel.state;
    				//依据已经审批的组的审批状态进行处理,begin
    				if (approveLevelRecentlyStatus==TmcConstant.APPROVE_STATE.PASS.intValue()) {
    					approveLeveAgreeCount++;
    					if (approveLeveAgreeCount==approvals.size()) {
        					approvalProcessPendingResponse.state=TmcConstant.APPROVE_STATE.PASS;
        					break;
        				}  
    					continue;    					 					   				
    				} else if (approveLevelRecentlyStatus==TmcConstant.APPROVE_STATE.REFUSE.intValue()) {
    					approvalProcessPendingResponse.state=TmcConstant.APPROVE_STATE.REFUSE;
    					break;
    				}  
    				//end
    				
    				List<ApproveAuditModel> approvalPendingauditors=new ArrayList<ApproveAuditModel>();
    				List<ApproveAuditModel> auditors=tempApproveLevelModel.auditors;
    				if (auditors!=null && auditors.size()>0) {
    					int auditorsAgreeCount=0;
    					int auditorsRefuseCount=0;
    					for (ApproveAuditModel tempApproveAuditModel:auditors) {
    						if (approvalProcessResponse.mode.intValue()==1) {
    							if (tempApproveAuditModel.state!=null) {
    								if (tempApproveAuditModel.state.intValue()==TmcConstant.APPROVE_STATE.PASS.intValue()) {
    									approveLevelRecentlyStatus=TmcConstant.APPROVE_STATE.PASS;
    									hasRrecentlyAuditors=false;
    									break;
    								} else if (tempApproveAuditModel.state.intValue()==TmcConstant.APPROVE_STATE.REFUSE.intValue()) {
    									auditorsRefuseCount++;
    									//if (auditorsRefuseCount==auditors.size()) {
    										approveLevelRecentlyStatus=TmcConstant.APPROVE_STATE.REFUSE;
    										hasRrecentlyAuditors=false;
    										break;
    									//}
    								} else {
    									approveLevelRecentlyStatus=TmcConstant.APPROVE_STATE.APPROVING;
    									hasRrecentlyAuditors=true;
    									approvalPendingauditors.add(tempApproveAuditModel);
    								}
    							} else {
    								hasRrecentlyAuditors=true;
    								approvalPendingauditors.add(tempApproveAuditModel);
    							}
    						} else if (approvalProcessResponse.mode.intValue()==2) {
    							if (tempApproveAuditModel.state!=null) {
    								if (tempApproveAuditModel.state.intValue()==TmcConstant.APPROVE_STATE.PASS.intValue()) {
    									auditorsAgreeCount++;
    									if (auditorsAgreeCount==auditors.size()) {
    										approveLevelRecentlyStatus=TmcConstant.APPROVE_STATE.PASS;
    										hasRrecentlyAuditors=false;
        									break;
    									}    									
    								} else if (tempApproveAuditModel.state.intValue()==TmcConstant.APPROVE_STATE.REFUSE.intValue()) {
    									approveLevelRecentlyStatus=TmcConstant.APPROVE_STATE.REFUSE;
    									hasRrecentlyAuditors=false;
    									break;
    								} else {
    									approveLevelRecentlyStatus=TmcConstant.APPROVE_STATE.APPROVING;
    									hasRrecentlyAuditors=true;
    									approvalPendingauditors.add(tempApproveAuditModel);
    								}
    							} else {
    								hasRrecentlyAuditors=true;
    								approvalPendingauditors.add(tempApproveAuditModel);
    							}  							
    						}  else if (approvalProcessResponse.mode.intValue()==3) {
    							String querySqlStr="select aPolicy.APPROVE_POLICY_ID,aPolicy.APPROVE_REQUIRE"
    									+ " from t_tmc_approve_policy aPolicy where aPolicy.approve_policy_id=?";		
    							List<HashMap> objList=tTmcApproveProcessDao.queryBySQLForObject(querySqlStr, new Object[]{approvalProcessResponse.id},HashMap.class);
    							Integer require=null;
    							if (objList!=null && objList.size()>0) {	
    								Map tempMap=objList.get(0);
    								require=(tempMap.get("APPROVE_REQUIRE")==null?null:Integer.valueOf(tempMap.get("APPROVE_REQUIRE").toString()));
    							}
    							if 	(require==null || require.intValue()<1) {
    								require=auditors.size();
    							}
    							if (tempApproveAuditModel.state!=null) {
    								if (tempApproveAuditModel.state.intValue()==TmcConstant.APPROVE_STATE.PASS.intValue()) {
    									auditorsAgreeCount++;
    									if (auditorsAgreeCount==require.intValue()) {
    										approveLevelRecentlyStatus=TmcConstant.APPROVE_STATE.PASS;
    										hasRrecentlyAuditors=false;
        									break;
    									}    									
    								} else if (tempApproveAuditModel.state.intValue()==TmcConstant.APPROVE_STATE.REFUSE.intValue()) {
    									approveLevelRecentlyStatus=TmcConstant.APPROVE_STATE.REFUSE;
    									hasRrecentlyAuditors=false;
    									break;
    								} else {
    									approveLevelRecentlyStatus=TmcConstant.APPROVE_STATE.APPROVING;
    									hasRrecentlyAuditors=true;
    									approvalPendingauditors.add(tempApproveAuditModel);
    								}
    							} else {
    								hasRrecentlyAuditors=true;
    								approvalPendingauditors.add(tempApproveAuditModel);
    							}  							
    						}       					
        				}
    					tempApproveLevelModel.state=approveLevelRecentlyStatus;
    					tempApproveLevelModel.auditors=approvalPendingauditors;
    					approvalProcessPendingResponse.state=approveLevelRecentlyStatus;
    					approvalProcessPendingResponse.currentApproveLevel=tempApproveLevelModel.approveLevel;
    				}  
    				
    				//依据最近审核的组的审核状态进行处理,beging
    				if (hasRrecentlyAuditors) {
    					approvalPendingApprovals.add(tempApproveLevelModel);
    					approvalProcessPendingResponse.approvals=approvalPendingApprovals;
    					break;
    				} 
    				if (approveLevelRecentlyStatus==TmcConstant.APPROVE_STATE.PASS.intValue()) {
    					approveLeveAgreeCount++;    					
    				}    				
    				if (approveLeveAgreeCount==approvals.size()) {
    					approvalProcessPendingResponse.status=TmcConstant.APPROVE_STATE.PASS;
    					break;
    				}  
    				if (approveLevelRecentlyStatus==TmcConstant.APPROVE_STATE.REFUSE.intValue()) {
    					approvalProcessPendingResponse.status=TmcConstant.APPROVE_STATE.REFUSE;
    					break;
    				}
    				//end
    			}    			
    		}    		
    	}    
    	logger.info("produceRecentlyApprovalPending:"+JSONObject.toJSONString(approvalProcessPendingResponse));
    	return approvalProcessPendingResponse;     	
    }
	
    public Response_CallbackApproval callbackApproval(Integer businessType,String orderno,Response_GetApprovalProcess response_GetApprovalProcess) {
    	Request_CallbackApproval request_CallbackApproval=new Request_CallbackApproval();
    	Response_CallbackApproval response_CallbackApproval=new Response_CallbackApproval();
    	Response_GetApprovalProcess tempGetApprovalProcess=new Response_GetApprovalProcess();
    	try {
    		request_CallbackApproval.orderno=orderno;
    		request_CallbackApproval.businessType=businessType;
    		tempGetApprovalProcess.state=response_GetApprovalProcess.state;
    		tempGetApprovalProcess.currentApproveLevel=response_GetApprovalProcess.currentApproveLevel;
    		request_CallbackApproval.data=tempGetApprovalProcess;
    		request_CallbackApproval.method = "ApprovalCallback";
	    	String json = JSON.toJSONString(request_CallbackApproval);
			String req = json+"&sign="+EncryptHelper.MD5Encrypt(json);
			logger.info("callbackApproval req:"+req);
			req = EncryptHelper.DESEncrypt(req);
			
			req = "data="+URLEncoder.encode(req,"utf-8");
			
			String url = PropertiesUtils.getProperty("hotelHtml5." + Const.PRO_SERVER_URL, Const.FILE_CONFIG);
			String res = HttpUtils.doPost(url, req, 30000, 30000);			
			res = URLDecoder.decode(res,"utf-8");
			res = EncryptHelper.DESDecrypt(res);
			logger.info("callbackApproval result:"+res);
			response_CallbackApproval=JSONObject.parseObject(res.split("&")[0],Response_CallbackApproval.class);
    	} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
    	return null;
    }

}
