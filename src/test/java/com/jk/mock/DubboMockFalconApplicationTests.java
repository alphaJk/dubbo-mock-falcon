package com.jk.mock;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jk.mock.mapper.MockInfoMapper;
import com.jk.mock.entity.MockInfo;
import com.jk.mock.service.IMockInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@SpringBootTest
class DubboMockFalconApplicationTests {

	@Resource
	private MockInfoMapper mockInfoMapper;

	@Autowired
	private IMockInfoService iMockInfoService;

	@Test
	void contextLoads() {
		MockInfo mockInfo  = new MockInfo();
		mockInfo.setFunctionName("functionName2");
		mockInfo.setInterfaceName("interfaceName2");
		mockInfo.setResponse(JSONObject.parse("{\"code\": 201}").toJSONString());
		mockInfo.setDubboGroup("group1");
		mockInfo.setVersion("version1");
		mockInfo.setParameterTypes("parameterTypes");
		log.info(mockInfo.toString());
		mockInfoMapper.insert(mockInfo);
	}

	@Test
	void contextLoads2() {
		List<MockInfo> list = iMockInfoService.lambdaQuery()
				.eq(MockInfo::getFunctionName, "functionName1")
				.list();
		list.forEach(System.out::println);
	}

	@Test
	void test2(){
		QueryWrapper<MockInfo> wrapper = new QueryWrapper<>();
		wrapper.eq("interface_name", "com.vaasplus.region.facade.region.RegionFacade");
		wrapper.eq("function_name", "saasGetNowRegion");
		wrapper.eq("parameter_types", "com.vaasplus.region.model.region.req.SaasGetNowRegionReq");
		MockInfo data = iMockInfoService.getOne(wrapper);
		System.out.println(data.getResponse());
	}

}
