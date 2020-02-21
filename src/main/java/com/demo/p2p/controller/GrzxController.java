package com.demo.p2p.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.p2p.entity.Certification;
import com.demo.p2p.entity.Investinfo;
import com.demo.p2p.entity.Users;
import com.demo.p2p.service.CertificationService;
import com.demo.p2p.service.InvestinfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/grzx")
public class GrzxController {

    @Resource
    private CertificationService certificationService;

    @Resource
    private InvestinfoService investinfoService;

    /**
     * 个人中心——账户总览/首页
     *
     * @return
     */
    @RequestMapping(value = "/grzx")
    public void zhanghu(HttpServletResponse response, HttpSession session) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Users user = (Users) session.getAttribute("loginUser");
        if (user == null) {
            out.print("<script>alert('请先登录！');window.location.href='/sys/login';</script>");
        } else {
            out.print("<script>window.location.href='/grzx/grzx_zhzl';</script>");
        }
        out.flush();
        out.close();
    }

    /**
     * 进入账户总览
     *
     * @return
     */
    @RequestMapping(value = "/grzx_zhzl")
    public String grzx_zhzl(HttpServletRequest request, HttpSession session,Model model) {
        QueryWrapper<Certification> queryWrapper= new QueryWrapper<Certification>();
        Users user = (Users) session.getAttribute("loginUser");
        queryWrapper.eq("cserial",user.getUid());
        List<Certification> list1 = certificationService.getcserial(queryWrapper);
        List<Investinfo> list = investinfoService.getFive(user.getUid());
        System.out.println(list.size() + "集合数据一共就有这么多");
        request.setAttribute("infolist", list);
        model.addAttribute("list",list1);
        return "personalpage";
    }

    /**
     * 个人中心——账户设置
     *
     * @return
     */
    @RequestMapping(value = "/grzx_zhsz")
    public String grzx_zhsz(Model model) {
        Map<String, Object> map = new HashMap<>();
        map.put("aiid", "1");
        map.put("ainame", "身份认证");
        model.addAttribute("list", map);
        return "account";
    }

    /**
     * 个人中心——投资记录
     *
     * @return
     */
    @RequestMapping(value = "/grzx_tzjl")
    public String grzx_tzjl() {
        return "redirect:/investinfo/toInvestcordPage";
    }

    /**
     * 个人中心——资金记录
     *
     * @return
     */
    @RequestMapping(value = "/grzx_zjjl")
    public String grzx_zjjl() {
        return "moneyrecord";
    }

    /**
     * 个人中心——充值
     *
     * @return
     */
    @RequestMapping(value = "/grzx_cz")
    public String grzx_cz() {
        return "pay";
    }

    /**
     * 个人中心——开通第三方
     *
     * @return
     */
    @RequestMapping(value = "/grzx_ktdsf")
    public String grzx_ktdsf() {
        return "thirdparty";
    }

    /**
     * 个人中心——提现
     *
     * @return
     */
    @RequestMapping(value = "/grzx_tx")
    public String grzx_tx() {
        return "Withdraw";
    }

    /**
     * 个人中心——未开通第三方账户时充值
     *
     * @return
     */
    @RequestMapping(value = "/grzx_cz1")
    public String grzx_cz1() {
        return "Payno";
    }

    /**
     * 个人中心——未开通第三方账户时提现
     *
     * @return
     */
    @RequestMapping(value = "/grzx_tx1")
    public String grzx_tx1() {
        return "Withdrawno";
    }

    /**
     * 个人中心——回款计划
     *
     * @return
     */
    @RequestMapping(value = "/grzx_hkjh")
    public String grzx_hkjh() {
        return "个人中心-回款计划";
    }

    /**
     * 个人中心——兑换历史
     *
     * @return
     */
    @RequestMapping(value = "/grzx_dhls")
    public String grzx_dhls() {
        return "个人中心-兑换历史";
    }

    /**
     * 个人中心——我的红包
     *
     * @return
     */
    @RequestMapping(value = "/grzx_wdhb")
    public String grzx_wdhb() {
        return "个人中心-我的红包";
    }
}
