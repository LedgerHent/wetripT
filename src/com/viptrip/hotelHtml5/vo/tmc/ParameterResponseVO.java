package com.viptrip.hotelHtml5.vo.tmc;

public class ParameterResponseVO implements java.io.Serializable{

    /** 
       * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
       */ 
    private static final long serialVersionUID = 267706241987933031L;

private String n;//名称

  private String v;//value

  private String attribute;//其他属性
  public String getN() {
    return n;
  }

  public void setN(String n) {
    this.n = n;
  }

  public String getV() {
    return v;
  }

  public void setV(String v) {
    this.v = v;
  }

public String getAttribute() {
    return attribute;
}

public void setAttribute(String attribute) {
    this.attribute = attribute;
}
  

}
