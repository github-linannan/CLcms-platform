package com.letu.healthplatform.platformmanage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.letu.healthplatform.platformmanage.common.aop.PropertiesConfig;

@EnableScheduling
@EnableAutoConfiguration
@SpringBootApplication
@EnableTransactionManagement
@ServletComponentScan(basePackages={"com.letu.healthplatform.platformmanage"})
@MapperScan(basePackages={"com.letu.healthplatform.platformmanage.*.mapper"})
public class PlatformManageApplication {
    
	public static void main(String[] args) {
		SpringApplication.run(PlatformManageApplication.class, args);
	}
}
