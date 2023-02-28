package com.jk.mock;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jk.mock.dao.RequestHistoryDao;
import com.jk.mock.entity.RequestHistory;
import com.jk.mock.mapper.MockInfoMapper;
import com.jk.mock.entity.MockInfo;
import com.jk.mock.service.IMockInfoService;
import javafx.scene.chart.Chart;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@SpringBootTest
class DubboMockFalconApplicationTests {

	@Resource
	private MockInfoMapper mockInfoMapper;

	@Autowired
	private IMockInfoService iMockInfoService;

	@Autowired
	private RequestHistoryDao requestHistoryDao;

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

	@Test
	void test3(){
		requestHistoryDao.saveOneRequestInfo("1111","String","StringValue","group");
	}

	@Test
	void test4(){
		RequestHistory requestHistory = requestHistoryDao.getLatestOne("core_data");
		System.out.println(requestHistory.getRequestId());
	}

	@Test
	void test5(){
		List<RequestHistory> list = requestHistoryDao.getReqInfoByReqId("21ba8c20cb2541dca618ef6c294bed99");
	}
}
