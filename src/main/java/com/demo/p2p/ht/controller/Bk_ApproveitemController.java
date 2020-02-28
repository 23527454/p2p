package com.demo.p2p.ht.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.p2p.ht.entity.*;
import com.demo.p2p.ht.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 认证项管理 前端控制器
 * </p>
 *
 * @author gzd
 * @since 2020-02-27
 */
@Controller
@RequestMapping("/bk/approve")
public class Bk_ApproveitemController {
    @Resource
    private Bk_ApproveitemService approveitemService;
    @Resource
    private Bk_ClapplyforService clapplyforService;
    @Resource
    private Bk_CertifrecordService certifrecordService;
    @Resource
    private Bk_UserauditorService userauditorService;
    @Resource
    private Bk_UsersService usersService;
    @Resource
    private Bk_EmployeeService employeeService;

    @RequestMapping(value = "/newuserInfoList")
    public String newuserInfoList(Model model){
        //查出所有用户
        List<Users> users=usersService.list();
        //查出所有在职员工
        QueryWrapper<Employee> queryWrapper1=new QueryWrapper<>();
        queryWrapper1.eq("estatus",1);
        List<Employee> employees=employeeService.list(queryWrapper1);
        //查出所有用户审核人
        List<Userauditor> userauditors=userauditorService.list();
        //查出所有审核记录
        List<Certifrecord> certifrecords=certifrecordService.list();

        if(userauditors!=null){
            for (Userauditor userauditor : userauditors) {
                for(Users u : users){
                    if(userauditor.getUserid()==u.getUid()){
                        users.remove(u);
                        break;
                    }
                }
            }
        }

        //查询出未分配审核人的记录
        List<Certifrecord> cr = null;
        if(certifrecords!=null){
            cr =  new ArrayList<Certifrecord>();
            for (Users u : users) {
                Certifrecord cerrecord = new Certifrecord();
                int integral = 0;
                int ispass = 0;
                for (Certifrecord c : certifrecords) {
                    if(u.getUid()==c.getCruserid()){
                        if(c.getCrintegral()!=null){
                            integral+=c.getCrintegral();
                        }else{
                            integral+=0;
                        }
                        if(c.getCrispass().equals("1")){
                            ispass+=1;
                        }
                    }
                }
                cerrecord.setCruserid(u.getUid());
                cerrecord.setCrusername(u.getUnickname());
                cerrecord.setCrintegral(integral);
                cerrecord.setCheckpend(ispass);
                cr.add(cerrecord);
            }
        }
        model.addAttribute("cr",cr);
        model.addAttribute("users",users);
        model.addAttribute("employees",employees);
        return "view/anewuserinfolist";
    }

    /**
     * 信用额度申请列表
     * @param current
     * @param clpuname
     * @param mindate
     * @param maxdate
     * @param clpstate
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/limitApplyfor")
    public String limitApplyfor(Integer current, String clpuname, String mindate, String maxdate, String clpstate, Model model) throws Exception{
        if(current==null){
            current=1;
        }
        QueryWrapper<Clapplyfor> queryWrapper=new QueryWrapper<>();
        if (clpuname!=null && clpuname!=""){
            queryWrapper.like("clpuname",clpuname);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(mindate!=null && mindate!=""){
            Date date = sdf.parse(mindate);
            queryWrapper.ge("clpdate",date);
        }
        if(maxdate!=null && maxdate!=""){
            Date date = sdf.parse(maxdate);
            queryWrapper.le("clpdate",date);
        }
        if (clpstate!=null && clpstate!="" && !clpstate.equals("-1")){
            queryWrapper.eq("clpstate",clpstate);
        }
        Page<Clapplyfor> page=new Page<>(current,5);
        IPage<Clapplyfor> iPage=clapplyforService.page(page,queryWrapper);

        List<Certifrecord> cr=certifrecordService.list();
        List<Clapplyfor> cps=iPage.getRecords();
        model.addAttribute("cr",cr);
        model.addAttribute("cps",cps);
        model.addAttribute("page",iPage);
        model.addAttribute("clpuname",clpuname);
        model.addAttribute("mindate",mindate);
        model.addAttribute("maxdate",maxdate);
        model.addAttribute("clpstate",clpstate);
        return "view/limitapplyforlist";
    }

}

