package com.demo.p2p.ht.entity;

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
 * @since 2020-03-12
 */
public class Dope implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "did", type = IdType.AUTO)
    private Integer did;

    /**
     * 关联外键
     */
    private Integer dprimkey;

    /**
     * 标题
     */
    private String dtitle;

    /**
     * 消息详情
     */
    private String details;

    /**
     * 操作时间
     */
    private Date dtime;


    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public Integer getDprimkey() {
        return dprimkey;
    }

    public void setDprimkey(Integer dprimkey) {
        this.dprimkey = dprimkey;
    }

    public String getDtitle() {
        return dtitle;
    }

    public void setDtitle(String dtitle) {
        this.dtitle = dtitle;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getDtime() {
        return dtime;
    }

    public void setDtime(Date dtime) {
        this.dtime = dtime;
    }

    @Override
    public String toString() {
        return "Dope{" +
        "did=" + did +
        ", dprimkey=" + dprimkey +
        ", dtitle=" + dtitle +
        ", details=" + details +
        ", dtime=" + dtime +
        "}";
    }
}
