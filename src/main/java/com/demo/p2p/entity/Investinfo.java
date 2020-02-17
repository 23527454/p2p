package com.demo.p2p.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 投资表
 * </p>
 *
 * @author gzd
 * @since 2020-02-12
 */
public class Investinfo implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 投资信息表主键
     */
    @TableId(value = "inid", type = IdType.AUTO)
    private Integer inid;

    /**
     * 投资用户主键
     */
    private Integer userid;

    /**
     * 投标的主键
     */
    private Integer brrowid;

    /**
     * 投资金额
     */
    private BigDecimal inmoney;

    /**
     * 投资状态 0 收益中的投资  1已完成的投资
     */
    private String instatus;

    /**
     * 投资类型
     */
    private String instyle;

    /**
     * 借贷状态是否凑资完
     */
    private String brrowstatus;

    /**
     * 投资利率
     */
    private String interest;

    /**
     * 盈利方式 如等额本金
     */
    private String profitmodel;

    /**
     * 盈利金额
     */
    private BigDecimal profitmoney;

    /**
     * 投资时间，可为空
     */
    private LocalDateTime indate;

    /**
     * 还款期限单位天
     */
    private String replaydate;

    /**
     * 投标状态 0默认投标中 1 投标通过（中标） 2投标未通过（失标）
     */
    private Integer markstatus;


    //自定义属性
    @TableField(exist = false)
    private String title;
    @TableField(exist = false)
    private String type;
    @TableField(exist = false)
    private String uname;

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getInid() {
        return inid;
    }

    public void setInid(Integer inid) {
        this.inid = inid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getBrrowid() {
        return brrowid;
    }

    public void setBrrowid(Integer brrowid) {
        this.brrowid = brrowid;
    }

    public BigDecimal getInmoney() {
        return inmoney;
    }

    public void setInmoney(BigDecimal inmoney) {
        this.inmoney = inmoney;
    }

    public String getInstatus() {
        return instatus;
    }

    public void setInstatus(String instatus) {
        this.instatus = instatus;
    }

    public String getInstyle() {
        return instyle;
    }

    public void setInstyle(String instyle) {
        this.instyle = instyle;
    }

    public String getBrrowstatus() {
        return brrowstatus;
    }

    public void setBrrowstatus(String brrowstatus) {
        this.brrowstatus = brrowstatus;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getProfitmodel() {
        return profitmodel;
    }

    public void setProfitmodel(String profitmodel) {
        this.profitmodel = profitmodel;
    }

    public BigDecimal getProfitmoney() {
        return profitmoney;
    }

    public void setProfitmoney(BigDecimal profitmoney) {
        this.profitmoney = profitmoney;
    }

    public LocalDateTime getIndate() {
        return indate;
    }

    public void setIndate(LocalDateTime indate) {
        this.indate = indate;
    }

    public String getReplaydate() {
        return replaydate;
    }

    public void setReplaydate(String replaydate) {
        this.replaydate = replaydate;
    }

    public Integer getMarkstatus() {
        return markstatus;
    }

    public void setMarkstatus(Integer markstatus) {
        this.markstatus = markstatus;
    }

    @Override
    public String toString() {
        return "Investinfo{" +
        "inid=" + inid +
        ", userid=" + userid +
        ", brrowid=" + brrowid +
        ", inmoney=" + inmoney +
        ", instatus=" + instatus +
        ", instyle=" + instyle +
        ", brrowstatus=" + brrowstatus +
        ", interest=" + interest +
        ", profitmodel=" + profitmodel +
        ", profitmoney=" + profitmoney +
        ", indate=" + indate +
        ", replaydate=" + replaydate +
        ", markstatus=" + markstatus +
        "}";
    }
}
