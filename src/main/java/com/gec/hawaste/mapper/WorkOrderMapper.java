package com.gec.hawaste.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gec.hawaste.domain.WorkOrderDetailDo;
import com.gec.hawaste.domain.WorkOrderDo;
import com.gec.hawaste.entity.WorkOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author gec
 * @since 2022-08-15
 */
public interface WorkOrderMapper extends BaseMapper<WorkOrder> {

    @Select("SELECT\n" +
            "\two.*,\n" +
            "\tcu.name create_user_name,\n" +
            "\tco.name create_office_name,\n" +
            "\ttu.name transport_user_name,\n" +
            "\tru.name recipient_user_name\n" +
            "FROM\n" +
            "\twork_order wo\n" +
            "\tLEFT JOIN sys_user cu\n" +
            "\tON wo.create_user_id=cu.id\n" +
            "\tLEFT JOIN sys_office co\n" +
            "\tON cu.office_id=co.id\n" +
            "\tLEFT JOIN sys_user tu\n" +
            "\tON wo.transport_user_id = tu.id\n" +
            "\tLEFT JOIN sys_office `to`\n" +
            "\tON tu.office_id=`to`.id\n" +
            "\tLEFT JOIN sys_user ru\n" +
            "\tON wo.recipient_user_id=ru.id\n" +
            "\tLEFT JOIN sys_office ro\n" +
            "\tON ru.office_id=ro.id\n" +
            "${ew.customSqlSegment}")
    IPage<WorkOrderDo> selectByCondition(IPage<WorkOrderDo> page, Wrapper ew);

    @Select("SELECT\n" +
            "\two.*,\n" +
            "\tcu.name create_user_name,\n" +
            "\tcu.phone create_user_phone,\n" +
            "\tco.name create_office_name,\n" +
            "\ttu.name transport_user_name,\n" +
            "\ttu.phone transport_user_phone,\n" +
            "\t`to`.name transport_office_name,\n" +
            "\tru.name recipient_user_name,\n" +
            "\tru.phone recipient_user_phone,\n" +
            "\tro.name recipient_office_name\n" +
            "FROM\n" +
            "\twork_order wo\n" +
            "\tLEFT JOIN sys_user cu\n" +
            "\tON wo.create_user_id=cu.id\n" +
            "\tLEFT JOIN sys_office co\n" +
            "\tON cu.office_id=co.id\n" +
            "\tLEFT JOIN sys_user tu\n" +
            "\tON wo.transport_user_id = tu.id\n" +
            "\tLEFT JOIN sys_office `to`\n" +
            "\tON tu.office_id=`to`.id\n" +
            "\tLEFT JOIN sys_user ru\n" +
            "\tON wo.recipient_user_id=ru.id\n" +
            "\tLEFT JOIN sys_office ro\n" +
            "\tON ru.office_id=ro.id\n" +
            "WHERE\n" +
            "\two.del_flag=0\n" +
            "\tAND wo.id=#{oid}")
    WorkOrderDetailDo selectDetailById(Serializable oid);
}
