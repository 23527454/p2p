package com.demo.p2p.ht.controller;


import com.demo.p2p.ht.entity.Biao;
import com.demo.p2p.ht.entity.Withdrawal;
import com.demo.p2p.ht.service.Bk_BiaoService;
import com.demo.p2p.ht.service.Bk_WithdrawalService;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.p2p.ht.entity.Biao;
import com.demo.p2p.ht.service.Bk_BiaoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gzd
 * @since 2020-02-25
 */
@Controller
@RequestMapping("/bk/biao")
public class Bk_BiaoController {
    @Resource
    private Bk_BiaoService biaoService;

    @RequestMapping(value = "/delete")
    public void delete(Integer id, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        boolean result=biaoService.removeById(id);
        if (result){
            out.print("<script>alert('删除成功！');window.location.href='/bk/biao/list';</script>");
        }else{
            out.print("<script>alert('删除失败！');window.location.href='/bk/biao/list';</script>");
        }
        out.flush();
        out.close();
    }

    @RequestMapping(value = "/save")
    public void save(Biao biao,HttpServletResponse response) throws Exception{
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        boolean result=false;
        if(biao.getId()!=null && biao.getId()!=0){
            result=biaoService.updateById(biao);
            if(result){
                out.print("<script>alert('修改成功！');window.location.href='/bk/biao/list';</script>");
            }else{
                out.print("<script>alert('修改失败！');window.location.href='/bk/biao/input?id="+biao.getId()+"';</script>");
            }
        }else{
            result=biaoService.save(biao);
            if(result){
                out.print("<script>alert('添加成功！');window.location.href='/bk/biao/list';</script>");
            }else{
                out.print("<script>alert('添加失败！');window.location.href='/bk/biao/input';</script>");
            }
        }
    }

    @RequestMapping(value = "/input")
    public String input(Integer id,Model model){
        Biao biao=null;
        if(id!=null){
            biao=biaoService.getById(id);
        }else{
            biao=new Biao();
        }
        model.addAttribute("b",biao);
        return "view/bk_input_biao";
    }

    @RequestMapping(value = "/list")
    public String list(Integer current, Model model){
        if(current==null || current==0){
            current=1;
        }
        Page<Biao> page=new Page<>(current,5);
        IPage<Biao> iPage=biaoService.page(page);
        List<Biao> list=iPage.getRecords();

        model.addAttribute("lists",list);
        model.addAttribute("page",iPage);
        return "view/bk_biao_list";
    }

}

