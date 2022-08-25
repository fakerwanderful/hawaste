package com.gec.hawaste.mapper;

import com.gec.hawaste.domain.TransferDo;
import com.gec.hawaste.entity.Transfer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gec
 * @since 2022-08-15
 */
public interface TransferMapper extends BaseMapper<Transfer> {

    @Select("SELECT\n" +
            "\ttr.*,\n" +
            "\tsu.name user_name,\n" +
            "\tsu.phone user_phone\n" +
            "FROM\n" +
            "\ttransfer tr,\n" +
            "\tsys_user su\n" +
            "WHERE \n" +
            "\ttr.work_order_id=#{oid}\n" +
            "\tAND tr.oprate_user_id=su.id\n" +
            "ORDER BY\n" +
            "\ttr.create_date DESC")
    List<TransferDo> selectByOrderId(Serializable oid);

}
