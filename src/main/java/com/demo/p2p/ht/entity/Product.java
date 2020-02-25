package com.demo.p2p.ht.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author gzd
 * @since 2020-02-25
 */
public class Product implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 已募集总金额
     */
    private Long pmoney;

    /**
     * 预期年化收益
     */
    private String pincome;

    /**
     * 项目期限
     */
    private Date ptime;

    /**
     * 类型
     */
    private Integer ptype;

    /**
     * 还款方式 到期还本付息 按月付息,到期还本 等额本息
     */
    private String pway;

    /**
     * 还款截止期限
     */
    private Date pcount;

    /**
     * 投资进度
     */
    private String progress;

    /**
     * 保障方式
     */
    private String psaveway;

    /**
     * 需还本息
     */
    private String prateben;

    /**
     * 发布时间
     */
    private Date ppublishtime;

    /**
     * 产品名称
     */
    private String pname;

    /**
     * 募集资金
     */
    private Long ptotalmoney;

    /**
     * 投资范围
     */
    private String prange;

    /**
     * 资金用途
     */
    private String puse;

    /**
     * 状态 1筹资中 2筹资完 3失效,4项目完成
     */
    private String pstate;

    /**
     * 封面
     */
    private String picture;

    /**
     * 标主id
     */
    private Integer pproduce;

    /**
     * 项目描述
     */
    private String pdesc;

    /**
     * 保障措施
     */
    private String psafe;

    /**
     * details表的主键
     */
    private Integer did;

    @TableField(exist = false)
    private Users users;

    @TableField(exist = false)
    private Biao biao;

    @TableField(exist = false)
    private Details details;

    public Biao getBiao() {
        return biao;
    }

    public void setBiao(Biao biao) {
        this.biao = biao;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getPmoney() {
        return pmoney;
    }

    public void setPmoney(Long pmoney) {
        this.pmoney = pmoney;
    }

    public String getPincome() {
        return pincome;
    }

    public void setPincome(String pincome) {
        this.pincome = pincome;
    }

    public Date getPtime() {
        return ptime;
    }

    public void setPtime(Date ptime) {
        this.ptime = ptime;
    }

    public Integer getPtype() {
        return ptype;
    }

    public void setPtype(Integer ptype) {
        this.ptype = ptype;
    }

    public String getPway() {
        return pway;
    }

    public void setPway(String pway) {
        this.pway = pway;
    }

    public Date getPcount() {
        return pcount;
    }

    public void setPcount(Date pcount) {
        this.pcount = pcount;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getPsaveway() {
        return psaveway;
    }

    public void setPsaveway(String psaveway) {
        this.psaveway = psaveway;
    }

    public String getPrateben() {
        return prateben;
    }

    public void setPrateben(String prateben) {
        this.prateben = prateben;
    }

    public Date getPpublishtime() {
        return ppublishtime;
    }

    public void setPpublishtime(Date ppublishtime) {
        this.ppublishtime = ppublishtime;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Long getPtotalmoney() {
        return ptotalmoney;
    }

    public void setPtotalmoney(Long ptotalmoney) {
        this.ptotalmoney = ptotalmoney;
    }

    public String getPrange() {
        return prange;
    }

    public void setPrange(String prange) {
        this.prange = prange;
    }

    public String getPuse() {
        return puse;
    }

    public void setPuse(String puse) {
        this.puse = puse;
    }

    public String getPstate() {
        return pstate;
    }

    public void setPstate(String pstate) {
        this.pstate = pstate;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getPproduce() {
        return pproduce;
    }

    public void setPproduce(Integer pproduce) {
        this.pproduce = pproduce;
    }

    public String getPdesc() {
        return pdesc;
    }

    public void setPdesc(String pdesc) {
        this.pdesc = pdesc;
    }

    public String getPsafe() {
        return psafe;
    }

    public void setPsafe(String psafe) {
        this.psafe = psafe;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    @Override
    public String toString() {
        return "Product{" +
        "id=" + id +
        ", pmoney=" + pmoney +
        ", pincome=" + pincome +
        ", ptime=" + ptime +
        ", ptype=" + ptype +
        ", pway=" + pway +
        ", pcount=" + pcount +
        ", progress=" + progress +
        ", psaveway=" + psaveway +
        ", prateben=" + prateben +
        ", ppublishtime=" + ppublishtime +
        ", pname=" + pname +
        ", ptotalmoney=" + ptotalmoney +
        ", prange=" + prange +
        ", puse=" + puse +
        ", pstate=" + pstate +
        ", picture=" + picture +
        ", pproduce=" + pproduce +
        ", pdesc=" + pdesc +
        ", psafe=" + psafe +
        ", did=" + did +
        "}";
    }
}
