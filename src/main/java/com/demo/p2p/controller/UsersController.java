package com.demo.p2p.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.p2p.entity.*;
import com.demo.p2p.service.*;
import com.demo.p2p.util.SendMessage;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gzd
 * @since 2020-02-09
 */
@Controller
@RequestMapping("/users")
public class UsersController {
    @Resource
    private UsersService usersService;
    @Resource
    private LogService logService;
    @Resource
    private ApproveitemService approveitemService;
    @Resource
    private CertifrecordService certifrecordService;
    @Resource
    private CertificationService certificationService;

    private SendMessage sendMessage=new SendMessage();

    //发邮件
    @Autowired
    JavaMailSenderImpl mailSender;

    @RequestMapping(value = "/modifyPayPwd")
    @ResponseBody
    public void modifyPayPwd(String paypassword,HttpServletResponse response,HttpSession session) throws IOException{
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        Users users=(Users)session.getAttribute("loginUser");
        users.setUpwdZd(paypassword);
        PrintWriter out=response.getWriter();
        boolean result=usersService.updateById(users);
        if (result){
            out.print("<script>alert('修改成功！');window.location.href='/grzx/grzx_zhsz';</script>");
        }else{
            out.print("<script>alert('修改失败！');window.location.href='/grzx/grzx_zhsz';</script>");
        }
        out.flush();
        out.close();
    }

    @RequestMapping(value = "/modifyPwd")
    @ResponseBody
    public void modifyPwd(String password,HttpServletResponse response,HttpSession session) throws IOException{
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        Users users=(Users)session.getAttribute("loginUser");
        users.setUpassword(password);
        PrintWriter out=response.getWriter();
        boolean result=usersService.updateById(users);
        if (result){
            out.print("<script>alert('修改成功！');window.location.href='/grzx/grzx_zhsz';</script>");
        }else{
            out.print("<script>alert('修改失败！');window.location.href='/grzx/grzx_zhsz';</script>");
        }
        out.flush();
        out.close();
    }

    @RequestMapping(value = "/sendEmail")
    @ResponseBody
    public Object sendEmail(String email,String name){
        System.out.println("sendEmail......");
        Map<String,Object> map=new HashMap<>();

        //验证码
        Random rd=new Random();
        int sjs=(int)rd.nextInt(9999);
        String yzm=String.valueOf(sjs);
        System.out.println("验证码："+yzm);
        try{
            //创建一个复杂的消息邮件
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            //设置邮件的内容
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setSubject("您正在修改亿人宝绑定邮箱");
            helper.setText("尊敬的"+name+"，您好！<br><br>这是您本次修改所需的验证码："+yzm+"<br><br>该邮件为系统自动发出，请勿回复!<br>"+new Date(), true);
            //收件人
            helper.setTo(email);
            //发件人
            helper.setFrom("23527454@qq.com");
            //发送邮件
            mailSender.send(mimeMessage);
            map.put("result",true);
            map.put("yzm",yzm);
        }catch (Exception e){
            map.put("result",false);
            map.put("messages",e.getMessage());
        }
        return map;
    }

    @RequestMapping(value = "/modifyEmail")
    @ResponseBody
    public void modifyEmail(String newEmail,HttpServletResponse response,HttpSession session) throws IOException{
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        Users users=(Users)session.getAttribute("loginUser");
        users.setUmailbox(newEmail);
        PrintWriter out=response.getWriter();
        boolean result=usersService.updateById(users);
        if (result){
            out.print("<script>alert('修改成功！');window.location.href='/grzx/grzx_zhsz';</script>");
        }else{
            out.print("<script>alert('修改失败！');window.location.href='/grzx/grzx_zhsz';</script>");
        }
        out.flush();
        out.close();
    }

    @RequestMapping(value = "/modifyIdentity")
    @ResponseBody
    public void modifyIdentity(Users users, Approveitem approveitem,HttpServletResponse response,HttpSession session) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();

