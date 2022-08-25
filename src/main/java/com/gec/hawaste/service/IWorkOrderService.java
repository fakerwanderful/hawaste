package com.gec.hawaste.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gec.hawaste.domain.WorkOrderDetailDo;
import com.gec.hawaste.domain.WorkOrderDo;
import com.gec.hawaste.entity.WorkOrder;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gec
 * @since 2022-08-15
 */
public interface IWorkOrderService extends IService<WorkOrder> {

    public IPage<WorkOrderDo> selectByCondition(IPage<WorkOrderDo> page, Map<String,Object> params);

    public WorkOrderDetailDo selectDetailById(Serializable oid);
}
