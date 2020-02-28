package com.demo.p2p.ht.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 用户与审核人
 * </p>
 *
 * @author gzd
 * @since 2020-02-27
 */
public class Userauditor implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 编号
     */
    @TableId(value = "uaid", type = IdType.AUTO)
    private Integer uaid;

    /**
     * 用户ID
     */
    private Integer userid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 审核人ID
     */
    private Integer uauditorid;

    /**
     * 审核人姓名
     */
    private String uauditor;


    public Integer getUaid() {
        return uaid;
    }

    public void setUaid(Integer uaid) {
        this.uaid = uaid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUauditorid() {
        return uauditorid;
    }

    public void setUauditorid(Integer uauditorid) {
        this.uauditorid = uauditorid;
    }

    public String getUauditor() {
        return uauditor;
    }

    public void setUauditor(String uauditor) {
        this.uauditor = uauditor;
    }

    @Override
    public String toString() {
        return "Userauditor{" +
        "uaid=" + uaid +
        ", userid=" + userid +
        ", username=" + username +
        ", uauditorid=" + uauditorid +
        ", uauditor=" + uauditor +
        "}";
    }
}
