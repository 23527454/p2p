package com.demo.p2p.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.p2p.entity.*;
import com.demo.p2p.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gzd
 * @since 2020-02-11
 */
@Controller
@RequestMapping("/brower")
public class BorrowmoneyController {
    @Resource
    private BorrowmoneyService borrowmoneyService;
    @Resource
    private BorrowcordService borrowcordService;
    @Resource
    private ProductService productService;
    @Resource
    private InvestinfoService investinfoService;
    @Resource
    private CertificationService certificationService;
    @Resource
    private TradeService tradeService;
    @Resource
    private UsersService usersService;

    /**
     * 还款
     * @param ids
     * @param id
     * @return
     */
    @RequestMapping(value = "/tohuankuanupds")
    public String tohuankuanupds(Integer ids,Integer id,Double bl,Double lx,HttpSession session){
        Borrowcord borrowcord=borrowcordService.getById(ids);
        borrowcord.setBstatue(1);
        borrowcordService.updateById(borrowcord);
        Long ptotalmoney=productService.findPtotalmoney(borrowcord.getBid());
        List<Investinfo> investinfos=investinfoService.findInMoneySum(borrowcord.getBid());
        //获取发起借款的账户余额
        Certification certification1=certificationService.findCertificationByBmId(borrowcord.getBid());
        //获取投资用户的账户余额
        List<Certification> certifications=certificationService.findCertificationByBmId2(borrowcord.getBid());
        for(int i=0;i<investinfos.size();i++){
            //获取当前这一次投资记录的投资占比（以根据用户id进行了组合，相当于一个用户一次该项目的总投资信息）
            Double value=investinfos.get(i).getInmoney()/Double.parseDouble(ptotalmoney.toString());
            //计算本次投资的收益/回款
            Double je=bl*value;
            //计算待收、可用余额、总余额
            Double tz_cdue=certifications.get(i).getCdue()-je;
            Double tz_cbalance=Double.parseDouble(certifications.get(i).getCbalance())+je;
            Double tz_ctotalmoney=Double.parseDouble(certifications.get(i).getCtotalmoney())+je;
            //存储待收、可用余额、总余额，存储时进行转换，否则数据过长会加符号：E
            certifications.get(i).setCdue(tz_cdue);
            certifications.get(i).setCbalance(String.format("%.2f",tz_cbalance));
            certifications.get(i).setCtotalmoney(String.format("%.2f",tz_ctotalmoney));
            //修改当前用户余额
            certificationService.updateById(certifications.get(i));

            //根据用户名查询users
            QueryWrapper<Users> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("unickname",certifications.get(i).getCusername());
            Users users=usersService.getOne(queryWrapper);
            //创建资金记录，并且添加到数据库,回款
            Trade trade=new Trade();
            trade.setuID(users.getUid());
            trade.setUname(users.getUnickname());
            trade.setZname(users.getUname());
            trade.setJymoney(je);
            trade.setWhat("回款");
            trade.setJytime(new Date());
            trade.setOther("借款的收益");
            tradeService.save(trade);
        }

        Users users=(Users)session.getAttribute("loginUser");
        //创建资金记录，并且添加到数据库，还款
        Trade trade2=new Trade();
        trade2.setuID(users.getUid());
        trade2.setUname(users.getUnickname());
        trade2.setZname(users.getUname());
        trade2.setJymoney(bl);
        trade2.setWhat("还款");
        trade2.setJytime(new Date());
        trade2.setOther("借的钱终究是要还的");
        tradeService.save(trade2);

        //获取发起借款的用户的待还金额、余额、总金额，减去本次还款的利息，获得还款后的待还金额
        Double fqjk_cpaid=certification1.getCpaid()-bl;
        Double fqjk_cbalance=Double.parseDouble(certification1.getCbalance())-bl;
        Double fqjk_ctotalmoney=Double.parseDouble(certification1.getCtotalmoney())-bl;
        certification1.setCpaid(fqjk_cpaid);
        certification1.setCbalance(fqjk_cbalance.toString());
        certification1.setCtotalmoney(fqjk_ctotalmoney.toString());
        certificationService.updateById(certification1);

        return "redirect:/brower/tohuankuan?id="+id;
    }

    /**
     * 获取还款列表
     * @param id
     * @return
     */
    @RequestMapping(value = "/tohuankuanupdison")
    @ResponseBody
    public List<Borrowcord> tohuankuanupdison(Integer id){
        QueryWrapper<Borrowcord> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("bid",id);
        List<Borrowcord> lists=borrowcordService.list(queryWrapper);
        return lists;
    }

    @RequestMapping(value = "/tohuankuan")
    public String tohuankuan(Integer id,Model model){
        Borrowmoney borrowmoney=borrowmoneyService.getById(id);
        QueryWrapper<Borrowcord> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("bid",id);
        List<Borrowcord> lists=borrowcordService.list(queryWrapper);
        //如果全部还完了就修改状态
        Integer num=0;
        for(Borrowcord b:lists){
            if (b.getBstatue()==1){
                num++;
            }
        }
        if (num==lists.size()){
            borrowmoney.setBstate("3");
            borrowmoneyService.updateById(borrowmoney);
        }
        model.addAttribute("borr",borrowmoney);
        return "huankuaninfo";
    }

    /**
     * 进入还款列表
     * @param current
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/toHuanKuanListByUserId")
    public String toHuanKuanListByUserId(Integer current, Model model,HttpSession session){
        Users user = (Users) session.getAttribute("loginUser");
        if (user == null) {
            return "redirect:/sys/login";
        }
        if (current==null){
            current=1;
        }

        Page<Borrowmoney> page=new Page<>(current,5);
        page.setRecords(borrowmoneyService.selHuanKuanList(user.getUid(),page));
        List<Borrowmoney> list=page.getRecords();
        Double ljjk=borrowmoneyService.sumBorrow(user.getUid());

        model.addAttribute("ljjk",ljjk);
        model.addAttribute("list",list);
        model.addAttribute("page",page);
        return "huankuanlist";
    }

    @RequestMapping("toaddborr.do")
    @ResponseBody
    public Object shang(Borrowmoney borrowmoney, HttpServletRequest request ) {
        Map<String,Object> map=new HashMap<String,Object>();
        System.out.println("shang----------------------");
        HttpSession session = request.getSession();
        int num = 0;
        Users users = null;

        users  = (Users) session.getAttribute("loginUser");
        if(users !=  null ){
            System.out.println(borrowmoney.getBtimelimit());
            DateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Calendar c = Calendar.getInstance();
            int inum = Integer.parseInt(borrowmoney.getBtimelimit());
            c.add(Calendar.MONTH,inum);
            borrowmoney.setBlimit(df.format(c.getTime()));
            borrowmoney.setBusername(users.getUid().toString());
            boolean real = borrowmoneyService.save(borrowmoney);
            map.put("jie",true);
            System.out.println(real);
        }else {
            map.put("jie",false);
            System.out.println(borrowmoney.getBrecommend());
        }
        return map;
    }
}

