package com.demo.p2p.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.p2p.entity.Users;
import com.demo.p2p.service.UsersService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

public class MyShiroRealm extends AuthorizingRealm {
    @Resource
    private UsersService usersService;

    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。AuthenticationToken*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        System.out.println("1：********************************身份认证：MyShiroRealm.doGetAuthenticationInfo()");
        //获取用户输入的信息
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        String password = new String(token.getPassword());

        QueryWrapper<Users> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("unickname",username).eq("upassword",password);
        Users users=usersService.login(queryWrapper);
        if (users == null) {
            throw new UnknownAccountException("登录失败！");
        }
        //存入session中
        SecurityUtils.getSubject().getSession().setAttribute("loginUser", users);

        //认证信息
        SimpleAuthenticationInfo authorizationInfo = new SimpleAuthenticationInfo(users,users.getUpassword(),users.getUnickname());

        return authorizationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("2：权限认证-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();//授权信息
        Users loginUser = (Users) SecurityUtils.getSubject().getSession().getAttribute("loginUser");
        authorizationInfo.addStringPermission("login");
        return authorizationInfo;
    }
}
