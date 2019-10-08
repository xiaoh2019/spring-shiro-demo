package com.cyzs.shirodemo.realms;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author xiaoh
 * @create 2019-09-03 12:26
 */
public class ShiroRealm extends AuthorizingRealm {
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken= (UsernamePasswordToken) authenticationToken;
        String username=usernamePasswordToken.getUsername();
        char[] password = usernamePasswordToken.getPassword();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i <password.length ; i++) {
            stringBuffer.append(password[i]);
        }
        System.out.println("---------"+stringBuffer);
        System.out.println("username ===="+usernamePasswordToken.getUsername());
        //从数据库查出用户信息  username status
        //   User user = Userservice.findUserByUsername();
        if ("unknow".equals(username)){
            throw new UnknownAccountException("用户不存在！");
        }
        if("lock".equals(username)){
            throw new LockedAccountException("账户被锁定！");
        }
        //SimpleAuthenticationInfo(Object principal, Object credentials, String realmName)
        //用户信息
        Object principal = username;
        //数据库密码
        Object credentials = "fc1709d0a95a6be30bc5926fdb7f22f4";
        //当前类的名字
        String realmName=getName();
        //如果数据库的密码加了盐值，这里要说明
        //ByteSource credentialsSalt = ByteSource.Util.bytes(username);

        // 这是加盐值的构造器SimpleAuthenticationInfo(Object principal, Object hashedCredentials, ByteSource credentialsSalt, String realmName)
        //返回一个AuthenticationInfo，这个对象有数据库的信息组成，信息的比对由另外的处理
        AuthenticationInfo info=new SimpleAuthenticationInfo(principal,credentials,realmName);
        return info;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //1.获取用户信息
        Object primaryPrincipal = principalCollection.getPrimaryPrincipal();
        System.out.println("==========>"+primaryPrincipal);
        //2.根据信息赋予权限
        Set<String> roles = new HashSet();
        //roles.add("admin");
        roles.add("general");
        AuthorizationInfo info = new SimpleAuthorizationInfo(roles);
        return info;
    }
}
