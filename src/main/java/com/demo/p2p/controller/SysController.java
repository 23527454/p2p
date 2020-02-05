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
    public String infor(){
        return "infor";
    }
}
