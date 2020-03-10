package com.demo.p2p.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.p2p.ht.entity.Employee;
import com.demo.p2p.ht.service.Bk_EmployeeService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

public class MyShiroRealm extends AuthorizingRealm {
    @Resource
    private Bk_EmployeeService employeeService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    //用户登录次数计数  redisKey 前缀
    private String SHIRO_LOGIN_COUNT = "shiro_login_count_";

    //用户登录是否被锁定    一小时 redisKey 前缀
    private String SHIRO_IS_LOCK = "shiro_is_lock_";

    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。AuthenticationToken*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        System.out.println("1：********************************身份认证：MyShiroRealm.doGetAuthenticationInfo()");
        //获取用户输入的信息
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        String password = new String(token.getPassword());

        //访问一次，计数一次
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        opsForValue.increment(SHIRO_LOGIN_COUNT + username, 1);  //每次增加1
        System.out.println(username + "：账号登陆的次数是：" + opsForValue.get(SHIRO_LOGIN_COUNT + username));

        //如果这个账号登陆异常，则在登陆页面提醒。
        if (Integer.parseInt(opsForValue.get(SHIRO_LOGIN_COUNT + username)) >= 3) {
            if ("LOCK".equals(opsForValue.get(SHIRO_IS_LOCK + username))) {
                //计数大于3次，设置用户被锁定一分钟
                throw new DisabledAccountException("由于输入错误次数大于3次，帐号1分钟内已经禁止登录！");
            }
        }
        //实现锁定
        if (Integer.parseInt(opsForValue.get(SHIRO_LOGIN_COUNT + username)) >= 3) {
            opsForValue.set(SHIRO_IS_LOCK + username, "LOCK");  //锁住这个账号，值是LOCK。
            stringRedisTemplate.expire(SHIRO_IS_LOCK + username, 1, TimeUnit.MINUTES);  //expire  变量存活期限
        }

        QueryWrapper<Employee> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("ename",username);
        queryWrapper.eq("epassword",password);
        Employee employee=employeeService.login(queryWrapper);
        if (employee==null){
            throw new UnknownAccountException("登录失败，请检查用户名或密码是否正确！");
        }
        //存入session中
        SecurityUtils.getSubject().getSession().setAttribute("loginEmp", employee);
        //认证信息
        SimpleAuthenticationInfo authorizationInfo = new SimpleAuthenticationInfo(employee,employee.getEpassword(),employee.getEname());

        //清空登录计数
        opsForValue.set(SHIRO_LOGIN_COUNT + username, "0");
        //清空锁
        opsForValue.set(SHIRO_IS_LOCK + username, "");
        return authorizationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("2：权限认证-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();//授权信息
        authorizationInfo.addStringPermission("login");
        return authorizationInfo;
    }
}
