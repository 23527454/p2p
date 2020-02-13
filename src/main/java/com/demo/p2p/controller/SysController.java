package com.demo.p2p.controller;

import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.demo.p2p.service.CertificationService;
import com.demo.p2p.service.InvestinfoService;
import com.demo.p2p.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

@Controller
@RequestMapping(value = "/sys")
public class SysController {
    @Resource
    private CertificationService certificationService;
    @Resource
    private UsersService usersService;
    @Resource
    private InvestinfoService investinfoService;

    /**
     * 首页
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request){
        System.out.println("sumCertification==================");
        Integer certification = certificationService.certification();
        Integer usersnamecount = usersService.usersNameCount();
        Integer sumInmoney = investinfoService.sumInmoney();
        Integer sumprofitmoney = investinfoService.sumProfitmoney();
        request.setAttribute("sumcertification",certification);
        request.setAttribute("sumuserscount",usersnamecount);
        request.setAttribute("sumInmoney",sumInmoney);
        request.setAttribute("sumprofitmoney",sumprofitmoney);
        return "index";
    }

    /**
     * 注册
     * @return
     */
    @RequestMapping(value = "/register")
    public String zhuce(){
        return "register";
    }

    /**
     * 注册验证
     * 短信验证码
     * @param phone
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/register_ph")
    public String zhuce1(String phone, Model model){
        //发送短信验证码
        HashMap<String, Object> result = null;

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
            }
        }else{
            //异常返回输出错误码和错误信息
            System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
        }
        //将设置的验证码发送给前台页面
        model.addAttribute("yzm",yzm);
        return yzm;
    }

    /**
     * 注册成功
     * @return
     */
    @RequestMapping(value = "/register1")
    public String register1(){
        return "register1";
    }

    /**
     * 忘记密码——1
     * @return
     */
    @RequestMapping(value = "/resetPwd1")
    public String resetPwd1(){
        return "/resetpwd1";
    }

