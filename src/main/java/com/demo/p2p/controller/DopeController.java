package com.demo.p2p.controller;

import com.demo.p2p.entity.Dope;
import com.demo.p2p.service.DopeService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String grzx_xtxx(Model model,@RequestParam(value="currpage",required=false)String conent){
        int pagecount = 1;//每页显示行数
        int currpage = 1;//当前行数
        int totalPage = 0;//总页数
        int totalRow = 0;//总行数
        //获取总行数
        totalRow=dopeService.list().size();
        System.out.println(totalRow+"////////////////");
        //分页
        totalPage = (totalRow + pagecount - 1) / pagecount;
        if(conent!=null&&!"".equals(conent)){
            currpage=Integer.parseInt(conent);
        }
        if(currpage<1){
            currpage=1;
        }
        if(currpage>totalPage){
            currpage=totalPage;
        }
        Integer candp = (currpage - 1) * pagecount;
        Map<String, Object> map=new HashMap<>();
        map.put("pagecount", pagecount);
        map.put("currpage", candp);
        List<Dope> list = dopeService.findDope(map);
        model.addAttribute("list",list);
        model.addAttribute("pagecount",pagecount);
        model.addAttribute("currpage",currpage);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("totalRow",totalRow);
        return "messages";
    }
    @RequestMapping(value = "/grzx_sc")
    @ResponseBody
    public String grzx_sc(String delitems) {
        String[] item = delitems.split(",");
        List list = new ArrayList();
        for (int i = 0; i < item.length; i++) {
            list.add(item[i]);
        }
        for (int i = 0; i < list.size(); i++) {
            dopeService.batchDeletes(Integer.parseInt((String) list.get(i)));
        }
        return "success";
    }
}

