package com.demo.p2p.ht.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.p2p.ht.entity.Biao;
import com.demo.p2p.ht.entity.Borrowcord;
import com.demo.p2p.ht.entity.Borrowmoney;
import com.demo.p2p.ht.service.Bk_BiaoService;
import com.demo.p2p.ht.service.Bk_BorrowcordService;
import com.demo.p2p.ht.service.Bk_BorrowmoneyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
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
    @Resource
    private Bk_BiaoService biaoService;
    @Resource
    private Bk_BorrowcordService borrowcordService;

    @RequestMapping(value = "/audit")
    public String audit(Integer id,Integer status){
        Borrowmoney borrowmoney = borrowmoneyService.getById(id);
        borrowmoney.setBstate(status.toString());
        borrowmoneyService.updateById(borrowmoney);
        return "redirect:/bk/brower/check";
    }

    /**
     *借款审核通过，并添加还款表数据
     * @param borrowmoney
     * @return
     */
    @RequestMapping(value = "/borxg")
    public String borxg(Borrowmoney borrowmoney){
        //修改借款的状态
        borrowmoney.setBstate("1");
        borrowmoneyService.updateById(borrowmoney);

        //创建还款对象
        Borrowcord borrowcord=new Borrowcord();
        int ys = Integer.parseInt(borrowmoney.getBtimelimit());

        Date date = new Date();

        borrowcord.setBstatue(0);// 设置还款记录表状态
        borrowcord.setBid(borrowmoney.getId());// 设置借款表ID

        Calendar calendar = Calendar.getInstance();// 时间转换
        if (borrowmoney.getBserial() != "1") {
            for (int i = 0; i < ys; i++) {
                calendar.setTime(date);
                calendar.add(Calendar.SECOND,  60 * 60 * 24 * 30);
                date = calendar.getTime();
                borrowcord.setBdate(date);
                borrowcord.setBcs(i + 1);
                borrowcordService.save(borrowcord);
            }
        } else {
            calendar.setTime(date);
            calendar.add(Calendar.SECOND, 1000 * 60 * 60 * 24 * 30 * ys);
            date = calendar.getTime();
            borrowcord.setBdate(date);
            borrowcord.setBcs(1);
            borrowcordService.save(borrowcord);
        }
        return "redirect:/bk/brower/check";
    }

    /**
     * 进入借款审核详情页面
     * @param ids
     * @param model
     * @return
     */
    @RequestMapping(value = "/borqr")
    public String borqr(Integer ids,Model model){
        Borrowmoney borrowmoney=borrowmoneyService.getById(ids);
        model.addAttribute("borr",borrowmoney);
        return "view/bk_huankuanget";
    }

    /**
     * 进入待审核的借款
     * @param current
     * @param model
     * @return
     */
    @RequestMapping(value = "/check")
    public String check(Integer current,Model model){
        if(current==null || current==0){
            current=1;
        }
        QueryWrapper<Borrowmoney> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("bstate","0");
        Page<Borrowmoney> page=new Page<>(current,5);
        IPage<Borrowmoney> iPage=borrowmoneyService.page(page,queryWrapper);
        List<Borrowmoney> list=iPage.getRecords();
        List<Biao> biaos=biaoService.list();

        model.addAttribute("lists",list);
        model.addAttribute("bList",biaos);
        model.addAttribute("page",iPage);
        return "view/bk_money_check";
    }

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

