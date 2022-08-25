package com.gec.hawaste.domain;

import com.gec.hawaste.entity.Detail;
import com.gec.hawaste.entity.Transfer;
import lombok.Data;

@Data
public class TransferDo extends Transfer {
    private String userName;
    private String userPhone;
}
