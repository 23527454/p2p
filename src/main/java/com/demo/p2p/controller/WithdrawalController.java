package com.demo.p2p.controller;


import com.demo.p2p.entity.Bankcard;
import com.demo.p2p.entity.Poundage;
import com.demo.p2p.entity.Users;
import com.demo.p2p.entity.Withdrawal;
import com.demo.p2p.service.BankcardService;
import com.demo.p2p.service.CertificationService;
import com.demo.p2p.service.PoundageService;
import com.demo.p2p.service.WithdrawalService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * 提现管理表 前端控制器
 * </p>
 *
 * @author gzd
 * @since 2020-02-25
 */
@Controller
@RequestMapping("/withdrawal")
public class WithdrawalController {

    @Resource
    private CertificationService certificationService;

    @Resource
    private BankcardService bankcardService;

    @Resource
    private WithdrawalService withdrawalService;

    @Resource
    private PoundageService poundageService;

    @RequestMapping("/withdrawpay")
    public String tixian(HttpServletRequest request, HttpSession session) throws ParseException {
        System.out.println("tixian-------------------------------");

        Date now = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time = df.format(new Date());// new Date()为获取当前系统时间

        Users user = (Users) session.getAttribute("loginUser");
        String bid = request.getParameter("bankl");//选中的下拉列表银行卡
        String actualMoney = request.getParameter("actualMoney");//提现金额 //certification实体类需要扣除得金额

        String blance = request.getParameter("formblance");//总额度
        int id = Integer.parseInt(request.getParameter("id"));//certification实体类得id

        double sxf = Double.parseDouble(actualMoney) * 0.001;
        double dzmoney = Double.parseDouble(actualMoney) - sxf;
//        double blances =  Double.parseDouble(blance);//总额
        double actualMoneys = Double.parseDouble(actualMoney);
//        double sum =  blances - actualMoneys;//总额扣除提现金额
//        System.out.println("需要提交得提现金额" + actualMoneys + "\t提现金额" + actualMoney /*+ "总额:" + blances + "总额减提现金额" + sum*/);


//        System.out.println("actualMoney" + actualMoney);
        Bankcard bankcard = bankcardService.getInfo(Integer.parseInt(bid));//查询下拉列表数据


        Withdrawal wl = new Withdrawal();
        wl.setuID(user.getUid());
        wl.setUname(user.getUnickname());
        wl.setZname(user.getUname());
        wl.setTxnum(bankcard.getCardid());
        wl.setTxbank(bankcard.getKhh());
        wl.setTxmoney(actualMoney);
        wl.setTxtime(time);
        wl.setStatu("3");
        wl.setSxf(String.valueOf(sxf));
        wl.setDzmoney(String.valueOf(dzmoney));

        Poundage pe = new Poundage();
        pe.setuID(user.getUid());
        pe.setUname(user.getUnickname());
        pe.setZname(user.getUname());
        pe.setSxmoney(actualMoney);
        pe.setWhat("提现");
        pe.setSxtime(time);

        certificationService.updateMoney(id, actualMoney);//提现功能对certidication表  提现得钱扣在此表中
        poundageService.save(pe);
        withdrawalService.save(wl);//添加数据到witdrawal表中  提现记录

     /*   System.out.println("uid:\t"+pe.getuID());
        System.out.println("uname:\t"+pe.getUname());
        System.out.println("zname:\t"+pe.getZname());
        System.out.println("sxmoney:\t"+pe.getSxmoney());
        System.out.println("what:\t"+pe.getWhat());
        System.out.println("sxtime:\t"+pe.getSxtime());
*/
      /*  System.out.println("1uid：" + wl.getuID());
        System.out.println("2uname: " + wl.getUname());
        System.out.println("3zname: " + wl.getZname());
        System.out.println("4txnum: " + wl.getTxnum());
        System.out.println("5txbank: " + wl.getTxbank());
        System.out.println("6txmoney: " + wl.getTxmoney());
        System.out.println("7txtime: " + wl.getTxtime());
        System.out.println("8statu: " + wl.getStatu());
        System.out.println("9sxf: " + wl.getSxf());
        System.out.println("10dzmoney: " + wl.getDzmoney());*/

        return "redirect:/grzx/grzx";
    }


}

