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
 * @since 2020-02-22
 */
public class Borrowmoney implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * //序号//(还款方式:1,到期还本付息,2,按月付息，到期还本3,等额本息)
     */
    private String bserial;

    /**
     * 用户id
     */
    private String busername;

    /**
     * 真实姓名
     */
    private String brelname;

    /**
     * 手机号码
     */
    private String bpass;

    /**
     * 标ID(发布标ID)
     */
    private String btype;

    /**
     * 公司名称
     */
    private String btitle;

    /**
     * 借款金额 
     */
    private String bmoney;

    /**
     * 利率 
     */
    private String brate;

    /**
     * 期限
     */
    private String btimelimit;

    /**
     * 筹标期限
     */
    private String blimit;

    /**
     * 状态(0:提交申请未处理;1,后台审核通过(未还款);2审核失败;3.已还款)
     */
    private String bstate;

    /**
     * 所在区域
     */
    private String brecommend;

    /**
     * 类型(0车贷,1房贷)
     */
    private String bleixing;

    /**
     * 备注1(房屋面积or车辆品牌)
     */
    private String beizhu1;

    /**
     * 备注2(房龄/所属车型)
     */
    private String beizhu2;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBserial() {
        return bserial;
    }

    public void setBserial(String bserial) {
        this.bserial = bserial;
    }

    public String getBusername() {
        return busername;
    }

    public void setBusername(String busername) {
        this.busername = busername;
    }

    public String getBrelname() {
        return brelname;
    }

    public void setBrelname(String brelname) {
        this.brelname = brelname;
    }

    public String getBpass() {
        return bpass;
    }

    public void setBpass(String bpass) {
        this.bpass = bpass;
    }

    public String getBtype() {
        return btype;
    }

    public void setBtype(String btype) {
        this.btype = btype;
    }

    public String getBtitle() {
        return btitle;
    }

    public void setBtitle(String btitle) {
        this.btitle = btitle;
    }

    public String getBmoney() {
        return bmoney;
    }

    public void setBmoney(String bmoney) {
        this.bmoney = bmoney;
    }

    public String getBrate() {
        return brate;
    }

    public void setBrate(String brate) {
        this.brate = brate;
    }

    public String getBtimelimit() {
        return btimelimit;
    }

    public void setBtimelimit(String btimelimit) {
        this.btimelimit = btimelimit;
    }

    public String getBlimit() {
        return blimit;
    }

    public void setBlimit(String blimit) {
        this.blimit = blimit;
    }

    public String getBstate() {
        return bstate;
    }

    public void setBstate(String bstate) {
        this.bstate = bstate;
    }

    public String getBrecommend() {
        return brecommend;
    }

    public void setBrecommend(String brecommend) {
        this.brecommend = brecommend;
    }

    public String getBleixing() {
        return bleixing;
    }

    public void setBleixing(String bleixing) {
        this.bleixing = bleixing;
    }

    public String getBeizhu1() {
        return beizhu1;
    }

    public void setBeizhu1(String beizhu1) {
        this.beizhu1 = beizhu1;
    }

    public String getBeizhu2() {
        return beizhu2;
    }

    public void setBeizhu2(String beizhu2) {
        this.beizhu2 = beizhu2;
    }

    @Override
    public String toString() {
        return "Borrowmoney{" +
        "id=" + id +
        ", bserial=" + bserial +
        ", busername=" + busername +
        ", brelname=" + brelname +
        ", bpass=" + bpass +
        ", btype=" + btype +
        ", btitle=" + btitle +
        ", bmoney=" + bmoney +
        ", brate=" + brate +
        ", btimelimit=" + btimelimit +
        ", blimit=" + blimit +
        ", bstate=" + bstate +
        ", brecommend=" + brecommend +
        ", bleixing=" + bleixing +
        ", beizhu1=" + beizhu1 +
        ", beizhu2=" + beizhu2 +
        "}";
    }
}
