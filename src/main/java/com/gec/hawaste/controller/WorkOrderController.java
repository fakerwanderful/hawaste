package com.gec.hawaste.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gec.hawaste.domain.WorkOrderDetailDo;
import com.gec.hawaste.domain.WorkOrderDo;
import com.gec.hawaste.service.IWorkOrderService;
import com.gec.hawaste.utils.PageInfo;
import com.gec.hawaste.utils.ResultBean;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author gec
 * @since 2022-08-15
 */
@RestController
@RequestMapping("/manager/work")
public class WorkOrderController {
    @Autowired
    private IWorkOrderService workOrderService;

    @GetMapping("/select/{current}/{size}")
    @RequiresPermissions("center:select")
    public ResultBean<Page> select(@PathVariable Integer current,
                                   @PathVariable Integer size,
                                   @RequestParam Map<String, Object> params) {
        PageInfo<WorkOrderDo> pageInfo = (PageInfo<WorkOrderDo>) workOrderService
                .selectByCondition(new PageInfo<WorkOrderDo>(current, size), params);
        pageInfo.setNavigatePage(); // 设置分页导航栏数据
        return ResultBean.ok(pageInfo);
    }
    //13637952276
    @GetMapping("/selectOne/{oid}")
    @RequiresPermissions("work:detail")
    public ResultBean selectOne(@PathVariable Long oid) {
        WorkOrderDetailDo workOrderDetailDo = workOrderService.selectDetailById(oid);
        return ResultBean.ok(workOrderDetailDo);
    }
}
