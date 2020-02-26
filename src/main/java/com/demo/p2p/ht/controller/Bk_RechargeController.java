package com.demo.p2p.ht.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.p2p.ht.entity.Recharge;
import com.demo.p2p.ht.service.Bk_RechargeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 充值记录表 前端控制器
 * </p>
 *
 * @author gzd
 * @since 2020-02-22
 */
@Controller
@RequestMapping("/bk/rc")
public class Bk_RechargeController {
    @Resource
    private Bk_RechargeService rechargeService;

    @RequestMapping(value = "/rech")
    public String rech(Integer current,String uname,String yyy,String yyyy,String zflx,String statu, Model model)  throws Exception{
        QueryWrapper<Recharge> queryWrapper=new QueryWrapper<>();
        if(current==null || current==0){
            current=1;
        }
        if(uname!=null && uname!=""){
            queryWrapper.like("uname",uname);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(yyy!=null && yyy!=""){
            Date date = sdf.parse(yyy);
            queryWrapper.ge("cztime",date);
        }
        if(yyyy!=null && yyyy!=""){
            Date date = sdf.parse(yyyy);
            queryWrapper.le("cztime",date);
        }
        if(zflx!=null && zflx!=""){
            queryWrapper.eq("czlx",zflx);
        }
        if(statu!=null && statu!=""){
            queryWrapper.eq("statu",statu);
        }

        Page<Recharge> page=new Page<>(current,5);
        IPage<Recharge> iPage=rechargeService.page(page,queryWrapper);
        List<Recharge> list=iPage.getRecords();
        if (current>iPage.getPages()){
            page=new Page<>(1,5);
            iPage=rechargeService.page(page,queryWrapper);
            list=iPage.getRecords();
        }

        model.addAttribute("page",iPage);
        model.addAttribute("lrc",list);
        model.addAttribute("uname",uname);
        model.addAttribute("yyy",yyy);
        model.addAttribute("yyyy",yyyy);
        model.addAttribute("zflx",zflx);
        model.addAttribute("statu",statu);
        model.addAttribute("czmoneyre",rechargeService.sumCzMoney());
        model.addAttribute("dzmoneyre",rechargeService.sumDzMoney());
        return "view/Rechargelist";
    }
}

