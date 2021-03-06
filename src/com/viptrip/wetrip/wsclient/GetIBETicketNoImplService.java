package com.viptrip.wetrip.wsclient;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;

import com.viptrip.resource.Const;
import com.viptrip.util.PropertiesUtils;


/**
 * This class was generated by Apache CXF 2.7.6
 * 2013-10-11T17:28:35.311+08:00
 * Generated source version: 2.7.6
 * 
 */
@WebServiceClient(name = "GetIBETicketNoImplService", 
                  wsdlLocation = "/new_ws/GetIBETicketNoImplPort?wsdl",
                  targetNamespace = "http://webservice.viptrip.com/") 
public class GetIBETicketNoImplService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://webservice.viptrip.com/", "GetIBETicketNoImplService");
    public final static QName GetIBETicketNoImplPort = new QName("http://webservice.viptrip.com/", "GetIBETicketNoImplPort");
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
            url = new URL("http://" + partURL + "/new_ws/GetIBETicketNoImplPort?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(GetIBETicketNoImplService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://" + partURL + 
            		"/new_ws/GetIBETicketNoImplPort?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public GetIBETicketNoImplService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public GetIBETicketNoImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public GetIBETicketNoImplService() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns GetIBETicketNoImplDelegate
     */
    @WebEndpoint(name = "GetIBETicketNoImplPort")
    public GetIBETicketNoImplDelegate getGetIBETicketNoImplPort() {
        return super.getPort(GetIBETicketNoImplPort, GetIBETicketNoImplDelegate.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns GetIBETicketNoImplDelegate
     */
/*    
    @WebEndpoint(name = "GetIBETicketNoImplPort")
    public GetIBETicketNoImplDelegate getGetIBETicketNoImplPort(WebServiceFeature... features) {
        return super.getPort(GetIBETicketNoImplPort, GetIBETicketNoImplDelegate.class, features);
    }
*/
}
