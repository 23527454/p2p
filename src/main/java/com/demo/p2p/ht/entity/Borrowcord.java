package com.demo.p2p.ht.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 还款记录表
 * </p>
 *
 * @author gzd
 * @since 2020-02-26
 */
public class Borrowcord implements Serializable {

private static final long serialVersionUID=1L;

    @TableId(value = "boid", type = IdType.AUTO)
    private Integer boid;

    /**
     * 还款表id
     */
    private Integer bid;

    /**
     * 还款时间
     */
    private Date bdate;

    /**
     * 状态(0,未还款,1,已还款)
     */
    private Integer bstatue;

    /**
     * 还款次数
     */
    private Integer bcs;

    /**
     * 备用字段()
     */
    private String bz1;

    /**
     * 备用字段
     */
    private String bz2;


    public Integer getBoid() {
        return boid;
    }

    public void setBoid(Integer boid) {
        this.boid = boid;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public Date getBdate() {
        return bdate;
    }

    public void setBdate(Date bdate) {
        this.bdate = bdate;
    }

    public Integer getBstatue() {
        return bstatue;
    }

    public void setBstatue(Integer bstatue) {
        this.bstatue = bstatue;
    }

    public Integer getBcs() {
        return bcs;
    }

    public void setBcs(Integer bcs) {
        this.bcs = bcs;
    }

    public String getBz1() {
        return bz1;
    }

    public void setBz1(String bz1) {
        this.bz1 = bz1;
    }

    public String getBz2() {
        return bz2;
    }

    public void setBz2(String bz2) {
        this.bz2 = bz2;
    }

    @Override
    public String toString() {
        return "Borrowcord{" +
        "boid=" + boid +
        ", bid=" + bid +
        ", bdate=" + bdate +
        ", bstatue=" + bstatue +
        ", bcs=" + bcs +
        ", bz1=" + bz1 +
        ", bz2=" + bz2 +
        "}";
    }
}
