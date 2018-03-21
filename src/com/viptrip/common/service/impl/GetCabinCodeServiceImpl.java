package com.viptrip.common.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.viptrip.base.common.MyEnum;
import com.viptrip.base.common.MyEnum.CabinType;
import com.viptrip.common.model.ClassCodeInfoModel;
import com.viptrip.common.model.Request_GetCabinCodeByType;
import com.viptrip.common.model.Response_GetCabinCodeByType;
import com.viptrip.common.service.GetCabinCodeService;
import com.viptrip.wetrip.dao.ext.ComDao;
@Service
@Transactional
public class GetCabinCodeServiceImpl implements GetCabinCodeService{
	@Resource
	private ComDao comDao;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Response_GetCabinCodeByType GetCabinCodeByType(Request_GetCabinCodeByType para) {
		Response_GetCabinCodeByType respones=new Response_GetCabinCodeByType();
		String sql="select t.AIRCOMPANY,t.CLASSCODE,t.CLASSTYPE,t.CLASSNAME from T_AC_CLASS t where 1=1 ";
		if(CabinType.不限.getCabinType()!=para.cabinType){
			sql+=" and t.CLASSTYPE ="+MyEnum.CabinType.getIntValue(String.valueOf(para.cabinType))+" ";
		}
		sql+=" order by CLASSCODE";
		List<Object[]> list =(List) comDao.queryBySQL(sql, null);
		if(list!=null && list.size()>0){
			List<ClassCodeInfoModel> classList=new ArrayList<ClassCodeInfoModel>();
			ClassCodeInfoModel classCode=null;
			for (Object[] object : list) {
				classCode=new ClassCodeInfoModel();
				classCode.aircompany=object[0].toString();
				classCode.cabinCode=object[1].toString();
				classCode.cabinType=MyEnum.CabinType.getIntValue(object[2].toString());
				classCode.cabinDesc=object[3].toString();
				classList.add(classCode);
			}
			respones.data=classList;
		}
		return respones;
	}

}
