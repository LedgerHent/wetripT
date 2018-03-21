package com.viptrip.wetrip.service.impl;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.viptrip.base.cache.manager.RedisCacheManager;
import com.viptrip.base.common.MyEnum.BussinessType;
import com.viptrip.base.common.MyEnum.Excessinfo;
import com.viptrip.base.common.MyEnum.OrgRuleType;
import com.viptrip.base.common.MyEnum.OverproofShow;
import com.viptrip.base.common.MyEnum.Overproofdefault_v2;
import com.viptrip.base.common.MyEnum.PolicyType_v2;
import com.viptrip.base.common.MyEnum.isOpenType;
import com.viptrip.common.controller.GetRulesByTravller;
import com.viptrip.common.model.Request_GetRulesByTravller;
import com.viptrip.common.model.Response_GetRulesByTravller;
import com.viptrip.hotel.model.orggroups.OrgGroup;
import com.viptrip.ibeserver.model.DispplayTrip;
import com.viptrip.resource.Const;
import com.viptrip.util.DateUtil;
import com.viptrip.util.StringUtil;
import com.viptrip.wetrip.dao.ext.ComDao;
import com.viptrip.wetrip.entity.OrgPolicyTicket;
import com.viptrip.wetrip.entity.PolicyTicketRule;
import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.entity.policy.OrgGroupRelation;
import com.viptrip.wetrip.entity.policy.OrgGroupRelationId;
import com.viptrip.wetrip.entity.policy.TExcessManage;
import com.viptrip.wetrip.entity.OrgPolicyManage;
import com.viptrip.wetrip.model.Request_GetFlightList;
import com.viptrip.wetrip.model.flight.ReqData_GetFlightList_Passenger;
import com.viptrip.wetrip.model.flight.ReqData_GetFlightList_PassengerMessage;
import com.viptrip.wetrip.model.policy.BerthPolicyInfo;
import com.viptrip.wetrip.model.policy.DomTicketModel;
import com.viptrip.wetrip.model.policy.PolicyTicketInfo;
import com.viptrip.wetrip.model.policy.RuleId;
import com.viptrip.wetrip.model.policy.RuleInfo;
import com.viptrip.wetrip.model.policy.RuleMaps;
import com.viptrip.wetrip.model.policy.TicketItems;
import com.viptrip.wetrip.model.policy.TicketRules;
import com.viptrip.wetrip.service.PolicyManageService;

import etuf.v1_0.model.base.output.OutputResult;

@Service
@Transactional
public class PolicyManageServiceImpl implements PolicyManageService {
    private static Logger logger = LoggerFactory.getLogger(PolicyManageServiceImpl.class);

    @Resource
    private ComDao comDao;

    public PolicyTicketInfo getNewlyRule(List<PolicyTicketInfo> cangweiPolicyList, String aircomney, String cangwei) {
        PolicyTicketInfo ruleInfo = null;
        for (int j = 0; j < cangweiPolicyList.size(); j++) {
            PolicyTicketInfo tPolicyTicket = cangweiPolicyList.get(j);
            List<RuleInfo> ruleinfoList = tPolicyTicket.getRuleList();
            String companyRules = "*";// 航司规则
            String cabinrules = "*";// 舱位规则
            for (int i = 0; i < ruleinfoList.size(); i++) {
                RuleInfo info = ruleinfoList.get(i);
                if (info.getRuletype().intValue() == 20) {
                    companyRules = "," + info.getRules() + ",";// 航司规则
                    if (companyRules.contains("*/")) {
                        String[] companyRules_ = companyRules.split("\\*/");
                        companyRules = companyRules_[0] + "*/," + companyRules_[1];
                    }
                    if ("*".equals(info.getRules())) {
                        companyRules = info.getRules();
                    }
                } else if (info.getRuletype().intValue() == 40) {
                    cabinrules = "," + info.getRules() + ",";// 舱位规则
                    if (cabinrules.contains("*/")) {
                        String[] cabinrules_ = cabinrules.split("\\*/");
                        cabinrules = cabinrules_[0] + "*/," + cabinrules_[1];
                    }
                    if ("*".equals(info.getRules())) {
                        cabinrules = info.getRules();
                    }
                }
            }

            String berth2 = "," + cangwei + ",";
            String airCompany = "," + aircomney + ",";

            if ("*".equals(companyRules)
                    || (companyRules.indexOf("*/") >= 0 || (companyRules.indexOf("*/") < 0 && companyRules
                            .indexOf(airCompany) >= 0))) {// 航司判断

                if ("*".equals(cabinrules)
                        || (cabinrules.indexOf("*/") >= 0 || (cabinrules.indexOf("*/") < 0 && cabinrules
                                .indexOf(berth2) >= 0))) {// 舱位判断
                    ruleInfo = tPolicyTicket;
                    break;
                }
            }
        }
        return ruleInfo;
    }

    public int isMarryBerthPolicy(PolicyTicketInfo tPolicyTicket, BerthPolicyInfo berthPolicyInfo) {
        int flag = -1;

        List<RuleInfo> ruleinfoList = tPolicyTicket.getRuleList();

        String airlineRules = "*";// 航线规则
        String companyRules = "*";// 航司规则
        String flightRules = "*";// 航班规则
        String cabinrules = "*";// 舱位规则

        for (int i = 0; i < ruleinfoList.size(); i++) {
            RuleInfo info = ruleinfoList.get(i);
            if (info.getRuletype().intValue() == 20) {
                companyRules = "," + info.getRules() + ",";// 航司规则
                if (companyRules.contains("*/")) {
                    String[] companyRules_ = companyRules.split("\\*/");
                    companyRules = companyRules_[0] + "*/," + companyRules_[1];
                }
                if ("*".equals(info.getRules())) {
                    companyRules = info.getRules();
                }
            } else if (info.getRuletype().intValue() == 40) {
                cabinrules = "," + info.getRules() + ",";// 舱位规则
                if (cabinrules.contains("*/")) {
                    String[] cabinrules_ = cabinrules.split("\\*/");
                    cabinrules = cabinrules_[0] + "*/," + cabinrules_[1];
                }
                if ("*".equals(info.getRules())) {
                    cabinrules = info.getRules();
                }
            }
        }

        String berth2 = "," + berthPolicyInfo.getBerth() + ",";
        String airCompany = "," + berthPolicyInfo.getAirCompany() + ",";
        if (!"*".equals(airlineRules)
                && ((airlineRules.indexOf("*/") >= 0 && airlineRules.indexOf(berthPolicyInfo.getAirline()) >= 0) || (airlineRules
                        .indexOf("*/") < 0 && airlineRules.indexOf(berthPolicyInfo.getAirline()) < 0))) {// 航线判断
            flag = Excessinfo.航线不在预定权限超标.getOperateType();
        } else {
            if (!"*".equals(companyRules)
                    && ((companyRules.indexOf("*/") >= 0 && companyRules.indexOf(airCompany) >= 0) || (companyRules
                            .indexOf("*/") < 0 && companyRules.indexOf(airCompany) < 0))) {// 航司判断
                flag = Excessinfo.航司不在预定权限超标.getOperateType();
            } else {
                if (!"*".equals(flightRules)
                        && ((flightRules.indexOf("*/") >= 0 && flightRules.indexOf(berthPolicyInfo.getFlightnum()) >= 0) || (flightRules
                                .indexOf("*/") < 0 && flightRules.indexOf(berthPolicyInfo.getFlightnum()) < 0))) {// 航班判断
                    flag = Excessinfo.航班不在预定权限超标.getOperateType();
                } else {
                    if (!"*".equals(cabinrules)
                            && ((cabinrules.indexOf("*/") >= 0 && cabinrules.indexOf(berth2) >= 0) || (cabinrules
                                    .indexOf("*/") < 0 && cabinrules.indexOf(berth2) < 0))) {// 舱位判断
                        flag = Excessinfo.舱位不在预定权限超标.getOperateType();
                    } else {
                        flag = Excessinfo.可预定.getOperateType();
                    }
                }
            }
        }
        return flag;
    }

