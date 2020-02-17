package com.demo.p2p.controller;

import com.demo.p2p.entity.Dope;
import com.demo.p2p.service.DopeService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gzd
 * @since 2020-02-15
 */
@Controller
@RequestMapping("/dope")
public class DopeController {
    @Resource
    public DopeService dopeService;
    /**
     * 个人中心——系统消息
     * @return
     */
    @RequestMapping(value = "/grzx_xtxx")
    public String grzx_xtxx(Model model){
        List<Dope> list = dopeService.list();
        model.addAttribute("list",list);
        return "messages";
    }
}

