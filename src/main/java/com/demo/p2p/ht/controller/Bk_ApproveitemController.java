package com.demo.p2p.ht.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.p2p.ht.entity.*;
import com.demo.p2p.ht.service.*;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @RequestMapping(value = "/updateInfoAudit")
    @ResponseBody
    public Integer updateInfoAudit(Certifrecord certifrecord) {
        UpdateWrapper<Certifrecord> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("crviewpoint", certifrecord.getCrviewpoint());
        updateWrapper.set("crintegral", certifrecord.getCrintegral());
        updateWrapper.set("crispass", certifrecord.getCrispass());
        updateWrapper.set("crauditor", certifrecord.getCrauditor());
        updateWrapper.set("crdate", new Date());
        updateWrapper.eq("cruserid", certifrecord.getCruserid());
        updateWrapper.eq("craiid", certifrecord.getCraiid());
        boolean result = certifrecordService.update(updateWrapper);
        if (result) {
            return 200;
        }
        return 400;
    }

    /**
     * 进入用户资料列表
     *
     * @param cruserid
     * @param craiid
     * @param model
     * @return
     */
    @RequestMapping(value = "/infoAuditByuser")
    public String infoAuditByuser(Integer cruserid, Integer craiid, Model model) {
        Users users = usersService.getById(cruserid);
        List<Approveitem> approveitems = approveitemService.list();
        QueryWrapper<Certifrecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cruserid", cruserid);
        queryWrapper.eq("craiid", craiid);
        //queryWrapper.eq("crispass","1");
        List<Certifrecord> certifrecords = certifrecordService.list(queryWrapper);
        List<Userauditor> userauditors = userauditorService.list();
        Integer jf = 0;
        for (Certifrecord c : certifrecords) {
            if (c.getCrintegral() != null) {
                jf += c.getCrintegral();
            }
        }

        model.addAttribute("approve", approveitems);
        model.addAttribute("user", users);
        model.addAttribute("certrecod", certifrecords);
        model.addAttribute("useraud", userauditors);
        model.addAttribute("craiid", craiid);
        model.addAttribute("jf", jf);
        return "view/basicuserapprove";
    }

    /**
     * 用户资料认证
     *
     * @param current
     * @param model
     * @return
     */
    @RequestMapping(value = "/basicInfoApprove")
    public String basicInfoApprove(Integer current, Model model) {
        if (current == null) {
            current = 1;
        }
        Page<Users> page = new Page<>(current, 5);
        IPage<Users> iPage = usersService.page(page);
        List<Users> users = iPage.getRecords();
        List<Userauditor> userauditors = userauditorService.list();

        model.addAttribute("page", iPage);
        model.addAttribute("users", users);
        model.addAttribute("uas", userauditors);
        return "view/basicinfoList";
    }

    @RequestMapping(value = "/addApprove")
    private String addApprove(String aitype, String ainame) {
        Approveitem approveitem = new Approveitem();
        approveitem.setAistate("1");
        approveitem.setAiname(ainame);
        approveitem.setAitype(aitype);
        approveitemService.save(approveitem);
        return "redirect:/bk/approve/traverseApproves";
    }

    @RequestMapping(value = "/toupdateApprove")
    private String toupdateApprove(String aiid, Model model) {
        Approveitem approve = approveitemService.getById(aiid);
        model.addAttribute("approve", approve);
        return "view/approveupdate";
    }

    @RequestMapping(value = "/toaddApprove")
    private String toaddApprove() {
        return "view/approveadd";
    }

    @RequestMapping(value = "/updateApprove")
    private String updateApprove(String aiid, String aistate, String aitype, String ainame) {
        Approveitem approveitem = approveitemService.getById(aiid);
        if (aistate != null && aistate != "") {
            approveitem.setAistate(aistate);
        }
        if (aitype != null && aitype != "") {
            approveitem.setAitype(aitype);
        }
        if (ainame != null && ainame != "") {
            approveitem.setAiname(ainame);
        }
        approveitemService.updateById(approveitem);
        return "redirect:/bk/approve/traverseApproves";
    }

    @RequestMapping(value = "/traverseApproves")
    public String traverseApproves(Model model, String currpage, HttpSession session) {
        int pagerow = 5;// 每页5行
        int currpages = 1;// 当前页
        int totalpage = 0;// 总页数
        int totalrow = 0;// 总行数
        Map<String, Object> parameters = new HashMap<String, Object>();

        List<Approveitem> list = approveitemService.list();
        totalrow = list.size();
        if (currpage != null && !"".equals(currpage)) {
            currpages = Integer.parseInt(currpage);
        }

        int outcount = 0;// 不够一页的数据条数
        int count = 0;//
        if (currpage != null && !"".equals(currpage)) {
            currpages = Integer.parseInt(currpage);
        }

        outcount = totalrow % pagerow;
        count = totalrow / pagerow;

        totalpage = count;

        if (outcount > 0) {
            totalpage = count + 1;
        }

        if (currpages < 1) {
            currpages = 1;
        }
        if (currpages > totalpage) {
            currpages = totalpage;
        }

        Integer candp = (currpages - 1) * pagerow;
        if (candp < 0) {
            candp = 0;
        }
        parameters.put("pandc", 5);
        parameters.put("candp", candp);
        List<Approveitem> lists = approveitemService.selList(parameters);
        session.setAttribute("totalrow", totalrow);
        session.setAttribute("currpages", currpages);
        session.setAttribute("totalpage", totalpage);
        session.setAttribute("approveitems", lists);
        return "view/approvelist";
    }

    /**
     * 添加审核人
     *
     * @param userauditor
     * @return
     */
    @RequestMapping(value = "/affirmCrauditor")
    @ResponseBody
    public Integer affirmCrauditor(Userauditor userauditor) {
        //添加审核记录
        boolean result = userauditorService.save(userauditor);
        if (result) {
            QueryWrapper<Certifrecord> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("cruserid", userauditor.getUserid());
            //查询指定userid的所有记录
            List<Certifrecord> list = certifrecordService.list(queryWrapper);
            for (Certifrecord c : list) {
                //修改审核人
                c.setCrauditor(userauditor.getUauditor());
                certifrecordService.updateById(c);
            }
            return 200;
        } else {
            return 400;
        }
    }

    /**
     * 查询新用户认证资料
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/newuserInfoList")
    public String newuserInfoList(Model model) {
        //查出所有用户
        List<Users> users = usersService.list();
        //查出所有在职员工
        QueryWrapper<Employee> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("estatus", 1);
        List<Employee> employees = employeeService.list(queryWrapper1);
        //查出所有用户审核人
        List<Userauditor> userauditors = userauditorService.list();
        //查出所有审核记录
        List<Certifrecord> certifrecords = certifrecordService.list();

        if (userauditors != null) {
            for (Userauditor userauditor : userauditors) {
                for (Users u : users) {
                    if (userauditor.getUserid() == u.getUid()) {
                        users.remove(u);
                        break;
                    }
                }
            }
        }

        //查询出未分配审核人的记录
        List<Certifrecord> cr = null;
        if (certifrecords != null) {
            cr = new ArrayList<Certifrecord>();
            for (Users u : users) {
                Certifrecord cerrecord = new Certifrecord();
                int integral = 0;
                int ispass = 0;
                for (Certifrecord c : certifrecords) {
                    if (u.getUid() == c.getCruserid()) {
                        if (c.getCrintegral() != null) {
                            integral += c.getCrintegral();
                        } else {
                            integral += 0;
                        }
                        if (c.getCrispass().equals("1")) {
                            ispass += 1;
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
        model.addAttribute("cr", cr);
        model.addAttribute("users", users);
        model.addAttribute("employees", employees);
        return "view/anewuserinfolist";
    }

    /**
     * 信用额度申请列表
     *
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
    public String limitApplyfor(Integer current, String clpuname, String mindate, String maxdate, String clpstate, Model model) throws Exception {
        if (current == null) {
            current = 1;
        }
        QueryWrapper<Clapplyfor> queryWrapper = new QueryWrapper<>();
        if (clpuname != null && clpuname != "") {
            queryWrapper.like("clpuname", clpuname);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (mindate != null && mindate != "") {
            Date date = sdf.parse(mindate);
            queryWrapper.ge("clpdate", date);
        }
        if (maxdate != null && maxdate != "") {
            Date date = sdf.parse(maxdate);
            queryWrapper.le("clpdate", date);
        }
        if (clpstate != null && clpstate != "" && !clpstate.equals("-1")) {
            queryWrapper.eq("clpstate", clpstate);
        }
        Page<Clapplyfor> page = new Page<>(current, 5);
        IPage<Clapplyfor> iPage = clapplyforService.page(page, queryWrapper);
        List<Certifrecord> cr = certifrecordService.list();
        List<Clapplyfor> cps = iPage.getRecords();
        if (current > iPage.getPages()) {
            page = new Page<>(1, 5);
            iPage = clapplyforService.page(page, queryWrapper);
            cps = iPage.getRecords();
        }
        model.addAttribute("cr", cr);
        model.addAttribute("cps", cps);
        model.addAttribute("page", iPage);
        model.addAttribute("clpuname", clpuname);
        model.addAttribute("mindate", mindate);
        model.addAttribute("maxdate", maxdate);
        model.addAttribute("clpstate", clpstate);
        return "view/limitapplyforlist";
    }

    /**
     * 许俊毅 认证资料统计
     */
    @RequestMapping(value = "/approveStatistics")
    public String rz(HttpServletRequest request) {
        System.out.println("rz===========================");
        QueryWrapper<Certifrecord> queryWrapper1 = new QueryWrapper<>();
        String crusername = null;
        String craiid = null;
        int pageindex = 1;


        if(request.getParameter("current") != null){
            pageindex = Integer.parseInt(request.getParameter("current"));
        }
        System.out.println(pageindex);
        if (request.getParameter("crusername") != null) {
            crusername = request.getParameter("crusername");//获取用户名
        }

        if (request.getParameter("craiid") != null) {
            craiid = request.getParameter("craiid");//获取下拉列表数据
        }

        if (craiid != null && !craiid.equals("")) {
            queryWrapper1.eq("craiid", craiid);
        }
        if (crusername != null && !crusername.equals("")) {
            queryWrapper1.eq("crusername", crusername);
        }

        Page<Certifrecord> page = new Page<Certifrecord>(pageindex,5);
        List<Approveitem> itemlist = approveitemService.list();//下拉列表显示值
        IPage<Certifrecord> iPage = certifrecordService.page(page, queryWrapper1);
        List<Certifrecord> cdlist =  iPage.getRecords();//显示certifrecord表信息
        List<Users> usersList = usersService.list();//显示users表信息

        request.setAttribute("page",page);
        request.setAttribute("userlist", usersList);
        request.setAttribute("cdlist", cdlist);
        request.setAttribute("aplist", itemlist);
        return "view/approvestatistics";
    }


    @RequestMapping(value = "crlist")
    @ResponseBody
    public List<Certifrecord> getinfo(int crid){
        System.out.println("getinfo===============================================================");

        QueryWrapper<Certifrecord> queryWrapper = new QueryWrapper<Certifrecord>();
        queryWrapper.eq("crid",crid);
        List<Certifrecord> list  = certifrecordService.list(queryWrapper);
        for (Certifrecord ls : list){
            System.out.println(ls.getCrusername());
        }
        return list;
    }

}

