package com.gec.hawaste.domain;

import com.gec.hawaste.entity.Examine;
import lombok.Data;

import java.io.Serializable;

/**
 * Examine的扩展类,Domain Object
 */
@Data
public class ExamineDo extends Examine implements Serializable {
    private String userName;
    private String officeName;
}