    @Override
    public List<DispplayTrip> filterPolicyForTrips(long userid, String travelType,
            List<ReqData_GetFlightList_Passenger> passengers, List<DispplayTrip> entitys) {
        if (entitys==null || passengers == null) {
            return entitys;
        }
        TAcUser tacuser = null;
        String selectorguser = "";
        OrgPolicyManage orgPolicyManage = new OrgPolicyManage(); // 企业差旅政策管理
        Map<String, List<PolicyTicketInfo>> regularMap = null;
        Map<String, String> selectUserMap = new HashMap<String, String>();

        List<Integer> userIds = new ArrayList<Integer>();
        for (ReqData_GetFlightList_Passenger passenger : passengers) {
            List<ReqData_GetFlightList_PassengerMessage> passengerMessages = passenger.getPassengers();
            for (ReqData_GetFlightList_PassengerMessage passenger_ : passengerMessages) {
                userIds.add(Integer.valueOf(passenger_.userid.intValue()));
                selectUserMap.put(String.valueOf(passenger_.userid.intValue()),
                        String.valueOf(passenger_.getUsername()));
            }
        }

        Request_GetRulesByTravller rg = new Request_GetRulesByTravller();
        List<Integer> blist = new ArrayList<Integer>();
        List<Integer> tlist = userIds;
        blist.add(BussinessType.国内机票.getValue());
        if (userid == 0) {
            // rg.setUserId(para.getUserId());
        } else {
            rg.setUserId(userid);
        }
        rg.businessTypes = blist;
//        tlist.add((int) rg.userId);
        rg.travellers = tlist;
        GetRulesByTravller grb = new GetRulesByTravller();
        OutputResult<Response_GetRulesByTravller, String> or = grb.ClientRequest(rg, Response_GetRulesByTravller.class);
        Response_GetRulesByTravller rpg = new Response_GetRulesByTravller();
        if (or.IsSucceed()) {
            rpg = or.getResultObj();
            DomTicketModel ticketModel = rpg.getData().getTicket();
            // 整型数字，0-关闭，1-开启
            if (ticketModel.state.intValue() == 1) {
                regularMap = new HashMap<String, List<PolicyTicketInfo>>();
                orgPolicyManage.setOverproofAudit(Long.valueOf(ticketModel.audit));
                orgPolicyManage.setOverproofDefault(Long.valueOf(ticketModel.policyDefault));
                orgPolicyManage.setOverproofBook(Long.valueOf(ticketModel.book));
                orgPolicyManage.setPolicyControl(Long.valueOf(ticketModel.state));
                orgPolicyManage.setOverproofShow(Long.valueOf(ticketModel.show));

                List<RuleMaps> ruleMaps = ticketModel.ruleMaps;
                for (int i = 0; ruleMaps!=null && i < ruleMaps.size(); i++) {
                    List<PolicyTicketInfo> policyInfoList = new ArrayList<PolicyTicketInfo>();
                    RuleMaps ruleMap = ruleMaps.get(i);
                    List<TicketRules> rules = ruleMap.rules;
                    for (int j = 0; j < rules.size(); j++) {
                        TicketRules rule = rules.get(i);
                        PolicyTicketInfo ticketInfo = new PolicyTicketInfo();
                        List<RuleInfo> ruleList = new ArrayList<RuleInfo>();
                        ticketInfo.setPolicyType(rule.type);
                        ticketInfo.setOverproofshow(rule.show);
                        ticketInfo.setOverproofbook(rule.book);
                        ticketInfo.setOverproofaudit(rule.audit);
                        List<TicketItems> items = rule.items;
                        for (int k = 0; k < items.size(); k++) {
                            RuleInfo info = new RuleInfo();
                            RuleId ruleId = new RuleId();
                            ruleId.setFlowid(items.get(i).flowId);
                            info.setId(ruleId);
                            info.setRules(items.get(i).rule);
                            info.setLowerLimit(items.get(i).lowerLimit);
                            info.setRuletype(items.get(i).type);
                            info.setUpperLimit(items.get(i).upperLimit);
                            ruleList.add(info);
                        }
                        ticketInfo.setRuleList(ruleList);
                        policyInfoList.add(ticketInfo);
                    }
                    regularMap.put(ruleMap.traveller.toString(), policyInfoList);
                }
            }
        }

        if (userIds.size() > 0) {
            for (int i = 0; i < userIds.size(); i++) {
                tacuser = (TAcUser) this.comDao.queryForEntity(Long.valueOf(userIds.get(i)), TAcUser.class);
                if (tacuser != null) {
                    if (selectorguser.length() > 0) {
                        selectorguser += ",";
                    }
                    selectorguser += tacuser.getIdcard() + "_" + tacuser.getUserid() + "_" + tacuser.getOrgid() + "_"
                            + selectUserMap.get(tacuser.getUserid().toString()).toString() + "_" + tacuser.getUsername();
                }
            }
        }

        /**
         * @功能：过滤差旅管控政策
         * @author wjt
         * @时间： 2017-03-31
         */
        if ("1".equals(travelType) && StringUtils.isNotEmpty(selectorguser) && orgPolicyManage != null
                && orgPolicyManage.getPolicyControl().intValue() == isOpenType.开启.getOperateType()) {
            entitys = this.getPartTimeMinPrice_v2(entitys, regularMap, orgPolicyManage, selectUserMap);

        }
        return entitys;
    }

