package com.demo.p2p.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.p2p.entity.Users;
import com.demo.p2p.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gzd
 * @since 2020-02-09
 */
@Controller
@RequestMapping("/users")
public class UsersController {
    @Resource
    private UsersService usersService;

    @PostMapping(value = "/login")
    public String login(String j_username, String password, HttpServletRequest request){
        QueryWrapper<Users> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("unickname",j_username).eq("upassword",password);
        Users users=usersService.login(queryWrapper);
        if (users!=null){
            System.out.println("username:"+users.getUnickname());
            HttpSession session=request.getSession();
            session.setAttribute("loginUser",users);
            return "/个人中心首页";
        }else{
            return "/login";
        }
    }
}

