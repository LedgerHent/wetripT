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
 * 2013-10-17T11:04:21.892+08:00
 * Generated source version: 2.7.6
 * 
 */
@WebService(targetNamespace = "http://webservice.viptrip.com/", name = "GetIBEPNRMsgImplDelegate")
@XmlSeeAlso({ObjectFactory.class})
public interface GetIBEPNRMsgImplDelegate {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "getPNRMsg", targetNamespace = "http://webservice.viptrip.com/", className = "com.viptrip.wetrip.wsclient.GetPNRMsg")
    @WebMethod
    @ResponseWrapper(localName = "getPNRMsgResponse", targetNamespace = "http://webservice.viptrip.com/", className = "com.viptrip.wetrip.wsclient.GetPNRMsgResponse")
    public java.util.List<java.lang.String> getPNRMsg(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );
}