    /**
     * 差旅管控二期
     * 
     * @param ldtt
     * @param regularMap
     * @param orgPolicyManage
     * @param selectUserMap
     * @return
     */
    public List<DispplayTrip> getPartTimeMinPrice_v2(List<DispplayTrip> ldtt,
            Map<String, List<PolicyTicketInfo>> regularMap, OrgPolicyManage orgPolicyManage,
            Map<String, String> selectUserMap) {
        // RegularManager regularManagers;

        Long time1 = System.currentTimeMillis();
        if (ldtt==null || ldtt.size() == 0) {
            return ldtt;
        }

        // --------->>>对所有有人的规则 进行分类整理，[前后最低价、提前天数、按折扣、按金额、按舱位]
        Map<String, List<PolicyTicketInfo>> regularTyesMap = new HashMap<String, List<PolicyTicketInfo>>();
        String regularTypesKey = "POLICY_TYPE_";
        String usernames = "";
        Iterator<String> userIterators = selectUserMap.values().iterator();
        while (userIterators.hasNext()) {
            String usernames_ = userIterators.next();
            usernames += usernames_ + "、";
        }
        usernames = usernames.substring(0, usernames.length() - 1);
        // --------->>>未设置差旅规则
        // --------->>>如果没有匹配到任何规则，就意味着是没有设置差旅规则。
        // */规则，意味着所有规则都包含在内
        if (regularMap == null || regularMap.size() == 0) {
            int overproofdefault = orgPolicyManage.getOverproofDefault().intValue();// 未匹配到规则的默认值，1-超标|2-不超标
            String overproofshow = orgPolicyManage.getOverproofShow().toString();// 超标显示规则，10-隐藏|20-正常显示|21-超标显示
            int overproofBook = orgPolicyManage.getOverproofBook().intValue(); // 默认预定规则
                                                                               // 超标准预定规则，10-不允许预定|20-提示超标允许预定|21-选择超标理由允许预定
            if (overproofdefault == Overproofdefault_v2.超标.getValue()) {
                List<DispplayTrip> ldtts = new ArrayList<DispplayTrip>();
                for (int i = 0; i < ldtt.size(); i++) {
                    DispplayTrip trip = ldtt.get(i);
                    trip.setExcessinfo(Excessinfo.未设置差旅规则.getOperateType() + "_" + overproofBook + "_" + usernames);
                    trip.setShowRule(overproofshow);
                    ldtts.add(trip);
                }
                ldtts = filterEnd(ldtts);
                return ldtts;
            } else {
                return ldtt;    
            }
        } else {
            List<PolicyTicketInfo> lists = null;
            List<PolicyTicketInfo> typelists = null;
            PolicyTicketInfo iteratorPolicyInfo = null;
            Iterator<String> userids = regularMap.keySet().iterator();
            while (userids.hasNext()) {
                String useridsstr = userids.next();
                lists = regularMap.get(useridsstr);
                for (int i = 0; i < lists.size(); i++) {
                    iteratorPolicyInfo = lists.get(i);
                    if (iteratorPolicyInfo != null) {
                        String keyStr = new StringBuffer().append(regularTypesKey)
                                .append(iteratorPolicyInfo.getPolicyType().intValue()).toString();
                        typelists = regularTyesMap.get(keyStr);
                        if (typelists == null || typelists.size() == 0) {
                            typelists = new ArrayList<PolicyTicketInfo>();
                        }
                        iteratorPolicyInfo.setUserid(useridsstr);
                        iteratorPolicyInfo.setUsername(selectUserMap.get(useridsstr));
                        typelists.add(iteratorPolicyInfo);
                        regularTyesMap.put(keyStr, typelists);
                    }
                }
                if (lists.size() == 0) {
                    int overproofdefault = orgPolicyManage.getOverproofDefault().intValue();// 未匹配到规则的默认值，1-超标|2-不超标
                    String overproofshow = orgPolicyManage.getOverproofShow().toString();// 超标显示规则，10-隐藏|20-正常显示|21-超标显示
                    int overproofBook = orgPolicyManage.getOverproofBook().intValue(); // 默认预定规则
                                                                                       // 超标准预定规则，10-不允许预定|20-提示超标允许预定|21-选择超标理由允许预定
                    if (overproofdefault == Overproofdefault_v2.超标.getValue()) {
                        List<DispplayTrip> ldtts = new ArrayList<DispplayTrip>();
                        for (int i = 0; i < ldtt.size(); i++) {
                            DispplayTrip trip = ldtt.get(i);
                            // 同一个企业下的所有员工的默认设置都一样，如果没设置规则，则都初始为"未设置差旅规则"
                            if (StringUtils.isEmpty(trip.getExcessinfo())) {
                                String excessinfo = Excessinfo.未设置差旅规则.getOperateType() + "_" + overproofBook + "_"
                                        + selectUserMap.get(useridsstr);
                                trip.setExcessinfo(excessinfo);
                            } else {
                                trip.setExcessinfo(trip.getExcessinfo() + "、" + selectUserMap.get(useridsstr));
                            }

                            trip.setShowRule(overproofshow);

                            ldtts.add(trip);
                        }
                        ldtt = ldtts;
                    }
                }
            }

        }

        // lists=null;
        // typelists=null;
        // iteratorPolicyInfo=null;//释放

        long time4 = System.currentTimeMillis();
        logger.info("---------未设置差旅过滤------->>" + (time4 - time1));
        // --------->>> 提前预定天数判断,如果判断超标组合信息
        Date tripdate = ldtt.get(0).getStartdate();
        Long oo = DateUtil.getDiffDay(DateUtil.date2Str(new Date(), "yyyy-MM-dd"),
                DateUtil.date2Str(tripdate, "yyyy-MM-dd"), "yyyy-MM-dd");
        List<PolicyTicketInfo> preBookDaysList = regularTyesMap.get(new StringBuffer().append(regularTypesKey)
                .append(PolicyType_v2.提前预定天数.getValue()).toString());
        if (preBookDaysList != null && preBookDaysList.size() > 0) {
            for (int i = 0; i < preBookDaysList.size(); i++) {
                PolicyTicketInfo info = preBookDaysList.get(i);
                RuleInfo ruleinfo = info.getRuleList().get(0);
                Double preBookDay = ruleinfo.getUpperLimit();// 存储了提前预定天数
                if (oo.intValue() < preBookDay.intValue()) {
                    for (int j = 0; j < ldtt.size(); j++) {
                        DispplayTrip trip = ldtt.get(j);
                        String excessinfo = trip.getExcessinfo();
                        String newExcessinfo = Excessinfo.提前预定天数不在预定权限超标.getOperateType() + "_"
                                + info.getOverproofbook().intValue() + "_" + info.getUsername();
                        String excessionfo = "";
                        if (!(StringUtils.isNotEmpty(excessinfo) && excessinfo.contains(newExcessinfo))) {
                            excessionfo = (StringUtils.isEmpty(excessinfo) ? "" : excessinfo + "~") + newExcessinfo;
                            if (StringUtil.isEmpty(trip.getShowRule())) {
                                trip.setShowRule(info.getOverproofshow().toString());
                            } else {
                                if (trip.getShowRule().equals(String.valueOf(OverproofShow.超标显示.getValue()))
                                        && info.getOverproofshow().intValue() == OverproofShow.隐藏.getValue()) {
                                    trip.setShowRule(info.getOverproofshow().toString());
                                } else if (trip.getShowRule().equals(String.valueOf(OverproofShow.正常显示.getValue()))
                                        && info.getOverproofshow().intValue() == OverproofShow.超标显示.getValue()) {
                                    trip.setShowRule(info.getOverproofshow().toString());
                                }
                            }
                        } else {
                            // excessionfo=StringUtils.isEmpty(excessinfo)?Excessinfo.可预定.getOperateType()+"":excessinfo;
                        }
                        trip.setExcessinfo(excessionfo);
                    }
                }
            }
        }

        long time5 = System.currentTimeMillis();
        // System.out.println("------提前天数---------->>"+(time5-time4));
        logger.info("---------提前天数--------->>" + (time5 - time4));

        // --------->>>处理前后多少小时最低价过滤
        List<PolicyTicketInfo> lowerpriceList = regularTyesMap.get(new StringBuffer().append(regularTypesKey)
                .append(PolicyType_v2.最低价.getValue()).toString());
        Map<String, List<DispplayTrip>> allDispplayTripMap = new HashMap<String, List<DispplayTrip>>();
        // 排序，按照航班号分组
        for (int i = 0; i < ldtt.size(); i++) {
            DispplayTrip trip = ldtt.get(i);
            String airline = trip.getAirline().split("\\$")[0];
            List<DispplayTrip> tripList = allDispplayTripMap.get(airline);
            if (tripList == null) {
                tripList = new ArrayList<DispplayTrip>();
                tripList.add(trip);
            } else {
                tripList.add(trip);
            }
            allDispplayTripMap.put(airline, tripList);
        }
        // 获取航班最低价格的数据集合
        List<DispplayTrip> tripList_minprices = new ArrayList<DispplayTrip>();
        List<DispplayTrip> tripList_new = new ArrayList<DispplayTrip>();
        // 排序，按照给定的方式 排序
        Iterator<String> iteratorKey = allDispplayTripMap.keySet().iterator();
        while (iteratorKey.hasNext()) {
            String key_ = iteratorKey.next();
            List<DispplayTrip> tripList = allDispplayTripMap.get(key_);
            tripList = sortCase(0, tripList); // 按照价格升序
            allDispplayTripMap.put(key_, tripList);
            tripList_minprices.add(tripList.get(0));
        }

        double rightTime = 0d;
        double leftTime = 0d;
        if (lowerpriceList != null && lowerpriceList.size() > 0) {
            for (DispplayTrip disobj : ldtt) {
                String excessionfoStr = disobj.getExcessinfo();
                String showRule = disobj.getShowRule();
                for (int i = 0; i < lowerpriceList.size(); i++) {
                    PolicyTicketInfo info = lowerpriceList.get(i);
                    RuleInfo ruleinfo = info.getRuleList().get(0);
                    leftTime = ruleinfo.getLowerLimit();
                    rightTime = ruleinfo.getUpperLimit();// 存储了提前预定天数
                    // 前几个小时 后几个小时区间，是否为最低价判断
                    double res = disobj.getPrice();
                    Date disobjdate = DateUtil.str2Date(DateUtil.date2Str(disobj.getStartdate(), "yyyy-MM-dd") + " "
                            + disobj.getStarttime(), "yyyy-MM-dd HH:mm");
                    for (DispplayTrip dis : tripList_minprices) {
                        Date disdate = DateUtil.str2Date(DateUtil.date2Str(dis.getStartdate(), "yyyy-MM-dd") + " "
                                + dis.getStarttime(), "yyyy-MM-dd HH:mm");
                        long diffMs = DateUtil.getDiffMs(disobjdate, disdate) / 60000;// 内全循环对象起飞时间-外循环对象起飞时间
                        if ((diffMs < 0 && diffMs * -1 <= leftTime * 60) || (diffMs > 0 && diffMs <= rightTime * 60)
                                || diffMs == 0) {
                            if (dis.getPrice().doubleValue() < disobj.getPrice().doubleValue()) {
                                res = dis.getPrice();
                                break;
                            }
                        }
                    }
                    if (res < disobj.getPrice()) {
                        // 非最低价
                        String newExcessinfo = Excessinfo.非最低价超标.getOperateType() + "_"
                                + Integer.valueOf(info.getOverproofbook()).intValue() + "_" + info.getUsername();
                        if (!(StringUtils.isNotEmpty(excessionfoStr) && excessionfoStr.contains(newExcessinfo))) {
                            excessionfoStr = (StringUtils.isEmpty(excessionfoStr) ? "" : excessionfoStr + "~")
                                    + newExcessinfo;
                            if (StringUtil.isEmpty(showRule)) {
                                showRule = info.getOverproofshow().toString();
                            } else {
                                if (showRule.equals(String.valueOf(OverproofShow.超标显示.getValue()))
                                        && info.getOverproofshow().intValue() == OverproofShow.隐藏.getValue()) {
                                    showRule = info.getOverproofshow().toString();
                                } else if (showRule.equals(String.valueOf(OverproofShow.正常显示.getValue()))
                                        && info.getOverproofshow().intValue() == OverproofShow.超标显示.getValue()) {
                                    showRule = info.getOverproofshow().toString();
                                }
                            }
                        } else {
                            // excessionfoStr=StringUtils.isEmpty(excessionfoStr)?Excessinfo.可预定.getOperateType()+"":excessionfoStr;
                        }
                    }
                }
                disobj.setExcessinfo(excessionfoStr);
                disobj.setShowRule(showRule);
                tripList_new.add(disobj);
            }
            ldtt = tripList_new;
        }

        tripList_new = null;
        tripList_minprices = null;
        tripList_minprices = new ArrayList<DispplayTrip>();
        long time6 = System.currentTimeMillis();
        logger.info("-------最低价--------->>" + (time6 - time5));
        // --------->>>处理金额、折扣两个规则的过滤
        List<PolicyTicketInfo> moneyPolicyList = regularTyesMap.get(new StringBuffer().append(regularTypesKey)
                .append(PolicyType_v2.按金额.getValue()).toString());
        List<PolicyTicketInfo> disPolicyList = regularTyesMap.get(new StringBuffer().append(regularTypesKey)
                .append(PolicyType_v2.按折扣.getValue()).toString());
        if ((moneyPolicyList != null && moneyPolicyList.size() > 0)
                || (disPolicyList != null && disPolicyList.size() > 0)) {
            for (int i = 0; i < ldtt.size(); i++) {
                DispplayTrip trip = ldtt.get(i);
                // 进行其他判断,判断金额
                String excessionfoStr = trip.getExcessinfo();
                String showRule = trip.getShowRule();
                for (int j = 0; moneyPolicyList != null && j < moneyPolicyList.size(); j++) {
                    PolicyTicketInfo info = moneyPolicyList.get(j);
                    RuleInfo ruleinfo = info.getRuleList().get(0);
                    Double ruleLeft = ruleinfo.getLowerLimit();
                    Double ruleRight = ruleinfo.getUpperLimit();
                    // if(trip.getAirline().contains("CZ6412")){
                    // System.out.println();
                    // }
                    if (trip.getPrice() < ruleLeft || trip.getPrice() > ruleRight) {
                        String newExcessinfo = Excessinfo.金额不在预定权限超标.getOperateType() + "_"
                                + Integer.valueOf(info.getOverproofbook()).intValue() + "_" + info.getUsername();
                        if (!(StringUtils.isNotEmpty(excessionfoStr) && excessionfoStr.contains(newExcessinfo))) {
                            excessionfoStr = (StringUtils.isEmpty(excessionfoStr) ? "" : excessionfoStr + "~")
                                    + newExcessinfo;
                            if (StringUtil.isEmpty(showRule)) {
                                showRule = info.getOverproofshow().toString();
                            } else {
                                if (showRule.equals(String.valueOf(OverproofShow.超标显示.getValue()))
                                        && info.getOverproofshow().intValue() == OverproofShow.隐藏.getValue()) {
                                    showRule = info.getOverproofshow().toString();
                                } else if (showRule.equals(String.valueOf(OverproofShow.正常显示.getValue()))
                                        && info.getOverproofshow().intValue() == OverproofShow.超标显示.getValue()) {
                                    showRule = info.getOverproofshow().toString();
                                }
                            }
                        }
                    } else {
                        // excessionfoStr=StringUtils.isEmpty(excessionfoStr)?Excessinfo.可预定.getOperateType()+"":excessionfoStr;
                    }
                }
                for (int j = 0; disPolicyList != null && j < disPolicyList.size(); j++) {
                    PolicyTicketInfo info = disPolicyList.get(j);
                    RuleInfo ruleinfo = info.getRuleList().get(0);
                    Double ruleLeft = ruleinfo.getLowerLimit();
                    Double ruleRight = ruleinfo.getUpperLimit();
                    double ceil = Math.ceil((trip.getPrice().doubleValue() / trip.getyPrice().doubleValue()) * 100);
                    int discountrateForY = Double.valueOf(ceil).intValue();
                    if (discountrateForY < ruleLeft || discountrateForY > ruleRight) {
                        String newExcessinfo = Excessinfo.折扣不在预定权限超标.getOperateType() + "_"
                                + Integer.valueOf(info.getOverproofbook()).intValue() + "_" + info.getUsername();
                        if (!(StringUtils.isNotEmpty(excessionfoStr) && excessionfoStr.contains(newExcessinfo))) {
                            excessionfoStr = (StringUtils.isEmpty(excessionfoStr) ? "" : excessionfoStr + "~")
                                    + newExcessinfo;
                            if (StringUtil.isEmpty(showRule)) {
                                showRule = info.getOverproofshow().toString();
                            } else {
                                if (showRule.equals(String.valueOf(OverproofShow.超标显示.getValue()))
                                        && info.getOverproofshow().intValue() == OverproofShow.隐藏.getValue()) {
                                    showRule = info.getOverproofshow().toString();
                                } else if (showRule.equals(String.valueOf(OverproofShow.正常显示.getValue()))
                                        && info.getOverproofshow().intValue() == OverproofShow.超标显示.getValue()) {
                                    showRule = info.getOverproofshow().toString();
                                }
                            }
                        }
                    } else {
                        // excessionfoStr=StringUtils.isEmpty(excessionfoStr)?Excessinfo.可预定.getOperateType()+"":excessionfoStr;
                    }
                }
                trip.setExcessinfo(excessionfoStr);
                trip.setShowRule(showRule);
                tripList_minprices.add(trip);
            }
            // 直接放list
            ldtt = tripList_minprices;
        }
        long time7 = System.currentTimeMillis();
        logger.info("-------金额 折扣-------->>" + (time7 - time6));
        tripList_minprices = new ArrayList<DispplayTrip>();
        List<PolicyTicketInfo> cangweiPolicyList = regularTyesMap.get(new StringBuffer().append(regularTypesKey)
                .append(PolicyType_v2.按舱位.getValue()).toString());
        if (cangweiPolicyList != null && cangweiPolicyList.size() > 0) {
            // TikMgrManager tikMgrManager = (TikMgrManager)
            // Struts2Util.getBean("tikMgrManager");
            Map<String, List<PolicyTicketInfo>> regularTyesCangweiMap = new HashMap<String, List<PolicyTicketInfo>>();
            for (int i = 0; i < cangweiPolicyList.size(); i++) {
                PolicyTicketInfo cangweiRuleInfo = cangweiPolicyList.get(i);
                List<PolicyTicketInfo> iffos = regularTyesCangweiMap.get(cangweiRuleInfo.getUsername());
                if (iffos == null) {
                    iffos = new ArrayList<PolicyTicketInfo>();
                }
                iffos.add(cangweiRuleInfo);
                regularTyesCangweiMap.put(cangweiRuleInfo.getUsername(), iffos);
            }
            Iterator<String> usernamestr = regularTyesCangweiMap.keySet().iterator();
            long time71 = System.currentTimeMillis();
            logger.info("----------------------舱位1----->>" + (time71 - time7));
            while (usernamestr.hasNext()) {
                String usernamestr_ = usernamestr.next();
                List<PolicyTicketInfo> iffosList = regularTyesCangweiMap.get(usernamestr_);
                iffosList = this.compareByDate(iffosList, "desc");
                long time72 = System.currentTimeMillis();
                logger.info("-------------------------------time72-time71-------->>" + (time72 - time71));
                int overproofdefault = orgPolicyManage.getOverproofDefault().intValue();// 未匹配到规则的默认值，1-超标|2-不超标
                int overproofBook = orgPolicyManage.getOverproofBook().intValue(); // 默认预定规则
                                                                                   // 超标准预定规则，10-不允许预定|20-提示超标允许预定|21-选择超标理由允许预定
                // int
                // overproofshow=orgPolicyManage.getOverproofShow().intValue();
                // //超标显示规则，10-隐藏|20-正常显示|21-超标显示
                String overproofshow = orgPolicyManage.getOverproofShow().toString();// 超标显示规则，10-隐藏|20-正常显示|21-超标显示
                for (int i = 0; i < ldtt.size(); i++) {
                    // 三个默认值 需要重新复制，因为下面对三个变量有重新复制的操作。每次循环需要获取到初始设置。注意！！！
                    overproofdefault = orgPolicyManage.getOverproofDefault().intValue();
                    overproofBook = orgPolicyManage.getOverproofBook().intValue();
                    overproofshow = orgPolicyManage.getOverproofShow().toString();

                    DispplayTrip trip = ldtt.get(i);
                    String excessionfoStr = trip.getExcessinfo();
                    BerthPolicyInfo berthPolicyInfo = this.getBerthPolicyInfo(trip);
                    String username = usernamestr_;

                    int flag = -1;
                    // if(trip.getAirline().contains("MU") &&
                    // trip.getCangwei().equals("F")){
                    // System.out.println();
                    // }
                    long time82 = System.currentTimeMillis();
                    // @说明：未匹配到规则的定义
                    // 舱位规则：Y,D,E,G,H,I,J,K,L,M,N，F舱不在规则里，则表示未设置差旅规则。2017-17-06小会
                    PolicyTicketInfo ticketInfo = this.getNewlyRule(iffosList, trip.getAirline().substring(0, 2),
                            trip.getCangwei());
                    long time83 = System.currentTimeMillis();
                    // logger.info("-------------------------------time82-time83-------->>"+(time83-time82));
                    if (ticketInfo != null) {
                        flag = this.isMarryBerthPolicy(ticketInfo, berthPolicyInfo);
                        // if(flag>0){
                        // System.out.println("============================555555555555555================");
                        // }
                        overproofdefault = ticketInfo.getOverproofdefault().intValue();
                        overproofBook = ticketInfo.getOverproofbook().intValue();
                        overproofshow = ticketInfo.getOverproofshow().toString();
                        username = ticketInfo.getUsername();
                    }

                    if (flag != 0) {
                        // flag==-1 表示 未设置规则
                        if (flag == -1) {
                            if (overproofdefault == Overproofdefault_v2.超标.getValue()) {
                                if (StringUtils.isEmpty(excessionfoStr)) {
                                    excessionfoStr = (StringUtils.isEmpty(excessionfoStr) ? "" : excessionfoStr + "~")
                                            + Excessinfo.未设置差旅规则.getOperateType() + "_" + overproofBook + "_"
                                            + username;
                                    if (StringUtil.isEmpty(trip.getShowRule())) {
                                        trip.setShowRule(overproofshow);
                                    } else {
                                        if (trip.getShowRule().equals(String.valueOf(OverproofShow.超标显示.getValue()))
                                                && overproofshow.equals(String.valueOf(OverproofShow.隐藏.getValue()))) {
                                            trip.setShowRule(overproofshow);
                                        } else if (trip.getShowRule().equals(
                                                String.valueOf(OverproofShow.正常显示.getValue()))
                                                && overproofshow.equals(String.valueOf(OverproofShow.超标显示.getValue()))) {
                                            trip.setShowRule(overproofshow);
                                        }
                                    }
                                } else {
                                    // 不用处理 上个超标信息已有
                                    String newExcessinfo = Excessinfo.未设置差旅规则.getOperateType() + "_" + overproofBook
                                            + "_" + username;
                                    String newexcessionfoStr = "";
                                    if (StringUtils.isNotEmpty(excessionfoStr)) {
                                        String[] excessions = excessionfoStr.split("\\~");
                                        for (int j = 0; j < excessions.length; j++) {
                                            String excession = excessions[j];
                                            if (!excession.equals("0")) {
                                                newexcessionfoStr += excession + "~";
                                            }
                                        }
                                    }
                                    excessionfoStr = newexcessionfoStr + newExcessinfo;

                                    if (!(StringUtils.isNotEmpty(newexcessionfoStr) && newexcessionfoStr
                                            .contains(newExcessinfo))) {
                                        newexcessionfoStr = (StringUtils.isEmpty(newexcessionfoStr) ? ""
                                                : newexcessionfoStr + "~") + newExcessinfo;
                                        if (StringUtil.isEmpty(trip.getShowRule())) {
                                            trip.setShowRule(overproofshow);
                                        } else {
                                            if (trip.getShowRule()
                                                    .equals(String.valueOf(OverproofShow.超标显示.getValue()))
                                                    && overproofshow
                                                            .equals(String.valueOf(OverproofShow.隐藏.getValue()))) {
                                                trip.setShowRule(overproofshow);
                                            } else if (trip.getShowRule().equals(
                                                    String.valueOf(OverproofShow.正常显示.getValue()))
                                                    && overproofshow.equals(String.valueOf(OverproofShow.超标显示
                                                            .getValue()))) {
                                                trip.setShowRule(overproofshow);
                                            }
                                        }
                                    }
                                }

                            } else {
                                if (StringUtils.isEmpty(excessionfoStr)) {
                                    String newExcessinfo = Excessinfo.可预定.getOperateType() + "";
                                    if (!(StringUtils.isNotEmpty(excessionfoStr) && excessionfoStr
                                            .contains(newExcessinfo))) {
                                        excessionfoStr = (StringUtils.isEmpty(excessionfoStr) ? "" : excessionfoStr
                                                + "~")
                                                + newExcessinfo;
                                    }
                                } else {
                                    // 不用处理 上个超标信息已有
                                }
                            }
                            trip.setExcessinfo(excessionfoStr);
                        } else {
                            String newExcessinfo = flag + "_" + overproofBook + "_" + username;
                            String newexcessionfoStr = "";
                            if (StringUtils.isNotEmpty(excessionfoStr)) {
                                String[] excessions = excessionfoStr.split("\\~");
                                for (int j = 0; j < excessions.length; j++) {
                                    String excession = excessions[j];
                                    if (!excession.equals("0")) {
                                        newexcessionfoStr += excession + "~";
                                    }
                                }
                            }
                            excessionfoStr = newexcessionfoStr + newExcessinfo;
                            if (!(StringUtils.isNotEmpty(newexcessionfoStr) && newexcessionfoStr
                                    .contains(newExcessinfo))) {
                                newexcessionfoStr = (StringUtils.isEmpty(newexcessionfoStr) ? "" : newexcessionfoStr
                                        + "~")
                                        + newExcessinfo;
                                if (StringUtil.isEmpty(trip.getShowRule())) {
                                    trip.setShowRule(overproofshow);
                                } else {
                                    if (trip.getShowRule().equals(String.valueOf(OverproofShow.超标显示.getValue()))
                                            && overproofshow.equals(String.valueOf(OverproofShow.隐藏.getValue()))) {
                                        trip.setShowRule(overproofshow);
                                    } else if (trip.getShowRule().equals(String.valueOf(OverproofShow.正常显示.getValue()))
                                            && overproofshow.equals(String.valueOf(OverproofShow.超标显示.getValue()))) {
                                        trip.setShowRule(overproofshow);
                                    }
                                }
                            }
                            trip.setExcessinfo(excessionfoStr);
                        }
                        tripList_minprices.add(trip);
                    } else {
                        if (StringUtils.isEmpty(excessionfoStr)) {
                            String newExcessinfo = Excessinfo.可预定.getOperateType() + "";
                            if (!(StringUtils.isNotEmpty(excessionfoStr) && excessionfoStr.contains(newExcessinfo))) {
                                excessionfoStr = (StringUtils.isEmpty(excessionfoStr) ? "" : excessionfoStr + "~")
                                        + newExcessinfo;
                            }
                        }
                        trip.setExcessinfo(excessionfoStr);
                        tripList_minprices.add(trip);
                    }
                }
                long time73 = System.currentTimeMillis();
                logger.info("-------------------------------------73-72-->>" + (time73 - time72));
                ldtt = tripList_minprices;
                tripList_minprices = new ArrayList<DispplayTrip>();
            }
            tripList_minprices = ldtt;
        } else {
            tripList_minprices = ldtt;
        }

        long time8 = System.currentTimeMillis();
        logger.info("-------舱位--------->>" + (time8 - time7));

        List<DispplayTrip> tripList_minprices_new = filterEnd(tripList_minprices);

        long time9 = System.currentTimeMillis();
        logger.info("------显示赋值--------->>" + (time9 - time8));

        for (int i = 0; i < tripList_minprices.size(); i++) {
            System.out.println(tripList_minprices.get(i).getAirline() + "____" + tripList_minprices.get(i).getCangwei()
                    + "___" + tripList_minprices.get(i).getPrice() + "_____"
                    + tripList_minprices.get(i).getExcessinfo() + "___" + tripList_minprices.get(i).getShowRule());
        }

        Long time2 = System.currentTimeMillis();
        // System.out.println("差旅规则过滤时间:" + (time2 - time1));
        logger.info(DateUtil.date2Str(new Date(), "yyyy-MM-dd HH:mm:ss"), "  差旅规则过滤时间:" + (time2 - time1));
        return tripList_minprices_new;
    }

