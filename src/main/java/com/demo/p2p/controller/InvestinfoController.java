package com.demo.p2p.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.p2p.entity.*;
import com.demo.p2p.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
    @Resource
    private ProductService productService;
    @Resource
    private DetailsService detailsService;
    @Resource
    private CertificationService certificationService;

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
    public String toInvestInfo(String bmid,String currpage, HttpServletRequest req) {
        System.out.println(bmid.toString());
        HttpSession session = req.getSession();
        int pagerow = 5;// 每页5行
        int currpages = 1;// 当前页
        int totalpage = 0;// 总页数
        int totalrow = 0;// 总行数

        int outcount = 0;// 不够一页的数据条数
        int count = 0;//

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("bid", bmid);
        List<Investinfo> page = investinfoService.selInfo(parameters);// 查出数据条数
        totalrow = page.size();// 获取总行数x
        System.out.println("此标的投资信息记录条数" + totalrow);
        if (currpage != null && !"".equals(currpage)) {
            currpages = Integer.parseInt(currpage);
        }

        outcount = totalrow % pagerow;
        count = totalrow / pagerow;

        totalpage = count;

        if (outcount > 0) {
            totalpage = count + 1;
        }

        if (currpages < 1) {
            currpages = 1;
        }
        if (currpages > totalpage) {
            currpages = totalpage;
        }

        Integer candp = (currpages - 1) * pagerow;
        if (candp < 0) {
            candp = 0;
        }
        parameters.put("pandc", 5);
        parameters.put("candp", candp);

        List<Investinfo> lists = investinfoService.selInfo(parameters);
        session.setAttribute("totalrow", totalrow);
        session.setAttribute("currpages", currpages);
        session.setAttribute("totalpage", totalpage);
        session.setAttribute("bmid", bmid);
        session.setAttribute("record", lists);

        // 查出一些总额
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rowName", "inmoney");// 查出投资总额
        map.put("tableName", "investinfo");
        map.put("bid", bmid);//获得此标的所有投资记录

        Double tm = investinfoService.sumMoney(map);
        session.setAttribute("tm", tm);
        System.out.println("tm" + tm);
        map.put("rowName", "profitmoney");// 查出收益总额

        Double gm = investinfoService.sumMoney(map);
        session.setAttribute("gm", gm);
        System.out.println("gm" + gm);

        List<Biao> biao = biaoService.selList();
        session.setAttribute("biao", biao);


        Product pro = productService.selById(Integer.parseInt(bmid));
        session.setAttribute("Borrowmoney", pro);

        List<Details> list = detailsService.selList(pro.getDid());
        System.out.println("标详情列表大小" + list.size());
        session.setAttribute("Product", pro);
        session.setAttribute("Details", list);

        System.out.println("pro.getPstate()获取到的值为   " + pro.getPstate());
        long days = (pro.getPcount().getTime() - pro.getPtime().getTime())
                / (24 * 60 * 60 * 1000);
        session.setAttribute("days", days);
        if (pro.getPstate().equals("1")) {
            Users us = (Users) req.getSession().getAttribute("loginUser");
            us.getUname();
            if (us != null) {
                Certification certification = certificationService.selById(us.getUid());
                Double kymoney = certification.getCbalance();
                System.out.println("进入到输入金额页面  用户余额" + kymoney);
                session.setAttribute("kymoney", kymoney);
            }
            return "redirect:/sys/inforadd";
        } else {
            System.out.println("进入到显示页面");
            return "redirect:/sys/infor";
        }
    }
}

