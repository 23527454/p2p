package com.demo.p2p.ht.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 提现管理表
 * </p>
 *
 * @author gzd
 * @since 2020-02-22
 */
public class Withdrawal implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * ID编号
     */
    @TableId(value = "wID", type = IdType.AUTO)
    private Integer wID;

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
     * 提现账号
     */
    private String txnum;

    /**
     * 提现银行
     */
    private String txbank;

    /**
     * 提现金额
     */
    private Double txmoney;

    /**
     * 到账金额
     */
    private Double dzmoney;

    /**
     * 手续费
     */
    private Double sxf;

    /**
     * 提现时间
     */
    private Date txtime;

    /**
     * 转账时间
     */
    private Date zztime;

    /**
     * 状态（失败，已提现,转账中，审核中）
     */
    private String statu;

    /**
     * 审核人
     */
    private String shwho;

    /**
     * 审核时间
     */
    private LocalDateTime shtime;

    /**
     * 备注
     */
    private String nothing;


    public Integer getwID() {
        return wID;
    }

    public void setwID(Integer wID) {
        this.wID = wID;
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

    public String getTxnum() {
        return txnum;
    }

    public void setTxnum(String txnum) {
        this.txnum = txnum;
    }

    public String getTxbank() {
        return txbank;
    }

    public void setTxbank(String txbank) {
        this.txbank = txbank;
    }

    public Double getTxmoney() {
        return txmoney;
    }

    public void setTxmoney(Double txmoney) {
        this.txmoney = txmoney;
    }

    public Double getDzmoney() {
        return dzmoney;
    }

    public void setDzmoney(Double dzmoney) {
        this.dzmoney = dzmoney;
    }

    public Double getSxf() {
        return sxf;
    }

    public void setSxf(Double sxf) {
        this.sxf = sxf;
    }

    public Date getTxtime() {
        return txtime;
    }

    public void setTxtime(Date txtime) {
        this.txtime = txtime;
    }

    public Date getZztime() {
        return zztime;
    }

    public void setZztime(Date zztime) {
        this.zztime = zztime;
    }

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    public String getShwho() {
        return shwho;
    }

    public void setShwho(String shwho) {
        this.shwho = shwho;
    }

    public LocalDateTime getShtime() {
        return shtime;
    }

    public void setShtime(LocalDateTime shtime) {
        this.shtime = shtime;
    }

    public String getNothing() {
        return nothing;
    }

    public void setNothing(String nothing) {
        this.nothing = nothing;
    }

    @Override
    public String toString() {
        return "Withdrawal{" +
        "wID=" + wID +
        ", uID=" + uID +
        ", uname=" + uname +
        ", zname=" + zname +
        ", txnum=" + txnum +
        ", txbank=" + txbank +
        ", txmoney=" + txmoney +
        ", dzmoney=" + dzmoney +
        ", sxf=" + sxf +
        ", txtime=" + txtime +
        ", zztime=" + zztime +
        ", statu=" + statu +
        ", shwho=" + shwho +
        ", shtime=" + shtime +
        ", nothing=" + nothing +
        "}";
    }
}
