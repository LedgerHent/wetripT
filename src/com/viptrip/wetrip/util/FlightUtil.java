package com.viptrip.wetrip.util;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.viptrip.util.PageParam;
import com.viptrip.util.Pager;
import com.viptrip.util.StringUtil;
import com.viptrip.wetrip.model.flight.RespData_GetFlightList;

/**
 * 航班工具类
 * @author selfwhisper
 *
 */
public class FlightUtil {
	/**
	 * 计算经停次数
	 * @param ffstr
	 * @return
	 */
	public static int countStops(String ffstr){
		int result = 0;
		if(!StringUtil.isEmpty(ffstr)){
			String[] split = ffstr.split("^");
			result = split.length;
		}
		return result;
	}
	
	/**
	 * 将航班list按照航班号分页
	 * @param list
	 * @param pp
	 */
	public static Pager<RespData_GetFlightList> countFlightListPager(List<RespData_GetFlightList> list,PageParam pp){
		Pager<RespData_GetFlightList> pager = null;
		if(null!=list&&list.size()>0){
			Set<String> flightNos = new LinkedHashSet<String>();//保存所有的航班号
			List<Integer> subscript = new ArrayList<Integer>();//保存每个航班号的第一个下标
			for(int i=0;i<list.size();i++){
				RespData_GetFlightList respData_GetFlightList = list.get(i);
				String flightNo = respData_GetFlightList.getFlightNo();
				if(!flightNos.contains(flightNo)){
					subscript.add(i);
					flightNos.add(flightNo);
				}
			}
			int totalCount = subscript.size();
			pager = new Pager<RespData_GetFlightList>(pp.getPageNum(),pp.getNumPerPage(),totalCount);
			int pageNum = pager.getPageNum();
			int pageSize = pager.getPageSize();
			int totalPage = (int)Math.ceil((double)totalCount/pageSize);//总页数
			int start = (pageNum-1)*pageSize;//开始记录数
			int end = pageNum>=totalPage?totalCount:pageNum*pageSize;//结束记录数
			//设置list的结束下标
			if(end==totalCount){//如果是最后一页 结束下标直接为list的大小
				end = list.size();
			}else{//不是最后一页 
				end = subscript.get(end);
			}
			list = new ArrayList<>(list.subList(subscript.get(start), end));
			pager.setList(list);
		}else{
			pager = new Pager<>(pp.getPageNum(), pp.getNumPerPage(), 0);
			pager.setList(list);
		}
		return pager;
	}
}
