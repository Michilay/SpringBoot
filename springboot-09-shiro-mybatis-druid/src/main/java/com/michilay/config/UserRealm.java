package com.michilay.config;

import com.michilay.pojo.User;
import com.michilay.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

//自定义Realm继承authorizingRealm
public class UserRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;
    //    授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        拿到当前登录用户对象
        Subject subject = SecurityUtils.getSubject();
//        认证中        return new SimpleAuthenticationInfo(userByName,userByName.getUserPwd(),"");得到了userByName这个user
//        这里就可以拿到user
        User currentUser = (User) subject.getPrincipal();
//        设置权限
        info.addStringPermission(currentUser.getPerms());
        return info;
    }
//   认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了认证");
//        连接真实的数据库
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        User userByName = userService.findUserByName(userToken.getUsername());
        if (userByName==null){
            return null;
        }
//        密码加密：md5  md5盐值加密：
//        密码认证，shiro来做
        return new SimpleAuthenticationInfo(userByName,userByName.getUserPwd(),"");
    }
}
