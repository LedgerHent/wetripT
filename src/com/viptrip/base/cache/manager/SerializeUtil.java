package com.viptrip.base.cache.manager;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.google.gson.Gson;
import com.viptrip.wetrip.model.Request_GetOrderList;
import com.viptrip.wetrip.model.orderlist.ReqData_GetOrderList;

/**
 * 序列化工具
 * @author selfwhisper
 *
 */
public class SerializeUtil {
	public static byte[] serialize(Object object) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			// 序列化
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			byte[] bytes = baos.toByteArray();
			return bytes;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static Object unserialize(byte[] bytes) {
		ByteArrayInputStream bais = null;
		try {
			// 反序列化
			bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public static void main(String[] args) {
		Request_GetOrderList para = new Request_GetOrderList();
		ReqData_GetOrderList data = new ReqData_GetOrderList(1, 20, 0);
		para.setData(data );
		para.setUserId(22121);
		byte[] b = serialize(para);
		String str = para.getClass().getName() + "_" + new Gson().toJson(para);
		System.out.println("SerializeUtil.main()==str==" + str);
	}
}
