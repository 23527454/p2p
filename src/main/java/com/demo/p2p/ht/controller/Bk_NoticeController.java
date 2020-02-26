package com.demo.p2p.ht.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.p2p.ht.entity.Employee;
import com.demo.p2p.ht.entity.Notice;
import com.demo.p2p.ht.service.Bk_NoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
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
 * @since 2020-02-21
 */
@Controller
@RequestMapping("/bk/notice")
public class Bk_NoticeController {
    @Resource
    private Bk_NoticeService noticeService;

    @RequestMapping(value = "/nottop")
    public void nottop(Integer ids,HttpServletResponse response) throws IOException{
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        Notice notice=noticeService.getById(ids);
        notice.setNoticelasttime(new Date());
        boolean result=noticeService.updateById(notice);
        if(result){
            out.print("<script>alert('置顶成功！');window.location.href='/bk/notice/toaddlisttupian';</script>");
        }else{
            out.print("<script>alert('置顶失败！');window.location.href='/bk/notice/toaddlisttupian';</script>");
        }
        out.flush();
        out.close();
    }

    @RequestMapping(value = "/toaddlisttupian")
    public String toaddlisttupian(Integer current,Model model){
        QueryWrapper<Notice> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("noticetype","6");
        queryWrapper.orderByDesc("noticelasttime");
        if (current==null){
            current=1;
        }
        Page<Notice> page=new Page<>(current,5);
        IPage<Notice> iPage=noticeService.page(page,queryWrapper);
        List<Notice> noticeList=iPage.getRecords();

        model.addAttribute("list",noticeList);
        model.addAttribute("page",page);
        return "view/noticeaddlisttupian";
    }

    @RequestMapping(value = "/addtupian")
    public String addtupian(){
        return "view/noticeaddtupian";
    }

    @RequestMapping(value = "/notdel")
    public void notdel(Integer ids,HttpServletResponse response) throws IOException{
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        Notice notice=noticeService.getById(ids);
        boolean result=noticeService.removeById(ids);
        if (result){
            //删除图片
            File directory = new File("src/main/resources/static/file");
            String path = directory.getCanonicalPath();// 获得上传的路径
            //删除旧图片
            File delFile=new File(path+notice.getNoticepicture().substring(5));
            if (delFile.exists()){
                delFile.delete();
            }
            if(notice.getNoticetype().equals("6")){
                out.print("<script>alert('删除成功！');window.location.href='/bk/notice/toaddlisttupian';</script>");
            }else{
                out.print("<script>alert('删除成功！');window.location.href='/bk/notice/notlists';</script>");
            }
        }else{
            if(notice.getNoticetype().equals("6")){
                out.print("<script>alert('删除失败！');window.location.href='/bk/notice/toaddlisttupian';</script>");
            }else{
                out.print("<script>alert('删除失败！');window.location.href='/bk/notice/notlists';</script>");
            }
        }
        out.flush();
        out.close();
    }

