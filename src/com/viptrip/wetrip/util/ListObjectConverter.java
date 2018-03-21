package com.viptrip.wetrip.util;

import java.util.ArrayList;
import java.util.List;

/**
 * list内对象转换
 * @author selfwhisper
 *
 */
public class ListObjectConverter {
	/**
	 * 将list内的对象进行转换
	 * @param from
	 * @param to
	 * @param convertor
	 */
	public static <F, T> List<T> convert(List<F> from,List<T> to,Convertor<F,T> convertor){
		if(null!=from&&from.size()>0){
			if(null==to||to.size()<=0){
				to = new ArrayList<T>();
			}
			for(F obj:from){
				T t = convertor.convert(obj);
				to.add(t);
			}
		}
		return to;
	}
	
	/**
	 * 将F对象的list列表转成T对象
	 * @param from
	 * @param to
	 * @param convertor
	 */
	public static <F, T> T convert(List<F> from,Convertor<List<F>,T> convertor){
		return convertor.convert(from);
	}
}
