package com.demo.p2p.ht.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.p2p.ht.entity.Users;
import com.demo.p2p.ht.service.Bk_UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
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
@RequestMapping("/bk/users")
public class Bk_UsersController {
    @Resource
    private Bk_UsersService usersService;

    @RequestMapping(value = "/list")
    public String list(Integer current, String unickname,Model model){
        if (current==null){
            current=1;
        }
        QueryWrapper<Users> queryWrapper=new QueryWrapper<>();
        if(unickname!=null && unickname!=""){
            queryWrapper.like("unickname",unickname);
        }
        Page<Users> page=new Page<>(current,5);
        IPage<Users> iPage=usersService.page(page,queryWrapper);
        List<Users> users=iPage.getRecords();
        int stas=1;
        if(users!=null && users.size()>0){
            stas=0;
        }
        model.addAttribute("ulist",users);
        model.addAttribute("unickname",unickname);
        model.addAttribute("page",page);
        model.addAttribute("stas",stas);
        return "view/bk_userslist";
    }
}

