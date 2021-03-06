package com.demo.p2p.ht.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author gzd
 * @since 2020-03-03
 */
public class Certification implements Serializable {

private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 序号
     */
    private String cserial;

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
    private String cfreeze;

    /**
     * 待收金额 
     */
    private String cdue;

    /**
     * 待还金额
     */
    private String cpaid;

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

    public String getCserial() {
        return cserial;
    }

    public void setCserial(String cserial) {
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

    public String getCfreeze() {
        return cfreeze;
    }

    public void setCfreeze(String cfreeze) {
        this.cfreeze = cfreeze;
    }

    public String getCdue() {
        return cdue;
    }

    public void setCdue(String cdue) {
        this.cdue = cdue;
    }

    public String getCpaid() {
        return cpaid;
    }

    public void setCpaid(String cpaid) {
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
