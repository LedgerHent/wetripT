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
 * 2013-10-10T13:04:59.476+08:00
 * Generated source version: 2.7.6
 * 本文件夹下第 16个文件，和学银 检查完成 时间：2015年6月12日09:54:04
 */
@WebService(targetNamespace = "http://webservice.viptrip.com/", name = "DeleteIBEPNRImplDelegate")
@XmlSeeAlso({ObjectFactory.class})
public interface DeleteIBEPNRImplDelegate {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "DeletePNR", targetNamespace = "http://webservice.viptrip.com/", className = "com.viptrip.wetrip.wsclient.DeletePNR")
    @WebMethod(operationName = "DeletePNR")
    @ResponseWrapper(localName = "DeletePNRResponse", targetNamespace = "http://webservice.viptrip.com/", className = "com.viptrip.wetrip.wsclient.DeletePNRResponse")
    public java.lang.String deletePNR(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );
}
