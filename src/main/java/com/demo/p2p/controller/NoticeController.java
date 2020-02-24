package com.demo.p2p.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.p2p.entity.Notice;
import com.demo.p2p.service.NoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Resource
    private NoticeService noticeService;

    /**
     * 网站公告
     * @return
     */
    @RequestMapping(value = "/wzgg")
    public String wzgg(Model model,@RequestParam(value="currpage",required=false)String conent){
        int pagecount = 5;//每页显示行数
        int currpage = 1;//当前行数
        int totalPage = 0;//总页数
        int totalRow = 0;//总行数
        QueryWrapper<Notice> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("noticetype",1);
        totalRow = noticeService.select1(queryWrapper).size();
        System.out.println(totalRow+"dddddddddddddddd");
        //分页
        totalPage = (totalRow + pagecount - 1) / pagecount;
        if(conent!=null&&!"".equals(conent)){
            currpage=Integer.parseInt(conent);
        }
        if(currpage<1){
            currpage=1;
        }
        if(currpage>totalPage){
            currpage=totalPage;
        }
        Integer candp = (currpage - 1) * pagecount;
        Map<String, Object> map=new HashMap<>();
        map.put("pagecount", pagecount);
        map.put("currpage", candp);
        List<Notice>list = noticeService.findDope1(map);
        model.addAttribute("list",list);
        model.addAttribute("pagecount",pagecount);
        model.addAttribute("currpage",currpage);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("totalRow",totalRow);
        return "inform";
    }
    /**
     * 媒体报道
     * @return
     */
    @RequestMapping(value = "/mtbd")
    public String mtbd(Model model,@RequestParam(value="currpage",required=false)String conent){
        int pagecount = 5;//每页显示行数
        int currpage = 1;//当前行数
        int totalPage = 0;//总页数
        int totalRow = 0;//总行数
        QueryWrapper<Notice> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("noticetype",2);
        totalRow = noticeService.select1(queryWrapper).size();
        System.out.println(totalRow+"dddddddddddddddd");
        //分页
        totalPage = (totalRow + pagecount - 1) / pagecount;
        if(conent!=null&&!"".equals(conent)){
            currpage=Integer.parseInt(conent);
        }
        if(currpage<1){
            currpage=1;
        }
        if(currpage>totalPage){
            currpage=totalPage;
        }
        Integer candp = (currpage - 1) * pagecount;
        Map<String, Object> map=new HashMap<>();
        map.put("pagecount", pagecount);
        map.put("currpage", candp);
        List<Notice>list = noticeService.findDope2(map);
        model.addAttribute("list",list);
        model.addAttribute("pagecount",pagecount);
        model.addAttribute("currpage",currpage);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("totalRow",totalRow);
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

