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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    @ResponseBody
    public Object tixian(String actualMoney, String bankl, String id, String bankname, String bankhao,
                         HttpServletRequest request, HttpSession session) throws ParseException {
        System.out.println("tixian-------------------------------");
//        System.out.println("bankl:" + bankl);
//        System.out.println("bankname" + bankname);
//        System.out.println("bankhao" + bankhao);

        Date time = new Date();
        Map<String, Object> map = new HashMap<>();
        /*SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time = df.format(new Date());// new Date()为获取当前系统时间*/
        Users user = (Users) session.getAttribute("loginUser");
    /*    int id = 0;
        String actualMoney = null;
        String bid = null;
        String blance = null;
        double actualMoneys = 0;
        if (request.getParameter("bankl") != null) {
            bid = request.getParameter("bankl");//选中的下拉列表银行卡
        }
        if (request.getParameter("actualMoney") != null) {
            actualMoney = request.getParameter("actualMoney");//提现金额 //certification实体类需要扣除得金额
        }
        if (request.getParameter("formblance") != null) {
            blance = request.getParameter("formblance");//总额度
        }
        if (request.getParameter("id") != null) {
            id = Integer.parseInt(request.getParameter("id"));//certification实体类得id
        }*/


        double sxf = Double.parseDouble(actualMoney) * 0.001;
        double dzmoney = Double.parseDouble(actualMoney) - sxf;

        //        actualMoneys = Double.parseDouble(actualMoney);
        //      double blances =  Double.parseDouble(blance);//总额
        //      double sum =  blances - actualMoneys;//总额扣除提现金额
        //      System.out.println("需要提交得提现金额" + actualMoneys + "\t提现金额" + actualMoney /*+ "总额:" + blances + "总额减提现金额" + sum*/);
        //      System.out.println("actualMoney" + actualMoney);
        Bankcard bankcard = null;
        if (!bankl.equals("未选择")) {
            bankcard = bankcardService.getInfo(Integer.parseInt(bankl));//查询下拉列表数据
        }


        Bankcard bd = new Bankcard();
        System.out.println(bankhao + bankname + user.getUid());
        if (!bankname.equals("") && !bankhao.equals("")) {
            bd.setuID(user.getUid());
            bd.setUname(user.getUnickname());
            bd.setZname(user.getUname());
            bd.setSfz(user.getUcardid());
            bd.setKhh(bankname);
            bd.setCardid(bankhao);
            bd.setTjtime(time);
            bd.setStatu("成功");
        }

        System.out.println("bd" + bd);
        Withdrawal wl = new Withdrawal();
        wl.setuID(user.getUid());
        wl.setUname(user.getUnickname());
        wl.setZname(user.getUname());
        if (bankcard != null) {
            System.out.println(1111111);
            wl.setTxnum(bankcard.getCardid());//提现银行卡号
            wl.setTxbank(bankcard.getKhh());//提现银行名称
        } else {
            System.out.println(2222222);
            wl.setTxnum(bankhao);//提现银行卡号
            wl.setTxbank(bankname);//提现银行名称
        }

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
        int num = 0;
        boolean pbol = false;
        boolean wbol = false;
        boolean bbol = false;
        num = certificationService.updateMoney(id, actualMoney);//提现功能对certidication表  提现得钱扣在此表中
        pbol = poundageService.save(pe);
        wbol = withdrawalService.save(wl);//添加数据到witdrawal表中  提现记录
        if (bd.getuID() != null && bd.getUname() != null && bd.getZname() != null && bd.getSfz() != null && bd.getKhh() != null && bd.getCardid() != null && bd.getTjtime() != null && bd.getStatu() != null) {
            bbol = bankcardService.save(bd);
        }

        if ((num > 0 && pbol == true && wbol == true) || (num > 0 && pbol == true && wbol == true && bbol == true)) {
            map.put("result", true);
        } else {
            map.put("result", false);
        }
        return map;
     /*   System.out.println("uid:\t"+pe.getuID());
        System.out.println("uname:\t"+pe.getUname());
        System.out.println("zname:\t"+pe.getZname());
        System.out.println("sxmoney:\t"+pe.getSxmoney());
        System.out.println("what:\t"+pe.getWhat());
        System.out.println("sxtime:\t"+pe.getSxtime());
        System.out.println("1uid：" + wl.getuID());
        System.out.println("2uname: " + wl.getUname());
        System.out.println("3zname: " + wl.getZname());
        System.out.println("4txnum: " + wl.getTxnum());
        System.out.println("5txbank: " + wl.getTxbank());
        System.out.println("6txmoney: " + wl.getTxmoney());
        System.out.println("7txtime: " + wl.getTxtime());
        System.out.println("8statu: " + wl.getStatu());
        System.out.println("9sxf: " + wl.getSxf());
        System.out.println("10dzmoney: " + wl.getDzmoney());*/


    }


}

