package com.demo.p2p.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author gzd
 * @since 2020-02-27
 */
public class Packetred implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 红包名称
     */
    private String rmoney;

    /**
     * 红包简介
     */
    private String jjname;

    /**
     * 兑换码
     */
    private String dhma;

    /**
     * 兑换日期
     */
    private Date dhtime;

    /**
     * 截止日期
     */
    private Date jztime;

    /**
     * 过期日期
     */
    private Date gqtime;

    /**
     * 红包状态1未使用2已使用3已过期
     */
    private Integer ptype;

    /**
     * 兑换类型 1 为50 2为100
     */
    private Integer dhtype;

    /**
     * 红包状态名字
     */
    private String pname;


    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRmoney() {
        return rmoney;
    }

    public void setRmoney(String rmoney) {
        this.rmoney = rmoney;
    }

    public String getJjname() {
        return jjname;
    }

    public void setJjname(String jjname) {
        this.jjname = jjname;
    }

    public String getDhma() {
        return dhma;
    }

    public void setDhma(String dhma) {
        this.dhma = dhma;
    }

    public Date getDhtime() {
        return dhtime;
    }

    public void setDhtime(Date dhtime) {
        this.dhtime = dhtime;
    }

    public Date getJztime() {
        return jztime;
    }

    public void setJztime(Date jztime) {
        this.jztime = jztime;
    }

    public Date getGqtime() {
        return gqtime;
    }

    public void setGqtime(Date gqtime) {
        this.gqtime = gqtime;
    }

    public Integer getPtype() {
        return ptype;
    }

    public void setPtype(Integer ptype) {
        this.ptype = ptype;
    }

    public Integer getDhtype() {
        return dhtype;
    }

    public void setDhtype(Integer dhtype) {
        this.dhtype = dhtype;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    @Override
    public String toString() {
        return "Packetred{" +
        "uid=" + uid +
        ", id=" + id +
        ", rmoney=" + rmoney +
        ", jjname=" + jjname +
        ", dhma=" + dhma +
        ", dhtime=" + dhtime +
        ", jztime=" + jztime +
        ", gqtime=" + gqtime +
        ", ptype=" + ptype +
        ", dhtype=" + dhtype +
        ", pname=" + pname +
        "}";
    }
}
