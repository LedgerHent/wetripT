
package com.viptrip.wetrip.wsclient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>trfdResult complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="trfdResult">
 *   &lt;complexContent>
 *     &lt;extension base="{http://webservice.viptrip.com/}ibeResult">
 *       &lt;sequence>
 *         &lt;element name="airlineCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="check" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cmdNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cmdOption" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cmdPrnt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cmdTktType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="command" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="commission" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="commissionRate" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="conjunction" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="creditcard" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deduction" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="flagforgroup" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="grossRefund" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="netRefund" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="passName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="payMethod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="refund" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="taxcount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="taxcount_inserted" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="tktNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tktNo_end" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "trfdResult", propOrder = {
    "airlineCode",
    "check",
    "cmdNo",
    "cmdOption",
    "cmdPrnt",
    "cmdTktType",
    "command",
    "commission",
    "commissionRate",
    "conjunction",
    "creditcard",
    "currency",
    "deduction",
    "flagforgroup",
    "grossRefund",
    "netRefund",
    "passName",
    "payMethod",
    "refund",
    "taxcount",
    "taxcountInserted",
    "tktNo",
    "tktNoEnd"
})
public class TrfdResult
    extends IbeResult
{

    protected String airlineCode;
    protected String check;
    protected String cmdNo;
    protected String cmdOption;
    protected String cmdPrnt;
    protected String cmdTktType;
    protected String command;
    protected double commission;
    protected double commissionRate;
    protected int conjunction;
    protected String creditcard;
    protected String currency;
    protected double deduction;
    protected String flagforgroup;
    protected double grossRefund;
    protected double netRefund;
    protected String passName;
    protected String payMethod;
    protected String refund;
    protected int taxcount;
    @XmlElement(name = "taxcount_inserted")
    protected int taxcountInserted;
    protected String tktNo;
    @XmlElement(name = "tktNo_end")
    protected String tktNoEnd;

    /**
     * 获取airlineCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAirlineCode() {
        return airlineCode;
    }

    /**
     * 设置airlineCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAirlineCode(String value) {
        this.airlineCode = value;
    }

    /**
     * 获取check属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCheck() {
        return check;
    }

    /**
     * 设置check属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheck(String value) {
        this.check = value;
    }

    /**
     * 获取cmdNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCmdNo() {
        return cmdNo;
    }

    /**
     * 设置cmdNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCmdNo(String value) {
        this.cmdNo = value;
    }

    /**
     * 获取cmdOption属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCmdOption() {
        return cmdOption;
    }

    /**
     * 设置cmdOption属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCmdOption(String value) {
        this.cmdOption = value;
    }

    /**
     * 获取cmdPrnt属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCmdPrnt() {
        return cmdPrnt;
    }

    /**
     * 设置cmdPrnt属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCmdPrnt(String value) {
        this.cmdPrnt = value;
    }

    /**
     * 获取cmdTktType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCmdTktType() {
        return cmdTktType;
    }

    /**
     * 设置cmdTktType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCmdTktType(String value) {
        this.cmdTktType = value;
    }

    /**
     * 获取command属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommand() {
        return command;
    }

    /**
     * 设置command属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommand(String value) {
        this.command = value;
    }

    /**
     * 获取commission属性的值。
     * 
     */
    public double getCommission() {
        return commission;
    }

    /**
     * 设置commission属性的值。
     * 
     */
    public void setCommission(double value) {
        this.commission = value;
    }

    /**
     * 获取commissionRate属性的值。
     * 
     */
    public double getCommissionRate() {
        return commissionRate;
    }

    /**
     * 设置commissionRate属性的值。
     * 
     */
    public void setCommissionRate(double value) {
        this.commissionRate = value;
    }

    /**
     * 获取conjunction属性的值。
     * 
     */
    public int getConjunction() {
        return conjunction;
    }

    /**
     * 设置conjunction属性的值。
     * 
     */
    public void setConjunction(int value) {
        this.conjunction = value;
    }

    /**
     * 获取creditcard属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditcard() {
        return creditcard;
    }

    /**
     * 设置creditcard属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditcard(String value) {
        this.creditcard = value;
    }

    /**
     * 获取currency属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * 设置currency属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrency(String value) {
        this.currency = value;
    }

    /**
     * 获取deduction属性的值。
     * 
     */
    public double getDeduction() {
        return deduction;
    }

    /**
     * 设置deduction属性的值。
     * 
     */
    public void setDeduction(double value) {
        this.deduction = value;
    }

    /**
     * 获取flagforgroup属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlagforgroup() {
        return flagforgroup;
    }

    /**
     * 设置flagforgroup属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlagforgroup(String value) {
        this.flagforgroup = value;
    }

    /**
     * 获取grossRefund属性的值。
     * 
     */
    public double getGrossRefund() {
        return grossRefund;
    }

    /**
     * 设置grossRefund属性的值。
     * 
     */
    public void setGrossRefund(double value) {
        this.grossRefund = value;
    }

    /**
     * 获取netRefund属性的值。
     * 
     */
    public double getNetRefund() {
        return netRefund;
    }

    /**
     * 设置netRefund属性的值。
     * 
     */
    public void setNetRefund(double value) {
        this.netRefund = value;
    }

    /**
     * 获取passName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassName() {
        return passName;
    }

    /**
     * 设置passName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassName(String value) {
        this.passName = value;
    }

    /**
     * 获取payMethod属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayMethod() {
        return payMethod;
    }

    /**
     * 设置payMethod属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayMethod(String value) {
        this.payMethod = value;
    }

    /**
     * 获取refund属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefund() {
        return refund;
    }

    /**
     * 设置refund属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefund(String value) {
        this.refund = value;
    }

    /**
     * 获取taxcount属性的值。
     * 
     */
    public int getTaxcount() {
        return taxcount;
    }

    /**
     * 设置taxcount属性的值。
     * 
     */
    public void setTaxcount(int value) {
        this.taxcount = value;
    }

    /**
     * 获取taxcountInserted属性的值。
     * 
     */
    public int getTaxcountInserted() {
        return taxcountInserted;
    }

    /**
     * 设置taxcountInserted属性的值。
     * 
     */
    public void setTaxcountInserted(int value) {
        this.taxcountInserted = value;
    }

    /**
     * 获取tktNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTktNo() {
        return tktNo;
    }

    /**
     * 设置tktNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTktNo(String value) {
        this.tktNo = value;
    }

    /**
     * 获取tktNoEnd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTktNoEnd() {
        return tktNoEnd;
    }

    /**
     * 设置tktNoEnd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTktNoEnd(String value) {
        this.tktNoEnd = value;
    }

}
