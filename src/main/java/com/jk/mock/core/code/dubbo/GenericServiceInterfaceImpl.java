package com.jk.mock.core.code.dubbo;

import com.alibaba.fastjson2.JSON;
import com.google.protobuf.ServiceException;
import com.jk.mock.core.code.Util;
import com.jk.mock.core.code.dubbo.config.DubboMockProperties;
import com.jk.mock.core.code.dubbo.config.MockInvocation;
import com.jk.mock.dao.MockInfoDao;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.dubbo.rpc.service.GenericException;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@Slf4j
@Service
public class GenericServiceInterfaceImpl implements GenericService {

    @Resource
    private MockInfoDao mockInfoDao;

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
        return JSON.parseObject(mockInfoDao.getOneMockInfo(interfaceName,method,params).getResponse());
    }
}
