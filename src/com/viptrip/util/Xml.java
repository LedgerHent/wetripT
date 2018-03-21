package com.viptrip.util;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import com.viptrip.yeego.model.Response_Base;

import etuf.v1_0.model.base.output.OutputBase;
import etuf.v1_0.model.base.output.OutputResult;

public class Xml {
	@SuppressWarnings({ "unused" })
	public static <T> Boolean Serialize(T req,OutputBase out)
	{
		Boolean falg =true;
	try { 
		JAXBContext context = JAXBContext.newInstance(req.getClass());

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");// //编码格式
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);// 是否格式化生成的xml串
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);// 是否省略xm头声明信息
        StringWriter writer = new StringWriter();
        marshaller.marshal(req, writer);
        
        out.code=0;
        out.result=writer.toString();
        out.exception=null;
        
        } catch (JAXBException e) {  
        	falg=false;
            e.printStackTrace();  
        }  
		
		return true;
		
	}
	
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T, F> Boolean XmlDeserialize(Class cla,String str,OutputResult<T,F> out) throws Exception
	{   
		Boolean flag=true;
		try { 
			T Obj=null;
				JAXBContext context = JAXBContext.newInstance(cla);
		        Unmarshaller unMarshaller = context.createUnmarshaller();
		        str=str.replaceAll("&lt;", "<");
		        str=str.replaceAll("&gt;", ">");
		        ByteArrayInputStream inputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
		        
		        
		       Obj=(T)unMarshaller.unmarshal(inputStream);
		       if(Obj==null){
		    	    flag=false;
					out.setResultObj(null);
			        out.code=1;
			        out.result=str;
		       }else{
		    	    out.setResultObj(Obj);
			        out.code=0;
			        out.result="成功";
			        out.exception=null;
		       }
	       
		} catch (Exception e) {  
			flag=false;
			out.setResultObj(null);
	        out.code=1;
	        out.result=str;
	        out.exception=e;
            e.printStackTrace();  
        }  
		return flag;
		
	}
	
	public static<T> String ObjectToJson(T t)
	{
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(t);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	
	/*public static<T> T JsonToObject(Class<T> clazz,String json)
	{
		ObjectMapper mapper = new ObjectMapper();
		T t;
		try {
			t = mapper.readValue(json, clazz);
			return t;
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}*/
	
}
