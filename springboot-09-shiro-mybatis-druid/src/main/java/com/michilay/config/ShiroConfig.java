package com.michilay.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;

@Configuration
public class ShiroConfig {

//    1.ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
//        设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
//        anon:无需认证就可以访问，authc:必须认证了才能访问，user:必须拥有记住我功能才能用
//        perms:拥有对某个资源的权限才能访问，role:拥有某个角色权限才能访问
        HashMap<String, String> filterMap = new LinkedHashMap<>();
//        正常情况下，没有授权会到未授权页面
//        只有在包含了add的字符串
        filterMap.put("/user/add","perms[user:add]");
        filterMap.put("/user/update","perms[user:update]");
        bean.setFilterChainDefinitionMap(filterMap);
//        设置登录请求
        bean.setLoginUrl("/toLogin");
//        设置未授权请求
        bean.setUnauthorizedUrl("/noauth");
        return bean;
    }
//    2.DefaultWebSecurityManager
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        关联realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }
//    1.创建realm对象,需要自定义
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }


//    整合ShiroDialect
    @Bean
    public ShiroDialect getShiroDialect(){
        return  new ShiroDialect();
    }
}