    public List<DispplayTrip> filterEnd(List<DispplayTrip> tripList_minprices) {
        List<DispplayTrip> tripList_minprices_new = new ArrayList<DispplayTrip>();
        for (int j = 0; j < tripList_minprices.size(); j++) {
            DispplayTrip dispplayTrip = tripList_minprices.get(j);
            // if(dispplayTrip.getAirline().contains("MU5122") &&
            // dispplayTrip.getCangwei().equals("I")){
            // System.out.println();
            // }
            if (StringUtils.isEmpty(dispplayTrip.getExcessinfo()) || dispplayTrip.getExcessinfo().equals("0")) {
                dispplayTrip.setExcessinfo(Excessinfo.可预定.getOperateType() + "");
                dispplayTrip.setShowRule("");
            }
            if (StringUtils.isNotEmpty(dispplayTrip.getExcessinfo()) && dispplayTrip.getExcessinfo().length() > 1) {
                String info[] = dispplayTrip.getExcessinfo().split("~");
                String infostr = "";
                for (int i = 0; i < info.length; i++) {
                    if (info[i].contains("_")) {
                        infostr += info[i];
                    }
                }
                if (StringUtil.isNotEmpty(infostr)
                        && Integer.valueOf(infostr.split("_")[0]) > Excessinfo.可预定.getOperateType()) {
                    if (dispplayTrip.getShowRule() != null
                            && dispplayTrip.getShowRule().equals(String.valueOf(OverproofShow.隐藏.getValue()))) {
                        // tripList_minprices.remove(idd);
                        // double ceil =
                        // Math.ceil((dispplayTrip.getPrice().doubleValue() /
                        // dispplayTrip.getYprice().doubleValue()) * 100);
                        // int discountrateForY =
                        // Double.valueOf(ceil).intValue();
                        // System.out.println("______________________--"+dispplayTrip.getAirline()+"____"+
                        // dispplayTrip.getCangwei()+"___"+
                        // dispplayTrip.getExcessinfo()+"___"+dispplayTrip.getShowRule()+"________"+discountrateForY);
                    } else {
                        tripList_minprices_new.add(dispplayTrip);
                    }
                } else {
                    tripList_minprices_new.add(dispplayTrip);
                }
            } else {
                tripList_minprices_new.add(dispplayTrip);
            }
        }
        return tripList_minprices_new;
    }

