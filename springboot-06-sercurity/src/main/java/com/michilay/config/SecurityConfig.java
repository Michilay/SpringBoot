package com.michilay.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    链式编程
//    授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        首页所有人都可以访问,功能页必须要有权限的人才能访问
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");
//        没有权限默认到登录页
        http.formLogin().loginPage("/toLogin").loginProcessingUrl("/login");
//        开启了注销功能,并且注销后跳转到首页
        http.logout().logoutSuccessUrl("/");
//        关闭csrf功能
        http.csrf().disable();
//        开启记住我功能
        http.rememberMe().rememberMeParameter("remember");
    }
//      认证
//    在spring security5.0+新增了很多机密方式
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        从内存中读取
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("michilay").password(new BCryptPasswordEncoder().encode("123")).roles("vip2","vip1")
                .and()
                .withUser("root").password(new BCryptPasswordEncoder().encode("123")).roles("vip1","vip2","vip3")
                .and()
                .withUser("guest").password(new BCryptPasswordEncoder().encode("123")).roles("vip1");
    }
}
