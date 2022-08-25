package com.gec.hawaste.junit;

import com.gec.hawaste.entity.SysUser;
import com.gec.hawaste.service.ISysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestShiro {

    @Autowired
    private ISysUserService userService;

    @Test
    public void test1() {
        List<SysUser> list = userService.list();
        list.forEach(sysUser -> {
            String password = "111111";
            Md5Hash md5 = new Md5Hash(password, sysUser.getUsername(), 3);
            System.out.println(md5);
            sysUser.setPassword(md5.toString()); //明文设置为密文
            userService.saveOrUpdate(sysUser);
        });
    }

    @Autowired
    DefaultWebSecurityManager securityManager;

    @Test
    public void test2() {
        SecurityUtils.setSecurityManager(securityManager);
        //获取主体
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("13637952276", "111111");
        subject.login(token);  //调用realm

        System.out.println("是否是合法用户：" + subject.isAuthenticated());
        System.out.println("是否有权限 user:select :" + subject.isPermitted("user:select"));

        subject.logout();

        System.out.println("是否是合法用户：" + subject.isAuthenticated());
    }
}
