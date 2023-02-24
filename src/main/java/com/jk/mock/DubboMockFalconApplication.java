package com.jk.mock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

@MapperScan("com.jk.mock.mapper")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableAsync
@SpringBootApplication
public class DubboMockFalconApplication {

	public static void main(String[] args) {
		SpringApplication.run(DubboMockFalconApplication.class, args);
	}

}
