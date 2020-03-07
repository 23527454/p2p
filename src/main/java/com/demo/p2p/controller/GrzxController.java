package com.demo.p2p.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.p2p.entity.*;
import com.demo.p2p.mapper.TradeMapper;
import com.demo.p2p.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/grzx")
public class GrzxController {
    @Resource
    private TradeService tradeService;

    @Resource
    private TradeMapper tradeMapper;

    @Resource
    private CertificationService certificationService;

    @Resource
    private InvestinfoService investinfoService;

    @Resource
    private BankcardService bankcardService;

    @Resource
    private PacketredService packetredService;
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
        Users user = (Users) session.getAttribute("loginUser");
        if (user == null) {
            return "redirect:/sys/login";
        } else {
            QueryWrapper<Certification> queryWrapper= new QueryWrapper<Certification>();
            queryWrapper.eq("cserial",user.getUid());
            List<Certification> list1 = certificationService.getcserial(queryWrapper);
            List<Investinfo> list = investinfoService.getFive(user.getUid());
            System.out.println(list.size() + "集合数据一共就有这么多");
            request.setAttribute("infolist", list);
            model.addAttribute("list",list1);
            return "personalpage";
        }
    }

    /**
     * 个人中心——账户设置
     *
     * @return
     */
    @RequestMapping(value = "/grzx_zhsz")
    public String grzx_zhsz(Model model,HttpSession session) {
        Users user = (Users) session.getAttribute("loginUser");
        if (user == null) {
            return "redirect:/sys/login";
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("aiid", "1");
            map.put("ainame", "身份认证");
            model.addAttribute("list", map);
            return "account";
        }
    }

    /**
     * 个人中心——投资记录
     *
     * @return
     */
    @RequestMapping(value = "/grzx_tzjl")
    public String grzx_tzjl(HttpSession session) {
        Users user = (Users) session.getAttribute("loginUser");
        if (user == null) {
            return "redirect:/sys/login";
        } else {
            return "redirect:/investinfo/toInvestcordPage";
        }
    }

    /**
     * 个人中心——资金记录
     *
     * @return
     */
    @RequestMapping(value = "/grzx_zjjl")
    public String grzx_zjjl(Integer current,Model model,HttpSession session,HttpServletRequest request) throws ParseException {
        Users user = (Users) session.getAttribute("loginUser");
        Trade trade = tradeMapper.selectById(user.getUid());
        System.out.println("grzx_zjjl=============================");
        if (user == null) {
            return "redirect:/sys/login";
        } else {
            if(current==null) {
                current=1;
            }
                String what = request.getParameter("what");
                String sDate = request.getParameter("sDate");
                String eDate = request.getParameter("eDate");
                QueryWrapper<Trade> queryWrapper = new QueryWrapper<>();
                if (what != null && what != "" && !what.equals("全部")) {
                    queryWrapper.like("what", what);
                }
                if (sDate != null && sDate != "") {
                    queryWrapper.ge("jytime", sDate);
                }
                if (eDate != null && eDate != "") {
                    queryWrapper.le("jytime", eDate);
                }
                Page<Trade> page = new Page<>(current, 5);
                IPage<Trade> iPage = tradeService.TradeList(page, queryWrapper);
                List<Trade> users = iPage.getRecords();
                int stas = 1;
                if (users != null && users.size() > 0) {
                    stas = 0;
                }
                if (current > iPage.getPages() ) {
                    page = new Page<>(1, 5);
                    iPage = tradeService.page(page, queryWrapper);
                    users = iPage.getRecords();
                }
                request.setAttribute("userlist", users);
                model.addAttribute("what", what);
                model.addAttribute("page", page);
                model.addAttribute("stas", stas);
                return "moneyrecord";
        }
    }

    /**
     * 个人中心——充值
     *
     * @return
     */
    @RequestMapping(value = "/grzx_cz")
    public String grzx_cz(HttpSession session) {
        Users user = (Users) session.getAttribute("loginUser");
        if (user == null) {
            return "redirect:/sys/login";
        } else {
            return "pay";
        }
    }

    /**
     * 个人中心——开通第三方
     *
     * @return
     */
    @RequestMapping(value = "/grzx_ktdsf")
    public String grzx_ktdsf(HttpSession session) {
        Users user = (Users) session.getAttribute("loginUser");
        if (user == null) {
            return "redirect:/sys/login";
        } else {
            return "thirdparty";
        }
    }

    /**
     * 个人中心——提现
     *
     * @return
     */
    @RequestMapping(value = "/grzx_tx")
    public String grzx_tx(HttpSession session) {
        Users user = (Users) session.getAttribute("loginUser");
        if (user == null) {
            return "redirect:/sys/login";
        } else {
            return "Withdraw";
        }
    }

    /**
     * 个人中心——未开通第三方账户时充值
     *
     * @return
     */
    /**
     * 个人中心——充值
     *
     * @return
     */
    @RequestMapping(value = "/grzx_cz1")
    public String grzx_cz (HttpSession session, HttpServletRequest request){
        Users users = (Users) session.getAttribute("loginUser");
        if (users.getUcertnumber() != null) {
            System.out.println(1);
            List<Bankcard> bankcards = bankcardService.bankcardList();
            request.setAttribute("bankcards", bankcards);
            return "pay";
        }
        System.out.println(2);
        return "Payno";
    }

    /**
     * 个人中心——未开通第三方账户时提现
     *
     * @return
     */
    @RequestMapping(value = "/grzx_tx1")
    public String grzx_tx1(HttpSession session,HttpServletRequest request) {
        Users user = (Users)session.getAttribute("loginUser");
        if (user == null) {
            return "redirect:/sys/login";
        } else {
            if(user.getUcertnumber() != null){
                System.out.println(1);
                List<Bankcard> list = bankcardService.getbank(user.getUid());
                for (Bankcard ls: list
                ) {
                    String strhours = String.valueOf( ls.getCardid());
                    String strh = strhours.substring(strhours.length() -2,strhours.length());   //截取
                    String strm = strhours.substring(0,2);   //截掉
                    String cardid = strm + "***" +strh;
                    ls.setCardid(cardid);
                }
                Certification certification = certificationService.getcserial(user.getUnickname());
                System.out.println(certification.getCtotalmoney());
                request.setAttribute("certification",certification);
                request.setAttribute("bankls",list);
                return "Withdraw";
            }
            return "Withdrawno";
        }
    }

    /**
     * 个人中心——回款计划
     *
     * @return
     */
    @RequestMapping(value = "/grzx_hkjh")
    public String grzx_hkjh(HttpSession session) {
        Users user = (Users) session.getAttribute("loginUser");
        if (user == null) {
            return "redirect:/sys/login";
        } else {
            return "个人中心-回款计划";
        }
    }

    /**
     * 个人中心——兑换历史
     *
     * @return
     */
    @RequestMapping(value = "/grzx_dhls")
    public String grzx_dhls(HttpSession session,HttpServletRequest request) {
        System.out.println("兑换历史+++++++++++++");
        List<Packetred> packetreds = packetredService.packetredList();
        request.setAttribute("packetreds", packetreds);
        return "个人中心-兑换历史";
    }

    /**
     * 个人中心——我的红包
     *
     * @return
     */
    @RequestMapping(value = "/grzx_wdhb")
    public String grzx_wdhb(HttpSession session,HttpServletRequest request) {
        List<Packetred> packetredss = packetredService.packetredList();
        request.setAttribute("packetredss", packetredss);
        return "个人中心-我的红包";
    }

    /**
     *  添加银行卡
     *
     * @return
     */
    @RequestMapping(value = "/pay1")
    public String pay1(HttpSession session,HttpServletRequest request) {
        return "pay1";
    }
}
