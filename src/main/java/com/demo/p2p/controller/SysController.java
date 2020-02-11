package com.demo.p2p.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/sys")
public class SysController {
    /**
     * 首页
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }

    /**
     * 注册
     * @return
     */
    @RequestMapping(value = "/register")
    public String zhuce(){
        return "register";
    }

    /**
     * 注册成功
     * @return
     */
    @RequestMapping(value = "/register1")
    public String register1(){
        return "register1";
    }

    /**
     * 登录
     * @return
     */
    @RequestMapping(value = "/login")
    public String denglu(){
        return "login";
    }

    /**
     * 我要投资
     * @return
     */
    @RequestMapping(value = "/touzi")
    public String touzi(){
        return "list";
    }

    /**
     * 我要贷款
     * @return
     */
    @RequestMapping(value = "/daikuang")
    public String daikuang(){
        return "borrowadd";
    }

    /**
     * 安全保障/帮助中心
     * @return
     */
    @RequestMapping(value = "/help")
    public String anquan(){
        return "help";
    }

    /**
     * 项目详情
     * @return
     */
    @RequestMapping(value = "/infor")
    public String infor() {
        return "infor";
    }

    /**
     * 个人中心——账户总览
     * @return
     */
    @RequestMapping(value = "/grzx")
    public String zhanghu(){
        return "personalpage";
    }

    /**
     * 个人中心——账户设置
     * @return
     */
    @RequestMapping(value = "/grzx_zhsz")
    public String grzx_zhsz(){
        return "account";
    }

    /**
     * 联系我们
     * @return
     */
    @RequestMapping(value = "/lxwm")
    public String lxwm(){
        return "contact";
    }

    /**
     * 合作伙伴
     * @return
     */
    @RequestMapping(value = "/hzhb")
    public String hzhb(){
        return "informhzhb";
    }

    /**
     * 网站公告
     * @return
     */
    @RequestMapping(value = "/wzgg")
    public String wzgg(){
        return "inform";
    }

    /**
     * 公告详情
     * @return
     */
    @RequestMapping(value = "/xiangqing")
    public String xiangqing(){
        return "informsel";
    }

    /**
     * 管理团队
     * @return
     */
    @RequestMapping(value = "/gltd")
    public String gltd(){
        return "informgltd";
    }

    /**
     * 团队风采
     * @return
     */
    @RequestMapping(value = "/tdfc")
    public String tdfc(){
        return "informtdfc";
    }

    /**
     * 公司简介
     * @return
     */
    @RequestMapping(value = "/gsjj")
    public String gsjj(){
        return "introduce";
    }

    /**
     * 个人中心——投资记录
     * @return
     */
    @RequestMapping(value = "/grzx_tzjl")
    public String grzx_tzjl(){
        return "accounts";
    }

    /**
     * 个人中心——系统消息
     * @return
     */
    @RequestMapping(value = "/grzx_xtxx")
    public String grzx_xtxx(){
        return "messages";
    }

    /**
     * 个人中心——资金记录
     * @return
     */
    @RequestMapping(value = "/grzx_zjjl")
    public String grzx_zjjl(){
        return "moneyrecord";
    }

    /**
     * 个人中心——充值
     * @return
     */
    @RequestMapping(value = "/grzx_cz")
    public String grzx_cz(){
        return "pay";
    }

    /**
     * 法律政策
     * @return
     */
    @RequestMapping(value = "/flzc")
    public String flzc(){
        return "policy";
    }

    /**
     * 资费说明
     * @return
     */
    @RequestMapping(value = "/zfsm")
    public String zfsm(){
        return "postage";
    }

    /**
     * 招贤纳士
     * @return
     */
    @RequestMapping(value = "/zxns")
    public String zxns(){
        return "recruit";
    }

    /**
     * 媒体报道
     * @return
     */
    @RequestMapping(value = "/mtbd")
    public String mtbd(){
        return "reportlist";
    }

    /**
     * 媒体报道详情
     * @return
     */
    @RequestMapping(value = "/mtbdxq")
    public String mtbdxq(){
        return "reportsel";
    }

    /**
     * 法律声明
     * @return
     */
    @RequestMapping(value = "/flsm")
    public String flsm(){
        return "statement";
    }

    /**
     * 开通第三方
     * @return
     */
    @RequestMapping(value = "/grzx_ktdsf")
    public String grzx_ktdsf(){
        return "thirdparty";
    }

    /**
     * 提现
     * @return
     */
    @RequestMapping(value = "/grzx_tx")
    public String grzx_tx(){
        return "Withdraw";
    }

    /**
     * 未开通第三方账户时充值
     * @return
     */
    @RequestMapping(value = "/grzx_cz1")
    public String grzx_cz1(){
        return "Payno";
    }

    /**
     * 未开通第三方账户时提现
     * @return
     */
    @RequestMapping(value = "/grzx_tx1")
    public String grzx_tx1(){
        return "Withdrawno";
    }

    /**
     * 个人中心——回款计划
     * @return
     */
    @RequestMapping(value = "/grzx_hkjh")
    public String grzx_hkjh(){
        return "个人中心-回款计划";
    }

    /**
     * 个人中心——兑换历史
     * @return
     */
    @RequestMapping(value = "/grzx_dhls")
    public String grzx_dhls(){
        return "个人中心-兑换历史";
    }

    /**
     * 个人中心——我的红包
     * @return
     */
    @RequestMapping(value = "/grzx_wdhb")
    public String grzx_wdhb(){
        return "个人中心-我的红包";
    }
}