    @RequestMapping(value = "/notadd")
    public void notadd(Notice notice, @RequestParam(value = "ufile", required = true) MultipartFile file, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException{
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        if (file.getSize() != 0) {
            File directory = new File("src/main/resources/static/file");
            String path = directory.getCanonicalPath();// 获得上传的路径
            String fileName = file.getOriginalFilename();// 获得上传的文件名

            File targetFile = new File(path, fileName);// 创建上传到服务器的文件对象
            try {
                //存储新图片
                file.transferTo(targetFile);
                //设置实体值
                String imgUrl = request.getContextPath() + "/file/" + fileName;
                notice.setNoticepicture(imgUrl);
                notice.setNoticelasttime(new Date());
                Employee employee=(Employee)session.getAttribute("loginEmp");
                notice.setNoticelastmodifier(employee.getEid());

                boolean result=noticeService.save(notice);
                if (result){
                    if(notice.getNoticetype().equals("6")){
                        out.print("<script>alert('添加成功！');window.location.href='/bk/notice/toaddlisttupian';</script>");
                    }else{
                        out.print("<script>alert('添加成功！');window.location.href='/bk/notice/notlists';</script>");
                    }
                }else{
                    if(notice.getNoticetype().equals("6")){
                        out.print("<script>alert('添加失败！');window.location.href='/bk/notice/addtupian';</script>");
                    }else{
                        out.print("<script>alert('添加失败！');window.location.href='/bk/notice/toadd';</script>");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            out.print("<script>alert('添加失败，请检查图片是否存在异常！');window.location.href='/bk/notice/toadd';</script>");
        }
        out.flush();
        out.close();
    }

    @RequestMapping("/toadd")
    public String toAdd() {
        return "view/noticeadd";
    }

    @RequestMapping(value = "/notupd")
    public void notupd(Notice notice, @RequestParam(value = "ufile", required = true) MultipartFile file, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException{
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        if (file.getSize() != 0) {
            File directory = new File("src/main/resources/static/file");
            String path = directory.getCanonicalPath();// 获得上传的路径
            String fileName = file.getOriginalFilename();// 获得上传的文件名

            File targetFile = new File(path, fileName);// 创建上传到服务器的文件对象
            try {
                //删除旧图片
                String oldFileName=notice.getNoticepicture();
                File delFile=new File(path+oldFileName.substring(5));
                if (delFile.exists()){
                    delFile.delete();
                }

                //存储新图片
                file.transferTo(targetFile);
                //设置实体值
                String imgUrl = request.getContextPath() + "/file/" + fileName;
                notice.setNoticepicture(imgUrl);
                notice.setNoticelasttime(new Date());
                Employee employee=(Employee)session.getAttribute("loginEmp");
                notice.setNoticelastmodifier(employee.getEid());

                boolean result=noticeService.updateById(notice);
                if (result){
                    if(notice.getNoticetype().equals("6")){
                        out.print("<script>alert('修改成功！');window.location.href='/bk/notice/toaddlisttupian';</script>");
                    }else{
                        out.print("<script>alert('修改成功！');window.location.href='/bk/notice/notlists';</script>");
                    }
                }else{
                    if(notice.getNoticetype().equals("6")){
                        out.print("<script>alert('修改失败！');window.location.href='/bk/notice/toaddlisttupian';</script>");
                    }else{
                        out.print("<script>alert('修改失败！');window.location.href='/bk/notice/notlists';</script>");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            if(notice.getNoticetype().equals("6")){
                out.print("<script>alert('修改失败，请检查图片是否存在异常！');window.location.href='/bk/notice/toaddlisttupian';</script>");
            }else{
                out.print("<script>alert('修改失败，请检查图片是否存在异常！');window.location.href='/bk/notice/notlists';</script>");
            }
        }
        out.flush();
        out.close();
    }

    @RequestMapping(value = "/sgetno")
    public String sgetno(Integer ids,Model model){
        Notice notice=noticeService.getById(ids);
        model.addAttribute("nots",notice);
        return "view/noticeupdate";
    }

    @RequestMapping(value = "/notlists")
    public String notlists(Integer current, String ids, Model model){
        QueryWrapper<Notice> queryWrapper=new QueryWrapper<>();
        queryWrapper.ne("noticetype","6");
        if (ids!=null && ids!="" && !ids.equals("0")){
            queryWrapper.eq("noticetype",ids);
        }
        queryWrapper.orderByDesc("noticelasttime");
        if (current==null){
            current=1;
        }
        Page<Notice> page=new Page<>(current,5);
        IPage<Notice> iPage=noticeService.page(page,queryWrapper);
        List<Notice> noticeList=iPage.getRecords();
        if (current>iPage.getPages()){
            page=new Page<>(1,5);
            iPage=noticeService.page(page,queryWrapper);
            noticeList=iPage.getRecords();
        }

        model.addAttribute("list",noticeList);
        model.addAttribute("page",page);
        model.addAttribute("type",ids);
        return "view/noticeaddlist";
    }
}

