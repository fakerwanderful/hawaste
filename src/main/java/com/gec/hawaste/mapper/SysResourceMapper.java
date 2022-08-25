package com.gec.hawaste.mapper;

import com.gec.hawaste.domain.SysRoleDo;
import com.gec.hawaste.entity.SysResource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gec
 * @since 2022-08-15
 */
public interface SysResourceMapper extends BaseMapper<SysResource> {

    @Select("SELECT\n" +
            "\tsre.*\n" +
            "FROM\n" +
            "\tsys_role sr,\n" +
            "\tsys_role_resource srr,\n" +
            "\tsys_resource sre\n" +
            "WHERE\n" +
            "\tsrr.role_id=#{rid}\n" +
            "\tAND sr.del_flag=0\n" +
            "\tAND srr.del_flag=0\n" +
            "\tAND sre.del_flag=0\n" +
            "\tAND sr.id=srr.role_id\n" +
            "\tAND srr.resource_id=sre.id")
    List<SysResource> selectByRid(long rid);

    @Select("SELECT DISTINCT\n" +
            "\tsre.*\n" +
            "FROM\n" +
            "\tsys_user sus,\n" +
            "\tsys_user_role sur,\n" +
            "\tsys_role sro,\n" +
            "\tsys_role_resource srr,\n" +
            "\tsys_resource sre\n" +
            "WHERE\n" +
            "\tsus.id=#{uid}\n" +
            "\tAND sus.del_flag=0\n" +
            "\tAND sur.del_flag=0\n" +
            "\tAND sro.del_flag=0\n" +
            "\tAND srr.del_flag=0\n" +
            "\tAND sre.del_flag=0\n" +
            "\tAND sus.id=sur.user_id\n" +
            "\tAND sur.role_id=sro.id\n" +
            "\tAND sro.id=srr.role_id\n" +
            "\tAND srr.resource_id=sre.id")
    List<SysResource> selectByUid(Long uid);
}