        String name=users.getUname();
        String sfz=users.getUcardid();
        approveitem=approveitemService.getById(approveitem.getAiid());
        Certifrecord certifrecord=new Certifrecord(users.getUid(),users.getUnickname(),approveitem.getAiid(),approveitem.getAiname(),approveitem.getAitype(),approveitem.getAistate(),new Date());
        boolean result1=certifrecordService.save(certifrecord);
        users=usersService.getById(users.getUid());
        users.setUname(name);
        users.setUcardid(sfz);
        boolean result2=usersService.updateById(users);
        session.setAttribute("loginUser",users);
        if (result1==result2 && result1==true){
            out.print("<script>alert('认证成功！');window.location.href='/grzx/grzx_zhsz';</script>");
        }else{
            out.print("<script>alert('认证失败！');window.location.href='/grzx/grzx_zhsz';</script>");
        }
        out.flush();
        out.close();
    }

    @RequestMapping(value = "/do_register")
    public String paaaaa(String unickname,String upassword,String uphonenumber,String xm,String sfz,String yx,String tjr,String tjrxm){
        Users users = new Users();
        Certification certification = new Certification();
        Date date = new Date();
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        users.setUnickname(unickname);
//        users.setUpassword(upassword);
        users.setUpassword(DigestUtils.md5DigestAsHex(upassword.getBytes()));
        users.setUphonenumber(uphonenumber);
        users.setUname(xm);
        users.setUcardid(sfz);
        users.setUmailbox(yx);
        users.setUreferrer(tjr);
        users.setUreferrername(tjrxm);
        users.setUregisterdate(date);
        usersService.saveUser(users);
        certification.setCserial(usersService.getUserMaxId());
        certification.setCbalance("0.0");
        certification.setCdue(0.0);
        certification.setCfreeze(0.0);
        certification.setCpaid(0.0);
        certification.setCtotalmoney("0.0");
        certification.setCrealname(users.getUname());
        certification.setCusername(users.getUnickname());
        certificationService.saveCertification(certification);
        return "redirect:/sys/register1";
    }

    @RequestMapping(value = "/modifyPhone")
    @ResponseBody
    public void modifyPhone(Integer id, String newPhone, HttpServletResponse response,HttpSession session) throws Exception{
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        Users users=(Users)session.getAttribute("loginUser");

        PrintWriter out=response.getWriter();
        boolean result=usersService.resetPhone(id,newPhone);
        if (result){
            users.setUphonenumber(newPhone);
            out.print("<script>alert('修改成功！');window.location.href='/grzx/grzx_zhsz';</script>");
        }else{
            out.print("<script>alert('修改失败！');window.location.href='/grzx/grzx_zhsz';</script>");
        }
        out.flush();
        out.close();
    }

    @RequestMapping(value = "/sendMessages")
    @ResponseBody
    public Object sendMessages(String phone){
        Random rd=new Random();
        int sjs=(int)rd.nextInt(9999);
        String yzm=String.valueOf(sjs);
        return sendMessage.sendPhoneMessage(phone,yzm);
    }

    /**
     * 注销
     * @return
     */
    @RequestMapping(value = "/loginout")
    public String zhuxiao(HttpSession session){
        //添加登录日志信息
        Users users=(Users)session.getAttribute("loginUser");
        Log log=new Log(users.getUnickname(),"退出系统","退出系统",new Date());
        logService.addLog(log);
        session.removeAttribute("loginUser");
        return "redirect:/sys/index";
    }

    /**
     * 重置密码
     * @param uid
     * @param password
     * @return
     */
    @PostMapping(value = "/resetPwd")
    @ResponseBody
    public Object resetPwd(Integer uid,String password){
        Map<String,Object> map=new HashMap<>();
        boolean result=usersService.resetPwd(uid,password);
        map.put("result",result);
        return map;
    }

    /**
     * 忘记密码时，检查手机号码是否正确
     * @param phone
     * @return
     */
    @PostMapping(value = "/checkPhone")
    @ResponseBody
    public Object checkPhone(String phone){
        Map<String,Object> map=new HashMap<String,Object>();
        QueryWrapper<Users> queryWrapper=new QueryWrapper<>();
        if(phone!=null){
            queryWrapper.eq("uphonenumber",phone);
        }
        Users users=usersService.checkUsersByCondition(queryWrapper);
        if (users!=null){
            map.put("status",true);
            map.put("message","存在");
        }else{
            map.put("status",false);
            map.put("message","手机号不匹配！");
        }
        return map;
    }

    /**
     * 忘记密码时，检查用户名是否存在
     * @param username
     * @return
     */
    @PostMapping(value = "/checkUnickname")
    @ResponseBody
    public Object checkUnickname(String username){
        Map<String,Object> map=new HashMap<String,Object>();
        QueryWrapper<Users> queryWrapper=new QueryWrapper<>();
        if(username!=null){
            queryWrapper.eq("unickname",username);
        }
        Users users=usersService.checkUsersByCondition(queryWrapper);
        if (users!=null){
            map.put("status",true);
            map.put("users",users);
            map.put("message","存在");
        }else{
            map.put("status",false);
            map.put("message","不存在该用户！");
        }
        return map;
    }

    /**
     * 登录
     * @param username
     * @param password
     * @param session
     * @return
     */
    @PostMapping(value = "/login")
    @ResponseBody
    public Object login(String username, String password, HttpSession session){
        Map<String,Object> map=new HashMap<String,Object>();
        try {
            QueryWrapper<Users> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("unickname",username).eq("upassword",password);
            Users users=usersService.login(queryWrapper);
            if (users == null) {
                throw new UnknownAccountException("登录失败！");
            }
            session.setAttribute("loginUser",users);
            Log log=new Log(users.getUnickname(),"进入系统","进入系统",new Date());
            logService.addLog(log);
            users.setUfldate(new Date());
            usersService.resetUfldate(users);
            map.put("message", "登录成功！");
            map.put("status", true);
        } catch (UnknownAccountException uae) {
            map.put("message", "登录失败请检查用户名或密码是否正确！");
            map.put("status", false);
        } catch (DisabledAccountException de) {
            map.put("message", de.getMessage());
            map.put("status", false);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message", "系统错误，请联系管理员！");
            map.put("status", false);
        }
        return map;
    }

    @RequestMapping(value = "/do_insertucertnum")
    public String do_insertucertnum(String uname, String ucardid,String umailbox,String uphonenumber,String upwd_zd,String id,HttpSession session) throws IOException {
        Users users = (Users)session.getAttribute("loginUser");
        users.setUname(uname);
        users.setUcardid(ucardid);
        users.setUphonenumber(uphonenumber);
        users.setUpwdZd(upwd_zd);
        users.setUmailbox(umailbox);
        if (users.getUname()!=null&&users.getUcardid()!=null&&users.getUphonenumber()!=null&&users.getUpwdZd()!=null&&users.getUmailbox()!=null){
            Certification certification = new Certification();
            certification.setCdue(0.00);
            certification.setCpaid(0.00);
            certification.setCfreeze(0.00);
            certification.setCbalance("0.00");
            certification.setCtotalmoney("0.00");
            certification.setCserial(users.getUid());
            certification.setCrealname(users.getUname());
            certification.setCusername(users.getUnickname());
            certificationService.save(certification);
            users.setUcertnumber("647988");
        }
        usersService.insertucertnum(users);
        session.setAttribute("loginUser",users);
        return "redirect:/grzx/grzx_ktdsf";
    }
}

