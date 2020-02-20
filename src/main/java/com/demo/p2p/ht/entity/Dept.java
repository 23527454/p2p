package com.demo.p2p.ht.entity;

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
 * @since 2020-02-20
 */
public class Dept implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 部门编号
     */
    @TableId(value = "did", type = IdType.AUTO)
    private Integer did;

    /**
     * 部门名称
     */
    private String dname;

    /**
     * 部门创建时间
     */
    private Date dtime;

    /**
     * 部门状态1为可用 0为不可用
     */
    private Integer dstatus;

    /**
     * 描述
     */
    private String describes;


    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public Date getDtime() {
        return dtime;
    }

    public void setDtime(Date dtime) {
        this.dtime = dtime;
    }

    public Integer getDstatus() {
        return dstatus;
    }

    public void setDstatus(Integer dstatus) {
        this.dstatus = dstatus;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    @Override
    public String toString() {
        return "Dept{" +
        "did=" + did +
        ", dname=" + dname +
        ", dtime=" + dtime +
        ", dstatus=" + dstatus +
        ", describes=" + describes +
        "}";
    }
}
