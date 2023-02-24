package com.jk.mock.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jk.mock.entity.MockInfo;
import com.jk.mock.service.IMockInfoService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jk
 * Date: 2023/2/23
 * Time: 15:54
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Repository
public class MockInfoDao {

    @Resource
    private IMockInfoService mockInfoService;

    public List<MockInfo> getAllMockInfo(){
        return mockInfoService.lambdaQuery().list();
    }

    public MockInfo getOneMockInfo(String interfaceName,String functionName,String parameterTypes){
        QueryWrapper<MockInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("interface_name", interfaceName);
        wrapper.eq("function_name", functionName);
        wrapper.eq("parameter_types", parameterTypes);
        return mockInfoService.getOne(wrapper);
    }

    public boolean updateResponse(String interfaceName,String functionName,String parameterTypes,String response){
        UpdateWrapper<MockInfo> wrapper = new UpdateWrapper<>();
        wrapper.eq("interface_name", interfaceName);
        wrapper.eq("function_name", functionName);
        wrapper.eq("parameter_types", parameterTypes);
        MockInfo entity = new MockInfo();
        entity.setResponse(response);
        return mockInfoService.update(entity, wrapper);
    }

    public boolean updateDubboGroup(String interfaceName,String functionName,String parameterTypes,String dubboGroup){
        UpdateWrapper<MockInfo> wrapper = new UpdateWrapper<>();
        wrapper.eq("interface_name", interfaceName);
        wrapper.eq("function_name", functionName);
        wrapper.eq("parameter_types", parameterTypes);
        MockInfo entity = new MockInfo();
        entity.setDubboGroup(dubboGroup);
        return mockInfoService.update(entity, wrapper);
    }

    public boolean updateVersion(String interfaceName,String functionName,String parameterTypes,String version){
        UpdateWrapper<MockInfo> wrapper = new UpdateWrapper<>();
        wrapper.eq("interface_name", interfaceName);
        wrapper.eq("function_name", functionName);
        wrapper.eq("parameter_types", parameterTypes);
        MockInfo entity = new MockInfo();
        entity.setVersion(version);
        return mockInfoService.update(entity, wrapper);
    }


    public boolean saveOneMockInfo(String interfaceName,String functionName,String parameterTypes,String response,String dubbGroup,String version){
        MockInfo entity = new MockInfo();
        entity.setInterfaceName(interfaceName);
        entity.setFunctionName(functionName);
        entity.setParameterTypes(parameterTypes);
        entity.setResponse(response);
        entity.setDubboGroup(dubbGroup);
        entity.setVersion(version);
        return mockInfoService.save(entity);
    }


    public boolean removeOneMock(String interfaceName,String functionName,String parameterTypes){
        QueryWrapper<MockInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("interface_name", interfaceName);
        wrapper.eq("function_name", functionName);
        wrapper.eq("parameter_types", parameterTypes);
        return mockInfoService.remove(wrapper);
    }
}
