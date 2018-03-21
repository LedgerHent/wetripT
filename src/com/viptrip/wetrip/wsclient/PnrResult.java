
package com.viptrip.wetrip.wsclient;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.viptrip.wetrip.wsclient.base.StringArray;


/**
 * <p>PnrResult complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="PnrResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="strpnrno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="intsegmentcount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="segments" type="{http://jaxb.dev.java.net/array}stringArray" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PnrResult", propOrder = {
    "strpnrno",
    "intsegmentcount",
    "segments"
})
public class PnrResult {

    protected String strpnrno;
    protected int intsegmentcount;
    @XmlElement(nillable = true)
    protected List<StringArray> segments;

    /**
     * 获取strpnrno属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrpnrno() {
        return strpnrno;
    }

    /**
     * 设置strpnrno属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrpnrno(String value) {
        this.strpnrno = value;
    }

    /**
     * 获取intsegmentcount属性的值。
     * 
     */
    public int getIntsegmentcount() {
        return intsegmentcount;
    }

    /**
     * 设置intsegmentcount属性的值。
     * 
     */
    public void setIntsegmentcount(int value) {
        this.intsegmentcount = value;
    }

    /**
     * Gets the value of the segments property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the segments property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSegments().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StringArray }
     * 
     * 
     */
    public List<StringArray> getSegments() {
        if (segments == null) {
            segments = new ArrayList<StringArray>();
        }
        return this.segments;
    }

}
