package com.yangfan.shiro;

import com.yangfan.dao.PermissionMapper;
import com.yangfan.dao.UserMapper;
import com.yangfan.domain.Permission;
import com.yangfan.domain.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * @Author 杨小柒丶
 * @Date 2019/9/28
 * @Time 19:32
 */
public class MyRealm extends AuthorizingRealm {
    @Resource
    private UserMapper userMapper;
    @Resource
    private PermissionMapper permissionMapper;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username= (String) principals.getPrimaryPrincipal();
        List<Permission> list=permissionMapper.selectPermissionByUsername(username);
        Collection set=new HashSet();
        for (Permission permission:list){
            set.add(permission.getPerName());
        }
        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermissions(set);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();  //得到用户输入的username
        User user = userMapper.selectByName(username);
        SimpleAuthenticationInfo simpleAuthenticationInfo=new SimpleAuthenticationInfo(user.getLoginName(),user.getPassword(),ByteSource.Util.bytes(user.getLoginName()),getName());
        return simpleAuthenticationInfo;
    }
}
