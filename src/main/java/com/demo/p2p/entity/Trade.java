package com.demo.p2p.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 交易记录表
 * </p>
 *
 * @author gzd
 * @since 2020-02-13
 */
public class Trade implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * ID编号
     */
    @TableId(value = "tID", type = IdType.AUTO)
    private Integer tID;

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
     * 交易金额
     */
    private String jymoney;

    /**
     * 交易类型
     */
    private String what;

    /**
     * 时间
     */
    private LocalDateTime jytime;

    /**
     * 备注
     */
    private String other;


    public Integer gettID() {
        return tID;
    }

    public void settID(Integer tID) {
        this.tID = tID;
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

    public String getJymoney() {
        return jymoney;
    }

    public void setJymoney(String jymoney) {
        this.jymoney = jymoney;
    }

    public String getWhat() {
        return what;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public LocalDateTime getJytime() {
        return jytime;
    }

    public void setJytime(LocalDateTime jytime) {
        this.jytime = jytime;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Override
    public String toString() {
        return "Trade{" +
        "tID=" + tID +
        ", uID=" + uID +
        ", uname=" + uname +
        ", zname=" + zname +
        ", jymoney=" + jymoney +
        ", what=" + what +
        ", jytime=" + jytime +
        ", other=" + other +
        "}";
    }
}
