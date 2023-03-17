package com.jk.mock.core.code.dubbo;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.jk.mock.core.code.util.Util;
import com.jk.mock.core.code.dubbo.config.DubboMockProperties;
import com.jk.mock.core.code.dubbo.config.MockInvocation;
import com.jk.mock.dao.MockInfoDao;
import com.jk.mock.dao.RequestHistoryDao;
import com.jk.mock.entity.MockInfo;
import com.jk.mock.exception.BaseException;
import com.jk.mock.exception.ErrorCode;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.dubbo.rpc.service.GenericException;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;


@Slf4j
@Service
public class GenericServiceInterfaceImpl implements GenericService {

    @Resource
    private MockInfoDao mockInfoDao;

    @Resource
    private RequestHistoryDao requestHistoryDao;

    @Resource
    private DubboMockProperties dubboMockProperties;

    @SneakyThrows
    @Override
    public Object $invoke(String method, String[] parameterTypes, Object[] args) throws GenericException {
        String interfaceName = Util.getUrlInterface();
        log.info("dubbo request interfaceName:{}",interfaceName);
        if (MapUtils.isEmpty(dubboMockProperties.getInvocations())) {
            throw new BaseException(ErrorCode.ERROR_CODE_60951.getCode(),ErrorCode.ERROR_CODE_60951.getMsg());
        }
        List<String> parameterTypesList = Arrays.asList(parameterTypes);
        String params = String.join(",",parameterTypesList);
        //使用interfaceName,method,params作为唯一key
        String key = String.format("%s,%s,%s",interfaceName,method,params);
        log.info("dubbo request key:{}",key);
        MockInvocation mockInvocation = dubboMockProperties.getInvocations().get(key);
        // 方法存不存在
        if (Objects.isNull(mockInvocation)) {
            throw new BaseException(ErrorCode.ERROR_CODE_60951.getCode(),ErrorCode.ERROR_CODE_60951.getMsg());
        }

        log.info(params);
        List<Object> req = new ArrayList<>(Arrays.asList(args));
        if (parameterTypes.length != args.length){
            throw new BaseException(ErrorCode.ERROR_CODE_60952.getCode(),ErrorCode.ERROR_CODE_60952.getMsg());
        }
        //requestId
        String requestId = UUID.randomUUID().toString().replace("-","");

        String groupName = Util.getUrlGroup();
        try {
            for (int i = 0; i < parameterTypes.length; i++) {
                requestHistoryDao.saveOneRequestInfo(requestId,parameterTypesList.get(i), JSON.toJSONString(req.get(i)),groupName);
            }
        }catch (Exception e){
            log.error("insert request info error: {}, {}", e.getMessage(), e);
        }
        MockInfo mockInfo =  mockInfoDao.getOneMockInfo(interfaceName,method,params);
        //业务特殊处理
       if (Objects.equals(method, "getEntity")){
           return Util.canaryReqHandle(mockInfo);
       }
        return JSONObject.parseObject(mockInfo.getResponse());
    }
}
