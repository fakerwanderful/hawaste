package com.gec.hawaste.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gec.hawaste.domain.DetailDo;
import com.gec.hawaste.domain.TransferDo;
import com.gec.hawaste.domain.WorkOrderDetailDo;
import com.gec.hawaste.domain.WorkOrderDo;
import com.gec.hawaste.entity.WorkOrder;
import com.gec.hawaste.mapper.DetailMapper;
import com.gec.hawaste.mapper.TransferMapper;
import com.gec.hawaste.mapper.WorkOrderMapper;
import com.gec.hawaste.service.IWorkOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author gec
 * @since 2022-08-15
 */
@Service
public class WorkOrderServiceImpl extends ServiceImpl<WorkOrderMapper, WorkOrder> implements IWorkOrderService {

    @Autowired
    private TransferMapper transferMapper;

    @Autowired
    private DetailMapper detailMapper;

    @Override
    public IPage<WorkOrderDo> selectByCondition(IPage<WorkOrderDo> page, Map<String, Object> params) {
        QueryWrapper<WorkOrderDo> wrapper = new QueryWrapper<>();
        wrapper.eq("wo.del_flag", 0)
                .eq(params.containsKey("status") && !ObjectUtils.isEmpty(params.get("status")), "wo.STATUS", params.get("status"))
                .ge(params.containsKey("startDate") && !ObjectUtils.isEmpty(params.get("startDate")), "wo.create_date", params.get("startDate"))
                .le(params.containsKey("endDate") && !ObjectUtils.isEmpty(params.get("endDate")), "wo.create_date", params.get("endDate"))
                //嵌套语句处理
                .and(params.containsKey("officeId") && !ObjectUtils.isEmpty(params.get("officeId")),
                        qw -> qw.eq("co.id", params.get("officeId"))
                                .or()
                                .eq("to.id", params.get("officeId"))
                                .or()
                                .eq("ro.id", params.get("officeId")));

        return baseMapper.selectByCondition(page, wrapper);
    }

    @Override
    public WorkOrderDetailDo selectDetailById(Serializable oid) {
        //工单和用户、用户公司的基本信息
        WorkOrderDetailDo workOrderDetailDo = baseMapper.selectDetailById(oid);

        //查出详单记录
        List<DetailDo> details = detailMapper.selectByOrderId(oid);
        workOrderDetailDo.setDetails(details);
        //查出转运记录
        List<TransferDo> transfers = transferMapper.selectByOrderId(oid);
        workOrderDetailDo.setTransfers(transfers);

        return workOrderDetailDo;
    }
}
