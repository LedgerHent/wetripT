package com.viptrip.wetrip.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import com.viptrip.util.DateUtil;
import com.viptrip.util.StringUtil;
import com.viptrip.wetrip.dao.ext.ComDao;
import com.viptrip.wetrip.entity.TAcClass;
import com.viptrip.wetrip.entity.TEndorse;
import com.viptrip.wetrip.entity.TNotifyPartyInformation;
import com.viptrip.wetrip.entity.TPreferentialRules;
import com.viptrip.wetrip.service.IComService;
@Service
public class ComService implements IComService{
	private static Logger logger = LoggerFactory.getLogger(ComService.class);
	@Resource
	private ComDao dao;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * 获取 三字码-城市名 map
	 * @return
	 */
	public Map<String,String> get3CharCityMap(){
		Map<String,String> map = new HashMap<>();
		String sql = "select dictcode,dictname from t_ac_dictdeta where dicttype in (1,7)";
		@SuppressWarnings("unchecked")
		List<Object[]> queryBySQL = (List<Object[]>) dao.queryBySQL(sql, null);
		if(null!=queryBySQL && queryBySQL.size()>0){
			for(Object[] row:queryBySQL){
				map.put((String)row[0], (String)row[1]);
			}
		}
		return map;
	}
	
	/**
	 * 获取 三字码-机场名 map
	 * @return
	 */
	public Map<String,String> get3CharAirportNameMap(){
		Map<String,String> map = new HashMap<>();
		String sql = "select dictcode,dictname from t_ac_dictdeta where dicttype in (8,42)";
		@SuppressWarnings("unchecked")
		List<Object[]> queryBySQL = (List<Object[]>) dao.queryBySQL(sql, null);
		if(null!=queryBySQL && queryBySQL.size()>0){
			for(Object[] row:queryBySQL){
				map.put((String)row[0], (String)row[1]);
			}
		}
		return map;
	}
	
	/**
	 * 获取 机型-机型名称 map
	 * @return
	 */
	public Map<String,String> getPlaneTypeMap(){
		Map<String,String> map = new HashMap<>();
		String sql = "select ptype,pname from t_planetype";
		@SuppressWarnings("unchecked")
		List<Object[]> queryBySQL = (List<Object[]>) dao.queryBySQL(sql, null);
		if(null!=queryBySQL && queryBySQL.size()>0){
			for(Object[] row:queryBySQL){
				map.put((String)row[0], (String)row[1]);
			}
		}
		return map;
	}
	
	/**
	 * 获取 航空公司二字码_舱位码-舱位名称 map
	 * @return
	 */
	public Map<String,String> getCompanyCabinname(){
		Map<String,String> map = new HashMap<>();
		String sql = "select classname,aircompany,classcode from t_ac_class group by classname,aircompany,classcode";
		@SuppressWarnings("unchecked")
		List<Object[]> queryBySQL = (List<Object[]>) dao.queryBySQL(sql, null);
		if(null!=queryBySQL && queryBySQL.size()>0){
			for(Object[] row:queryBySQL){
				map.put((String)row[1] + "_" + (String)row[2], (String)row[0]);
			}
		}
		return map;
		
	}
	
	/**
     * 获取各航空公司舱位 对应的全价舱舱位
     * @return
     */
    public Map<String,TAcClass> getAllFullTacClass(){
        Map<String,TAcClass> fullTacClass=new HashMap<String, TAcClass>();
        Map<String,TAcClass> allFullTacClass=new HashMap<String, TAcClass>();
        String strSQL = "from TAcClass t";
        List<TAcClass> userList = dao.queryForList(strSQL);
        for (int i = 0; i < userList.size(); i++) {
            TAcClass tacclass=userList.get(i);
            if(tacclass.getIsfull().equals("1")){
                fullTacClass.put(tacclass.getAircompany()+"_"+tacclass.getClasstype(), tacclass);
            }
        }
        for (int i = 0; i < userList.size(); i++) {
            TAcClass tacclass=userList.get(i);
            TAcClass tAcClass=fullTacClass.get(tacclass.getAircompany()+"_"+tacclass.getClasstype());
            allFullTacClass.put(tacclass.getAircompany()+"_"+tacclass.getClasscode(), tAcClass);
        }
        return allFullTacClass;
    }
	/**
	 * 获取 航空公司二字码_舱位码-舱位等级 map
	 * @return
	 */
	public Map<String,String> getCompanyCabinlevel(){
		Map<String,String> map = new HashMap<>();
		String sql = "select classtype,aircompany,classcode from t_ac_class group by classtype,aircompany,classcode";
		@SuppressWarnings("unchecked")
		List<Object[]> queryBySQL = (List<Object[]>) dao.queryBySQL(sql, null);
		if(null!=queryBySQL && queryBySQL.size()>0){
			for(Object[] row:queryBySQL){
				map.put((String)row[1] + "_" + (String)row[2], (String)row[0]);
			}
		}
		return map;
		
	}
	
