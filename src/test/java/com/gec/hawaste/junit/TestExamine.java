package com.gec.hawaste.junit;

import com.gec.hawaste.domain.ExamineDo;
import com.gec.hawaste.service.IExamineService;
import com.gec.hawaste.utils.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
public class TestExamine {

    @Autowired
    private IExamineService examineService;

    @Test
    public void test() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("officeId", 56);
        params.put("type", 1);
        params.put("name", "人员");
        PageInfo<ExamineDo> page = new PageInfo<>(1, 5);
        PageInfo<ExamineDo> pageInfo = (PageInfo<ExamineDo>) examineService.selectByCondition(page, params);
        pageInfo.getRecords().forEach(examineDo -> {
            System.out.println(examineDo);
        });
    }
}
