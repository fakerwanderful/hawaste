package com.gec.hawaste.shiro;

import com.gec.hawaste.entity.SysUser;
import com.gec.hawaste.service.ISysResourceService;
import com.gec.hawaste.service.ISysRoleService;
import com.gec.hawaste.service.ISysUserService;
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

import java.util.ArrayList;
import java.util.List;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysRoleService sysRoleService;

    @Autowired
    private ISysResourceService sysResourceService;

    //用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        System.out.println("用户认证：" + username);
        SysUser user = sysUserService.findUserByUsername(username);
        if (user == null) {
            return null;
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                user,
                user.getPassword(),
                ByteSource.Util.bytes(user.getUsername()),
                "MyRealm"
        );
        return info;
    }

    //用户权限
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        SysUser user = (SysUser) principal.getPrimaryPrincipal();
        //角色集合
        List<String> roles = new ArrayList<>();
        //权限集合
        List<String> resourceList = new ArrayList<>();

        //遍历角色列表
        sysRoleService.selectByUid(user.getId()).forEach(sysRole -> {
            if (roles.contains(sysRole.getName())) {
                roles.add(sysRole.getName());
            }
        });

        System.out.println("角色集合:" + roles);

        //遍历权限列表
        sysResourceService.selectByUid(user.getId()).forEach(resource -> {
            if (!resourceList.contains(resource.getPermissionStr())) {
                resourceList.add(resource.getPermissionStr());
            }
        });

        System.out.println("权限集合:" + resourceList);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roles);
        info.addStringPermissions(resourceList);
        return info;
    }


}
