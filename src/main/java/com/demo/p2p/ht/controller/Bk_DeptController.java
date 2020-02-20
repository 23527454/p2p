package com.demo.p2p.ht.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.p2p.ht.entity.Dept;
import com.demo.p2p.ht.service.Bk_DeptService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gzd
 * @since 2020-02-20
 */
@Controller
@RequestMapping("/bk/dept")
public class Bk_DeptController {
    @Resource
    private Bk_DeptService deptService;

    @RequestMapping(value = "/insert")
    public void add(Dept dept,HttpServletResponse response) throws IOException{
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        dept.setDstatus(1);
        dept.setDtime(new Date());
        boolean result=deptService.save(dept);
        if (result){
            out.print("<script>alert('添加成功！');window.location.href='/bk/dept/findall';</script>");
        }else{
            out.print("<script>alert('添加失败！');history.go(-1);</script>");
        }
        out.flush();
        out.close();
    }

    @RequestMapping(value = "/upd")
    public void modify(Dept dept,HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        boolean result=deptService.updateById(dept);
        if (result){
            out.print("<script>alert('修改成功！');window.location.href='/bk/dept/findall';</script>");
        }else{
            out.print("<script>alert('修改失败！');window.location.href='/bk/dept/findall';</script>");
        }
        out.flush();
        out.close();
    }

    @RequestMapping(value = "/del")
    public void del(Integer did,HttpServletResponse response) throws IOException{
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        boolean result=deptService.removeById(did);
        if (result){
            out.print("<script>alert('删除成功！');window.location.href='/bk/dept/findall';</script>");
        }else{
            out.print("<script>alert('删除失败！');window.location.href='/bk/dept/findall';</script>");
        }
        out.flush();
        out.close();
    }

    @RequestMapping(value = "/toupd")
    public String toUpdate(Integer did,Model model){
        Dept dept=deptService.getById(did);
        model.addAttribute("dept",dept);
        return "view/bk_deptupd";
    }

    @RequestMapping(value = "/toadd")
    public String toAdd(){
        return "view/bk_deptadd";
    }

    @RequestMapping(value = "/findall")
    public String findAll(Integer current, Model model){
        if (current==null){
            current=1;
        }
        Page<Dept> page=new Page<>(current,5);
        IPage<Dept> iPage=deptService.page(page);
        List<Dept> deptList=iPage.getRecords();
        model.addAttribute("deptlist",deptList);
        model.addAttribute("page",page);
        return "view/bk_deptlist";
    }
}

