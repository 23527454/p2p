package com.demo.p2p.ht.controller;

import com.demo.p2p.ht.entity.*;
import com.demo.p2p.ht.service.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/backend")
public class BackendController {
    @Resource
    private Bk_EmployeeService employeeService;

    @Resource
    private Bk_CertifrecordService bk_certifrecordService;

    @Resource
    private Bk_BorrowcordService bk_borrowcordService;

    @Resource
    private Bk_BorrowmoneyService bk_borrowmoneyService;

    @Resource
    private Bk_WithdrawalService bk_withdrawalService;

    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request) {
        List<Certifrecord> lssize = bk_certifrecordService.lssize();//新用户未处理待审核
        List<Borrowcord> lssum = bk_borrowcordService.lssum();//还款未处理的
        List<Borrowmoney> lscount = bk_borrowmoneyService.lscount();//借款未处理的
        List<Withdrawal> lsnum = bk_withdrawalService.lsnum();//提现未处理

        request.setAttribute("tos",lscount.size());
        request.setAttribute("tow",lsnum.size());
        request.setAttribute("tob",lssum.size());
        request.setAttribute("tou",lssize.size());

        return "view/bk_index";
    }

    @RequestMapping(value = "/loginout")
    public String loginout(HttpSession session) {
        session.removeAttribute("loginEmp");
        return "redirect:/backend/toLogin";
    }

    @RequestMapping(value = "/login")
    @ResponseBody
    public Object login(String uid, String pwd) {
        UsernamePasswordToken token = new UsernamePasswordToken(uid, pwd);
        try {
            SecurityUtils.getSubject().login(token);//调用Shiro认证
            Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
            return "1";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

    @RequestMapping(value = "/toLogin")
    public String toLogin() {
        return "view/bk_login";
    }
}
