package com.viptrip.wetrip.wsclient;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import com.viptrip.wetrip.wsclient.ChangeDate;
import com.viptrip.wetrip.wsclient.ChangeDateResponse;
import com.viptrip.wetrip.wsclient.ChangePnr;
import com.viptrip.wetrip.wsclient.ChangePnrResponse;
import com.viptrip.wetrip.wsclient.RrAndIssueIBETicke;
import com.viptrip.wetrip.wsclient.RrAndIssueIBETickeResponse;
import com.viptrip.wetrip.wsclient.SellIBETicket;
import com.viptrip.wetrip.wsclient.SellIBETicketResponse;
import com.viptrip.wetrip.wsclient.SplitPnrByName;
import com.viptrip.wetrip.wsclient.SplitPnrByNameResponse;
import com.viptrip.wetrip.wsclient.DispplayTrip;
import com.viptrip.wetrip.wsclient.GetIBEBigplait;
import com.viptrip.wetrip.wsclient.GetIBEBigplaitResponse;
import com.viptrip.wetrip.wsclient.GetIBEData;
import com.viptrip.wetrip.wsclient.GetIBEDataResponse;
import com.viptrip.wetrip.wsclient.GetIBEFD;
import com.viptrip.wetrip.wsclient.GetIBEFDResponse;
import com.viptrip.wetrip.wsclient.GetStopStr;
import com.viptrip.wetrip.wsclient.GetStopStrResponse;



