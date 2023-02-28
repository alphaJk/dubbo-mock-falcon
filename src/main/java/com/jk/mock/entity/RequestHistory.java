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
 * Date: 2023/2/28
 * Time: 10:27
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Data
@TableName("request_history")
public class RequestHistory implements Serializable{
    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("request_id")
    String requestId;

    @TableField("param_type")
    String paramType;

    @TableField("param_value")
    String paramValue;

    @TableField("group_name")
    String groupName;

    @TableField("create_time")
    String createTime;
}
