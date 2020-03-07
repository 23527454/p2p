package com.demo.p2p.ht.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.p2p.ht.entity.*;
import com.demo.p2p.ht.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gzd
 * @since 2020-02-22
 */
@Controller
@RequestMapping("/bk/brower")
public class Bk_BorrowmoneyController {
    @Resource
    private Bk_BorrowmoneyService borrowmoneyService;
    @Resource
    private Bk_BiaoService biaoService;
    @Resource
    private Bk_BorrowcordService borrowcordService;
    @Resource
    private Bk_ProductService productService;
    @Resource
    private Bk_CertificationService certificationService;
    @Resource
    private Bk_InvestinfoService investinfoService;
    @Resource
    private Bk_UsersService usersService;
    @Resource
    private Bk_TradeService tradeService;

    /**
     * 还款
     * @param ids
     * @param id
     * @return
     */
    @RequestMapping(value = "/tohuankuanupds")
    public String tohuankuanupds(Integer ids,Integer id,Double bl,Double lx){
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
            Double value=investinfos.get(i).getInmoney().doubleValue()/Double.parseDouble(ptotalmoney.toString());
            //计算本次投资的收益/回款
            Double je=bl*value;
            //计算待收、可用余额、总余额
            Double tz_cdue=Double.parseDouble(certifications.get(i).getCdue())-je;
            Double tz_cbalance=Double.parseDouble(certifications.get(i).getCbalance())+je;
            Double tz_ctotalmoney=Double.parseDouble(certifications.get(i).getCtotalmoney())+je;
            //存储待收、可用余额、总余额，存储时进行转换，否则数据过长会加符号：E
            certifications.get(i).setCdue(String.format("%.2f",tz_cdue));
            certifications.get(i).setCbalance(String.format("%.2f",tz_cbalance));
            certifications.get(i).setCtotalmoney(String.format("%.2f",tz_ctotalmoney));
            //修改当前用户余额
            certificationService.updateById(certifications.get(i));

            //根据用户名查询users
            QueryWrapper<Users> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("unickname",certifications.get(i).getCusername());
            Users users=usersService.getOne(queryWrapper);
            //创建资金记录，并且添加到数据库
            Trade trade=new Trade();
            trade.setuID(users.getUid());
            trade.setUname(users.getUnickname());
            trade.setZname(users.getUname());
            trade.setJymoney(String.format("%.2f",je));
            trade.setWhat("回款");
            trade.setJytime(new Date());
            trade.setOther("借款的收益");
            tradeService.save(trade);
        }
        //获取发起借款的用户的待还金额，减去本次还款的利息，获得还款后的待还金额
        Double fqjk_cpaid=Double.parseDouble(certification1.getCpaid())-bl;
        certification1.setCpaid(String.format("%.2f",fqjk_cpaid));
        certificationService.updateById(certification1);

        return "redirect:/bk/brower/tohuankuanupd?id="+id;
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

    /**
     * 进入还款详情
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/tohuankuanupd")
    public String tohuankuanupd(Integer id,Model model){
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
        model.addAttribute("list",lists);
        return "view/bk_huankuanupdeta";
    }

    /**
     * 进入还款列表
     * @param current
     * @param model
     * @return
     */
    @RequestMapping(value = "/tohk")
    public String tohk(Integer current,Model model){
        if(current==null || current==0){
            current=1;
        }
        QueryWrapper<Borrowmoney> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("bstate","1");
        Page<Borrowmoney> page=new Page<>(current,5);
        IPage<Borrowmoney> iPage=borrowmoneyService.page(page,queryWrapper);
        List<Borrowmoney> list=iPage.getRecords();
        List<Biao> biaos=biaoService.list();
        model.addAttribute("lists",list);
        model.addAttribute("bList",biaos);
        model.addAttribute("page",iPage);
        return "view/bk_huankuanlist";
    }

