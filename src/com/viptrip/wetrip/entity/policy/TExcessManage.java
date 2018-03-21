package com.viptrip.wetrip.entity.policy;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * TExcessManage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_EXCESS_MANAGE")
public class TExcessManage implements java.io.Serializable {

    // Fields

    private BigDecimal id;
    private String reason;
    private String operator;
    private Date operatorDate;
    private Long type;

    // Constructors

    /** default constructor */
    public TExcessManage() {
    }

    /** full constructor */
    public TExcessManage(String reason, String operator, Date operatorDate) {
        this.reason = reason;
        this.operator = operator;
        this.operatorDate = operatorDate;
    }

    // Property accessors
    @Id
    @Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_EXCESS_MANAGE")
    @SequenceGenerator(name="SEQ_EXCESS_MANAGE",allocationSize=1,initialValue=1, sequenceName="SEQ_EXCESS_MANAGE")
    public BigDecimal getId() {
        return this.id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    @Column(name = "REASON", length = 4000)
    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Column(name = "OPERATOR", length = 100)
    public String getOperator() {
        return this.operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Column(name = "OPERATOR_DATE", length = 7)
    public Date getOperatorDate() {
        return this.operatorDate;
    }

    public void setOperatorDate(Date operatorDate) {
        this.operatorDate = operatorDate;
    }
    @Column(name = "TYPE", length = 5)
    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }
}
    
