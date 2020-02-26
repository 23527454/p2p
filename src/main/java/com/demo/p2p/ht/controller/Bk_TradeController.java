package com.demo.p2p.ht.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.p2p.ht.entity.Trade;
import com.demo.p2p.ht.service.Bk_TradeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 交易记录表 前端控制器
 * </p>
 *
 * @author gzd
 * @since 2020-02-22
 */
@Controller
@RequestMapping("/bk/td")
public class Bk_TradeController {
    @Resource
    private Bk_TradeService tradeService;

    @RequestMapping(value = "/trade")
    public String trade(Integer current,String uname,String zname,String yyy,String yyyy,Model model) throws Exception{
        QueryWrapper<Trade> queryWrapper=new QueryWrapper<>();
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
            queryWrapper.ge("jytime",date);
        }
        if(yyyy!=null && yyyy!=""){
            Date date = sdf.parse(yyyy);
            queryWrapper.le("jytime",date);
        }
        Page<Trade> page=new Page<>(current,5);
        IPage<Trade> iPage=tradeService.page(page,queryWrapper);
        List<Trade> list=iPage.getRecords();
        if (current>iPage.getPages()){
            page=new Page<>(1,5);
            iPage=tradeService.page(page,queryWrapper);
            list=iPage.getRecords();
        }

        model.addAttribute("page",iPage);
        model.addAttribute("lts",list);
        model.addAttribute("uname",uname);
        model.addAttribute("zname",zname);
        model.addAttribute("yyy",yyy);
        model.addAttribute("yyyy",yyyy);
        return "view/Tradelist";
    }
}

