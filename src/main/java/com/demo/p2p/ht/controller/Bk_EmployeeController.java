package com.demo.p2p.ht.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.p2p.ht.entity.Dept;
import com.demo.p2p.ht.entity.Employee;
import com.demo.p2p.ht.service.Bk_DeptService;
import com.demo.p2p.ht.service.Bk_EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gzd
 * @since 2020-02-18
 */
@Controller
@RequestMapping("/bk/employee")
public class Bk_EmployeeController {
    @Resource
    private Bk_EmployeeService employeeService;
    @Resource
    private Bk_DeptService deptService;

    @RequestMapping(value = "/add")
    public void add(String ebirths,String etimes,Employee employee,HttpServletResponse response) throws IOException{
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date ebirth=df.parse(ebirths);
            Date etime=df.parse(etimes);
            employee.setEbirth(ebirth);
            employee.setEtime(etime);
        } catch (Exception e) {
            e.printStackTrace();

        }
        employee.setEstatus(1);
        boolean result=employeeService.save(employee);
        if (result){
            out.print("<script>alert('添加成功！');window.location.href='/bk/employee/list';</script>");
        }else{
            out.print("<script>alert('添加失败！');history.go(-1);</script>");
        }
        out.flush();
        out.close();
    }

    @RequestMapping(value = "/upd")
    public void modify(String ebirths,String etimes,Employee employee,HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date ebirth=df.parse(ebirths);
            Date etime=df.parse(etimes);
            employee.setEbirth(ebirth);
            employee.setEtime(etime);
        } catch (Exception e) {
            e.printStackTrace();

        }
        boolean result=employeeService.updateById(employee);
        if (result){
            out.print("<script>alert('修改成功！');window.location.href='/bk/employee/list';</script>");
        }else{
            out.print("<script>alert('修改失败！');window.location.href='/bk/employee/list';</script>");
        }
        out.flush();
        out.close();
    }

    @RequestMapping(value = "/del")
    public void del(Integer eid,HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        boolean result=employeeService.removeById(eid);
        if (result){
            out.print("<script>alert('删除成功！');window.location.href='/bk/employee/list';</script>");
        }else{
            out.print("<script>alert('删除失败！');window.location.href='/bk/employee/list';</script>");
        }
        out.flush();
        out.close();
    }

    @RequestMapping(value = "/toupd")
    public String toUpdate(Integer eid,Model model){
        Employee employee=employeeService.getById(eid);
        model.addAttribute("ee",employee);
        List<Dept> depts=deptService.list();
        model.addAttribute("depts",depts);
        return "view/bk_empupd";
    }

    @RequestMapping(value = "/toadd")
    public String toAdd(Model model){
        List<Dept> depts=deptService.list();
        model.addAttribute("depts",depts);
        return "view/bk_empadd";
    }

    @RequestMapping(value = "/list")
    public String list(Integer current, String ename, Model model){
        if (current==null){
            current=1;
        }
        QueryWrapper<Employee> queryWrapper=new QueryWrapper<>();
        if(ename!=null && ename!=""){
            queryWrapper.like("ename",ename);
        }
        Page<Employee> page=new Page<>(current,5);
        IPage<Employee> iPage=employeeService.page(page,queryWrapper);
        List<Employee> employees=iPage.getRecords();
        List<Dept> depts=deptService.list();
        if (current>iPage.getPages()){
            page=new Page<>(1,5);
            iPage=employeeService.page(page,queryWrapper);
            employees=iPage.getRecords();
        }
        model.addAttribute("emps",employees);
        model.addAttribute("depts",depts);
        model.addAttribute("ename",ename);
        model.addAttribute("page",page);
        return "view/bk_emplist";
    }
}