	/**
	 * 获取 航空公司二字码_舱位名称-舱位代码list  map
	 * 例如 map的key为CA_经济舱 value为['W','T','S','V']
	 * 
	 * @return
	 */
	public Map<String,List<String>> getCompanyCabinCode(){
		Map<String,List<String>> map = new HashMap<>();
		String sql = "select classname,aircompany,classcode from t_ac_class group by classname,aircompany,classcode";
		@SuppressWarnings("unchecked")
		List<Object[]> queryBySQL = (List<Object[]>) dao.queryBySQL(sql, null);
		if(null!=queryBySQL && queryBySQL.size()>0){
			for(Object[] row:queryBySQL){
				String key = (String)row[1] + "_" + (String)row[0];
				if(null!=map.get(key)){
					List<String> list = map.get(key);
					if(list.contains((String)row[2])){
						continue;
					}else{
						list.add((String)row[2]);
					}
				}else{
					List<String> list = new ArrayList<>();
					list.add((String)row[2]);
					map.put(key, list);
				}
			}
		}
		return map;
	}

	/**
	 * 获取所有票规
	 */
	public List<TEndorse> getAllEndorse() {
		 //String hql = "from TEndorse where isdel=0 and effectivedate < ? and expirydate > ?";
		 try {
			Date date = DateUtil.SDF_yyyy_MM_dd.parse(DateUtil.SDF_yyyy_MM_dd.format(new Date()));
			LinkedHashMap<String, String> conditions = new LinkedHashMap<String, String>();
			conditions.put("isdel", "=");
			conditions.put("effectivedate", "<");
			conditions.put("expirydate", ">");
			List<TEndorse> list = dao.queryForList(TEndorse.class, conditions, new Object[]{"0",date,date});
			return list;
		} catch (ParseException e) {
			logger.error(StringUtil.getExceptionStr(e));
		}
		return null;
	}

	
	public List<TNotifyPartyInformation> getByContactsByOid(Long oid,Integer type){
		LinkedHashMap<String, String> conditions = new LinkedHashMap<String, String>();
		conditions.put("orderid", "=");
		conditions.put("nationalityStatus", "=");
		conditions.put("position", "=");
		List<TNotifyPartyInformation> list = dao.queryForList(TNotifyPartyInformation.class, conditions, new Object[]{oid.intValue(),"0",(1==type?"国外":"国内") + "通知人"});
		return list;
	}


    @Override
    public List<String> get3CharCity() {
        List<String> list = new ArrayList<String>();
        String sql = "select dictcode,dictname from t_ac_dictdeta where dicttype in (1,7)";
        @SuppressWarnings("unchecked")
		List<Object[]> queryBySQL = (List<Object[]>) dao.queryBySQL(sql, null);
        if (null != queryBySQL && queryBySQL.size() > 0) {
            for (Object[] row : queryBySQL) {
                list.add((String) row[0] + "_" + (String) row[1]);
            }
        }
        return list;
    }

	@Override
    public List<TPreferentialRules> quyRules() {
        return dao.queryForList(TPreferentialRules.class);
    }
	
    /**
     * 获取航司二字码-航司简称map
     * @return
     */
    public Map<String,String> getAC2NameMap(){
    	Map<String,String> map = new HashMap<>();
    	String sql = "select dictcode,dictname from t_ac_dictdeta t where dicttype=4";
    	@SuppressWarnings("unchecked")
		List<Object[]> queryBySQL = (List<Object[]>) dao.queryBySQL(sql, null);
		if(null!=queryBySQL && queryBySQL.size()>0){
			for(Object[] row:queryBySQL){
				map.put((String)row[0], (String)row[1]);
			}
		}
    	return map;
    }
    
    /**
     * 查询客户结算类型
     */
    public Map<String,String> getPayTypeMap(){
        Map<String,String> map = new HashMap<>();
        String sql = "select d.DICTCODE,d.DICTNAME from T_AC_DICTTYPE t, T_AC_DICTDETA d where "
                + " t.DICTTYPEID = d.dicttype and t.dicttypecode = 'PAY_TYPE'";
        @SuppressWarnings("unchecked")
        List<Object[]> queryBySQL = (List<Object[]>) dao.queryBySQL(sql, null);
        if(null!=queryBySQL && queryBySQL.size()>0){
            for(Object[] row:queryBySQL){
                map.put((String)row[0], (String)row[1]);
            }
        }
        return map;
    }
    
    public Map<String,String> getIdcardTypeMap(){
        Map<String,String> map = new HashMap<>();
        String sql="select d.DICTCODE,d.DICTNAME from T_AC_DICTTYPE t, T_AC_DICTDETA d where "
                +" t.DICTTYPEID = d.dicttype and t.dicttypecode = 'IDCARD_TYPE' order by d.dictcode asc";
        @SuppressWarnings("unchecked")
        List<Object[]> queryBySQL = (List<Object[]>) dao.queryBySQL(sql, null);
        if(null!=queryBySQL && queryBySQL.size()>0){
            for(Object[] row:queryBySQL){
                map.put((String)row[0], (String)row[1]);
            }
        }
        return map;
    }
   
    public Map<String,String> getPersonnelTypeMap(){
        Map<String,String> map = new HashMap<>();
        String sql="select d.DICTCODE,d.DICTNAME from T_AC_DICTTYPE t, T_AC_DICTDETA d where "
                +" t.DICTTYPEID = d.dicttype and t.dicttypecode = 'PERSONNEL_TYPE' order by d.dictcode asc";
        @SuppressWarnings("unchecked")
        List<Object[]> queryBySQL = (List<Object[]>) dao.queryBySQL(sql, null);
        if(null!=queryBySQL && queryBySQL.size()>0){
            for(Object[] row:queryBySQL){
                map.put((String)row[0], (String)row[1]);
            }
        }
        return map;
    }
}
