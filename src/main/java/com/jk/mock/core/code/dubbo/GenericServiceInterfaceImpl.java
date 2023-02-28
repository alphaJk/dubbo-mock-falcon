package com.jk.mock.core.code.dubbo;

import com.alibaba.fastjson2.JSON;
import com.google.protobuf.ServiceException;
import com.jk.mock.core.code.Util;
import com.jk.mock.core.code.dubbo.config.DubboMockProperties;
import com.jk.mock.core.code.dubbo.config.MockInvocation;
import com.jk.mock.dao.MockInfoDao;
import com.jk.mock.dao.RequestHistoryDao;
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
            throw new ServiceException("{\"code\":60951,\"msg\":\"no provider\"}\n");
        }
        List<String> parameterTypesList = Arrays.asList(parameterTypes);
        String params = String.join(",",parameterTypesList);
        //使用interfaceName,method,params作为唯一key
        String key = String.format("%s,%s,%s",interfaceName,method,params);
        log.info("dubbo request key:{}",key);
        MockInvocation mockInvocation = dubboMockProperties.getInvocations().get(key);
        // 方法存不存在
        if (Objects.isNull(mockInvocation)) {
            throw new ServiceException("{\"code\":60951,\"msg\":\"no provider\"}\n");
        }

        log.info(params);
        List<Object> req = new ArrayList<>(Arrays.asList(args));
        if (parameterTypes.length != args.length){
            throw new ServiceException("{\"code\":60952,\"msg\":\"miss params\"}\n");
        }
        //requestId
        String requestId = UUID.randomUUID().toString().replace("-","");
        //
        String groupName = Util.getUrlGroup();
        try {
            for (int i = 0; i < parameterTypes.length; i++) {
                requestHistoryDao.saveOneRequestInfo(requestId,parameterTypesList.get(i),req.get(i).toString(),groupName);
            }
        }catch (Exception e){
            log.error("insert request info error: {}, {}", e.getMessage(), e);
        }

        return JSON.parseObject(mockInfoDao.getOneMockInfo(interfaceName,method,params).getResponse());
    }
}
