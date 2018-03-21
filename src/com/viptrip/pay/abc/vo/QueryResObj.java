package com.viptrip.pay.abc.vo;

import java.lang.reflect.ParameterizedType;

public abstract class QueryResObj{
//	out.println("PayTypeID      = [" + json.GetKeyValue("PayTypeID") + "]<br/>");
//	out.println("OrderNo      = [" + json.GetKeyValue("OrderNo") + "]<br/>");
//	out.println("OrderDate      = [" + json.GetKeyValue("OrderDate") + "]<br/>");
//	out.println("OrderTime      = [" + json.GetKeyValue("OrderTime") + "]<br/>");
//	out.println("RefundAmount      = [" + json.GetKeyValue("RefundAmount") + "]<br/>");
//	out.println("Status      = [" + json.GetKeyValue("Status") + "]<br/>");

//	@SuppressWarnings("rawtypes")
//	protected Class clz;
	
	public QueryResObj() {
//		this.clz = getTypeClass();
	}

	@SuppressWarnings("rawtypes")
	protected Class getTypeClass(){
         Class clazz = (Class)((ParameterizedType) this.getClass().getGenericSuperclass())
            .getActualTypeArguments()[0];
         return clazz;
    }
}