    /**
     * 所有借款
     * @param current
     * @param brelname
     * @param btype
     * @param bstate
     * @param model
     * @return
     */
    @RequestMapping(value = "/qurey")
    public String qurey(Integer current,String brelname,String btype,String bstate,Model model){
        if(current==null || current==0){
            current=1;
        }
        QueryWrapper<Borrowmoney> queryWrapper=new QueryWrapper<>();
        if(brelname!=null && brelname!=""){
            queryWrapper.like("brelname",brelname);
        }
        if (btype!=null && btype!="" && !btype.equals("0")){
            queryWrapper.eq("btype",btype);
        }
        if (bstate!=null && bstate!="" && !bstate.equals("-1")){
            queryWrapper.eq("bstate",bstate);
        }
        Page<Borrowmoney> page=new Page<>(current,5);
        IPage<Borrowmoney> iPage=borrowmoneyService.page(page,queryWrapper);
        List<Borrowmoney> list=iPage.getRecords();
        List<Biao> biaos=biaoService.list();
        if (current>iPage.getPages()){
            page=new Page<>(1,5);
            iPage=borrowmoneyService.page(page,queryWrapper);
            list=iPage.getRecords();
        }
        model.addAttribute("lists",list);
        model.addAttribute("bList",biaos);
        model.addAttribute("page",iPage);
        model.addAttribute("brelname",brelname);
        model.addAttribute("bstate",bstate);
        model.addAttribute("btype",btype);
        return "view/bk_moneylist";
    }

    /**
     * 借款审核不通过
     * @param id
     * @param status
     * @return
     */
    @RequestMapping(value = "/audit")
    public String audit(Integer id,Integer status){
        Borrowmoney borrowmoney = borrowmoneyService.getById(id);
        borrowmoney.setBstate(status.toString());
        borrowmoneyService.updateById(borrowmoney);
        return "redirect:/bk/brower/check";
    }

