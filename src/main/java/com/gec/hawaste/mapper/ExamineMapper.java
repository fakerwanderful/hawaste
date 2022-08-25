package com.gec.hawaste.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gec.hawaste.entity.Examine;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gec.hawaste.domain.ExamineDo;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author gec
 * @since 2022-08-15
 */
public interface ExamineMapper extends BaseMapper<Examine> {
    //${ew.customSqlSegment}表示动态条件的拼接
    @Select("select ex.*, su.name user_name, so.name office_name " +
            "from examine ex " +
            "join sys_user su on ex.examine_user_id = su.id " +
            "join sys_office so on su.office_id = so.id " +
            "${ew.customSqlSegment}")
    IPage<ExamineDo> selectByCondition(IPage<ExamineDo> page, QueryWrapper<ExamineDo> ew);
}
