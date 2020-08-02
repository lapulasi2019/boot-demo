package com.gaodun.bootshiro.shiro;

import com.gaodun.bootshiro.entity.User;
import com.gaodun.bootshiro.service.Impl.UserServiceImpl;
import com.gaodun.bootshiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;


    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authenticationInfo = new SimpleAuthorizationInfo();
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        User dbUser = userService.findById(user.getId());
        authenticationInfo.addStringPermission(dbUser.getPerms());
        return authenticationInfo;
    }

    /**
     * 执行认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userService.findByName(token.getUsername());
        if (user == null) {
            return null;
        }
        //第一个参数：返回login方法的数据
        return new SimpleAuthenticationInfo(user, user.getPassword(), "");
    }
}
