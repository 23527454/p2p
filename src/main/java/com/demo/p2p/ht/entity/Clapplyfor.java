package com.demo.p2p.ht.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 信用额度申请
 * </p>
 *
 * @author gzd
 * @since 2020-02-27
 */
public class Clapplyfor implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 申请编号
     */
    @TableId(value = "clpid", type = IdType.AUTO)
    private Integer clpid;

    /**
     * 用户账号
     */
    private String clpubcid;

    /**
     * 用户姓名
     */
    private String clpuname;

    /**
     * 原信用额
     */
    private Double clpporiginal;

    /**
     * 身份证明照片
     */
    private String clpidpaper;

    /**
     * 房产证明照片
     */
    private String clphpoc;

    /**
     * 申请信用额
     */
    private Double clpf;

    /**
     * 申请时间
     */
    private Date clpdate;

    /**
     * 跟踪审核
     */
    private String clpauditor;

    /**
     * 状态
     */
    private String clpstate;


    public Integer getClpid() {
        return clpid;
    }

    public void setClpid(Integer clpid) {
        this.clpid = clpid;
    }

    public String getClpubcid() {
        return clpubcid;
    }

    public void setClpubcid(String clpubcid) {
        this.clpubcid = clpubcid;
    }

    public String getClpuname() {
        return clpuname;
    }

    public void setClpuname(String clpuname) {
        this.clpuname = clpuname;
    }

    public Double getClpporiginal() {
        return clpporiginal;
    }

    public void setClpporiginal(Double clpporiginal) {
        this.clpporiginal = clpporiginal;
    }

    public String getClpidpaper() {
        return clpidpaper;
    }

    public void setClpidpaper(String clpidpaper) {
        this.clpidpaper = clpidpaper;
    }

    public String getClphpoc() {
        return clphpoc;
    }

    public void setClphpoc(String clphpoc) {
        this.clphpoc = clphpoc;
    }

    public Double getClpf() {
        return clpf;
    }

    public void setClpf(Double clpf) {
        this.clpf = clpf;
    }

    public Date getClpdate() {
        return clpdate;
    }

    public void setClpdate(Date clpdate) {
        this.clpdate = clpdate;
    }

    public String getClpauditor() {
        return clpauditor;
    }

    public void setClpauditor(String clpauditor) {
        this.clpauditor = clpauditor;
    }

    public String getClpstate() {
        return clpstate;
    }

    public void setClpstate(String clpstate) {
        this.clpstate = clpstate;
    }

    @Override
    public String toString() {
        return "Clapplyfor{" +
        "clpid=" + clpid +
        ", clpubcid=" + clpubcid +
        ", clpuname=" + clpuname +
        ", clpporiginal=" + clpporiginal +
        ", clpidpaper=" + clpidpaper +
        ", clphpoc=" + clphpoc +
        ", clpf=" + clpf +
        ", clpdate=" + clpdate +
        ", clpauditor=" + clpauditor +
        ", clpstate=" + clpstate +
        "}";
    }
}
