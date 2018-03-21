package com.viptrip.wetrip.wsclient;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Dispatch;
import javax.xml.ws.EndpointReference;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

import com.viptrip.resource.Const;
import com.viptrip.util.PropertiesUtils;
import com.viptrip.wetrip.wsclient.base.IBEservice_base;

//import com.viptrip.base.util.PropertiesUtil;

/**
 * This class was generated by Apache CXF 2.7.6
 * 2013-09-30T15:32:15.363+08:00
 * Generated source version: 2.7.6
 * 本文件夹下第 7个文件，和学银 检查完成 时间：2015年6月12日09:50:01
 */
@WebServiceClient(name = "CreateIBEPNRImplService", 
                  wsdlLocation = "/new_ws/CreateIBEPNRImplPort?wsdl",
                  targetNamespace = "http://webservice.viptrip.com/") 
public class CreateIBEPNRImplService extends Service {
    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://webservice.viptrip.com/", "CreateIBEPNRImplService");
    public final static QName CreateIBEPNRImplPort = new QName("http://webservice.viptrip.com/", "CreateIBEPNRImplPort");
    static {
        URL url = null;  
        StringBuffer partURL=new StringBuffer();
		try {
			partURL.append(PropertiesUtils.getProperty(Const.PRO_IBESERVER_IP,Const.FILE_IBESERVER));
			partURL.append(PropertiesUtils.getProperty(Const.PRO_IBESERVER_PORT,Const.FILE_IBESERVER));
		} catch (IOException e) {
			e.printStackTrace();
		}
        try {
            url = new URL("http://" + partURL.toString()+ "/new_ws/CreateIBEPNRImplPort?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(CreateIBEPNRImplService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://" + partURL.toString() + 
            		"/new_ws/CreateIBEPNRImplPort?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public CreateIBEPNRImplService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public CreateIBEPNRImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CreateIBEPNRImplService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public static String getPartURL() {
		StringBuffer url=new StringBuffer();
		try {
			url.append(PropertiesUtils.getProperty(Const.PRO_IBESERVER_IP,Const.FILE_IBESERVER));
			url.append(PropertiesUtils.getProperty(Const.PRO_IBESERVER_PORT,Const.FILE_IBESERVER));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return url.toString();
	}
    /**
     *
     * @return
     *     returns CreateIBEPNR
     */
    @WebEndpoint(name = "CreateIBEPNRImplPort")
    public CreateIBEPNR getCreateIBEPNRImplPort() {
        return super.getPort(CreateIBEPNRImplPort, CreateIBEPNR.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns CreateIBEPNR
     */
/*
    @WebEndpoint(name = "CreateIBEPNRImplPort")
    public CreateIBEPNR getCreateIBEPNRImplPort(WebServiceFeature... features) {
        return super.getPort(CreateIBEPNRImplPort, CreateIBEPNR.class, features);
    }
*/
}