package com.demo.p2p.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 银行卡管理表
 * </p>
 *
 * @author gzd
 * @since 2020-02-21
 */
public class Bankcard implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * ID编号
     */
    @TableId(value = "bID", type = IdType.AUTO)
    private Integer bID;

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
     * 身份证
     */
    private String sfz;

    /**
     * 开户行
     */
    private String khh;

    /**
     * 卡号
     */
    private String cardid;

    /**
     * 提交时间
     */
    private Date tjtime;

    /**
     * 状态
     */
    private String statu;


    public Integer getbID() {
        return bID;
    }

    public void setbID(Integer bID) {
        this.bID = bID;
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

    public String getSfz() {
        return sfz;
    }

    public void setSfz(String sfz) {
        this.sfz = sfz;
    }

    public String getKhh() {
        return khh;
    }

    public void setKhh(String khh) {
        this.khh = khh;
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    public Date getTjtime() {
        return tjtime;
    }

    public void setTjtime(Date tjtime) {
        this.tjtime = tjtime;
    }

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    @Override
    public String toString() {
        return "Bankcard{" +
        "bID=" + bID +
        ", uID=" + uID +
        ", uname=" + uname +
        ", zname=" + zname +
        ", sfz=" + sfz +
        ", khh=" + khh +
        ", cardid=" + cardid +
        ", tjtime=" + tjtime +
        ", statu=" + statu +
        "}";
    }
}
