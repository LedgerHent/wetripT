package com.viptrip.yeego;

import com.viptrip.intlAirticket.common.Constants;
import com.viptrip.intlAirticket.model.base.Params;
import com.viptrip.util.HttpPost;
import com.viptrip.util.JSON;
import com.viptrip.util.Xml;
import com.viptrip.yeego.model.Filter_Base;
import com.viptrip.yeego.model.Identity_1_0;
import com.viptrip.yeego.model.Request_Base;
import com.viptrip.yeego.model.Response_Base;

import etuf.v1_0.model.base.output.OutputBase;
import etuf.v1_0.model.base.output.OutputResult;

public class yeego {

	private static String _identity_1_0_Xml = "";
	
	private static String getIdentity_1_0_XML(){
		int a=0;
		if("".equals(_identity_1_0_Xml)){
			OutputBase tempLoginToxml=new OutputBase();
			Identity_1_0 identity=new Identity_1_0(Params.Code_Operator,Params.Code_Pwd,Params.Code_PassportID,Params.Code_Terminal,Params.Code_UserType);
			Boolean flag=false;
			do{
				if(a<10){
					if(Xml.Serialize(identity, tempLoginToxml)){
						_identity_1_0_Xml =tempLoginToxml.result ;
						flag=true;
				     }	
					a+=1;
				}else{
					flag=true;
				}
				
			
			}while(!flag);
		}
		return _identity_1_0_Xml;
	}
	
	public static <T extends Request_Base,K extends Response_Base,F extends Filter_Base> 
			Boolean Request(T req,OutputResult<K,String> out,F filter){
		Boolean flag=true;
		try {
			
			//限定T的类型，必须是Request_Base的子类
			//限定K的类型，必须是Response_Base的子类
			OutputBase tempRequestToXml=new OutputBase();
			if(Xml.Serialize(req, tempRequestToXml)){
				StringBuilder sb=new StringBuilder();
				OutputBase tempFilterToXml=new OutputBase();
				if(Xml.Serialize(filter, tempFilterToXml)){
						
						sb.append("identity="+getIdentity_1_0_XML());
						sb.append("&request="+tempRequestToXml.result);
						sb.append("&filter="+tempFilterToXml.result);
					
					OutputBase tempResponseToXml=new OutputBase();
					Boolean httpPost = HttpPost.httpPost(Constants.url, sb.toString(), tempResponseToXml);
					if(httpPost){
						tempResponseToXml.result=tempResponseToXml.result.substring(tempResponseToXml.result.indexOf("tempuri.org/")+14, tempResponseToXml.result.indexOf("</string"));
						if(tempResponseToXml.code==0){
							System.out.println(sb.toString());
							System.out.println(tempResponseToXml.result);
						}
						if("X".equals(filter.DataFormat)){
							flag=Xml.XmlDeserialize(out.getResultObj().getClass(),tempResponseToXml.result, out);
						}else{
							flag=JSON.JsonDeserialize(out.getResultObj().getClass(),tempResponseToXml.result, out);
						}
					}else{
						flag=false;
						out.code=tempResponseToXml.code;
						out.result=tempResponseToXml.result;
						out.exception=tempResponseToXml.exception;
					}
				}
			  
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag=false;
		}
		return flag;
	}
	
	
}
