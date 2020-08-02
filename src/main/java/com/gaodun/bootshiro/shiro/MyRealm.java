package com.gaodun.bootshiro.shiro;

import org.apache.catalina.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.StringUtils;

public class MyRealm extends AuthorizingRealm {

    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 执行认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = "shenxb";
        String password = "root";
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        if (!token.getUsername().equals(userName)) {
            return null;
        }
        //第一个参数：返回login方法的数据
        return new SimpleAuthenticationInfo("", password, "");
    }
}
