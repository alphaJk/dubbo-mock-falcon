package com.jk.mock.core.code.util;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttributeModel implements Serializable {
    private Integer id;
    private String value;
    private String resValue;
    private String desc;
    private Integer priority;
    private Date createTime;
    private Date modifiedTime;
}