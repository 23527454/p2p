package com.demo.p2p.ht.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.p2p.ht.entity.*;
import com.demo.p2p.ht.service.*;
import com.demo.p2p.util.RandomCharacterAndNumber;
import com.demo.p2p.util.SendMessage;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private SendMessage sendMessage=new SendMessage();

    //发邮件
    @Autowired
    JavaMailSenderImpl mailSender;

    @RequestMapping(value = "/rePwd")
    @ResponseBody
    public String rePwd(String username){
        QueryWrapper<Employee> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("ename",username);
        Employee employee=employeeService.getOne(queryWrapper);
        Map<String,Object> map=new HashMap<>();
        if (employee!=null){
            RandomCharacterAndNumber randomCharacterAndNumber=new RandomCharacterAndNumber();
            String newPwd=randomCharacterAndNumber.getRandomCharacterAndNumber(3);
            employee.setEpassword(newPwd);
            employeeService.updateById(employee);
            sendMessage.sendPhoneMessage(employee.getEphone(),newPwd);
            try{
                //创建一个复杂的消息邮件
                MimeMessage mimeMessage = mailSender.createMimeMessage();
                //设置邮件的内容
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                helper.setSubject("您的亿人宝账号密码已重置");
                helper.setText("尊敬的"+employee.getEname()+"，您好！<br><br>您的亿人宝账号密码已重置为："+newPwd+"<br><br>该邮件为系统自动发出，请勿回复!<br>"+new Date(), true);
                //收件人
                helper.setTo(employee.getEmail());
                //发件人
                helper.setFrom("23527454@qq.com");
                //发送邮件
                mailSender.send(mimeMessage);
            }catch (Exception e){
                e.printStackTrace();
            }
            return "密码已重置，新的密码已通过短信和邮件的方式发送到您的号码！";
        }else {
            return "不存在此用户！";
        }
    }

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
    public Integer login(String uid, String pwd) {
        UsernamePasswordToken token = new UsernamePasswordToken(uid, pwd);
        try {
            SecurityUtils.getSubject().login(token);//调用Shiro认证
            Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
            if(employee!=null){
                return 1;
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @RequestMapping(value = "/toLogin")
    public String toLogin() {
        return "view/bk_login";
    }
}
