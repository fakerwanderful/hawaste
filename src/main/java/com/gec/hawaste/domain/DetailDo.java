package com.gec.hawaste.domain;

import com.gec.hawaste.entity.Detail;
import lombok.Data;

@Data
public class DetailDo extends Detail {
    /*
    *  wt.CODE waste_type_code,
   wt.NAME waste_type_name,
   wa.CODE waste_code */
    private String wasteTypeCode;
    private String wasteTypeName;
    private String wasteCode;
}
