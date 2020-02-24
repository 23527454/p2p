package com.demo.p2p.ht.controller;


import com.demo.p2p.ht.entity.Withdrawal;
import com.demo.p2p.ht.service.Bk_WithdrawalService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 提现管理表 前端控制器
 * </p>
 *
 * @author gzd
 * @since 2020-02-22
 */
@Controller
@RequestMapping("/bk/withdrawal")
public class Bk_WithdrawalController {
    @Resource
    Bk_WithdrawalService bk_withdrawalService;

    @RequestMapping(value = "/wlist_do")
    public String wlist_do(String currpage,Integer btn,String wname,String yyy,String yyyy,Integer wstatu,HttpSession session){
        int pagerow = 5;// 每页5行
        int currpages = 1;// 当前页
        int totalpage = 0;// 总页数
        int totalrow = 0;// 总行数
        Map<String, Object> parameters = new HashMap<String, Object>();
        if (wname != null && wname != ""){
            parameters.put("uname",wname);
            session.setAttribute("wname",wname);
        }
        if (yyy != null && yyy != ""){
            parameters.put("yyy",yyy);
            session.setAttribute("yyy",yyy);
        }
        if (yyyy != null && yyyy != ""){
            parameters.put("yyyy",yyyy);
            session.setAttribute("yyyy",yyyy);
        }
        if (wstatu != null){
            parameters.put("statu",wstatu);
            session.setAttribute("statu",wstatu);
        }
        if (btn != null){
            parameters.put("statu",btn);
            session.setAttribute("btn",btn);
        }
        List<Withdrawal> list = bk_withdrawalService.sellist(parameters);
        totalrow = list.size();
        if (currpage != null && !"".equals(currpage)) {
            currpages = Integer.parseInt(currpage);
        }

        int outcount = 0;// 不够一页的数据条数
        int count = 0;//
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


        double sumtxmoney = 0;
        double sumdzmoney = 0;
        double sumsxf = 0;
        parameters.put("pandc", 5);
        parameters.put("candp", candp);
        list = bk_withdrawalService.sellist(parameters);
        for (Withdrawal withdrawal : list) {
            sumtxmoney += withdrawal.getTxmoney();
            sumdzmoney += withdrawal.getDzmoney();
            sumsxf += withdrawal.getSxf();
        }
        session.setAttribute("totalrow", totalrow);
        session.setAttribute("currpages", currpages);
        session.setAttribute("totalpage", totalpage);
        session.setAttribute("sumtxmoney",sumtxmoney);
        session.setAttribute("sumdzmoney",sumdzmoney);
        session.setAttribute("sumsxf",sumsxf);
        session.setAttribute("wdlist",list);
        return "view/Withdrawallist";
    }
}

