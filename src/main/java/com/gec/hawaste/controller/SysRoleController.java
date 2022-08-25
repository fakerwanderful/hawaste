package com.gec.hawaste.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gec.hawaste.domain.SysRoleDo;
import com.gec.hawaste.service.ISysRoleService;
import com.gec.hawaste.utils.PageInfo;
import com.gec.hawaste.utils.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author gec
 * @since 2022-08-15
 */
@RestController
@RequestMapping("/manager/role")
public class SysRoleController {

    @Autowired
    private ISysRoleService sysRoleService;

    @GetMapping("/select/{current}/{size}")
    public ResultBean<Page> select(@PathVariable Integer current,
                                   @PathVariable Integer size,
                                   @RequestParam Map<String, Object> params) {
        PageInfo<SysRoleDo> pageInfo = (PageInfo<SysRoleDo>) sysRoleService.selectByCondition(new PageInfo<SysRoleDo>(current, size), params);
        pageInfo.setNavigatePage();
        return ResultBean.ok(pageInfo);
    }

}
