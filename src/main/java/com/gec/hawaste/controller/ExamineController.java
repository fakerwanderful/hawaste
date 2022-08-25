package com.gec.hawaste.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gec.hawaste.domain.ExamineDo;
import com.gec.hawaste.service.IExamineService;
import com.gec.hawaste.utils.PageInfo;
import com.gec.hawaste.utils.ResultBean;
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
@RequestMapping("/manager/examine")
public class ExamineController {
    @Autowired
    private IExamineService examineService;

    /*
     * 业务管理-考核管理
     * */
    @GetMapping("/select/{current}/{size}")
    public ResultBean<Page> select(@PathVariable Integer current,
                                   @PathVariable Integer size,
                                   @RequestParam Map<String, Object> params){
        PageInfo<ExamineDo> page = (PageInfo<ExamineDo>)
                examineService.selectByCondition(new PageInfo<>(current, size), params);
        //设置分页导航栏的数据
        page.setNavigatePage();

        return ResultBean.ok(page);
    }
}
