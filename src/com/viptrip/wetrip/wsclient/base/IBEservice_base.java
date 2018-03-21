package com.viptrip.wetrip.wsclient.base;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.viptrip.resource.Const;
import com.viptrip.util.PropertiesUtils;

public class IBEservice_base extends Service implements Serializable {

	protected IBEservice_base(URL wsdlDocumentLocation, QName serviceName) {
		super(wsdlDocumentLocation, serviceName);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 8901573504596182422L;
	
	public String partURL;

	public String getPartURL() {
		StringBuffer url=new StringBuffer();
		try {
			url.append(PropertiesUtils.getProperty(Const.PRO_IBESERVER_IP,Const.FILE_IBESERVER));
			url.append(PropertiesUtils.getProperty(Const.PRO_IBESERVER_PORT,Const.FILE_IBESERVER));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		partURL=url.toString();
		return partURL;
	}
}
