package com.gec.hawaste.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gec.hawaste.entity.AppVersion;
import com.gec.hawaste.service.IAppVersionService;
import com.gec.hawaste.utils.PageInfo;
import com.gec.hawaste.utils.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author gec
 * @since 2022-08-15
 */
@RestController
@RequestMapping("/manager/app")
public class AppVersionController {

    @Autowired
    private IAppVersionService appVersionService;

    @GetMapping("/select")
    public ResultBean<Page> select(@RequestParam("current") Integer current, @RequestParam("size") Integer size) {
        PageInfo<AppVersion> page = appVersionService.page(new PageInfo<AppVersion>(current, size));
        //设置分页对象的导航栏数据
        page.setNavigatePage();
        return ResultBean.ok(page);
    }

    @PostMapping("/saveOrUpdate")
    public ResultBean saveOrUpdate(@RequestBody AppVersion app) {
        System.out.println("app唯一标识" + app.getId());
        appVersionService.saveOrUpdate(app);
        return ResultBean.ok();
    }

    /**
     * 根据唯一表示查询单条记录(编辑时查询)
     * @param id
     * @return
     */
    @GetMapping("selectOne")
    public ResultBean<AppVersion> selectOne(Long id){
        AppVersion app=appVersionService.getById(id);
        System.out.println("[编辑：]"+app);
        return ResultBean.ok(app);
    }

    @GetMapping("doDelete")
    public ResultBean doDelete(Long id){
        appVersionService.removeById(id);
        return ResultBean.ok();
    }
}
