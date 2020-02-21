package com.demo.p2p.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.p2p.entity.*;
import com.demo.p2p.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
            String money = tr.getJymoney()+"";
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
        String reea = (String)session.getAttribute("reea");
        if (reea != null && reea != ""){
            if (reea.equals("no")){
                session.setAttribute("reea","yes");
            }else {
                session.setAttribute("end","no");
            }
        }else {
            session.setAttribute("end","no");
        }
        System.out.println(reea);
        System.out.println(session.getAttribute("end"));
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

    @RequestMapping("investAdd_do")
    public String investAdd(double money, HttpServletRequest request) {// 投标

        HttpSession session = request.getSession();


        Product pro = (Product) session.getAttribute("Product");

        Investinfo ii = new Investinfo();
        Users user = (Users) session.getAttribute("loginUser");
        // inid; //'投资信息表主键',
        // ii.setInid(2);
        if(user != null){
            Date date = new Date();
            ii.setUserid(user.getUid()); // '投资用户主键',
            ii.setBrrowid(pro.getId());//
            ii.setInmoney(money); // '投资金额',
            ii.setInstatus("不用"); // '投资状态 0 收益中的投资 1已完成的投资',
            ii.setInstyle("不用"); // '投资类型',
            ii.setBrrowstatus("不用");
            ii.setInterest(pro.getPincome()); // '投资利率',
            ii.setProfitmodel(pro.getPway()); // '盈利方式 如等额本金',
            ii.setProfitmoney(0.00); // '盈利金额',
            ii.setIndate(date); // '投资时间，可为空'
            long days = (pro.getPcount().getTime() - pro.getPtime().getTime())
                    / (24 * 60 * 60 * 1000);// 相差几天
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String ds = sdf.format(pro.getPcount());

            ii.setReplaydate(ds + "(共" + days + "天)");
            ii.setMarkstatus(0); // '投标状态 0默认投标中 1 投标通过（中标） 2投标未通过（失标）';

            System.out.println(ii.toString());
            session.removeAttribute("Product");
            session.removeAttribute("Details");
            investinfoService.addInfo(ii);//添加投资记录

            double kym = (Double) session.getAttribute("kymoney");//可用总金额
            double nkym = kym - money;//扣除投资后剩余的可用金额
            Certification certification = new Certification();
            certification.setId(user.getUid());
            certification.setCbalance(nkym);
            certificationService.updateById(certification);
            session.removeAttribute("kymoney");
            Trade td = new Trade();
            td.setuID(user.getUid());
            td.setUname(user.getUnickname());
            td.setZname(user.getUname());
            td.setJymoney(money);
            td.setOther("要投资就要舍得花钱");
            tradeService.save(td);
            Double updMoney = pro.getPmoney() + money;
            System.out.println("修改完后的金额"+updMoney);
            pro.setPmoney(updMoney.intValue());
            productService.updateById(pro);
            DecimalFormat df = new DecimalFormat( "0.00");
            String udm = df.format(updMoney).toString();
            String odm = df.format(pro.getPtotalmoney()).toString();
            if(udm.equals(odm)){//刚好凑集完
                pro.setPstate("2");//修改为凑资完
                productService.updateById(pro);
            }
            session.setAttribute("end", "end");
            session.setAttribute("reea","no");
        }

        return "redirect:/investinfo/toInvestInfo?bmid="+pro.getId();
    }
}

