package com.gec.hawaste.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gec.hawaste.domain.SysRoleDo;
import com.gec.hawaste.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author gec
 * @since 2022-08-15
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    @Select("SELECT\n" +
            "\tsr.*,\n" +
            "\tso.name office_name\n" +
            "FROM\n" +
            "\tsys_role sr,\n" +
            "\tsys_office so\n" +
            "${ew.customSqlSegment}")
    IPage<SysRoleDo> selectByCondition(IPage<SysRoleDo> page, Wrapper ew);

    @Select("SELECT\n" +
            "\tsr.*,\n" +
            "\tso.name office_name\n" +
            "FROM\n" +
            "\tsys_role sr,\n" +
            "\tsys_office so\n" +
            "WHERE\n" +
            "\tsr.del_flag=0\n" +
            "\tAND so.del_flag=0\n" +
            "\tAND sr.office_id=so.id\n" +
            "\tAND sr.id=#{rid}")
    SysRoleDo selectByRid(long rid);

    @Select("SELECT\n" +
            "\tsr.*\n" +
            "FROM\n" +
            "\tsys_user su,\n" +
            "\tsys_user_role sur,\n" +
            "\tsys_role sr\n" +
            "WHERE\n" +
            "\tsu.id=sur.user_id\n" +
            "\tAND sur.role_id=sr.id\n" +
            "\tAND su.id=#{uid}\n" +
            "\tAND su.del_flag=0\n" +
            "\tAND su.del_flag=0\n" +
            "\tAND su.del_flag=0")
    List<SysRole> selectByUid(Long uid);
}
