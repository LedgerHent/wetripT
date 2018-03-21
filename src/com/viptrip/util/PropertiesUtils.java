package com.viptrip.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Properties;

import org.slf4j.LoggerFactory;





import ch.qos.logback.classic.Logger;



public class PropertiesUtils {
	
	final static Logger log =  (Logger) LoggerFactory.getLogger(PropertiesUtils.class);
	
	/**
	 * 加载Properties文件 相对路径
	 * @param filename 文件名(相对路径)
	 * @return 
	 * @throws IOException
	 */
	public static synchronized Properties loadFile(String filename) throws IOException{
		Properties props = new Properties();
		InputStreamReader isFileStream = null;
		try {
//			isFileStream = new InputStreamReader(PropertiesUtils.class.getClassLoader().getResourceAsStream(filename),"UTF-8") ;
			isFileStream = new InputStreamReader(PropertiesUtils.class.getResourceAsStream(filename),"UTF-8") ;
			props.load(isFileStream);
		} catch (IOException e) {
			throw new IOException("加载文件失败！");
		}finally{
			if(isFileStream!=null){
				isFileStream.close();
			}
		}
		return props;
	}
	
	/**
	 * 加载Properties文件 绝对路径下
	 * @param filename  文件名(绝对路径)
	 * @return
	 * @throws IOException
	 */
	public static synchronized Properties loadAbsoluteFile(String filename) throws IOException{
		Properties props = new Properties();
		InputStreamReader isFileStream = null;
		try {
			isFileStream = new InputStreamReader(new FileInputStream(filename),"UTF-8") ;
			props.load(isFileStream);
		} catch (IOException e) {
			throw new IOException("加载文件失败！");
		}finally{
			if(isFileStream!=null){
				isFileStream.close();
			}
		}
		return props;
	}
	
