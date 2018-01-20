package com.shiro.realm;

import com.shiro.domain.User;
import com.shiro.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义Realm实现
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Override
    public String getName() {
        return "UserRealm";
    }

    //用于认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        //从token中获取身份信息
        String username = (String) token.getPrincipal();
        User user = null;
        try {
            user = userService.getByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (user == null || !username.equals(user.getUsername())) {
            return null;
        }
        //返回认证信息
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username,
                user.getPassword(), ByteSource.Util.bytes(user.getSalt()), this.getName());
        return simpleAuthenticationInfo;
    }

    //用于授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        return null;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
