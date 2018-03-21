package com.viptrip.wetrip.wsclient;


import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.1.10
 * 2017-04-06T16:40:47.579+08:00
 * Generated source version: 3.1.10
 * 
 */
@WebServiceClient(name = "ReadIBEDataImplService", 
                  wsdlLocation = "http://webservice.viptrip.com/new_ws/ReadIBEDataImplPort?wsdl",
                  targetNamespace = "http://webservice.viptrip.com/") 
public class ReadIBEDataImplService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://webservice.viptrip.com/", "ReadIBEDataImplService");
    public final static QName ReadIBEDataImplPort = new QName("http://webservice.viptrip.com/", "ReadIBEDataImplPort");
    static {
        URL url = null;
        try {
            url = new URL("http://webservice.viptrip.com/new_ws/ReadIBEDataImplPort?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(ReadIBEDataImplService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://webservice.viptrip.com/new_ws/ReadIBEDataImplPort?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public ReadIBEDataImplService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ReadIBEDataImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ReadIBEDataImplService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public ReadIBEDataImplService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public ReadIBEDataImplService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public ReadIBEDataImplService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    




    /**
     *
     * @return
     *     returns ReadIBEDataImplDelegate
     */
    @WebEndpoint(name = "ReadIBEDataImplPort")
    public ReadIBEDataImplDelegate getReadIBEDataImplPort() {
        return super.getPort(ReadIBEDataImplPort, ReadIBEDataImplDelegate.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ReadIBEDataImplDelegate
     */
    @WebEndpoint(name = "ReadIBEDataImplPort")
    public ReadIBEDataImplDelegate getReadIBEDataImplPort(WebServiceFeature... features) {
        return super.getPort(ReadIBEDataImplPort, ReadIBEDataImplDelegate.class, features);
    }

}
