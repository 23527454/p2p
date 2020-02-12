package com.demo.p2p.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.p2p.entity.Users;
import com.demo.p2p.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

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


    @PostMapping(value = "/resetPwd")
    @ResponseBody
    public Object resetPwd(Integer uid,String password){
        Map<String,Object> map=new HashMap<>();
        boolean result=usersService.resetPwd(uid,password);
        map.put("result",result);
        return map;
    }

    @PostMapping(value = "/checkPhone")
    @ResponseBody
    public Object checkPhone(String phone){
        Map<String,Object> map=new HashMap<String,Object>();
        QueryWrapper<Users> queryWrapper=new QueryWrapper<>();
        if(phone!=null){
            queryWrapper.eq("uphonenumber",phone);
        }
        Users users=usersService.checkUsersByCondition(queryWrapper);
        if (users!=null){
            map.put("status",true);
            map.put("message","存在");
        }else{
            map.put("status",false);
            map.put("message","手机号不匹配！");
        }
        return map;
    }

    @PostMapping(value = "/checkUnickname")
    @ResponseBody
    public Object checkUnickname(String username){
        Map<String,Object> map=new HashMap<String,Object>();
        QueryWrapper<Users> queryWrapper=new QueryWrapper<>();
        if(username!=null){
            queryWrapper.eq("unickname",username);
        }
        Users users=usersService.checkUsersByCondition(queryWrapper);
        if (users!=null){
            map.put("status",true);
            map.put("users",users);
            map.put("message","存在");
        }else{
            map.put("status",false);
            map.put("message","不存在该用户！");
        }
        return map;
    }


    @PostMapping(value = "/login")
    @ResponseBody
    public Object login(String username, String password, HttpServletRequest request){
        QueryWrapper<Users> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("unickname",username).eq("upassword",password);
        Users users=usersService.login(queryWrapper);
        Map<String,Object> map=new HashMap<String,Object>();
        if (users!=null){
            HttpSession session=request.getSession();
            session.setAttribute("loginUser",users);
            map.put("status",true);
            map.put("message","登录成功！");
        }else{
            map.put("status",false);
            map.put("message","登录失败，请检查用户名或密码是否正确！");
        }
        return map;
    }

}

