package com.jk.mock.core.code.dubbo;

import com.jk.mock.core.code.dubbo.config.DubboMockProperties;
import com.jk.mock.core.code.dubbo.config.MockInvocation;
import com.jk.mock.service.IMockInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.MethodConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.rpc.service.GenericService;
import org.apache.dubbo.spring.boot.autoconfigure.DubboConfigurationProperties;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.*;

/**
 * @author qiumadai
 * @date 2022/8/22/022
 **/
@Slf4j
@Service
public class MockServiceImpl implements MockService {


    @Resource
    private DubboMockProperties dubboMockProperties;
    @Resource
    private DubboConfigurationProperties dubboConfigurationProperties;
    @Resource
    private GenericServiceInterfaceImpl genericServiceInterface;

    private final List<ServiceConfig<GenericService>> serviceConfigList = new LinkedList<>();

    @PostConstruct
    public void init() {
        // 应用暴露

        // 接口暴露
        exportInterface();
    }

    private void exportInterface() {
        // interface export
        if (MapUtils.isEmpty(dubboMockProperties.getInvocations())) {
            log.info("no dubbo mock");
            return;
        }

        log.info("++++++ dubbo mock export start");

        // 服务实现
        // 应用配置
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(dubboConfigurationProperties.getApplication().getName());

        // 服务提供者协议配置
        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName(dubboConfigurationProperties.getProtocol().getName());
        protocol.setPort(dubboConfigurationProperties.getProtocol().getPort());
//        protocol.setSerialization("fastjson");
        protocol.setThreads(200);
        for (Map.Entry<String, MockInvocation> entry : dubboMockProperties.getInvocations().entrySet()) {
            exportInterfaceSingle(applicationConfig, protocol, entry.getKey(), entry.getValue());
        }
        log.info("++++++ dubbo mock export end\n");
    }

    private void exportInterfaceSingle(ApplicationConfig applicationConfig, ProtocolConfig protocol, String methodName, MockInvocation mockInvocation) {
        // 服务提供者暴露服务配置
        // 此实例很重，封装了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
        ServiceConfig<GenericService> service = new ServiceConfig<>();
        service.setApplication(applicationConfig);
        // 多个注册中心可以用setRegistries()
        service.setRegistries(new ArrayList<>(dubboConfigurationProperties.getRegistries().values()));
        // 多个协议可以用setProtocols()
        service.setProtocol(protocol);
        service.setInterface(mockInvocation.getInterfaceName());
        service.setRef(genericServiceInterface);
        service.setVersion(mockInvocation.getVersion());
        service.setGroup(mockInvocation.getGroup());
        service.setProvider(dubboConfigurationProperties.getProvider());

        MethodConfig methodConfig = new MethodConfig();
//        methodConfig.setName(methodName);
        service.setMethods(Collections.singletonList(methodConfig));
        log.info("++++++ dubbo mock export interface={} method={}", mockInvocation.getInterfaceName(), methodName);
        // 暴露及注册服务
        service.export();

        serviceConfigList.add(service);
    }

    //重新载入
    public void reload(){
        destroy();
        dubboMockProperties.dbReload();
        exportInterface();
    }

    @PreDestroy
    public void destroy() {
        // server unExport

        // interface unExport
        serviceConfigList.forEach(ServiceConfig::unexport);
    }

}
