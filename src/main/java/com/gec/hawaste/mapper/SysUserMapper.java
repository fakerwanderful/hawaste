package com.gec.hawaste.mapper;

import com.gec.hawaste.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author gec
 * @since 2022-08-15
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    @Select("SELECT\n" +
            "\tsu.*\n" +
            "FROM\n" +
            "\tsys_user_role sur,\n" +
            "\tsys_user su\n" +
            "WHERE\n" +
            "\tsur.del_flag=0\n" +
            "\tAND su.del_flag=0\n" +
            "\tAND sur.role_id=#{rid}\n" +
            "\tAND sur.user_id=su.id")
    List<SysUser> selectByRid(long rid);

    @Select("SELECT\n" +
            "\t*\n" +
            "FROM\n" +
            "\tsys_user\n" +
            "WHERE\n" +
            "\toffice_id=#{oid}\n" +
            "\tAND id NOT IN(\n" +
            "SELECT\n" +
            "\tsu.id\n" +
            "FROM\n" +
            "\tsys_user_role sur,\n" +
            "\tsys_user su\n" +
            "WHERE\n" +
            "\tsur.del_flag=0\n" +
            "\tAND su.del_flag=0\n" +
            "\tAND sur.role_id=#{rid}\n" +
            "\tAND sur.user_id=su.id\n" +
            "\t)")
    List<SysUser> selectNoRole(@Param("rid") long rid, @Param("oid") long oid);
}
