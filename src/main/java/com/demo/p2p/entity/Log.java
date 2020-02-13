package com.demo.p2p.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author gzd
 * @since 2020-02-12
 */
public class Log implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String laccount;

    private String logtype;

    private String lremark;

    private Date lprocesstime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLaccount() {
        return laccount;
    }

    public void setLaccount(String laccount) {
        this.laccount = laccount;
    }

    public String getLogtype() {
        return logtype;
    }

    public void setLogtype(String logtype) {
        this.logtype = logtype;
    }

    public String getLremark() {
        return lremark;
    }

    public void setLremark(String lremark) {
        this.lremark = lremark;
    }

    public Date getLprocesstime() {
        return lprocesstime;
    }

    public void setLprocesstime(Date lprocesstime) {
        this.lprocesstime = lprocesstime;
    }

    @Override
    public String toString() {
        return "Log{" +
        "id=" + id +
        ", laccount=" + laccount +
        ", logtype=" + logtype +
        ", lremark=" + lremark +
        ", lprocesstime=" + lprocesstime +
        "}";
    }

    public Log() {
    }

    public Log(String laccount, String logtype, String lremark, Date lprocesstime) {
        this.laccount = laccount;
        this.logtype = logtype;
        this.lremark = lremark;
        this.lprocesstime = lprocesstime;
    }
}
