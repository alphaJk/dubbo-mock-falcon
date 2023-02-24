package com.jk.mock.core.code.dubbo.config;


import com.jk.mock.dao.MockInfoDao;
import com.jk.mock.entity.MockInfo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Slf4j
@Data
@Component
public class DubboMockProperties {

    private Map<String, String> server;

    @Resource
    private MockInfoDao mockInfoDao;

    /**
     * key = interfaceName,functionName,parameterTypes
     * value = mockInvocation
     */
    private Map<String, MockInvocation> invocations = new HashMap<>();

    public DubboMockProperties(MockInfoDao mockInfoDao){
        dataInit(mockInfoDao);
    }

    public void dbReload(){
        invocations.clear();
        dataInit(mockInfoDao);
    }

    private void dataInit(MockInfoDao mockInfoDao){
        List<MockInfo> list = mockInfoDao.getAllMockInfo();
        list.forEach( e->{
            String interfaceName = e.getInterfaceName();
            String functionName = e.getFunctionName();
            String group = e.getDubboGroup();
            String version = e.getVersion();
            String resp = e.getResponse();
            String[] parameterTypes = e.getParameterTypes().split(",");
            List<String> strList = Arrays.asList(parameterTypes);
            String param = String.join(",",strList);
            MockInvocation mockInvocation = MockInvocation.builder()
                    .interfaceName(interfaceName)
                    .methodName(functionName)
                    .parameterTypes(strList)
                    .group(group)
                    .version(version)
                    .response(resp)
                    .build();
            String key = String.format("%s,%s,%s",interfaceName,functionName,param);
            log.info("init dubbo mock info key : {}",key);
            invocations.put(key,mockInvocation);
        });
    }
}
