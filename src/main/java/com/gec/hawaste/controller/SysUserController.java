package com.gec.hawaste.controller;


import com.gec.hawaste.service.ISysUserService;
import com.gec.hawaste.utils.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author gec
 * @since 2022-08-15
 */
@RestController
@RequestMapping("/manager/user")
public class SysUserController {
    @Autowired
    ISysUserService service;

    @RequestMapping(value = "selectByRid/{rid}")
    public ResultBean selectByRid(@PathVariable("rid") long rid) {
        return ResultBean.ok(service.selectByRid(rid));//统一结果响应
    }

    @RequestMapping("selectNoRole/{rid}/{oid}")
    public ResultBean selectNoRole(@PathVariable long rid, @PathVariable long oid) {
        return ResultBean.ok(service.selectNoRole(rid, oid));
    }
}
