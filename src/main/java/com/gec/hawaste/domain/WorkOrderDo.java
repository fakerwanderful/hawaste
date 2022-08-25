package com.gec.hawaste.domain;

import com.gec.hawaste.entity.WorkOrder;
import lombok.Data;

/**
 * 扩展
 */
@Data
public class WorkOrderDo extends WorkOrder {
    private String createUserName;
    private String createOfficeName;
    private String transportUserName;
    private String recipientUserName;
}
