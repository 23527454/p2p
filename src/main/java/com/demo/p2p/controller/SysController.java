package com.demo.p2p.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SysController {

    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }


    /**
     * 跳转公告详情
     * @return
     */
    @RequestMapping(value = "/xiangqing")
    public String xiangqing(){
        return "公司公告详细";
    }

    /**
     * 网站公告
     * @return
     */
    @RequestMapping(value = "/gonggao")
    public String gonggao(){
        return "网站公告";
    }

    /**
     * 推荐项目
     * @return
     */
    @RequestMapping(value = "/tuijian")
    public String tuijian(){
        return "list";
    }

    @RequestMapping(value = "/infor")
    public String infor() {
        return "infor";
    }

    @RequestMapping(value = "/grzx_grzxsy")
    public String grzx_grzxsy(){
        return "个人中心首页";
    }

    @RequestMapping(value = "/grzx_cz")
    public String grzx_cz(){
        return "个人中心-充值";
    }

    @RequestMapping(value = "/grzx_cz1")
    public String grzx_cz1(){
        return "个人中心-充值1";
    }

    @RequestMapping(value = "/grzx_dhls")
    public String grzx_dhls(){
        return "个人中心-兑换历史";
    }

    @RequestMapping(value = "/grzx_hkjh")
    public String grzx_hkjh(){
        return "个人中心-回款计划";
    }

    @RequestMapping(value = "/grzx_ktdsf")
    public String grzx_ktdsf(){
        return "个人中心-开通第三方";
    }

    @RequestMapping(value = "/grzx_ktdsf1")
    public String grzx_ktdsf1(){
        return "个人中心-开通第三方1";
    }

    @RequestMapping(value = "/grzx_wdhb")
    public String grzx_wdhb(){
        return "个人中心-我的红包";
    }

    @RequestMapping(value = "/grzx_tzjl")
    public String grzx_tzjl(){
        return "个人中心-投资记录";
    }

    @RequestMapping(value = "/grzx_tx")
    public String grzx_tx(){
        return "个人中心-提现";
    }

    @RequestMapping(value = "/grzx_tx1")
    public String grzx_tx1(){
        return "个人中心-提现1";
    }

    @RequestMapping(value = "/grzx_xtxx")
    public String grzx_xtxx(){
        return "个人中心-系统消息";
    }

    @RequestMapping(value = "/grzx_zhsz")
    public String grzx_zhsz(){
        return "个人中心-账户设置";
    }

    @RequestMapping(value = "/grzx_zjjl")
    public String grzx_zjjl(){
        return "个人中心-资金记录";
    }
}
