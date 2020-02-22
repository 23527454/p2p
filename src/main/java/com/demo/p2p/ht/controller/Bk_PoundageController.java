package com.demo.p2p.ht.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.p2p.ht.entity.Poundage;
import com.demo.p2p.ht.service.Bk_PoundageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 手续费记录表 前端控制器
 * </p>
 *
 * @author gzd
 * @since 2020-02-22
 */
@Controller
@RequestMapping("/bk/pa")
public class Bk_PoundageController {
    @Resource
    private Bk_PoundageService poundageService;

    @RequestMapping(value = "/poundage")
    public String poundage(Integer current,String uname,String zname,String yyy,String yyyy,Model model) throws Exception{
        QueryWrapper<Poundage> queryWrapper=new QueryWrapper<>();
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
            queryWrapper.ge("sxtime",date);
        }
        if(yyyy!=null && yyyy!=""){
            Date date = sdf.parse(yyyy);
            queryWrapper.le("sxtime",date);
        }
        Page<Poundage> page=new Page<>(current,5);
        IPage<Poundage> iPage=poundageService.page(page,queryWrapper);
        List<Poundage> list=iPage.getRecords();

        model.addAttribute("page",iPage);
        model.addAttribute("lpa",list);
        model.addAttribute("uname",uname);
        model.addAttribute("zname",zname);
        model.addAttribute("yyy",yyy);
        model.addAttribute("yyyy",yyyy);
        return "view/Poundagelist";
    }
}

