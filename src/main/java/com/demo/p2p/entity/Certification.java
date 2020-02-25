package com.demo.p2p.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author gzd
 * @since 2020-02-11
 */
public class Certification implements Serializable {

private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 序号
     */
    private Integer cserial;

    /**
     * 用户名
     */
    private String cusername;

    /**
     * 真实姓名 
     */
    private String crealname;

    /**
     * 可用余额
     */
    private String cbalance;

    /**
     * 冻结金额 
     */
    private Double cfreeze;

    /**
     * 待收金额 
     */
    private Double cdue;

    /**
     * 待还金额
     */
    private Double cpaid;

    /**
     * 总金额
     */
    private String ctotalmoney;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCserial() {
        return cserial;
    }

    public void setCserial(Integer cserial) {
        this.cserial = cserial;
    }

    public String getCusername() {
        return cusername;
    }

    public void setCusername(String cusername) {
        this.cusername = cusername;
    }

    public String getCrealname() {
        return crealname;
    }

    public void setCrealname(String crealname) {
        this.crealname = crealname;
    }

    public String getCbalance() {
        return cbalance;
    }

    public void setCbalance(String cbalance) {
        this.cbalance = cbalance;
    }

    public Double getCfreeze() {
        return cfreeze;
    }

    public void setCfreeze(Double cfreeze) {
        this.cfreeze = cfreeze;
    }

    public Double getCdue() {
        return cdue;
    }

    public void setCdue(Double cdue) {
        this.cdue = cdue;
    }

    public Double getCpaid() {
        return cpaid;
    }

    public void setCpaid(Double cpaid) {
        this.cpaid = cpaid;
    }

    public String getCtotalmoney() {
        return ctotalmoney;
    }

    public void setCtotalmoney(String ctotalmoney) {
        this.ctotalmoney = ctotalmoney;
    }

    @Override
    public String toString() {
        return "Certification{" +
        "id=" + id +
        ", cserial=" + cserial +
        ", cusername=" + cusername +
        ", crealname=" + crealname +
        ", cbalance=" + cbalance +
        ", cfreeze=" + cfreeze +
        ", cdue=" + cdue +
        ", cpaid=" + cpaid +
        ", ctotalmoney=" + ctotalmoney +
        "}";
    }
}
