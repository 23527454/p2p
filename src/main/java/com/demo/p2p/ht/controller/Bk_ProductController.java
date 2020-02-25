package com.demo.p2p.ht.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.p2p.ht.entity.Biao;
import com.demo.p2p.ht.entity.Investinfo;
import com.demo.p2p.ht.entity.Product;
import com.demo.p2p.ht.entity.Users;
import com.demo.p2p.ht.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gzd
 * @since 2020-02-25
 */
@Controller
@RequestMapping("/bk/product")
public class Bk_ProductController {
    @Resource
    private Bk_ProductService productService;
    @Resource
    private Bk_UsersService usersService;
    @Resource
    private Bk_BiaoService biaoService;
    @Resource
    private Bk_DetailsService detailsService;
    @Resource
    private Bk_InvestinfoService investinfoService;

    @RequestMapping(value = "/save")
    public void save(String xmqx,String jkqx,String fbsj,Product product, @RequestParam(value = "ufile", required = true) MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception{
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
                    out.print("<script>alert('添加成功！');window.location.href='/bk/product/list';</script>");
                }else{
                    out.print("<script>alert('添加失败！');window.location.href='/bk/notice/input';</script>");
                }
            } catch (IOException e) {
                out.print("<script>alert('添加失败！');window.location.href='/bk/notice/input';</script>");
            }
        }else{
            out.print("<script>alert('添加失败，请检查图片是否存在异常！');window.location.href='/bk/product/input';</script>");
        }
        out.flush();
        out.close();
    }

    @RequestMapping(value = "/input")
    public String input(Model model){
        List<Biao> biaos=biaoService.list();
        model.addAttribute("blist",biaos);
        return "view/bk_input_pro";
    }

    @RequestMapping(value = "/delete")
    public void delete(Integer pid, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        Product product=productService.getById(pid);
        boolean result=productService.removeById(pid);
        //删除图片
        File directory = new File("src/main/resources/static/cover");
        String path = directory.getCanonicalPath();// 获得上传的路径
        //删除旧图片
        File delFile=new File(path+product.getPicture().substring(6));
        if (delFile.exists()){
            delFile.delete();
        }
        if (result){
            out.print("<script>alert('删除成功！');window.location.href='/bk/product/list';</script>");
        }else{
            out.print("<script>alert('删除失败！');window.location.href='/bk/product/list';</script>");
        }
        out.flush();
        out.close();
    }

    @RequestMapping(value = "/biaolist")
    public String biaoList(Integer pid,Integer current,Model model){
        if(current==null || current==0){
            current=1;
        }
        QueryWrapper<Investinfo> queryWrapper=new QueryWrapper<>();
        if(pid!=null){
            queryWrapper.eq("brrowid",pid);
        }
        Page<Investinfo> page=new Page<>(current,5);
        IPage<Investinfo> iPage=investinfoService.page(page,queryWrapper);
        List<Investinfo> lists=iPage.getRecords();
        List<Users> us=usersService.list();

        model.addAttribute("pid",pid);
        model.addAttribute("lists",lists);
        model.addAttribute("us",us);
        model.addAttribute("page",page);
        return "view/bk_list_biaos";
    }

    @RequestMapping(value = "/list")
    public String list(Integer current, Model model){
        if(current==null || current==0){
            current=1;
        }
        Page<Product> page=new Page<>(current,5);
        IPage<Product> iPage=productService.page(page);
        List<Product> list=iPage.getRecords();
        for (int i=0;i<list.size();i++){
            Integer uid=list.get(i).getPproduce();
            Integer bid=list.get(i).getPtype();
            Integer did=list.get(i).getDid();
            list.get(i).setUsers(usersService.getById(uid));
            list.get(i).setBiao(biaoService.getById(bid));
            list.get(i).setDetails(detailsService.getById(did));
        }
        model.addAttribute("lists",list);
        model.addAttribute("page",iPage);
        return "view/bk_list_pro";
        /**
         * 使用SQL语句查询
         * SELECT p.id,p.pmoney,p.pincome,p.ptime,b.id AS 'biao.id',b.bname AS 'biao.bname',
         * 	p.pway,p.pcount,p.progress,p.psaveway,p.prateben,p.ppublishtime,p.pname,
         * 	p.ptotalmoney,p.prange,p.puse,p.pstate,p.picture,u.uid AS 'users.uid',
         * 	u.uname AS 'users.uname',p.pdesc,p.psafe
         * FROM product p LEFT JOIN users u ON p.pproduce=u.uid LEFT JOIN biao b ON p.ptype=b.id
         */
    }

}

