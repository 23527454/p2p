package com.demo.p2p.controller;

import com.demo.p2p.entity.*;
import com.demo.p2p.mapper.*;
import com.demo.p2p.service.ApproveitemService;
import com.demo.p2p.service.BankcardService;
import com.demo.p2p.service.CertificationService;
import com.demo.p2p.service.DopeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gzd
 * @since 2020-02-15
 */
@Controller
@RequestMapping("/dope")
public class DopeController {
    @Resource
    public DopeService dopeService;

    @Resource
    private UsersMapper usersMapper;


    @Resource
    private PoundageMapper poundageMapper;


    @Resource
    private CertificationMapper certificationMapper;

    @Resource
    private CertificationService certificationService;

    @Resource
    private DopeMapper dopeMapper;

    @Resource
    private BankcardMapper bankcardMapper;

    @Resource
    private ApproveitemService approveitemService;

    @Resource
    private BankcardService bankcardService;
    /**
     * 个人中心——系统消息
     * @return
     */
    @RequestMapping(value = "/grzx_xtxx")
    public String grzx_xtxx(Model model,@RequestParam(value="currpage",required=false)String conent,HttpSession session){
        int pagecount = 5;//每页显示行数
        int currpage = 1;//当前行数
        int totalPage = 0;//总页数
        int totalRow = 0;//总行数
        //获取总行数
        totalRow=dopeService.list().size();
        System.out.println(totalRow+"////////////////");
        //分页
        totalPage = (totalRow + pagecount - 1) / pagecount;
        if(conent!=null&&!"".equals(conent)){
            currpage=Integer.parseInt(conent);
        }
        if(currpage<1){
            currpage=1;
        }
        if(currpage>totalPage){
            currpage=totalPage;
        }
        Integer candp = (currpage - 1) * pagecount;
        Map<String, Object> map=new HashMap<>();
        map.put("pagecount", pagecount);
        map.put("currpage", candp);
        Users users=(Users)session.getAttribute("loginUser");
        map.put("uid", users.getUid());
        List<Dope> list = dopeService.findDope(map);
        model.addAttribute("list",list);
        model.addAttribute("pagecount",pagecount);
        model.addAttribute("currpage",currpage);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("totalRow",totalRow);
        return "messages";
    }
    @RequestMapping(value = "/grzx_sc")
    @ResponseBody
    public String grzx_sc(String delitems) {
        String[] item = delitems.split(",");
        List list = new ArrayList();
        for (int i = 0; i < item.length; i++) {
            list.add(item[i]);
        }
        for (int i = 0; i < list.size(); i++) {
            dopeService.batchDeletes(Integer.parseInt((String) list.get(i)));
        }
        return "success";
    }

    @RequestMapping(value = "/userpay")
    @ResponseBody
    public String userpay(Poundage po, HttpServletRequest request, HttpSession session){
        Users users = (Users) session.getAttribute("loginUser");
        System.out.println("userpay============================");
        String code="200";
        Date date = new Date();
        Map<String, Object> usermap = new HashMap<>();
        Map<String, Object> map = new HashMap<>();

        usermap.put("id",users.getUid());

        Users user = usersMapper.selectById(users.getUid());
        po.setUname(user.getUnickname());
        po.setZname(user.getUname());
        po.setWhat("充值");
        po.setSxtime(date);
        po.setBookaccount(user.getUid()+"");
        po.setPaytype("快捷支付");

        Certification certi = certificationMapper.selectById(users.getUid());
        certi.setId(certi.getId()) ;
        //可用余额
        String cba11 = certi.getCbalance();
        String cbal ="";
        cbal =String.valueOf(cba11);
        String xmoney = po.getSxmoney();
        Float fmoney = Float.valueOf(cbal)+Float.valueOf(xmoney);


        //总余额
        String moneyString = certi.getCtotalmoney();
        Float money = Float.valueOf(moneyString)+Float.valueOf(xmoney);
//        map.put("id", certi);
//        map.put("cbalance", fmoney.toString());
//        map.put("ctotalmoney", money.toString());
        Integer money2 =null;
        if(money2!=null){
            money2 = Integer.parseInt(String.valueOf(money));
        }
        certi.setCtotalmoney(String.valueOf(money));

        Dope dope = new Dope();
        dope.setDprimkey(certi.getId());
        dope.setDtitle("充值成功");
        dope.setDetails("尊敬的"+user.getUnickname()+",您通过"+po.getPaytype()+"充值的"+po.getSxmoney()+"元已到账!");
        dope.setDtime(date);

        //增加充值明细表数据
        poundageMapper.insert(po);
        //增加账户金额数据
        certificationService.upmoney(certi);
        //添加广播数据
        dopeMapper.insert(dope);
        return code;
    }

    // 账户信息查询
    @RequestMapping(value = "find")
    public String find(@RequestParam(value = "id", required = false) String id, Model model,
                       HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        Users user = usersMapper.find(map);
        List<Certification> list = certificationService.certificationList();
        request.setAttribute("num", user.getUphonenumber());
        request.setAttribute("mailbox", user.getUmailbox());
        Certification app = list.get(0);
        model.addAttribute("list", app);
        model.addAttribute("user", user);
        return "redirect:/grzx/grzx_zhzl";
    }

    //银行卡添加
    @RequestMapping(value = "/saveBank")
    public void saveBank(String  cardid,HttpSession session, String name, String kahao, HttpServletResponse response,HttpServletRequest request) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        Users users = (Users) session.getAttribute("loginUser");
        Date date = new Date();
        Bankcard bankcard = new Bankcard();
        Bankcard bankcard1 = bankcardMapper.selectById(users.getUid());
        bankcard.setuID(bankcard1.getuID());
        bankcard.setUname(users.getUnickname());
        bankcard.setZname(users.getUname());
        bankcard.setSfz(users.getUcardid());
        bankcard.setKhh(name);
        bankcard.setCardid(kahao);
        bankcard.setTjtime(date);
        bankcard.setStatu("成功");
        if (!bankcard.getCardid().equals(bankcard1.getCardid())) {
            if(bankcard.getSfz()!=null && !bankcard.getSfz().equals("")){
                int savebankcard = bankcardService.savebankcard(bankcard);
                if (savebankcard > 0) {
                    out.print("<script>alert('添加成功！');window.location.href='/grzx/grzx_cz1';</script>");
                } else {
                    out.print("<script>alert('添加失败！');window.location.href='/grzx/pay1';</script>");
                }
          }else {
                out.print("<script>alert('身份证为空,请先绑定身份证！');window.location.href='/grzx/pay1';</script>");
            }
        } else {
            out.print("<script>alert('已经添加过该银行卡！');window.location.href='/grzx/pay1';</script>");
        }
        out.flush();
        out.close();
    }

}

