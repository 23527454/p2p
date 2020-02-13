package com.demo.p2p.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.demo.p2p.entity.Log;
import com.demo.p2p.entity.Users;
import com.demo.p2p.service.LogService;
import com.demo.p2p.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

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

    @RequestMapping(value = "/do_register")
    public String paaaaa(String unickname,String upassword,String uphonenumber,String xm,String sfz,String yx,String tjr,String tjrxm){
        Users users = new Users();
        Date date = new Date();
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();

        users.setUnickname(unickname);
        users.setUpassword(upassword);
        users.setUphonenumber(uphonenumber);
        users.setUname(xm);
        users.setUcardid(sfz);
        users.setUmailbox(yx);
        users.setUreferrer(tjr);
        users.setUreferrername(tjrxm);
        users.setUregisterdate(localDateTime);
        usersService.saveUser(users);
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
            out.print("<script>alert('修改成功！');window.location.href='/sys/grzx_zhsz';</script>");
        }else{
            out.print("<script>alert('修改失败！');window.location.href='/sys/grzx_zhsz';</script>");
        }
        out.flush();
        out.close();
    }

    @RequestMapping(value = "/sendMessages")
    @ResponseBody
    public Object sendMessages(String phone){
        //发送短信验证码
        HashMap<String, Object> result = null;
        HashMap<String,Object> map=new HashMap<>();

        //初始化SDK
        CCPRestSmsSDK restAPI = new CCPRestSmsSDK();

        //******************************注释*********************************************
        //*初始化服务器地址和端口                                                       *
        //*沙盒环境（用于应用开发调试）：restAPI.init("sandboxapp.cloopen.com", "8883");*
        //*生产环境（用户应用上线使用）：restAPI.init("app.cloopen.com", "8883");       *
        //*******************************************************************************
        restAPI.init("app.cloopen.com", "8883");

        //******************************注释*********************************************
        //*初始化主帐号和主帐号令牌,对应官网开发者主账号下的ACCOUNT SID和AUTH TOKEN     *
        //*ACOUNT SID和AUTH TOKEN在登陆官网后，在“应用-管理控制台”中查看开发者主账号获取*
        //*参数顺序：第一个参数是ACOUNT SID，第二个参数是AUTH TOKEN。                   *
        //*******************************************************************************
        restAPI.setAccount("8aaf07086c6b60c5016c6c1af0c40075", "29d09f2154f34c979d05c4b8ddaa203b");


        //******************************注释*********************************************
        //*初始化应用ID                                                                 *
        //*测试开发可使用“测试Demo”的APP ID，正式上线需要使用自己创建的应用的App ID     *
        //*应用ID的获取：登陆官网，在“应用-应用列表”，点击应用名称，看应用详情获取APP ID*
        //*******************************************************************************
        restAPI.setAppId("8aaf07086c6b60c5016c6c1af120007b");


        //******************************注释****************************************************************
        //*调用发送模板短信的接口发送短信                                                                  *
        //*参数顺序说明：                                                                                  *
        //*第一个参数:是要发送的手机号码，可以用逗号分隔，一次最多支持100个手机号                          *
        //*第二个参数:是模板ID，在平台上创建的短信模板的ID值；测试的时候可以使用系统的默认模板，id为1。    *
        //*系统默认模板的内容为“【云通讯】您使用的是云通讯短信模板，您的验证码是{1}，请于{2}分钟内正确输入”*
        //*第三个参数是要替换的内容数组。																														       *
        //**************************************************************************************************

        //**************************************举例说明***********************************************************************
        //*假设您用测试Demo的APP ID，则需使用默认模板ID 1，发送手机号是13800000000，传入参数为6532和5，则调用方式为           *
        //*result = restAPI.sendTemplateSMS("13800000000","1" ,new String[]{"6532","5"});																		  *
        //*则13800000000手机号收到的短信内容是：【云通讯】您使用的是云通讯短信模板，您的验证码是6532，请于5分钟内正确输入     *
        //*********************************************************************************************************************
        String tel=phone;
        Random rd=new Random();
        int sjs=(int)rd.nextInt(9999);
        String yzm=String.valueOf(sjs);
        String time="5";

        result = restAPI.sendTemplateSMS(tel,"1" ,new String[]{yzm,time});

        System.out.println("SDKTestGetSubAccounts result=" + result);
        if("000000".equals(result.get("statusCode"))){
            Cookie cookie1 = new Cookie("yzm",yzm);
            cookie1.setMaxAge(60*Integer.parseInt(time));
            //正常返回输出data包体信息（map）
            HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
            Set<String> keySet = data.keySet();
            for(String key:keySet){
                Object object = data.get(key);
                System.out.println(key +" = "+object);
                map.put("result",true);
                map.put("yzm",yzm);
            }
        }else{
            //异常返回输出错误码和错误信息
            System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
            map.put("result",false);
            map.put("messages",result.get("statusMsg"));
        }
        return map;
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
     * @param request
     * @return
     */
    @PostMapping(value = "/login")
    @ResponseBody
    public Object login(String username, String password, HttpServletRequest request){
        QueryWrapper<Users> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("unickname",username).eq("upassword",password);
        Users users=usersService.login(queryWrapper);
        Map<String,Object> map=new HashMap<String,Object>();
        if (users!=null){
            //添加登录日志信息
            Log log=new Log(users.getUnickname(),"进入系统","进入系统",new Date());
            logService.addLog(log);
            users.setUfldate(new Date());
            usersService.resetUfldate(users);
            HttpSession session=request.getSession();
            session.setAttribute("loginUser",users);
            map.put("status",true);
            map.put("message","登录成功！");
        }else{
            map.put("status",false);
            map.put("message","登录失败，请检查用户名或密码是否正确！");
        }
        return map;
    }
}

