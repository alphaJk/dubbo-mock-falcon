package com.jk.mock.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: jk
 * Date: 2023/2/22
 * Time: 16:06
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Data
@TableName("mock_config")
public class MockInfo  implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("function_name")
    private String functionName;

    @TableField("interface_name")
    private String interfaceName;

    @TableField("dubbo_group")
    private String dubboGroup;

    private String version;

    private String response;

    @TableField("parameter_types")
    private String parameterTypes;
}
