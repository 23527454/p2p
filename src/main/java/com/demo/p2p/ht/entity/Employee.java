package com.demo.p2p.ht.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author gzd
 * @since 2020-02-18
 */
public class Employee implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 员工编号
     */
    @TableId(value = "eid", type = IdType.AUTO)
    private Integer eid;

    /**
     * 员工姓名
     */
    private String ename;

    /**
     * 员工性别
     */
    private String esex;

    /**
     * 生日
     */
    private LocalDateTime ebirth;

    /**
     * 身份证号码
     */
    private String eidcard;

    /**
     * 电话号码
     */
    private String ephone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 部门编号
     */
    private Integer edeptno;

    /**
     * 职位编号
     */
    private String epostno;

    /**
     * 入职时间
     */
    private LocalDateTime etime;

    /**
     * 状态1为在职 0为离职
     */
    private Integer estatus;

    /**
     * 密码
     */
    private String epassword;


    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getEsex() {
        return esex;
    }

    public void setEsex(String esex) {
        this.esex = esex;
    }

    public LocalDateTime getEbirth() {
        return ebirth;
    }

    public void setEbirth(LocalDateTime ebirth) {
        this.ebirth = ebirth;
    }

    public String getEidcard() {
        return eidcard;
    }

    public void setEidcard(String eidcard) {
        this.eidcard = eidcard;
    }

    public String getEphone() {
        return ephone;
    }

    public void setEphone(String ephone) {
        this.ephone = ephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getEdeptno() {
        return edeptno;
    }

    public void setEdeptno(Integer edeptno) {
        this.edeptno = edeptno;
    }

    public String getEpostno() {
        return epostno;
    }

    public void setEpostno(String epostno) {
        this.epostno = epostno;
    }

    public LocalDateTime getEtime() {
        return etime;
    }

    public void setEtime(LocalDateTime etime) {
        this.etime = etime;
    }

    public Integer getEstatus() {
        return estatus;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }

    public String getEpassword() {
        return epassword;
    }

    public void setEpassword(String epassword) {
        this.epassword = epassword;
    }

    @Override
    public String toString() {
        return "Employee{" +
        "eid=" + eid +
        ", ename=" + ename +
        ", esex=" + esex +
        ", ebirth=" + ebirth +
        ", eidcard=" + eidcard +
        ", ephone=" + ephone +
        ", email=" + email +
        ", edeptno=" + edeptno +
        ", epostno=" + epostno +
        ", etime=" + etime +
        ", estatus=" + estatus +
        ", epassword=" + epassword +
        "}";
    }
}
