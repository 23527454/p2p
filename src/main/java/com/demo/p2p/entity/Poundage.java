package com.demo.p2p.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 手续费记录表
 * </p>
 *
 * @author gzd
 * @since 2020-02-25
 */
public class Poundage implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * ID编号
     */
    @TableId(value = "pID", type = IdType.AUTO)
    private Integer pID;

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
     * 真实姓名
     */
    private String zname;

    /**
     * 手续费金额
     */
    private String sxmoney;

    /**
     * 什么手续
     */
    private String what;

    /**
     * 时间
     */
    private Date sxtime;

    /**
     * 本地账户
     */
    @TableField("Localaccount")
    private String Localaccount;

    /**
     * 往来账户
     */
    private String bookaccount;

    /**
     * 交易方式
     */
    private String paytype;


    public Integer getpID() {
        return pID;
    }

    public void setpID(Integer pID) {
        this.pID = pID;
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

    public String getZname() {
        return zname;
    }

    public void setZname(String zname) {
        this.zname = zname;
    }

    public String getSxmoney() {
        return sxmoney;
    }

    public void setSxmoney(String sxmoney) {
        this.sxmoney = sxmoney;
    }

    public String getWhat() {
        return what;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public Date getSxtime() {
        return sxtime;
    }

    public void setSxtime(Date sxtime) {
        this.sxtime = sxtime;
    }

    public String getLocalaccount() {
        return Localaccount;
    }

    public void setLocalaccount(String Localaccount) {
        this.Localaccount = Localaccount;
    }

    public String getBookaccount() {
        return bookaccount;
    }

    public void setBookaccount(String bookaccount) {
        this.bookaccount = bookaccount;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    @Override
    public String toString() {
        return "Poundage{" +
        "pID=" + pID +
        ", uID=" + uID +
        ", uname=" + uname +
        ", zname=" + zname +
        ", sxmoney=" + sxmoney +
        ", what=" + what +
        ", sxtime=" + sxtime +
        ", Localaccount=" + Localaccount +
        ", bookaccount=" + bookaccount +
        ", paytype=" + paytype +
        "}";
    }
}
