	package com.viptrip.wetrip.wsclient;

	import java.io.IOException;
	import java.net.MalformedURLException;
	import java.net.URL;
	import javax.xml.namespace.QName;
	import javax.xml.ws.Service;
	import javax.xml.ws.WebEndpoint;
	import javax.xml.ws.WebServiceClient;
	import java.net.MalformedURLException;
	import java.net.URL;
	import java.util.logging.Logger;
	import javax.xml.namespace.QName;
	import javax.xml.ws.Service;
	import javax.xml.ws.WebEndpoint;
	import javax.xml.ws.WebServiceClient;
	import com.viptrip.resource.Const;
	import com.viptrip.util.PropertiesUtils;

@javax.jws.WebService(
targetNamespace = 
	"http://wsclient.wetrip.viptrip.com/"
,
serviceName = 
	"IBEPNRAddPATAImplServiceService"
, 
portName =
	"IBEPNRAddPATAImplServicePort"
	,wsdlLocation = "WEB-INF/wsdl/IBEPNRAddPATAImplServiceService.wsdl"
)



public class IBEPNRAddPATAImplServiceDelegate {

	com.viptrip.wetrip.wsclient.IBEPNRAddPATAImplService iBEPNRAddPATAImplService = new com.viptrip.wetrip.wsclient.IBEPNRAddPATAImplService();

			public IBEPNRAddPATAImplDelegate getIBEPNRAddPATAImplPort()  {		
			return iBEPNRAddPATAImplService.getIBEPNRAddPATAImplPort();
		}
	
}