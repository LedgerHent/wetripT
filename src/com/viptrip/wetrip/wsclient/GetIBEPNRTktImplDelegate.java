package com.viptrip.wetrip.wsclient;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.6
 * 2014-01-03T14:05:19.798+08:00
 * Generated source version: 2.7.6
 * 
 */
@WebService(targetNamespace = "http://webservice.viptrip.com/", name = "GetIBEPNRTktImplDelegate")
@XmlSeeAlso({ObjectFactory.class})
public interface GetIBEPNRTktImplDelegate {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getPNRTkt", targetNamespace = "http://webservice.viptrip.com/", className = "com.viptrip.wetrip.wsclient.GetPNRTkt")
    @WebMethod
    @ResponseWrapper(localName = "getPNRTktResponse", targetNamespace = "http://webservice.viptrip.com/", className = "com.viptrip.wetrip.wsclient.GetPNRTktResponse")
    public java.util.List<java.lang.String> getPNRTkt(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );
}
