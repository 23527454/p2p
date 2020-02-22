package com.demo.p2p.ht.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.p2p.ht.entity.Bankcard;
import com.demo.p2p.ht.service.Bk_BankcardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 银行卡管理表 前端控制器
 * </p>
 *
 * @author gzd
 * @since 2020-02-22
 */
@Controller
@RequestMapping("/bk/bc")
public class Bk_BankcardController {
    @Resource
    private Bk_BankcardService bankcardService;

    @RequestMapping(value = "/bankcard")
    public String backCard(Integer current,String uname,String zname,String yyy,String yyyy, Model model) throws Exception{
        QueryWrapper<Bankcard> queryWrapper=new QueryWrapper<>();
        if(current==null || current==0){
            current=1;
        }
        if(uname!=null && uname!=""){
            queryWrapper.like("uname",uname);
        }
        if(zname!=null && zname!=""){
            queryWrapper.like("zname",zname);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(yyy!=null && yyy!=""){
            Date date = sdf.parse(yyy);
            queryWrapper.ge("tjtime",date);
        }
        if(yyyy!=null && yyyy!=""){
            Date date = sdf.parse(yyyy);
            queryWrapper.le("tjtime",date);
        }
        Page<Bankcard> page=new Page<>(current,5);
        IPage<Bankcard> iPage=bankcardService.page(page,queryWrapper);
        List<Bankcard> list=iPage.getRecords();

        model.addAttribute("page",iPage);
        model.addAttribute("bc",list);
        model.addAttribute("uname",uname);
        model.addAttribute("zname",zname);
        model.addAttribute("yyy",yyy);
        model.addAttribute("yyyy",yyyy);
        return "view/BankCardllist";
    }
}