    /**
     * 忘记密码——2
     * 短信验证码
     * @param uid
     * @param phone
     * @param model
     * @return
     */
    @RequestMapping(value = "/resetPwd2")
    public String resetPwd2(Integer uid,String phone, Model model){
        //发送短信验证码
        HashMap<String, Object> result = null;

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
            }
        }else{
            //异常返回输出错误码和错误信息
            System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
        }
        //将设置的验证码发送给前台页面
        model.addAttribute("yzm",yzm);
        model.addAttribute("uid",uid);
        return "/resetpwd2";
    }

    /**
     * 忘记密码——3
     * @param uid
     * @param model
     * @return
     */
    @RequestMapping(value = "/resetPwd3")
    public String resetPwd3(Integer uid, Model model){
        model.addAttribute("uid",uid);
        return "/resetpwd3";
    }

    /**
     * 登录
     * @return
     */
    @RequestMapping(value = "/login")
    public String denglu(){
        return "login";
    }

    /**
     * 我要投资
     * @return
     */
    @RequestMapping(value = "/touzi")
    public String touzi(){
        return "list";
    }

    /**
     * 我要贷款
     * @return
     */
    @RequestMapping(value = "/daikuang")
    public String daikuang(){
        return "borrowadd";
    }

    /**
     * 安全保障/帮助中心
     * @return
     */
    @RequestMapping(value = "/help")
    public String anquan(){
        return "help";
    }

    /**
     * 项目详情
     * @return
     */
    @RequestMapping(value = "/infor")
    public String infor() {
        return "infor";
    }

    /**
     * 个人中心——账户总览
     * @return
     */
    @RequestMapping(value = "/grzx")
    public String zhanghu(){
        return "personalpage";
    }

    /**
     * 个人中心——账户设置
     * @return
     */
    @RequestMapping(value = "/grzx_zhsz")
    public String grzx_zhsz(){
        return "account";
    }

    /**
     * 联系我们
     * @return
     */
    @RequestMapping(value = "/lxwm")
    public String lxwm(){
        return "contact";
    }

    /**
     * 合作伙伴
     * @return
     */
    @RequestMapping(value = "/hzhb")
    public String hzhb(){
        return "informhzhb";
    }

    /**
     * 网站公告
     * @return
     */
    @RequestMapping(value = "/wzgg")
    public String wzgg(){
        return "inform";
    }

    /**
     * 公告详情
     * @return
     */
    @RequestMapping(value = "/xiangqing")
    public String xiangqing(){
        return "informsel";
    }

    /**
     * 管理团队
     * @return
     */
    @RequestMapping(value = "/gltd")
    public String gltd(){
        return "informgltd";
    }

    /**
     * 团队风采
     * @return
     */
    @RequestMapping(value = "/tdfc")
    public String tdfc(){
        return "informtdfc";
    }

    /**
     * 公司简介
     * @return
     */
    @RequestMapping(value = "/gsjj")
    public String gsjj(){
        return "introduce";
    }

    /**
     * 个人中心——投资记录
     * @return
     */
    @RequestMapping(value = "/grzx_tzjl")
    public String grzx_tzjl(){
        return "accounts";
    }

    /**
     * 个人中心——系统消息
     * @return
     */
    @RequestMapping(value = "/grzx_xtxx")
    public String grzx_xtxx(){
        return "messages";
    }

    /**
     * 个人中心——资金记录
     * @return
     */
    @RequestMapping(value = "/grzx_zjjl")
    public String grzx_zjjl(){
        return "moneyrecord";
    }

    /**
     * 个人中心——充值
     * @return
     */
    @RequestMapping(value = "/grzx_cz")
    public String grzx_cz(){
        return "pay";
    }

    /**
     * 法律政策
     * @return
     */
    @RequestMapping(value = "/flzc")
    public String flzc(){
        return "policy";
    }

    /**
     * 资费说明
     * @return
     */
    @RequestMapping(value = "/zfsm")
    public String zfsm(){
        return "postage";
    }

    /**
     * 招贤纳士
     * @return
     */
    @RequestMapping(value = "/zxns")
    public String zxns(){
        return "recruit";
    }

    /**
     * 媒体报道
     * @return
     */
    @RequestMapping(value = "/mtbd")
    public String mtbd(){
        return "reportlist";
    }

    /**
     * 媒体报道详情
     * @return
     */
    @RequestMapping(value = "/mtbdxq")
    public String mtbdxq(){
        return "reportsel";
    }

    /**
     * 法律声明
     * @return
     */
    @RequestMapping(value = "/flsm")
    public String flsm(){
        return "statement";
    }

    /**
     * 开通第三方
     * @return
     */
    @RequestMapping(value = "/grzx_ktdsf")
    public String grzx_ktdsf(){
        return "thirdparty";
    }

    /**
     * 提现
     * @return
     */
    @RequestMapping(value = "/grzx_tx")
    public String grzx_tx(){
        return "Withdraw";
    }

    /**
     * 未开通第三方账户时充值
     * @return
     */
    @RequestMapping(value = "/grzx_cz1")
    public String grzx_cz1(){
        return "Payno";
    }

    /**
     * 未开通第三方账户时提现
     * @return
     */
    @RequestMapping(value = "/grzx_tx1")
    public String grzx_tx1(){
        return "Withdrawno";
    }

    /**
     * 个人中心——回款计划
     * @return
     */
    @RequestMapping(value = "/grzx_hkjh")
    public String grzx_hkjh(){
        return "个人中心-回款计划";
    }

    /**
     * 个人中心——兑换历史
     * @return
     */
    @RequestMapping(value = "/grzx_dhls")
    public String grzx_dhls(){
        return "个人中心-兑换历史";
    }

    /**
     * 个人中心——我的红包
     * @return
     */
    @RequestMapping(value = "/grzx_wdhb")
    public String grzx_wdhb(){
        return "个人中心-我的红包";
    }
}
