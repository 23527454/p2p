package com.demo.p2p.ht.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 认证记录
 * </p>
 *
 * @author gzd
 * @since 2020-02-27
 */
public class Certifrecord implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 认证记录编号
     */
    @TableId(value = "crid", type = IdType.AUTO)
    private Integer crid;

    /**
     * 用户id
     */
    private Integer cruserid;

    /**
     * 用户姓名
     */
    private String crusername;

    /**
     * 认证项编号
     */
    private Integer craiid;

    /**
     * 认证项名称
     */
    private String crainame;

    /**
     * 认证项类型  1.基本项; 2.可选项
     */
    private String craitype;

    /**
     * 认证状态  0.未申请; 1.待审核；2.审核成功；3.审核失败
     */
    private String crispass;

    /**
     * 审核时间
     */
    private Date crdate;

    /**
     * 审核人
     */
    private String crauditor;

    /**
     * 信用积分
     */
    private Integer crintegral;

    /**
     * 观点
     */
    private String crviewpoint;

    /**
     * 认证图片
     */
    private String crimg;

    /**
     * 上传时间
     */
    private Date crupldate;

    //待审核
    @TableField(exist = false)
    private Integer checkpend;

    public Integer getCheckpend() {
        return checkpend;
    }

    public void setCheckpend(Integer checkpend) {
        this.checkpend = checkpend;
    }

    public Integer getCrid() {
        return crid;
    }

    public void setCrid(Integer crid) {
        this.crid = crid;
    }

    public Integer getCruserid() {
        return cruserid;
    }

    public void setCruserid(Integer cruserid) {
        this.cruserid = cruserid;
    }

    public String getCrusername() {
        return crusername;
    }

    public void setCrusername(String crusername) {
        this.crusername = crusername;
    }

    public Integer getCraiid() {
        return craiid;
    }

    public void setCraiid(Integer craiid) {
        this.craiid = craiid;
    }

    public String getCrainame() {
        return crainame;
    }

    public void setCrainame(String crainame) {
        this.crainame = crainame;
    }

    public String getCraitype() {
        return craitype;
    }

    public void setCraitype(String craitype) {
        this.craitype = craitype;
    }

    public String getCrispass() {
        return crispass;
    }

    public void setCrispass(String crispass) {
        this.crispass = crispass;
    }

    public Date getCrdate() {
        return crdate;
    }

    public void setCrdate(Date crdate) {
        this.crdate = crdate;
    }

    public String getCrauditor() {
        return crauditor;
    }

    public void setCrauditor(String crauditor) {
        this.crauditor = crauditor;
    }

    public Integer getCrintegral() {
        return crintegral;
    }

    public void setCrintegral(Integer crintegral) {
        this.crintegral = crintegral;
    }

    public String getCrviewpoint() {
        return crviewpoint;
    }

    public void setCrviewpoint(String crviewpoint) {
        this.crviewpoint = crviewpoint;
    }

    public String getCrimg() {
        return crimg;
    }

    public void setCrimg(String crimg) {
        this.crimg = crimg;
    }

    public Date getCrupldate() {
        return crupldate;
    }

    public void setCrupldate(Date crupldate) {
        this.crupldate = crupldate;
    }

    @Override
    public String toString() {
        return "Certifrecord{" +
        "crid=" + crid +
        ", cruserid=" + cruserid +
        ", crusername=" + crusername +
        ", craiid=" + craiid +
        ", crainame=" + crainame +
        ", craitype=" + craitype +
        ", crispass=" + crispass +
        ", crdate=" + crdate +
        ", crauditor=" + crauditor +
        ", crintegral=" + crintegral +
        ", crviewpoint=" + crviewpoint +
        ", crimg=" + crimg +
        ", crupldate=" + crupldate +
        "}";
    }
}
