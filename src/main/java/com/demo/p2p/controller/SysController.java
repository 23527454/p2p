package com.demo.p2p.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.p2p.entity.*;
import com.demo.p2p.mapper.CertificationMapper;
import com.demo.p2p.mapper.PacketredMapper;
import com.demo.p2p.service.*;
import com.demo.p2p.util.SendMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping(value = "/sys")
public class SysController {
    @Resource
    private CertificationService certificationService;
    @Resource
    private UsersService usersService;
    @Resource
    private InvestinfoService investinfoService;

    @Resource
    private ProductService productService;

    @Resource
    private BiaoService biaoService;

    @Resource
    private PacketredService packetredService;

    @Resource
    private PacketredMapper packetredMapper;

    @Resource
    private CertificationMapper certificationMapper;

    @Resource
    private BankcardService bankcardService;

    private SendMessage sendMessage=new SendMessage();

    @RequestMapping(value = "/403")
    public String wqx(){
        return "page/403";
    }

    @RequestMapping(value = "/syjsq")
    public String syjsq(){
        return "demo";
    }

    /**
     * 首页
     * @return
     */
    @Resource
    private NoticeService noticeService;
    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request,Model mode){
        QueryWrapper<Notice> queryWrapper=new QueryWrapper<>();
        QueryWrapper<Notice> queryWrapper1=new QueryWrapper<>();
        QueryWrapper<Notice> queryWrapper2=new QueryWrapper<>();
        QueryWrapper<Notice> queryWrapper3=new QueryWrapper<>();
        QueryWrapper<Notice> queryWrapper4=new QueryWrapper<>();
        queryWrapper.eq("noticetype",1);
        queryWrapper1.eq("noticetype",2);
        queryWrapper2.eq("noticetype",1);
        queryWrapper3.eq("noticeid",43);
        queryWrapper4.eq("noticetype",6);
        queryWrapper4.orderByDesc("noticelasttime");
        List<Notice> list = noticeService.select1(queryWrapper);
        List<Notice> list1 = noticeService.select1(queryWrapper1);
        List<Notice> list2 = noticeService.select1(queryWrapper2);
        List<Notice> list3 = noticeService.select1(queryWrapper3);
        List<Notice> list4=noticeService.list(queryWrapper4);
        Integer certification = certificationService.certification();
        Integer usersnamecount = usersService.usersNameCount();
        Integer sumInmoney = investinfoService.sumInmoney();
        Integer sumprofitmoney = investinfoService.sumProfitmoney();
        mode.addAttribute("list",list);
        mode.addAttribute("list1",list1);
        mode.addAttribute("list2",list2);
        mode.addAttribute("list3",list3);
        mode.addAttribute("sy",list4);

        List<Product> products = productService.productList();
        List<Product> products2 = productService.productList2();
        List<Biao> biaos = biaoService.biaoList();
        mode.addAttribute("products", products);
        mode.addAttribute("products2", products2);
        mode.addAttribute("biaos", biaos);

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
        Random rd=new Random();
        int sjs=(int)rd.nextInt(9999);
        String yzm=String.valueOf(sjs);
        sendMessage.sendPhoneMessage(phone,yzm);
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
        Random rd=new Random();
        int sjs=(int)rd.nextInt(9999);
        String yzm=String.valueOf(sjs);
        sendMessage.sendPhoneMessage(phone,yzm);
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
        return "redirect:/invest/investSel";
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
     * 项目详情2
     * @return
     */
    @RequestMapping(value = "/inforadd")
    public String inforadd() {
        return "inforadd";
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
     * 公司简介
     * @return
     */
    @RequestMapping(value = "/gsjj")
    public String gsjj(){
        return "introduce";
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
     * 法律声明
     * @return
     */
    @RequestMapping(value = "/flsm")
    public String flsm(){
        return "statement";
    }

    /**
     * 个人中心——我的红包
     *
     * @return
     */
    @RequestMapping(value = "/grzx_wdhb")
    public String grzx_wdhb (HttpServletRequest request, HttpSession session){
        List<Packetred> packetredss = packetredService.packetredList();
        request.setAttribute("packetredss", packetredss);
        return "个人中心-我的红包";
    }
    /**
     * 个人中心——我的红包1
     *
     * @return
     */
    @RequestMapping(value = "/grzx_wdhb1")
    public String grzx_wdhb1 (HttpServletRequest request, HttpSession session){
        List<Packetred> packetredss = packetredService.packetredList();
        request.setAttribute("packetredss", packetredss);
        return "个人中心-我的红包1";
    }

    /**
     * 个人中心——我的红包2
     *
     * @return
     */
    @RequestMapping(value = "/grzx_wdhb2")
    public String grzx_wdhb2(HttpServletRequest request, HttpSession session){
        List<Packetred> packetredss = packetredService.packetredList();
        request.setAttribute("packetredss", packetredss);
        return "个人中心-我的红包2";
    }

    /**
     * 刷新兑换红包
     *
     * @return
     */
    @RequestMapping(value = "/sxduhb")
    public String sxduhb(HttpServletRequest request, HttpSession session){
        return "redirect:/grzx/grzx_zhzl";
    }

    /**
     * 个人中心——红包兑换
     *
     * @return
     */
    @RequestMapping(value = "/grzx_hbdh")
    public void grzx_hbdh (String code, String dhma, HttpServletRequest request, HttpSession session, HttpServletResponse response, String cbalance) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Packetred pa = new Packetred();
        pa.setDhma(code);
        pa.setPname("已使用");
        pa.setPtype(2);
        Users users = (Users) session.getAttribute("loginUser");
        Packetred packetred = packetredMapper.selectById(users.getUid());
        Certification certification = new Certification();
        Certification certi = certificationMapper.selectById(users.getUid());
        System.out.println(certi.getId() + "pa.getId()");
        System.out.println(certi.getId() + "certification.getId()");
        certification.setId(certi.getId());
        String cbalance1 = certi.getCtotalmoney();
        Float cbalance2 = Float.valueOf(cbalance1) + 50;
        Integer cbalance3=null;
        if(cbalance3!=null){
            cbalance3 = Integer.parseInt(String.valueOf(cbalance2));
        }
        certification.setCtotalmoney(String.valueOf(cbalance2));
        System.out.println(pa.getPname()+"!pa.getPname()");
        System.out.println(packetred.getPname()+"packetred.getPname()");

        if(!packetred.getPname().equals("已使用") && !packetred.getPname().equals("已过期")) {
            int certificationupup = certificationService.certificationupup(certification);
            int packetredupdate = packetredService.packetredupdate(pa);
            if (packetredupdate > 0) {
                if (certificationupup > 0) {
                    out.print("<script>alert('兑换成功！');window.location.href='/sys/sxduhb';</script>");
                }
            } else {
                out.print("<script>alert('验证码错误,兑换失败！');window.location.href='/sys/grzx_wdhb';</script>");
            }
        }else {
            out.print("<script>alert('该验证码已经兑换过了！');window.location.href='/sys/sxduhb';</script>");
        }
        out.flush();
        out.close();
    }
}
