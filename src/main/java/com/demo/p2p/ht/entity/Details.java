package com.demo.p2p.ht.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author gzd
 * @since 2020-02-25
 */
public class Details implements Serializable {

private static final long serialVersionUID=1L;

    @TableId(value = "did", type = IdType.AUTO)
    private Integer did;

    /**
     * 详情标题
     */
    private String dname;

    /**
     * 详情内容
     */
    private String dcontent;

    /**
     * 详情类型:(0:借款人信息模块;1,风控步骤模块;2,图片信息模块)
     */
    private Integer dtype;

    private Integer pid;


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

    public String getDcontent() {
        return dcontent;
    }

    public void setDcontent(String dcontent) {
        this.dcontent = dcontent;
    }

    public Integer getDtype() {
        return dtype;
    }

    public void setDtype(Integer dtype) {
        this.dtype = dtype;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "Details{" +
        "did=" + did +
        ", dname=" + dname +
        ", dcontent=" + dcontent +
        ", dtype=" + dtype +
        ", pid=" + pid +
        "}";
    }
}
