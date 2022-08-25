package com.gec.hawaste.mapper;

import com.gec.hawaste.entity.SysOffice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 机构表 Mapper 接口
 * </p>
 *
 * @author gec
 * @since 2022-08-15
 */
public interface SysOfficeMapper extends BaseMapper<SysOffice> {
    @Select("SELECT\n" +
            "\tsof.id,\n" +
            "\tsof.parent_id,\n" +
            "\tsof.name,\n" +
            "\tsof.icon\n" +
            "FROM\n" +
            "\tsys_role sr,\n" +
            "\tsys_role_office sro,\n" +
            "\tsys_office sof\n" +
            "WHERE\n" +
            "\tsro.role_id=#{rid}\n" +
            "\tAND sr.del_flag=0\n" +
            "\tAND sro.del_flag=0\n" +
            "\tAND sof.del_flag=0\n" +
            "\tAND sr.id=sro.role_id\n" +
            "\tAND sro.office_id=sof.id")
    List<SysOffice> selectByRid(long rid);
}
