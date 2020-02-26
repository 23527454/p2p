package com.demo.p2p.ht.controller;


import com.demo.p2p.ht.entity.Biao;
import com.demo.p2p.ht.entity.Withdrawal;
import com.demo.p2p.ht.service.Bk_BiaoService;
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
 *  前端控制器
 * </p>
 *
 * @author gzd
 * @since 2020-02-25
 */
@Controller
@RequestMapping("/bk/biao")
public class Bk_BiaoController {
    @Resource
    Bk_BiaoService bk_biaoService;


    @RequestMapping(value = "/list")
    public String list(String currpage,HttpSession session){
        int pagerow = 5;// 每页5行
        int currpages = 1;// 当前页
        int totalpage = 0;// 总页数
        int totalrow = 0;// 总行数
        Map<String, Object> parameters = new HashMap<String, Object>();
        List<Biao> list = bk_biaoService.list();
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
        parameters.put("pandc", 5);
        parameters.put("candp", candp);
        List<Biao> sellist = bk_biaoService.sellist(parameters);
        session.setAttribute("totalrow", totalrow);
        session.setAttribute("currpages", currpages);
        session.setAttribute("totalpage", totalpage);
        session.setAttribute("list", sellist);
        return "view/bk_biao_list";
    }
}

