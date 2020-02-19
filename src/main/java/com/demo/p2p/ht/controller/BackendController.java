package com.demo.p2p.ht.controller;

import com.demo.p2p.ht.entity.Employee;
import com.demo.p2p.ht.service.EmployeeService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/backend")
public class BackendController {
    @Resource
    private EmployeeService employeeService;

    @RequestMapping(value = "/index")
    public String index(){
        return "view/bk_index";
    }

    @RequestMapping(value = "/loginout")
    public String loginout(HttpSession session){
        session.removeAttribute("loginEmp");
        return "redirect:/backend/toLogin";
    }

    @RequestMapping(value = "/login")
    @ResponseBody
    public Object login(String uid, String pwd){
        UsernamePasswordToken token = new UsernamePasswordToken(uid,pwd);
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
    public String toLogin(){
        return "view/bk_login";
    }
}
