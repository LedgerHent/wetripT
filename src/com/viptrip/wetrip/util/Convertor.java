package com.viptrip.wetrip.util;

import java.util.List;

import com.viptrip.ibeserver.model.DispplayTrip;

/**
 * 属性转换器  例如将Entity转成model
 * @author selfwhisper
 *
 * @param <F>
 * @param <T>
 */
public interface Convertor<F,T> {
	T convert(F from);
}
