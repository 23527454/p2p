package com.demo.p2p.ht.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.p2p.ht.entity.Borrowmoney;
import com.demo.p2p.ht.service.Bk_BorrowmoneyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gzd
 * @since 2020-02-22
 */
@Controller
@RequestMapping("/bk/brower")
public class Bk_BorrowmoneyController {
    @Resource
    private Bk_BorrowmoneyService borrowmoneyService;

    @RequestMapping(value = "/bajax")
    @ResponseBody
    public Borrowmoney bajax(Integer id){
        return borrowmoneyService.getById(id);
    }

    @RequestMapping(value = "/hjyList")
    public String hjyList(Integer current, Model model){
        if(current==null || current==0){
            current=1;
        }
        Page<Borrowmoney> page=new Page<>(current,5);
        IPage<Borrowmoney> iPage=borrowmoneyService.page(page);
        List<Borrowmoney> list=iPage.getRecords();
        model.addAttribute("page",iPage);
        model.addAttribute("wdlist",list);
        return "view/Borrowmoneylist";
    }
}

