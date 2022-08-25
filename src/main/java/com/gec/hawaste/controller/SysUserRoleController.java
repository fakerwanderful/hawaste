package com.gec.hawaste.controller;


import com.gec.hawaste.service.ISysUserRoleService;
import com.gec.hawaste.utils.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gec
 * @since 2022-08-15
 */
@RestController
@RequestMapping("/manager/role")
public class SysUserRoleController {
    @Autowired
    ISysUserRoleService userRoleService;

    /**
     * 批量删除角色的已选用户
     * 如果参数自动封装成list，也需要转换为long
     * @param rid
     * @param ids
     * @return
     */
    @RequestMapping("deleteBatch")
    public ResultBean deleteBatch(long rid,Long[] ids){
        return ResultBean.ok(userRoleService.deleteBatch(rid,ids));
    }

    /**
     * 角色批量分配用户
     * @param rid
     * @param ids
     * @return
     */
    @RequestMapping("insertBatch")
    public ResultBean insertBatch(long rid,Long[] ids){
        return ResultBean.ok(userRoleService.insertBatch(rid, Arrays.asList(ids)));
    }
}
