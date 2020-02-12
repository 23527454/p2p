package com.demo.p2p.controller;


import com.demo.p2p.entity.Borrowmoney;
import com.demo.p2p.service.BorrowmoneyService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gzd
 * @since 2020-02-11
 */
@Controller
@RequestMapping("/brower")
public class BorrowmoneyController {

    @Resource
    private BorrowmoneyService borrowmoneyService;


    @RequestMapping("toaddborr.do")
    @ResponseBody
    public void shang(Borrowmoney borrowmoney){
        System.out.println("shang----------------------");
        int num = 0;
        num = borrowmoneyService.addMoney(borrowmoney);
        System.out.println(num);
    }
}