    /**
     * 舱位过滤使用转换的方法
     * 
     * @param trip
     * @return
     */
    protected BerthPolicyInfo getBerthPolicyInfo(DispplayTrip trip) {
        BerthPolicyInfo berthPolicyInfo = new BerthPolicyInfo();
        berthPolicyInfo.setAirCompany(trip.getAirline().split("\\$")[0].substring(0, 2));
        // 匹配汉字
        // String orgcity_ = getDictCodeByNameFromCache("CITY_3CHAR",
        // trip.getOrgcity()); // 根据汉字找三字码
        // String decity_ = getDictCodeByNameFromCache("CITY_3CHAR",
        // trip.getDetcity()); // 根据汉字找三字码

        String orgcity_ = this.GetPortNameByCode(trip.getOrgcity());
        String decity_ = this.GetPortNameByCode(trip.getDetcity());
        if (StringUtils.isNotEmpty(orgcity_) && StringUtils.isNotEmpty(orgcity_)) {
            berthPolicyInfo.setAirline(orgcity_ + "-" + decity_);
        }
        berthPolicyInfo.setFlightnum(trip.getAirline().split("\\$")[0]);
        berthPolicyInfo.setBerth(trip.getCangwei().split("\\^")[0]);
        return berthPolicyInfo;
    }

