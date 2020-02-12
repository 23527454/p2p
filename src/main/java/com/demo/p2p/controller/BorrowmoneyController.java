package com.demo.p2p.controller;


import com.demo.p2p.entity.Borrowmoney;
import com.demo.p2p.entity.Users;
import com.demo.p2p.service.BorrowmoneyService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

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
    public Object shang(Borrowmoney borrowmoney, HttpServletRequest request ) {
        Map<String,Object> map=new HashMap<String,Object>();
        System.out.println("shang----------------------");
        HttpSession session = request.getSession();
        int num = 0;
        Users users = null;

        users  = (Users) session.getAttribute("loginUser");
        if(users !=  null ){
            borrowmoney.setBusername(users.getUid().toString());
            System.out.println("uid"+users.getUid());
            num = borrowmoneyService.addMoney(borrowmoney);
            map.put("jie",true);
            System.out.println(num);
        }else {
            map.put("jie",false);
            System.out.println(1111);
        }
        return map;
    }
}

