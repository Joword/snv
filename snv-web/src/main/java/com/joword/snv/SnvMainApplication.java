package com.joword.snv;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.SpringServletContainerInitializer;

/**
 * @author Joword
 * @date: ${DATE} ${HOUR}:${MINUTE}
 * @version: 1.0
 * @description: main booter
 */
@MapperScan(basePackages = {"com.snv.mgb.mapper"})
@ServletComponentScan
@EnableTransactionManagement
@EnableConfigurationProperties
@SpringBootApplication(scanBasePackages = {"com.snv"})
public class SnvMainApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(SnvMainApplication.class, args);
        System.out.println("SNV project activated now.");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SnvMainApplication.class);
    }
}