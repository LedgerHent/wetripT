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
 * 2013-10-11T17:29:07.789+08:00
 * Generated source version: 2.7.6
 * 
 */
@WebService(targetNamespace = "http://webservice.viptrip.com/", name = "IBEvoidETicketImplDelegate")
@XmlSeeAlso({ObjectFactory.class})
public interface IBEvoidETicketImplDelegate {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "voidETicket", targetNamespace = "http://webservice.viptrip.com/", className = "com.viptrip.wetrip.wsclient.VoidETicket")
    @WebMethod
    @ResponseWrapper(localName = "voidETicketResponse", targetNamespace = "http://webservice.viptrip.com/", className = "com.viptrip.wetrip.wsclient.VoidETicketResponse")
    public java.lang.String voidETicket(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        java.lang.String arg3
    );
}