/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.viptrip.wetrip.wsclient package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetIBEFDResponse_QNAME = new QName("http://webservice.viptrip.com/", "getIBEFDResponse");

    private final static QName _DispplayTrip_QNAME = new QName("http://webservice.viptrip.com/", "dispplayTrip");

    private final static QName _GetIBEResult_QNAME = new QName("http://webservice.viptrip.com/", "getIBEResult");

    private final static QName _GetIBEResultResponse_QNAME = new QName("http://webservice.viptrip.com/",
            "getIBEResultResponse");

    private final static QName _GetIBEResultObj_QNAME = new QName("http://webservice.viptrip.com/", "getIBEResultObj");

    private final static QName _GetIBEDataResponse_QNAME = new QName("http://webservice.viptrip.com/",
            "getIBEDataResponse");

    private final static QName _GetIBEData_QNAME = new QName("http://webservice.viptrip.com/", "getIBEData");

    private final static QName _GetIBEFD_QNAME = new QName("http://webservice.viptrip.com/", "getIBEFD");

    private final static QName _GetIBEResultObjResponse_QNAME = new QName("http://webservice.viptrip.com/",
            "getIBEResultObjResponse");
   
    private final static QName _GetIBEBigplait_QNAME = new QName("http://webservice.viptrip.com/", "getIBEBigplait");

    private final static QName _GetStopStr_QNAME = new QName("http://webservice.viptrip.com/", "getStopStr");

    private final static QName _GetStopStrResponse_QNAME = new QName("http://webservice.viptrip.com/",
            "getStopStrResponse");

    private final static QName _GetIBEBigplaitResponse_QNAME = new QName("http://webservice.viptrip.com/",
            "getIBEBigplaitResponse");
    
    //更改PNR相关
    private final static QName _ChangePnrResponse_QNAME = new QName("http://webservice.viptrip.com/",
            "changePnrResponse");

    private final static QName _ChangeDate_QNAME = new QName("http://webservice.viptrip.com/", "changeDate");

    private final static QName _RrAndIssueIBETickeResponse_QNAME = new QName("http://webservice.viptrip.com/",
            "rrAndIssueIBETickeResponse");

    private final static QName _SellIBETicketResponse_QNAME = new QName("http://webservice.viptrip.com/",
            "SellIBETicketResponse");

    private final static QName _RrAndIssueIBETicke_QNAME = new QName("http://webservice.viptrip.com/",
            "rrAndIssueIBETicke");

    private final static QName _SplitPnrByNameResponse_QNAME = new QName("http://webservice.viptrip.com/",
            "splitPnrByNameResponse");

    private final static QName _SplitPnrByName_QNAME = new QName("http://webservice.viptrip.com/", "splitPnrByName");

    private final static QName _SellIBETicket_QNAME = new QName("http://webservice.viptrip.com/", "SellIBETicket");

    private final static QName _ChangePnr_QNAME = new QName("http://webservice.viptrip.com/", "changePnr");

    private final static QName _ChangeDateResponse_QNAME = new QName("http://webservice.viptrip.com/",
            "changeDateResponse");
    /**
     * Create a new ObjectFactory that can be used to create new instances of
     * schema derived classes for package: com.viptrip.wetrip.wsclient
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetIBEBigplaitResponse }
     * 
     */
    public GetIBEBigplaitResponse createGetIBEBigplaitResponse() {
        return new GetIBEBigplaitResponse();
    }

    /**
     * Create an instance of {@link GetStopStrResponse }
     * 
     */
    public GetStopStrResponse createGetStopStrResponse() {
        return new GetStopStrResponse();
    }

    /**
     * Create an instance of {@link GetIBEBigplait }
     * 
     */
    public GetIBEBigplait createGetIBEBigplait() {
        return new GetIBEBigplait();
    }

    /**
     * Create an instance of {@link GetStopStr }
     * 
     */
    public GetStopStr createGetStopStr() {
        return new GetStopStr();
    }


    /**
     * Create an instance of {@link GetIBEFDResponse }
     * 
     */
    public GetIBEFDResponse createGetIBEFDResponse() {
        return new GetIBEFDResponse();
    }


    /**
     * Create an instance of {@link GetIBEData }
     * 
     */
    public GetIBEData createGetIBEData() {
        return new GetIBEData();
    }


    /**
     * Create an instance of {@link DispplayTrip }
     * 
     */
    public DispplayTrip createDispplayTrip() {
        return new DispplayTrip();
    }


    /**
     * Create an instance of {@link GetIBEFD }
     * 
     */
    public GetIBEFD createGetIBEFD() {
        return new GetIBEFD();
    }


    /**
     * Create an instance of {@link GetIBEDataResponse }
     * 
     */
    public GetIBEDataResponse createGetIBEDataResponse() {
        return new GetIBEDataResponse();
    }


    /**
     * Create an instance of {@link JAXBElement }{@code <}
     * {@link GetIBEFDResponse }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.viptrip.com/", name = "getIBEFDResponse")
    public JAXBElement<GetIBEFDResponse> createGetIBEFDResponse(GetIBEFDResponse value) {
        return new JAXBElement<GetIBEFDResponse>(_GetIBEFDResponse_QNAME, GetIBEFDResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DispplayTrip }
     * {@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.viptrip.com/", name = "dispplayTrip")
    public JAXBElement<DispplayTrip> createDispplayTrip(DispplayTrip value) {
        return new JAXBElement<DispplayTrip>(_DispplayTrip_QNAME, DispplayTrip.class, null, value);
    }



    /**
     * Create an instance of {@link JAXBElement }{@code <}
     * {@link GetIBEDataResponse }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.viptrip.com/", name = "getIBEDataResponse")
    public JAXBElement<GetIBEDataResponse> createGetIBEDataResponse(GetIBEDataResponse value) {
        return new JAXBElement<GetIBEDataResponse>(_GetIBEDataResponse_QNAME, GetIBEDataResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetIBEData }
     * {@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.viptrip.com/", name = "getIBEData")
    public JAXBElement<GetIBEData> createGetIBEData(GetIBEData value) {
        return new JAXBElement<GetIBEData>(_GetIBEData_QNAME, GetIBEData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetIBEFD }
     * {@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.viptrip.com/", name = "getIBEFD")
    public JAXBElement<GetIBEFD> createGetIBEFD(GetIBEFD value) {
        return new JAXBElement<GetIBEFD>(_GetIBEFD_QNAME, GetIBEFD.class, null, value);
    }


    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetIBEBigplait }
     * {@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.viptrip.com/", name = "getIBEBigplait")
    public JAXBElement<GetIBEBigplait> createGetIBEBigplait(GetIBEBigplait value) {
        return new JAXBElement<GetIBEBigplait>(_GetIBEBigplait_QNAME, GetIBEBigplait.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetStopStr }
     * {@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.viptrip.com/", name = "getStopStr")
    public JAXBElement<GetStopStr> createGetStopStr(GetStopStr value) {
        return new JAXBElement<GetStopStr>(_GetStopStr_QNAME, GetStopStr.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}
     * {@link GetStopStrResponse }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.viptrip.com/", name = "getStopStrResponse")
    public JAXBElement<GetStopStrResponse> createGetStopStrResponse(GetStopStrResponse value) {
        return new JAXBElement<GetStopStrResponse>(_GetStopStrResponse_QNAME, GetStopStrResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}
     * {@link GetIBEBigplaitResponse }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.viptrip.com/", name = "getIBEBigplaitResponse")
    public JAXBElement<GetIBEBigplaitResponse> createGetIBEBigplaitResponse(GetIBEBigplaitResponse value) {
        return new JAXBElement<GetIBEBigplaitResponse>(_GetIBEBigplaitResponse_QNAME, GetIBEBigplaitResponse.class,
                null, value);
    }
    
    
    /**
     * Create an instance of {@link ChangePnr }
     * 
     */
    public ChangePnr createChangePnr() {
        return new ChangePnr();
    }

    /**
     * Create an instance of {@link ChangeDate }
     * 
     */
    public ChangeDate createChangeDate() {
        return new ChangeDate();
    }

    /**
     * Create an instance of {@link SplitPnrByName }
     * 
     */
    public SplitPnrByName createSplitPnrByName() {
        return new SplitPnrByName();
    }

    /**
     * Create an instance of {@link RrAndIssueIBETickeResponse }
     * 
     */
    public RrAndIssueIBETickeResponse createRrAndIssueIBETickeResponse() {
        return new RrAndIssueIBETickeResponse();
    }

    /**
     * Create an instance of {@link SellIBETicket }
     * 
     */
    public SellIBETicket createSellIBETicket() {
        return new SellIBETicket();
    }

    /**
     * Create an instance of {@link SellIBETicketResponse }
     * 
     */
    public SellIBETicketResponse createSellIBETicketResponse() {
        return new SellIBETicketResponse();
    }

    /**
     * Create an instance of {@link ChangePnrResponse }
     * 
     */
    public ChangePnrResponse createChangePnrResponse() {
        return new ChangePnrResponse();
    }

    /**
     * Create an instance of {@link ChangeDateResponse }
     * 
     */
    public ChangeDateResponse createChangeDateResponse() {
        return new ChangeDateResponse();
    }

    /**
     * Create an instance of {@link RrAndIssueIBETicke }
     * 
     */
    public RrAndIssueIBETicke createRrAndIssueIBETicke() {
        return new RrAndIssueIBETicke();
    }

    /**
     * Create an instance of {@link SplitPnrByNameResponse }
     * 
     */
    public SplitPnrByNameResponse createSplitPnrByNameResponse() {
        return new SplitPnrByNameResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}
     * {@link ChangePnrResponse }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.viptrip.com/", name = "changePnrResponse")
    public JAXBElement<ChangePnrResponse> createChangePnrResponse(ChangePnrResponse value) {
        return new JAXBElement<ChangePnrResponse>(_ChangePnrResponse_QNAME, ChangePnrResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangeDate }
     * {@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.viptrip.com/", name = "changeDate")
    public JAXBElement<ChangeDate> createChangeDate(ChangeDate value) {
        return new JAXBElement<ChangeDate>(_ChangeDate_QNAME, ChangeDate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}
     * {@link RrAndIssueIBETickeResponse }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.viptrip.com/", name = "rrAndIssueIBETickeResponse")
    public JAXBElement<RrAndIssueIBETickeResponse> createRrAndIssueIBETickeResponse(RrAndIssueIBETickeResponse value) {
        return new JAXBElement<RrAndIssueIBETickeResponse>(_RrAndIssueIBETickeResponse_QNAME,
                RrAndIssueIBETickeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}
     * {@link SellIBETicketResponse }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.viptrip.com/", name = "SellIBETicketResponse")
    public JAXBElement<SellIBETicketResponse> createSellIBETicketResponse(SellIBETicketResponse value) {
        return new JAXBElement<SellIBETicketResponse>(_SellIBETicketResponse_QNAME, SellIBETicketResponse.class, null,
                value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}
     * {@link RrAndIssueIBETicke }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.viptrip.com/", name = "rrAndIssueIBETicke")
    public JAXBElement<RrAndIssueIBETicke> createRrAndIssueIBETicke(RrAndIssueIBETicke value) {
        return new JAXBElement<RrAndIssueIBETicke>(_RrAndIssueIBETicke_QNAME, RrAndIssueIBETicke.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}
     * {@link SplitPnrByNameResponse }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.viptrip.com/", name = "splitPnrByNameResponse")
    public JAXBElement<SplitPnrByNameResponse> createSplitPnrByNameResponse(SplitPnrByNameResponse value) {
        return new JAXBElement<SplitPnrByNameResponse>(_SplitPnrByNameResponse_QNAME, SplitPnrByNameResponse.class,
                null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SplitPnrByName }
     * {@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.viptrip.com/", name = "splitPnrByName")
    public JAXBElement<SplitPnrByName> createSplitPnrByName(SplitPnrByName value) {
        return new JAXBElement<SplitPnrByName>(_SplitPnrByName_QNAME, SplitPnrByName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SellIBETicket }
     * {@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.viptrip.com/", name = "SellIBETicket")
    public JAXBElement<SellIBETicket> createSellIBETicket(SellIBETicket value) {
        return new JAXBElement<SellIBETicket>(_SellIBETicket_QNAME, SellIBETicket.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangePnr }
     * {@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.viptrip.com/", name = "changePnr")
    public JAXBElement<ChangePnr> createChangePnr(ChangePnr value) {
        return new JAXBElement<ChangePnr>(_ChangePnr_QNAME, ChangePnr.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}
     * {@link ChangeDateResponse }{@code >}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.viptrip.com/", name = "changeDateResponse")
    public JAXBElement<ChangeDateResponse> createChangeDateResponse(ChangeDateResponse value) {
        return new JAXBElement<ChangeDateResponse>(_ChangeDateResponse_QNAME, ChangeDateResponse.class, null, value);
    }
}
