package com.marveliu.web.shiro.realm;


import com.marveliu.web.domain.vo.AccountVo;
import com.marveliu.web.shiro.provider.AccountProvider;
import com.marveliu.web.shiro.token.PasswordToken;
import com.marveliu.web.util.AESUtil;
import com.marveliu.web.util.MD5Util;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @Author: Marveliu
 * @Date: 2018/9/29 下午2:32
 * @Description: 登录认证realm
 **/

public class PasswordRealm extends AuthorizingRealm {


    private AccountProvider accountProvider;

    /**
     * Realm只支持PasswordToken
     *
     * @return
     */
    @Override
    public Class<?> getAuthenticationTokenClass() {
        return PasswordToken.class;
    }

    /**
     * 这里只需要认证登录，成功之后派发 json web token 授权在那里进行
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (!(authenticationToken instanceof PasswordToken)) return null;

        if (null == authenticationToken.getPrincipal() || null == authenticationToken.getCredentials()) {
            throw new UnknownAccountException();
        }

        String username = authenticationToken.getPrincipal().toString();
        AccountVo account = accountProvider.loadAccount(username);
        PasswordToken passwordToken = (PasswordToken) authenticationToken;
        if (account != null) {
            // 用盐对密码进行MD5加密
            String password = AESUtil.aesDecode(passwordToken.getPassword(), passwordToken.getTokenKey());
            ((PasswordToken) authenticationToken).setPassword(MD5Util.md5((password + account.getSalt())));
            return new SimpleAuthenticationInfo(username, account.getPassword(), getName());
        } else {
            return new SimpleAuthenticationInfo(username, "", getName());
        }

    }

    public void setAccountProvider(AccountProvider accountProvider) {
        this.accountProvider = accountProvider;
    }
}