	/**
	 * 读取Properties的map 相对路径
	 * @param filename 文件名（相对路径）
	 * @return 值
	 * @throws Exception
	 */
	public static synchronized Map<String,String> getPropertyMap(String filename) throws IOException {
		Map<String,String> map = new TreeMap<String, String>();
		if(!StringUtil.isEmpty(filename)){
			try {
				Properties props = loadFile(filename);
				@SuppressWarnings("unchecked")
				Enumeration<String> propNames = (Enumeration<String>) props.propertyNames();
				while(propNames.hasMoreElements()){
					String name = (String) propNames.nextElement();
					String value = props.getProperty(name);
					if(!StringUtil.isEmpty(name=name.trim())&&!StringUtil.isEmpty(value=value.trim())){
						map.put(name, value);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				throw new IOException("加载文件失败！");
			}
		}
		return map;
	}
	
	/**
	 * 读取Properties的map 绝对路径
	 * @param filename 文件名（绝对路径）
	 * @return 值
	 * @throws Exception
	 */
	public static synchronized Map<String,String> getAbsolutePropertyMap(String filename) throws IOException {
		Map<String,String> map = new TreeMap<String, String>();
		if(!StringUtil.isEmpty(filename)){
			try {
				Properties props = loadAbsoluteFile(filename);
				@SuppressWarnings("unchecked")
				Enumeration<String> propNames = (Enumeration<String>) props.propertyNames();
				while(propNames.hasMoreElements()){
					String name = (String) propNames.nextElement();
					String value = props.getProperty(name);
					if(!StringUtil.isEmpty(name)&&!StringUtil.isEmpty(value)){
						map.put(name, value);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				throw new IOException("加载文件失败！");
			}
		}
		return map;
	}
	
	/**
	 * 获取Properties文件的属性值 相对路径
	 * @param propname 属性名
	 * @param filename 文件名  （相对路径）
	 * @return 值
	 * @throws Exception
	 */
	public static synchronized String getProperty(String propname,String filename) throws IOException {
		String value = null;
		if(!StringUtil.isEmpty(propname)&&!StringUtil.isEmpty(filename)){
			try {
				Properties props = loadFile(filename);
				value = props.getProperty(propname);
			} catch (IOException e) {
				throw new IOException("加载文件失败！");
			}
		}
		return value;
	}
	
	/**
	 * 获取Properties文件的属性值 绝对路径
	 * @param propname 属性名
	 * @param filename 文件名  (绝对路径)
	 * @return 值
	 * @throws Exception
	 */
	public static synchronized String getAbsoluteProperty(String propname,String filename) throws IOException {
		String value = null;
		if(!StringUtil.isEmpty(propname)&&!StringUtil.isEmpty(filename)){
			try {
				Properties props = loadAbsoluteFile(filename);
				value = props.getProperty(propname);
			} catch (IOException e) {
				throw new IOException("加载文件失败！");
			}
		}
		return value;
	}
	
	/**
	 * 添加Properties属性 相对路径
	 * @param filename 文件名（ 相对路径）
	 * @param propname 属性名
	 * @param value 属性值
	 * @throws IOException
	 */
	public static synchronized Properties setProperty(String filename,String propname,String value) throws IOException{
		Properties props = null;
		if(!StringUtil.isEmpty(propname)&&!StringUtil.isEmpty(filename)){
			props = loadFile(filename);
			props.setProperty(propname, value);
		}
		return props;
	}
	
	/**
	 * 添加Properties属性 绝对路径
	 * @param filename 文件名（ 绝对路径）
	 * @param propname 属性名
	 * @param value 属性值
	 * @throws IOException
	 */
	public static synchronized Properties setAbsoluteProperty(String filename,String propname,String value) throws IOException{
		Properties props = null;
		if(!StringUtil.isEmpty(propname)&&!StringUtil.isEmpty(filename)){
			props = loadFile(filename);
			props.setProperty(propname, value);
		}
		return props;
	}
	
	/**
	 * 添加Properties属性
	 * @param props 需要写入的属性集合
	 * @param propname 属性名
	 * @param value 属性值
	 * @return
	 * @throws IOException
	 */
	public static synchronized Properties setProperty(Properties props,String propname,String value) throws IOException{
		if(props!=null){
			props.setProperty(propname, value);
		}
		return props;
	}
	
	/**
	 * 将Properties写入到文件
	 * @param filename 文件名（相对路径）
	 * @param p Properties属性
	 * @throws IOException
	 */
	public static synchronized void writeFile(String filename,Properties p) throws IOException{
		if(!StringUtil.isEmpty(filename)&&p!=null){
			OutputStreamWriter fos = null;
			filename = PropertiesUtils.class.getResource("/").getPath() + filename;
			try {
				fos = new OutputStreamWriter(new FileOutputStream(filename),"UTF-8");
				p.store(fos, null);
				fos.flush();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				if(fos!=null){
					fos.close();
				}
			}
		}
	}
	
	/**
	 * 将Properties写入到文件
	 * @param filename 文件名（绝对路径）
	 * @param p Properties属性
	 * @throws IOException
	 */
	public static synchronized void writeAbsoluteFile(String filename,Properties p) throws IOException{
		if(!StringUtil.isEmpty(filename)&&p!=null){
			OutputStreamWriter fos = null;
			try {
				fos = new OutputStreamWriter(new FileOutputStream(filename),"UTF-8");
				p.store(fos, null);
				fos.flush();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				if(fos!=null){
					fos.close();
				}
			}
		}
	}
	
	
	/*public static void clear(){
		try {
			Map<String,String> props  = getPropertyMap(Const.CARD_FILE);
			String day = props.get("day");
			String date = props.get("date");
			if(!StringUtil.isEmpty(date)){
				java.util.Date d = sdf.parse(date);
				d.setDate(d.getDate()+Integer.parseInt(day));
				java.util.Date now = sdf.parse(sdf.format(new java.util.Date()));
				if(d.compareTo(now)>=0){
					Iterator<Entry<String, String>> it = props.entrySet().iterator();
					while(it.hasNext()){
						Entry<String, String> en = it.next();
						String key = en.getKey();
						if(!"day".equals(key)&&!"date".equals(key)){
							String value = en.getValue();
							value = value.substring(value.indexOf("/")+1, value.indexOf("/")+9);
						}
					}
				}else{
					Properties p = new Properties();
					p.setProperty("date", sdf.format(new java.util.Date()));
					p.setProperty("day", "7");
					writeFile(Const.CARD_FILE, p);
				}
			}else{
				Properties p = new Properties();
				p.setProperty("date", sdf.format(new java.util.Date()));
				p.setProperty("day", "7");
				writeFile(Const.CARD_FILE, p);
			}
			
		} catch (IOException e) {
			log.error(StringUtil.getExceptionStr(e));
		} catch (ParseException e) {
			log.error(StringUtil.getExceptionStr(e));
		}
	}*/
	
	/**
	 * 读取单行配置文件list 相对路径
	 * @param filename 文件名（相对路径）
	 * @return
	 */
	public static synchronized List<String> readFileList(String filename){
		List<String> result = new ArrayList<String>();
		if(!StringUtil.isEmpty(filename)){
			BufferedReader reader = null;
			try {
				reader = new BufferedReader( new InputStreamReader(PropertiesUtils.class.getResourceAsStream(filename),"UTF-8"));
				String txt = null;
				while(null != (txt=reader.readLine())){
					if(!StringUtil.isEmpty(txt)){
						result.add(txt);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(null!=reader){
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		Collections.sort(result);
		return result;
	}
	
	/**
	 * 读取单行配置文件list 绝对路径 
	 * @param filename 文件名（绝对路径）
	 * @return
	 */
	public static synchronized List<String> readAbsoluteFileList(String filename){
		List<String> result = new ArrayList<String>();
		if(!StringUtil.isEmpty(filename)){
			BufferedReader reader = null;
			try {
				File file = new File(filename);
				if(file.exists()){
					reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
					String txt = null;
					while(null != (txt=reader.readLine())){
						if(!StringUtil.isEmpty(txt)){
							result.add(txt);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				if(null!=reader){
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return result;
	}
	
	public static void main(String[] args) throws Exception {
		
		
		String str = "﻿name_en";
		for(int i=0;i<str.length();i++){
			System.out.println(str.charAt(i) + "--" + Integer.toHexString(str.charAt(i)));
		}
		
		
	}
	
}
