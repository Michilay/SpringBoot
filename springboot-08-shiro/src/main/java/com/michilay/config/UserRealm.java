package com.michilay.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

//自定义Realm继承authorizingRealm
public class UserRealm extends AuthorizingRealm {
//    授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权");
        return null;
    }
//   认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了认证");

//        用户名，密码 从数据中去
        String name="admin";
        String password="123";
        UsernamePasswordToken userToken=(UsernamePasswordToken) token;
        if (!userToken.getUsername().equals(name)){
//            抛出异常UnknownAccountException e
            return null;
        }
//        密码认证，shiro来做
        return new SimpleAuthenticationInfo("",password,"");
    }
}
