package com.viptrip.wetrip.service.impl;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.viptrip.util.Pager;
import com.viptrip.wetrip.dao.InsuranceDao;
import com.viptrip.wetrip.dao.ext.ComDao;
import com.viptrip.wetrip.entity.TAcInsurance;
import com.viptrip.wetrip.model.base.Response_PriceAndDescription;
import com.viptrip.wetrip.service.InsuranceService;
@Service
public class InsuranceServiceImpl implements InsuranceService {

	@Resource
	private InsuranceDao id;
	@Resource
	private ComDao cd;
	
	@Override
	public List<Response_PriceAndDescription> getInsuranceList() {
		// 调用查询方法
//		Iterable<TAcInsurance> allIns = id.
		String strHQL = " from TAcInsurance where upDownLine='1' ";
		List<TAcInsurance> insList =cd.queryForList(strHQL);
//		List<TAcInsurance> insList = new ArrayList<TAcInsurance>();
		List<Response_PriceAndDescription> giList = new ArrayList<Response_PriceAndDescription>();
		//将返回iterable转换iterator
//		Iterator<TAcInsurance> iterator = allIns.iterator();
		//遍历iterator,存入list中
//		while (iterator.hasNext()) {
//			TAcInsurance ins = iterator.next();
//			insList.add(ins);
//		}
		//将list结果集转换为接口指定泛型list对象
		for(int i = 0;i < insList.size();i++){
			Response_PriceAndDescription gil = new Response_PriceAndDescription();
			System.out.println(insList.get(i).toString());
			gil.id = insList.get(i).getId();
			gil.price = insList.get(i).getPrice();
			gil.description = insList.get(i).getNote();
			giList.add(gil);
		}
		return giList;
	}

}
