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
 * @since 2020-02-09
 */
public class Users implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 用户ID
     */
    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;

    /**
     * 用户名
     */
    private String unickname;

    /**
     * 用户真实姓名
     */
    private String uname;

    /**
     * 邮箱
     */
    private String umailbox;

    /**
     * 手机号
     */
    private String uphonenumber;

    /**
     * 注册时间
     */
    private Date uregisterdate;

    /**
     * 最后登录ip
     */
    private String uflip;

    /**
     * 最后登录时间
     */
    private Date ufldate;

    /**
     * 推荐人
     */
    private String ureferrer;

    /**
     * 推荐人姓名
     */
    private String ureferrername;

    /**
     * 用户汇付号
     */
    private String userpaytoid;

    /**
     * 法大大证书编号
     */
    private String ucertnumber;

    /**
     * 密码
     */
    private String upassword;

    /**
     * 身份证
     */
    private String ucardid;

    /**
     * 头像
     */
    private String uhead;

    /**
     * 支付密码
     */
    private String upwdZd;


    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUnickname() {
        return unickname;
    }

    public void setUnickname(String unickname) {
        this.unickname = unickname;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUmailbox() {
        return umailbox;
    }

    public void setUmailbox(String umailbox) {
        this.umailbox = umailbox;
    }

    public String getUphonenumber() {
        return uphonenumber;
    }

    public void setUphonenumber(String uphonenumber) {
        this.uphonenumber = uphonenumber;
    }

    public Date getUregisterdate() {
        return uregisterdate;
    }

    public void setUregisterdate(Date uregisterdate) {
        this.uregisterdate = uregisterdate;
    }

    public String getUflip() {
        return uflip;
    }

    public void setUflip(String uflip) {
        this.uflip = uflip;
    }

    public Date getUfldate() {
        return ufldate;
    }

    public void setUfldate(Date ufldate) {
        this.ufldate = ufldate;
    }

    public String getUreferrer() {
        return ureferrer;
    }

    public void setUreferrer(String ureferrer) {
        this.ureferrer = ureferrer;
    }

    public String getUreferrername() {
        return ureferrername;
    }

    public void setUreferrername(String ureferrername) {
        this.ureferrername = ureferrername;
    }

    public String getUserpaytoid() {
        return userpaytoid;
    }

    public void setUserpaytoid(String userpaytoid) {
        this.userpaytoid = userpaytoid;
    }

    public String getUcertnumber() {
        return ucertnumber;
    }

    public void setUcertnumber(String ucertnumber) {
        this.ucertnumber = ucertnumber;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public String getUcardid() {
        return ucardid;
    }

    public void setUcardid(String ucardid) {
        this.ucardid = ucardid;
    }

    public String getUhead() {
        return uhead;
    }

    public void setUhead(String uhead) {
        this.uhead = uhead;
    }

    public String getUpwdZd() {
        return upwdZd;
    }

    public void setUpwdZd(String upwdZd) {
        this.upwdZd = upwdZd;
    }

    @Override
    public String toString() {
        return "Users{" +
        "uid=" + uid +
        ", unickname=" + unickname +
        ", uname=" + uname +
        ", umailbox=" + umailbox +
        ", uphonenumber=" + uphonenumber +
        ", uregisterdate=" + uregisterdate +
        ", uflip=" + uflip +
        ", ufldate=" + ufldate +
        ", ureferrer=" + ureferrer +
        ", ureferrername=" + ureferrername +
        ", userpaytoid=" + userpaytoid +
        ", ucertnumber=" + ucertnumber +
        ", upassword=" + upassword +
        ", ucardid=" + ucardid +
        ", uhead=" + uhead +
        ", upwdZd=" + upwdZd +
        "}";
    }
}
