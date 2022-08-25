package com.gec.hawaste.mapper;

import com.gec.hawaste.domain.DetailDo;
import com.gec.hawaste.entity.Detail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author gec
 * @since 2022-08-15
 */
public interface DetailMapper extends BaseMapper<Detail> {

    @Select("SELECT\n" +
            "\tde.*,\n" +
            "\twt.code waste_type_code,\n" +
            "\twt.name waste_type_name,\n" +
            "\twa.code waste_code\n" +
            "FROM\n" +
            "\tdetail de,\n" +
            "\twaste_type wt,\n" +
            "\twaste wa\n" +
            "WHERE\n" +
            "\tde.work_order_id=#{oid}\n" +
            "\tAND de.waste_type_id=wt.id\n" +
            "\tAND\tde.waste_id=wa.id")
    List<DetailDo> selectByOrderId(Serializable oid);   //数值类型 string
}
