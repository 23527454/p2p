package com.demo.p2p.config;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 服务器启动就会自动执行下列方法
 */
@Configuration
public class ShiroConfig {

    /**
     * cookie管理对象
     *
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }

    @Bean
    public SimpleCookie rememberMeCookie() {
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //setcookie的httponly属性如果设为true的话，会增加对xss防护的安全系数。它有以下特点：

        //setcookie()的第七个参数
        //设为true后，只能通过http访问，javascript无法访问
        //防止xss读取cookie
        simpleCookie.setHttpOnly(true);
        simpleCookie.setPath("/");
        //<!-- 记住我cookie生效时间,单位秒;-->
        simpleCookie.setMaxAge(30);
        return simpleCookie;
    }

    @Bean
    public SecurityManager securityManager(){
        System.out.println("1:securityManager..............................");
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRememberMeManager(rememberMeManager());
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }
    @Bean
    public MyShiroRealm myShiroRealm(){
        System.out.println("2:myShiroRealm..............................");
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        return myShiroRealm;
    }
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        System.out.println("3:ShiroConfiguration.shiroFilter()：配置权限控制规则");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //进入登录页面
        shiroFilterFactoryBean.setLoginUrl("/backend/toLogin");
        //访问没有授权的资源
        shiroFilterFactoryBean.setUnauthorizedUrl("/sys/403");
        Map<String, String> filtrChainDefinitionMap = new LinkedHashMap<String, String>();
        //不登录可以访问的地址
        //前台跳转
        filtrChainDefinitionMap.put("/sys/**", "anon");
        filtrChainDefinitionMap.put("/backend/toLogin", "anon");
        filtrChainDefinitionMap.put("/backend/rePwd", "anon");
        filtrChainDefinitionMap.put("/backend/login", "anon");
        //前台
        filtrChainDefinitionMap.put("/assets/**", "anon");
        filtrChainDefinitionMap.put("/cover/**", "anon");
        filtrChainDefinitionMap.put("/css/**", "anon");
        filtrChainDefinitionMap.put("/file/**", "anon");
        filtrChainDefinitionMap.put("/images/**", "anon");
        filtrChainDefinitionMap.put("/img/**", "anon");
        filtrChainDefinitionMap.put("/js/**", "anon");
        filtrChainDefinitionMap.put("/picture/**", "anon");
        filtrChainDefinitionMap.put("/script/**", "anon");
        //后台
        filtrChainDefinitionMap.put("/view/assets/**", "anon");
        filtrChainDefinitionMap.put("/view/css/**", "anon");
        filtrChainDefinitionMap.put("/view/img/**", "anon");
        filtrChainDefinitionMap.put("/view/js/**", "anon");
        filtrChainDefinitionMap.put("/view/My97DatePicker/**", "anon");
        filtrChainDefinitionMap.put("/view/Ueditor/**", "anon");
        filtrChainDefinitionMap.put("/view/validate/**", "anon");
        filtrChainDefinitionMap.put("/bk/employee/**","anon");
        //配置退出（记住我状态下，可清除记住我的cookie）
        filtrChainDefinitionMap.put("/backend/loginout", "logout");

        //设置会被拦截的请求
        filtrChainDefinitionMap.put("/backend/**", "perms[login]");
        filtrChainDefinitionMap.put("/bk/**", "perms[login]");

        //所有路径必须授权访问（登录），且必须放在最后
        filtrChainDefinitionMap.put("/", "user");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filtrChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

}