    @RequestMapping(value = "/addProduct")
    public void addProduct(String xmqx,String jkqx,String fbsj,Product product, @RequestParam(value = "ufile", required = true) MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        if (file.getSize() != 0) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            File directory = new File("src/main/resources/static/cover");
            String path = directory.getCanonicalPath();// 获得上传的路径
            String fileName = file.getOriginalFilename();// 获得上传的文件名
            File targetFile = new File(path, fileName);// 创建上传到服务器的文件对象
            try {
                //存储新图片
                file.transferTo(targetFile);
                //设置实体值
                String imgUrl = request.getContextPath() + "/cover/" + fileName;
                product.setPicture(imgUrl);
                product.setPtime(sdf.parse(xmqx));
                product.setPcount(sdf.parse(jkqx));
                product.setPpublishtime(sdf.parse(fbsj));
                boolean result=productService.save(product);
                if (result){
                    Borrowmoney borrowmoney=borrowmoneyService.getById(product.getBmid());
                    borrowmoney.setBtype(product.getPtype().toString());
                    borrowmoneyService.updateById(borrowmoney);
                    out.print("<script>alert('审核成功！');window.location.href='/bk/brower/check';</script>");
                }else{
                    out.print("<script>alert('审核失败！');window.location.href='/bk/brower/toAddProduct';</script>");
                }
            } catch (IOException e) {
                out.print("<script>alert('审核失败！');window.location.href='/bk/brower/toAddProduct';</script>");
            }
        }else{
            out.print("<script>alert('审核失败，请检查图片是否存在异常！');window.location.href='/bk/brower/toAddProduct';</script>");
        }
        out.flush();
        out.close();
    }

    @RequestMapping(value = "/toAddProduct")
    public String toAddProduct(Integer bmid,Model model) throws Exception{
        Borrowmoney borrowmoney=borrowmoneyService.getById(bmid);
        Product product=new Product();
        product.setPmoney(0L);
        product.setProgress("0.00");

        Calendar c = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
        Date time = simpleDateFormat.parse(borrowmoney.getBlimit());
        c.setTime(time);   //设置时间
        c.add(Calendar.MONTH, Integer.parseInt(borrowmoney.getBtimelimit())); //日期分钟加1,Calendar.DATE(天),Calendar.HOUR(小时)
        Date date = c.getTime();
        product.setPtime(date);
        product.setPcount(date);
        product.setPpublishtime(new Date());
        product.setPtotalmoney(Long.parseLong(borrowmoney.getBmoney()));
        product.setPrange("50元~ 不限");
        product.setPstate("1");
        product.setPproduce(Integer.parseInt(borrowmoney.getBusername()));
        product.setBmid(bmid);
        List<Biao> biaos=biaoService.list();
        model.addAttribute("blist",biaos);
        model.addAttribute("pr",product);
        return "view/bk_input_pro2";
    }

    /**
     *借款审核通过，并添加还款表数据
     * @param borrowmoney
     * @return
     */
    @RequestMapping(value = "/borxg")
    public String borxg(Borrowmoney borrowmoney){
        //修改借款的状态
        borrowmoney.setBstate("1");
        borrowmoneyService.updateById(borrowmoney);

        //创建还款对象
        Borrowcord borrowcord=new Borrowcord();
        int ys = Integer.parseInt(borrowmoney.getBtimelimit());

        Date date = new Date();

        borrowcord.setBstatue(0);// 设置还款记录表状态
        borrowcord.setBid(borrowmoney.getId());// 设置借款表ID

        Calendar calendar = Calendar.getInstance();// 时间转换
        if (!borrowmoney.getBserial().equals("1")) {
            for (int i = 0; i < ys; i++) {
                calendar.setTime(date);
                calendar.add(Calendar.SECOND,  60 * 60 * 24 * 30);
                date = calendar.getTime();
                borrowcord.setBdate(date);
                borrowcord.setBcs(i + 1);
                borrowcordService.save(borrowcord);
            }
        } else {
            calendar.setTime(date);
            calendar.add(Calendar.SECOND, 1000 * 60 * 60 * 24 * 30 * ys);
            date = calendar.getTime();
            borrowcord.setBdate(date);
            borrowcord.setBcs(1);
            borrowcordService.save(borrowcord);
        }
        return "redirect:/bk/brower/toAddProduct?bmid="+borrowmoney.getId();
    }

    /**
     * 进入借款审核详情页面
     * @param ids
     * @param model
     * @return
     */
    @RequestMapping(value = "/borqr")
    public String borqr(Integer ids,Model model){
        Borrowmoney borrowmoney=borrowmoneyService.getById(ids);
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());   //设置时间
        c.add(Calendar.MONTH, Integer.parseInt(borrowmoney.getBtimelimit())); //日期分钟加1,Calendar.DATE(天),Calendar.HOUR(小时)
        Date date = c.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
        String time = simpleDateFormat.format(date);
        borrowmoney.setBlimit(time);
        model.addAttribute("borr",borrowmoney);
        return "view/bk_huankuanget";
    }

    /**
     * 进入待审核的借款
     * @param current
     * @param model
     * @return
     */
    @RequestMapping(value = "/check")
    public String check(Integer current,Model model){
        if(current==null || current==0){
            current=1;
        }
        QueryWrapper<Borrowmoney> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("bstate","0");
        Page<Borrowmoney> page=new Page<>(current,5);
        IPage<Borrowmoney> iPage=borrowmoneyService.page(page,queryWrapper);
        List<Borrowmoney> list=iPage.getRecords();
        List<Biao> biaos=biaoService.list();

        model.addAttribute("lists",list);
        model.addAttribute("bList",biaos);
        model.addAttribute("page",iPage);
        return "view/bk_money_check";
    }

    @RequestMapping(value = "/bajax")
    @ResponseBody
    public Borrowmoney bajax(Integer id){
        return borrowmoneyService.getById(id);
    }

    @RequestMapping(value = "/hjyList")
    public String hjyList(Integer current, Model model){
        if(current==null || current==0){
            current=1;
        }
        Page<Borrowmoney> page=new Page<>(current,5);
        IPage<Borrowmoney> iPage=borrowmoneyService.page(page);
        List<Borrowmoney> list=iPage.getRecords();
        model.addAttribute("page",iPage);
        model.addAttribute("wdlist",list);
        return "view/Borrowmoneylist";
    }
}

