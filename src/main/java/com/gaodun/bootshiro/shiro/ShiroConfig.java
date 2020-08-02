package com.gaodun.bootshiro.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        /**
         * shiro过滤器
         * anon: 无需认证
         * authc: 需认证
         * user: 需使用rememberme功能实现访问
         * perms: 需得到资源权限实现访问
         * role: 需得到角色权限实现访问
         */
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/add", "authc");
        filterMap.put("/update", "authc");
        filterMap.put("/greeting", "anon");
        filterMap.put("/login", "anon");

        //未授权拦截
        filterMap.put("/add", "perms[user:add]");
        filterMap.put("/update", "perms[user:update]");
        //未授权跳转页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getSecurityManager(@Qualifier("myRealm") MyRealm myRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm);
        return securityManager;
    }

    @Bean(name = "myRealm")
    public MyRealm getMyRealm() {
        return new MyRealm();
    }

    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }
}
