package com.demo.p2p.controller;

import com.demo.p2p.entity.Certification;
import com.demo.p2p.entity.Dope;
import com.demo.p2p.entity.Poundage;
import com.demo.p2p.entity.Users;
import com.demo.p2p.mapper.CertificationMapper;
import com.demo.p2p.mapper.DopeMapper;
import com.demo.p2p.mapper.PoundageMapper;
import com.demo.p2p.mapper.UsersMapper;
import com.demo.p2p.service.ApproveitemService;
import com.demo.p2p.service.BankcardService;
import com.demo.p2p.service.CertificationService;
import com.demo.p2p.service.DopeService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    private ApproveitemService approveitemService;

    @Resource
    private BankcardService bankcardService;
    /**
     * 个人中心——系统消息
     * @return
     */
    @RequestMapping(value = "/grzx_xtxx")
    public String grzx_xtxx(Model model,@RequestParam(value="currpage",required=false)String conent){
        int pagecount = 1;//每页显示行数
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
        System.out.println("userpay============================");
        String code="200";
        Date date = new Date();
        Map<String, Object> usermap = new HashMap<>();
        Map<String, Object> map = new HashMap<>();

        usermap.put("id",po.getuID());

        Users user = usersMapper.find(usermap);
        po.setUname(user.getUnickname());
        po.setZname(user.getUname());
        po.setWhat("充值");
        po.setSxtime(date);
        po.setBookaccount(user.getUid()+"");
        po.setPaytype("快捷支付");

        Certification certi = certificationMapper.selectById(po.getuID());
        //可用余额
        String cba11 = certi.getCbalance();
        String cbal ="";
        cbal =String.valueOf(cba11);
        String xmoney = po.getSxmoney();
        Float fmoney = Float.valueOf(cbal)+Float.valueOf(xmoney);

        //总余额
        String moneyString = certi.getCtotalmoney();
        Float money = Float.valueOf(moneyString)+Float.valueOf(xmoney);
        map.put("id", po.getuID());
        map.put("cbalance", fmoney.toString());
        map.put("ctotalmoney", money.toString());

        Dope dope = new Dope();
        dope.setDprimkey(po.getuID());
        dope.setDtitle("充值成功");
        dope.setDetails("尊敬的"+user.getUnickname()+",您通过"+po.getPaytype()+"充值的"+po.getSxmoney()+"元已到账!");
        dope.setDtime(date);

        //增加充值明细表数据
        poundageMapper.insert(po);
        //增加账户金额数据
        certificationService.undate(map);
        //添加广播数据
        dopeMapper.insert(dope);
        return code;
    }

    // 账户信息查询
    @RequestMapping("find")
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
        return "personalpage";
    }
}

