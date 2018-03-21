package com.viptrip.pay.wx.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.viptrip.util.JSON;
import com.viptrip.util.StringUtil;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class XMLParser {
	private static Logger log = LoggerFactory.getLogger(XMLParser.class);

	/**
	 * 将xml数据转换成对应的实体类
	 * @param xmlString
	 * @param clz
	 * @return
	 */
	public static <T> T convertToObjFromXML(String xmlString,Class<T> clz,String charset){
		T instance = null;
		try {
//			instance = clz.newInstance();
			Map<String, Object> map = getMapFromXML(xmlString,charset);
			/*if(null != map && !map.isEmpty()){
				Field[] fields = clz.getDeclaredFields();
				for (int i = 0; i < fields.length; i++) {
					String fname = fields[i].getName();
					Object obj = map.get(fname);
					if(null!=obj){
						String setterName = "set" + (fname.charAt(0)+"").toUpperCase() + fname.substring(1);
						try {
							Method method = clz.getDeclaredMethod(setterName, fields[i].getType());
							method.invoke(instance, obj);
						} catch (Exception e) {
							log.error("调用" + clz.getName() + "的" + setterName + "方法设值失败\r\n" + StringUtil.getExceptionStr(e));
							continue;
						}
					}
				}
			}*/
			instance = JSON.get().fromJson(JSON.get().toJson(map), clz);
		} catch (Exception e) {
			log.error("XML数据解析为对象发生错误！\r\n" + StringUtil.getExceptionStr(e));
		}
		return instance;
	}
	
	/**
	 * 将xml字符串 转换为map对象
	 * @param xmlString
	 * @return
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 */
    public static Map<String,Object> getMapFromXML(String xmlString,String charset) throws ParserConfigurationException, IOException, SAXException {

        //这里用Dom的方式解析回包的最主要目的是防止API新增回包字段
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStream is =  getStringStream(xmlString,charset);
        Document document = builder.parse(is);

        //获取到document里面的全部结点
        NodeList allNodes = document.getFirstChild().getChildNodes();
        Node node;
        Map<String, Object> map = new HashMap<String, Object>();
        int i=0;
        while (i < allNodes.getLength()) {
            node = allNodes.item(i);
            if(node instanceof Element){
                map.put(node.getNodeName(),node.getTextContent());
            }
            i++;
        }
        return map;

    }
    
    public static SortedMap<String, Object> getSortedMapFromXML(String xmlString,String charset){
    	SortedMap<String, Object> result = new TreeMap<>();
    	Map<String, Object> map = null;
    	try {
    		map = getMapFromXML(xmlString, charset);
		} catch (Exception e) {
			log.error(StringUtil.getExceptionStr(e));
		}
    	String[] arr = new String[map.size()];
    	map.keySet().toArray(arr);
    	Arrays.sort(arr);
    	for(String key:arr){
    		result.put(key, map.get(key));
    	}
    	return result;
    }
    
    private static InputStream getStringStream(String sInputString,String charset) throws UnsupportedEncodingException {
        ByteArrayInputStream tInputStringStream = null;
        if (sInputString != null && !sInputString.trim().equals("")) {
            tInputStringStream = new ByteArrayInputStream(sInputString.getBytes(charset));
        }
        return tInputStringStream;
    }

}
