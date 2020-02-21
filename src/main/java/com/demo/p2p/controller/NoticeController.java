package com.demo.p2p.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.p2p.entity.Notice;
import com.demo.p2p.service.NoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gzd
 * @since 2020-02-13
 */
@Controller
@RequestMapping("/notice")
public class NoticeController {
    /**
     * 网站公告
     * @return
     */
    @RequestMapping(value = "/wzgg")
    public String wzgg(Model model){
        QueryWrapper<Notice> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("noticetype",1);
        List<Notice>list = noticeService.select1(queryWrapper);
        int sum = noticeService.select1(queryWrapper).size();
        System.out.println(sum+"////////////////////////");
        model.addAttribute("list",list);
        return "inform";
    }
    /**
     * 媒体报道
     * @return
     */
    @Resource
    private NoticeService noticeService;
    @RequestMapping(value = "/mtbd")
    public String mtbd(Model model){
        QueryWrapper<Notice> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("noticetype",2);
        List<Notice> list = noticeService.select1(queryWrapper);
        model.addAttribute("list",list);
        return "reportlist";
    }
    /**
     * 媒体报道详情
     * @return
     */
    @RequestMapping(value = "/mtbdxq",method = RequestMethod.GET)
    public String mtbdxq(HttpServletRequest request,Model model){
        QueryWrapper<Notice> queryWrapper=new QueryWrapper<>();
        String ids = request.getParameter("ids");
        int id = Integer.parseInt(ids);
        queryWrapper.eq("noticeid",id);
        List<Notice>list=noticeService.select1(queryWrapper);
        model.addAttribute("list",list);
        return "reportsel";
    }
    /**
     * 公告详情
     * @return
     */
    @RequestMapping(value = "/xiangqing",method = RequestMethod.GET)
    public String xiangqing(HttpServletRequest request,Model model){
        QueryWrapper<Notice> queryWrapper=new QueryWrapper<>();
        String ids = request.getParameter("ids");
        int id = Integer.parseInt(ids);
        queryWrapper.eq("noticeid",id);
        List<Notice>list=noticeService.select1(queryWrapper);
        model.addAttribute("list",list);
        return "informsel";
    }

    /**
     * 管理团队
     * @return
     */
    @RequestMapping(value = "/gltd")
    public String gltd(Model model){
        QueryWrapper<Notice> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("noticetype",3);
        List<Notice> list = noticeService.select1(queryWrapper);
        model.addAttribute("list",list);
        return "informgltd";
    }
    /**
     * 团队风采
     * @return
     */
    @RequestMapping(value = "/tdfc")
    public String tdfc(Model model){
        QueryWrapper<Notice> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("noticetype",5);
        List<Notice> list = noticeService.select1(queryWrapper);
        model.addAttribute("list",list);
        return "informtdfc";
    }

    /**
     * 合作伙伴
     * @return
     */
    @RequestMapping(value = "/hzhb")
    public String hzhb(Model model){
        QueryWrapper<Notice> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("noticetype",4);
        List<Notice> list = noticeService.select1(queryWrapper);
        model.addAttribute("list",list);
        return "informhzhb";
    }

}

