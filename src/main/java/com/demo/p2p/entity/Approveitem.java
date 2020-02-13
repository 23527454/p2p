package com.demo.p2p.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 认证项管理
 * </p>
 *
 * @author gzd
 * @since 2020-02-13
 */
public class Approveitem implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 认证项编号
     */
    @TableId(value = "aiid", type = IdType.AUTO)
    private Integer aiid;

    /**
     * 认证项名称
     */
    private String ainame;

    /**
     * 认证项类型  1.基本项; 2.可选项
     */
    private String aitype;

    /**
     * 认证项状态  0.已弃用; 1.未弃用
     */
    private String aistate;


    public Integer getAiid() {
        return aiid;
    }

    public void setAiid(Integer aiid) {
        this.aiid = aiid;
    }

    public String getAiname() {
        return ainame;
    }

    public void setAiname(String ainame) {
        this.ainame = ainame;
    }

    public String getAitype() {
        return aitype;
    }

    public void setAitype(String aitype) {
        this.aitype = aitype;
    }

    public String getAistate() {
        return aistate;
    }

    public void setAistate(String aistate) {
        this.aistate = aistate;
    }

    @Override
    public String toString() {
        return "Approveitem{" +
        "aiid=" + aiid +
        ", ainame=" + ainame +
        ", aitype=" + aitype +
        ", aistate=" + aistate +
        "}";
    }
}
