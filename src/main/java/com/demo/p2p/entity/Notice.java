package com.demo.p2p.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author gzd
 * @since 2020-02-11
 */
public class Notice implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 公告表id
     */
    @TableId(value = "noticeid", type = IdType.AUTO)
    private Integer noticeid;

    /**
     * 公告表标题
     */
    private String noticetitle;

    /**
     * 公告类型(1网站公告;2.媒体报道;3.管理团队;4,合作伙伴;5.团队风采,6首页图片和网址)
     */
    private String noticetype;

    /**
     * 公告图片
     */
    private String noticepicture;

    /**
     * 公告内容
     */
    private String noticecontent;

    /**
     * 公告最后修改时间,发布时间
     */
    private LocalDateTime noticelasttime;

    /**
     * 公告最后发布人,操作人.
     */
    private Integer noticelastmodifier;


    public Integer getNoticeid() {
        return noticeid;
    }

    public void setNoticeid(Integer noticeid) {
        this.noticeid = noticeid;
    }

    public String getNoticetitle() {
        return noticetitle;
    }

    public void setNoticetitle(String noticetitle) {
        this.noticetitle = noticetitle;
    }

    public String getNoticetype() {
        return noticetype;
    }

    public void setNoticetype(String noticetype) {
        this.noticetype = noticetype;
    }

    public String getNoticepicture() {
        return noticepicture;
    }

    public void setNoticepicture(String noticepicture) {
        this.noticepicture = noticepicture;
    }

    public String getNoticecontent() {
        return noticecontent;
    }

    public void setNoticecontent(String noticecontent) {
        this.noticecontent = noticecontent;
    }

    public LocalDateTime getNoticelasttime() {
        return noticelasttime;
    }

    public void setNoticelasttime(LocalDateTime noticelasttime) {
        this.noticelasttime = noticelasttime;
    }

    public Integer getNoticelastmodifier() {
        return noticelastmodifier;
    }

    public void setNoticelastmodifier(Integer noticelastmodifier) {
        this.noticelastmodifier = noticelastmodifier;
    }

    @Override
    public String toString() {
        return "Notice{" +
        "noticeid=" + noticeid +
        ", noticetitle=" + noticetitle +
        ", noticetype=" + noticetype +
        ", noticepicture=" + noticepicture +
        ", noticecontent=" + noticecontent +
        ", noticelasttime=" + noticelasttime +
        ", noticelastmodifier=" + noticelastmodifier +
        "}";
    }
}