    public String GetPortNameByCode(String code) {
        HashMap<String, String> hashMap = (HashMap<String, String>) RedisCacheManager.get(
                Const.APP_MAP_3CHAR_AIRPORTNAME, Map.class);
        Iterator<String> keys = hashMap.keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            if (key.equals(code)) {
                return hashMap.get(key);
            }
        }
        return code;
    }

    // -------------------------- 规则排序 相关代码-----------
    /**
     * 仓位规则排序
     * 
     * @param cangweiPolicyList
     *            规则list by（desc：降序 asc：升序）
     */
    public List<PolicyTicketInfo> compareByDate(List<PolicyTicketInfo> cangweiPolicyList, String by) {
        /*
         * Collections.sort(cangweiPolicyList,new
         * Comparator<PolicyTicketInfo>(){ public int compare(PolicyTicketInfo
         * arg0, PolicyTicketInfo arg1) {
         * if(arg0.getOrgruletype().compareTo(arg1.getOrgruletype())==0){ return
         * arg0.getUpdatetime().compareTo(arg1.getUpdatetime()); }else{ return
         * arg0.getOrgruletype().compareTo(arg1.getOrgruletype()); } } });
         */
        String[] sortNameArr = { "orgruletype", "updatetime" };
        // 按orgruletype降序、updatetime降序
        boolean flag = true;
        if ("desc".equals(by)) {
            flag = false; // 降序
        }
        boolean[] isAscArr = { flag, flag };
        sort(cangweiPolicyList, sortNameArr, isAscArr);
        return cangweiPolicyList;
    }

    /**
     * 给list的每个属性都指定是升序还是降序
     * 
     * @param list
     * @param sortnameArr
     *            参数数组
     * @param typeArr
     *            每个属性对应的升降序数组， true升序，false降序
     */
    public static <E> void sort(List<E> list, final String[] sortnameArr, final boolean[] typeArr) {
        if (sortnameArr.length != typeArr.length) {
            throw new RuntimeException("属性数组元素个数和升降序数组元素个数不相等");
        }
        Collections.sort(list, new Comparator<E>() {
            public int compare(E a, E b) {
                int ret = 0;
                try {
                    for (int i = 0; i < sortnameArr.length; i++) {
                        ret = compareObject(sortnameArr[i], typeArr[i], a, b);
                        if (0 != ret) {
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return ret;
            }
        });
    }

    /**
     * 对2个对象按照指定属性名称进行排序
     * 
     * @param sortname
     *            属性名称
     * @param isAsc
     *            true升序，false降序
     * @param a
     * @param b
     * @return
     * @throws Exception
     */
    private static <E> int compareObject(final String sortname, final boolean isAsc, E a, E b) throws Exception {
        int ret;
        Object value1 = forceGetFieldValue(a, sortname);
        Object value2 = forceGetFieldValue(b, sortname);
        String str1 = value1.toString();
        String str2 = value2.toString();
        if (value1 instanceof Number && value2 instanceof Number) {
            int maxlen = Math.max(str1.length(), str2.length());
            str1 = addZero2Str((Number) value1, maxlen);
            str2 = addZero2Str((Number) value2, maxlen);
        } else if (value1 instanceof Date && value2 instanceof Date) {
            long time1 = ((Date) value1).getTime();
            long time2 = ((Date) value2).getTime();
            int maxlen = Long.toString(Math.max(time1, time2)).length();
            str1 = addZero2Str(time1, maxlen);
            str2 = addZero2Str(time2, maxlen);
        }
        if (isAsc) {
            ret = str1.compareTo(str2);
        } else {
            ret = str2.compareTo(str1);
        }
        return ret;
    }

    /**
     * 获取指定对象的指定属性值（去除private,protected的限制）
     * 
     * @param obj
     *            属性名称所在的对象
     * @param fieldName
     *            属性名称
     * @return
     * @throws Exception
     */
    public static Object forceGetFieldValue(Object obj, String fieldName) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        Object object = null;
        boolean accessible = field.isAccessible();
        if (!accessible) {
            // 如果是private,protected修饰的属性，需要修改为可以访问的
            field.setAccessible(true);
            object = field.get(obj);
            // 还原private,protected属性的访问性质
            field.setAccessible(accessible);
            return object;
        }
        object = field.get(obj);
        return object;
    }

    /**
     * 给数字对象按照指定长度在左侧补0.
     * 
     * 使用案例: addZero2Str(11,4) 返回 "0011", addZero2Str(-18,6)返回 "-000018"
     * 
     * @param numObj
     *            数字对象
     * @param length
     *            指定的长度
     * @return
     */
    public static String addZero2Str(Number numObj, int length) {
        NumberFormat nf = NumberFormat.getInstance();
        // 设置是否使用分组
        nf.setGroupingUsed(false);
        // 设置最大整数位数
        nf.setMaximumIntegerDigits(length);
        // 设置最小整数位数
        nf.setMinimumIntegerDigits(length);
        return nf.format(numObj);
    }

    // --------------
    /**
     * 按照指定方式排序
     * 
     * @param by
     * @param tripList
     * @return
     */
    protected List<DispplayTrip> sortCase(int by, List<DispplayTrip> tripList) {
        Comparator<Object> comparator = null;
        switch (by) {
        case 0: // 0-按价格升序
            comparator = new Comparator<Object>() {
                public int compare(Object a, Object b) {
                    DispplayTrip a2 = (DispplayTrip) a;
                    DispplayTrip b2 = (DispplayTrip) b;
                    double one = a2.getPrice().doubleValue();
                    double two = b2.getPrice().doubleValue();
                    // if(a2.getCangwei().equals(b2.getCangwei())){
                    // return 0;
                    // } else {
                    if (one >= two) {
                        return 1;
                    } else {
                        return -1;
                    }
                    // }

                }
            };
            break;
        case 1: // 1-按价格降序
            comparator = new Comparator<Object>() {
                public int compare(Object a, Object b) {
                    DispplayTrip a2 = (DispplayTrip) a;
                    DispplayTrip b2 = (DispplayTrip) b;
                    double one = a2.getPrice().doubleValue();
                    double two = b2.getPrice().doubleValue();
                    // if (a2.getCangwei().equals(b2.getCangwei())) {
                    // return 0;
                    // } else {
                    if (one < two) {
                        return 1;
                    } else {
                        return -1;
                    }
                    // }
                }
            };
        case 2: // 2-按时间升序
            comparator = new Comparator<Object>() {
                public int compare(Object a, Object b) {
                    DispplayTrip a2 = (DispplayTrip) a;
                    DispplayTrip b2 = (DispplayTrip) b;
                    Date one = a2.getStartdate();
                    Date two = b2.getStartdate();
                    if (one.compareTo(two) < 0) {
                        return -1;
                    } else if (one.compareTo(two) == 0) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
            };
            break;
        case 3: // 3-按时间降序
            comparator = new Comparator<Object>() {
                public int compare(Object a, Object b) {
                    DispplayTrip a2 = (DispplayTrip) a;
                    DispplayTrip b2 = (DispplayTrip) b;
                    Date one = a2.getStartdate();
                    Date two = b2.getStartdate();
                    if (one.compareTo(two) > 0) {
                        return -1;
                    } else if (one.compareTo(two) == 0) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
            };
            break;
        }
        Collections.sort(tripList, comparator);
        return tripList;
    }

    @Override
    public TAcOrg getSuperTAcOrg(Long orgId) {
        TAcOrg org = null;
        org = (TAcOrg) comDao.queryForEntity(orgId, TAcOrg.class);// 子公司
        org = (TAcOrg) comDao.queryForEntity(Long.parseLong(org.getCompany()), TAcOrg.class);// 总公司

        return org == null ? null : org;
    }
    
    /**
     * 查询维护的超标理由
     * @param type
     */
    @Override
    public List<TExcessManage> getExcess() {
        String hql = " from TExcessManage r where type < 1000";
        List<TExcessManage> list = comDao.queryForList(hql);
        if (list.size() > 0) {
            return list;
        } else {
            return null;
        }
    }

}
