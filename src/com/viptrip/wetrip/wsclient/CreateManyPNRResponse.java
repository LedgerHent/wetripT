
package com.viptrip.wetrip.wsclient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>CreateManyPNRResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="CreateManyPNRResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://webservice.viptrip.com/}PnrResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 本文件夹下第 12个文件，和学银 检查完成 时间：2015年6月12日09:51:58
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreateManyPNRResponse", propOrder = {
    "_return"
})
public class CreateManyPNRResponse {

    @XmlElement(name = "return")
    protected PnrResult _return;

    /**
     * 获取return属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PnrResult }
     *     
     */
    public PnrResult getReturn() {
        return _return;
    }

    /**
     * 设置return属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PnrResult }
     *     
     */
    public void setReturn(PnrResult value) {
        this._return = value;
    }

}
