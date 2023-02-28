package com.jk.mock.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jk.mock.entity.MockInfo;
import com.jk.mock.entity.RequestHistory;
import com.jk.mock.service.IRequestHistoryService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jk
 * Date: 2023/2/28
 * Time: 11:13
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Repository
public class RequestHistoryDao {

    @Resource
    IRequestHistoryService requestHistoryService;

    //根据group，获取最后一次请求数据
    public RequestHistory getLatestOne(String group){
        QueryWrapper<RequestHistory> wrapper = new QueryWrapper<>();
        wrapper.eq("group_name",group);
        wrapper.orderByDesc("create_time");
        wrapper.last("limit 1");
        return requestHistoryService.getOne(wrapper);
    }

    public void saveOneRequestInfo(String requestId,String paramType,String paramValue,String groupName){
        RequestHistory entity = new RequestHistory();
        entity.setRequestId(requestId);
        entity.setParamType(paramType);
        entity.setParamValue(paramValue);
        entity.setGroupName(groupName);
        requestHistoryService.save(entity);
    }

    public List<RequestHistory> getReqInfoByReqId(String reqId){
        QueryWrapper<RequestHistory> wrapper = new QueryWrapper<>();
        wrapper.eq("request_id",reqId);
        return requestHistoryService.list(wrapper);
    }
}
