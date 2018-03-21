package com.viptrip.wetrip.wsclient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>DispplayTrip complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="DispplayTrip"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="airline" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="orgcity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="detcity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="startdate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="starttime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="arrvidate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="arrvitime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="cangwei" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="food" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="airlineshare" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="planetype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="orgterm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="detterm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="taxfee" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="fueltax" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="discountrate" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="enddate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="rulecode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="seatsLeft" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="cangweiDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="yPrice" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="ffstr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DispplayTrip", propOrder = {
    "airline",
    "orgcity",
    "detcity",
    "startdate",
    "starttime",
    "arrvidate",
    "arrvitime",
    "cangwei",
    "food",
    "airlineshare",
    "planetype",
    "orgterm",
    "detterm",
    "price",
    "taxfee",
    "fueltax",
    "discountrate",
    "enddate",
    "rulecode",
    "seatsLeft",
    "cangweiDesc",
    "yPrice",
    "ffstr"
})
public class DispplayTrip {

    protected String airline;
    protected String orgcity;
    protected String detcity;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startdate;
    protected String starttime;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar arrvidate;
    protected String arrvitime;
    protected String cangwei;
    protected String food;
    protected String airlineshare;
    protected String planetype;
    protected String orgterm;
    protected String detterm;
    protected Double price;
    protected Double taxfee;
    protected Double fueltax;
    protected Double discountrate;
    protected String enddate;
    protected String rulecode;
    protected String seatsLeft;
    protected String cangweiDesc;
    protected Double yPrice;
    protected String ffstr;

    /**
     * 获取airline属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAirline() {
        return airline;
    }

    /**
     * 设置airline属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAirline(String value) {
        this.airline = value;
    }

    /**
     * 获取orgcity属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgcity() {
        return orgcity;
    }

    /**
     * 设置orgcity属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgcity(String value) {
        this.orgcity = value;
    }

    /**
     * 获取detcity属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDetcity() {
        return detcity;
    }

    /**
     * 设置detcity属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDetcity(String value) {
        this.detcity = value;
    }

    /**
     * 获取startdate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartdate() {
        return startdate;
    }

    /**
     * 设置startdate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartdate(XMLGregorianCalendar value) {
        this.startdate = value;
    }

    /**
     * 获取starttime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStarttime() {
        return starttime;
    }

    /**
     * 设置starttime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStarttime(String value) {
        this.starttime = value;
    }

    /**
     * 获取arrvidate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getArrvidate() {
        return arrvidate;
    }

    /**
     * 设置arrvidate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setArrvidate(XMLGregorianCalendar value) {
        this.arrvidate = value;
    }

    /**
     * 获取arrvitime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArrvitime() {
        return arrvitime;
    }

    /**
     * 设置arrvitime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArrvitime(String value) {
        this.arrvitime = value;
    }

    /**
     * 获取cangwei属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCangwei() {
        return cangwei;
    }

    /**
     * 设置cangwei属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCangwei(String value) {
        this.cangwei = value;
    }

    /**
     * 获取food属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFood() {
        return food;
    }

    /**
     * 设置food属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFood(String value) {
        this.food = value;
    }

    /**
     * 获取airlineshare属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAirlineshare() {
        return airlineshare;
    }

    /**
     * 设置airlineshare属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAirlineshare(String value) {
        this.airlineshare = value;
    }

    /**
     * 获取planetype属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlanetype() {
        return planetype;
    }

    /**
     * 设置planetype属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanetype(String value) {
        this.planetype = value;
    }

    /**
     * 获取orgterm属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgterm() {
        return orgterm;
    }

    /**
     * 设置orgterm属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgterm(String value) {
        this.orgterm = value;
    }

    /**
     * 获取detterm属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDetterm() {
        return detterm;
    }

    /**
     * 设置detterm属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDetterm(String value) {
        this.detterm = value;
    }

    /**
     * 获取price属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 设置price属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPrice(Double value) {
        this.price = value;
    }

    /**
     * 获取taxfee属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTaxfee() {
        return taxfee;
    }

    /**
     * 设置taxfee属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTaxfee(Double value) {
        this.taxfee = value;
    }

    /**
     * 获取fueltax属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getFueltax() {
        return fueltax;
    }

    /**
     * 设置fueltax属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setFueltax(Double value) {
        this.fueltax = value;
    }

    /**
     * 获取discountrate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDiscountrate() {
        return discountrate;
    }

    /**
     * 设置discountrate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDiscountrate(Double value) {
        this.discountrate = value;
    }

    /**
     * 获取enddate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnddate() {
        return enddate;
    }

    /**
     * 设置enddate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnddate(String value) {
        this.enddate = value;
    }

    /**
     * 获取rulecode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRulecode() {
        return rulecode;
    }

    /**
     * 设置rulecode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRulecode(String value) {
        this.rulecode = value;
    }

    /**
     * 获取seatsLeft属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeatsLeft() {
        return seatsLeft;
    }

    /**
     * 设置seatsLeft属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeatsLeft(String value) {
        this.seatsLeft = value;
    }

    /**
     * 获取cangweiDesc属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCangweiDesc() {
        return cangweiDesc;
    }

    /**
     * 设置cangweiDesc属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCangweiDesc(String value) {
        this.cangweiDesc = value;
    }

    /**
     * 获取yPrice属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getYPrice() {
        return yPrice;
    }

    /**
     * 设置yPrice属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setYPrice(Double value) {
        this.yPrice = value;
    }

    /**
     * 获取ffstr属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFfstr() {
        return ffstr;
    }

    /**
     * 设置ffstr属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFfstr(String value) {
        this.ffstr = value;
    }

}
