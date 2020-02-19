package com.demo.p2p.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.p2p.entity.Biao;
import com.demo.p2p.entity.Investinfo;
import com.demo.p2p.entity.Trade;
import com.demo.p2p.entity.Users;
import com.demo.p2p.service.BiaoService;
import com.demo.p2p.service.InvestinfoService;
import com.demo.p2p.service.TradeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 投资表 前端控制器
 * </p>
 *
 * @author gzd
 * @since 2020-02-12
 */
@Controller
@RequestMapping("/investinfo")
public class InvestinfoController {
    @Resource
    private InvestinfoService investinfoService;
    @Resource
    private TradeService tradeService;
    @Resource
    private BiaoService biaoService;

    /**
     * 获取信息进入投资记录页面
     * @param current
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/toInvestcordPage")
    public String toInvestcordPage(Integer current,Model model, HttpSession session){
        //获取当前登录用户的信息
        Users users=(Users)session.getAttribute("loginUser");
        //没有当前页参数就赋值1
        if(current==null){
            current=1;
        }
        //分页查询
        Page<Investinfo> page=new Page<Investinfo>(current,5);
        page=investinfoService.selInvestinfoPageByUId(users.getUid(),page);
        model.addAttribute("page",page);
        List<Investinfo> list=page.getRecords();
        //查询biao表所有数据
        List<Biao> biaos=biaoService.list();
        model.addAttribute("list",list);
        model.addAttribute("biao",biaos);

        // 查出总额信息，在mapper中编写动态sql
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rowName", "inmoney");// 查出投资总额
        map.put("tableName", "investinfo");
        if(users != null){//用户已登录就查出此用户的数据否则所有数据
            map.put("uid", users.getUid());
        }

        Double tm = investinfoService.sumMoney(map);//查出投资总额
        tm=tm==null?0:tm;
        model.addAttribute("tm", tm);
        map.put("rowName", "profitmoney");

        Double gm = investinfoService.sumMoney(map);// 查出收益总额
        model.addAttribute("gm", gm);

        //查出退还的本金
        List<Trade> tmonery = tradeService.selectMoney(users.getUid());
        Integer allM = 0;
        for(Trade tr : tmonery){
            String money = tr.getJymoney().replace("+", "");
            allM += Integer.parseInt(money);
        }

        //查出总收益
        Integer gtm = investinfoService.getMoney(users.getUid());
        model.addAttribute("gtm", gtm);
        model.addAttribute("thm", allM);
        return "investrecord";
    }

    @RequestMapping(value = "/toInvestInfo")
    public String toInvestInfo(String bmid,Model model){

        return "redirect:/sys/index";
    }
}

