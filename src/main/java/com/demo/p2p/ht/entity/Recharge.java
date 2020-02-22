package com.demo.p2p.ht.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 充值记录表
 * </p>
 *
 * @author gzd
 * @since 2020-02-22
 */
public class Recharge implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * ID编号
     */
    @TableId(value = "rID", type = IdType.AUTO)
    private Integer rID;

    /**
     * 用户id
     */
    @TableField("uID")
    private Integer uID;

    /**
     * 用户名
     */
    private String uname;

    /**
     * 充值类型
     */
    private String czlx;

    /**
     * 流水号
     */
    private String lsh;

    /**
     * 充值金额
     */
    private String czmoney;

    /**
     * 费率
     */
    private String fl;

    /**
     * 到账金额
     */
    private String dzmoney;

    /**
     * 充值时间
     */
    private Date cztime;

    /**
     * 状态
     */
    private String statu;

    /**
     * 真实姓名
     */
    private String zname;


    public Integer getrID() {
        return rID;
    }

    public void setrID(Integer rID) {
        this.rID = rID;
    }

    public Integer getuID() {
        return uID;
    }

    public void setuID(Integer uID) {
        this.uID = uID;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getCzlx() {
        return czlx;
    }

    public void setCzlx(String czlx) {
        this.czlx = czlx;
    }

    public String getLsh() {
        return lsh;
    }

    public void setLsh(String lsh) {
        this.lsh = lsh;
    }

    public String getCzmoney() {
        return czmoney;
    }

    public void setCzmoney(String czmoney) {
        this.czmoney = czmoney;
    }

    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    public String getDzmoney() {
        return dzmoney;
    }

    public void setDzmoney(String dzmoney) {
        this.dzmoney = dzmoney;
    }

    public Date getCztime() {
        return cztime;
    }

    public void setCztime(Date cztime) {
        this.cztime = cztime;
    }

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    public String getZname() {
        return zname;
    }

    public void setZname(String zname) {
        this.zname = zname;
    }

    @Override
    public String toString() {
        return "Recharge{" +
        "rID=" + rID +
        ", uID=" + uID +
        ", uname=" + uname +
        ", czlx=" + czlx +
        ", lsh=" + lsh +
        ", czmoney=" + czmoney +
        ", fl=" + fl +
        ", dzmoney=" + dzmoney +
        ", cztime=" + cztime +
        ", statu=" + statu +
        ", zname=" + zname +
        "}";
    }
}
